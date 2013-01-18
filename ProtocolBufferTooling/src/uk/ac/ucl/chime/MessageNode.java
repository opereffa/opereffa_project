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
package uk.ac.ucl.chime;

import com.google.protobuf.Descriptors.Descriptor;

public class MessageNode extends PBNode{
	private Descriptor _nodeDescriptor;
	
	
	/*
	 * Gets the full type name for message types, excluding the package name
	 * @return Full type name excludign the package name 
	 */
	public String getFullTypeNameWOPackage(){
		String name =  getTypeNameWithParents(_nodeDescriptor);
		System.out.println("full type name: " + name);
		return name;
	}
	
	/*
	 * Walks up the descriptor hieararchy to generate full type names, without the package name.
	 * 
	 * @return Type name starting from the highest level message.
	 */
	private String getTypeNameWithParents(Descriptor pNodeDescriptor){
		if(pNodeDescriptor.getContainingType() != null){
			return getTypeNameWithParents(pNodeDescriptor.getContainingType()) + 
			_typeNameSeparator + 
			pNodeDescriptor.getName();
		}else
			return pNodeDescriptor.getName() == null ? "" : pNodeDescriptor.getName();
	}
	
	/*
	 * Sets the separator used between type names, when getting full type name
	 * 
	 * @param pSeperator Character to use between types
	 */
	public void setTypeNameSeparator(String pSeparator){
		_typeNameSeparator = pSeparator;
	}
	
	/*
	 * Sets the Descriptor instance that describes this message type
	 * @param pDescriptor The descriptor instance.
	 */
	public void setDescriptor(Descriptor pDescriptor){
		_nodeDescriptor = pDescriptor;
	}
	
	/*
	 * Provides access to descriptor for this message type
	 * @return A Descriptor instance
	 */
	public Descriptor getDescriptor(){
		return _nodeDescriptor;
	}

	@Override
	public void accept(IPBNodeVisitor pNodeVisitor) throws Exception {
		pNodeVisitor.visit(this);		
	}
	
}
