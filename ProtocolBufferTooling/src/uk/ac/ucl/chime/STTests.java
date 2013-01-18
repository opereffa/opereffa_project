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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

public class STTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testWrapper();
//		testAddressWrapper();
		EiffelWrapperGenerator gen = new EiffelWrapperGenerator();
		gen.testWrapper();
		gen.clear();
		gen.testAddressWrapper();
	}
	
	public static void test(){
		StringTemplateGroup group =  new StringTemplateGroup("myGroup", "C:\\tmp\\stemplTests", DefaultTemplateLexer.class);
		StringTemplate testTmpl = group.getInstanceOf("test");
		
		StringTemplate childTmpl = group.getInstanceOf("testChild1");
		childTmpl.setAttribute("second_val", "this is the second val");
		childTmpl.setAttribute("second_multivals", new String[]{"multi1", "multi2"});

		testTmpl.setAttribute("singleval", "Welcome To StringTemplate");
		ArrayList<String> multiVals = new ArrayList<String>();
		for(int i = 0; i < 3; i++){
			multiVals.add("multi" + String.valueOf(i));
		}
		testTmpl.setAttribute("multivals", multiVals);
		
		testTmpl.setAttribute("insertion_point", childTmpl);

		System.out.println(testTmpl.toString());

	}
	
	public static void testAddressWrapper(){
		StringTemplateGroup group =  new StringTemplateGroup("bosphorus", "C:\\tmp\\stemplTests", DefaultTemplateLexer.class);
		StringTemplate wrapper = group.getInstanceOf("wrapper");
		
		String headerFile = "ProtocolBuffersStubs-Eiffel.h";
		
		wrapper.setAttribute("type_name", "ADDRESS_BOOK_WRAPPER_GEN");
		
		//message type members
		ArrayList<StringTemplate> messageTypeMembers = new ArrayList<StringTemplate>();
		
		StringTemplate messageTypeMember = group.getInstanceOf("wrapper_message_type_member");
		messageTypeMember.setAttribute("member_name", "manager");
		messageTypeMember.setAttribute("member_type", "PERSON_WRAPPER_GEN");
		messageTypeMembers.add(messageTypeMember);
		
		wrapper.setAttribute("message_type_members", messageTypeMembers);
		
		//message type field getters
		ArrayList<StringTemplate> messageTypeFieldGetters = new ArrayList<StringTemplate>();
		
		StringTemplate messageTypeFieldGetter = group.getInstanceOf("message_type_field_getter");
		messageTypeFieldGetter.setAttribute("field_name", "manager");
		messageTypeFieldGetter.setAttribute("field_type", "PERSON_WRAPPER_GEN");
		messageTypeFieldGetters.add(messageTypeFieldGetter);
		
		wrapper.setAttribute("message_type_field_getters", messageTypeFieldGetters);
		
		//collections of message types
		ArrayList<StringTemplate> messageTypeCollections = new ArrayList<StringTemplate>();
		
		StringTemplate messageTypeCollection = group.getInstanceOf("collection_field");
		messageTypeCollection.setAttribute("field_name", "person");
		messageTypeCollection.setAttribute("coll_type", "PERSON_WRAPPER_GEN");
		
		messageTypeCollections.add(messageTypeCollection);
		
		wrapper.setAttribute("collection_fields", messageTypeCollections);
		
		//init collections
		ArrayList<StringTemplate> collFieldInitCalls = new ArrayList<StringTemplate>();
		
		StringTemplate collFieldInitCall = group.getInstanceOf("collection_field_init");
		collFieldInitCall.setAttribute("coll_field_name", "person");		
		
		collFieldInitCalls.add(collFieldInitCall);
		
		wrapper.setAttribute("coll_field_init_calls", collFieldInitCalls);
		
		
		//collection accessors follows 
		
		//collection adders
		ArrayList<StringTemplate> messageTypeCollectionAdders = new ArrayList<StringTemplate>();
		
		StringTemplate messageTypeCollectionItemAdder = group.getInstanceOf("repeating_field_adder");
		messageTypeCollectionItemAdder.setAttribute("field_name", "person");
		messageTypeCollectionItemAdder.setAttribute("collection_field", "person");
		messageTypeCollectionItemAdder.setAttribute("field_type", "PERSON_WRAPPER_GEN");
		messageTypeCollectionAdders.add(messageTypeCollectionItemAdder);				
		
		wrapper.setAttribute("repeating_field_adders", messageTypeCollectionAdders);
		
//		$collection_field_getters; seperator="\n"$ GETS VIA INDEX
		ArrayList<StringTemplate> msgTypeCollFldGetters = new ArrayList<StringTemplate>();
		
		StringTemplate collFldGetter = group.getInstanceOf("collection_field_getter");
		collFldGetter.setAttribute("field_name", "person");		
		collFldGetter.setAttribute("field_type", "PERSON_WRAPPER_GEN");
		msgTypeCollFldGetters.add(collFldGetter);
		
		wrapper.setAttribute("collection_field_getters", msgTypeCollFldGetters);

//		$collection_low_index_getters; seperator="\n"$
		ArrayList<StringTemplate> msgTypeCollFldLowIndxGetters = new ArrayList<StringTemplate>();
		
		StringTemplate collFldLowIndxGetter = group.getInstanceOf("collection_field_low_index_getter");
		collFldLowIndxGetter.setAttribute("field_name", "person");
		msgTypeCollFldLowIndxGetters.add(collFldLowIndxGetter);
		
		wrapper.setAttribute("collection_low_index_getters", msgTypeCollFldLowIndxGetters);

//		$collection_upper_index_getters; seperator="\n"$
		ArrayList<StringTemplate> msgTypeCollFldUpperIndxGetters = new ArrayList<StringTemplate>();
		
		StringTemplate collFldUpperIndxGetter = group.getInstanceOf("collection_field_upper_index_getter");
		collFldUpperIndxGetter.setAttribute("field_name", "person");
		msgTypeCollFldUpperIndxGetters.add(collFldUpperIndxGetter);
		
		wrapper.setAttribute("collection_upper_index_getters", msgTypeCollFldUpperIndxGetters);
		
		//cpp getter for message type field
		ArrayList<StringTemplate> msgTypeFieldGetters = new ArrayList<StringTemplate>();
		
		StringTemplate cppMsgTypFldGetter = group.getInstanceOf("cpp_message_type_field_getter");
		cppMsgTypFldGetter.setAttribute("field_name", "manager");
		cppMsgTypFldGetter.setAttribute("field_type", "bosphorus::Person");
		cppMsgTypFldGetter.setAttribute("header_file_name", headerFile);
		cppMsgTypFldGetter.setAttribute("pb_message_type_name", "bosphorus::AddressBook");
		msgTypeFieldGetters.add(cppMsgTypFldGetter);
		
		wrapper.setAttribute("cpp_message_type_field_getters", msgTypeFieldGetters);
		
		//cpp message type field adders
		ArrayList<StringTemplate> cppMsgTypeFieldAdders = new ArrayList<StringTemplate>();
		
		StringTemplate cppMsgTypFldAdder = group.getInstanceOf("cpp_add_message_to_pb_object");
		cppMsgTypFldAdder.setAttribute("field_name", "person");
		cppMsgTypFldAdder.setAttribute("pb_parent_type", "bosphorus::AddressBook");
		cppMsgTypFldAdder.setAttribute("header_file_name", headerFile);
		cppMsgTypFldAdder.setAttribute("field_type", "bosphorus::Person");
		cppMsgTypeFieldAdders.add(cppMsgTypFldAdder);
		
		wrapper.setAttribute("cpp_repating_field_adders", cppMsgTypeFieldAdders);
		
		
		//cpp object creation 
		StringTemplate createCppObjFunc = group.getInstanceOf("create_cpp_object");
		createCppObjFunc.setAttribute("pb_message_type_name", "bosphorus::AddressBook");
		createCppObjFunc.setAttribute("header_file_name", headerFile);
		
		wrapper.setAttribute("create_cpp_object", createCppObjFunc);
				
		
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:\\work\\data\\Eiffel\\Eiffel-PB-ZMQ-Wrappers\\Protocol_Buffer_Wrapper\\address_book_wrapper_gen.e")));
			out.write(wrapper.toString().getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
//------------------------------------------------------------------------------------	
	public static void testWrapper(){
		StringTemplateGroup group =  new StringTemplateGroup("bosphorus", "C:\\tmp\\stemplTests", DefaultTemplateLexer.class);
		StringTemplate wrapper = group.getInstanceOf("wrapper");
		
		String headerFile = "ProtocolBuffersStubs-Eiffel.h";
		
		wrapper.setAttribute("type_name", "PERSON_WRAPPER_GEN");
		
		//message type members
		ArrayList<StringTemplate> messageTypeMembers = new ArrayList<StringTemplate>();
		
		StringTemplate messageTypeMember = group.getInstanceOf("wrapper_message_type_member");
		messageTypeMember.setAttribute("member_name", "phone");
		messageTypeMember.setAttribute("member_type", "PHONE_NUMBER_WRAPPER");
 
		messageTypeMembers.add(messageTypeMember);		
		wrapper.setAttribute("message_type_members", messageTypeMembers);
		
		//string getters
		ArrayList<StringTemplate> stringGetters = new ArrayList<StringTemplate>();
		
		StringTemplate stringGetter = group.getInstanceOf("string_getter");
		stringGetter.setAttribute("field_name", "name");
		stringGetters.add(stringGetter);
		
		StringTemplate stringGetter2 = group.getInstanceOf("string_getter");
		stringGetter2.setAttribute("field_name", "email");
		stringGetters.add(stringGetter2);
		
		wrapper.setAttribute("string_getters", stringGetters);
		
		//string setters
		ArrayList<StringTemplate> stringSetters = new ArrayList<StringTemplate>();
		
		StringTemplate stringSetter = group.getInstanceOf("string_setter");
		stringSetter.setAttribute("field_name", "name");
		stringSetters.add(stringSetter);
		
		StringTemplate stringSetter2 = group.getInstanceOf("string_setter");
		stringSetter2.setAttribute("field_name", "email");
		stringSetters.add(stringSetter2);
		
		wrapper.setAttribute("string_setters", stringSetters);
		
		//int getters
		ArrayList<StringTemplate> intGetters = new ArrayList<StringTemplate>();
		
		StringTemplate intGetter = group.getInstanceOf("int_getter");
		intGetter.setAttribute("field_name", "id");
		intGetters.add(intGetter);
		
		wrapper.setAttribute("int_getters", intGetters);
		
		//int setters
		ArrayList<StringTemplate> intSetters = new ArrayList<StringTemplate>();
				
		StringTemplate intSetter = group.getInstanceOf("int_setter");
		intSetter.setAttribute("field_name", "id");
		intSetters.add(intSetter);
		
		wrapper.setAttribute("int_setters", intSetters);
		
		//cpp string setters
		ArrayList<StringTemplate> cppStringSetters = new ArrayList<StringTemplate>();
		
		StringTemplate cppStringSetter = group.getInstanceOf("cpp_string_setter");
		cppStringSetter.setAttribute("field_name", "name");
		cppStringSetter.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppStringSetter.setAttribute("header_file_name", headerFile);
		cppStringSetters.add(cppStringSetter);
		
		StringTemplate cppStringSetter2 = group.getInstanceOf("cpp_string_setter");
		cppStringSetter2.setAttribute("field_name", "email");
		cppStringSetter2.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppStringSetter2.setAttribute("header_file_name", headerFile);
		cppStringSetters.add(cppStringSetter2);
		
		wrapper.setAttribute("cpp_string_setters", cppStringSetters);
		
		//cpp string getters
		ArrayList<StringTemplate> cppStringGetters = new ArrayList<StringTemplate>();
		
		StringTemplate cppStringGetter = group.getInstanceOf("cpp_string_getter");
		cppStringGetter.setAttribute("field_name", "name");
		cppStringGetter.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppStringGetter.setAttribute("header_file_name", headerFile);
		cppStringGetters.add(cppStringGetter);
		
		StringTemplate cppStringGetter2 = group.getInstanceOf("cpp_string_getter");
		cppStringGetter2.setAttribute("field_name", "email");
		cppStringGetter2.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppStringGetter2.setAttribute("header_file_name", headerFile);
		cppStringGetters.add(cppStringGetter2);
		
		wrapper.setAttribute("cpp_string_getters", cppStringGetters);
		
		//cpp int setters
		ArrayList<StringTemplate> cppIntSetters = new ArrayList<StringTemplate>();
		
		StringTemplate cppIntSetter = group.getInstanceOf("cpp_int_setter");
		cppIntSetter.setAttribute("field_name", "id");
		cppIntSetter.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppIntSetter.setAttribute("header_file_name", headerFile);
		cppIntSetters.add(cppIntSetter);
		
		wrapper.setAttribute("cpp_int_setters", cppIntSetters);
		
		//cpp int getters
		ArrayList<StringTemplate> cppIntGetters = new ArrayList<StringTemplate>();
		
		StringTemplate cppIntGetter = group.getInstanceOf("cpp_int_getter");
		cppIntGetter.setAttribute("field_name", "id");
		cppIntGetter.setAttribute("pb_message_type_name", "bosphorus::Person");
		cppIntGetter.setAttribute("header_file_name", headerFile);
		cppIntGetters.add(cppIntGetter);
		
		wrapper.setAttribute("cpp_int_getters", cppIntGetters);
		
		//create cpp object function
		StringTemplate createCppObjFunc = createCppObjectFunction(group, headerFile);		
		wrapper.setAttribute("create_cpp_object", createCppObjFunc);
		
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:\\work\\data\\Eiffel\\Eiffel-PB-ZMQ-Wrappers\\Protocol_Buffer_Wrapper\\person_wrapper_gen.e")));
			out.write(wrapper.toString().getBytes());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static StringTemplate createCppObjectFunction(
			StringTemplateGroup group, String headerFile) {
		StringTemplate createCppObjFunc = group.getInstanceOf("create_cpp_object");
		createCppObjFunc.setAttribute("pb_message_type_name", "bosphorus::Person");
		createCppObjFunc.setAttribute("header_file_name", headerFile);
		return createCppObjFunc;
	}

}
