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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Query;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;

public class TestCodesMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TestCodesMain codesMain = new TestCodesMain();
		ArrayList<ArrayList<ArchetypeData>> allPojos = codesMain.load("Test patient Id1");
		System.out.println(allPojos.size());

	}

	private void testHibernateBasedUpdates() {
		ArchetypeDataDAO dao = new ArchetypeDataDAO();	
		dao.getSession().getTransaction().begin();
		ArchetypeData d = new ArchetypeData();
		Query q = dao.getSession().createQuery("UPDATE ArchetypeData SET deleted = ? where archetypePath = ? and contextId = ? and sessionId = ?");
		q.setBoolean(0, true);
		q.setString(1, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/items[at0049]/items[at0050]/value");		
		q.setString(2, "Test patient Id22");
		q.setString(3, "84d2a249-f3dc-4766-8669-99b867c8b6b4");
		q.executeUpdate();
		dao.getSession().getTransaction().commit();
		
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

}
