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

import java.io.File;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ActionArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.AdminEntryArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ClusterArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ElementArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.EntryArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.EvaluationArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.InstructionArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ItemArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ItemListArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ItemSingleArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ItemTableArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ItemTreeArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ObservationArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.SectionArchetypeWrapper;

public class ArchetypeWrapperFactory {
	protected Archetype lastLoadedArchetype;
	protected boolean isInSlot = false;
	
	public ArchetypeWrapperFactory(){
		
	}
	
	public void setIsInSlot(boolean pIsInSlot){
		isInSlot = pIsInSlot; 
	}
	
	public ArchetypeWrapper loadFromFile(String pFullFilePath) throws Exception{
		File f = new File(pFullFilePath);
		if (!f.exists())
			throw new Exception("Could not load archetype with the given path: " + pFullFilePath);
		try{
			ADLParser parser = new ADLParser(f);
			Archetype arc = parser.parse();			
			lastLoadedArchetype = arc;
			ArchetypeWrapper wrapper = getWrapper(arc);
			wrapper.setAdlFileName(f.getName());
//			System.out.println(wrapper.getArchetypeId());		
			return wrapper;
		}
		catch (RuntimeException e) {
          System.out.println(e.toString());  
          return null;
        } 
		catch (Error e) {
            System.out.println(e.toString());
            return null;
        }
		catch(Exception ex){
			System.out.println(ex.toString());
			return null;
		}
		
	}
	
	public ArchetypeWrapper getCopyOfLastLoadedArchetypeWrapper() throws Exception{
		ArchetypeWrapper wrapper = getWrapper(lastLoadedArchetype);
		return wrapper;
	}

	protected ArchetypeWrapper getWrapper(Archetype arc ) throws Exception {
		ArchetypeWrapper wrapper = null;
		String archetypeClass = arc.getDefinition().getRmTypeName();
		ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
		if (archetypeClass.equals("OBSERVATION"))
			wrapper = new ObservationArchetypeWrapper(arc, factory, isInSlot);				
		else if (archetypeClass.equals("INSTRUCTION"))
			wrapper = new InstructionArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("OBSERVATION"))
			wrapper = new ObservationArchetypeWrapper(arc, factory, isInSlot);	
		else if (archetypeClass.equals("EVALUATION"))
			wrapper = new EvaluationArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("SECTION"))
			wrapper = new SectionArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ENTRY"))
			wrapper = new EntryArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ADMIN_ENTRY"))
			wrapper = new AdminEntryArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ACTION"))
			wrapper = new ActionArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ITEM_SINGLE"))
			wrapper = new ItemSingleArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ITEM_LIST"))
			wrapper = new ItemListArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ITEM_TREE"))
			wrapper = new ItemTreeArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ITEM_TABLE"))
			wrapper = new ItemTableArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ITEM"))
			wrapper = new ItemArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("CLUSTER"))
			wrapper = new ClusterArchetypeWrapper(arc, factory, isInSlot);
		else if (archetypeClass.equals("ELEMENT"))
			wrapper = new ElementArchetypeWrapper(arc, factory, isInSlot);			
		else
			throw new Exception("Unknown archetype class: " + archetypeClass);
		return wrapper;
	}
	
	

}
