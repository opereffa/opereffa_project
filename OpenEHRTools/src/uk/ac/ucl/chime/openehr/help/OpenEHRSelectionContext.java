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
package uk.ac.ucl.chime.openehr.help;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.help.IContext;
import org.eclipse.help.IContext2;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.viewers.IStructuredSelection;


import uk.ac.ucl.chime.wrappers.ActivityWrapper;
import uk.ac.ucl.chime.wrappers.ClusterWrapper;
import uk.ac.ucl.chime.wrappers.CodedTextValueWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.EvaluationWrapper;
import uk.ac.ucl.chime.wrappers.EventWrapper;
import uk.ac.ucl.chime.wrappers.HistoryWrapper;
import uk.ac.ucl.chime.wrappers.InstructionWrapper;
import uk.ac.ucl.chime.wrappers.ItemTreeWrapper;
import uk.ac.ucl.chime.wrappers.ObservationWrapper;
import uk.ac.ucl.chime.wrappers.QuantityWrapper;
import uk.ac.ucl.chime.wrappers.TextValueWrapper;

public class OpenEHRSelectionContext implements IContext2 {

	private String _text;
	private String _title;
	private IHelpResource[] _staticHelpResources;
	
	public OpenEHRSelectionContext(IContext pContext, IStructuredSelection pSelection) {				
		
		if (pContext instanceof IContext2) {
			_title = ((IContext2) pContext).getTitle();
		}
		
		System.out.println(pSelection.getFirstElement().toString());
		List<IHelpResource> helpResourcesList = new ArrayList<IHelpResource>();
		String label = null;
		String location = null;
		//use selection to find out which title and location to use
		if(pSelection.getFirstElement() instanceof TextValueWrapper){
			label = "DV_Text";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/textPackage-1.xhtml#pgfId-1046948";
		}
		if(pSelection.getFirstElement() instanceof CodedTextValueWrapper){
			label = "DV_Coded_Text";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/textPackage-1.xhtml#pgfId-1062224";
		}
		else if (pSelection.getFirstElement() instanceof QuantityWrapper){
			label = "DV_Quantity";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/quantityPackage-1.xhtml#pgfId-1083034"; 
		}
		else if (pSelection.getFirstElement() instanceof ItemTreeWrapper){
			label = "Item_Tree";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/item_structure-1.xhtml#pgfId-1106136"; 
		}
		else if (pSelection.getFirstElement() instanceof ElementWrapper){
			label = "Element";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/representation-1.xhtml#pgfId-1106481";
		}
		else if (pSelection.getFirstElement() instanceof ClusterWrapper){
			label = "Cluster";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/representation-1.xhtml#pgfId-1106401";
		}
		else if (pSelection.getFirstElement() instanceof ObservationWrapper){
			label = "Observation";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/main-clinical-3.xhtml#pgfId-1084355";
		}
		else if (pSelection.getFirstElement() instanceof EvaluationWrapper){
			label = "Evaluation";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/main-clinical-3.xhtml#pgfId-1084424"; 
		}
		else if (pSelection.getFirstElement() instanceof InstructionWrapper){
			label = "Instruction";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/main-clinical-3.xhtml#pgfId-1124595";
		}
		else if (pSelection.getFirstElement() instanceof ActivityWrapper){
			label = "Activity";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/main-clinical-3.xhtml#pgfId-1114900";
		}
		else if (pSelection.getFirstElement() instanceof HistoryWrapper){
			label = "History";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/historyPackage-1.xhtml#pgfId-1113825";
		}
		else if (pSelection.getFirstElement() instanceof EventWrapper){
			label = "Event";
			location = "/uk.ac.ucl.chime.opereffa/helpcontent/historyPackage-1.xhtml#pgfId-1126924";
		}
		
		//ehr, action 
		//StringBuffer location = new StringBuffer("OpenEHRTools/helpcontent/main-clinical-3.xhtml#pgfId-1121441");
		helpResourcesList.clear();
		if (label != null && location != null)
			helpResourcesList.add(new OpenEHRSelectionHelpResource(label, location.toString()));		
		// these topics will always be used
		if (pContext != null) {
			IHelpResource[] helpResourceArr = pContext.getRelatedTopics();
			if (helpResourceArr != null) {
				for (int j = 0; j < helpResourceArr.length; j++) {
					helpResourcesList.add(helpResourceArr[j]);
				}
			}
		}
		_staticHelpResources = helpResourcesList.toArray(new IHelpResource[helpResourcesList.size()]);
		if (pContext != null) {
			_text = pContext.getText();
		}
		if (_text == null) {
			_text = "";
		}
	}

	@Override
	public String getCategory(IHelpResource topic) {
		if (topic instanceof OpenEHRSelectionHelpResource) {
			return "openEHR standard sections";
		}
		return null;
	}

	@Override
	public String getStyledText() {
		return _text;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public IHelpResource[] getRelatedTopics() {
		return _staticHelpResources;
	}

	@Override
	public String getText() {
		return _text;
	}

}
