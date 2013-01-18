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

import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.PartyRef;

import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public abstract class EHRStructureWrapper extends RIMWrapper {

	protected ArchetypeWrapper containerArchetype;

	//this is the reference to AOM object wrapped by EHRStructureWrapper descendents
	protected CComplexObject ccomplexReference; 
	
	//dummy subject
	protected PartySelf getDefaultSubject() throws Exception {
		PartyRef party = new PartyRef(new HierObjectID("1.2.4.5.6.12.1"),
				"PARTY");
		return new PartySelf(party);
	}

	// dummy provider
	protected PartyIdentified getDefaultProvider() throws Exception {
		PartyRef performer = new PartyRef(new HierObjectID("1.3.3.1"),
				"ORGANISATION");
		return new PartyIdentified(performer, "provider's name", null);
	}
	
	//dummy time
	protected DvDateTime getCurrentTime(){
		String year  = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		//"-" ;
		String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
		if(month.length() < 2)
			month = "0" + month;
//		"-" + 
		String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		if (day.length() < 2)
			day = "0" + day;
		
		//dateStr = "2009-02-22T12:00:03";
		String dateStr = year + "-" + month + "-" + day + "T12:00:03"; 
		return new DvDateTime(dateStr);
	}
	
	public String getName(){
		return containerArchetype.getNodeName(ccomplexReference.getNodeID());
	}

}
