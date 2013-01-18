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

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.tree.FieldNode;

public class FieldPrinter implements FieldVisitor {

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		System.out.println("visit begin for field printer");
		return new AnnotationPrinter(this);
//		return null;
		
		
	}

	public void visitAttribute(Attribute attr) {
		System.out.println(attr.type);

	}

	public void visitEnd() {
		System.out.println("visit end for field printer");		
	}

}
