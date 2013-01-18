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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.ConstraintRef;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

/*
 * This class is used for analysing the distribution of data types to archetypes, 
 * Instead of throwing exceptions for the types it can't handle, it just records them
 */
public class AnalysisElementWrapper  extends ElementWrapper{

	public AnalysisElementWrapper(CComplexObject obj, ArchetypeWrapper pArchetypeWrapper, boolean pHasContainerTypeParent, IWrapperNavigator pParent) throws Exception{
		super (obj, pArchetypeWrapper, pHasContainerTypeParent, pParent);
	}
	protected void processElement() throws Exception{
		if (ccomplexReference == null)
			throw new Exception("Null element in element wrapper");
		if (!ccomplexReference.isAnyAllowed()){
			CAttribute valueAtr = ccomplexReference.getAttribute("value");
			if (valueAtr != null){
				List<CObject> children = valueAtr.getChildren();
				Iterator<CObject> childrenIterator = children.iterator();
				while(childrenIterator.hasNext()){
					//we know dataValueWrapper will be null, since this is what processValue returns
					System.out.println("now processing: " + containerArchetype.getNodeName(ccomplexReference.getNodeID()));
					dataValueWrapper = processValue(childrenIterator.next());					
				}											
			}			
			else //TODO: how do we encounter this condition?
				dataValueWrapper = null;
		}
		else //TODO: how do we encounter this condition
			dataValueWrapper = null;		
	}	
		
	protected DataValueWrapper processValue(CObject value) throws Exception{
		
		RMBasicTypeName typeName = RMBasicTypeName.get(value.getRmTypeName().toLowerCase());
		if (typeName == null)
			throw new Exception("Unknown type when constructing elementwrapper");
		//if java 5 allowed switches over enumsets, that'd be delicious.
		//for now I have to map multiple cases to same method. ugly...
		switch (typeName) {
		case dv_quantity:
			return processQuantity(value);
		case dvquantity:
			return processQuantity(value);
		case quantity:
			return processQuantity(value);
		case dv_coded_text:
			return processText(value);
		case dv_text:
			return processText(value);
		case coded_text:
			return processText(value);
		case text:
			return processText(value);
		case dv_boolean:
			return processBoolean(value);
		case bool:
			return processBoolean(value);
		case dv_ordinal:
			return processOrdinal(value);
		case dvordinal:
			return processOrdinal(value);
		case ordinal:
			return processOrdinal(value);
		case dv_date_time:
			return processDateTime(value);
		case dv_date:
			return processDateTime(value);
		case dv_time:
			return processDateTime(value);
		case datetime:
			return processDateTime(value);
		case date:
			return processDateTime(value);
		case time:
			return processDateTime(value);
		case c_date:
			return processDateTime(value);
		case quantity_ratio:
			return processRatio(value);
		case dv_proportion:
			return processProportion(value);
		case proportion:
			return processProportion(value);
		case dv_count:
			return processCount(value);
		case count:
			return processCount(value);
		case interval_count:
			return processInterval(value);
		case interval_quantity:
			return processInterval(value);
		case dv_interval_dv_count_:
			return processInterval(value);
		case dv_interval_dv_quantity_:
			return processInterval(value);
		case dv_internal_dv_datetime_:
			return processInterval(value);
		case dv_interval_count_:
			return processInterval(value);
		case dv_interval_quantity_:
			return processInterval(value);
		case dv_interval_date_time_:
			return processInterval(value);
		case interval_datetime:
			return processInterval(value);
		case dv_multimedia:
			return processMultimedia(value);
		case multimedia:
			return processMultimedia(value);
		case multi_media:
			return processMultimedia(value);
		case uri:
			return processUri(value);
		case dv_uri:
			return processUri(value);
		case dv_ehr_uri:
			return processUri(value);
		case dv_identifier:
			return processIdentifier(value);
		case dv_currency:
			return processCurrency(value);
		case dv_duration:
			return processDuration((CComplexObject) value);
		case duration:
			return processDuration((CComplexObject) value);
		default:
			throw new Exception("unknown type");
		}
	}
	
	protected void processRMTypeName(String pTypeName){
		System.out.println(pTypeName);
	}
	
	protected DataValueWrapper processCurrency(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processIdentifier(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processUri(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processMultimedia(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;		
	}

	protected DataValueWrapper processInterval(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null; 
	}

	protected DataValueWrapper processCount(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processProportion(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processRatio(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processDateTime(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processOrdinal(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processBoolean(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processText(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}

	protected DataValueWrapper processQuantity(CObject value) {
		processRMTypeName(value.getRmTypeName());
		return null;
	}
	
	protected DataValueWrapper processDuration(CComplexObject obj) {
		processRMTypeName(obj.getRmTypeName());
		return null;
	}

}

