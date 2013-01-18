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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JTextField;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.DvText;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.TextValueInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class TextValueWrapper extends DataValueWrapper {
	
	static Logger logger = Logger.getLogger(TextValueWrapper.class);	
		
	public TextValueWrapper(CObject pvalue, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent){
		parent = pParent;
		logger.setLevel(Level.OFF);
		logger.info("TextValueConstructor Called");
		wrappedObject = pvalue;
		containerArchetype = pContainerArchetype;
	}
	
//	public Object getRIMValue(){
//		//use saved reference to txtbox to get back your text value
//		String txtVal = ((JTextField)guiComponent).getText();
//		DvText value = new DvText(txtVal);
//		return value;
//	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRMTypeName() throws Exception {
		return wrappedObject.getRmTypeName();
	}

	@Override
	public String getGUITestString() {
		if (dataValue != null){
			TextValueInfo textValueInfo = (TextValueInfo) getValue();
			return textValueInfo.getValue();
		}
		CComplexObject complexVal = (CComplexObject) wrappedObject;
		if (!complexVal.isAnyAllowed()){
			CAttribute valAttr = complexVal.getAttribute("value");
			CObject stringObj = ((CSingleAttribute)valAttr).getChildren().get(0);
			List<String> allowedVals = ((CString)((CPrimitiveObject)stringObj).getItem()).getList();
			StringBuffer sb = new StringBuffer();
			sb.append("this is a text value with the following values allowed:");
			for(String s : allowedVals){
				sb.append(s + ",");
			}
			return sb.toString();
		}
		else
			return "";
	}

	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvText";
        Map<String, Object> values = new HashMap<String, Object>();
        String value = "test text value" + String.valueOf(new Random().nextInt(999999));
        CComplexObject complexVal = (CComplexObject) wrappedObject;
        ArrayList<String> allowedValues = new ArrayList<String>();
        if (!complexVal.isAnyAllowed()){
			CAttribute valAttr = complexVal.getAttribute("value");
			CObject stringObj = ((CSingleAttribute)valAttr).getChildren().get(0);
			List<String> allowedVals = ((CString)((CPrimitiveObject)stringObj).getItem()).getList();						
			for(String s : allowedVals){
				allowedValues.add(s);
			}						
		}
        //if there is a set of allowed values, put one of them as value
        if(allowedValues.size() > 0){
        	int allowedValSize = allowedValues.size();
        	int randomIndex = new Random().nextInt(allowedValSize);
        	values.put("value", allowedValues.get(randomIndex));
        }else
        	values.put("value", value);
        RMObject obj = getBuilder().construct(type, values);
        return obj;
	}

	@Override
	public Object getValue() {
		TextValueInfo info = new TextValueInfo();
		CComplexObject complexVal = (CComplexObject) wrappedObject;
		if (!complexVal.isAnyAllowed()){
			CAttribute valAttr = complexVal.getAttribute("value");
			CObject stringObj = ((CSingleAttribute)valAttr).getChildren().get(0);
			List<String> allowedVals = ((CString)((CPrimitiveObject)stringObj).getItem()).getList();						
			for(String s : allowedVals){
				info.allowedValues.add(s);
			}			
			
			if (dataValue != null){
				info.setValue(((DvText)dataValue).getValue());
			}
			else//get the first of options as current selection
				info.setValue(info.allowedValues.get(0));
		}
		else{
			if (dataValue != null){
				info.setValue(((DvText)dataValue).getValue());//all values are allowed
			}
			else
				info.setValue("");//all values are allowed
		}
		return info;
	}
	
	protected void createRMInstance(String pValue) throws RMObjectBuildingException{
		String type = "DvText";
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("value", pValue);
		RMObject obj = getBuilder().construct(type, values);
		dataValue = (DvText) obj;
	}

	@Override
	public void setValue(Object pValue) throws RMObjectBuildingException {
		if (pValue == null || pValue == "")
			return;
			createRMInstance(pValue.toString());
//			saveValueToDB();
	}
	
	public void saveValueToDB(){
		if (dataValue == null)
			return;
		ArchetypeData data = getContextBasedArcetypeData();
		data.setName("value");
		data.setValueString(((DvText)dataValue).getValue());
		archetypeDataInstance = saveWithDelayedPersistenceCheck(data);
	}

	public String getJSFWidget(){
		//based on isAnyAllowed this data type can 
		//be represented with a text box or a combobox
		CComplexObject complexVal = (CComplexObject) wrappedObject;
		String widgetText = "";
		if(!complexVal.isAnyAllowed()){
			widgetText = "<h:selectOneMenu value=\"#{archetypeBinder.dvtext.selectedText.inNode['" + getPath() + "']}\">" + 
					"				<f:selectItems value=\"#{archetypeBinder.dvtext.allowedValues.inNode['" + getPath() + "']}\" />\n" + 
					"				</h:selectOneMenu>";
		}
		else//just a textbox with a single value
			widgetText = "<h:inputText value=\"#{archetypeBinder.dvtext.text.inNode['" + getPath() + "']}\"/>";
		return widgetText;
	}

	@Override
	public void setDataValueWithPersistedValue(ArchetypeData data) throws RMObjectBuildingException {
		createRMInstance(data.getValueString());		
		archetypeDataInstance = data;
	}

	@Override
	public void addValueFromDB(ArchetypeData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersistedValue() throws Exception {
		if (archetypeDataInstance == null)
			throw new Exception("No data provided by persistence in BooleanValueWrapper");
		if (dataValue != null)
			saveValueToDB();
		else{
			//we have null data value, and a db row! this is interpreted as delete row, since null is assigned to it as value
			//make sure this happens in an update
			if (containerArchetype.getMode().equals(ArchetypeWrapper.MODE_UPDATE)){
				ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
				dataDAO.getSession().getTransaction().begin();
				//use archetype creation date in container archetype
				archetypeDataInstance.setDeleted(true);
				dataDAO.attachDirty(archetypeDataInstance);
				dataDAO.getSession().getTransaction().commit();
			}
		}
	}
}
