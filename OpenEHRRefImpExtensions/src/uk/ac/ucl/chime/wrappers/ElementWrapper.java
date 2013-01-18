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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.ConstraintRef;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.basic.Interval;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.utilities.HTMLText;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

public class ElementWrapper extends Containable {
	static Logger logger = Logger.getLogger(ElementWrapper.class);
	
	protected DataValueWrapper dataValueWrapper;	
	
//	public void setValue(String pValue){		
//	}
	
	public void setLengthOfTime(String pValue){
		if (dataValueWrapper == null)
			return;		
		DurationValueWrapper dw = (DurationValueWrapper) dataValueWrapper;
		dw.setLengthOfTime(pValue);
	}
	
	public void setUnitOfTime(String pValue){
		if (dataValueWrapper == null)
			return;
		DurationValueWrapper dw = (DurationValueWrapper) dataValueWrapper;
		dw.setUnitOfTime(pValue);
	}
	
	public void setMagnitudeOfQuantity(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		QuantityWrapper qw = (QuantityWrapper) dataValueWrapper;
		qw.setMagnitude(pValue);
	}
	
	public void setUnitOfQuantity(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		QuantityWrapper qw = (QuantityWrapper) dataValueWrapper;
		qw.setUnitOfQuantity(pValue);
	}
	
	public void setBooleanValue(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		BooleanValueWrapper bw = (BooleanValueWrapper) dataValueWrapper;
		bw.setValue(pValue);
		
	}
	
	public void setSelectedCodeForText(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		CodedTextValueWrapper ctw = (CodedTextValueWrapper) dataValueWrapper;
		ctw.setValue(pValue);
	}
	
	public void setSelectedTerminologyItem(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		CodedTextValueConstraintBindingWrapper ctw = (CodedTextValueConstraintBindingWrapper) dataValueWrapper;
		ctw.setValue(pValue);
	}
	
	public void setSelectedCodesForText(Object pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		CodedTextValueWrapper ctw = (CodedTextValueWrapper) dataValueWrapper;
		ctw.setValue(pValue);
	}
	
	public void addSelectedCodeForText(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		CodedTextValueWrapper ctw = (CodedTextValueWrapper) dataValueWrapper;
		ctw.addValue(pValue);
	}
	
	public void deleteSelectedCodeForText(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		CodedTextValueWrapper ctw = (CodedTextValueWrapper) dataValueWrapper;
		ctw.removeValue(pValue);
	}
	
	public void setSelectedText(String pValue) throws Exception{
		if (dataValueWrapper == null)
			return;
		TextValueWrapper tvw = (TextValueWrapper) dataValueWrapper;
		tvw.setValue(pValue);
	}
	
	public void setText(String pValue) throws Exception{
		setSelectedText(pValue);//they end up the same...
	}
	
	public ElementWrapper(CComplexObject obj, ArchetypeWrapper pArchetypeWrapper, boolean pHasContainerTypeParent, IWrapperNavigator pParent) throws Exception{
		parent = pParent;
		logger.setLevel(Level.OFF);
		ccomplexReference = obj;
		containerArchetype = pArchetypeWrapper;
		hasContainerTypeParent = pHasContainerTypeParent;
		logger.info("path for element added: " + ccomplexReference.path());
		processElement();
		//we are adding this element wrapper with the path of the data value wrapper. 
		//this may change in the future, where setting of values is no longer done by 
		//element wrappers. 
		if (dataValueWrapper != null){
			containerArchetype.addElementWrapper(dataValueWrapper.getPath(), this);
		}
	}

	public boolean isEmpty(){
		if(dataValueWrapper == null)
			return true;
		else
			return false;
	}
	
	protected void processElement() throws Exception{
		if (ccomplexReference == null)
			throw new Exception("Null element in element wrapper");
		if (!ccomplexReference.isAnyAllowed()){
			CAttribute valueAtr = ccomplexReference.getAttribute("value");
			if (valueAtr != null){
				dataValueWrapper = processValue(valueAtr.getChildren().get(0));
				//configure cardinality/occurance combinations
				if (hasContainerTypeParent && this.ccomplexReference.getOccurrences() != null){
					if( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() > 1){				
						dataValueWrapper.setOneoftheMultipleInstances(true);
					}					
				}				
			}			
			else //TODO: how do we encounter this condition?
				dataValueWrapper = null;
		}
		else //TODO: how do we encounter this condition
			dataValueWrapper = null;		
	}	
		
	protected DataValueWrapper processValue(CObject value) throws Exception{
		
		RMBasicTypeName typeName = RMBasicTypeName.get(value.getRmTypeName().toLowerCase());
		if (typeName == null)
			throw new Exception("Unknown type when constructing elementwrapper");
		//if java 5 allowed switches over enumsets, that'd be delicious.
		//for now I have to map multiple cases to same method. ugly...
		switch (typeName) {
		case dv_quantity:
			return processQuantity(value);
		case dvquantity:
			return processQuantity(value);
		case quantity:
			return processQuantity(value);
		case dv_coded_text:
			return processText(value);
		case dv_text:
			return processText(value);
		case coded_text:
			return processText(value);
		case text:
			return processText(value);
		case dv_boolean:
			return processBoolean(value);
		case bool:
			return processBoolean(value);
		case dv_ordinal:
			return processOrdinal(value);
		case dvordinal:
			return processOrdinal(value);
		case ordinal:
			return processOrdinal(value);
		case dv_date_time:
			return processDateTime(value);
		case dv_date:
			return processDateTime(value);
		case dv_time:
			return processDateTime(value);
		case datetime:
			return processDateTime(value);
		case date:
			return processDateTime(value);
		case time:
			return processDateTime(value);
		case c_date:
			return processDateTime(value);
		case quantity_ratio:
			return processRatio(value);
		case dv_proportion:
			return processProportion(value);
		case proportion:
			return processProportion(value);
		case dv_count:
			return processCount(value);
		case count:
			return processCount(value);
		case interval_count:
			return processInterval(value);
		case interval_quantity:
			return processInterval(value);
		case dv_interval_dv_count_:
			return processInterval(value);
		case dv_interval_dv_quantity_:
			return processInterval(value);
		case dv_internal_dv_datetime_:
			return processInterval(value);
		case dv_interval_count_:
			return processInterval(value);
		case dv_interval_quantity_:
			return processInterval(value);
		case dv_interval_date_time_:
			return processInterval(value);
		case interval_datetime:
			return processInterval(value);
		case dv_multimedia:
			return processMultimedia(value);
		case multimedia:
			return processMultimedia(value);
		case multi_media:
			return processMultimedia(value);
		case uri:
			return processUri(value);
		case dv_uri:
			return processUri(value);
		case dv_ehr_uri:
			return processUri(value);
		case dv_identifier:
			return processIdentifier(value);
		case dv_currency:
			return processCurrency(value);
		case dv_duration:
			return processDuration((CComplexObject) value);
		case duration:
			return processDuration((CComplexObject) value);
		default:
			throw new Exception("unknown type");
		}
	}
	
	protected DataValueWrapper processCurrency(CObject value) {
		throw new NotImplementedException("processcurrency not implemented yet");
	}

	protected DataValueWrapper processIdentifier(CObject value) {
		throw new NotImplementedException("process identifier not implemented yet");
	}

	protected DataValueWrapper processUri(CObject value) {
		throw new NotImplementedException("processURI not implemented yet");
	}

	protected DataValueWrapper processMultimedia(CObject value) {
		throw new NotImplementedException("processmultimedia not implemented yet");		
	}

	protected DataValueWrapper processInterval(CObject value) {
		throw new NotImplementedException("processninterval not implemneted yet"); 
	}

	protected DataValueWrapper processCount(CObject value) {
		throw new NotImplementedException("processcount not implemented yet");
	}

	protected DataValueWrapper processProportion(CObject value) {
		throw new NotImplementedException("processmultimedia not implemented yet");
	}

	protected DataValueWrapper processRatio(CObject value) {
		throw new NotImplementedException("processmultimedia not implemented yet");
	}

	protected DataValueWrapper processDateTime(CObject value) {
		throw new NotImplementedException("processDateTime not implemented yet");
	}

	protected DataValueWrapper processOrdinal(CObject value) {
		CDvOrdinal ordinal = (CDvOrdinal)value;
		return new OrdinalValueWrapper(ordinal,containerArchetype, this );
	}

	protected DataValueWrapper processBoolean(CObject value) {
//		DvBoolean dvboolean = (DvBoolean)value;
		CPrimitiveObject boolobj = (CPrimitiveObject) ((CComplexObject)value).getAttribute("value").getChildren().get(0);
		return new BooleanValueWrapper(boolobj,containerArchetype, this);
	}

	protected DataValueWrapper processText(CObject value) {
		if(value.getRmTypeName().toLowerCase().equals("dv_coded_text"))
		{
			//this is a little bit tricky. dv_coded_text may be using values from the archetype
			//or it may be using a constraint that refers to a terminology source outside of the
			//archetype, like a terminology server. We can only use the type of object returned by ADL parser
			//to discover which case exists. 
			CComplexObject ccompx = (CComplexObject)value;
			CSingleAttribute atr =  (CSingleAttribute) ccompx.getAttribute("defining_code");
			if (atr.getChildren().get(0) instanceof CCodePhrase)			
				return new CodedTextValueWrapper(value,containerArchetype, this);
			else if (atr.getChildren().get(0) instanceof ConstraintRef)
				return new CodedTextValueConstraintBindingWrapper(value, containerArchetype, this);
			else return null;
		}
		else{
			// a simple text container, with no coding constraints
			return new TextValueWrapper(value, containerArchetype, this);
		}
	}

	protected DataValueWrapper processQuantity(CObject value) {
		CDvQuantity dvQuantity = (CDvQuantity) value;
		CComplexObject justfortest = dvQuantity.standardEquivalent();
		QuantityWrapper qw = new QuantityWrapper(dvQuantity, containerArchetype, this);
		return qw;
	}
	
	protected DataValueWrapper processDuration(CComplexObject obj) {
		if (obj.isAnyAllowed())
			return  null;//TODO return default cduration instance
		CPrimitiveObject primitive = (CPrimitiveObject) obj.getAttributes().get(0).getChildren().get(0);
		CDuration d = (CDuration) primitive.getItem();
		String p = d.getPattern();
		//this p is going to be used to provide combobox for selecting time unit
		Interval<DvDuration> i = d.getInterval();
		return new DurationValueWrapper(obj);
	}
	
	public String getElementName(){
		return containerArchetype.getNodeName(ccomplexReference.getNodeID());
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRIMValue() throws Exception {
//		if (dataValueWrapper == null)
//			return null;
//		Map<String, Object> values = new HashMap<String, Object>();
//		Object value = dataValueWrapper.getRIMValue();
//        values.put("archetypeNodeId", ccomplexReference.getNodeID());
//        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
//        values.put("name", name);
//        values.put("value", value);
//        if ( value == null)
//        	return null;
//        RMObject obj = builder.construct("Element", values);
//        return obj;
//	}

	@Override
	public String getRMTypeName() throws Exception {
		if (ccomplexReference != null)
			return ccomplexReference.getRmTypeName();
		else
			throw new Exception("Could not get RMTypeName in ElementWrapper");
	}

	@Override
	public String getGUITestString() throws Exception {
		StringBuffer elWrpSB = new StringBuffer();
		if (ccomplexReference != null && ccomplexReference.getNodeID() != null){
			String nodeName = containerArchetype.getNodeName(ccomplexReference.getNodeID());
			if (nodeName == null)
				nodeName = "ERROR: Could not find name for this node";
			elWrpSB.append(nodeName + " : ");												
			//now add component from datavaluewrapper
			if(dataValueWrapper == null)
				throw new Exception("Could not get DataValueWrapper in ElementWrapper");
			String guiTestString = "\n" + dataValueWrapper.getGUITestString();
			elWrpSB.append(guiTestString);			
		}
		else
			elWrpSB.append("ERROR: could not get name for element");		
		return elWrpSB.toString();
	}

	@Override
	public Object getTestInstance() throws Exception {
		if (dataValueWrapper == null)
			return null;
		Map<String, Object> values = new HashMap<String, Object>();
			Object value = dataValueWrapper.getTestInstance();
        values.put("archetypeNodeId", ccomplexReference.getNodeID());
        DvText name = new DvText(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
        values.put("name", name);
        values.put("value", value);
        if ( value == null)
        	return null;
        RMObject obj = getBuilder().construct("Element", values);
        return obj;
	}
	
	public Object getValue(){
		return dataValueWrapper.getValue();
	}
		

	@Override
	public String getJSFWidget() {
		//return a tr with two cells for an element
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(2));
				sb.append(HTMLText.TR_Begin);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_Begin);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.Div_Dojo_Marker_Begin);
					sb.append(HTMLText.Div_Begin);
					sb.append(containerArchetype.getNodeName(ccomplexReference.getNodeID()));
					sb.append(HTMLText.Div_End);
					sb.append(HTMLText.Div_Dojo_Marker_End);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_End);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_Begin);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(4));
		//if this an element in a container, and this element has a limited occurance, 
		//create special markup for a simpleadder panel
		if (hasContainerTypeParent && this.ccomplexReference.getOccurrences() != null){
			if( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() > 1){
				sb.append(dataValueWrapper.getSimpleAdderBegin());				
			}
			else if ( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() == 1){
				sb.append(HTMLText.Div_Dojo_Marker_Begin);
			} 
			else//this element is in a container, but occurances is not limited
				//TODO: this is not handled for now, semantics is a little bit weird
				//-> same data item added multiple times under a collection???
				sb.append("<div>MULTIPLE ITEMS OF SAME PRIMITIVE UNDER CONTAINER: NOT IMPLEMENTED YET!</div>");
		}
		else if (!hasContainerTypeParent &&  this.ccomplexReference.getOccurrences() != null){
			//primitive data type under non container parent, 1 to 1 mapping betw 
			//ui widget and RM object instance
			sb.append(HTMLText.Div_Dojo_Marker_Begin);
		}				
					sb.append(dataValueWrapper.getJSFWidget());
		if (hasContainerTypeParent && this.ccomplexReference.getOccurrences() != null){
			if( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() > 1){
				sb.append(dataValueWrapper.getSimpleAdderEnd());
			}
			else if ( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() == 1){
				sb.append(HTMLText.Div_Dojo_Marker_End);
			}
			else//this element is in a container, but occurances is not limited
				//TODO: this is not handled for now, semantics is a little bit weird
				//-> same data item added multiple times under a collection???
				sb.append("<div>MULTIPLE ITEMS OF SAME PRIMITIVE UNDER CONTAINER: NOT IMPLEMENTED YET!</div>");
		}
		else if (!hasContainerTypeParent &&  this.ccomplexReference.getOccurrences() != null){
			//primitive data type under non container parent, 1 to 1 mapping betw 
			//ui widget and RM object instance
			sb.append(HTMLText.Div_Dojo_Marker_End);
		}			
		sb.append(HTMLText.NewLine);
		
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_End);
					
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_Begin);
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
			sb.append(dataValueWrapper.getValidationMessageTag());
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(3));
					sb.append(HTMLText.TD_End);
		sb.append(HTMLText.NewLine);
					
		sb.append(HTMLText.NewLine);
		sb.append(HTMLText.getTabs(2));
				sb.append(HTMLText.TR_End);
		sb.append(HTMLText.NewLine);
		return sb.toString();		
	}

	public void setValueFromDB(ArchetypeData data) throws Exception {
		if (this.ccomplexReference.getOccurrences() != null){
			if(this.ccomplexReference.getOccurrences().getUpper().intValue() > 1){
				dataValueWrapper.addValueFromDB(data);
			}
			else
				dataValueWrapper.setDataValueWithPersistedValue(data);
		}
		else //assume occurences as 0..1 PROBABLY NEVER OCCURS, due to parser behaviour
			dataValueWrapper.setDataValueWithPersistedValue(data);	
	}
	
	public void updatePersistedData(ArchetypeData pData) throws Exception{
		//the following assumes default parser beaviour assigns an occurence value all the time
		dataValueWrapper.loadPersistedData(pData);
		dataValueWrapper.updatePersistedValue();
//		if (this.element.getOccurrences() != null){
//			dataValueWrapper.loadValueFromDB(pData);
//			dataValueWrapper.updatePersistedValue();
//		}
//		else{ //assume occurences as 0..1 PROBABLY NEVER OCCURS, due to parser behaviour
//			dataValueWrapper.loadValueFromDB(pData);
//			dataValueWrapper.updatePersistedValue();
//		}	
	}
	
	public void saveValueToDB() throws Exception{
		if (dataValueWrapper == null)
			throw new Exception("Can't save data when datavaluewrapper is null in ElementWrapper");
		dataValueWrapper.saveValueToDB();
	}
	
	public void clearAllData(){
		if (dataValueWrapper != null){
			dataValueWrapper.clearAllValues();
			dataValueWrapper.clearArchetypeDataInstance();
		}
			
	}
	
	public void saveTestData() throws Exception{
		if (hasContainerTypeParent && this.ccomplexReference.getOccurrences() != null){
			if( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() > 1){
				int maxInstanceSize = this.ccomplexReference.getOccurrences().getUpper().intValue();
				int randomInstanceSize = new Random().nextInt(maxInstanceSize + 1);
				for(int i = 0; i < randomInstanceSize; i++){
					dataValueWrapper.createAndSaveTestInstance();
				}
			}
			else if ( this.ccomplexReference.getOccurrences().isUpperIncluded() && this.ccomplexReference.getOccurrences().getUpper().intValue() == 1){
				dataValueWrapper.createAndSaveTestInstance();
			} 
			else//this element is in a container, but occurances is not limited
				//TODO: this is not handled for now, semantics is a little bit weird
				//-> same data item added multiple times under a collection???
				System.out.println("Unhandled cardinality-occurance combination");
		}
	}

	/*
	 * used for tooling, which needs to travel up and down in the hieararchy
	 * @see uk.ac.ucl.chime.wrappers.RIMWrapper#getChildren()
	 */
	@Override
	public List<Object> getChildren() throws Exception {
		ArrayList<RIMWrapper> datavalueWrp = new ArrayList<RIMWrapper>();
		datavalueWrp.add(dataValueWrapper);
		return castList(datavalueWrp, Object.class);
	}
}
