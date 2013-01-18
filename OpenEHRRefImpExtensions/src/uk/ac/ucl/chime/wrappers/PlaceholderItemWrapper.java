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

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.apache.commons.lang.NotImplementedException;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;

import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.NodeInfo;

public class PlaceholderItemWrapper extends RIMWrapper {
	private ArchetypeInternalRef internalRef;
	
	public ArchetypeInternalRef getInternalRef(){
		return internalRef;
	}
	
//	public PlaceholderItemWrapper(ArchetypeInternalRef realNodetouse, ItemWrapper pparent){
//		parent = pparent;
//		internalRef = realNodetouse;
//	}
	
	public PlaceholderItemWrapper(ArchetypeInternalRef realNodetouse){
		internalRef = realNodetouse;
	}
	
	@Override
	public List<Object> getChildren() {
		// TODO implement it based on the slot handling implementation
		//for now, simply throw an exception
		throw new NotImplementedException("Navigation in adl nodes has not been implemented yet for PlaceholderItemWrapper");
	}

//	@Override
//	public Component getGUIComponent() {
//		return new JLabel("this is just a placeholder for another archetype");
//	}

//	@Override
//	public void setValueFromRIMInstance(Cluster c) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void setRIMValue(Cluster c) throws Exception {
//		internalRef.getRmTypeName();
//		
//	}

//	@Override
//	public RIMWrapper getParent() {
//		return parent;
//	}

	//TODO: this will have to change as archetypeslots are implemented
//	@Override
//	public List<RIMWrapper> getChildren() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	//TODO: this will have to change as archetypeslots are implemented
//	@Override
//	public boolean hasChildren() {
//		return false;
//	}

//	@Override
//	public ArrayList<ArrayList<PanelFieldInfo>> getComponentRows() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String getEncodedNodePath() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Object getRIMValue(NodeInfo[] infoArr) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getGUITestString() {
		return "this is a placeholder";
	}

	@Override
	public Object getTestInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJSFWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "NOT IMPLEMENTED YET";
	}
}
