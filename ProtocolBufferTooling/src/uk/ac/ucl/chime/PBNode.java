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
package uk.ac.ucl.chime;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.Descriptors.Descriptor;

/*
 * This is the abstract class for protocol buffer nodes. All nodes representing
 * messages, fields etc in a protocol buffer description are represented with descendents of this
 * class. 
 * It provides methods for representing parent/child relations, and it also implements IPBNode, which 
 * allows visitors of IPBNodeVisitor to visit hierarchies of this class
 */
public abstract class PBNode implements IPBNode  {
	
	protected PBNode _parent;	
	protected List<PBNode> _children = new ArrayList<PBNode>();
	protected String _packageName;
	protected String _typeNameSeparator = "_";
	
	public PBNode() {				
	}
	
	/*
	 * Get the package name used in proto file. This is used in code generation
	 * @return The package name
	 */
	public String getPackageName(){
		return _packageName;
	}
	
	/*
	 * Sets the package name, this should come from the proto file. 
	 */
	public void setPackageName(String pPackageName){
		_packageName = pPackageName;
	}
	
	/*
	 * Add a child node, and set its parent to this instance
	 * @param pChild the PBNode childe to add
	 */
	public void addChild(PBNode pChild){
		_children.add(pChild);
		pChild.setParent(this);
	}
	
	/*
	 * Gets the parent node
	 * @return The PBNode that is the parent of this node
	 */
	public PBNode getParent(){
		return _parent;
	}
	
	/*
	 * Sets the parent node of this instance
	 * @param pParent The PBNode parent of this child
	 */
	public void setParent(PBNode pParent){
		_parent = pParent;
	}
	
	/*
	 * Accepts a visitor, and calls the corresponding visit method
	 * @see uk.ac.ucl.chime.IPBNode#accept(uk.ac.ucl.chime.IPBNodeVisitor)
	 */
	public abstract void accept(IPBNodeVisitor pNodeVisitor) throws Exception;
	
	/*
	 * @return A list of children
	 */
	public List<PBNode> getChildren(){
		return _children;
	}
	
}
