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

import java.util.HashSet;
import java.util.ListIterator;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class ClassNodePB extends ClassNode {

	
	private HashSet<String> _fieldTypes = new HashSet<String>();
	
	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {	
		super.visit(version, access, name, signature, superName, interfaces);		
	}
	
	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {		
		return super.visitField(access, name, desc, signature, value);		
	}
	
	@Override
	public void visitEnd() {		
		super.visitEnd();		
	}

	public void setFieldTypes(HashSet<String> pFieldTypes) {
		_fieldTypes = pFieldTypes;
	}

	public HashSet<String> getFieldTypes() {
		return _fieldTypes;
	}
}
