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
package uk.ac.ucl.chime.utilities;

import org.openehr.am.archetype.constraintmodel.CComplexObject;

import uk.ac.ucl.chime.wrappers.AnalysisElementWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

/*
 * This class uses the configuration file to return either a normal ElementWrapper instance or 
 * an AnalysisElementWrapper instance 
 */
public class ElementWrapperFactory {
	private ElementWrapper _ew;
	
	//don't allow empty initialization
	private ElementWrapperFactory(){}
	
	public  ElementWrapperFactory(CComplexObject obj, ArchetypeWrapper pArchetypeWrapper, boolean pHasContainerTypeParent, IWrapperNavigator pParent) throws Exception{
		if (ConfigurationManager.getElementWrapperType().equals(ElementWrapper.class))
			_ew = new ElementWrapper(obj, pArchetypeWrapper, pHasContainerTypeParent, pParent);
		else if (ConfigurationManager.getElementWrapperType().equals(AnalysisElementWrapper.class))
			_ew = new AnalysisElementWrapper(obj, pArchetypeWrapper, pHasContainerTypeParent, pParent);
	}
	
	public ElementWrapper getElementWrapperInstance(){
		return _ew;
	}
}

