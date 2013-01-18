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
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;

public class DataNode extends PBNode {

	private FieldDescriptor _fieldDescriptor;
	private MessageNode _messageNode;
	private boolean _isMessageNode = false;	
	
	public void setFieldDescriptor(FieldDescriptor pFieldDescriptor){
		_fieldDescriptor = pFieldDescriptor;
	}
	
	public FieldDescriptor getFieldDescriptor(){
		return _fieldDescriptor;
	}
	
	public void setMessageNode(MessageNode pMessageNode){
		_messageNode = pMessageNode;
		_isMessageNode = true;
	}
	
	public MessageNode getMessageNode(){
		return _messageNode;
	}
	
	public boolean isEnumNode(){
		return (_fieldDescriptor.getType().compareTo(Type.ENUM) == 0);
	}
		
	
	public boolean isMessageNode(){
		return _isMessageNode;
	}

	@Override
	public void accept(IPBNodeVisitor pNodeVisitor) throws Exception {
		pNodeVisitor.visit(this);		
	}
	
	public boolean isRepeated(){
		return _fieldDescriptor.isRepeated();
	}
	
	/*
	 * Gets the full type name for message types, excluding the package name
	 * @return Full type name excludign the package name 
	 */
	public String getFullTypeNameWOPackage(){
		if(isMessageNode())
			return _messageNode.getFullTypeNameWOPackage();
		else if(isEnumNode()){
			if(_fieldDescriptor.getEnumType().getContainingType() == null)//no parent types
			{
				return _fieldDescriptor.getEnumType().getName();
			}
			else{
				return getTypeNameWithParents(_fieldDescriptor.getEnumType().getContainingType()) + 
				_typeNameSeparator +
				_fieldDescriptor.getEnumType().getName();
			}
			
		}
		else
			_fieldDescriptor.getType().name();
		return "UNKNOWN TYPE";
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
}
