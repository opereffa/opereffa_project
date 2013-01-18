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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RMBasicTypeName {
	dv_quantity ("quantity"),
	quantity ("quantity"),
	dvquantity("dvquantity"),
	dv_coded_text ("dv_coded_text"),
	dv_text ("dv_text"),
	coded_text ("coded_text"),
	text ("text"),
	dv_boolean ("dv_boolean"),
	bool ("boolean"),
	dv_ordinal ("dv_ordinal"),
	dvordinal("dvordinal"),
	ordinal ("ordinal"),
	dv_date_time ("dv_date_time"),
	dv_date ("dv_date"),
	dv_time ("dv_time"),
	datetime ("datetime"),
	date_time ("date_time"),
	date ("date"),
	time ("time"),
	c_date ("c_date"),
	quantity_ratio ("quantity_ratio"),
	dv_proportion ("dv_proportion"),
	proportion ("proportion"),
	dv_count ("dv_count"),
	count ("count"),
	interval_count ("interval_count"),
	interval_quantity ("interval_quantity"),
	interval_quantity_ ("interval<quantity>"),
	dv_interval_dv_count_ ("dv_interval<dv_count>"),
	dv_interval_dv_quantity_ ("dv_interval<dv_quantity>"), 
	dv_interval_date_time_ ("dv_interval<date_time>"),
	dv_internal_dv_datetime_ ("dv_interval<dv_datetime>"),
	dv_interval_count_ ("dv_interval<count>"),
	dv_interval_quantity_ ("dv_interval<quantity>"),
	interval_datetime ("interval_datetime"),
	interval_datetime_ ("interval<datetime>"),
	dv_multimedia ("dv_multimedia"),
	multimedia ("multimedia"),
	multi_media ("multi_media"),
	dv_uri ("dv_uri"),
	dv_ehr_uri("dv_ehr_uri"),
	uri ("uri"),
	dv_identifier ("dv_identifier"),
	dv_currency ("dv_currency"),
	dv_duration ("dv_duration"),
	duration ("duration");
	
	private String _typeName;
	
	private RMBasicTypeName(){}
	
	RMBasicTypeName(String typeName){
		_typeName = typeName;
	}
	
	public String getTypeName(){
		return _typeName;
	}
	
	public static RMBasicTypeName get(String str){
		return lookup.get(str);
	}
	
	private static final Map<String,RMBasicTypeName> lookup     = new HashMap<String ,RMBasicTypeName>();

	static {
		for(RMBasicTypeName r : EnumSet.allOf(RMBasicTypeName.class))
        lookup.put(r.getTypeName(), r);
	}		
}
