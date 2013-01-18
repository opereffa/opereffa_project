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
import java.util.Random;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class InstructionWrapper extends EHRStructureWrapper {
	
	protected ArrayList<ActivityWrapper> activities = new ArrayList<ActivityWrapper>();
	
	public InstructionWrapper(CComplexObject pInstructionObject, ArchetypeWrapper pInstArchWrapper, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pInstructionObject;
		containerArchetype = pInstArchWrapper;		
		processInstructionWrapper();
	}
	
	protected void processInstructionWrapper()throws Exception{
		CMultipleAttribute activitiesAttr =  (CMultipleAttribute) ccomplexReference.getAttribute("activities");
		for(CObject activity : activitiesAttr.getChildren()){
			ActivityWrapper aw = new ActivityWrapper((CComplexObject)activity, containerArchetype, this);
			activities.add(aw);
		}
	}
		
	@Override
	public String getGUITestString() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(ActivityWrapper wrapper : activities){
			sb.append(wrapper.getGUITestString() + "\n");
		}
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
			throw new Exception("Could not get RMTypeName in InstructionWrapper");
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
        DvText narrative = new DvText("some instruction", new CodePhrase("ISO_639-1", "en"),
        		new CodePhrase("IANA_character-sets",
    			"UTF-8"), ts);
        List<Activity> activityList = new ArrayList<Activity>();
        for (ActivityWrapper wrapper : activities){
        	activityList.add((Activity) wrapper.getTestInstance());
        }
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archeDetails);
        values.put("name", name);
        values.put("language", getDefaultLanguage());
        values.put("encoding", getDefaultEncoding());
        values.put("subject", getDefaultSubject());
        values.put("provider", getDefaultProvider());
        values.put("narrative", narrative);
        values.put("activities", activityList);
        RMObject obj = getBuilder().construct("Instruction", values);
        return obj;
	}

	@Override
	public String getJSFWidget() {
		StringBuffer sb = new StringBuffer();
		for (ActivityWrapper wrapper : activities){
			sb.append(wrapper.getJSFWidget());
		}
		return sb.toString();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		return castList(activities, Object.class);
	}
	
}
