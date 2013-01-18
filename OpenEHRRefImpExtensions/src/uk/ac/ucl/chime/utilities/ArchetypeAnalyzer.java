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
package uk.ac.ucl.chime.utilities;

import java.util.Hashtable;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

/*
 * This class loads an archetype and performs analysis on the archetype structure, like what data types are used etc.
 * 
 */
public class ArchetypeAnalyzer {

	//the String key is the data type name, for the value (Hashtable): the key is the referring archetype name and the integer is the number of times the type is referred
	private static Hashtable<String, Hashtable<String, Integer>> _archetypesReferringToType = new Hashtable<String, Hashtable<String,Integer>>(); 

	private String _ADLFileName;
	
	public ArchetypeAnalyzer(String pADLFileName){
		_ADLFileName = pADLFileName;
		
	}
		
	public static void main(String[] args){
		ArchetypeAnalyzer analyzer = new ArchetypeAnalyzer("openEHR-EHR-INSTRUCTION.medication.v1.adl");
		analyzer.loadArchetype();
	}
	
	public void loadArchetype(){
		try{
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			ArchetypeWrapper wrapper = factory.loadFromFile(ConfigurationManager.getRepositoryPath() + "\\" + _ADLFileName);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
	
	public void loadArchetype(boolean pIsInSlot){
		try{
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			factory.setIsInSlot(pIsInSlot);
			ArchetypeWrapper wrapper = factory.loadFromFile(ConfigurationManager.getRepositoryPath() + "\\" + _ADLFileName);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
	
	
//	private Hashtable<String, Integer> _archetypesReferringToType = new Hashtable<String, Integer>(); 
	
	//used when the archetype is in a slot:  the String key is the data type name, for the value (Hashtable): the key is the referring archetype name and the integer is the number of times the type is referred
	private static Hashtable<String, Hashtable<String, Integer>>  _archetypesInSlotsReferringToType = new Hashtable<String, Hashtable<String,Integer>>();
	
	public static synchronized void addReferenceToType(String pRMTypeName, String pArchetypeName, boolean pIsInSlot){
		Hashtable<String, Hashtable<String, Integer>> containerToUse = null;
		if (pIsInSlot)
			containerToUse = _archetypesInSlotsReferringToType;
		else
			containerToUse = _archetypesReferringToType;
		if (containerToUse.contains(pRMTypeName)){
			Hashtable<String, Integer> referringArchetypes = containerToUse.get(pRMTypeName);
			if (referringArchetypes.contains(pArchetypeName)){
				//this archetype has a reference count for this RM type, so increase the counter for it
				referringArchetypes.put(pArchetypeName, referringArchetypes.get(pArchetypeName) + 1);
			}
			else{
				//this is the first reference to this RMType for this archetype
				referringArchetypes.put(pArchetypeName, 1);
			}
		}
		else{
			//an archetype in a slot has referred to an rm type for the first time
			//so create the value hashtable
			Hashtable<String, Integer> referringArchetypes = new Hashtable<String, Integer>();
			//add the archetype to it..
			referringArchetypes.put(pArchetypeName, 1);
			//and put it in the container hashtable
			containerToUse.put(pRMTypeName, referringArchetypes);
		}
	}
}

