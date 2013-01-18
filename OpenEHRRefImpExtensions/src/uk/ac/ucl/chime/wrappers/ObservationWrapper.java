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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ObservationWrapper extends EHRStructureWrapper {
	
	protected HistoryWrapper historyWrapper;
	
	public ObservationWrapper(CComplexObject pObservationObject, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pObservationObject;
		containerArchetype = pContainerArchetype;
		processObservation();
	}
	
	protected void processObservation() throws Exception {
		List<CObject> dataAttrChildren = ccomplexReference.getAttribute("data").getChildren();
		if (dataAttrChildren.size() > 1)
			throw new Exception("Unhandled case: Observation data attribute with multiple values");
		CComplexObject historyObj = (CComplexObject) ccomplexReference.getAttribute("data").getChildren().get(0);
		HistoryWrapper hWrapper = new HistoryWrapper(historyObj, containerArchetype, this);
		historyWrapper = hWrapper;
	}

	@Override
	public String getGUITestString() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(historyWrapper.getGUITestString() + "\n");
		return sb.toString();
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
			throw new Exception("Could not get RMTypeName in ObservationWrapper");
	}

	@Override
	public Object getTestInstance() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		String node = ccomplexReference.getNodeID();
        TerminologyService ts =  SimpleTerminologyService.getInstance();
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()), 
        		getDefaultLanguage(),
        		getDefaultEncoding(), ts);
        Archetyped archeDetails = new Archetyped(containerArchetype.getArchetype().getArchetypeId(),"v1.0");
        History<ItemStructure> data = ((History)historyWrapper.getTestInstance());
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archeDetails);
        values.put("name", name);
        values.put("language", getDefaultLanguage());
        values.put("encoding", getDefaultEncoding());
        values.put("subject", getDefaultSubject());
        values.put("provider", getDefaultProvider());
        values.put("data", data);
        RMObject obj = getBuilder().construct("Observation", values);
        return obj;
	}

	@Override
	public String getJSFWidget() {		
		return historyWrapper.getJSFWidget();
	}
	
	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		ArrayList<RIMWrapper> historyWrapperList = new  ArrayList<RIMWrapper>();
		historyWrapperList.add(historyWrapper);
		return castList(historyWrapperList, Object.class);
	}
}
