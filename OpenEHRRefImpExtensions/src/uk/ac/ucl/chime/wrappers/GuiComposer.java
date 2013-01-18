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
package uk.ac.ucl.chime.wrappers;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.openehr.rm.datastructure.itemstructure.representation.Cluster;

import uk.ac.ucl.chime.gui.MainForm;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ClusterArchetypeWrapper;


public class GuiComposer {

	protected ClusterArchetypeWrapper archetypeWrapper;
	protected MainForm formtoDecorate;
	
	public GuiComposer(ClusterArchetypeWrapper pArchetypeWrapper){
		archetypeWrapper = pArchetypeWrapper;		
	}
	
	public  void ComposeGui(MainForm pformToDecorate) throws Exception{
//		formtoDecorate = pformToDecorate;
//		ClusterWrapper cw = archetypeWrapper.getRootCluster();
//		formtoDecorate.clearForm();
//		addElement(cw);
	}
	
//	public void ComposeGui(MainForm pformToDecorate, Cluster pcluster) throws Exception{
//		ComposeGui(pformToDecorate);
//		//now update ui values. 
//		archetypeWrapper.setUIValues(pcluster);
//	}
	
//	protected void addElement(ClusterWrapper cw) throws Exception{
//		formtoDecorate.addComponentasRow(cw.getGUIComponent());
//		for(int i = 0; i < cw.getItemWrappers().size(); i++){
//			if (cw.getItemWrappers().get(i) instanceof ClusterWrapper)
//				addElement((ClusterWrapper) cw.getItemWrappers().get(i));
//			else if (cw.getItemWrappers().get(i) instanceof ElementWrapper)
//				addElement(cw.getItemWrappers().get(i));
//		}
//	}

//	private void addElement(EHRConceptWrapper itemWrapper) {		
//		formtoDecorate.addComponentasRow(itemWrapper.getGUIComponent());
//		
//	}
}
