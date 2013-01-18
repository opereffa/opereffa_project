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
package uk.ac.ucl.chime.utilities;

import java.util.ArrayList;

public class CodedTextInfo {
	protected String currentSelectionCode;
	protected String currentSelectionRubric;
	protected ArrayList<String> codesOfCurrentSelections = new ArrayList<String>();
	protected ArrayList<String> namesOfCurrentSelections = new ArrayList<String>();
	public ArrayList<String> codes = new ArrayList<String>();
	public ArrayList<String> values = new ArrayList<String>();
	
	public String getCurrentSelectionCode(){
		return currentSelectionCode;
	}
	
	public void setCurrentSelectionCode(String pCurrentCode){
		currentSelectionCode = pCurrentCode;
	}
	
	public String getCurrentSelectionRubric(){
		return currentSelectionRubric;
	}
	
	public void setCurrentSelectionRubric(String pRubric){
		currentSelectionRubric = pRubric;
	}
	
	public ArrayList<String> getCodesOfCurrentSelections(){
		return codesOfCurrentSelections;
	}
	
	public void setCurrentSelections(ArrayList<String> pCurrentSelections){
		codesOfCurrentSelections = pCurrentSelections;
	}
	
	public ArrayList<String> getNamesOfCurrentSelections(){
		return namesOfCurrentSelections;
	}
	
	public void setNamesOfCurrentSelections(ArrayList<String> pNamesOfCurrentSelections){
		namesOfCurrentSelections = pNamesOfCurrentSelections;
	}
}
