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
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.Cardinality;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class HistoryWrapper extends EHRStructureWrapper {
		
	protected ArrayList<EventWrapper> eventWrappers = new ArrayList<EventWrapper>();
//	private Cardinality cardinality;
	
	public HistoryWrapper(CComplexObject pHistoryObject, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pHistoryObject;
		containerArchetype = pContainerArchetype;
		processHistoryWrapper();
	}
	
	protected void processHistoryWrapper() throws Exception{
		CMultipleAttribute eventsAttr = (CMultipleAttribute) ccomplexReference.getAttribute("events");
//		Cardinality cardinality = eventsAttr.getCardinality();
//		if( cardinality != null ){
//			processCardinality(cardinality);
//		}
		for(CObject eventObj : eventsAttr.getChildren())
		{
			EventWrapper eWrapper = new EventWrapper((CComplexObject) eventObj, containerArchetype, this);
			eventWrappers.add(eWrapper);
		}
	}
	
//	protected void processCardinality(Cardinality pCardinality) {
//		cardinality = pCardinality;
//		Interval<Integer> interval = cardinality.getInterval();
//		if (interval.isLowerIncluded() && interval.getLower() == 1 && !interval.isUpperIncluded()){
//			//a container with a single item
//			System.out.println("A single events");
//		}
//		else if(interval.isLowerIncluded()){
//			
//		}
//		
//		
//		
//	}

	@Override
	public String getGUITestString() throws Exception {
		StringBuffer sb = new StringBuffer();
		for (EventWrapper w : eventWrappers){
			sb.append(w.getGUITestString() + "\n");
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
//		return null;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		if (ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in HistoryWrapper");
	}

	@Override
	public Object getTestInstance() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = ccomplexReference.getNodeID();
        TerminologyService ts =  SimpleTerminologyService.getInstance();
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()),
        		getDefaultLanguage(),
        		getDefaultEncoding(), ts);
        DvDateTime origin = getCurrentTime();
        List<Event<ItemSingle>> events = new ArrayList<Event<ItemSingle>>();
        for (EventWrapper wrapper : eventWrappers){
        	events.add((Event<ItemSingle>) wrapper.getTestInstance());
        }
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("origin", origin);
        values.put("events", events);
        RMObject obj = getBuilder().construct("History", values);
        return obj;
	}

	@Override
	public String getJSFWidget() {
		StringBuffer sb = new StringBuffer();
		for (EventWrapper eventWrapper : eventWrappers ){
			sb.append(eventWrapper.getJSFWidget());
		}
		return sb.toString();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		return castList(castList(eventWrappers, RIMWrapper.class), Object.class);
	}
}
