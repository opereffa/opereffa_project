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

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;

public class MainONe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArchetypeDataDAO dao = new ArchetypeDataDAO();
		dao.getSession().getTransaction().begin();
		ArchetypeData data = new ArchetypeData();
		data.setContextId("contextId");
		data.setArchetypeName("my archetype");
		data.setArchetypePath("my archetype path");
		data.setName("unit");
		data.setValueString("string_value");
		data.setValueInt(99L);
		data.setValueDouble(99d);
		data.setSessionId("session Id");
		data.setInstanceIndex(0);
		dao.save(data);
		
		ArchetypeData data2 = new ArchetypeData();
		data2.setContextId("context Id2");
		data2.setArchetypeName("my arche 2");
		data2.setArchetypePath("my arche path2");
		data2.setName("value2");
		data2.setValueString("val str2");
		data2.setSessionId("ses Id2");
		data2.setInstanceIndex(2);
		dao.save(data2);
		
		dao.getSession().getTransaction().commit();

	}

}
