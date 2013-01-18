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

public class AnnotationPrinter implements AnnotationVisitor {

	private FieldPrinter _parent; 
	
	public AnnotationPrinter(FieldPrinter pParent){
		_parent = pParent;
	}
	
	public void visit(String name, Object value) {
		System.out.println("annotation visitor name and value");
	}

	public void visitEnum(String name, String desc, String value) {
		System.out.println("visit enum in annotation printer");

	}

	public AnnotationVisitor visitAnnotation(String name, String desc) {
		return new AnnotationPrinter(_parent);
	}

	public AnnotationVisitor visitArray(String name) {
		return new AnnotationPrinter(_parent);
	}

	public void visitEnd() {
		System.out.println("annotation printer visit end");

	}

}
