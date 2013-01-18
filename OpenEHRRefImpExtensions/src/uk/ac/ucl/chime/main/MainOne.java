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
package uk.ac.ucl.chime.main;

import java.io.File;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.build.RMObjectBuilder;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datatypes.text.DvText;

import se.acode.openehr.parser.ADLParser;

import uk.ac.ucl.chime.gui.Generator;
import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utilities.ConfigurationManager;
import uk.ac.ucl.chime.wrappers.ClusterWrapper;
import uk.ac.ucl.chime.wrappers.GuiComposer;
import uk.ac.ucl.chime.wrappers.RMClassName;
import uk.ac.ucl.chime.wrappers.RMTreeConstructor;
import uk.ac.ucl.chime.wrappers.TextValueWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ClusterArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.InstructionArchetypeWrapper;

public class MainOne {
	
	static Logger logger = Logger.getLogger(MainOne.class);
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		try {
//			File f = new File("Q:\\archetypeRepository\\openEHR-EHR-ITEM_TREE.medication.v1.adl");
//			ADLParser parser = new ADLParser(f);
//			Archetype arc = parser.parse();		
//			
//			int oi = 2;
			
//			ArchetypeWrapper wrapper =  factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-INSTRUCTION.medication.v1.adl");
//			wrapper.InitSlots();
//			int i = 2;
			//ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-OBSERVATION.soap_examinationsa.v1draft.adl");
			
//			ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-OBSERVATION.soap_examinationsa2.v1draft.adl");
//			wrapper.setContextId("Test patient Id180");
//			wrapper.setPersistenceSessionId("64223401-28fa-4d06-9e0a-bf3e1fee041b");
//			wrapper.loadFromDB();
//			
//			
			//ArchetypeWrapper wrapper = factory.loadFromFile(ConfigurationManager.getRepositoryPath() + "openEHR-EHR-OBSERVATION.soap_examinationsa2.v1draft.adl");
			ArchetypeWrapper wrapper = factory.loadFromFile("c:\\archetypeRepository\\openEHR-EHR-EVALUATION.SOAP_Assessment_RCPTERMBOUND.v3.adl");
//			wrapper2.setContextId("Test patient Id180");
//			wrapper2.setPersistenceSessionId("55ea72c3-0770-458b-85cb-d2cf03f89366");
//			wrapper2.loadFromDB();
			
//			ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-OBSERVATION.SOAP_Investigations_SA.v3draft.adl");
//			ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-OBSERVATION.SOAP_History.v2draft.adl");
//			ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-EVALUATION.SOAP_Assessment_aspect.v1draft.adl");
//			ArchetypeWrapper wrapper = factory.loadFromFile("Q:\\archetypeRepository\\openEHR-EHR-INSTRUCTION.SOAP_Plan_aspect.v1draft.adl");
			int patientNo = 1;
			long beginDate = getBaseDateForRandomDates();
			long endDate = getEndDateForRandomDates();
			for (int i = 0; i < 100; i++){
//				if (i % 500 == 0)
//					patientNo++;
				//wrapper.setContextId("demouser" + String.valueOf(patientNo));
				wrapper.setContextId("demouser");
				wrapper.setArchetypeCreationDate(getRandomDate(beginDate, endDate));
				wrapper.setPersistenceSessionId(UUID.randomUUID().toString());
////				wrapper.loadFromDB();
				wrapper.setDelayedDBPersistence(true);
				wrapper.saveTestData();
				//wrapper.persistPendingData();
				if (i % 1000 == 0)
					System.out.println(i + " : " + Calendar.getInstance().getTime().toGMTString());
			}
			
			wrapper.saveTestData();
			System.out.println("hello");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static long getBaseDateForRandomDates(){
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, 1990);
		calender.set(Calendar.MONTH, 1);
		calender.set(Calendar.DAY_OF_MONTH, 1);
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		long valInMilliseconds=calender.getTimeInMillis();
		return valInMilliseconds;
	}
	
	public static long getEndDateForRandomDates(){
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, 2008);
		calender.set(Calendar.MONTH, 12);
		calender.set(Calendar.DAY_OF_MONTH, 31);
		calender.set(Calendar.HOUR_OF_DAY, 23);
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.SECOND, 59);
		long valInMilliseconds=calender.getTimeInMillis();
		return valInMilliseconds;
	}
	
	public static Timestamp getRandomDate(long pBaseDateinMs, long pEndDateInMs){
		Random r=new Random();
		long randomTS=(long)(r.nextDouble()*(pEndDateInMs - pBaseDateinMs))+ pBaseDateinMs;
		Timestamp t = new Timestamp(randomTS);
		return t;
	}
	
	
	public static ClusterArchetypeWrapper getArchetypeWrapper(){
//		final Test t = new Test();
		try {
//			java.awt.EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					t.setVisible(true);
//				}
//			});
			//openEHR-EHR-CLUSTER.cranial_nerves.v1draft.adl
//			File f = new File("archetypes/openEHR-EHR-CLUSTER.coordination.v1draft.adl");
			File f = new File("archetypes/openEHR-EHR-CLUSTER.auscultation-chest.v1.adl");
//			File f = new File("archetypes/openEHR-EHR-CLUSTER.exam-chest.v1.adl");
//			File f = new File("archetypes/openEHR-EHR-CLUSTER.health_event-poisoning.v1.adl");
			if (!f.exists()){
				logger.info("Archetype file not found, quitting");
				return null;
			}
			ADLParser parser = new ADLParser(f);
			Archetype arc = parser.parse();
//			RMTreeConstructor treeConstructor = new RMTreeConstructor(arc, t.getPanelToFill());
			
//			process definition subsection of archetype
			CComplexObject definition =  arc.getDefinition();
			String definitionTypeName = definition.getRmTypeName();
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			if (RMClassName.valueOf(definitionTypeName) == RMClassName.CLUSTER){
				ClusterArchetypeWrapper aw  = new ClusterArchetypeWrapper(arc, factory);
//				ClusterWrapper cw = new ClusterWrapper(definition,aw);
// 				ClusterWrapper processed = cw.processCluster(null);
 				return aw;
			}	
			return null;
			
		} catch (Exception e) {			
			return null;
		}
	}

}
