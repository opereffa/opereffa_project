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

import java.sql.Array;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utilities.ConfigurationManager;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;


public class ReportingAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(new ReportingAdapter().getPatientSummary("Test patient Id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mainTest() throws Exception{
		System.out.println("START: " + Calendar.getInstance().getTime().toGMTString());
		ReportingAdapter pTests = new ReportingAdapter();
//		ArrayList<ArchetypeWrapper> wrappers = pTests.getWrappersFromDB("Test patient Id1", "2001-07-02", "2008-08-02", "openEHR-EHR-OBSERVATION.soap_examinationsa2.v1draft.adl");
		ArrayList<ArchetypeWrapper> wrappers = pTests.getArchetypeWrappersFromDB("Test patient Id1", "2001-07-02", "2008-08-02", "openEHR-EHR-OBSERVATION.soap_examinationsa2.v1draft.adl");			
		System.out.println(wrappers.size());
//		for(ArchetypeWrapper wrapper : wrappers){
//			System.out.println(wrapper.getArchetypeCreationDate());
//		}
		System.out.println("END: " + Calendar.getInstance().getTime().toGMTString());
	}
	
	private static void test2() throws Exception{
		
		ArrayList<ArrayList<ArchetypeData>> allArchetypes = load("Test patient Id");
		System.out.println("loaded");
		for (ArrayList<ArchetypeData> aList : allArchetypes){
			ArchetypeWrapper wrapper = new ArchetypeWrapperFactory().loadFromFile(ConfigurationManager.getRepositoryPath() + aList.get(0).getArchetypeName());
			wrapper.loadFromPOJO(aList);
			System.out.println(wrapper.getRootWrapper().getGUITestString());
//			else
//				System.out.println(aList.get(0).getArchetypeName());
		}
	}

	public  String getPatientSummary(String pPatientId) throws Exception{
		StringBuffer sb = new StringBuffer();
		ArrayList<ArrayList<ArchetypeData>> allArchetypes = load(pPatientId);
		System.out.println("loaded");
		for (ArrayList<ArchetypeData> aList : allArchetypes){
			ArchetypeWrapper wrapper = new ArchetypeWrapperFactory().loadFromFile(ConfigurationManager.getRepositoryPath() + aList.get(0).getArchetypeName());
			wrapper.loadFromPOJO(aList);
			String formUrl = aList.get(0).getArchetypeName().replaceAll("\\.adl", "\\.jsf");
			String editLink = "<a href=\"#\" class=\"archetypeLink\" onmouseover=\"archLinkMouseOver(this);\" onmouseout=\"archLinkMouseOut(this);\" onclick=\"getArchetypeFormToEdit('" + wrapper.getPersistenceSessionId() + "', '" + formUrl + "');return false;\">Update</a>";
			sb.append("<div id=\"summary" + wrapper.getPersistenceSessionId() + "\" >" + editLink  + "<br>" +  wrapper.getRootWrapper().getGUITestString() + "</div><br><hr>");			
			//System.out.println(wrapper.getRootWrapper().getGUITestString());
//			else
//				System.out.println(aList.get(0).getArchetypeName());
		}
		return sb.toString();
	}
	
//tolven version
//	public  String getPatientSummary(String pPatientId, String pTolvenContextId) throws Exception{
//		StringBuffer sb = new StringBuffer();
//		ArrayList<ArrayList<ArchetypeData>> allArchetypes = load(pPatientId);
//		System.out.println("loaded");
//		for (ArrayList<ArchetypeData> aList : allArchetypes){
//			ArchetypeWrapper wrapper = new ArchetypeWrapperFactory().loadFromFile(ConfigurationManager.getRepositoryPath() + aList.get(0).getArchetypeName());
//			wrapper.loadFromPOJO(aList);
//			String formUrl = aList.get(0).getArchetypeName().replaceAll("\\.adl", "\\.jsf");
//			String editLink = "<a href=\"#\" class=\"archetypeLink\" onmouseover=\"archLinkMouseOver(this);\" onmouseout=\"archLinkMouseOut(this);\" onclick=\"getArchetypeFormToEdit('" + wrapper.getPersistenceSessionId() + "', '" + formUrl + "'" + ", '" + pTolvenContextId + "');return false;\">Update</a>";
//			sb.append("<div id=\"summary" + wrapper.getPersistenceSessionId() + "\" >" + editLink  + "<br>" +  wrapper.getRootWrapper().getGUITestString() + "</div><br><hr>");			
//			//System.out.println(wrapper.getRootWrapper().getGUITestString());
////			else
////				System.out.println(aList.get(0).getArchetypeName());
//		}
//		return sb.toString();
//	}
	
	private static void test1() throws Exception{
		ArchetypeWrapper wrapper = new ArchetypeWrapperFactory().loadFromFile(ConfigurationManager.getRepositoryPath() + "openEHR-EHR-INSTRUCTION.SOAP_Plan_aspect.v1draft.adl");
		ArrayList<ArrayList<ArchetypeData>> allArchetypes = load("Test patient Id");
		System.out.println("loaded");
		for (ArrayList<ArchetypeData> aList : allArchetypes){
			if (aList.get(0).getArchetypeName().equals("openEHR-EHR-INSTRUCTION.SOAP_Plan_aspect.v1draft.adl")){
				System.out.println("beginning: " + Calendar.getInstance().getTime().toGMTString());
				wrapper.loadFromPOJO(aList);
				System.out.println("ending: " + Calendar.getInstance().getTime().toGMTString());
				break;
			}
//			else
//				System.out.println(aList.get(0).getArchetypeName());
		}
	}
	
	public static ArrayList<ArrayList<ArchetypeData>> load(String pPatientId) throws Exception{
//		System.out.println("beginning: " + Calendar.getInstance().getTime().toGMTString());
		Class.forName("org.postgresql.Driver");
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                "postgres", "postgres");
		if (c != null){
			Statement s = c.createStatement();
			ResultSet rs = null;
			rs = s.executeQuery("select * from getnodecontainers2('" + pPatientId + "')");
			ArrayList<ArrayList<ArchetypeData>> allPojos = new ArrayList<ArrayList<ArchetypeData>>();
			while(rs.next()){
				//id
				Array arrId = rs.getArray(1);
				Long[] idArr = (Long[]) arrId.getArray();
//				System.out.println(idArr[0]);
				
				//context_id
				Array arrContextId = rs.getArray(2);
				String[] contextIdArr = (String[]) arrContextId.getArray();
//				System.out.println(contextIdArr[0]);
				
				
				//archetype_name
				Array arrArchetypeName = rs.getArray(3);
				String[] archetypeNameArr = (String[]) arrArchetypeName.getArray();
//				System.out.println(archetypeNameArr[0]);
				
				//archetype_path
				Array arrArchetypePath = rs.getArray(4);
				String[] archetypePathArr = (String[]) arrArchetypePath.getArray();
//				System.out.println(archetypePathArr[0]);
				
				//name
				Array arrName = rs.getArray(5);
				String[] nameArr = (String[]) arrName.getArray();
//				System.out.println(nameArr[0]);
				
				//value_string
				Array arrValueString = rs.getArray(6);
				String[] valueStringArr = (String[]) arrValueString.getArray();
//				System.out.println(valueStringArr[0]);
				
				//value_int
				Array arrValueInt = rs.getArray(7);
				Long[] valueIntArr = (Long[]) arrValueInt.getArray();
//				System.out.println(valueIntArr[0]);
				
				//value_double
				Array arrValueDouble = rs.getArray(8);
				Double[] valueDoubleArr = (Double[]) arrValueDouble.getArray();
//				System.out.println(valueDoubleArr[0]);
				
				//session_id
				Array arrSessionId = rs.getArray(9);
				String[] sessionIdArr = (String[]) arrSessionId.getArray();
//				System.out.println(sessionIdArr[0]);
				
				//instance_index
				Array arrInstanceIndex = rs.getArray(10);
				Integer[] instanceIndexArr = (Integer[]) arrInstanceIndex.getArray();
//				System.out.println(instanceIndexArr[0]);		
				
				//use all arrays to create POJOS
				ArrayList<ArchetypeData> nodesOfArchetype = new ArrayList<ArchetypeData>();
				for(int i = 0; i < idArr.length; i++){
					ArchetypeData data = new ArchetypeData();
					data.setId(idArr[i]);
					data.setContextId(contextIdArr[i]);
					data.setArchetypeName(archetypeNameArr[i]);
					data.setArchetypePath(archetypePathArr[i]);
					data.setName(nameArr[i]);
					data.setValueString(valueStringArr[i]);
					data.setValueInt(valueIntArr[i]);
					data.setValueDouble(valueDoubleArr[i]);
					data.setSessionId(sessionIdArr[i]);
					data.setInstanceIndex(instanceIndexArr[i]);
					nodesOfArchetype.add(data);
				}
				allPojos.add(nodesOfArchetype);
			}			
//			System.out.println("ended at: " + Calendar.getInstance().getTime().toGMTString());
			return allPojos;
			}
		else return null;
	}
	
	public ArrayList<ArchetypeWrapper> getWrappersFromDB(String pContextId, String pStartDate, String pEndDate, String pAdlName) throws Exception{
		System.out.println("start loading from db: " + Calendar.getInstance().getTime().toGMTString());
		ArrayList<ArrayList<ArchetypeData>> nodesOfArchetypes = getArchetypeData(pContextId, pStartDate, pEndDate, pAdlName);
		System.out.println("end loading from db: " + Calendar.getInstance().getTime().toGMTString());
		ArrayList<ArchetypeWrapper> wrappers = new ArrayList<ArchetypeWrapper>();
		System.out.println("populating wrappers: " + Calendar.getInstance().getTime().toGMTString());
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		for(int i = 0; i < nodesOfArchetypes.size(); i++ ){
			ArrayList<ArchetypeData> nodesOfSingleArchetype  = nodesOfArchetypes.get(i);
			ArchetypeWrapper wrapper = null;
			if (i == 0){
				wrapper = factory.loadFromFile(ConfigurationManager.getRepositoryPath() + nodesOfSingleArchetype.get(0).getArchetypeName());
				System.out.println("file based wrapper populated: " + Calendar.getInstance().getTime().toGMTString());
			}
				
			else
				wrapper = factory.getCopyOfLastLoadedArchetypeWrapper();
			wrapper.loadFromPOJO(nodesOfSingleArchetype);
			wrappers.add(wrapper);
		}
		System.out.println("wrappers populated: " + Calendar.getInstance().getTime().toGMTString());
		return wrappers;
	}
	
	protected  ArrayList<ArrayList<ArchetypeData>> getArchetypeData(String pContextId, String pStartDate, String pEndDate, String pAdlName) throws Exception{
//		System.out.println("beginning: " + Calendar.getInstance().getTime().toGMTString());
		Class.forName("org.postgresql.Driver");
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                "postgres", "postgres");
		if (c != null){
			Statement s = c.createStatement();
			ResultSet rs = null;
			rs = s.executeQuery("select * from getnodecontainers4('" + pContextId + "', '" + 
					pStartDate + "', '" + pEndDate + "', '" + pAdlName + "'  )");
			ArrayList<ArrayList<ArchetypeData>> allPojos = new ArrayList<ArrayList<ArchetypeData>>();
			while(rs.next()){
				//id
				Array arrId = rs.getArray(1);
				Long[] idArr = (Long[]) arrId.getArray();
//				System.out.println(idArr[0]);
				
				//context_id
				Array arrContextId = rs.getArray(2);
				String[] contextIdArr = (String[]) arrContextId.getArray();
//				System.out.println(contextIdArr[0]);
				
				
				//archetype_name
				Array arrArchetypeName = rs.getArray(3);
				String[] archetypeNameArr = (String[]) arrArchetypeName.getArray();
//				System.out.println(archetypeNameArr[0]);
				
				//archetype_path
				Array arrArchetypePath = rs.getArray(4);
				String[] archetypePathArr = (String[]) arrArchetypePath.getArray();
//				System.out.println(archetypePathArr[0]);
				
				//name
				Array arrName = rs.getArray(5);
				String[] nameArr = (String[]) arrName.getArray();
//				System.out.println(nameArr[0]);
				
				//value_string
				Array arrValueString = rs.getArray(6);
				String[] valueStringArr = (String[]) arrValueString.getArray();
//				System.out.println(valueStringArr[0]);
				
				//value_int
				Array arrValueInt = rs.getArray(7);
				Long[] valueIntArr = (Long[]) arrValueInt.getArray();
//				System.out.println(valueIntArr[0]);
				
				//value_double
				Array arrValueDouble = rs.getArray(8);
				Double[] valueDoubleArr = (Double[]) arrValueDouble.getArray();
//				System.out.println(valueDoubleArr[0]);
				
				//session_id
				Array arrSessionId = rs.getArray(9);
				String[] sessionIdArr = (String[]) arrSessionId.getArray();
//				System.out.println(sessionIdArr[0]);
				
				//instance_index
				Array arrInstanceIndex = rs.getArray(10);
				Integer[] instanceIndexArr = (Integer[]) arrInstanceIndex.getArray();
//				System.out.println(instanceIndexArr[0]);
				
				//archetype_created_at
				Array arrArchetypeCreatedAt = rs.getArray(11);
				Timestamp[] archetypeCreatedAtArr = (Timestamp[]) arrArchetypeCreatedAt.getArray();
				
						

				
				//use all arrays to create POJOS
				ArrayList<ArchetypeData> nodesOfArchetype = new ArrayList<ArchetypeData>();
				for(int i = 0; i < idArr.length; i++){
					ArchetypeData data = new ArchetypeData();
					data.setId(idArr[i]);
					data.setContextId(contextIdArr[i]);
					data.setArchetypeName(archetypeNameArr[i]);
					data.setArchetypePath(archetypePathArr[i]);
					data.setName(nameArr[i]);
					data.setValueString(valueStringArr[i]);
					data.setValueInt(valueIntArr[i]);
					data.setValueDouble(valueDoubleArr[i]);
					data.setSessionId(sessionIdArr[i]);
					data.setInstanceIndex(instanceIndexArr[i]);
					data.setArchetypeCreatedAt(archetypeCreatedAtArr[i]);
					nodesOfArchetype.add(data);
				}
				allPojos.add(nodesOfArchetype);
			}			
//			System.out.println("ended at: " + Calendar.getInstance().getTime().toGMTString());
			return allPojos;
			}
		else return null;
	}
	
	//this should be more memory efficient
	protected  ArrayList<ArchetypeWrapper> getArchetypeWrappersFromDB(String pContextId, String pStartDate, String pEndDate, String pAdlName) throws Exception{
//		System.out.println("beginning: " + Calendar.getInstance().getTime().toGMTString());
		ArrayList<ArchetypeWrapper> wrappers = new ArrayList<ArchetypeWrapper>();
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		boolean initialWrapper = true;
		Class.forName("org.postgresql.Driver");
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                "postgres", "postgres");
		if (c != null){
			Statement s = c.createStatement();
			ResultSet rs = null;
			rs = s.executeQuery("select * from getnodecontainers4('" + pContextId + "', '" + 
					pStartDate + "', '" + pEndDate + "', '" + pAdlName + "'  )");
			ArrayList<ArrayList<ArchetypeData>> allPojos = new ArrayList<ArrayList<ArchetypeData>>();
			while(rs.next()){
				//id
				Array arrId = rs.getArray(1);
				Long[] idArr = (Long[]) arrId.getArray();
//				System.out.println(idArr[0]);
				
				//context_id
				Array arrContextId = rs.getArray(2);
				String[] contextIdArr = (String[]) arrContextId.getArray();
//				System.out.println(contextIdArr[0]);
				
				
				//archetype_name
				Array arrArchetypeName = rs.getArray(3);
				String[] archetypeNameArr = (String[]) arrArchetypeName.getArray();
//				System.out.println(archetypeNameArr[0]);
				
				//archetype_path
				Array arrArchetypePath = rs.getArray(4);
				String[] archetypePathArr = (String[]) arrArchetypePath.getArray();
//				System.out.println(archetypePathArr[0]);
				
				//name
				Array arrName = rs.getArray(5);
				String[] nameArr = (String[]) arrName.getArray();
//				System.out.println(nameArr[0]);
				
				//value_string
				Array arrValueString = rs.getArray(6);
				String[] valueStringArr = (String[]) arrValueString.getArray();
//				System.out.println(valueStringArr[0]);
				
				//value_int
				Array arrValueInt = rs.getArray(7);
				Long[] valueIntArr = (Long[]) arrValueInt.getArray();
//				System.out.println(valueIntArr[0]);
				
				//value_double
				Array arrValueDouble = rs.getArray(8);
				Double[] valueDoubleArr = (Double[]) arrValueDouble.getArray();
//				System.out.println(valueDoubleArr[0]);
				
				//session_id
				Array arrSessionId = rs.getArray(9);
				String[] sessionIdArr = (String[]) arrSessionId.getArray();
//				System.out.println(sessionIdArr[0]);
				
				
				//archetype_created_at
				Array arrArchetypeCreatedAt = rs.getArray(10);
				Timestamp[] archetypeCreatedAtArr = (Timestamp[]) arrArchetypeCreatedAt.getArray();
				
				//instance_index
				Array arrInstanceIndex = rs.getArray(12);
				Integer[] instanceIndexArr = (Integer[]) arrInstanceIndex.getArray();
//				System.out.println(instanceIndexArr[0]);		
				
				
				
				//use all arrays to create POJOS
				ArrayList<ArchetypeData> nodesOfArchetype = new ArrayList<ArchetypeData>();
				for(int i = 0; i < idArr.length; i++){
					ArchetypeData data = new ArchetypeData();
					data.setId(idArr[i]);
					data.setContextId(contextIdArr[i]);
					data.setArchetypeName(archetypeNameArr[i]);
					data.setArchetypePath(archetypePathArr[i]);
					data.setName(nameArr[i]);
					data.setValueString(valueStringArr[i]);
					data.setValueInt(valueIntArr[i]);
					data.setValueDouble(valueDoubleArr[i]);
					data.setSessionId(sessionIdArr[i]);
					data.setInstanceIndex(instanceIndexArr[i]);
					data.setArchetypeCreatedAt(archetypeCreatedAtArr[i]);
					nodesOfArchetype.add(data);
				}
				//allPojos.add(nodesOfArchetype);
				ArchetypeWrapper wrapper = null;
				if (initialWrapper){
					wrapper = factory.loadFromFile(ConfigurationManager.getRepositoryPath() + nodesOfArchetype.get(0).getArchetypeName());
					System.out.println("file based wrapper populated: " + Calendar.getInstance().getTime().toGMTString());
					initialWrapper =  false;
				}
					
				else
					wrapper = factory.getCopyOfLastLoadedArchetypeWrapper();
				wrapper.loadFromPOJO(nodesOfArchetype);
				wrappers.add(wrapper);
			}			
//			System.out.println("ended at: " + Calendar.getInstance().getTime().toGMTString());
			return wrappers;
			}
		else return null;
	}
		

}
