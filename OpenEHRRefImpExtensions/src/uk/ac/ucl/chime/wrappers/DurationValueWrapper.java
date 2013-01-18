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
import java.util.Map;
import java.util.Vector;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.DurationInfo;

public class DurationValueWrapper extends DataValueWrapper {
	
	protected String lengthOfTime = null;
	protected String unitOfTime = null;
	public DurationValueWrapper(CComplexObject pvalue){
		wrappedObject = pvalue;
	}	

	protected CDuration getCDurationFromWrappedValue() {
		return (CDuration) ((CPrimitiveObject)((CComplexObject)wrappedObject).getAttributes().get(0).getChildren().get(0)).getItem();
	}

	protected Vector<ComboBoxItem> getComboBoxItemsForAllowedUnits(
			String allowedUnits) {
		Vector<ComboBoxItem> items = new Vector<ComboBoxItem>();
		char[] charsArr = allowedUnits.toCharArray();
		//the implementation is  similar to one in Ocean's archetype designer
		boolean time = false;
		for(char c : charsArr){
			String s = String.valueOf(c).toLowerCase();
			if (s.equals("t")){
				time = true;				
			}
			else if (!s.equals("p")){
				if (s.equals("m")){
					if (time)
						s = "min";
					else
						s = "mo";
				} else if (s.equals("y"))
					s = "a";
				else if (s.equals("w" ))
					s = "wk";
				ComboBoxItem item = new ComboBoxItem(TimeUnits.getLanguageStringForIso(s), String.valueOf(c));
				items.add(item);
			}
		}
		return items;
	}
	
	protected ArrayList<String> getStringTuplesForAllowedUnits(
			String allowedUnits) {
		ArrayList<String> items = new ArrayList<String>();
		char[] charsArr = allowedUnits.toCharArray();
		//following ripped from Ocean's archetype designer
		boolean time = false;
		for(char c : charsArr){
			String s = String.valueOf(c).toLowerCase();
			if (s.equals("t")){
				time = true;				
			}
			else if (!s.equals("p")){
				if (s.equals("m")){
					if (time)
						s = "min";
					else
						s = "mo";
				} else if (s.equals("y"))
					s = "a";
				else if (s.equals("w" ))
					s = "wk";
				String tupple = TimeUnits.getLanguageStringForIso(s) + ":" + String.valueOf(c);
				items.add(tupple);
			}
		}
		return items;
	}
	
	public String[] getAllowedDurationUnits(){
		return null;
	}

//	@Override
//	public Object getRIMValue() throws Exception {
//		String type = "DvDuration";
//		Map<String, Object> values = new HashMap<String, Object>();
//        values.put("value", "P10DT20H30M40S");
//        RMObject obj = builder.construct(type, values);			
//        return obj;
//	}
	
	protected int getDurationLengthForTimeUnit(String timeUnitLangString, DvDuration pduration){
		//TODO: very ugly trick, handle duration values properly
		try{
			if (timeUnitLangString.equals(TimeUnits.UnitName_Days))
				return pduration.getDays();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Hr))
				return pduration.getHours();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Min))
				return pduration.getMinutes();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Sec))
				return pduration.getSeconds();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Microsec))
				return 0;
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Mth))
				return pduration.getMonths();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Yr))
				return pduration.getYears();
			else if (timeUnitLangString.equals(TimeUnits.UnitName_Wk))
				return pduration.getWeeks();
			return 0;
		}
		catch(Exception ex){
			return 0;
		}
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRMTypeName() throws Exception {
		if (wrappedObject != null)
			return wrappedObject.getRmTypeName();
		else throw new Exception("Could not get RMTypeName in DurationValueWrapper");
	}

	@Override
	public String getGUITestString() {
		StringBuffer dvWrSB = new StringBuffer();
		CDuration duration = getCDurationFromWrappedValue();
		String allowedUnits = duration.getPattern();
		ArrayList<String> tupples = getStringTuplesForAllowedUnits(allowedUnits);
		for(String s : tupples)
			dvWrSB.append(s);
		return dvWrSB.toString();
	}

	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvDuration";
        // without referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", "P10DT20H30M40S");
        RMObject obj = getBuilder().construct(type, values);
        return obj;
	}

	@Override
	public Object getValue() {
		//TODO: should be context dependent-> return default val or actual etc..
		DurationInfo info = new DurationInfo();
		info.setLength("1");
		info.setUnit("days");
		return info;
	}

	@Override
	public void setValue(Object pValue) {
	//not valid for this type, we can't use a single value for duration		
		//actually we can pull this to elementwrapper level, but that'd be taking the responsibility away from this
		//class, I would rather not do that.
	}
	
	public void setValue(){
		try {
			if (lengthOfTime == null || unitOfTime == null)
				return;
			//TODO: process them and create rm instance PROPERLY
			//default value for now...
			String type = "DvDuration";
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("value", "P10DT20H30M40S");
			RMObject obj = getBuilder().construct(type, values);
			dataValue = (DvDuration) obj;
//			saveValueToDB();
		} catch (RMObjectBuildingException e) {
			// TODO bind to logging
			e.printStackTrace();
		}
	}
	
	public void saveValueToDB(){
		//TODO: implement
	}
	
	public void setLengthOfTime(String pLength){
		lengthOfTime = pLength;
		//call set value, which should work if both unit and time is ready
		setValue();
	}
	
	public void setUnitOfTime(String pUnit){
		unitOfTime = pUnit;
		//call set value, which should work if both unit and time is ready
		setValue();
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
	public void updatePersistedValue() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
