/*******************************************************************************
 * Copyright 2012 Sevket Seref Arikan, David Ingram
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.ac.ucl.chime.wrappers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;

import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.utilities.OrdinalInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class OrdinalValueWrapper extends DataValueWrapper {

//	protected DvOrdinal rmDvOrdinal;	
	public OrdinalValueWrapper(CDvOrdinal pordinal, ArchetypeWrapper pcontainerArchWrapper, RIMWrapper pParent){
		parent = pParent;
		wrappedObject = pordinal;
		//builder = pbuilder;
		containerArchetype = pcontainerArchWrapper;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public Object getRIMValue() {
//		//TODO: we need to save the value from the UI, but RM class does not seem to have the ability???
////		String path = ordinalVal.path();
////		Interval<Integer> occurrences = ordinalVal.getOccurrences();
////		String nodeId = ordinalVal.getNodeID();
////		CAttribute parent = ordinalVal.getParent();
////		Set<Ordinal> list = ordinalVal.getList();
////		CDvOrdinal c = new CDvOrdinal(path, occurrences, nodeId, parent, list,
////				null, null);
////		return c;
//		DvText txt = new DvText(((JComboBox)guiComponent).getSelectedItem().toString());
//		return txt;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		return wrappedObject.getRmTypeName();
	}


	@Override
	public String getGUITestString() {
		StringBuffer ovSB = new StringBuffer();
		Set<Ordinal> set = ((CDvOrdinal)wrappedObject).getList();
		for(Ordinal o : set){
			if(o.getSymbol().getTerminologyId().getValue().equals("local")){
				ovSB.append("This is an ordinal value: " + String.valueOf(o.getValue()) + " " + containerArchetype.getNodeName(o.getSymbol().getCodeString()) + ":" + o.getSymbol().getCodeString());				
			}			
		}		
		return ovSB.toString();
	}

	@Override
	public Object getTestInstance() throws Exception {
		 String type = "DvOrdinal";
	        Map<String, Object> values = new HashMap<String, Object>();
	        TerminologyService ts =  SimpleTerminologyService.getInstance();
	        DvCodedText symbol = new DvCodedText("test for ordinal value", getDefaultLanguage(),
	        		getDefaultEncoding(), new CodePhrase("test terms", "00001"),
	                ts);
	        values.put("symbol", symbol);
	        values.put("value", 1);
	        RMObject obj = getBuilder().construct(type, values);
	        return obj;
	}

	@Override
	public Object getValue() {
		Set<Ordinal> set = ((CDvOrdinal)wrappedObject).getList();					
		OrdinalInfo info = new OrdinalInfo();
		for(Ordinal o : set){
			if(o.getSymbol().getTerminologyId().getValue().equals("local")){				
				info.values.add(String.valueOf(o.getValue()));
				info.labels.add(containerArchetype.getNodeName(o.getSymbol().getCodeString()));				
			}			
		}
		return info;		
	}

	@Override
	public void setValue(Object value) {
		try {
			Set<Ordinal> set = ((CDvOrdinal)wrappedObject).getList();
			for(Ordinal o : set){
				if(o.getSymbol().getTerminologyId().getValue().equals("local")){
					if (String.valueOf(o.getValue()).equals(value)){
						String type = "DvOrdinal";
				        Map<String, Object> values = new HashMap<String, Object>();
				        TerminologyService ts =  SimpleTerminologyService.getInstance();
				        values.put("symbol", o.getSymbol());
				        values.put("value", value);
				        RMObject obj = getBuilder().construct(type, values);
						dataValue = (DvOrdinal) obj;
						break;
					}
				}			
			}
		} catch (RMObjectBuildingException e) {
			// TODO bind to logging
			e.printStackTrace();
		} catch (Exception e) {
			// TODO bind to logging
			e.printStackTrace();
		}		
		
	}
	
	@Override
	public String getJSFWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDataValueWithPersistedValue(ArchetypeData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addValueFromDB(ArchetypeData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveValueToDB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersistedValue() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
