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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.ElementWrapperFactory;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ItemTreeWrapper extends ItemStructureWrapper {
	
	//An ITEM_TREE from IM contains an ITEM which is a combination of 
	//CLUSTERs and ELEMENTs. (List<ITEM>) this variable is the root of the tree, which is 
	//represented by one or more ELEMENT or CLUSTERs
	//nodes at the root of the tree must be processed according to its RM type 
	protected ArrayList<RIMWrapper> rootItems = new ArrayList<RIMWrapper>();
	
	public ArrayList<RIMWrapper> getRootItems(){
		return rootItems;
	}
	
	public ItemTreeWrapper(CComplexObject pItemTreeObject, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pItemTreeObject;
		containerArchetype = pContainerArchetype;
		processItemStructureWrapper();
	}
	
	/*
	 * @param complexObject the Ccomplexobject instance that contains an ITEM_TREE
	 * as first child of attributes, which is a cmultipleattribute
	 */
	protected void processItemStructureWrapper() throws Exception {
		//itemscontainer has an attribute called items according to RM specs
		//this attribute points to a list of ITEM s, and ITEM is the abstract class from which 
		//CLUSTERs and ELEMENTs drive. so let's process these ITEMs
		CMultipleAttribute itemsAttribute = (CMultipleAttribute) ccomplexReference.getAttribute("items");
		boolean isContainerAttribute = false;
		if (itemsAttribute instanceof CMultipleAttribute 
				&& ((CMultipleAttribute)itemsAttribute).getCardinality() != null )
			//this is a container type, so lets let the children know this
			//they should know that their parent is a container type
			isContainerAttribute = true;
		for (CObject obj : itemsAttribute.getChildren()){
			if (RMClassName.valueOf(obj.getRmTypeName()) == RMClassName.CLUSTER){
				if (obj instanceof CComplexObject){
					ClusterWrapper childCw = new ClusterWrapper((CComplexObject) obj, containerArchetype, isContainerAttribute, this);
					rootItems.add(childCw);
				}
				else if (obj instanceof ArchetypeSlot){
					ClusterWrapper childCw = new ClusterWrapper((ArchetypeSlot)obj, containerArchetype, isContainerAttribute, this);
					rootItems.add(childCw);
				}
			}					
			else if (RMClassName.valueOf(obj.getRmTypeName()) == RMClassName.ELEMENT){
				if (obj instanceof CComplexObject){
					ElementWrapperFactory factory = new ElementWrapperFactory((CComplexObject)obj, containerArchetype, isContainerAttribute, this);
					ElementWrapper ew = factory.getElementWrapperInstance();
//					ew.processElement();
					if (!ew.isEmpty())
						rootItems.add(ew);						
				}					
				else if (obj instanceof ArchetypeInternalRef){
					//TODO: you may not be able to resolve this path now, it may
					//be referring to a node further in the tree, which has not been processed yet!
					ArchetypeInternalRef internalRef = (ArchetypeInternalRef) obj;
					
					PlaceholderItemWrapper plcholder = new PlaceholderItemWrapper(internalRef);
					//add a placeholder instead of actual item and let parent archetype know.
					rootItems.add(plcholder);
					//keep o link to plcholder to allow archetype to update it later
					//containerArchetype.internalRefPlaceholders.add(plcholder);
					containerArchetype.addInternalRef(this, internalRef);
				}
			}						
		}
	}	

	@Override
	public String getGUITestString() throws Exception {
//		StringBuffer sb = new StringBuffer("this is a tree of following items");
		StringBuffer sb = new StringBuffer();
		for(RIMWrapper wrapper : rootItems){
			sb.append(wrapper.getGUITestString() + "\n");
		}
		return sb.toString();
	}

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

	@Override
	public Object getTestInstance() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = ccomplexReference.getNodeID();
        TerminologyService ts =  SimpleTerminologyService.getInstance();
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()),
        		getDefaultLanguage(),
        		getDefaultEncoding(), ts);
        List<Item> items = new ArrayList<Item>();
        for(RIMWrapper wrapper : rootItems){
        	items.add((Item) wrapper.getTestInstance());
        }
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("items", items);
        RMObject obj = getBuilder().construct("ItemTree", values);
        return obj;
	}

	@Override
	public String getJSFWidget() {
		//just join items and send them
		StringBuffer sb = new StringBuffer();
		for(RIMWrapper wrapper : rootItems){
			sb.append(wrapper.getJSFWidget());
		}
		return sb.toString();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() {
		if (rootItems == null || rootItems.size() < 1)
			return null;
		return castList(rootItems, Object.class);
	}
	
	
}
