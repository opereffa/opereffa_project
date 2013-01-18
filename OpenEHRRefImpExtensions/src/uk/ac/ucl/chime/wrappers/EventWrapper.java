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
import java.util.List;
import java.util.Map;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.utilities.ItemStructureWrapperFactory;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class EventWrapper extends EHRStructureWrapper {
	
	protected ItemStructureWrapper itemStructureOfEvent;
	
	public EventWrapper(CComplexObject pEvent, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		containerArchetype = pContainerArchetype;
		ccomplexReference = pEvent;
		processEvent();
	}
	
	public void processEvent() throws Exception{
		CSingleAttribute dataAttr = (CSingleAttribute) ccomplexReference.getAttribute("data");
		ItemStructureWrapperFactory wrapperFactory = new ItemStructureWrapperFactory();
		ItemStructureWrapper wrapper = wrapperFactory.getItemStructureWrapper(((CComplexObject)dataAttr.getChildren().get(0)), containerArchetype, this);
		itemStructureOfEvent = wrapper;
	}
	
	@Override
	public String getGUITestString() throws Exception {
		return itemStructureOfEvent.getGUITestString();
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		if (ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in EventWrapper");
	}

	@Override
	public Object getTestInstance() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();        
        TerminologyService ts =  SimpleTerminologyService.getInstance();
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()),
        		getDefaultLanguage(),
        		getDefaultEncoding(), ts);
        ItemStructure data = (ItemStructure) itemStructureOfEvent.getTestInstance();
        DvDateTime time = getCurrentTime();
        values.put("archetypeNodeId", "at0001");
        values.put("name", name);
        values.put("data", data);
        values.put("time", time);
        RMObject obj = getBuilder().construct("PointEvent", values);
        return obj;
	}

	@Override
	public String getJSFWidget() {
		return itemStructureOfEvent.getJSFWidget();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		return castList(itemStructureOfEvent.getChildren(), Object.class);
	}
}
