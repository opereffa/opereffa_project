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

import uk.ac.ucl.chime.wrappers.ItemStructureWrapper;
import uk.ac.ucl.chime.wrappers.ItemTreeWrapper;
import uk.ac.ucl.chime.wrappers.RIMWrapper;
import uk.ac.ucl.chime.wrappers.RMClassName;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ItemStructureWrapperFactory  {
	public ItemStructureWrapper getItemStructureWrapper(CComplexObject pItemStructureAOMObj, ArchetypeWrapper pContainerArchetype, RIMWrapper pParent) throws Exception{
		String rmtypeStr = pItemStructureAOMObj.getRmTypeName();
		if (RMClassName.valueOf(rmtypeStr.toUpperCase()).equals(RMClassName.ITEM_LIST)){
			throw new Exception("ITEM_LIST processing not implemented yet");
		}
		else if (RMClassName.valueOf(rmtypeStr.toUpperCase()).equals(RMClassName.ITEM_SINGLE)){
			throw new Exception("ITEM_SINGLE processing not implemented yet");
		}
		else if (RMClassName.valueOf(rmtypeStr.toUpperCase()).equals(RMClassName.ITEM_TABLE)){
			throw new Exception("ITEM_TABLE processing not implemented yet");
		}
		else if (RMClassName.valueOf(rmtypeStr.toUpperCase()).equals(RMClassName.ITEM_TREE)){
			ItemTreeWrapper itemTreeWrapper = new ItemTreeWrapper(pItemStructureAOMObj, pContainerArchetype, pParent );
			return itemTreeWrapper;
		}
		else
			throw new Exception("Unknown ITEM_STRUCTURE process request");
	}
}

