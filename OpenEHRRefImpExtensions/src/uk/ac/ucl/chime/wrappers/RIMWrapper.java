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
import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.NodeInfo;

import org.openehr.build.RMObjectBuilder;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datatypes.text.CodePhrase;

public abstract class RIMWrapper implements IWrapperNavigator {	
	
	/*
	 * The utility class instance for building RM objects. it is made private
	 * so that descending classes can't use it directly. getBuilder method allows access to this field
	 * so that it is always initialized
	 */
	private RMObjectBuilder builder;
	
	/*
	 * required for tooling
	 */
	protected IWrapperNavigator parent;
	
	/*
	 * the collection of Swing components that represent the widget for 
	 * the node. 
	 */
//	protected ArrayList<Component> guiComponentss = new ArrayList<Component>();
	
	/*
	 * if there is only a single component like a textbox for a dvtextValue
	 * then 
	 */
//	protected Component guiComponent;
	//protected RIMWrapper parent;
	/*
	 * method for getting a description of the item, it can be type info, 
	 * a short snippet from the relevant standard section etc
	 * mostly for tooling purposes
	 */
	public abstract String getInfo();
	
	/*
	 * to get a name of the node, used by tooling at the moment
	 */
	public abstract String getName();
	
//	/*
//	 * method for java Swing UI artefact generation
//	 * this is used when GUI and wrapper are in the same VM
//	 */
//	public abstract Component getGUIComponents();
	
	/*
	 * method for getting a string representation of the archetype
	 * it is context sensitive, if the archetype node contains a RM object link
	 * the value of the rm object is provided, otherwise, generic info about 
	 * the node is given by this method. 
	 */
	public abstract String getGUITestString() throws Exception;
	
	/*method for getting the linked RM object(s) from the archetype node
	 * 
	 */
//	public abstract Object getRIMValue() throws Exception;

	/*
	 * TODO: this should be deleted, the values will have to come from persistence and at some point they will 
	 * be converted into relevant RM objects. This will likely happen per wrapper, so taking a RM instance directly is 
	 * unlikely 
	 */
//	public abstract void setRIMValue(Cluster c) throws Exception;
	
	/*
	 * method that takes an array of nodeinfos, 
	 * which can be used by all wrappers to build their own RM instances
	 */
//	public abstract Object getRIMValue(NodeInfo[] infoArr);
	
	/*
	 * utility function for creating an instance of the rmobjectbuilder instance
	 * rmobjectbuilder is used to create rm objects
	 * this function is the only way to get a builder for children of RIMWrapper, and this 
	 * has a reason, since the children can use only this method to get a builder, 
	 * initialization of the builder is guaranteed.
	 */
	protected RMObjectBuilder getBuilder(){
		if(builder == null){
			builder = BuilderFactory.getNewBuilder();
		}
		return builder;
	}
	
	/*
	 * creates a test rmobject instance. can be used to get
	 * a test RM hierarchy using the archtype
	 */
	public abstract Object getTestInstance() throws Exception;
	
	/*
	 * the rm type name of the node
	 */
	public abstract String getRMTypeName() throws Exception;
	
	/*
	 * get children,  using this class as the returned type
	 * of children. used by tooling  
	 */
	public abstract List<Object> getChildren() throws Exception;
	
	/*
	 * used by tooling  
	 */
	public IWrapperNavigator getParent() throws Exception{
		if (parent != null)
			return parent;
		else
			throw new Exception("Parent is null in RIMWrapper");
	}
	
	/*
	 * method to get a describing entity of the archetype node
	 * for SWING gui generation
	 * TODO: This will be migrated into a system where it does not use 
	 * path encoding.
	 */
//	public abstract ArrayList<ArrayList<PanelFieldInfo>> getComponentRows();
	
	/*
	 * get a pure character representation of the path of this node
	 * this is needed for cases where full node path can't be serialized or 
	 * it is not desired to do so. mostly obsolete, should delete.
	 */
//	public abstract String getEncodedNodePath();
	
	/*
	 * get the JSF widget text of the node. 	
	 */
	public abstract String getJSFWidget();
	
	
	/*
	 * to check if are there any child wrappers of this node
	 * seems obsolete, should go?
	 */
	//public abstract boolean hasChildren(); 
	
	/*
	 * Utility method to cast a list of type A to B. Used by descendants of RimWrapper
	 * to cast their container for children to rimwrapper lists. Java 5 does not 
	 * seem to support direct cast of a typed list from A to B
	 * 
	 */
	protected <T,S> List<T> castList(List<S> sourceList, Class<T> clz){
		ArrayList<T> targetList = new ArrayList<T>();
		for(S s : sourceList){
			targetList.add((T)s);
		}
		return targetList;
	}
	
	protected String encodePath(String pPath){
		if (pPath == null || pPath == "")
			return "ERROR-NULL-OR-EMPTY-PATH";
		return "gui_" + String.valueOf(pPath.hashCode()).replaceAll("-", "_m_");
	}
	
	protected String[] getValueFromNodeInfoss(NodeInfo[] nodeInfos, String encodedPath){
		//encoded path is the identifier for nodeinfo
		//so search the array to find the node and return values array
		ArrayList<String> valuesArrL = new ArrayList<String>();
		for(NodeInfo info : nodeInfos){
			if (info.identifier.indexOf(encodedPath) == 0){
				for(String val : info.values){
					valuesArrL.add(val);
				}
			}				
		}
		if (valuesArrL.size() < 1)
			return null;
		else {
			String[] results = new String[valuesArrL.size()];
			return valuesArrL.toArray(results);
		}
			
	}
	
	/*
	 *default language used by test instance generator methods 
	 */
	protected CodePhrase getDefaultLanguage(){
		return new CodePhrase("ISO_639-1", "en");
	}
	
	/*
	 * default encoding used by test instance generator methods
	 */
	protected CodePhrase getDefaultEncoding(){
		return new CodePhrase("IANA_character-sets","UTF-8");
	}
	
}
