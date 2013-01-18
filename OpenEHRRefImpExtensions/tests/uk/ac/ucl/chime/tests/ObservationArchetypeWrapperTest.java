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
package uk.ac.ucl.chime.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.openehr.am.archetype.Archetype;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;


import uk.ac.ucl.chime.wrappers.ObservationWrapper;
import uk.ac.ucl.chime.wrappers.RIMWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ObservationArchetypeWrapper;
import junit.framework.TestCase;

public class ObservationArchetypeWrapperTest extends AbstractOpenEHRTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		arcWrapper1 = factory.loadFromFile(archetypesDir + obsArchFilename1);
				
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testPerformAction() {
//		fail("Not yet implemented");
//	}
//
	public void testGetRootWrapper() {
		RIMWrapper wrapper = arcWrapper1.getRootWrapper();
		assertTrue(wrapper instanceof ObservationWrapper);
		
	}

	public void testGetArchetype() {
		Archetype arc = arcWrapper1.getArchetype();
		assertTrue(arc != null);
	}
//
//	public void testGetNodeName() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetElementValue() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetElementAtNode() {
//		fail("Not yet implemented");
//	}
//
	public void testGetJSFForm() throws Exception {
		String obsArchXHTML1 = arcWrapper1.getJSFForm();
		String fileName1 = arcWrapper1.getAdlFileName();
		fileName1 = fileName1.replaceAll(".adl", ".xhtml");
		WriteXHTMLFile(testOutputsDir + fileName1, obsArchXHTML1);
		File output1 = new File(testOutputsDir + fileName1);
		assertEquals(output1.exists(), true);
	}

	public void testGetAdlFileName() {
		assertEquals(obsArchFilename1, arcWrapper1.getAdlFileName());
	}
	
	protected void WriteXHTMLFile(String pFileName, String pContent) throws Exception{
		FileOutputStream fos = new FileOutputStream(new File(pFileName));
		OutputStreamWriter writer = new OutputStreamWriter(fos);
		writer.write(pContent);
		writer.close();		
	}

}
