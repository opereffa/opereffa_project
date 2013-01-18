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
package uk.ac.ucl.chime.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utilities.ConfigurationManager;
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ConnectorBean {
	
	protected String adlFileName;
	protected String persistenceSessionId;
	protected ArchetypeWrapper archetypeWrapper;
	protected String defaultRepositoryDir = ConfigurationManager.getRepositoryPath();
	//this hashtable contains guids keyed on node paths, it is used to generate random guids for groups of components
	protected Hashtable<String, String> groupGUIDs = new Hashtable<String, String>();
	
	public ConnectorBean() throws Exception{
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String loadForEdit = request.getParameter("loadForEdit");
		
		String sessionId = request.getParameter("sessionId");
		String contextId = request.getParameter("contextId");
		
		
		if (sessionId != null && sessionId != "" && contextId != null && contextId != ""){
			setPersistenceSessionId(sessionId);
			
			if (loadForEdit == null || !loadForEdit.equals("true"))
				return;
			// we can load the archetype via this session id alone, but let's also use contextId
			ArrayList<ArchetypeData> archetypeDatas = new ArrayList<ArchetypeData>(getArchetypeDataList(sessionId, contextId));
			String archetypeFileName = null;
			if (archetypeDatas.size() < 1){
				FacesContext.getCurrentInstance().getExternalContext().redirect("ArchetypeNotFound.jsf");
				FacesContext.getCurrentInstance().responseComplete();
			}else
			{
				archetypeFileName = archetypeDatas.get(0).getArchetypeName();
				archetypeWrapper = getArchetypeWrapper(archetypeFileName);
				for(ArchetypeData data : archetypeDatas){
					System.out.println("Loaded archetypedata===============================> " + String.valueOf(data.getId()) + " : " + data.getValueString());
				}
				archetypeWrapper.loadFromPOJO(archetypeDatas);
				archetypeWrapper.setMode(ArchetypeWrapper.MODE_UPDATE);
			}
		}
		
	}
	
	public String getGroupGUID(String pNodePath){
		if (groupGUIDs.containsKey(pNodePath))
			return groupGUIDs.get(pNodePath);
		String guid = UUID.randomUUID().toString();
		groupGUIDs.put(pNodePath, guid);
		return guid;
	}
	
	public String getAdlFileName(){
		return adlFileName;
	}
	
	protected List<ArchetypeData> getArchetypeDataList(String pSessionId, String pContextId){
		ArchetypeDataDAO dao = new ArchetypeDataDAO();
		Query q = dao.getSession().createQuery("from ArchetypeData where sessionId = ? and contextId = ? and deleted = ?");
		q.setString(0, pSessionId);
		q.setString(1, pContextId);
		q.setBoolean(2, false);
		return q.list();
	}
	
	public void setAdlFileName(String pAdlFileName){
		adlFileName = pAdlFileName;
	}
	
	public ArchetypeWrapper getArchetypeWrapper(String pAdlFileName) throws Exception{
		if (pAdlFileName == null)
			throw new Exception("Null archetype file name");
		//this method should not be called with a new file name, while this bean is in initizalized state
		if ((adlFileName != null && !adlFileName.equals("")) && !adlFileName.equals(pAdlFileName))
			throw new Exception("Trying to load a different archetype into an initialized ConnectorBean intance");
		//return initialized wrapper
		if (archetypeWrapper !=  null){
			System.out.println("returning cached wrapper");
			return archetypeWrapper;
		}
			
		if (adlFileName == null || adlFileName.equals(""))
			adlFileName = pAdlFileName;
		System.out.println("loading wrapper from disk");
		adlFileName = pAdlFileName;
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		archetypeWrapper = factory.loadFromFile(defaultRepositoryDir + adlFileName);
		archetypeWrapper.setPersistenceSessionId(getPersistenceSessionId());
		return archetypeWrapper;			
	}
	
	public String getMode(){
		//if we do not have an archetypewrapper yet, it means we'll be creating one
		//so let's return new as default mode
		if(archetypeWrapper == null)
			return archetypeWrapper.MODE_NEW;
		return archetypeWrapper.getMode();
	}
	
	public void setMode(String pMode){
		archetypeWrapper.setMode(pMode);
	}
	
	public void validateNumeric(FacesContext context,UIComponent pComponentToValidate, Object pValue) throws Exception {
		boolean canCastToDouble = false;
		try {
			double d = Double.parseDouble(pValue.toString());
			canCastToDouble = true;
		} catch (Exception e) {
			//nothing to do
		}	
		if (!canCastToDouble){
			((UIInput)pComponentToValidate).setValid(false);			
			FacesMessage message = new FacesMessage("Invalid value for numeric field");
			String clientId = pComponentToValidate.getClientId(context);
			context.addMessage(clientId, message);
			context.renderResponse();
		}		
	}
	
	public String getModeLabel(){
		//TODO: weird situation here. on a failed update, the mode stays update but the value
		//of the submit button changes!! the code below does the same thing with the "loadtoedit" 
		//parameter check in the constructor, but there is obviously a problem here
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String mode = request.getParameter("mainform:mode");
		if (mode != null && mode != "" && mode.equals(archetypeWrapper.MODE_UPDATE))
				return "Update";
		
		if (archetypeWrapper == null)
			return "Save";
		if (archetypeWrapper.getMode().equals(archetypeWrapper.MODE_NEW))
			return "Save";
		if (archetypeWrapper.getMode().equals(archetypeWrapper.MODE_UPDATE))
			return "Update";
		return "Unknown Mode!";
	}
	
	
	public void setModeLabel(String pModelLabel){
		if (archetypeWrapper == null || pModelLabel == null || pModelLabel == "")
			return;
		if (pModelLabel.equals("Save")){
			archetypeWrapper.setMode(archetypeWrapper.MODE_NEW);
			return;
		} 
		else if (pModelLabel.equals("Update")){
			archetypeWrapper.setMode(archetypeWrapper.MODE_UPDATE);
			return;
		}					
	}
	
	public String getPersistenceSessionId(){
		if (persistenceSessionId == null || persistenceSessionId == "")
			persistenceSessionId = UUID.randomUUID().toString();
		return persistenceSessionId;
	}
	
	public void setPersistenceSessionId(String pSessionId){
		persistenceSessionId = pSessionId;
	}
	
	public String getArchetypeCreationDate(){
		if (archetypeWrapper == null){
			long l = Calendar.getInstance().getTimeInMillis();
			return String.valueOf(l);
		}else if (archetypeWrapper != null && archetypeWrapper.getArchetypeCreationDate() == null){
			//a validation error leads to this point, where created at is not set yet, but needed.
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String createdAt = request.getParameter("createdAt");
			//Timestamp createdAtTS = new Timestamp(Long.parseLong(String.valueOf(createdAt)));
			return createdAt;
		}
		//we have the archetypewrapper and it has its date
		return String.valueOf(archetypeWrapper.getArchetypeCreationDate().getTime());
	}
	
	public void setArchetypeCreationDate(){
		//dummy
	}

}
