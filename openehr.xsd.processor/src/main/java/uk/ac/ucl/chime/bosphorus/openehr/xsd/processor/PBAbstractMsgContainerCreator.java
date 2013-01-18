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

import java.util.ArrayList;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

/*
 * This class creates StringTemplate for a type which has other types descending from it.
 * Then this type is referenced in a field, it means either this type (if it is not abstract) or one
 * of its children can be used at this point. To move data around, the protocol buffer message needs to support
 * all types descending from it. This class generates a container message in protocol buffers which provides
 * this functionality. Added fields end up as options for the actual type to be used, which is a descendant of the parent type
 */
public class PBAbstractMsgContainerCreator {
	//the template for message
	private StringTemplate _messageST;
	//main template group, necessary to create the string template for message
	private StringTemplateGroup _group;
	private int _order = 1;
	private ArrayList<StringTemplate> _subtypeFields = new ArrayList<StringTemplate>();
	
	public PBAbstractMsgContainerCreator(){
		_group = new StringTemplateGroup("PBMessages", "st", DefaultTemplateLexer.class);
		StringTemplate messageST = _group.getInstanceOf("abstract_type_alternatives");		
		_messageST = messageST;
	}
	
	public void setMessageName(String pMessageName){
		_messageST.setAttribute("abstract_type_name", pMessageName.toUpperCase());
	}
	
	public void addField(String pSubtypeName, String pFieldName){	
		StringTemplate field = _group.getInstanceOf("abstract_type_alternative");
		field.setAttribute("abstract_type_alternative", pSubtypeName);
		field.setAttribute("alternative_field_name", pFieldName);		
		field.setAttribute("order", _order);
		_subtypeFields.add(field);					
		_order++;		
	}		
	
	public StringTemplate getPBMessage(){
		//reset fields, then set it up	
		_messageST.setAttribute("abstract_type_alternatives", null);
		_messageST.setAttribute("abstract_type_alternatives", _subtypeFields);
		
		return _messageST;
	}
}
