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

import uk.ac.ucl.chime.utilities.CodedTextInfo;
import uk.ac.ucl.chime.utilities.QuantityInfo;
import uk.ac.ucl.chime.utilities.TextValueInfo;
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class TextAdapter extends RMDataTypeAdapter {

	public TextAdapter(ArchetypeWrapper pArchetypeWrapper) {
		archetypeWrapper = pArchetypeWrapper;
	}

	@Override
	protected Object getValue() throws Exception {		
		Object o = archetypeWrapper.getElementValue(targetNodePath);
		if (operationName.equals("selectedText")){									
			TextValueInfo info = (TextValueInfo) o;
			String currentSelection = ((TextValueInfo)o).getValue();
			return currentSelection;
		}
		else if (operationName.equals("allowedValues")){
			TextValueInfo info = (TextValueInfo) o;
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for (int i=0; i < info.allowedValues.size(); i++){
				items.add(new SelectItem(info.allowedValues.get(i), info.allowedValues.get(i)));
			}
			return items;
		}
		else if (operationName.equals("text")){
			TextValueInfo info = (TextValueInfo) o;
			return info.getValue();
		}
		else
			return null;
	}

	@Override
	protected void setValue(String nodePath, Object pValue) throws Exception {
		if (operationName.equals("selectedText")){									
			archetypeWrapper.getElementAtNode(nodePath).setSelectedText(pValue.toString());
		}		
		else if (operationName.equals("text")){
			archetypeWrapper.getElementAtNode(nodePath).setText(pValue.toString());
		}

	}

}
