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

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.Query;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.support.identification.ArchetypeID;



import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utilities.ConfigurationManager;
import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.wrappers.ClusterWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.ItemStructureWrapper;
import uk.ac.ucl.chime.wrappers.RIMWrapper;
import uk.ac.ucl.chime.wrappers.slotwrappers.ISlotWrapper;

public abstract class ArchetypeWrapper implements IWrapperNavigator {
	
	public static String MODE_NEW = "new";
	public static String MODE_UPDATE = "update";
	
	protected boolean isInSlot = false; 
	
	protected String mode = MODE_NEW; //default is new
	
	//creation date/time for the archetype, or in other words logical composition
	protected Timestamp archetypeCreatedAt = null;
	
	//container of hibernate class instances for batch processing
	protected ArrayList<ArchetypeData> archetypeDatas = new ArrayList<ArchetypeData>();
	
	//required for loading archetypes in slots
	protected ArchetypeWrapperFactory wrapperFactory;
	
	//the context id is used to bind the whole wrapper to some external context, like a patient
	protected String contextId;
	
	//the nodes use this variable to decide to save the data on their own instantly, 
	//or to send it to their container so that the container can save all data at once for better performance
	protected boolean delayedDBPersistence;
	
	//required for db based persistence. since each child node can persist itself, 
	//this variable lets us join all nodes later.
	protected String persistenceSessionId;
	
	//The AM instance that represents the parsed archetype
	protected Archetype archetype;
	
	//the EHR related wrapper at the root 
	//can be an observation wrapper, evaluation, instruction etc.
	//at the moment it is the archetype class wrapped by a wrapper
	protected RIMWrapper rootWrapper;
	
	//a hastable that maps DataValue paths to their container elements	
	protected Hashtable<String, ElementWrapper> elementWrappers = new Hashtable<String, ElementWrapper>();
	
	protected Hashtable<String, ISlotWrapper> slotWrappers = new Hashtable<String, ISlotWrapper>();
	
	//TODO: this should be re-written for internal reference handling
	//protected Hashtable<ClusterWrapper, List<ArchetypeInternalRef>>  archetypeInternalRefs = new Hashtable<ClusterWrapper, List<ArchetypeInternalRef>>();
	
	
	//protected Object rmObject;
	public RIMWrapper getRootWrapper(){
		return rootWrapper;
	}
	
	//the link to .ADL file on the file system
	//at the moment we are assuming a file based archetype repository
	private String adlFileName = null;
	
	/*
	 * this init method is necessary for more flexibility of archetype wrapper construction
	 * Java does not let a constructor call to be anyting other than the first call in another constructor
	 * and the workaround is to use this init method in constructor, so that multiple constructors in a class
	 * can change initialization of fields
	 */
	protected  abstract void initArchetypeWrapper(Archetype pArchetype,
			ArchetypeWrapperFactory pFactory) throws Exception; 
	
	//method through which sublasses add their datavalue paths and elements that contain them
	//watch out, the path belongs to a datavaluewrapper, and the object is an Element
	//this is a way of efficiently knowing paths in the archetype, instead of searching with nodes.
	public abstract void addElementWrapper(String pDataValueWrapperPath, ElementWrapper pElementWrapper);
	
	//method to get all registered ElementWrappers within this 
	//Archetype wrapper. Introduced for persistence and easier testing purposes
	//also returning the hastable would expose it for modification, which is not necessary
	public  ArrayList<ElementWrapper> getAllElementWrappers(){
		ArrayList<ElementWrapper> wrappers = new ArrayList<ElementWrapper>();
		for(String path : elementWrappers.keySet()){
			wrappers.add(((ElementWrapper)elementWrappers.get(path)));
		}
		return wrappers;
	}
	
	//method to get a single ElementWrapper, without dealing with iteration over an array
	//prefer this if you know the path to dataValueWrapper
	//the parameter is DataValueWrapper, and its parent, ElementValueWrapper is returned
	public ElementWrapper getElementWrapper(String pDataWrapperPath){
		ElementWrapper wrapper = elementWrappers.get(pDataWrapperPath);
		return wrapper;
	}
	
	//used to get access to AM archetype instance
	//usually used by EHR structures to get access to archetype for things like node name querying 
	//access to ontology section etc.
	public Archetype getArchetype(){
		return archetype;
	}
	
	//related to tooling. JSF based tooling requires an input field in forms
	//this should get out of this class along with the rest of web based app code generation
	protected String getHiddenInputFieldText(){
		String hiddenInput = "<input id=\"archetypeFileName\" type=\"hidden\" value=\"" + getAdlFileName() + "\"/>";
		return hiddenInput;
	}
	
	//get node name of a node using the ontology section of the archetype
	public String getNodeName(String archetypeNodeId){
		if (archetypeNodeId == null || getArchetype() == null)
			return null;
		List<OntologyDefinitions> l = getArchetype().getOntology().getTermDefinitionsList();
		for (OntologyDefinitions defs : l){
			List<ArchetypeTerm> terms = defs.getDefinitions();
			for (ArchetypeTerm t : terms){
				if (archetypeNodeId.equals(t.getCode()))
				return t.getText();
			}
		}
		return null;
	}
	
	public boolean resolveInternalReferences(ClusterWrapper cwrapperToResolve) throws Exception{
//		if (archetypeInternalRefs.contains(cwrapperToResolve)){
//			List<ArchetypeInternalRef> archetypeRefsList = archetypeInternalRefs.get(cwrapperToResolve);
//			for(int i = 0; i < archetypeRefsList.size(); i++){
//				ItemWrapper resolvedItem = findNodeInTree(archetypeRefsList.get(i));
//				if (resolvedItem == null)
//					throw new Exception("Could not resolve ItemWrapper: " + getNodeName(archetypeRefsList.get(i).getTargetPath()));
//				cwrapperToResolve.updateInternalReference(archetypeRefsList.get(i), resolvedItem);
//			}
//			return true;
//		}
//		else{
//			throw new Exception("There are no registered internal references for this cluster: " + getNodeName(cwrapperToResolve.getClusterNodeId()));
//		}		
		//TODO: implement internal archetype references resolver here 
		return true;
	}
	
	//TODO: implement properly for slots
	public void addInternalRef(ClusterWrapper addingClstWrapper, ArchetypeInternalRef refToResolve){
//		if (archetypeInternalRefs.contains(addingClstWrapper)){
//			List<ArchetypeInternalRef> archetypeRefsList = archetypeInternalRefs.get(addingClstWrapper);
//			archetypeRefsList.add(refToResolve);
//			return;
//		}
//		else{
//			List<ArchetypeInternalRef> internalRefListForThisCluster = new ArrayList<ArchetypeInternalRef>();
//			internalRefListForThisCluster.add(refToResolve);
//			archetypeInternalRefs.put(addingClstWrapper, internalRefListForThisCluster);
//		}
	}
	
	public void addInternalRef(ItemStructureWrapper addingISWrapper, ArchetypeInternalRef refToResolve){
		//TODO this is a new type of reference, originating from a structure. don't know what to do if I encounter this!
	}
	
	public Object getElementValue(String pElementPath){
		ElementWrapper wrapper = elementWrappers.get(pElementPath);
		//TODO: implement rest
		return wrapper.getValue();
	}
	
	public ElementWrapper getElementAtNode(String pNodePath){
		ElementWrapper wrapper = elementWrappers.get(pNodePath);
		if (wrapper == null){
			System.out.println("could not find element wrapper" + 
								"Node path: " + pNodePath + 
								"Archetype name: " + getAdlFileName());
		}
		return wrapper;
	}
	
	public  String getJSFForm(){
		//this is where we build the table..
		StringBuffer sb = new StringBuffer();
		//sb.append(HTMLText.Div_Dojo_Marker_Begin);
		sb.append(getHiddenInputFieldText());
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.Table_Begin);
		sb.append(rootWrapper.getJSFWidget());
		sb.append(HTMLText.TR_Submit_Button);
		sb.append(HTMLText.Table_End);
		//sb.append(HTMLText.Div_Dojo_Marker_End);
		return sb.toString();
	}
	
	public  String performAction() {
		try{
			System.out.println("I should arrive here with all things set!");
			//TODO: THERE IS A LOT THAT YOU CAN DO HERE TO IMPROVE PERFORMANCE!! You don't need to load
			//all nodes from db, most of them are already set, etc. etc.  fix this after the initial release
			
			//perform action based on the context
			if(mode.equals(MODE_NEW)){
				//datavalue wrappers saved values from jsf, but they must be saved to db first
				//for performance, let's use batch saves
				setDelayedDBPersistence(true);
				saveDataValues();
				persistPendingData();//COMMIT
				//so first, clear values assigned by JSF mechanims, and load values from db
				clearDataValues();
				loadFromDB();
				//now switch mode to edit, 
				mode = MODE_UPDATE;
			}
			else if(mode.equals(MODE_UPDATE)){
				//first load values from db and update them with values set by jsf
				List<ArchetypeData> updatedDBRows = updateDBWithExistingValues();
				//now clear updated elements, but do not touch inited, but not saved ones
				clearDBInitedElements(updatedDBRows);
				//change mode to new
				setMode(MODE_NEW);
				setDelayedDBPersistence(true);
				saveDataValues();
				persistPendingData();
				setMode(MODE_UPDATE);
				//clear all and load from db
				clearDataValues();
				loadFromDB();
			}
			
			return "done";
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			//TODO: put this error into response using message
			return "Error";
		}
	}

	public void setAdlFileName(String adlFileName) {
		this.adlFileName = adlFileName;
	}

	public String getAdlFileName() {
		return adlFileName;
	}	
	
//	public ArchetypeWrapperFactory getWrapperFactory(){
//		return wrapperFactory;
//	}
	
	public void addSlotWrapper(String pPath, ISlotWrapper pSlotWrapper){
		slotWrappers.put(pPath, pSlotWrapper);
	}
	
	public ArchetypeWrapper getWrapperFromRepository(String pAdlFileName) throws Exception{
		return wrapperFactory.loadFromFile(ConfigurationManager.getRepositoryPath() + pAdlFileName);
	}

	public void InitSlots() throws Exception{
		for(String path : slotWrappers.keySet()){
			ISlotWrapper slotWrapper = slotWrappers.get(path);
			if (!slotWrapper.isSlotFilled()){
				ArrayList<String> availableArchtypesForSlot = slotWrapper.getIncludedArchetypeFileNames();
				String firstOne = availableArchtypesForSlot.get(0);
				System.out.println("Now filling slot with " + firstOne);
				slotWrapper.fillWrapper(firstOne);
			}
		}
	}
	
	public String getPersistenceSessionId(){
		return persistenceSessionId;
	}
	
	public void setPersistenceSessionId(String pPersistenceSessionId){
		persistenceSessionId = pPersistenceSessionId;
	}
	
	public boolean getDelayedDBPersistence(){
		return delayedDBPersistence;
	}
	
	public void setDelayedDBPersistence(boolean pValue){
		delayedDBPersistence = pValue;
	}
	
	public String getContextId(){
		return contextId;
	}
	
	public void setContextId(String pContextId){
		contextId = pContextId;
	}
	
	//method to clear all values in datavaluewrappers
	//useful for cleaning an in memory wrapper before filling from db etc..
	public void clearDataValues(){
		for (String key : elementWrappers.keySet()){
			elementWrappers.get(key).clearAllData();
		}
	}
	
	public void saveDataValues() throws Exception{
		for (String key : elementWrappers.keySet()){
			elementWrappers.get(key).saveValueToDB();
		}
	}
	
	public void loadFromDB() throws Exception{
		List<ArchetypeData> l = getArchetypeDataList();
		for (ArchetypeData data : l){
			ElementWrapper w = getElementWrapper(data.getArchetypePath());
			w.setValueFromDB(data);
		}
		//System.out.println(getRootWrapper().getGUITestString());
		//System.out.println(l.size());
	}
	
	protected List<ArchetypeData> getArchetypeDataList(){
		ArchetypeDataDAO dao = new ArchetypeDataDAO();
		Query q = dao.getSession().createQuery("from ArchetypeData where sessionId = ? and contextId = ? and deleted = ?");
		q.setString(0, persistenceSessionId);
		q.setString(1, contextId);
		q.setBoolean(2, false);
		return q.list();
	}
	
	public List<ArchetypeData> updateDBWithExistingValues() throws Exception{
		List<ArchetypeData> l = getArchetypeDataList();
		for (ArchetypeData data : l){
			ElementWrapper w = getElementWrapper(data.getArchetypePath());
			//these are direct saves to db
			w.updatePersistedData(data);
		}
		return l;
	}
	
	public void clearDBInitedElements(List<ArchetypeData> pArchetypeDatas) throws Exception{
		for (ArchetypeData data : pArchetypeDatas){
			ElementWrapper w = getElementWrapper(data.getArchetypePath());
			//these are direct saves to db
			w.clearAllData();
		}
	}
	
	public void loadFromPOJO(ArrayList<ArchetypeData> pPojos) throws Exception{
		for (ArchetypeData data : pPojos){
			if (archetypeCreatedAt == null && data.getArchetypeCreatedAt() != null){
				setArchetypeCreationDate(data.getArchetypeCreatedAt());
			}
			if (persistenceSessionId == null || persistenceSessionId == "")
				setPersistenceSessionId(data.getSessionId());
			ElementWrapper w = getElementWrapper(data.getArchetypePath());
			w.setValueFromDB(data);
		}
	}
	
	public String getArchetypeId(){
		ArchetypeID id = archetype.getArchetypeId();
		return id.getValue().substring(0, id.getValue().lastIndexOf('.'));
	}
	
	public void saveTestData() throws Exception{
		for (String s : elementWrappers.keySet()){
			ElementWrapper wrapper = elementWrappers.get(s);
			wrapper.saveTestData();
		}
	}
	
	//add an archetypedata instance to arraylist, so that it can be saved later
	public void addArchetypeData(ArchetypeData pArchetypeData){
		archetypeDatas.add(pArchetypeData);
	}
	
	//method to save all pending hibernate class instances.
	//will reset the arraylist after save
	public void persistPendingData(){
		ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
		dataDAO.getSession().getTransaction().begin();
		for (int i = 0; i < archetypeDatas.size(); i++){
			archetypeDatas.get(i).setDeleted(false);
			archetypeDatas.get(i).setArchetypeCreatedAt(getArchetypeCreationDate());
			archetypeDatas.get(i).setFieldCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			if (getMode().equals(ArchetypeWrapper.MODE_NEW))
				dataDAO.save(archetypeDatas.get(i));
			else if (getMode().equals(ArchetypeWrapper.MODE_UPDATE))
				dataDAO.save(archetypeDatas.get(i));
			
//			if(i % 20 == 0){
//				//for performance purposes, clear first level session cache
//				//20 is assumed to be jdbc batch size here
//				dataDAO.getSession().flush();
//				dataDAO.getSession().clear();
//			}
		}		
		dataDAO.getSession().getTransaction().commit();
		dataDAO.getSession().close();
		archetypeDatas.clear();
		}
	
	//getter setter for archetype creation date
	public Timestamp getArchetypeCreationDate(){
		return archetypeCreatedAt;
	}
	
	public void setArchetypeCreationDate(Timestamp pTimestamp){
		archetypeCreatedAt = pTimestamp;
	}
	
	//get operation mode, new, update etc
	public String getMode(){
		return mode;
	}
	
	//set operation mode new, update etc
	public void setMode(String pMode){
		mode = pMode;
	}
	
}
