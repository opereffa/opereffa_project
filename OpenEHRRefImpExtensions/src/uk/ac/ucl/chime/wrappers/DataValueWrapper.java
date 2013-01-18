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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.build.RMObjectBuilder;
import org.openehr.rm.datatypes.basic.DataValue;



import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public abstract class DataValueWrapper  extends RIMWrapper{
	
	//the db access data object instance used for saving data
	//necessary to switch to edit mode
	protected ArchetypeData archetypeDataInstance;
	
	//the db access data object collection, where multiple instances of a RM type is serialized 
	//like diagnosis codes under codedtextvaluewrapper
	protected ArrayList<ArchetypeData>	archetypeDataInstances = new ArrayList<ArchetypeData>();
	
	//the field that holds the cardinality/occurance context of the data value wrapper
	//this context can only be known by the element wrapper, since datawrappers do not have access
	//to their parents. therefore this field will be set by the element wrapper, and datavaluewrapper will act accordingly
	//at the moment this is used only by codedtextvalue wrappers
	protected boolean oneOftheMultipleInstances = false;
	
	//The RM data value object wrapped by the wrapper
	protected DataValue dataValue;
	protected ArrayList<DataValue> dataValueList = new ArrayList<DataValue>(); 
	
	//the container archetype, usually required for access to ontology section
	//to get the name of the DataValue nodes
	protected ArchetypeWrapper containerArchetype;
	
	//the AM object that comes from the parser
	protected CObject wrappedObject;
	
	//method that sets the value for the stored RM instance
	//each descendent of this class implements it 
	//to cast the string parameter to suitable types and process it
	public abstract void setValue(Object pValue) throws Exception;	
	
	public DataValueWrapper(){
	}
	
	//gets the archetype path of the wrapped
	//AM object. this is used by tooling and is 
	//also required to set values of RM instances
	public String getPath(){
		return wrappedObject.path();
	}			
	
	//gets the value of the wrapped RM object
	//at the moment this is in the form of a pojo like class
	//which is casted to its correct type by the clients
	public abstract Object getValue();

	public abstract void setDataValueWithPersistedValue(ArchetypeData data) throws Exception;
	
	//assign loaded archetypedata instance, but don't touch existing values, if there are any
	//this combination is used for updates
	//multiple archetypedata node instances should be handled by overriding this function (like quantitywrapper)
	public void loadPersistedData(ArchetypeData pData) throws Exception{
		archetypeDataInstance = pData;
	}
	
	//transfer the existing value to DB classes (second step in update)
	public abstract void updatePersistedValue() throws Exception;

	public abstract void addValueFromDB(ArchetypeData data) throws Exception;
	
	//get the first half of markup for jsf+dojo integration
	//this markup is used for simpleadder widget, 
	//for multiple instances of a simple data item, like a combobox of diagnoses 
	public String getSimpleAdderBegin(){
		return null;
	}
	
	//get the second half of markup for jsf+dojo integration
	//this markup is used for simpleadder widget, 
	//for multiple instances of a simple data item, like a combobox of diagnoses
	public String getSimpleAdderEnd(){
		return null;
	}
	
	//method that saves the referred rm instance to db
	public abstract void saveValueToDB() throws Exception;
	
	//create a test instance of the referred RM type and save it to db
	public void createAndSaveTestInstance() throws Exception{
		dataValue = (DataValue) getTestInstance();
		if (dataValue != null) // it can happen if a previously saved code is created again
			saveValueToDB();
	}
	
	//method that saves an hibernate class either directly to db
	//or to parent archetype for batch processing later
	protected ArchetypeData saveWithDelayedPersistenceCheck(ArchetypeData pArchetypeData){
				
		if (containerArchetype.getDelayedDBPersistence()){		
			containerArchetype.addArchetypeData(pArchetypeData);
			//won't be saved now, so no point in returning it
			return null;
		}
		else{
			ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
			dataDAO.getSession().getTransaction().begin();
			//use archetype creation date in container archetype
			pArchetypeData.setArchetypeCreatedAt(containerArchetype.getArchetypeCreationDate());
			pArchetypeData.setFieldCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			pArchetypeData.setDeleted(false);
			if (containerArchetype.getMode().equals(ArchetypeWrapper.MODE_NEW))
				dataDAO.save(pArchetypeData);
			else if (containerArchetype.getMode().equals(ArchetypeWrapper.MODE_UPDATE))
				dataDAO.attachDirty(pArchetypeData);
			dataDAO.getSession().getTransaction().commit();
			//return saved instance
			return pArchetypeData;
		}
	}
	
	//getter setter for one of the multiple instances
	public boolean getOneoftheMultipleInstances(){
		return oneOftheMultipleInstances;
	}
	
	public void setOneoftheMultipleInstances(boolean pOneOftheMultipleInstances){
		oneOftheMultipleInstances = pOneOftheMultipleInstances;
	}
	
	//method that clears both single value and value collection
	public void clearAllValues(){
		dataValue = null;
		dataValueList = new ArrayList<DataValue>();		
	}
	
	public void clearArchetypeDataInstance(){
		archetypeDataInstance = null;
	}
	
	//method that clears only single instance values like text, a single code etc.
	public void clearSingleValue(){
		dataValue = null;
	}
	
	//method that clears collection that contains values
	public void clearDataValueCollection(){
		dataValueList = new ArrayList<DataValue>();
	}
	
	//create and return an archetypedata instance based on 
	//edit or create mode
	protected ArchetypeData getContextBasedArcetypeData(){
		ArchetypeData data = null;
		if (archetypeDataInstance != null)
			data = archetypeDataInstance;
		else 
		{
			data = new ArchetypeData();
			data.setContextId(containerArchetype.getContextId());
			data.setArchetypeName(containerArchetype.getAdlFileName());
			data.setArchetypePath(wrappedObject.path());
			data.setSessionId(containerArchetype.getPersistenceSessionId());
			data.setInstanceIndex(0);
		}
		return data;
	}
	
	//return a string of numeric characters using the path of this node
	public String getHashedPath(){
		return String.valueOf(Math.abs(getPath().hashCode()));
	}
	
	//jsf validation message text, should be overriden by descendants of the class
	public String getValidationMessageTag() {
		return "";
	}
	
	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() {
		//data value wrappers have no children to return
		return null;
	}
	
	//data value wrappers return their rm type names instead of their names, since 
	//meaningful names given to archetypes stay at element level, but tooling needs
	//to display names for data values too, so we use type name instead of instance name, which does not exist 
	//at datavalue wrapper level
	public String getName(){
		return wrappedObject.getRmTypeName();
	}

}
