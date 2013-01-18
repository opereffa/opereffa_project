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
package uk.ac.ucl.chime.wrappers.archetypewrappers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Savepoint;
import java.util.List;

import javax.swing.text.html.HTML;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;

import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.ObservationWrapper;

public class ObservationArchetypeWrapper extends ArchetypeWrapper implements Serializable {

	public ObservationArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory) throws Exception{
		initArchetypeWrapper(pArchetype, pFactory);
	}

	protected void initArchetypeWrapper(Archetype pArchetype,
			ArchetypeWrapperFactory pFactory) throws Exception {
		wrapperFactory = pFactory;
		archetype = pArchetype;
		CComplexObject definition = archetype.getDefinition();
		rootWrapper = new ObservationWrapper(definition, this, null);
	}
	
	/*
	 * containment aware constructor
	 */
	public ObservationArchetypeWrapper(Archetype pArchetype, ArchetypeWrapperFactory pFactory, boolean pIsInSlot) throws Exception{
		isInSlot = pIsInSlot;
		initArchetypeWrapper(pArchetype, pFactory);
	}
	
	public ObservationArchetypeWrapper(){
		
	}

	@Override
	public void addElementWrapper(String elementPath,
			ElementWrapper elementWrapper) {
		elementWrappers.put(elementPath, elementWrapper);		
	}

	@Override
	public List<Object> getChildren() throws Exception {
		return rootWrapper.getChildren();
	}

	@Override
	public IWrapperNavigator getParent() throws Exception {
		return null;
	}
	
//	public Object getElementValue(String pElementPath){
//		ElementWrapper wrapper = elementWrappers.get(pElementPath);
//		//TODO: implement rest
//		return wrapper.getValue();
//	}
//	
//	public ElementWrapper getElementAtNode(String pNodePath){
//		ElementWrapper wrapper = elementWrappers.get(pNodePath);
//		return wrapper;
//	}
	
//	public String performAction(){
//		try{
//			System.out.println("I should arrive here with all things set!");
//			//TODO: THERE IS A LOT THAT YOU CAN DO HERE TO IMPROVE PERFORMANCE!! You don't need to load
//			//all nodes from db, most of them are already set, etc. etc.  fix this after the initial release
//			
//			//perform action based on the context
//			if(mode.equals(MODE_NEW)){
//				//datavalue wrappers saved values from jsf, but they must be saved to db first
//				//for performance, let's use batch saves
//				setDelayedDBPersistence(true);
//				saveDataValues();
//				persistPendingData();//COMMIT
//				//so first, clear values assigned by JSF mechanims, and load values from db
//				clearDataValues();
//				loadFromDB();
//				//now switch mode to edit, 
//				mode = MODE_UPDATE;
//			}
//			else if(mode.equals(MODE_UPDATE)){
//				//first load values from db and update them with values set by jsf
//				List<ArchetypeData> updatedDBRows = updateDBWithExistingValues();
//				//now clear updated elements, but do not touch inited, but not saved ones
//				clearDBInitedElements(updatedDBRows);
//				//change mode to new
//				setMode(MODE_NEW);
//				setDelayedDBPersistence(true);
//				saveDataValues();
//				persistPendingData();
//				setMode(MODE_UPDATE);
//				//clear all and load from db
//				clearDataValues();
//				loadFromDB();
//			}
//			
//			return "done";
//		}
//		catch(Exception ex){
//			System.out.println(ex.toString());
//			//TODO: put this error into response using message
//			return "Error";
//		}
//		
//	}
}
