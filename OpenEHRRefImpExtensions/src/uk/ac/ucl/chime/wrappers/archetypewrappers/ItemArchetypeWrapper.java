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
package uk.ac.ucl.chime.wrappers.archetypewrappers;

import java.util.List;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;

public class ItemArchetypeWrapper extends ArchetypeWrapper {
	
	public ItemArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory) throws Exception{
		initArchetypeWrapper(pArchetype, pFactory);
	}	
	
	public ItemArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory, boolean pIsInSlot) throws Exception{
		isInSlot = pIsInSlot;
		initArchetypeWrapper(pArchetype, pFactory);
	}

	@Override
	public void addElementWrapper(String dataValueWrapperPath,
			ElementWrapper elementWrapper) {
		// TODO Auto-generated method stub

	}

	@Override
	public String performAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getChildren() throws Exception {
		return rootWrapper.getChildren();
	}

	@Override
	public IWrapperNavigator getParent() throws Exception {		
		return null;
	}

	@Override
	protected void initArchetypeWrapper(Archetype pArchetype,
			ArchetypeWrapperFactory pFactory) throws Exception {
		wrapperFactory = pFactory;
		archetype = pArchetype;
		CComplexObject definition = archetype.getDefinition();
		//TODO: well, item is an abstract class
		//so this archetype wrapper and the class below should
		//never be needed, but this case exists in Ocean's archetype
		//designer tool, and I've included it just to make sure
		//though I don't think the class below will ever be implemented
//		rootWrapper = new ItemWrapper(definition, this);
		
	}

}
