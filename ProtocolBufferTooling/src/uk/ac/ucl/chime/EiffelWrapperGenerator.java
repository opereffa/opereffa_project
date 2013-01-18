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
import java.sql.Savepoint;
import java.util.ArrayList;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

public class EiffelWrapperGenerator {
	
	private StringTemplateGroup _group;
	private StringTemplate _wrapper;
	private String _headerFile;
	public ArrayList<StringTemplate> messageTypeMembers = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> messageTypeFieldGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> messageTypeCollections = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> collFieldInitCalls = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> messageTypeCollectionAdders = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> msgTypeCollFldGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> msgTypeCollFldLowIndxGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> msgTypeCollFldUpperIndxGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppMsgTypeFieldGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppMsgTypeFieldAdders = new ArrayList<StringTemplate>();
	
	public ArrayList<StringTemplate> stringGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> stringSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppStringSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppStringGetters = new ArrayList<StringTemplate>();
	
	//integer fields in protocol buffer class
	public ArrayList<StringTemplate> intGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> intSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppIntSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppIntGetters = new ArrayList<StringTemplate>();
	
	//boolean fields in protocol buffer class
	public ArrayList<StringTemplate> booleanGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> booleanSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppBooleanSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppBooleanGetters = new ArrayList<StringTemplate>();
	
	//collections of integers in protocol buffer class
	public ArrayList<StringTemplate> addIntInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppAddIntInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getIntFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetIntFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getIntInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetIntInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	
	//collections of booleans in protocol buffer class
	public ArrayList<StringTemplate> addBooleanInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppAddBooleanInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getBooleanFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetBooleanFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getBooleanInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetBooleanInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	
	//collections of strings in protocol buffer class
	public ArrayList<StringTemplate> addStringInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppAddStringInstanceMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getStringFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetStringFieldSizeMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> getStringInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppGetStringInstanceAtIndexMethods = new ArrayList<StringTemplate>();
	
	
	public ArrayList<StringTemplate> enumValFields = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> enumGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> enumSetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppEnumGetters = new ArrayList<StringTemplate>();
	public ArrayList<StringTemplate> cppEnumSetters = new ArrayList<StringTemplate>();
	
	public StringTemplate createCppObjFunc;	
	public StringTemplate serializeObjFunc;
	public StringTemplate serializeObjectToStrFunc;
	public StringTemplate getObjSizeFunc;
	public StringTemplate releaseObjectFunc;
	public StringTemplate deserializePBObjectFunc;	
	public StringTemplate deserializePBObjectFromStrFunc;
	
	public EiffelWrapperGenerator(){
		_group = new StringTemplateGroup("bosphorus", "C:\\tmp\\stemplTests", DefaultTemplateLexer.class);
		_wrapper = _group.getInstanceOf("wrapper");
		_headerFile = "ProtocolBuffersStubs-Eiffel.h";
	}
	
	public static void createProject(String pProjectTargetName,String pProjFileNameFullPath, 
									String pMainClassFullPath, String pPBObjectWrapperFullPath){
		StringTemplateGroup group = new StringTemplateGroup("eiffelproj", "C:\\tmp\\stemplTests", DefaultTemplateLexer.class);
		StringTemplate projFile = group.getInstanceOf("eiffel_studio_proj");
		projFile.setAttribute("target_name", pProjectTargetName);		
		
		StringTemplate mainClass = group.getInstanceOf("main_class");
		
		StringTemplate pbObjectWrapper = group.getInstanceOf("pb_object_wrapper");
		
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(pProjFileNameFullPath)));
			out.write(projFile.toString().getBytes());
			out.close();
			
			BufferedOutputStream outMainClass = new BufferedOutputStream(new FileOutputStream(new File(pMainClassFullPath)));
			outMainClass.write(mainClass.toString().getBytes());
			outMainClass.close();
			
			BufferedOutputStream outPBObjectWrapper = new BufferedOutputStream(new FileOutputStream(new File(pPBObjectWrapperFullPath)));
			outPBObjectWrapper.write(pbObjectWrapper.toString().getBytes());
			outPBObjectWrapper.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clear(){
		messageTypeMembers = new ArrayList<StringTemplate>();
		messageTypeFieldGetters = new ArrayList<StringTemplate>();
		messageTypeCollections = new ArrayList<StringTemplate>();
		collFieldInitCalls = new ArrayList<StringTemplate>();
		messageTypeCollectionAdders = new ArrayList<StringTemplate>();
		msgTypeCollFldGetters = new ArrayList<StringTemplate>();
		msgTypeCollFldLowIndxGetters = new ArrayList<StringTemplate>();
		msgTypeCollFldUpperIndxGetters = new ArrayList<StringTemplate>();
		cppMsgTypeFieldGetters = new ArrayList<StringTemplate>();
		cppMsgTypeFieldAdders = new ArrayList<StringTemplate>();
		stringGetters = new ArrayList<StringTemplate>();
		stringSetters = new ArrayList<StringTemplate>();
		
		intGetters = new ArrayList<StringTemplate>();
		intSetters = new ArrayList<StringTemplate>();
		
		booleanGetters = new ArrayList<StringTemplate>();
		booleanSetters = new ArrayList<StringTemplate>();		
		
		cppStringSetters = new ArrayList<StringTemplate>();
		cppStringGetters = new ArrayList<StringTemplate>();
		
		createCppObjFunc = null;	
		releaseObjectFunc = null;
		serializeObjFunc = null;
		serializeObjectToStrFunc = null;
		getObjSizeFunc = null;		
		deserializePBObjectFunc = null;	
		deserializePBObjectFromStrFunc = null;
		
		enumValFields = new ArrayList<StringTemplate>();
		enumGetters = new ArrayList<StringTemplate>();
		enumSetters = new ArrayList<StringTemplate>();
		cppEnumGetters = new ArrayList<StringTemplate>();
		cppEnumSetters = new ArrayList<StringTemplate>();
		
		addIntInstanceMethods = new ArrayList<StringTemplate>();
		cppAddIntInstanceMethods = new ArrayList<StringTemplate>();
		getIntFieldSizeMethods = new ArrayList<StringTemplate>();
		cppGetIntFieldSizeMethods = new ArrayList<StringTemplate>();
		getIntInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		cppGetIntInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		
		addBooleanInstanceMethods = new ArrayList<StringTemplate>();
		cppAddBooleanInstanceMethods = new ArrayList<StringTemplate>();
		getBooleanFieldSizeMethods = new ArrayList<StringTemplate>();
		cppGetBooleanFieldSizeMethods = new ArrayList<StringTemplate>();
		getBooleanInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		cppGetBooleanInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		
		addStringInstanceMethods = new ArrayList<StringTemplate>();
		cppAddStringInstanceMethods = new ArrayList<StringTemplate>();
		getStringFieldSizeMethods = new ArrayList<StringTemplate>();
		cppGetStringFieldSizeMethods = new ArrayList<StringTemplate>();
		getStringInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		cppGetStringInstanceAtIndexMethods = new ArrayList<StringTemplate>();
		
		_wrapper = _group.getInstanceOf("wrapper");
	}
	
	public void setHeaderFileName(String pFileName){
		_headerFile = pFileName;
	}
	
	public void setTypeName(String pTypeName){
		_wrapper.setAttribute("type_name", pTypeName);
	}
	
	public String getGeneratedString(){
		return _wrapper.toString();
	}

	public void testAddressWrapper(){
		
		//message type members
		addMessageTypeMember("manager", "PERSON_WRAPPER_GEN");			
		//message type field getters			
		addMessageTypeFieldGetter("manager", "PERSON_WRAPPER_GEN");			
		//collections of message types
		addMessageTypeCollection("person", "PERSON_WRAPPER_GEN");							
		//init collections				
		addCollectionInitializer("person");							
		//collection accessors follows 
		//collection adders
		addMessageTypeItemAdder("person", "person", "PERSON_WRAPPER_GEN");						
//		$collection_field_getters; seperator="\n"$ GETS VIA INDEX
		addMessageTypeCollFieldGetter("person", "PERSON_WRAPPER_GEN");		
//		$collection_low_index_getters; seperator="\n"$		
		addCollectionFieldLowIndxGetter("person");			
//		$collection_upper_index_getters; seperator="\n"$
		addCollectionFieldUpperIndxGetter("person");					
		//cpp getter for message type field
		addCppMessageTypeFieldGetter("manager", "bosphorus::Person", "bosphorus::AddressBook");						
		//cpp message type field adders
		addCppMsgTypeFieldAdder("person", "bosphorus::AddressBook", "bosphorus::Person");			
		//cpp object creation 
		addCppCreateObjectFunction("bosphorus::AddressBook");		
		
		setTypeName("ADDRESS_BOOK_WRAPPER_GEN");
		//write all values to template
		setWrapperValues();
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:\\work\\data\\Eiffel\\Eiffel-PB-ZMQ-Wrappers\\Protocol_Buffer_Wrapper\\address_book_wrapper_gen.e")));
			out.write(_wrapper.toString().getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	public void addCppCreateObjectFunction(String pMessageType) {
		createCppObjFunc = getCppCreateObjectFunction(pMessageType);
	}
	public StringTemplate getCppCreateObjectFunction(String pMessageType) {
		StringTemplate createCppObjFunc = _group.getInstanceOf("create_cpp_object");
		createCppObjFunc.setAttribute("pb_message_type_name", pMessageType);
		createCppObjFunc.setAttribute("header_file_name", _headerFile);
		return createCppObjFunc;
	}
	
	//----------------------------------------------------------------------------
	public void addCppSerializeObjectFunction(String pMessageType) {
		serializeObjFunc = getCppSerializeObjectFunction(pMessageType);
	}
	public StringTemplate getCppSerializeObjectFunction(String pObjectType) {
		StringTemplate serializeObjFunc = _group.getInstanceOf("serialize_pb_object");
		serializeObjFunc.setAttribute("pb_object_type", pObjectType);
		serializeObjFunc.setAttribute("header_file_name", _headerFile);
		return serializeObjFunc;
	}
	
	public void addCppSerializeObjectToStringFunction(String pMessageType) {
		serializeObjectToStrFunc = getCppSerializeObjectToStringFunction(pMessageType);
	}
	public StringTemplate getCppSerializeObjectToStringFunction(String pObjectType) {
		StringTemplate serializeObjToStrFunc = _group.getInstanceOf("serialize_pb_object_to_string");
		serializeObjToStrFunc.setAttribute("pb_object_type", pObjectType);
		serializeObjToStrFunc.setAttribute("header_file_name", _headerFile);
		return serializeObjToStrFunc;
	}
	
	public void addCppGetObjectSizeFunction(String pMessageType) {
		getObjSizeFunc = getCppGetObjectSizeFunction(pMessageType);
	}
	public StringTemplate getCppGetObjectSizeFunction(String pObjectType) {
		StringTemplate getObjSizeFunc = _group.getInstanceOf("get_cpp_object_byte_size");
		getObjSizeFunc.setAttribute("pb_object_type", pObjectType);
		getObjSizeFunc.setAttribute("header_file_name", _headerFile);
		return getObjSizeFunc;
	}
	//-----------------------------------------------------------------------------
	
	public void addCppReleaseObjectFunction(String pMessageType) {
		releaseObjectFunc = getCppGetReleaseObjectFunction(pMessageType);
	}
	public StringTemplate getCppGetReleaseObjectFunction(String pObjectType) {
		StringTemplate getObjSizeFunc = _group.getInstanceOf("cpp_release_object");
		getObjSizeFunc.setAttribute("pb_object_type", pObjectType);
		getObjSizeFunc.setAttribute("header_file_name", _headerFile);
		return getObjSizeFunc;
	}
	
	public void addCppDeserializePBObjectFunction(String pParentType){
		deserializePBObjectFunc = getCppDeserializePBObjectFunction(pParentType);
	}
	
	public StringTemplate getCppDeserializePBObjectFunction(String pParentType) {
		StringTemplate deserializeFunc = _group.getInstanceOf("cpp_deserialize_pb_object");		
		deserializeFunc.setAttribute("header_file_name", _headerFile);
		deserializeFunc.setAttribute("pb_parent_type", pParentType);
		return deserializeFunc;
	}
	
	public void addCppDeserializePBObjectFromStrFunction(String pParentType){
		deserializePBObjectFromStrFunc = getCppDeserializePBObjectFromStrFunction(pParentType);
	}
	
	public StringTemplate getCppDeserializePBObjectFromStrFunction(String pParentType) {
		StringTemplate deserializeFromStrFunc = _group.getInstanceOf("deserialize_pb_object_from_string");		
		deserializeFromStrFunc.setAttribute("header_file_name", _headerFile);
		deserializeFromStrFunc.setAttribute("pb_parent_type", pParentType);
		return deserializeFromStrFunc;
	}
	

	public void addCppMsgTypeFieldAdder(String pFieldName, String pParentType, String pFieldType) {
		cppMsgTypeFieldAdders.add(getCppMsgTypeFieldAdder(pFieldName, pParentType, pFieldType));
	}
	public StringTemplate getCppMsgTypeFieldAdder(String pFieldName, String pParentType, String pFieldType) {
		StringTemplate cppMsgTypFldAdder = _group.getInstanceOf("cpp_add_message_to_pb_object");
		cppMsgTypFldAdder.setAttribute("field_name", pFieldName);
		cppMsgTypFldAdder.setAttribute("pb_parent_type", pParentType);
		cppMsgTypFldAdder.setAttribute("header_file_name", _headerFile);
		cppMsgTypFldAdder.setAttribute("field_type", pFieldType);
		return cppMsgTypFldAdder;
	}

	public void addCppMessageTypeFieldGetter(String pFieldName, String pFieldType, String pMessgTypeName) {
		cppMsgTypeFieldGetters.add(getCppMessageTypeFieldGetter(pFieldName, pFieldType, pMessgTypeName));
	}
	public StringTemplate getCppMessageTypeFieldGetter(String pFieldName, String pFieldType, String pMessgTypeName) {
		StringTemplate cppMsgTypFldGetter = _group.getInstanceOf("cpp_message_type_field_getter");
		cppMsgTypFldGetter.setAttribute("field_name", pFieldName);
		cppMsgTypFldGetter.setAttribute("field_type", pFieldType);
		cppMsgTypFldGetter.setAttribute("header_file_name", _headerFile);
		cppMsgTypFldGetter.setAttribute("pb_message_type_name", pMessgTypeName);
		return cppMsgTypFldGetter;
	}

	public void addCollectionFieldUpperIndxGetter(String pFieldName) {
		msgTypeCollFldUpperIndxGetters.add(getCollectionFieldUpperIndxGetter(pFieldName));
	}
	public StringTemplate getCollectionFieldUpperIndxGetter(String pFieldName) {
		StringTemplate collFldUpperIndxGetter = _group.getInstanceOf("collection_field_upper_index_getter");
		collFldUpperIndxGetter.setAttribute("field_name", pFieldName);
		return collFldUpperIndxGetter;
	}

	public void addCollectionFieldLowIndxGetter(String pFieldName) {
		msgTypeCollFldLowIndxGetters.add(getCollectionFieldLowIndxGetter(pFieldName));
	}
	public StringTemplate getCollectionFieldLowIndxGetter(String pFieldName) {
		StringTemplate collFldLowIndxGetter = _group.getInstanceOf("collection_field_low_index_getter");
		collFldLowIndxGetter.setAttribute("field_name", pFieldName);
		return collFldLowIndxGetter;
	}

	public void addMessageTypeCollFieldGetter(String pFieldName, String pFieldType) {
		msgTypeCollFldGetters.add(getMessageTypeCollFieldGetter(pFieldName, pFieldType));
	}
	public StringTemplate getMessageTypeCollFieldGetter(String pFieldName, String pFieldType) {
		StringTemplate collFldGetter = _group.getInstanceOf("collection_field_getter");
		collFldGetter.setAttribute("field_name", pFieldName);		
		collFldGetter.setAttribute("field_type", pFieldType);
		return collFldGetter;
	}

	public void addMessageTypeItemAdder(String pFieldName, String pCollFieldName, String pFieldType) {
		messageTypeCollectionAdders.add(getMessageTypeItemAdder(pFieldName, pCollFieldName, pFieldType));
	}
	public StringTemplate getMessageTypeItemAdder(String pFieldName, String pCollFieldName, String pFieldType) {
		StringTemplate messageTypeCollectionItemAdder = _group.getInstanceOf("repeating_field_adder");
		messageTypeCollectionItemAdder.setAttribute("field_name", pFieldName);
		messageTypeCollectionItemAdder.setAttribute("collection_field", pCollFieldName);
		messageTypeCollectionItemAdder.setAttribute("field_type", pFieldType);
		return messageTypeCollectionItemAdder;
	}

	public void addCollectionInitializer(String pCollectionFieldName) {
		collFieldInitCalls.add(getCollectionInitializer(pCollectionFieldName));
	}
	public StringTemplate getCollectionInitializer(String pCollectionFieldName) {
		StringTemplate collFieldInitCall = _group.getInstanceOf("collection_field_init");
		collFieldInitCall.setAttribute("coll_field_name", pCollectionFieldName);
		return collFieldInitCall;
	}

	public void addMessageTypeCollection(String pFieldName, String pCollType) {
		messageTypeCollections.add(getMessageTypeCollection(pFieldName, pCollType));
	}
	public StringTemplate getMessageTypeCollection(String pFieldName, String pCollType) {
		StringTemplate messageTypeCollection = _group.getInstanceOf("collection_field");
		messageTypeCollection.setAttribute("field_name", pFieldName);
		messageTypeCollection.setAttribute("coll_type", pCollType);
		return messageTypeCollection;
	}

	public void addMessageTypeFieldGetter(String pFieldName, String pFieldType) {
		messageTypeFieldGetters.add(getMessageTypeFieldGetter(pFieldName, pFieldType));
	}
	public StringTemplate getMessageTypeFieldGetter(String pFieldName, String pFieldType) {
		StringTemplate messageTypeFieldGetter = _group.getInstanceOf("message_type_field_getter");
		messageTypeFieldGetter.setAttribute("field_name", pFieldName);
		messageTypeFieldGetter.setAttribute("field_type", pFieldType);
		return messageTypeFieldGetter;
	}

	public void addMessageTypeMember(String pMemberFieldName, String pMemberType) {
		messageTypeMembers.add(getMessageTypeMember(pMemberFieldName, pMemberType));
	}
	public StringTemplate getMessageTypeMember(String pMemberFieldName, String pMemberType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("wrapper_message_type_member");
		messageTypeMember.setAttribute("member_name", pMemberFieldName);
		messageTypeMember.setAttribute("member_type", pMemberType);
		return messageTypeMember;
	}
	
	//boolean collections in protocol buffer
	//-----------------------------------------------------------------------------------------
	public void addBooleanInstance(String pMemberFieldName) {
		addBooleanInstanceMethods.add(getAddBooleanInstance(pMemberFieldName));
	}
	public StringTemplate getAddBooleanInstance(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("add_boolean_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	public void addCppAddBooleanInstance(String pMemberFieldName, String pParentType) {
		cppAddBooleanInstanceMethods.add(getCppAddBooleanInstance(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppAddBooleanInstance(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_add_boolean_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addGetBooleanFieldSizeMethod(String pMemberFieldName) {
		getBooleanFieldSizeMethods.add(getAddBooleanFieldSizeMethod(pMemberFieldName));
	}
	public StringTemplate getAddBooleanFieldSizeMethod(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_boolean_field_size");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	public void addCppGetBooleanFieldSizeMethod(String pMemberFieldName, String pParentType) {
		cppGetBooleanFieldSizeMethods.add(getCppBooleanFieldSizeMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppBooleanFieldSizeMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_boolean_field_size_method");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addBooleanAtIndexMethod(String pMemberFieldName) {
		getBooleanInstanceAtIndexMethods.add(getBooleanAtIndexMethod(pMemberFieldName));
	}
	public StringTemplate getBooleanAtIndexMethod(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_boolean_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);				
		return messageTypeMember;
	}
	
	public void addCppGetBooleanFieldAtMethod(String pMemberFieldName, String pParentType) {
		cppGetBooleanFieldSizeMethods.add(getCppGetBooleanFieldAtMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppGetBooleanFieldAtMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_boolean_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	//integer collectins in protocol buffer
	//--------------------------------------------------------------
	public void addIntegerInstance(String pMemberFieldName) {
		addIntInstanceMethods.add(getAddIntegerInstance(pMemberFieldName));
	}
	public StringTemplate getAddIntegerInstance(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("add_integer_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	
	public void addCppAddIntegerInstance(String pMemberFieldName, String pParentType) {
		cppAddIntInstanceMethods.add(getCppAddIntegerInstance(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppAddIntegerInstance(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_add_integer_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addGetIntFieldSizeMethod(String pMemberFieldName) {
		getIntFieldSizeMethods.add(getAddIntFieldSizeMethod(pMemberFieldName));
	}
	public StringTemplate getAddIntFieldSizeMethod(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_integer_field_size");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	public void addCppGetIntegerFieldSizeMethod(String pMemberFieldName, String pParentType) {
		cppGetIntFieldSizeMethods.add(getCppIntegerFieldSizeMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppIntegerFieldSizeMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_integer_field_size_method");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addIntegerAtIndexMethod(String pMemberFieldName) {
		getIntInstanceAtIndexMethods.add(getIntegerAtIndexMethod(pMemberFieldName));
	}
	public StringTemplate getIntegerAtIndexMethod(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_integer_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);				
		return messageTypeMember;
	}
	
	public void addCppGetIntegerFieldAtMethod(String pMemberFieldName, String pParentType) {
		cppGetIntFieldSizeMethods.add(getCppGetIntegerFieldAtMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppGetIntegerFieldAtMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_integer_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	//--------------------------------------------------------------
	
	//String collections in protocol buffer
	//--------------------------------------------------------------
	public void addStringInstance(String pMemberFieldName) {
		addStringInstanceMethods.add(getAddStringInstance(pMemberFieldName));
	}
	public StringTemplate getAddStringInstance(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("add_string_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	
	public void addCppAddStringInstance(String pMemberFieldName, String pParentType) {
		cppAddStringInstanceMethods.add(getCppAddStringInstance(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppAddStringInstance(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_add_string_instance");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addGetStringFieldSizeMethod(String pMemberFieldName) {
		getStringFieldSizeMethods.add(getAddStringFieldSizeMethod(pMemberFieldName));
	}
	public StringTemplate getAddStringFieldSizeMethod(String pMemberFieldName) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_string_field_size");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		return messageTypeMember;
	}
	
	public void addCppGetStringFieldSizeMethod(String pMemberFieldName, String pParentType) {
		cppGetStringFieldSizeMethods.add(getCppStringFieldSizeMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppStringFieldSizeMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_string_field_size_method");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addStringAtIndexMethod(String pMemberFieldName, String pParentType) {
		getStringInstanceAtIndexMethods.add(getStringAtIndexMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getStringAtIndexMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("get_string_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	public void addCppGetStringFieldAtMethod(String pMemberFieldName, String pParentType) {
		cppGetIntFieldSizeMethods.add(getCppGetStringFieldAtMethod(pMemberFieldName, pParentType));
	}
	public StringTemplate getCppGetStringFieldAtMethod(String pMemberFieldName, String pParentType) {
		StringTemplate messageTypeMember = _group.getInstanceOf("cpp_get_string_field_at");
		messageTypeMember.setAttribute("field_name", pMemberFieldName);		
		messageTypeMember.setAttribute("header_file_name", _headerFile);
		messageTypeMember.setAttribute("pb_parent_type", pParentType);
		return messageTypeMember;
	}
	
	//--------------------------------------------------------------
	
	public void setWrapperValues(){		
		_wrapper.setAttribute("message_type_members", messageTypeMembers);
		_wrapper.setAttribute("message_type_field_getters", messageTypeFieldGetters);
		_wrapper.setAttribute("collection_fields", messageTypeCollections);
		_wrapper.setAttribute("coll_field_init_calls", collFieldInitCalls);
		_wrapper.setAttribute("collection_field_getters", msgTypeCollFldGetters);
		_wrapper.setAttribute("collection_low_index_getters", msgTypeCollFldLowIndxGetters);
		_wrapper.setAttribute("collection_upper_index_getters", msgTypeCollFldUpperIndxGetters);
		_wrapper.setAttribute("repeating_field_adders", messageTypeCollectionAdders);
		_wrapper.setAttribute("cpp_message_type_field_getters", cppMsgTypeFieldGetters);
		_wrapper.setAttribute("cpp_repating_field_adders", cppMsgTypeFieldAdders);
		
		_wrapper.setAttribute("int_getters", intGetters);
		_wrapper.setAttribute("int_setters", intSetters);
		_wrapper.setAttribute("cpp_int_setters", cppIntSetters);
		_wrapper.setAttribute("cpp_int_getters", cppIntGetters);
		
		_wrapper.setAttribute("boolean_getters", booleanGetters);
		_wrapper.setAttribute("boolean_setters", booleanSetters);
		_wrapper.setAttribute("cpp_boolean_setters", cppBooleanSetters);
		_wrapper.setAttribute("cpp_boolean_getters", cppBooleanGetters);
				
		_wrapper.setAttribute("string_getters", stringGetters);
		_wrapper.setAttribute("string_setters", stringSetters);
		_wrapper.setAttribute("cpp_string_setters", cppStringSetters);
		_wrapper.setAttribute("cpp_string_getters", cppStringGetters);
		
		_wrapper.setAttribute("enum_val_fields", enumValFields);
		_wrapper.setAttribute("enum_getters", enumGetters);
		_wrapper.setAttribute("cpp_enum_getters", cppEnumGetters);
		_wrapper.setAttribute("enum_setters", enumSetters);
		_wrapper.setAttribute("cpp_enum_setters", cppEnumSetters);
		
		_wrapper.setAttribute("create_cpp_object", createCppObjFunc);
		_wrapper.setAttribute("serialize_cpp_object", serializeObjFunc);
		_wrapper.setAttribute("serialize_cpp_object_to_string", serializeObjectToStrFunc);
		_wrapper.setAttribute("get_cpp_object_size", getObjSizeFunc);
		_wrapper.setAttribute("cpp_release_object", releaseObjectFunc);
		_wrapper.setAttribute("cpp_deserialize_pb_object", deserializePBObjectFunc);
		_wrapper.setAttribute("deserialize_pb_object_from_string", deserializePBObjectFromStrFunc);
		
		//integer collections TODO: the next collection is set to two attributes!!!! something is wrong here with addIntInstanceMethods CHECK IT OUT!!
		_wrapper.setAttribute("add_integer_instance_methods", addIntInstanceMethods); 
		_wrapper.setAttribute("cpp_add_integer_instance_methods", cppAddIntInstanceMethods);
		_wrapper.setAttribute("get_integer_field_size_methods", getIntFieldSizeMethods);
		_wrapper.setAttribute("cpp_get_integer_field_size_methods", cppGetIntFieldSizeMethods);
		_wrapper.setAttribute("get_integer_field_at_methods", getIntInstanceAtIndexMethods);
		_wrapper.setAttribute("cpp_get_integer_field_at_methods", cppGetIntInstanceAtIndexMethods);
		
		_wrapper.setAttribute("add_boolean_instance_methods", addBooleanInstanceMethods); 
		_wrapper.setAttribute("cpp_add_boolean_instance_methods", cppAddBooleanInstanceMethods);
		_wrapper.setAttribute("get_boolean_field_size_methods", getBooleanFieldSizeMethods);
		_wrapper.setAttribute("cpp_get_boolean_field_size_methods", cppGetBooleanFieldSizeMethods);
		_wrapper.setAttribute("get_boolean_field_at_methods", getBooleanInstanceAtIndexMethods);
		_wrapper.setAttribute("cpp_get_boolean_field_at_methods", cppGetBooleanInstanceAtIndexMethods);
		
		
		_wrapper.setAttribute("add_string_instance_methods", addStringInstanceMethods);
		_wrapper.setAttribute("cpp_add_string_instance_methods", cppAddStringInstanceMethods);
		_wrapper.setAttribute("get_string_field_size_methods", getStringFieldSizeMethods);
		_wrapper.setAttribute("cpp_get_string_field_size_methods", cppGetStringFieldSizeMethods);
		_wrapper.setAttribute("get_string_field_at_methods", getStringInstanceAtIndexMethods);
		_wrapper.setAttribute("cpp_get_string_field_at_methods", cppGetStringInstanceAtIndexMethods);
		
		
		
	}
//------------------------------------------------------------------------------------	
	public void testWrapper(){		
		//message type members
		addMessageTypeMember("phone", "PHONE_NUMBER_WRAPPER");				
		
		//string getters
		addStringGetter("name");
		addStringGetter("email");

		//string setters
		addStringSetter("name");
		addStringSetter("email");

		//int getters				
		addIntGetter("id");
		//int setters
		addIntSetter("id");
		//cpp string setters
		addCppStringSetter("name", "bosphorus::Person");
		addCppStringSetter("email", "bosphorus::Person");				
		//cpp string getters		
		addCppStringGetter("name", "bosphorus::Person");
		addCppStringGetter("email", "bosphorus::Person");		
		//cpp int setters
		addCppIntSetter("id", "bosphorus::Person");
		//cpp int getters
		addCppIntGetter("id", "bosphorus::Person");
				
		//create cpp object function
		addCppCreateObjectFunction("bosphorus::Person");
		
		//insert everything into template
		_wrapper.setAttribute("type_name", "PERSON_WRAPPER_GEN");
		setWrapperValues();
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:\\work\\data\\Eiffel\\Eiffel-PB-ZMQ-Wrappers\\Protocol_Buffer_Wrapper\\person_wrapper_gen.e")));
			out.write(_wrapper.toString().getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCppIntGetter(String pFieldName, String pMessgeTypeName) {
		cppGetIntFieldSizeMethods.add(getCppIntGetter(pFieldName, pMessgeTypeName));
	}
	public StringTemplate getCppIntGetter(String pFieldName, String pMessgeTypeName) {
		StringTemplate cppIntGetter = _group.getInstanceOf("cpp_int_getter");
		cppIntGetter.setAttribute("field_name", pFieldName);
		cppIntGetter.setAttribute("pb_message_type_name", pMessgeTypeName);
		cppIntGetter.setAttribute("header_file_name",_headerFile);
		return cppIntGetter;
	}

	public void addCppIntSetter(String pFieldName, String pMessageTypeName) {
		getIntFieldSizeMethods.add(getCppIntSetter(pFieldName, pMessageTypeName));
	}	
	public StringTemplate getCppIntSetter(String pFieldName, String pMessageTypeName) {
		StringTemplate cppIntSetter = _group.getInstanceOf("cpp_int_setter");
		cppIntSetter.setAttribute("field_name", pFieldName);
		cppIntSetter.setAttribute("pb_message_type_name", pMessageTypeName);
		cppIntSetter.setAttribute("header_file_name", _headerFile);
		return cppIntSetter;
	}

	public void addCppStringGetter(String pFieldName, String pMessageTypeName) {
		cppStringGetters.add(getCppStringGetter(pFieldName, pMessageTypeName));
	}
	public StringTemplate getCppStringGetter(String pFieldName, String pMessageTypeName) {
	StringTemplate cppStringGetter = _group.getInstanceOf("cpp_string_getter");
	cppStringGetter.setAttribute("field_name", pFieldName);
	cppStringGetter.setAttribute("pb_message_type_name", pMessageTypeName);
	cppStringGetter.setAttribute("header_file_name", _headerFile);
	return cppStringGetter;
}

	public void addCppStringSetter(String pFieldName, String pMessageType) {
		cppStringSetters.add(getCppStringSetter(pFieldName, pMessageType));
	}
		public StringTemplate getCppStringSetter(String pFieldName, String pMessageType) {
		StringTemplate cppStringSetter = _group.getInstanceOf("cpp_string_setter");
		cppStringSetter.setAttribute("field_name", pFieldName);
		cppStringSetter.setAttribute("pb_message_type_name", pMessageType);
		cppStringSetter.setAttribute("header_file_name", _headerFile);
		return cppStringSetter;
	}

	public void addIntSetter(String pFieldName) {
		cppAddIntInstanceMethods.add(getIntSetter(pFieldName));
	}	
	public StringTemplate getIntSetter(String pFieldName) {
		StringTemplate intSetter = _group.getInstanceOf("int_setter");
		intSetter.setAttribute("field_name", pFieldName);
		return intSetter;
	}

	public void addIntGetter(String pFieldName) {
		addIntInstanceMethods.add(getIntGetter(pFieldName));
	}
	public StringTemplate getIntGetter(String pFieldName) {
	StringTemplate intGetter = _group.getInstanceOf("int_getter");
	intGetter.setAttribute("field_name", pFieldName);
	return intGetter;
	}
	
	//-------------------------------------------------------------------------------------
	
	public void addBooleanGetter(String pFieldName) {
		addBooleanInstanceMethods.add(getBooleanGetter(pFieldName));
	}
	public StringTemplate getBooleanGetter(String pFieldName) {
		StringTemplate booleanGetter = _group.getInstanceOf("boolean_getter");
		booleanGetter.setAttribute("field_name", pFieldName);
		return booleanGetter;
	}
	
	public void addBooleanSetter(String pFieldName) {
		cppAddBooleanInstanceMethods.add(getBooleanSetter(pFieldName));
	}
	
	public StringTemplate getBooleanSetter(String pFieldName) {
		StringTemplate booleanSetter = _group.getInstanceOf("boolean_setter");
		booleanSetter.setAttribute("field_name", pFieldName);
		return booleanSetter;
	}
	
	public void addCppBooleanGetter(String pFieldName, String pMessgeTypeName) {
		cppGetBooleanFieldSizeMethods.add(getCppBooleanGetter(pFieldName, pMessgeTypeName));
	}
	public StringTemplate getCppBooleanGetter(String pFieldName, String pMessgeTypeName) {
		StringTemplate cppBooleanGetter = _group.getInstanceOf("cpp_boolean_getter");
		cppBooleanGetter.setAttribute("field_name", pFieldName);
		cppBooleanGetter.setAttribute("pb_message_type_name", pMessgeTypeName);
		cppBooleanGetter.setAttribute("header_file_name",_headerFile);
		return cppBooleanGetter;
	}
	
	public void addCppBooleanSetter(String pFieldName, String pMessageTypeName) {
		getBooleanFieldSizeMethods.add(getCppBooleanSetter(pFieldName, pMessageTypeName));
	}	
	public StringTemplate getCppBooleanSetter(String pFieldName, String pMessageTypeName) {
		StringTemplate cppBooleanSetter = _group.getInstanceOf("cpp_boolean_setter");
		cppBooleanSetter.setAttribute("field_name", pFieldName);
		cppBooleanSetter.setAttribute("pb_message_type_name", pMessageTypeName);
		cppBooleanSetter.setAttribute("header_file_name", _headerFile);
		return cppBooleanSetter;
	}
	
	//-------------------------------------------------------------------------------------
	public void addEnumValField(String pFieldName, String pEnumName, String pEnumValue) {
		enumValFields.add(getEnumValField(pFieldName, pEnumName, pEnumValue));
	}
	public StringTemplate getEnumValField(String pFieldName, String pEnumName, String pEnumValue) {
	StringTemplate enumValField = _group.getInstanceOf("enum_val_field");
	enumValField.setAttribute("field_name", pFieldName.toUpperCase());
	enumValField.setAttribute("enum_name", pEnumName.toUpperCase());
	enumValField.setAttribute("enum_value", pEnumValue);
	return enumValField;
	}
	
	public void addEnumGetter(String pFieldName) {
		enumGetters.add(getEnumGetter(pFieldName));
	}
	public StringTemplate getEnumGetter(String pFieldName) {
	StringTemplate enumGetter = _group.getInstanceOf("enum_getter");
	enumGetter.setAttribute("field_name", pFieldName);
	return enumGetter;
	}
	
	public void addCppEnumGetter(String pFieldName, 
								StringTemplate pEnumCppGetterIfStatement, String pCppFieldType,
								String pEnumDefaultValue,
								ArrayList<StringTemplate> pEnumCppGetterElseIfStatements) {
		cppEnumGetters.add(getCppEnumGetter(pFieldName, pEnumCppGetterIfStatement, 
										pCppFieldType, pEnumDefaultValue, pEnumCppGetterElseIfStatements));
	}
	public StringTemplate getCppEnumGetter(String pFieldName, 
											StringTemplate pEnumCppGetterIfStatement, String pCppFieldType,
											String pEnumDefaultValue,
											ArrayList<StringTemplate> pEnumCppGetterElseIfStatements) {
	StringTemplate enumCppGetter = _group.getInstanceOf("enum_cpp_getter");
	enumCppGetter.setAttribute("field_name", pFieldName);
	enumCppGetter.setAttribute("header_file_name", _headerFile);
	enumCppGetter.setAttribute("field_type", pCppFieldType);
	enumCppGetter.setAttribute("enum_default_value", pEnumDefaultValue);
	enumCppGetter.setAttribute("enum_cpp_getter_if_statement", pEnumCppGetterIfStatement);
	enumCppGetter.setAttribute("enum_cpp_getter_elseif_statements", pEnumCppGetterElseIfStatements);
	return enumCppGetter;
	}
	
	public void addEnumSetter(String pFieldName) {
		enumSetters.add(getEnumSetter(pFieldName));
	}
	public StringTemplate getEnumSetter(String pFieldName) {
	StringTemplate enumGetter = _group.getInstanceOf("enum_setter");
	enumGetter.setAttribute("field_name", pFieldName);
	return enumGetter;
	}
	

	public void addCppEnumSetter(String pFieldName,
			StringTemplate pEnumCppSetterIfStatement, String pCppFieldType,
			String pEnumType,
			ArrayList<StringTemplate> pEnumCppSetterElseIfStatements) {
	cppEnumSetters.add(getCppEnumSetter(pFieldName,  
						pEnumCppSetterIfStatement, pCppFieldType, 
						pEnumType, pEnumCppSetterElseIfStatements));
	}
	public StringTemplate getCppEnumSetter(String pFieldName, 
							StringTemplate pEnumCppSetterIfStatement, String pCppFieldType,
							String pEnumType,
							ArrayList<StringTemplate> pEnumCppSetterElseIfStatements) {
	StringTemplate enumCppSetter = _group.getInstanceOf("enum_cpp_setter");
	enumCppSetter.setAttribute("field_name", pFieldName);
	enumCppSetter.setAttribute("header_file_name", _headerFile);
	enumCppSetter.setAttribute("field_type", pCppFieldType);
	enumCppSetter.setAttribute("enum_type", pEnumType);
	enumCppSetter.setAttribute("enum_cpp_setter_if_statement", pEnumCppSetterIfStatement);
	enumCppSetter.setAttribute("enum_cpp_setter_elseif_statements", pEnumCppSetterElseIfStatements);
	return enumCppSetter;
	}
	
	public StringTemplate getCppEnumGetterIfStatement(String pFieldName, String pValueName, String pValue){
		StringTemplate st = _group.getInstanceOf("enum_cpp_getter_if_statement");
		st.setAttribute("field_name", pFieldName);
		st.setAttribute("enum_value_name", pValueName);
		st.setAttribute("value", pValue);
		return st;
	}
	
	public StringTemplate getCppEnumGetterElseIfStatement(String pFieldName, String pValueName, String pValue){
		StringTemplate st = _group.getInstanceOf("enum_cpp_getter_elseif_statement");
		st.setAttribute("field_name", pFieldName);
		st.setAttribute("enum_value_name", pValueName);
		st.setAttribute("value", pValue);
		return st;
	}
	
	public StringTemplate getCppEnumSetterIfStatement(String pCppEnumValueLiteral, String pIntValue){
		StringTemplate st = _group.getInstanceOf("enum_cpp_setter_if_statement");		
		st.setAttribute("enum_value", pCppEnumValueLiteral);		
		st.setAttribute("integer_value", pIntValue);
		return st;
	}
	
	public StringTemplate getCppEnumSetterElseIfStatement(String pEnumValue, String pIntValue){
		StringTemplate st = _group.getInstanceOf("enum_cpp_setter_elseif_statement");		
		st.setAttribute("enum_value", pEnumValue);	
		st.setAttribute("integer_value", pIntValue);
		return st;
	}
	//--------------------------------------------------------------------------------------

	public void addStringSetter(String pFieldName) {
		stringSetters.add(getStringSetter(pFieldName));
	}
		public StringTemplate getStringSetter(String pFieldName) {
		StringTemplate stringSetter = _group.getInstanceOf("string_setter");
		stringSetter.setAttribute("field_name", pFieldName);
		return stringSetter;
	}

	public void addStringGetter(String pFieldName) {
		stringGetters.add(getStringGetter(pFieldName));
	}	
	public StringTemplate getStringGetter(String pFieldName) {
	StringTemplate stringGetter = _group.getInstanceOf("string_getter");
	stringGetter.setAttribute("field_name", pFieldName);
	return stringGetter;
}

//	public StringTemplate createCppObjectFunction(String headerFile) {
//		_group.getInstanceOf("create_cpp_object");
//		createCppObjFunc.setAttribute("pb_message_type_name", "bosphorus::Person");
//		createCppObjFunc.setAttribute("header_file_name", headerFile);
//		return createCppObjFunc;
//	}

	
}
