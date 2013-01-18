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
package uk.ac.ucl.chime.bosphorus.openehr.xsd.processor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

/*
 * This class creates string templates for protocol buffer messages, which are supposed to represent 
 * some java types.
 */
public class PBMessageCreator {
	
	//the actual string template to be created
	private StringTemplate _messageST;

	//the template group, kind of a factory for the template to create
	private StringTemplateGroup _group;
	
	//order counter for protocol buffer message orders
	private int _order = 1;
	
	//fields of the type
	private ArrayList<StringTemplate> _fields = new ArrayList<StringTemplate>();
	
	//fields for representing all fields inherited from a parent type. Replacing inheritance with composition through
	//these fields. Parent A becomes field A, by adding it to this collection.
	private ArrayList<StringTemplate> _inheritedFieldsContainers = new ArrayList<StringTemplate>();
	
	/*fields for representing all fields inherited from a parent type. This linkedhashmap includes all fields individually
	*rather than using their container type's instance. A flattened representation of inheritance
	*linkedhashmap is used to preserve insertion order, which looks nicer when pb messages are generated
	*/	
	private LinkedHashMap<String, StringTemplate> _inheritedFields = new LinkedHashMap<String, StringTemplate>();
	
	public  PBMessageCreator(){
		_group = new StringTemplateGroup("PBMessages", "st", DefaultTemplateLexer.class);
		StringTemplate messageST = _group.getInstanceOf("pb_message");		
		_messageST = messageST;
	}
	
	public void setMessageName(String pMessageName){
		_messageST.setAttribute("messageName", pMessageName);
	}
	
	public void addField(String pFieldName, String pFieldType, String pFieldKind){	
			StringTemplate field = createField(pFieldName, pFieldType,pFieldKind);
			_fields.add(field);					
			_order++;		
	}
	
	/*
	 * Add an inherited field in the form of a string template
	 * Most likely to be added by a parent type
	 */
	public void addInheritedField(StringTemplate pField){		
		if(!(_inheritedFields.containsKey(pField.getAttribute("name").toString()))){
			//create a new st rather than modifying the incoming one (for order)
			StringTemplate field = createField(pField.getAttribute("name").toString(), 
												pField.getAttribute("type").toString(),
												pField.getAttribute("kind").toString());
			_inheritedFields.put(pField.getAttribute("name").toString(), field);
			_order++;
		}
	}

	private StringTemplate createField(String pFieldName, String pFieldType,
			String pFieldKind) {
		StringTemplate field = _group.getInstanceOf("pb_field");
		field.setAttribute("kind", pFieldKind);
		field.setAttribute("type", pFieldType);
		field.setAttribute("name", pFieldName);
		field.setAttribute("order", _order);
		return field;
	}
	
	/*
	 * This method adds a field with the type of a container class for a parent java type
	 * Each parent type ends up as a field. This is roughly replacing inheritance with composition
	 */
	public void addInheritedFieldsContainer(String pParentType){
		StringTemplate field = _group.getInstanceOf("pb_inheritence_field");
		field.setAttribute("parent_type", pParentType);
		field.setAttribute("parent_type_lower", pParentType.toLowerCase());
		field.setAttribute("order", _order);
		_inheritedFieldsContainers.add(field);
		_order++;
	}
	
	public StringTemplate getPBMessage(){
		//reset fields, then set it up
		_messageST.setAttribute("fields", null);
		_messageST.setAttribute("fields", _fields);
		
		_messageST.setAttribute("inheritence_fields", null);		

		//using one field in the pb message template to add both flattened fields and container type fields
		//only one of these approaches is used when generating pb messages
		_inheritedFieldsContainers.addAll(_inheritedFields.values());
		_messageST.setAttribute("inheritence_fields", _inheritedFieldsContainers);
		
		return _messageST;
	}
	
	//get an unmodifiable copy of fields
	public List<StringTemplate> getFields(){
		return Collections.unmodifiableList(_fields);
	}
	
//	public void writeToFile() throws Exception{
//		
//		
//		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("st/output.txt")));		
//		out.write(_projFile.toString().getBytes());
//		out.close();
//	}

}
