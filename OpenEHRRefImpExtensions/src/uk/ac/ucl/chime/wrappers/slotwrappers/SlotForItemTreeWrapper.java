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
package uk.ac.ucl.chime.wrappers.slotwrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.primitive.CString;

import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.ItemStructureWrapper;
import uk.ac.ucl.chime.wrappers.ItemTreeWrapper;
import uk.ac.ucl.chime.wrappers.RIMWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class SlotForItemTreeWrapper extends ItemStructureWrapper implements ISlotWrapper, IWrapperNavigator {
	
	protected ArchetypeSlot archetypeSlot; 
	protected ArrayList<String> includedArchetypes;
	protected boolean allIncluded = false;
	protected ItemTreeWrapper slotContent;
	protected ArrayList<String> excludedArchetypes;
	protected boolean allExcluded = false;
	
	public SlotForItemTreeWrapper(ArchetypeSlot pSlot, ArchetypeWrapper pContainerArchetypeWrapper){
		archetypeSlot = pSlot;
		containerArchetype = pContainerArchetypeWrapper;
		//add this instance to container archetype for lazy loading later
		containerArchetype.addSlotWrapper(archetypeSlot.path(), this);
		processSlot();
	}
	
	//Begin: ISlotWrapper implementation
	public boolean isAllIncluded(){
		return allIncluded;
	}
	
	public boolean isAllExcluded(){
		return allExcluded;
	}
	
	public boolean isSlotFilled(){
		return (slotContent != null);
	}
	
	public ArrayList<String> getIncludedArchetypeFileNames(){
		return includedArchetypes;
	}
	
	public ArrayList<String> getExcludedArchetypeFileNames(){
		return excludedArchetypes;
	}
	
	public void fillWrapper(String pArchetypeName) throws Exception{
		boolean archetypeIncluded = false;
		if (!allIncluded && includedArchetypes != null){
			for(String included : includedArchetypes){
				if (pArchetypeName.equals(included)){
					archetypeIncluded = true;
					break;
				}
			}
		}
		
		boolean archetypeExcluded = false;
		if (!allExcluded && excludedArchetypes != null){
			for(String excluded : excludedArchetypes){
				if (pArchetypeName.equals(excluded)){
					archetypeExcluded = true;
					break;
				}			
			}
		}		
		if (archetypeIncluded)//assume that this dominates all others
			slotContent = (ItemTreeWrapper) (containerArchetype.getWrapperFromRepository(pArchetypeName).getRootWrapper());
		
	}
	//Begin: ISlotWrapper implementation

	protected void processSlot() {		
		if (allowsAnyArchetype(archetypeSlot.getIncludes()))
			allIncluded = true;
		else			
			includedArchetypes = getArchetypeFileNames(archetypeSlot.getIncludes());
		
		if (allowsAnyArchetype((archetypeSlot.getExcludes())))
			allExcluded = true;
		else
			excludedArchetypes = getArchetypeFileNames(archetypeSlot.getExcludes());		
	}
	
	protected boolean allowsAnyArchetype(Set<Assertion> pAssertions){
		for(Assertion assertion : pAssertions){
			ExpressionItem eItem = assertion.getExpression();
			if (eItem instanceof ExpressionBinaryOperator){
				ExpressionLeaf rightOperand = (ExpressionLeaf) ((ExpressionBinaryOperator)eItem).getRightOperand();
				CString item = (CString) rightOperand.getItem();
				String[] allowedArchetypes = item.getPattern().split("\\|");				
				for(String archetypeName : allowedArchetypes ){
					if (archetypeName.equals(".*"))
						return true;
					}
				}				
			}
		return false;
	}
	
	protected ArrayList<String> getArchetypeFileNames(Set<Assertion> pAssertions){
		//just in case multiple assertsions arrive in the set, the arraylist
		//should stay out of loop (don't know how this can happen though??)
		// a single include or exclude statment creates a single assertion
		//so why it is returning a set??
		//TODO: learn this
		ArrayList<String> allowedArchetypeFileNames = new ArrayList<String>();
		for(Assertion assertion : pAssertions){
			ExpressionItem eItem = assertion.getExpression();
			if (eItem instanceof ExpressionBinaryOperator){
				ExpressionLeaf rightOperand = (ExpressionLeaf) ((ExpressionBinaryOperator)eItem).getRightOperand();
				CString item = (CString) rightOperand.getItem();					
				String[] allowedArchetypes = item.getPattern().split("\\|");				
				for(String archetypeName : allowedArchetypes ){
					archetypeName = archetypeName.replaceAll("\\\\.", ".") + ".adl";
					allowedArchetypeFileNames.add(archetypeName);
				}
			}
		}
		return allowedArchetypeFileNames;
	}

	@Override
	protected void processItemStructureWrapper() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getGUITestString() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJSFWidget() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() throws Exception {
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() {
		// TODO implement it based on the slot handling implementation
		//for now, simply throw an exception
		throw new NotImplementedException("Navigation in adl nodes has not been implemented yet for SlotWrapper of an ItemTreeWrapper");
	}
	
	@Override
	public IWrapperNavigator getParent(){
		throw new NotImplementedException("Navigation in adl nodes has not been implemented yet for SlotWrapper of an ItemTreeWrapper");
	}

}
