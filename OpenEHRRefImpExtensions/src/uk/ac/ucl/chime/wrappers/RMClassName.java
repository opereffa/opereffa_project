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

public enum RMClassName {
	CLUSTER ("CLUSTER"),
	ELEMENT ("ELEMENT"),
	ITEM_TREE ("ITEM_TREE"),
	ITEM_LIST ("ITEM_LIST"),
	ITEM_SINGLE ("ITEM_SINGLE"),
	ITEM_TABLE ("ITEM_TABLE");
	
	private String _clsName;
	
	RMClassName(String clsName){
		_clsName = clsName;
	}
	
//	public String className(){ return _clsName; }
}
