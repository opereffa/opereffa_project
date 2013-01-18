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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.quantity.DvQuantity;

import sun.util.logging.resources.logging;
import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.db.ArchetypeDataDAO;
import uk.ac.ucl.chime.gui.GuiCodeConstants;
import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.utilities.BooleanInfo;
import uk.ac.ucl.chime.utilities.NodeInfo;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class BooleanValueWrapper extends DataValueWrapper {
	
	public BooleanValueWrapper(CPrimitiveObject pdvboolean, ArchetypeWrapper pContainerArchetype, RIMWrapper pParent){
		//builder = pbuilder;
		parent = pParent;
		wrappedObject = pdvboolean;
		containerArchetype = pContainerArchetype;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() throws Exception {
//		String type = "DvBoolean";
//        Map<String, Object> values = new HashMap<String, Object>();
//        String boolval = "";
//        if (((JCheckBox)guiComponent).isSelected())
//        	boolval = "true";
//        else
//        	boolval = "false";
//        values.put("value", "true");
//        DvBoolean dvb = (DvBoolean) builder.construct(type, values);
//        return dvb;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		return wrappedObject.getRmTypeName();
	}

	@Override
	public String getGUITestString() {
		if (dataValue != null){
			return String.valueOf(((DvBoolean)dataValue).getValue());
		}
		else
			return "";
	}

	@Override
	public Object getTestInstance() throws Exception {
		String type = "DvBoolean";
        Map<String, Object> values = new HashMap<String, Object>();
        int randomIntBetweenZeroandTen = new Random().nextInt(11);
        if (randomIntBetweenZeroandTen <= 5)
        	values.put("value", "true");
        else
        	values.put("value", "false");
        RMObject obj = getBuilder().construct(type, values);
        return obj;
	}

	@Override
	public Object getValue() {		
		BooleanInfo binfo = new BooleanInfo();
		if (dataValue != null)
			binfo.setValue(((DvBoolean)dataValue).getValue());
		else
				binfo.setValue(false);
		return binfo;
	}
	
	protected void createRMInstance(String value) throws Exception{
		try {
			if (value == null || value.length() < 1)
				return;
			String strVal = value.toUpperCase();
			if (!(strVal.equals("TRUE") || strVal.equals("FALSE")))
				return;
			String type = "DvBoolean";
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("value", strVal.toLowerCase());
			DvBoolean dvb = (DvBoolean) getBuilder().construct(type, values);
			dataValue = dvb;
		} catch (RMObjectBuildingException e) {
			//TODO: bind to logging
			e.printStackTrace();
		}
	}
	
	@Override
	public void setValue(Object pValue) throws Exception {
		if (pValue == null || pValue == "")
			return;
		createRMInstance(pValue.toString());		
		//save this to db
		//saveValueToDB();
	}
	
	public void saveValueToDB(){
		if (dataValue == null)
			return;
		ArchetypeData data = getContextBasedArcetypeData();
		data.setName("value");
		data.setValueString(String.valueOf(((DvBoolean)dataValue).getValue()).toLowerCase());
		archetypeDataInstance = saveWithDelayedPersistenceCheck(data);
	}

	@Override
	public String getJSFWidget() {
		String widget = "<h:selectBooleanCheckbox value=\"#{archetypeBinder.boolean.value.inNode['" + getPath() + "']}\"></h:selectBooleanCheckbox>";		
		return widget;
	}

	@Override
	public void setDataValueWithPersistedValue(ArchetypeData data) throws Exception {
		createRMInstance(data.getValueString().toUpperCase());
		archetypeDataInstance = data;
	}

	@Override
	public void addValueFromDB(ArchetypeData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersistedValue() throws Exception {
		if (archetypeDataInstance == null)
			throw new Exception("No data provided by persistence in BooleanValueWrapper");
		saveValueToDB();	
	}

	

}
