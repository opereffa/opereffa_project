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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.utilities.ItemStructureWrapperFactory;
import uk.ac.ucl.chime.wrappers.archetypewrappers.EvaluationArchetypeWrapper;

public class EvaluationWrapper extends EHRStructureWrapper {
	
	static Logger logger = Logger.getLogger(EvaluationWrapper.class);	
	
	protected ItemStructureWrapper itemStructureWrapper;
	
	public EvaluationWrapper(CComplexObject pEvaluationObject, EvaluationArchetypeWrapper pcontainerArchetypeWrapper, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pEvaluationObject;
		containerArchetype  = pcontainerArchetypeWrapper;		
		processEvaluation();
	}
	
	/*
	 * method that configures this wrapper. recursive initialization is performed
	 * using the incoming aom instance	
	 */
	public void processEvaluation() throws Exception{		
		CSingleAttribute dataAttr = (CSingleAttribute) ccomplexReference.getAttribute("data");		
		//according to IM specs, dataAttr has a member which descends from ITEM_STRUCTURE
		//which means it can be item_tree, item_list, Item_single, or item_table
		//dataattr should have only one children, its type being one of the above		
		processItemStructure((CComplexObject)dataAttr.getChildren().get(0));		
	}
	
	/*
	 * @param complexObject the Ccomplexobject instance that contains an ITEM_TREE
	 * as first child of attributes, which is a cmultipleattribute
	 */
	private void processItemStructure(CComplexObject pComplexObject) throws Exception {
		ItemStructureWrapperFactory factory = new ItemStructureWrapperFactory();
		ItemStructureWrapper wrapper =  factory.getItemStructureWrapper(pComplexObject, containerArchetype, this);
		itemStructureWrapper  = wrapper;		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() throws Exception {
//		//TODO: get rm evaluation instance
//		return null;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		if (ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in EvaluationWrapper");
	}

	@Override
	public String getGUITestString() throws Exception {
		return getEvaluationName() + " : \n" + itemStructureWrapper.getGUITestString();
	}

	private String getEvaluationName() {
		String nodeId = ccomplexReference.getNodeID();
		if (nodeId == null)
			nodeId = "";
		return containerArchetype.getNodeName(nodeId);
	}

	@Override
	public Object getTestInstance() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		TerminologyService ts =  SimpleTerminologyService.getInstance();
        DvText name = new DvText("test evlauation", getDefaultLanguage(), getDefaultEncoding(), ts);
        String node = "at0001";
        Archetyped archetypeDetails = new Archetyped(
            new ArchetypeID(containerArchetype.getArchetypeId()), "v1.0");
        ItemStructure data = (ItemStructure) itemStructureWrapper.getTestInstance();
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("language", getDefaultLanguage());
        values.put("encoding", getDefaultEncoding());
        values.put("subject", getDefaultSubject());
        values.put("provider", getDefaultProvider());
        values.put("data", data);
        RMObject obj = getBuilder().construct("Evaluation", values);
        return obj;
	}
	
	@Override
	public String getJSFWidget() {
		return itemStructureWrapper.getJSFWidget();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		return castList(itemStructureWrapper.getChildren(), Object.class);
	}
}
