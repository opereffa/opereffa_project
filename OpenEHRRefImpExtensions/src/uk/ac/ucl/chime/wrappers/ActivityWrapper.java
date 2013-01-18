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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.AssertionVariable;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.assertion.OperatorKind;
import org.openehr.am.archetype.assertion.ExpressionLeaf.ReferenceType;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import uk.ac.ucl.chime.utilities.ArchetypeAnalyzer;
import uk.ac.ucl.chime.utilities.ArchetypeRepositoryManager;
import uk.ac.ucl.chime.utilities.ItemStructureWrapperFactory;
import uk.ac.ucl.chime.utilities.SlotForItemStructureWrapperFactory;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.slotwrappers.SlotForItemTreeWrapper;

public class ActivityWrapper extends EHRStructureWrapper {

	protected ItemStructureWrapper activityMembers;		
	
	public ActivityWrapper(CComplexObject pActivityObject, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		ccomplexReference = pActivityObject;
		containerArchetype = pContainerArchetype;
		processActivityWrapper();
	}
	
	protected void processActivityWrapper() throws Exception{
		processItemStructure();
	}
		
	/*
	 * @param itemStructure the CComplexObject instance that contains a descent of IM type 
	 * ITEM_STRUCTURE. This method checks the IM type, and based on the result, calls the 
	 * appropriate method to process it
	 */
	protected void processItemStructure() throws Exception {
		//TODO: PROCESS action_attribute_id attribute of activity object
		CSingleAttribute descriptionAttr = (CSingleAttribute) ccomplexReference.getAttribute("description");
		//the description can be a Slot
		if (descriptionAttr.getChildren().get(0) instanceof ArchetypeSlot){
			ArchetypeSlot slot = (ArchetypeSlot) descriptionAttr.getChildren().get(0);			
			Set<Assertion> includes = slot.getIncludes();
			for(Assertion a : includes){
				ExpressionItem eItem = a.getExpression();
				if (eItem instanceof ExpressionBinaryOperator){
					if (((ExpressionBinaryOperator) eItem).getLeftOperand() instanceof ExpressionLeaf &&
						((ExpressionLeaf)((ExpressionBinaryOperator) eItem).getLeftOperand()).getReferenceType().equals(ReferenceType.CONSTANT) &&
						((ExpressionBinaryOperator) eItem).getRightOperand() instanceof ExpressionLeaf &&
						((ExpressionLeaf)((ExpressionBinaryOperator) eItem).getRightOperand()).getReferenceType().equals(ReferenceType.CONSTANT) &&
						((ExpressionBinaryOperator) eItem).getOperator().compareTo(OperatorKind.OP_MATCHES) == 0 && 
						((ExpressionLeaf)((ExpressionBinaryOperator) eItem).getRightOperand()).getItem() instanceof CString){
						String pattern = ((CString)((ExpressionLeaf)((ExpressionBinaryOperator) eItem).getRightOperand()).getItem()).getPattern();
						Pattern p = Pattern.compile(pattern);
						String[] archetypeNames = ArchetypeRepositoryManager.getAllArchtypeNames();
						ArrayList<String> matchedFileNames = new ArrayList<String>();
						for(String aName : archetypeNames){
							if (p.matcher(aName.substring(0, aName.lastIndexOf(".adl"))).matches())
								matchedFileNames.add(aName);
						}
						if (matchedFileNames.size() > 0){
							for (String fName : matchedFileNames){
								ArchetypeAnalyzer analyzer = new ArchetypeAnalyzer(fName);
								analyzer.loadArchetype();								
							}
						}
					}
					
				}
								
			}				
			SlotForItemStructureWrapperFactory factory = new SlotForItemStructureWrapperFactory();
			ItemStructureWrapper slotWrapper = factory.getItemStructureWrapperSlot(slot, containerArchetype);
			activityMembers = slotWrapper;
		}
		else{
			//descriptionAttr contains a child of ITEM_STRUCTURE, find out the exact type
			CComplexObject description = (CComplexObject)(descriptionAttr.getChildren().get(0));
			ItemStructureWrapperFactory wrapperFactory = new ItemStructureWrapperFactory();
			activityMembers = wrapperFactory.getItemStructureWrapper(description, containerArchetype, this);
		}		
	}
		
	@Override
	public String getGUITestString() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(activityMembers.getGUITestString());
		return sb.toString();
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() {
//		return null;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		if(ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in ActivityWrapper");
	}

	@Override
	public Object getTestInstance() throws Exception {
		TerminologyService ts =  SimpleTerminologyService.getInstance();
		DvParsable timing = new DvParsable(getDefaultEncoding(),getDefaultLanguage(),
        		 1, "timing value", "formalism", ts);
		Activity testActivity = new Activity(ccomplexReference.getNodeID(), 
				text(containerArchetype.getNodeName(ccomplexReference.getNodeID())),
				(ItemStructure) activityMembers.getTestInstance(), 
				timing,containerArchetype.getArchetype().getConcept());
		return testActivity;
	}
	
	protected DvText text(String value) {
		return new DvText(value);
	}

	@Override
	public String getJSFWidget() {
		return activityMembers.getJSFWidget();
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() {
		List<RIMWrapper> wrapperList = new ArrayList<RIMWrapper>();
		wrapperList.add(activityMembers);
		return castList(wrapperList , Object.class);
	}
}
