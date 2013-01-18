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

import org.omg.CORBA.INTERNAL;

public class TimeUnits {

	private static boolean arrayInitialized = false;
	private static ArrayList<String[]> timeUnits = new ArrayList<String[]>();
	
	public static String UnitName_Microsec = "microsec";
	public static String UnitVal_Microsec = "microsec";

	public static String UnitName_Millisec = "millisec";
	public static String UnitVal_Millisec = "millisec";
	
	public static String UnitName_Sec = "sec";
	public static String UnitVal_Sec = "s";
	
	public static String UnitName_Min = "min";
	public static String UnitVal_Min = "min";
	
	public static String UnitName_Hr = "hr";
	public static String UnitVal_Hr = "h";
	
	public static String UnitName_Days = "day(s)";
	public static String UnitVal_Days = "d";
	
	public static String UnitName_Mth = "mth";
	public static String UnitVal_Mth = "mo";
	
	public static String UnitName_Yr = "yr";
	public static String UnitVal_Yr = "a";
	
	public static String UnitName_Wk = "wk";
	public static String UnitVal_Wk = "wk";
	
	
	
	
	public static String getLanguageStringForIso(String s){
		if (!arrayInitialized)
			initArray();
		for(String[] nameValueArr : timeUnits){
			if(nameValueArr[1].equals(s))
				return nameValueArr[0];
		}
		return "ERROR COULD NOT FIND TIME UNIT";
	}

	private static void initArray() {
		timeUnits.add(new  String[]{UnitName_Microsec, UnitVal_Millisec});
		timeUnits.add(new  String[]{UnitName_Millisec,UnitVal_Millisec});
		timeUnits.add(new  String[]{UnitName_Sec, UnitVal_Sec});
		timeUnits.add(new  String[]{UnitName_Min, UnitVal_Min});		
		timeUnits.add(new  String[]{UnitName_Hr, UnitVal_Hr});		
		timeUnits.add(new  String[]{UnitName_Days, UnitVal_Days});
		timeUnits.add(new  String[]{UnitName_Mth, UnitVal_Mth});		
		timeUnits.add(new  String[]{UnitName_Yr, UnitVal_Yr});
		timeUnits.add(new  String[]{UnitName_Wk, UnitVal_Wk});			
	}
		
}
