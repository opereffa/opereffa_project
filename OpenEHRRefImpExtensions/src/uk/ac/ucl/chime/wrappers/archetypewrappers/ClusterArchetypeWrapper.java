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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;

import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.wrappers.ClusterWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.RIMWrapper;

public class ClusterArchetypeWrapper extends ArchetypeWrapper {
	protected String archetypeFileName;
	protected UUID instanceId = UUID.randomUUID();
	
	public ClusterWrapper getRootCluster(){
		return (ClusterWrapper)rootWrapper;
	}
	
	public UUID getInstanceId(){
		return instanceId;
	}
		
	private RIMWrapper findNodeInTree(ArchetypeInternalRef archetypeInternalRef) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getArchetypeFileName(){
		return archetypeFileName;
	}
	
	public ClusterArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory) throws Exception{
		initArchetypeWrapper(pArchetype, pFactory);
	}
	
	public ClusterArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory, boolean pIsInSlot) throws Exception{
		isInSlot = pIsInSlot;
		initArchetypeWrapper(pArchetype, pFactory);
	}
	
	@Override
	public void addElementWrapper(String elementPath,
			ElementWrapper elementWrapper) {
		elementWrappers.put(elementPath, elementWrapper);
		
	}

	@Override
	public String performAction() {
		System.out.println("I should arrive here with all things set!");
		return "done";
	}

	@Override
	public List<Object> getChildren() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		rootWrapper = new ClusterWrapper(definition,this, false, this);		
	}

}
