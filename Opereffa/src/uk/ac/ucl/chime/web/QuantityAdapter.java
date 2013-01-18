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

import uk.ac.ucl.chime.utilities.QuantityInfo;
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class QuantityAdapter extends RMDataTypeAdapter {
	
	public QuantityAdapter(ArchetypeWrapper pWrapper){
		archetypeWrapper = pWrapper;
	}
	
	protected Object getValue(){		
		Object o = archetypeWrapper.getElementValue(targetNodePath);
		if (operationName.equals("selectedUnit")){						
			//we know that this is a quantitywrapper and we are expected to get selected unit
			QuantityInfo info = (QuantityInfo) o;
			String currentSelection = ((QuantityInfo)o).getCurrentUnit();
			return currentSelection;
		}
		else if (operationName.equals("unitsList")){
			QuantityInfo info = (QuantityInfo) o;
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for (int i=0; i < info.units.size(); i++){
				items.add(new SelectItem(info.units.get(i), info.units.get(i)));
			}
			return items;
		}
		else if (operationName.equals("magnitude")){
			QuantityInfo info = (QuantityInfo) o;
			return info.getMagnitude();
		}
		else
			return null;
	}
	
	public void setValue(String pNodePath, Object pValue) throws Exception{
		if (operationName.equals("selectedUnit")){						
			archetypeWrapper.getElementAtNode(pNodePath).setUnitOfQuantity(pValue.toString());
		}
		else if(operationName.equals("magnitude")){
			archetypeWrapper.getElementAtNode(pNodePath).setMagnitudeOfQuantity(pValue.toString());
		}
		System.out.println("trying to set value for operation " + operationName  +
				" with value " + pValue + " and node path " + pNodePath);
	}

}
