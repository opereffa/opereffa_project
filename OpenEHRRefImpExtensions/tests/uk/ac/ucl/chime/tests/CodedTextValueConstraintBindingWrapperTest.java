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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvCodedText;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.CodedTextValueConstraintBindingWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.EvaluationArchetypeWrapper;

public class CodedTextValueConstraintBindingWrapperTest extends AbstractOpenEHRTestCase {

	private ArchetypeWrapper termBoundArcWrp;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		termBoundArcWrp = factory.loadFromFile("c:\\archetypeRepository\\openEHR-EHR-EVALUATION.SOAP_Assessment_RCPTERMBOUND.v3.adl");
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetTestInstance() throws Exception {
		EvaluationArchetypeWrapper wrapper = (EvaluationArchetypeWrapper) termBoundArcWrp;
		ElementWrapper ew = wrapper.getElementAtNode("/data[at0001]/items[at0003]/value");
		assertEquals(ew == null, false);
		//get 100 test instances, which should have different codes
		for(int i = 0; i < 100; i++){
			Object rmElement = ew.getTestInstance();
			if (rmElement == null)//at the moment, random terminology code generator may return null
				continue;
			DvCodedText dvCodedTxt =  (DvCodedText) ((Element)rmElement).getValue();
			assertEquals(dvCodedTxt.getValue() == null, false);
			assertEquals(dvCodedTxt.getValue().length() < 1, false);
			assertEquals(dvCodedTxt.getDefiningCode().getCodeString() == null, false);
			assertEquals(dvCodedTxt.getDefiningCode().getCodeString().length() < 1, false);
			assertEquals(((DvCodedText)dvCodedTxt).getValue().length() > 0 , true);			
		}					
	}

}

