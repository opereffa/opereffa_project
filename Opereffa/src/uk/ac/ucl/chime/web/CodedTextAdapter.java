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
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class CodedTextAdapter extends RMDataTypeAdapter {

	public CodedTextAdapter(ArchetypeWrapper pArchetypeWrapper) {
		archetypeWrapper = pArchetypeWrapper;
	}
	

	@Override
	protected Object getValue() throws Exception {		
		Object o = archetypeWrapper.getElementValue(targetNodePath);
		CodedTextInfo info = (CodedTextInfo) o;
		if (operationName.equals("selectedCode")){									
			String currentSelection = info.getCurrentSelectionCode();
			return currentSelection;
		}
		else if (operationName.equals("codesList")){			
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			for (int i=0; i < info.codes.size(); i++){
				items.add(new SelectItem(info.codes.get(i), info.values.get(i)));
			}
			return items;
		}
		else if(operationName.equals("selectedCodes")){
			ArrayList<SelectItem> items = new ArrayList<SelectItem>();
			ArrayList<String> itemCodes = new ArrayList<String>();
			ArrayList<String> codesOfCurrentSelections = info.getCodesOfCurrentSelections();
			ArrayList<String> namesOfCurrentSelections = info.getNamesOfCurrentSelections();
			for (int i = 0; i < codesOfCurrentSelections.size(); i++) {
				//items.add(new SelectItem(codesOfCurrentSelections.get(i), namesOfCurrentSelections.get(i)));
				itemCodes.add(codesOfCurrentSelections.get(i));
			}
			return itemCodes;
		}
		else if (operationName.equals("selectedTerminologyItemName")){
			String currentSelectionRubric = info.getCurrentSelectionRubric();
			return currentSelectionRubric;
		}
		else if (operationName.equals("selectedTerminologyItemCode")){
			String currentSelectionCode = info.getCurrentSelectionCode();
			return currentSelectionCode;
		}
		else
			return null;
	}

	@Override
	protected void setValue(String nodePath, Object pValue) throws Exception {		
		if (operationName.equals("selectedCode")){									
			archetypeWrapper.getElementAtNode(nodePath).setSelectedCodeForText(pValue.toString());
		}
		else if (operationName.equals("selectedCodes")){									
			archetypeWrapper.getElementAtNode(nodePath).setSelectedCodeForText(pValue.toString());
		}
		else if (operationName.equals("selectedTerminologyItemCode")){
			archetypeWrapper.getElementAtNode(nodePath).setSelectedTerminologyItem(pValue.toString());
		}

	}
	
	//this add operation is DB based, in a web environment, this is pretty much 
	//obvious, but a session based bean or storage would allow the same things without db persistence
	public void addValue(String nodePath, String pValue) throws Exception {		
		if (operationName.equals("selectedCodes")){									
			archetypeWrapper.getElementAtNode(nodePath).addSelectedCodeForText(pValue);
		}		

	}
	
	public void removeValue(String pNodePath, String pValue) throws Exception{
		if (operationName.equals("selectedCodes")){
			archetypeWrapper.getElementAtNode(pNodePath).deleteSelectedCodeForText(pValue);
		}
	}

}
