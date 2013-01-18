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

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="terminologyitemlist")
public class TerminologyItemList {
	
	private List<TerminologyItem> _terminologyItems;
	
	public TerminologyItemList(){}
	
	public TerminologyItemList(List<TerminologyItem> pTermItemList){
		_terminologyItems = pTermItemList;
	}
	
	@XmlElement(name="terminologyitems")
	public List<TerminologyItem> getTerminologyItems(){
		return _terminologyItems;
	}
}
