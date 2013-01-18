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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.Cardinality;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import uk.ac.ucl.chime.utilities.ConfigurationManager;
import uk.ac.ucl.chime.utilities.ElementWrapperFactory;
import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ClusterWrapper extends Containable {
	
	static Logger logger = Logger.getLogger(ClusterWrapper.class);	
	
	/*
	 * the openEHR RM Item that is being wrapped by this
	 */
	protected Item item;
	protected List<RIMWrapper> itemWrappers = new ArrayList<RIMWrapper>();
	protected List<PlaceholderItemWrapper> internalRefPlaceholders = new ArrayList<PlaceholderItemWrapper>();
	protected boolean internalReferencesResolved = false;
	protected ArchetypeSlot archetypeSlot; 
	protected boolean isArchetypeSlot;

	public List<RIMWrapper> getItemWrappers() throws Exception{
		//do not return items unless all references are resolved!
		if (internalReferencesResolved)
			return itemWrappers;
		else if(!containerArchetype.resolveInternalReferences(this)){
			throw new Exception("Could not resolve internal reference(s) for cluster" + containerArchetype.getNodeName(this.ccomplexReference.getNodeID()));
		}
		internalReferencesResolved = true;
		return itemWrappers;
	}
		
	//TODO: this implementation can be shorter, eliminate internalplaceholders list and check the type of items in itemwrappers instead.
	public void updateInternalReference(ArchetypeInternalRef reftoupdate, RIMWrapper realItem) throws Exception{
		for(int i = 0; i < internalRefPlaceholders.size(); i++){
			if (internalRefPlaceholders.get(i).getInternalRef() == reftoupdate){
				//find this in itemwrappers and replace with realItem
				for(int j = 0; j < itemWrappers.size(); j++){
					if(itemWrappers.get(j) == internalRefPlaceholders.get(i)){
						itemWrappers.set(j, realItem);
						return;
					}
				}
			}
		}
		throw new Exception("Could not find placeholder for reference to update");
	}
	
	public ClusterWrapper(CComplexObject pclusterObject, ArchetypeWrapper pArchetypeWrapper, boolean pHasContainerTypeParent, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
//	public ClusterWrapper(CComplexObject pclusterObject, ArchetypeWrapper pArchetypeWrapper) throws Exception{
		ccomplexReference = pclusterObject;
		containerArchetype = pArchetypeWrapper;
		hasContainerTypeParent = pHasContainerTypeParent;
		processCluster();
	}
	
	public ClusterWrapper(ArchetypeSlot slot, ArchetypeWrapper pArchetypeWrapper, boolean pHasContainerTypeParent, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
//	public ClusterWrapper(ArchetypeSlot slot, ArchetypeWrapper pArchetypeWrapper) throws Exception{
		archetypeSlot = slot;
		containerArchetype = pArchetypeWrapper;
		hasContainerTypeParent = pHasContainerTypeParent;
		processClusterOfArchetypeSlot();
	}
	
	public String getName(){
		return containerArchetype.getNodeName(ccomplexReference.getNodeID());
	}
		
	protected void processCluster() throws Exception{
		logger.setLevel(Level.OFF);
		String archetypeNodeId = ccomplexReference.getNodeID();
		String nodeName = containerArchetype.getNodeName(archetypeNodeId);
		logger.info("Cluster: " + nodeName);
		
		for (CAttribute atr : ccomplexReference.getAttributes()) {
			logger.info("[" + atr.getRmAttributeName() + "]");
			if (RMClassAttributeNames.valueOf(atr.getRmAttributeName().toUpperCase()) == RMClassAttributeNames.ITEMS){
				for (CObject obj : atr.getChildren()){
					boolean isContainerAttribute = false;
					if (atr instanceof CMultipleAttribute 
							&& ((CMultipleAttribute)atr).getCardinality() != null )
						//this is a container type, so lets let the children know this
						//they should know that their parent is a container type
						isContainerAttribute = true;
						
					if (RMClassName.valueOf(obj.getRmTypeName()) == RMClassName.CLUSTER){
						if (obj instanceof CComplexObject){							
							ClusterWrapper childCw = new ClusterWrapper((CComplexObject) obj, containerArchetype, isContainerAttribute, this);
							itemWrappers.add(childCw);																						
						}
						else if (obj instanceof ArchetypeSlot){
							//TODO: handle cardinality for slots...
							ClusterWrapper childCw = new ClusterWrapper((ArchetypeSlot)obj, containerArchetype, false, this);
							itemWrappers.add(childCw);
						}
					}					
					else if (RMClassName.valueOf(obj.getRmTypeName()) == RMClassName.ELEMENT){
						if (obj instanceof CComplexObject){
							ElementWrapperFactory ewf = new ElementWrapperFactory((CComplexObject)obj, containerArchetype, isContainerAttribute, this);
							ElementWrapper ew = ewf.getElementWrapperInstance(); 							
//							ew.processElement();
							if (!ew.isEmpty())
								itemWrappers.add(ew);						
						}					
						else if (obj instanceof ArchetypeInternalRef){
							//TODO: you may not be able to resolve this path now, it may
							//be referring to a node further in the tree, which has not been processed yet!
							ArchetypeInternalRef internalRef = (ArchetypeInternalRef) obj;
							logger.info("Target Path of ref: " + internalRef.getTargetPath());
							PlaceholderItemWrapper plcholder = new PlaceholderItemWrapper(internalRef);
							//add a placeholder instead of actual item and let parent archetype know.
							itemWrappers.add(plcholder);
							//keep o link to plcholder to allow archetype to update it later
							internalRefPlaceholders.add(plcholder);
							containerArchetype.addInternalRef(this, internalRef);
						}
					}						
				}
			}
		}		
	}
	
//	public Cluster buildRIMCluster() throws Exception{
//		//archetype slots...
//		if (ccomplexReference == null)
//			return null;
//		List<Item> elements = new ArrayList<Item>();
//		for(RIMWrapper wp : itemWrappers){
//			if(wp!= null && wp instanceof ElementWrapper){
//				Element e = (Element) ((ElementWrapper)wp).getRIMValue();
//				if (e != null)
//					elements.add(e);
//			}
//			else if (wp != null & wp instanceof ClusterWrapper){
//				Cluster c = (Cluster) ((ClusterWrapper)wp).buildRIMCluster();
//				if (c != null)
//					elements.add(c);
//			}
//		}
//		
//		Map<String, Object> values = new HashMap<String, Object>();
//        String archetypeNodeId = ccomplexReference.getNodeID();
////      DvText name = new DvText(containerClusterArchetype.getNodeName(clusterObject.getNodeID()), lang, charset, ts);
//        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
//        values.put("archetypeNodeId", archetypeNodeId);
//        values.put("name", name);
//        values.put("items", elements);
//        if (elements != null && elements.size() > 0){
//        	RMObject obj = builder.construct("Cluster", values);
//            return (Cluster) obj;
//        }
//        else
//        	return null;        
//	}
	
	protected void processClusterOfArchetypeSlot() throws Exception {
		//TODO how do we process an archetype slot?
		//use archetypeslot member here...
		logger.info("Archetype Slot found, omitting");
		isArchetypeSlot = true;
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
		if (ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RmTypeName in ClusterWrapper");
	}

	@Override
	public String getGUITestString() throws Exception {						
		StringBuffer clusterWrpSBuf = new StringBuffer();	
		if (archetypeSlot != null)
			clusterWrpSBuf.append("(Archetype Slot)");			
		if(ccomplexReference == null)
			throw new Exception("Could not get cluster name in cluster wrapper");									
		else 
		{
			clusterWrpSBuf.append(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
			for(RIMWrapper wrapper : getItemWrappers()){
				clusterWrpSBuf.append("\n" + wrapper.getGUITestString() + "\n");
			}
		}
		return clusterWrpSBuf.toString();
	}

	@Override
	public Object getTestInstance() throws Exception {
		//archetype slots...
		if (ccomplexReference == null)
			return null;
		List<Item> elements = new ArrayList<Item>();
		for(RIMWrapper wp : itemWrappers){
			if(wp!= null && wp instanceof ElementWrapper){
				Element e = (Element) ((ElementWrapper)wp).getTestInstance();
				if (e != null)
					elements.add(e);
			}
			else if (wp != null & wp instanceof ClusterWrapper){
				Cluster c = (Cluster) ((ClusterWrapper)wp).getTestInstance();
				if (c != null)
					elements.add(c);
			}
		}
		
		Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = ccomplexReference.getNodeID();
//      DvText name = new DvText(containerClusterArchetype.getNodeName(clusterObject.getNodeID()), lang, charset, ts);
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("items", elements);
        if (elements != null && elements.size() > 0){
        	RMObject obj = getBuilder().construct("Cluster", values);
            return obj;
        }
        else
        	return null;
	}

	@Override
	public String getJSFWidget() {
		//a cluster has  a table row with colspan=2, and all items added after 
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLText.TR_Begin);
		sb.append(HTMLText.getTDBeginWithColspan(3));
		sb.append(HTMLText.Div_Begin);
		sb.append(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
		sb.append(HTMLText.Div_End);
		sb.append(HTMLText.TD_End);
		sb.append(HTMLText.TR_End);
		sb.append(HTMLText.NewLine);
		//now add items as rows in the table
		for (RIMWrapper wrapper : itemWrappers){
			sb.append(wrapper.getJSFWidget());
		}
		return sb.toString();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		//the following call instead of directly returning itemwrappers 
		//should ensure that placeholders are resolved before returning the itemwrappers
		return castList(getItemWrappers(), Object.class);
	}


}
