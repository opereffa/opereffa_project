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
import java.util.Random;

import javax.swing.JComboBox;

import org.hibernate.Query;

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.CodedTextInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class CodedTextValueWrapper extends DataValueWrapper {
	
	//used for test value generation
	//when the random value generator is called multiple times, 
	//we don't want to save the same code twice, like same observation saved twice
	protected ArrayList<String> generatedCodes = new ArrayList<String>();
	
	//protected DvCodedText rmDvCodedText; 	
	public CodedTextValueWrapper(CObject pvalue, ArchetypeWrapper pcontainerArchetype, IWrapperNavigator pParent){
		parent = pParent;
		wrappedObject = pvalue;
		containerArchetype = pcontainerArchetype;
	}

//	@Override
//	public Object getRIMValue() {
//		try {
//			String type = "DvCodedText";
//	        Map<String, Object> values = new HashMap<String, Object>();	        
//	        ComboBoxItem item = (ComboBoxItem) ((JComboBox)guiComponent).getSelectedItem();
//	        CodePhrase definingCode = new CodePhrase("local", item.value.toString());
//	        String value = item.name;
//	        values.put("value", value);
//	        values.put("definingCode", definingCode);
//	        RMObject obj = builder.construct(type, values);
//			return obj;
//		} catch (Exception e) {
//			return null;
//		}
//	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRMTypeName() throws Exception {
		return wrappedObject.getRmTypeName();
	}

	@Override
	public String getGUITestString() {
		if (dataValue != null){
			CodedTextInfo cTextInfo = (CodedTextInfo) getValue();
			String label = "";
			for(int i = 0; i < cTextInfo.codes.size(); i++){
				if (cTextInfo.codes.get(i).equals(cTextInfo.getCurrentSelectionCode())){
					label = cTextInfo.values.get(i);
					break;
				}				
			}
			return label;
		}
		else if (dataValue == null && dataValueList.size() > 0){
			CodedTextInfo cTextInfo = (CodedTextInfo) getValue();
			StringBuffer labels = new StringBuffer("Selected Values: \n");
			for (String addedCode : cTextInfo.getCodesOfCurrentSelections()){
				for(int i = 0; i < cTextInfo.codes.size(); i++){
					if (cTextInfo.codes.get(i).equals(addedCode)){
						labels.append(cTextInfo.values.get(i) + "\n");
						break;
					}
					
				}
			}
			return labels.toString();
		}
//		else
//		{
//			StringBuffer ctvSB = new StringBuffer();
//			if (wrappedObject instanceof CComplexObject){
//				CComplexObject ccompx = (CComplexObject)wrappedObject;
//				CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
//				if (atr != null){
//					CCodePhrase phrase =  (CCodePhrase) atr.getChildren().get(0);
//					List<String> codes = phrase.getCodeList(); 
//					if (codes != null){					
//						for(int i = 0; i < codes.size(); i++){
//							ctvSB.append("this is coded text value " + containerArchetype.getNodeName(codes.get(i)) + " : " + codes.get(i));			
//						}
//						return ctvSB.toString();
//					}
//					else//no values set in terms of codes..
//					{
//						return "this is coded text value with no values set in terms of codes";
//					}
//				}		
//			}
//		}		
		return "";
	}

	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvCodedText";
        Map<String, Object> values = new HashMap<String, Object>();
        CComplexObject ccompx = (CComplexObject)wrappedObject;
		CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
        CCodePhrase phrase =  (CCodePhrase) atr.getChildren().get(0);
		List<String> codes = phrase.getCodeList();
		String actualCode = null;
		if(codes != null){//it may be null, when no codes are defined
			int randomCodeIndex = new Random().nextInt(codes.size());
			actualCode = codes.get(randomCodeIndex);	
			boolean generatedBefore = false;
			for (String existingCode : generatedCodes){
				if (actualCode.equals(existingCode)){
					generatedBefore = true;
					break;
				}
			}
			if (generatedBefore)
				return null;
		}
		else //no code, no rm value to return in exchnage
			throw new Exception("could not find code in Coded text value wrapper");
		if (actualCode == null)
			return null;//could not find the actual code for this incoming string
        CodePhrase definingCode = new CodePhrase("local", actualCode);
        generatedCodes.add(actualCode);
        values.put("value", actualCode);
        values.put("definingCode", definingCode);
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
			CCodePhrase phrase =  (CCodePhrase) atr.getChildren().get(0);
			List<String> codes = phrase.getCodeList(); 
			if (codes != null){
				String currentCode = null;
				//single code in a code list
				if (dataValue != null){
					currentCode = ((DvCodedText)dataValue).getValue();
					fillInfoCodes(info, codes);				
					info.setCurrentSelectionCode(currentCode);
				}//where multiple codes can be added
				else if (dataValue == null && dataValueList.size() > 0){
					for (DataValue dVal : dataValueList){
						String codeToAdd =  ((DvCodedText)dVal).getValue();
						info.getCodesOfCurrentSelections().add(codeToAdd);
						info.getNamesOfCurrentSelections().add(containerArchetype.getNodeName(codeToAdd));
					}
					//also fill the list of codes/values
					fillInfoCodes(info, codes);
				}
				//no single or multiple value, select first code as selected
				else if (dataValue == null && dataValueList.size() < 1)
				{
					currentCode = codes.get(0);
					fillInfoCodes(info, codes);
					info.setCurrentSelectionCode(currentCode);
				}				
			}
			//no values set in terms of codes, if we fall here...			
		}
		return info;		
	}
	
	protected void fillInfoCodes(CodedTextInfo pInfo, List<String> pCodes){
		for(int i = 0; i < pCodes.size(); i++){
			pInfo.values.add(containerArchetype.getNodeName(pCodes.get(i))); 
			pInfo.codes.add(pCodes.get(i));			
		}	
	}

	protected DataValue createRMInstance(String pValue) throws RMObjectBuildingException{
		//serialization from web layer sends the pValue in brackets, so we need to clean it
		String selectedCode = pValue.replaceAll("\\[", "").replaceAll("\\]", ""); 
		//find the corresponding actual code for the selected code
		CComplexObject ccompx = (CComplexObject)wrappedObject;
		CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
		CCodePhrase phrase =  (CCodePhrase) atr.getChildren().get(0);
		List<String> codes = phrase.getCodeList();
		String actualCode = null;
		//this loop actually validates that incoming code exists in the list of codes
		//we can bypass this and save pValue directly, but this is safer
		if(codes != null){//it may be null, when no codes are defined				
			for(int i = 0; i < codes.size(); i++){
				if (selectedCode.equals(codes.get(i)))
					actualCode = codes.get(i);
			}
		}
		else //no code, no rm value to set
			return null;
		if (actualCode == null)
			return null;//could not find the actual code for this incoming string
		CodePhrase definingCode = new CodePhrase("local", actualCode);
		Map<String, Object> values = new HashMap<String, Object>();
		String type = "DvCodedText";
		values.put("value", selectedCode);
		values.put("definingCode", definingCode);
		return (DvCodedText) getBuilder().construct(type, values);		
	}
	
	protected String[] getStringArrFromStringForm(String pStringForm){
		pStringForm = pStringForm.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] splittedArr = pStringForm.split(",");
		String[] trimmedArr = new String[splittedArr.length];
		for (int i = 0; i < splittedArr.length; i++){
			trimmedArr[i] = splittedArr[i].trim();
		}
		return trimmedArr;
	}
	
	@Override
	public void setValue(Object pValue) throws Exception {
		
		if (pValue == null || pValue instanceof String && pValue.equals("[]"))
			return;		
		if (pValue.toString().indexOf("[") == 0 && (pValue.toString().lastIndexOf("]") == pValue.toString().length() - 1))
		{
			pValue = getStringArrFromStringForm(pValue.toString());
		}
		//[at0058, at0057] should be turned into String array
		if (pValue instanceof String && pValue != "" )
			dataValue = createRMInstance(pValue.toString());
		else if (pValue instanceof String[] && ((String[])pValue).length > 0){
			for (String s : ((String[])pValue)){
				DataValue dv = createRMInstance(s);
				dataValueList.add(dv);
			}
		}
		//save this value to db
		//saveValueToDB();
	}
	
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
					data.setValueString(((DvCodedText)dv).getValue());
					insertDao.save(data);
				}				
				insertDao.getSession().getTransaction().commit();
			}						
		}		
		else if (dataValue != null){
			ArchetypeData data = getContextBasedArcetypeData();					
			data.setName("value");
			data.setValueString(((DvCodedText)dataValue).getValue());		
			archetypeDataInstance = saveWithDelayedPersistenceCheck(data);
		}
	}
	
	public void removeValue(String pValue) throws Exception{
		pValue = pValue.replaceAll("\\[", "").replaceAll("\\]", "");
		ArchetypeDataDAO dao = new ArchetypeDataDAO();
		Query q = dao.getSession().createQuery("from ArchetypeData where sessionId = ? and contextId = ? "
				+ " and archetype_path = ?"
				+ " and value_string = ?"
				+ " and deleted = ?");
		q.setString(0, containerArchetype.getPersistenceSessionId());
		q.setString(1, containerArchetype.getContextId());
		q.setString(2, wrappedObject.path());
		q.setString(3, pValue);
		q.setBoolean(4, false);
		List<ArchetypeData> rowsToDelete = q.list();
		if (rowsToDelete.size() > 1)
			throw new Exception("Multiple instances found for delete request");
		if (rowsToDelete.size() == 0)
			throw new Exception("No instances found for delete request");
		dao.getSession().getTransaction().begin();
		rowsToDelete.get(0).setDeleted(true);
		dao.attachDirty(rowsToDelete.get(0));
		dao.getSession().getTransaction().commit();
	}
	
	public void addValue(String pValue) throws Exception {
		pValue = pValue.replaceAll("\\[", "").replaceAll("\\]", "");
		DataValue val = createRMInstance(pValue);
		dataValueList.add(val);
		//save to database
		if (containerArchetype.getDelayedDBPersistence()){
			//TODO: save persistence request to container archetype, where it can be saved later by container archetype
			System.out.println("will save datavalue arr list later, storing it in container archetype for now.");
		}
		else{
			System.out.println("saving DataValue arr list in Coded Text Value Wrapper with session Id" + containerArchetype.getPersistenceSessionId());
//				ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
//				dataDAO.getSession().getTransaction().begin();
			ArchetypeData data = new ArchetypeData();
			data.setContextId(containerArchetype.getContextId());
			data.setArchetypeName(containerArchetype.getAdlFileName());
			data.setArchetypePath(wrappedObject.path());
			data.setName("value");
			data.setValueString(pValue);
			data.setSessionId(containerArchetype.getPersistenceSessionId());
			data.setInstanceIndex(0);
//				dataDAO.save(data);
//				dataDAO.getSession().getTransaction().commit();
			saveWithDelayedPersistenceCheck(data);
		}		
	}

	@Override
	public String getJSFWidget() {
		String widgetTextSingleSelection = "<h:selectOneMenu value=\"#{archetypeBinder.dvcodedtext.selectedCode.inNode['" + getPath() + "']}\">\n" + 
							"				<f:selectItems value=\"#{archetypeBinder.dvcodedtext.codesList.inNode['" + getPath() + "']}\" />\n" + 
							"				</h:selectOneMenu>";
		String widgetTextMultipleSelection = "<h:selectManyListbox value=\"#{archetypeBinder.dvcodedtext.selectedCodes.inNode['" + getPath() + "']}\">\n" + 
		"				<f:selectItems value=\"#{archetypeBinder.dvcodedtext.codesList.inNode['" + getPath() + "']}\" />\n" + 
		"				</h:selectManyListbox>";
		if (oneOftheMultipleInstances)
			return widgetTextMultipleSelection;
		else
			return widgetTextSingleSelection;
	}

	@Override
	public void setDataValueWithPersistedValue(ArchetypeData data) throws RMObjectBuildingException {
		dataValue = createRMInstance(data.getValueString());
		//now keep data to use it for edit mode
		archetypeDataInstance = data;
	}

	@Override
	public void addValueFromDB(ArchetypeData data) throws Exception {
		dataValueList.add(createRMInstance(data.getValueString()));
	}
	
	public String getSimpleAdderBegin(){		
		String pathHash = getHashedPath();
		
		String begin = "<div  id=\"dndLimitBox" + pathHash + "\">" + 
		"\n\t" + 
		"<div class=\"dndContainer\" style=\"float: left; margin: 5px;\"  multipleInstanceWidget=\"simpleAdderSource\" id=\"dndSource" + pathHash + "\"  >";
		return begin;
	}
	
	public String getSimpleAdderEnd(){
		String pathHash = String.valueOf(Math.abs(getPath().hashCode()));
		
		String end =  
				"			</div>\n" + 
				"			<div style=\"float: left; margin: 5px;\">\n" + 
				"				<div  class=\"dndContainer\" id=\"dndTarget" + pathHash + "\">			      \n" + 
				"    			</div>\n" + 
				"    		</div>" + "\n</div>\n";
		return end;
	}
	
	public void loadPersistedData(ArchetypeData pData) throws Exception{
		if (oneOftheMultipleInstances){	
			archetypeDataInstances.add(pData);
		}
		else {		
			archetypeDataInstance = pData;
		}
	}
	
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
					data.setValueString(((DvCodedText)dv).getValue());
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
