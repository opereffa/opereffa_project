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
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter implements ClassVisitor {

	private PBMessageCreator _creator = new PBMessageCreator();
	
	public void visit(int arg0, int arg1, String arg2, String arg3,
			String arg4, String[] arg5) {
		_creator.setMessageName(arg2.substring(arg2.lastIndexOf('/') + 1));
	}

	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void visitAttribute(Attribute arg0) {
		// TODO Auto-generated method stub

	}

	public void visitEnd() {
		

	}

	public FieldVisitor visitField(int arg0, String pName, String pDescriptor,
			String arg3, Object arg4) {
		System.out.println("visit begin for field");
		FieldPrinter fieldPrinter = new FieldPrinter();
		return fieldPrinter;
		
//		return null;
	}

	public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public MethodVisitor visitMethod(int arg0, String arg1, String arg2,
			String arg3, String[] arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	public void visitOuterClass(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	public void visitSource(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
