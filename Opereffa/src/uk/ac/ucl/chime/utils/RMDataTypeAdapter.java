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
package uk.ac.ucl.chime.utils;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;



public abstract class RMDataTypeAdapter {

	protected ArchetypeWrapper archetypeWrapper;
	protected String operationName;
	protected String targetNodePath;
	protected boolean executeNext = false;
	
	public String getOperationName(){
		return operationName;
	}
	
	public void setOperationName(String pOperationName){
		operationName = pOperationName;
	}
	
	//this method keeps whatever propery that arrives
	//as long as it is before inNode, inNode signals that the next parameter will be the 
	//archetype node to operate on
	public Object getValue(String pParameter) throws Exception{
		if (executeNext){
			targetNodePath = pParameter;
			return getValue();
		}			
		if (pParameter.equals("inNode")){
			executeNext = true;
			return this;
		}
		else{
			operationName = pParameter;
			return this;
		}		
	}
	
	protected abstract Object getValue() throws Exception;
	
	protected abstract void setValue(String pNodePath, Object pValue) throws Exception;
	
	public ArchetypeWrapper getArchetypeWrapper(){
		return archetypeWrapper;
	}
	
}
