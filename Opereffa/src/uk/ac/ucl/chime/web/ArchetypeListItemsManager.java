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

public class ArchetypeListItemsManager {

	protected ArchetypeWrapper archetypeWrapper;
	
	public ArchetypeListItemsManager(ArchetypeWrapper pArchetypeWrapper){
		archetypeWrapper = pArchetypeWrapper;
	}
	
	public Object getItemsListFromNode(String pNodePath){
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
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for(int i = 0; i < info.codes.size(); i++){
				items.add(new SelectItem(info.codes.get(i), info.values.get(i)));
			}
			return items;
		}
		else if (o instanceof DurationInfo){
			//contains multiple widgets in UI -> textbox and dropdown, can't return two objects..
			//TODO
			return null;
		}
		else if (o instanceof OrdinalInfo){
			OrdinalInfo info = (OrdinalInfo) o;
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for(int i = 0; i < info.labels.size(); i++){
				items.add(new SelectItem(info.values.get(i), info.labels.get(i)));
			}
			return items;
		}
		else if (o instanceof QuantityInfo){
			//in the context of this class, this method should return a list of units
			QuantityInfo info = (QuantityInfo) o;
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for (int i=0; i < info.units.size(); i++){
				items.add(new SelectItem(info.units.get(i), info.units.get(i)));
			}
			return items;
		}
		else return null;
		
	}
}
