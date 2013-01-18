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


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.DvText;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.gui.GuiCodeConstants;
import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.utilities.QuantityInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

import com.sun.security.auth.module.JndiLoginModule;

public class QuantityWrapper extends DataValueWrapper {
	
	protected String magnitude;
	protected String unit;
	protected ArchetypeData magnitudeDataInstance;
	protected ArchetypeData unitDataInstance;
	
	public QuantityWrapper(CDvQuantity pdvQuantity, ArchetypeWrapper pContainerArchetype, IWrapperNavigator pParent){
		parent = pParent;
	//builder = pBuilder;
	wrappedObject = pdvQuantity;
	containerArchetype = pContainerArchetype;
}
		
	public boolean isAnyAllowed(){
		return wrappedObject.isAnyAllowed();
	}
	
	private String[] getUnitsForWrappedQuantity(){
		List<CDvQuantityItem> qitems = ((CDvQuantity)wrappedObject).getList();
		if(!(qitems == null || qitems.size() < 1)){
			String[] items = new String[qitems.size()];
			for(int i = 0; i < qitems.size(); i++){
				items[i] = qitems.get(i).getUnits();
			}
			return items;
		}
		else 
			return new String[]{};
	}

//	@Override
//	public Object getRIMValue() {
//		//TODO: implement properly
//		return null;
//	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRMTypeName() throws Exception {
		if (dataValue != null)
			return wrappedObject.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in QuantityWrapper");
	}

	@Override
	public String getGUITestString() {
		if (dataValue != null){
			QuantityInfo qInfo = (QuantityInfo) getValue();
			if (qInfo.getCurrentUnit() != null && qInfo.getMagnitude() != null){
				return qInfo.getMagnitude() + " " +  qInfo.getCurrentUnit(); 
			}
		}
		//let the usual stuff work if there is no set value...
//		StringBuffer quantityWSB = new StringBuffer();
//		if(!wrappedObject.isAnyAllowed()){
//			//a cdv quantity has a list which contains cdvquantityitems
//			List<CDvQuantityItem> qitems = ((CDvQuantity)wrappedObject).getList();
//			if(!(qitems == null || qitems.size() < 1)){
//				
//				String[] items = getUnitsForWrappedQuantity();
//				quantityWSB.append("this is a quantity wrapper with a value and following units");
//				for(String s : items){
//					quantityWSB.append(s);
//				}
//				return quantityWSB.toString();
//			}
//			else
//				return "could not fine units for this quantity wrapper"; 				
//		}
//		else
//		{
//			//TODO how do we encounter this situation?
//			List<CDvQuantityItem> qitems = ((CDvQuantity)wrappedObject).getList();
//			if (qitems != null)
//				return "this is a quantity wrapper with some units allowed";
//			else
//				return "this is a quantity wrapper with all values allowed and no units";
//		}
		return "";
		
	}

	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvQuantity";
        Map<String, Object> values = new HashMap<String, Object>();
        ArrayList<String> allowedValues = new ArrayList<String>();
        if(!wrappedObject.isAnyAllowed()){
			//a cdv quantity has a list which contains cdvquantityitems
			List<CDvQuantityItem> qitems = ((CDvQuantity)wrappedObject).getList();
			if(!(qitems == null || qitems.size() < 1)){				
				String[] items = getUnitsForWrappedQuantity();
				for(String s : items){
					allowedValues.add(s);
				}
				int allowedItemsCount = allowedValues.size();
				int randomIndex = new Random().nextInt(allowedItemsCount);
				values.put("units", allowedValues.get(randomIndex));
			}
			else
				return null;//could not find no units 				
		}
		else
		{
			values.put("units", "kg/L");
		}        
        values.put("magnitude", new Double(new Random().nextDouble() * 10));
        RMObject obj = getBuilder().construct(type, values);
        return obj;        
	}

	@Override
	public Object getValue() {
		// TODO: should be context dependent-> return default val or actual etc..
		QuantityInfo info = new QuantityInfo();
		if(!wrappedObject.isAnyAllowed()){
			//a cdv quantity has a list which contains cdvquantityitems
			List<CDvQuantityItem> qitems = ((CDvQuantity)wrappedObject).getList();
			String currentUnit = null;
			if(!(qitems == null || qitems.size() < 1)){
				String[] items = getUnitsForWrappedQuantity();				
				for(String s : items){
					info.units.add(s);
				}
				//TODO for the moment, I can't see how I can get the current unit? so I'm using 
				//the first one...
				info.setCurrentUnit(info.units.get(0));
			}							 			
		}
		if (dataValue != null){
			String magnitude = String.valueOf(((DvQuantity)dataValue).getMagnitude());
			info.setMagnitude(magnitude);
		}
		else
			info.setMagnitude("");
		return info;		
	}

	@Override
	public void setValue(Object value) throws Exception {
		//not valid for this data type, we need to get two inputs to create it
		//unit and magnitude			
		throw new Exception("SetValue should not be called on this type: see setMagnitude and setUnitOfQuantity");
	}
	
	protected void createRMInstance() throws RMObjectBuildingException{
		if (magnitude == null || unit == null)
			return;		
		String type = "DvQuantity";
		Map<String, Object> values = new HashMap<String, Object>();	                
		values.put("units", unit);
		values.put("magnitude", new Double(String.valueOf(magnitude)));
		RMObject obj = getBuilder().construct(type, values);
		dataValue = (DvQuantity) obj;
	}
	
	protected void loadValue() throws Exception{
		createRMInstance();
	}
	
	protected void setValue() throws Exception {
		createRMInstance();				
			//save to database ONLY if unit and magnitude are set
		//TODO: what about quantities without units???
				
	}
	
	public void saveValueToDB(){
		if (dataValue == null)
			return;
//		System.out.println("saving quanity in Quantity Wrapper with session Id" + containerArchetype.getPersistenceSessionId());
		//for the moment this works slightly different from datawrappers that hold a single 
		//archetypedata instance TODO: in the future a generic mechanism for both should be implemented
		ArchetypeData dataUnit = null; 
		if (unitDataInstance == null){
			dataUnit = new ArchetypeData();
			dataUnit.setContextId(containerArchetype.getContextId());
			dataUnit.setArchetypeName(containerArchetype.getAdlFileName());
			dataUnit.setArchetypePath(wrappedObject.path());
			dataUnit.setSessionId(containerArchetype.getPersistenceSessionId());
			dataUnit.setInstanceIndex(0);
			dataUnit.setDeleted(false);
		}			
		else
			dataUnit = unitDataInstance;
		
		dataUnit.setName("unit");
		dataUnit.setValueString(((DvQuantity)dataValue).getUnits());
		unitDataInstance = saveWithDelayedPersistenceCheck(dataUnit);
		
		ArchetypeData dataMagnitude = null;
		if (magnitudeDataInstance == null){
			dataMagnitude = new ArchetypeData();
			dataMagnitude.setContextId(containerArchetype.getContextId());
			dataMagnitude.setArchetypeName(containerArchetype.getAdlFileName());
			dataMagnitude.setArchetypePath(wrappedObject.path());
			dataMagnitude.setSessionId(containerArchetype.getPersistenceSessionId());
			dataMagnitude.setInstanceIndex(0);
			dataMagnitude.setDeleted(false);
		}
		else
			dataMagnitude = magnitudeDataInstance;
		
		dataMagnitude.setName("magnitude");
		dataMagnitude.setValueDouble(new Double(((DvQuantity)dataValue).getMagnitude()));
		magnitudeDataInstance = saveWithDelayedPersistenceCheck(dataMagnitude);
		
	}

	public void setMagnitude(String pValue) throws Exception {
		if (pValue == null || pValue == "")
			return;
		magnitude = pValue;
		setValue();
	}

	public void setUnitOfQuantity(String pValue) throws Exception {
		if (pValue == null || pValue == "")
			return;
		unit = pValue;
		setValue();		
	}

	@Override
	public String getJSFWidget() {
		//TODO: we should return a simple textbox if no units are requested
		String widgetText = "<h:inputText id=\"quantity" + getHashedPath() + "\" value=\"#{archetypeBinder.dvquantity.magnitude.inNode['" + getPath() + "']}\" validator=\"#{connectorBean.validateNumeric}\" ></h:inputText>" + 
				"					<h:selectOneMenu value=\"#{archetypeBinder.dvquantity.selectedUnit.inNode['" + getPath() +  "']}\">" + 
				"  						<f:selectItems value=\"#{archetypeBinder.dvquantity.unitsList.inNode['" + getPath() + "']}\" />" + 
				"					</h:selectOneMenu>";
		return widgetText;
	}
	
	public String getValidationMessageTag(){
		return "<h:message style=\"color: red;\" for=\"quantity" + getHashedPath() + "\"/>";
	}

	//load value from DB but DO NOT TOUCH existing values, this is used for update
	@Override
	public void loadPersistedData(ArchetypeData pData){
		initArchetypeDataField(pData);
	}

	//This method updates the existing datavalue with DB values
	@Override
	public void setDataValueWithPersistedValue(ArchetypeData pData) throws Exception {
		initArchetypeDataField(pData);
		if (pData.getName().equals("unit")){
			unit = pData.getValueString();			
		}
		else if (pData.getName().equals("magnitude")){
			magnitude = pData.getValueDouble().toString();			
		}
		createRMInstance();
	}
	
	protected void initArchetypeDataField(ArchetypeData pData){
		if (pData.getName().equals("unit"))
			unitDataInstance = pData;					
		else if (pData.getName().equals("magnitude"))
			magnitudeDataInstance = pData;
	}

	@Override
	public void addValueFromDB(ArchetypeData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersistedValue() throws Exception {
		//only execute if both magnitude and unit exist
		if (magnitudeDataInstance == null || unitDataInstance == null)
			return;
		if(magnitude != null && unit != null){//update if both unit and mag. are ready
			ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
			dataDAO.getSession().getTransaction().begin();				
			unitDataInstance.setValueString(unit);
			dataDAO.attachDirty(unitDataInstance);
			magnitudeDataInstance.setValueDouble(Double.valueOf(magnitude));
			dataDAO.attachDirty(magnitudeDataInstance);
			dataDAO.getSession().getTransaction().commit();
		}
			
		else if (magnitude == null){
			//we are interpreting this as a delete at the moment, there is a db row for this node, 
			//but an empty value is set explicitly by the web clients, so let's delete the row in the db
			if (containerArchetype.getMode().equals(ArchetypeWrapper.MODE_UPDATE)){
				ArchetypeDataDAO dataDAO = new ArchetypeDataDAO();
				dataDAO.getSession().getTransaction().begin();				
				unitDataInstance.setDeleted(true);
				dataDAO.attachDirty(unitDataInstance);
				magnitudeDataInstance.setDeleted(true);
				dataDAO.attachDirty(magnitudeDataInstance);
				dataDAO.getSession().getTransaction().commit();
			}
		}
	}

}
