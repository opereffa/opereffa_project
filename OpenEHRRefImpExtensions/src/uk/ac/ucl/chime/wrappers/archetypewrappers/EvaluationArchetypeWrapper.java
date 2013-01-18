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
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;

import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;

import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.EvaluationWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;

public class EvaluationArchetypeWrapper extends ArchetypeWrapper {

	private Hashtable<EvaluationWrapper, List<ArchetypeInternalRef>>  archetypeInternalRefs = new Hashtable<EvaluationWrapper, List<ArchetypeInternalRef>>();
	
	public EvaluationWrapper getRootEvaluation(){
		return (EvaluationWrapper)rootWrapper;
	}
	
//	public Evaluation getEvaluation() throws Exception{
//		EvaluationWrapper evWrapper = (EvaluationWrapper)rootWrapper;
//		return (Evaluation) evWrapper.getRIMValue();
//	}
	
	protected void addInternalRef(EvaluationWrapper addingEvalWrapper, ArchetypeInternalRef refToResolve){
		if (archetypeInternalRefs.contains(addingEvalWrapper)){
			List<ArchetypeInternalRef> archetypeRefsList = archetypeInternalRefs.get(addingEvalWrapper);
			archetypeRefsList.add(refToResolve);
			return;
		}
		else{
			List<ArchetypeInternalRef> internalRefListForThisCluster = new ArrayList<ArchetypeInternalRef>();
			internalRefListForThisCluster.add(refToResolve);
			archetypeInternalRefs.put(addingEvalWrapper, internalRefListForThisCluster);
		}
	}
	
	public boolean resolveInternalReferences(EvaluationWrapper cwrapperToResolve) throws Exception{
//		if (archetypeInternalRefs.contains(cwrapperToResolve)){
//			List<ArchetypeInternalRef> archetypeRefsList = archetypeInternalRefs.get(cwrapperToResolve);
//			for(int i = 0; i < archetypeRefsList.size(); i++){
//				ItemWrapper resolvedItem = findNodeInTree(archetypeRefsList.get(i));
//				if (resolvedItem == null)
//					throw new Exception("Could not resolve ItemWrapper: " + getNodeName(archetypeRefsList.get(i).getTargetPath()));
//				cwrapperToResolve.updateInternalReference(archetypeRefsList.get(i), resolvedItem);
//			}
//			return true;
//		}
//		else{
//			throw new Exception("There are no registered internal references for this cluster: " + getNodeName(cwrapperToResolve.getClusterNodeId()));
//		}		
		//TODO: implement internal archetype references resolver here 
		return true;
	}

	public EvaluationArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory) throws Exception{
		initArchetypeWrapper(pArchetype, pFactory);
	}
	
	public EvaluationArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory, boolean pIsInSlot) throws Exception{
		isInSlot = pIsInSlot;
		initArchetypeWrapper(pArchetype, pFactory);
	}

	@Override
	public void addElementWrapper(String elementPath,
			ElementWrapper elementWrapper) {
		elementWrappers.put(elementPath, elementWrapper);
		
	}

	@Override
	public List<Object> getChildren() throws Exception {
		return rootWrapper.getChildren();
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
		EvaluationWrapper ew = new EvaluationWrapper(definition,this, this);
		rootWrapper = ew;
		
	}
}
