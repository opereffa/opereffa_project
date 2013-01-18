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
package uk.ac.ucl.chime.opereffa.termdata;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "terminologyitem")
public class TerminologyItem {
	private String _id;
	private String _code;
	private String _anotomicalArea;
	private String _diagnosisCode;
	private String _snomedPreferredName;
	private ArrayList<String> _snomedTerms;
	
	public TerminologyItem(){}
		
	public TerminologyItem(String pId, String pCode, String pAnotomicalArea, 
						String pDiagnosisCode, String pSnomedPreferredName, ArrayList<String> pSnomedTerms){
		_id = pId;
		_code = pCode;
		_anotomicalArea = pAnotomicalArea;
		_diagnosisCode = pDiagnosisCode;
		_snomedPreferredName = pSnomedPreferredName;
		_snomedTerms = pSnomedTerms;
	}
	
	public void setId(String pId) {
		this._id = pId;
	}
	
	@XmlElement
	public String getId() {
		return _id;
	}
	
	@XmlElement
	public String getCode(){
		return _code;
	}
	
	public void setCode(String pCode){
		_code = pCode;
	}
	
	@XmlElement
	public String getAnotomicalArea(){
		return _anotomicalArea;
	}
	
	public void setAnotomicalArea(String pAnotomicalArea){
		_anotomicalArea = pAnotomicalArea;
	}
	
	@XmlElement
	public String getDiagnosisCode(){
		return _diagnosisCode;
	}
	
	public void setDiagnosisCode(String pDiagnosisCode){
		_diagnosisCode = pDiagnosisCode;
	}
	
	@XmlElement
	public String getSnomedPreferredName(){
		return _snomedPreferredName;
	}
	
	public void setSnomedPreferredName(String pSnomedPreffredName){
		_snomedPreferredName = pSnomedPreffredName;
	}
	
	@XmlElement (name="snomedterms")
	public List<String> getSnomedTerm(){
		return _snomedTerms;
	}
	
	public void setSnomedTerm(String[] pSnomedTerm){
		for(String term : pSnomedTerm){
			_snomedTerms.add(term);
		}
	}
		
}


