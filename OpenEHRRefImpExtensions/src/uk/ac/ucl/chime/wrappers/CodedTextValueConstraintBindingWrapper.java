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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.hibernate.Query;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.ConstraintRef;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.opereffa.termdata.TerminologyItem;
import uk.ac.ucl.chime.opereffa.termservice.TerminologyProvider;
import uk.ac.ucl.chime.utilities.CodedTextInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

/*
 * This class is used to handle DV_CODED_TEXT instances which are bound to an external
 * terminology server.
 */
public class CodedTextValueConstraintBindingWrapper extends
		CodedTextValueWrapper {

	public CodedTextValueConstraintBindingWrapper(CObject pvalue,
			ArchetypeWrapper pcontainerArchetype, IWrapperNavigator parent) {
		super(pvalue, pcontainerArchetype, parent);
		
	}
	
	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvCodedText";
        Map<String, Object> values = new HashMap<String, Object>();
        CComplexObject ccompx = (CComplexObject)wrappedObject;
		CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
        ConstraintRef constRef =  (ConstraintRef) atr.getChildren().get(0);
        //normally the queryIdentifier should be sent to a service which should resolve it to a 
        //subset in the terminology server, but for now, we won't use it, we'll simply get all codes
        //it is created here just to be a reminder for future way of doing this.
        String queryIdentifier = constRef.getReference();
        List<TerminologyItem> tItems = TerminologyProvider.getAllTerms();         		
		String actualCode = null;
		String rubric = null;
		if(tItems != null && tItems.size() > 0){
			int randomCodeIndex = new Random().nextInt(tItems.size() - 1);
			actualCode = tItems.get(randomCodeIndex).getCode();
			if (actualCode != null)
				rubric = tItems.get(randomCodeIndex).getSnomedPreferredName();
			boolean generatedBefore = false;
			for (String existingCode : generatedCodes){
				if (actualCode != null && actualCode.equals(existingCode)){
					generatedBefore = true;
					break;
				}
			}
			if (generatedBefore)
				return null;
		}
		else //no code, no rm value to return in exchnage
			throw new Exception("Terminology server did not return any terminology items in Coded text value wrapper");
		if (actualCode == null || rubric == null) //term. server returned items, but we could not get a code and/or rubric from these items
			return null;
        CodePhrase definingCode = new CodePhrase(TerminologyProvider.SNOMED_CT, actualCode);
        generatedCodes.add(actualCode);
        values.put("value", rubric);
        values.put("definingCode", definingCode);
        RMObject obj = getBuilder().construct(type, values);
        return obj;
	}

	@Override
	public Object getValue() {
		//TODO: should be context dependent-> return default val or actual etc..
		CodedTextInfo info = new CodedTextInfo();
		CComplexObject ccompx = (CComplexObject)wrappedObject;
		CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
		if (atr != null){
			//normally the url in constref should be used to get a subset
			//at the moment, we are fetching all the codes
			ConstraintRef constRef =  (ConstraintRef) atr.getChildren().get(0);			
			List<TerminologyItem> termItems =  TerminologyProvider.getAllTerms();
			if (termItems != null){
				String currentCode = null;
				//single code in a code list
				if (dataValue != null){
					currentCode = ((DvCodedText)dataValue).getDefiningCode().getCodeString();
					fillInfoCodesUsingTerminology(info, termItems);				
					info.setCurrentSelectionCode(currentCode);
					//also set rubric
					info.setCurrentSelectionRubric(((DvCodedText)dataValue).getValue());
				}//where multiple codes can be added
				else if (dataValue == null && dataValueList.size() > 0){
					for (DataValue dVal : dataValueList){
						String codeToAdd =  ((DvCodedText)dVal).getDefiningCode().getCodeString();
						info.getCodesOfCurrentSelections().add(codeToAdd);
						info.getNamesOfCurrentSelections().add(((DvCodedText)dVal).getValue());
					}
					//also fill the list of codes/values
					fillInfoCodesUsingTerminology(info, termItems);
				}
				//there is a terminology, but no single or multiple value is set				
			}
			//terminology items are null. No terminology to use			
		}
		return info;		
	}
	
	
	protected void fillInfoCodesUsingTerminology(CodedTextInfo pInfo, List<TerminologyItem> pCodes){
		for(int i = 0; i < pCodes.size(); i++){
			pInfo.values.add(pCodes.get(i).getSnomedPreferredName()); 
			pInfo.codes.add(pCodes.get(i).getCode());			
		}	
	}
	
	protected DataValue createRMInstance(String pValue) throws RMObjectBuildingException{
		String selectedCode = pValue.replaceAll("\\[", "").replaceAll("\\]", "");
		//find rubric
		String rubric = null;
		List<TerminologyItem> termItems = TerminologyProvider.getAllTerms();
		boolean codeExistsInTerminology = false;
		for(TerminologyItem ti : termItems){
			if (ti.getCode().equals(selectedCode)){
				codeExistsInTerminology = true;
				rubric = ti.getSnomedPreferredName();
				break;
			}
		}
		if (rubric == null || !codeExistsInTerminology)//we should have found the rubric for this code, if not, there must be a problem
			return null;
		CodePhrase definingCode = new CodePhrase(TerminologyProvider.SNOMED_CT, selectedCode);
		Map<String, Object> values = new HashMap<String, Object>();
		String type = "DvCodedText";
		values.put("value", rubric);
		values.put("definingCode", definingCode);
		return (DvCodedText) getBuilder().construct(type, values);		
	}
	
	//TODO: Normally, saveValueToDB should be implemented at CodedTextValueWrapper
	//this implementation should move up to parent class, but that class is using value field of 
	//dvcodedtext at the moment, which is WRONG. The correct way is to use defining_code's code field
	//see below
	@Override
	public void saveValueToDB() throws Exception{
		if (dataValue == null && oneOftheMultipleInstances){			
			//now save updates if there are any. this is going to be a set of inserts for our case
			if (dataValueList != null && dataValueList.size() > 0){
				ArchetypeDataDAO insertDao = new ArchetypeDataDAO();
				insertDao.getSession().beginTransaction();
				for (DataValue dv : dataValueList){
					ArchetypeData data = new ArchetypeData();
					data.setContextId(containerArchetype.getContextId());
					data.setArchetypeName(containerArchetype.getAdlFileName());
					data.setArchetypePath(wrappedObject.path());
					data.setSessionId(containerArchetype.getPersistenceSessionId());
					data.setArchetypeCreatedAt(containerArchetype.getArchetypeCreationDate());
					data.setFieldCreatedAt(containerArchetype.getArchetypeCreationDate());
					data.setInstanceIndex(0);
					data.setInstanceIndex(0);
					data.setDeleted(false);
					data.setName("value");
					data.setValueString(((DvCodedText)dv).getDefiningCode().getCodeString());
					insertDao.save(data);
				}				
				insertDao.getSession().getTransaction().commit();
			}						
		}
		else if (dataValue == null && !oneOftheMultipleInstances && archetypeDataInstance != null){
			//this means there is a db row corresponding to this node, but its value is now NULL
			//this is interpreted as deleting that db row
			ArchetypeDataDAO dao = new ArchetypeDataDAO();	
			dao.getSession().getTransaction().begin();
			ArchetypeData d = new ArchetypeData();
			Query q = dao.getSession().createQuery("UPDATE ArchetypeData SET deleted = ? where archetypePath = ? and contextId = ? and sessionId = ?");
			q.setBoolean(0, true);
			q.setString(1, wrappedObject.path());
			q.setString(2, containerArchetype.getContextId());
			q.setString(3, containerArchetype.getPersistenceSessionId());
			q.executeUpdate();
			dao.getSession().getTransaction().commit();
		}
		else if (dataValue != null){
			ArchetypeData data = getContextBasedArcetypeData();					
			data.setName("value");
			data.setValueString(((DvCodedText)dataValue).getDefiningCode().getCodeString());		
			archetypeDataInstance = saveWithDelayedPersistenceCheck(data);
		}
	}
	
	@Override
	public String getJSFWidget() {
		//this shared id is going to be used by client side javascript to manage code selection widget related functionality
		String sharedControlId = "#{archetypeBinder.randomGroupId.inNode['"+ wrappedObject.path() + "']}";
		String widgetTextSingleSelection = "<div id=\"termRubric" + sharedControlId + "\" jsf2dojo=\"true\"  style=\"display:inline;\"><h:inputText value=\"#{archetypeBinder.dvcodedtext.selectedTerminologyItemName.inNode['"+ wrappedObject.path() + "']}\"/>\r\n" + 
				"					</div>\r\n" + 
				"					<div id=\"termCode" + sharedControlId + "\" jsf2dojo=\"true\"  style=\"display:inline;\"><h:inputHidden    value=\"#{archetypeBinder.dvcodedtext.selectedTerminologyItemCode.inNode['"+ wrappedObject.path() + "']}\"/>\r\n" + 
				"					</div>								\r\n" + 
				"				</div>				\r\n" + 
				"				<div id=\"dropDownContainerMarker" + sharedControlId + "\" style=\"display:none;\">" + sharedControlId + "</div><div id=\"dropDownContainer" + sharedControlId + "\" style=\"display:inline;\">\r\n" + 
				"					<div dojoType=\"dijit.form.DropDownButton\" id=\"dropDown" + sharedControlId + "\" >				 \r\n" + 
				"						<span>...</span>\r\n" + 
				"						<div dojoType=\"dijit.TooltipDialog\" id=\"dlgChooseTermItem" + sharedControlId + "\" onFocus=\"configureTooltipHandlers('" + sharedControlId + "');\" onBlur=\"performTooltipDialogShutdown('" + sharedControlId + "');\" style=\"width:550px;height:400px;\" title=\"Choose a terminology item\" >			\r\n" + 
				"							<table border=\"0\" width=\"100%\">\r\n" + 
				"								<tr> \r\n" + 
				"									<td>\r\n" + 
				"										<input type=\"text\" id=\"txtkeyword" + sharedControlId + "\" name=\"txtkeyword" + sharedControlId + "\" value=\"\"  style=\"margin-bottom:10px;\" dojoType=\"dijit.form.TextBox\" trim=\"true\" />						\r\n" + 
				"									</td>\r\n" + 
				"								</tr>\r\n" + 
				"								<tr> \r\n" + 
				"									<td>\r\n" + 
				"										<div style=\"width:100%;height:300px;\">\r\n" + 
				"											<div jsid=\"grid" + sharedControlId + "\" id=\"grid" + sharedControlId + "\" dojoType=\"dojox.Grid\" structure=\"layout\" class=\"terminologyGrid\"></div>\r\n" + 
				"										</div>\r\n" + 
				"									</td>\r\n" + 
				"								</tr>\r\n" + 
				"								<tr> \r\n" + 
				"									<td align=\"center\">\r\n" + 
				"									<button dojoType=\"dijit.form.Button\" id=\"selectTermCode" + sharedControlId + "\" onClick=\"performTooltipDialogShutdown('" + sharedControlId + "');\" name=\"selectTermCode" + sharedControlId + "\" type=\"submit\">OK</button>\r\n" + 
				"									</td>\r\n" + 
				"								</tr>\r\n" + 
				"							</table>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<div dojoType=\"dijit.form.Button\" onClick=\"clearCodeandRubric('" + sharedControlId + "');\">\r\n" + 
				"					   <img src=\"images/delete.gif\" width=\"18\" height=\"18\"/>				   \r\n" + 
				"					</div>	";
		return widgetTextSingleSelection;
	}
	
	
	
	//TODO: Normally, updatePersistedValue should be implemented at CodedTextValueWrapper
	//this implementation should move up to parent class, but that class is using value field of 
	//dvcodedtext at the moment, which is WRONG. The correct way is to use defining_code's code field
	//see below
	@Override
	public void updatePersistedValue() throws Exception {
		//there are values to add and remove, 
		if (oneOftheMultipleInstances && containerArchetype.getMode().equals(ArchetypeWrapper.MODE_UPDATE)){
			//remove all existing ones, and add new ones from db, if no new ones, it means simply removing old ones.
			ArchetypeDataDAO dao = new ArchetypeDataDAO();	
			dao.getSession().getTransaction().begin();
			ArchetypeData d = new ArchetypeData();
			Query q = dao.getSession().createQuery("UPDATE ArchetypeData SET deleted = ? where archetypePath = ? and contextId = ? and sessionId = ?");
			q.setBoolean(0, true);
			q.setString(1, wrappedObject.path());
			q.setString(2, containerArchetype.getContextId());
			q.setString(3, containerArchetype.getPersistenceSessionId());
			q.executeUpdate();
			dao.getSession().getTransaction().commit();
			//now save updates if there are any. this is going to be a set of inserts for our case
			if (dataValueList != null && dataValueList.size() > 0){
				ArchetypeDataDAO insertDao = new ArchetypeDataDAO();
				insertDao.getSession().beginTransaction();
				for (DataValue dv : dataValueList){
					ArchetypeData data = new ArchetypeData();
					data.setContextId(containerArchetype.getContextId());
					data.setArchetypeName(containerArchetype.getAdlFileName());
					data.setArchetypePath(wrappedObject.path());
					data.setSessionId(containerArchetype.getPersistenceSessionId());
					data.setArchetypeCreatedAt(containerArchetype.getArchetypeCreationDate());					
					data.setInstanceIndex(0);
					data.setInstanceIndex(0);
					data.setDeleted(false);
					data.setName("value");
					data.setValueString(((DvCodedText)dv).getDefiningCode().getCodeString());
					data.setFieldCreatedAt(containerArchetype.getArchetypeCreationDate());
					insertDao.save(data);
				}				
				insertDao.getSession().getTransaction().commit();
			}						
		}
		else if (containerArchetype.getMode().equals(ArchetypeWrapper.MODE_UPDATE))
			saveValueToDB();
		
	}
}

