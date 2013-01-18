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
package uk.ac.ucl.chime.web;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import uk.ac.ucl.chime.utilities.BooleanInfo;
import uk.ac.ucl.chime.utilities.CodedTextInfo;
import uk.ac.ucl.chime.utilities.DurationInfo;
import uk.ac.ucl.chime.utilities.OrdinalInfo;
import uk.ac.ucl.chime.utilities.QuantityInfo;
import uk.ac.ucl.chime.utilities.TextValueInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ObservationArchetypeWrapper;

public class ArchetypeSingleListItemManager {
	
	protected ArchetypeWrapper archetypeWrapper;
	
	public ArchetypeSingleListItemManager(ArchetypeWrapper pWrapper){
		archetypeWrapper = pWrapper;
	}
	
	public void setCurrentSelectionOfNode(String pNodePath, String pValue){
		//the elementwrapper can handle all the details here...
		System.out.println("Set value of node: " + pNodePath + " with value: \n" + pValue);
	}
	
	public Object getCurrentSelectionFromNode(String pNodePath){
		ObservationArchetypeWrapper oaw = (ObservationArchetypeWrapper) archetypeWrapper;//valid only for now
		Object o = oaw.getElementValue(pNodePath);
		if (o == null)
			return null;
		if (o instanceof TextValueInfo){
			//not valid...
			return null;
		}
		else if (o instanceof BooleanInfo){
			//not valid
			return null;
		}
		else if (o instanceof CodedTextInfo){
			CodedTextInfo info = (CodedTextInfo) o;
			String currentSelection = ((CodedTextInfo) o).getCurrentSelectionCode();
			return currentSelection;
		}
		else if (o instanceof DurationInfo){
			//contains multiple widgets in UI -> textbox and dropdown, can't return two objects..
			//TODO
			return null;
		}
		else if (o instanceof OrdinalInfo){
			OrdinalInfo info = (OrdinalInfo) o;
			//TODO
			return null;
		}
		else if (o instanceof QuantityInfo){
			//we should return the current unit in the context of this class
			QuantityInfo info = (QuantityInfo) o;
			String currentSelection = ((QuantityInfo)o).getCurrentUnit();
			return currentSelection;
		}
		else return null;
		
	}

}
