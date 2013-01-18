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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;

import com.google.protobuf.DescriptorProtos.EnumOptions;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;

public class EiffelWrapperCreatorVisitor implements IPBNodeVisitor {
		
	private EiffelWrapperGenerator _generator;	
	private String _mainEiffelType = "";		
	
	public EiffelWrapperCreatorVisitor(){
		_generator = new EiffelWrapperGenerator();
	}
	
	public String getMainEiffelType(){
		return _mainEiffelType;
	}		
	
	public String getEiffelType(Type pType){
		if(pType.equals(Type.INT32))
			return "INTEGER_32";
		else if(pType.equals(Type.STRING))
			return "STRING";
		else if(pType.equals(Type.ENUM))
			return "INTEGER";
		else
			return "UNSUPPORTED_TYPE";
	}
	
	private void addIntRelatedContent(String pFieldName, String pCppMsgType){
		_generator.addIntGetter(pFieldName);
		_generator.addIntSetter(pFieldName);
		_generator.addCppIntGetter(pFieldName, pCppMsgType);
		_generator.addCppIntSetter(pFieldName, pCppMsgType);
	}
	
	private void addBooleanRelatedContent(String pFieldName, String pCppMsgType){
		_generator.addBooleanGetter(pFieldName);
		_generator.addBooleanSetter(pFieldName);
		_generator.addCppBooleanGetter(pFieldName, pCppMsgType);
		_generator.addCppBooleanSetter(pFieldName, pCppMsgType);
	}
	
	private void addIntCollectionRelatedContent(String fieldName, String parentCppType) {				
		_generator.addIntegerInstance(fieldName);
		_generator.addCppAddIntegerInstance(fieldName, parentCppType);
		
		_generator.addGetIntFieldSizeMethod(fieldName);
		_generator.addCppGetIntegerFieldSizeMethod(fieldName, parentCppType);
		
		_generator.addIntegerAtIndexMethod(fieldName);
		_generator.addCppGetIntegerFieldAtMethod(fieldName, parentCppType);
		
	}
	
	private void addBooleanCollectionRelatedContent(String fieldName, String parentCppType) {				
		_generator.addBooleanInstance(fieldName);
		_generator.addCppAddBooleanInstance(fieldName, parentCppType);
		
		_generator.addGetBooleanFieldSizeMethod(fieldName);
		_generator.addCppGetBooleanFieldSizeMethod(fieldName, parentCppType);
		
		_generator.addBooleanAtIndexMethod(fieldName);
		_generator.addCppGetBooleanFieldAtMethod(fieldName, parentCppType);
		
	}
	
	private void addStringCollectionRelatedContent(String fieldName, String parentCppType) {				
		_generator.addStringInstance(fieldName);
		_generator.addCppAddStringInstance(fieldName, parentCppType);
		
		_generator.addGetStringFieldSizeMethod(fieldName);
		_generator.addCppGetStringFieldSizeMethod(fieldName, parentCppType);
		
		_generator.addStringAtIndexMethod(fieldName, parentCppType);
		_generator.addCppGetStringFieldAtMethod(fieldName, parentCppType);
		
	}
	
	private void addStringRelatedContent(String pFieldName, String pMessageType){
		_generator.addStringGetter(pFieldName);
		_generator.addStringSetter(pFieldName);
		_generator.addCppStringGetter(pFieldName, pMessageType);
		_generator.addCppStringSetter(pFieldName, pMessageType);		
	}
	
	private void addEnumRelatedContent(String pFieldName,String pCppFieldType, 
										EnumValueDescriptor pDefaultVal, 
										List<EnumValueDescriptor> pList, String pParentCppType){
		//val fields
		for(EnumValueDescriptor d : pList){
			_generator.addEnumValField(pFieldName, d.toProto().getName(), String.valueOf(d.toProto().getNumber()));			
		}
		
		String enumDefaultVal = String.valueOf(pDefaultVal.getNumber());
		
		//enum getter
		_generator.addEnumGetter(pFieldName);
		//enum getter cpp
		StringTemplate enumGetterCpp = null;
		
		ArrayList<StringTemplate> getterElseIfs = new ArrayList<StringTemplate>();
		//iterate over enum fields and generate required snippets
		for(int i = 0; i < pList.size(); i++){
			String enumFieldName = pCppFieldType + "_" + pList.get(i).toProto().getName();
			String enumValueName = String.valueOf(pList.get(i).toProto().getNumber());
			if(i == 0){				
				enumGetterCpp = _generator.getCppEnumGetterIfStatement(pFieldName, enumFieldName, enumValueName);
			}
			else{
				StringTemplate st = _generator.getCppEnumGetterElseIfStatement(pFieldName, enumFieldName, enumValueName);
				getterElseIfs.add(st);
			}
		}
		_generator.addCppEnumGetter(pFieldName,enumGetterCpp , pParentCppType, enumDefaultVal, getterElseIfs);
		
		//enum setter
		_generator.addEnumSetter(pFieldName);
		//enum setter cpp
		StringTemplate enumSetterCpp = null;		
		ArrayList<StringTemplate> setterElseIfs = new ArrayList<StringTemplate>();
		//iterate over enum fields and generate required snippets
		for(int i = 0; i < pList.size(); i++){
			String enumValueName = pCppFieldType + "_" + pList.get(i).toProto().getName();
			String enumIntValue = String.valueOf(pList.get(i).toProto().getNumber());
			if(i == 0){				
				enumSetterCpp = _generator.getCppEnumSetterIfStatement(enumValueName, enumIntValue);
			}
			else{
				StringTemplate st = _generator.getCppEnumSetterElseIfStatement(enumValueName, enumIntValue);
				setterElseIfs.add(st);
			}
		}
		_generator.addCppEnumSetter(pFieldName,enumSetterCpp , pParentCppType, pCppFieldType, setterElseIfs);
		
	}
	
	@Override
	public void visit(PBNode pNode) {
		System.out.println("Generic pNode visited, should not be used normally");
	}

	@Override
	public void visit(MessageNode pNode) throws Exception {				
		_mainEiffelType = pNode.getDescriptor().getName().toUpperCase() + "_WRAPPER_GEN";		
		_generator.setTypeName(_mainEiffelType);						
		_generator.addCppCreateObjectFunction( pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppSerializeObjectFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppSerializeObjectToStringFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppGetObjectSizeFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppReleaseObjectFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppDeserializePBObjectFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		_generator.addCppDeserializePBObjectFromStrFunction(pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage());
		
		for(PBNode child : pNode.getChildren()){
			child.accept(this);
		}
		
		//WHEN ALL CHILDREN ARE VISITED, SET VALUES IN GENERATOR
		_generator.setWrapperValues();
	}

	@Override
	public void visit(DataNode pNode) throws Exception {
		System.out.println("visiting " + pNode.getFieldDescriptor().getName());
		
		String fieldName = pNode.getFieldDescriptor().getName();
		String fieldType = "";
		String cppFieldType = "";
		//this is a data node so it must be under a message node
		String parentCppType = pNode.getParent().getPackageName() + "::" + ((MessageNode)pNode.getParent()).getFullTypeNameWOPackage();
		
		if(pNode.isMessageNode()){
			fieldType = pNode.getMessageNode().getDescriptor().getName().toUpperCase() + "_WRAPPER_GEN";
			cppFieldType = pNode.getMessageNode().getPackageName() + "::" + pNode.getMessageNode().getFullTypeNameWOPackage();
		}
		else{
			fieldType = getEiffelType(pNode.getFieldDescriptor().getType());			
		}
		
		if(pNode.isMessageNode()){
			if(pNode.isRepeated()){
				//perform collections related tasks for this node		
				_generator.addMessageTypeCollection(fieldName, fieldType);
				_generator.addCollectionInitializer(pNode.getFieldDescriptor().getName());
				
				_generator.addMessageTypeItemAdder(fieldName, fieldName, fieldType);
				_generator.addMessageTypeCollFieldGetter(fieldName, fieldType);
				_generator.addCollectionFieldLowIndxGetter(fieldName);
				_generator.addCollectionFieldUpperIndxGetter(fieldName);
				
				_generator.addCppMsgTypeFieldAdder(fieldName,parentCppType, cppFieldType);
			}
			else{
				_generator.addMessageTypeMember(fieldName,fieldType);
				_generator.addMessageTypeFieldGetter(fieldName, fieldType);
				_generator.addCppMessageTypeFieldGetter(fieldName, cppFieldType, parentCppType);
			}			
		}
		else{
			if(pNode.getFieldDescriptor().getType().equals(Type.INT32)){
				if(pNode.isRepeated())
					addIntCollectionRelatedContent(fieldName, parentCppType);
				else
					addIntRelatedContent(fieldName, parentCppType);
					
			}							 
			else if(pNode.getFieldDescriptor().getType().equals(Type.STRING)){
				if(pNode.isRepeated())
					addStringCollectionRelatedContent(fieldName, parentCppType);
				else
					addStringRelatedContent(fieldName, parentCppType);
			}
				
			else if(pNode.getFieldDescriptor().getType().equals(Type.ENUM)){
				cppFieldType = pNode.getPackageName() + "::" + pNode.getFullTypeNameWOPackage(); 
				EnumValueDescriptor defaultVal = (EnumValueDescriptor) pNode.getFieldDescriptor().getDefaultValue();
				List<EnumValueDescriptor> lst = pNode.getFieldDescriptor().getEnumType().getValues();				
				addEnumRelatedContent(fieldName, cppFieldType, defaultVal, lst, parentCppType);
			}
			else if(pNode.getFieldDescriptor().getType().equals(Type.BOOL)){
				if(pNode.isRepeated())
					addBooleanCollectionRelatedContent(fieldName, parentCppType);
				else
					addBooleanRelatedContent(fieldName, parentCppType);
			}
			else if(pNode.getFieldDescriptor().getType().equals(Type.BYTES)){
				if(pNode.isRepeated())
					throw new Exception("BYTES type of collections not supported at the moment");
				
			}				
			else{
				System.out.println("THIS TYPE HAS NOT BEEN IMPLEMENTED YET!!!");
				System.out.println(pNode.getFieldDescriptor().getType().name());;
			}
		}				

	}
				

	public String getOutput(){
		return _generator.getGeneratedString();
	}
	
	public void writeOutput(String pPath){		
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(pPath)));
			out.write(getOutput().getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
