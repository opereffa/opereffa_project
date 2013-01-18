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

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.ObservationWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import junit.framework.TestCase;

public class ObservationWrapperTest extends AbstractOpenEHRTestCase {

	protected ObservationWrapper wrapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		arcWrapper1 = factory.loadFromFile(archetypesDir + obsArchFilename1);
		wrapper = (ObservationWrapper) arcWrapper1.getRootWrapper();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetGUITestString() throws Exception {		
		String guiString = wrapper.getGUITestString();
		assertTrue(guiString != null);
	}

	public void testGetRMTypeName() throws Exception {
		String rmTypeName = wrapper.getRMTypeName();
		assertTrue(rmTypeName != null);
	}

	public void testGetJSFWidget() {
		String widgetSource = wrapper.getJSFWidget();
		assertTrue(widgetSource != null);
	}

}
