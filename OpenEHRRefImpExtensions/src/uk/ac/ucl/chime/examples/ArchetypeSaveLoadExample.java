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
package uk.ac.ucl.chime.examples;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;



import org.hibernate.Query;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ArchetypeSaveLoadExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ArchetypeSaveLoadExample testInstance = new ArchetypeSaveLoadExample();
			ArchetypeWrapper wrapperToSaveData = testInstance.loadArchetypeFromFile("C:\\archetypeRepository\\openEHR-EHR-OBSERVATION.SOAP_Clerking8.v8.adl");
			testInstance.setValuesOfClerkingArchetype(wrapperToSaveData);
			testInstance.saveClerkingArchetypeToDB(wrapperToSaveData);
			
			//now create a new wrapper instance and load its values from DB
			String sessionId = wrapperToSaveData.getPersistenceSessionId();
			String contextId = wrapperToSaveData.getContextId();
			ArchetypeWrapper wrapperToLoadData = null;
			ArrayList<ArchetypeData> archetypeDatas = new ArrayList<ArchetypeData>(testInstance.getArchetypeDataList(sessionId, contextId));
			String archetypeFileName = null;
			if (archetypeDatas.size() < 1){
				System.out.println("Could not load archetype based data from DB, quitting");
				System.exit(-1);
			}else
			{
				archetypeFileName = archetypeDatas.get(0).getArchetypeName();
				//the loaded archetypewrappers do not have the repository address, they only know the adl file name, so we 
				//should complete it to full repository address
				wrapperToLoadData = testInstance.loadArchetypeFromFile("C:\\archetypeRepository\\" + archetypeFileName);
				wrapperToLoadData.loadFromPOJO(archetypeDatas);
				System.out.println(wrapperToLoadData.getRootWrapper().getGUITestString());
				System.out.println(wrapperToLoadData.getRootWrapper().getGUITestString());
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}
	
	public ArchetypeWrapper loadArchetypeFromFile(String pArchetypePath) throws Exception{
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		ArchetypeWrapper wrapper = factory.loadFromFile(pArchetypePath);
		return wrapper;
	}
	
	public ArchetypeWrapper setValuesOfClerkingArchetype(ArchetypeWrapper pArchetypeWrapper) throws Exception{
		//responsibleConsultant
		String respConsNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0004]/value";
		pArchetypeWrapper.getElementAtNode(respConsNodePath).setText("this is responsible consultant");
		//clerking doctor
		String clerkingDoctorNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0005]/value";
		pArchetypeWrapper.getElementAtNode(clerkingDoctorNodePath).setText("clerking doctor is...");
		//source of referral
		String sourceOfReferralNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0042]/value";
		pArchetypeWrapper.getElementAtNode(sourceOfReferralNodePath).setText("source of referral value here");
		//time and date patient seen
		String timeAndDatePatSeenNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0050]/value";
		pArchetypeWrapper.getElementAtNode(timeAndDatePatSeenNodePath).setText("time and date patient seen");
		//time and date of clerking
		String timeAndDateOfClerkingNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0051]/value";
		pArchetypeWrapper.getElementAtNode(timeAndDateOfClerkingNodePath).setText("time and date of clerking");
		//patients location
		String patientsLocationNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0045]/value";
		pArchetypeWrapper.getElementAtNode(patientsLocationNodePath).setText("patient's location");
		//doctor's name
		String doctorsNameNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0047]/items[at0046]/value";
		pArchetypeWrapper.getElementAtNode(doctorsNameNodePath).setText("doctor's name");
		//grade
		String gradeNodePath = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0047]/items[at0048]/value";
		pArchetypeWrapper.getElementAtNode(gradeNodePath).setText("grade here");
		//doctor's signature
		String doctorsSignatur = "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/items[at0047]/items[at0049]/value";
		pArchetypeWrapper.getElementAtNode(doctorsSignatur).setText("signature in text form...");
		
		return pArchetypeWrapper;
	}
	
	public ArchetypeWrapper saveClerkingArchetypeToDB(ArchetypeWrapper pArchetypeWrapper) throws Exception{
		//first generate a persistence session id. This will be used 
		//to join all the values of RM insances created by this archetype
		//the values will be saved to database with this id in a column, so that we 
		//can get them back later
		String sessionId = UUID.randomUUID().toString();
		pArchetypeWrapper.setPersistenceSessionId(sessionId);
		//now we need a context Id, this is the link between the data and the actual 
		//system. in case of a clinical application, this can be the patient Id for example
		String contextId = "Mr. Test Patient";
		pArchetypeWrapper.setContextId(contextId);
		//wrappers have default mode of new, so no need to set it...
		//pArchetypeWrapper.setMode(ArchetypeWrapper.MODE_NEW);
		
		//set the creation date 
		long l = Calendar.getInstance().getTimeInMillis();		
		pArchetypeWrapper.setArchetypeCreationDate(new Timestamp(l));
		pArchetypeWrapper.setDelayedDBPersistence(true);
		pArchetypeWrapper.saveDataValues();
		pArchetypeWrapper.persistPendingData();
		return pArchetypeWrapper;
	}
	
	public List<ArchetypeData> getArchetypeDataList(String pSessionId, String pContextId){
		ArchetypeDataDAO dao = new ArchetypeDataDAO();
		Query q = dao.getSession().createQuery("from ArchetypeData where sessionId = ? and contextId = ? and deleted = ?");
		q.setString(0, pSessionId);
		q.setString(1, pContextId);
		q.setBoolean(2, false);
		return q.list();
	}

}
