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
package uk.ac.ucl.chime.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sun.reflect.FieldInfo;
import sun.security.action.GetLongAction;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Generator {
	protected String packageName = "package uk.ac.ucl.chime.generated;\n";
	protected String imports = "import javax.swing.JLabel;\n" + 
			"import javax.swing.JTextField;\n" + 
			"import javax.swing.JComboBox;\n" + 
			"import javax.swing.JCheckBox;\n" + 
			"import java.lang.reflect.Field;\n" + 
			"import java.util.ArrayList;\n" +
			"import uk.ac.ucl.chime.wsclient.NodeInfo;\n" + 
			"import uk.ac.ucl.chime.wsclient.NodeManagerDelegate;\n" + 
			"import uk.ac.ucl.chime.wsclient.NodeManagerService;\n" + 
			"import javax.swing.DefaultComboBoxModel;\n\n";
	protected String generatedClassName = "GeneratedPanel2";
	protected String layoutSetupCode = "		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(\n" + 
										"				this);\n" + 
										"		this.setLayout(layout);";
	protected String endOfClass = "\n" + 
			"}\n" + 
			"";
	
	protected String saveToRepositoryCode = "public void saveToRepository(String containerName){\n" + 
			"		try {\n" + 
			"			ArrayList<NodeInfo> nodeInfoArrl = new ArrayList<NodeInfo>();\n" + 
			"			Field[] flds = this.getClass().getDeclaredFields();\n" + 
			"			for(Field fld : flds ){\n" + 
			"				NodeInfo info = new NodeInfo();\n" + 
			"				if (fld.getType() == JTextField.class){					\n" + 
			"					info.setIdentifier(fld.getName());\n" + 
			"					info.getValues().add(((JTextField)fld.get(this)).getText());					\n" + 
			"				}\n" + 
			"				else if (fld.getType() == JComboBox.class){\n" + 
			"					if (((JComboBox)fld.get(this)).getSelectedIndex() < 0 || \n" + 
			"							((JComboBox)fld.get(this)).getItemCount() < 1)\n" + 
			"						continue;//simply ignore this jcombobox\n" + 
			"					info.setIdentifier(fld.getName());\n" + 
			"					info.getValues().add(((JComboBox)fld.get(this)).getSelectedItem().toString());\n" + 
			"				}\n" + 
			"				else if (fld.getType() == JCheckBox.class){\n" + 
			"					info.setIdentifier(fld.getName());\n" + 
			"					info.getValues().add(String.valueOf(((JCheckBox)fld.get(this)).isSelected()));\n" + 
			"				}\n" + 
			"				else\n" + 
			"					continue;//do not add anyting else to arraylist\n" + 
			"				nodeInfoArrl.add(info);\n" + 
			"			}\n" + 
			"			NodeManagerService service = new NodeManagerService();\n" + 
			"			NodeManagerDelegate delegate = service.getNodeManagerPort();\n" + 
			"			delegate.setSomeNodeInfos(containerName, adlFileName,nodeInfoArrl);\n" + 
			"		} catch (Exception ex) {\n" + 
			"			System.out.println(ex.toString());\n" + 
			"		}\n" + 
			"	}\n\n";
	
	protected String adlFileNameMember = "private String adlFileName = ";
	
	protected String adlFileName;
	
	protected String fromStartToBeginningOfLayoutXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + 
			"\n" + 
			"<Form version=\"1.3\" maxVersion=\"1.5\" type=\"org.netbeans.modules.form.forminfo.JPanelFormInfo\">\n" + 
			"  <AuxValues>\n" + 
			"    <AuxValue name=\"FormSettings_autoResourcing\" type=\"java.lang.Integer\" value=\"0\"/>\n" + 
			"    <AuxValue name=\"FormSettings_generateMnemonicsCode\" type=\"java.lang.Boolean\" value=\"false\"/>\n" + 
			"    <AuxValue name=\"FormSettings_i18nAutoMode\" type=\"java.lang.Boolean\" value=\"false\"/>\n" + 
			"    <AuxValue name=\"FormSettings_layoutCodeTarget\" type=\"java.lang.Integer\" value=\"2\"/>\n" + 
			"    <AuxValue name=\"FormSettings_listenerGenerationStyle\" type=\"java.lang.Integer\" value=\"0\"/>\n" + 
			"    <AuxValue name=\"FormSettings_variablesLocal\" type=\"java.lang.Boolean\" value=\"false\"/>\n" + 
			"    <AuxValue name=\"FormSettings_variablesModifier\" type=\"java.lang.Integer\" value=\"2\"/>\n" + 
			"  </AuxValues>\n" + 
			"\n" + 
			"  <Layout>";
	protected String fromEndOfLayoutToBegOfSubComponents = "</Layout>\n" + 
			"  <SubComponents>";
	protected String fromEndOfSubCompToEndOfForm = " </SubComponents>\n" + 
			"</Form>";
	
	public Generator(String className, String pAdlFileName){
		generatedClassName = className;
		adlFileName = pAdlFileName;
	}
	
	protected String getClassBeginningToInitComponents(){
		String begin = "public class ";
		begin += generatedClassName;
		begin += " extends javax.swing.JPanel {\n" + 
				"\n" + 
				adlFileNameMember + "\"" +adlFileName + "\"" + ";\n\n" +
				saveToRepositoryCode +
				"	/** Creates new form Main */\n" + 
				"	public " + generatedClassName + "() {\n" + 
				"		initComponents();\n" + 
				"	}\n" + 
				"\n" + 
				"	/** This method is called from within the constructor to\n" + 
				"	 * initialize the form.\n" + 
				"	 * WARNING: Do NOT modify this code. The content of this method is\n" + 
				"	 * always regenerated by the Form Editor.\n" + 
				"	 */\n" + 
				"	//GEN-BEGIN:initComponents\n" + 
				"	// <editor-fold defaultstate=\"collapsed\" desc=\"Generated Code\">\n" + 
				"	private void initComponents() {";
		return begin;
	}
	
	protected String getComponentInitCodes(HashMap<String,String> fieldnameInitCodeMap){
		String initcode = "";
		for(String key : fieldnameInitCodeMap.keySet()){
			String fieldName = key;
			String fieldInitCode = fieldnameInitCodeMap.get(key);
			initcode += key + " = " + fieldInitCode + ";\n";
		}
		return initcode;
	}
	
	protected String getComponentInitCodes(ArrayList<PanelFieldInfo> fieldInfos){
		String initcode = "";
		for(PanelFieldInfo info : fieldInfos){
			//if this instance has no new... statement just skip it
			if (info.createInstanceStatement == null)
				continue;
			String fieldName = info.instanceName;
			String fieldInitCode = info.createInstanceStatement;
			initcode += fieldName + " = " + fieldInitCode + ";\n";
		}
		return initcode;
	}
	
	protected String getComponentValues(HashMap<String,String> fieldnameFieldvalueMap){
		String valueSetterCode = "";
		for(String key : fieldnameFieldvalueMap.keySet()){
			String fieldName = key;
			String fieldValueCode = fieldnameFieldvalueMap.get(key);
			valueSetterCode += fieldValueCode + "\n;";
		}
		return valueSetterCode;
	}
	
	protected String getComponentValues(ArrayList<PanelFieldInfo> fieldInfos){
		String valueSetterCode = "";
		for(PanelFieldInfo info : fieldInfos){
			//skip if no instance.setValue... statement
			if (info.setInstanceValueStatement == null)
				continue;
			valueSetterCode += info.setInstanceValueStatement + ";\n";
		}
		return valueSetterCode;
	}
	
	protected String getHorizontalLayoutCode(ArrayList<String[]> componentNames){
		String startHorizontalLayoutCode = "		layout.setHorizontalGroup(\n" + 
				"			layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)\n" + 
				"				.add(\n" + 
				"					layout.createSequentialGroup()\n" + 
				"					.addContainerGap()\n" + 
				"					.add(\n" + 
				"						layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)";
		for (int i = 0; i < componentNames.size(); i++){
			startHorizontalLayoutCode += ".add(" + getSequentalGroupCode(componentNames.get(i)) + ")";
//			if (i + 1 == componentNames.size())
//				startHorizontalLayoutCode += ".addContainerGap(15, Short.MAX_VALUE)"; 
		}
		startHorizontalLayoutCode += ").addContainerGap(15, Short.MAX_VALUE)));";
		return startHorizontalLayoutCode;
	}
	
	protected String getHorizontalLayoutCode2(ArrayList<ArrayList<PanelFieldInfo>> components){
		String startHorizontalLayoutCode = "		layout.setHorizontalGroup(\n" + 
				"			layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)\n" + 
				"				.add(\n" + 
				"					layout.createSequentialGroup()\n" + 
				"					.addContainerGap()\n" + 
				"					.add(\n" + 
				"						layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)";
		for (int i = 0; i < components.size(); i++){
			startHorizontalLayoutCode += ".add(" + getSequentalGroupCode(components.get(i)) + ")";
//			if (i + 1 == componentNames.size())
//				startHorizontalLayoutCode += ".addContainerGap(15, Short.MAX_VALUE)"; 
		}
		startHorizontalLayoutCode += ").addContainerGap(15, Short.MAX_VALUE)));";
		return startHorizontalLayoutCode;
	}
	
	protected String getVerticalLayoutCode(ArrayList<String[]> componentNames){
		String startVerticalLayoutCode = "		layout.setVerticalGroup(\n" + 
				"				layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)\n" + 
				"				.add(\n" + 
				"					layout.createSequentialGroup()\n" + 
				"						.addContainerGap()\n";
		for (int i = 0; i < componentNames.size(); i++){
			startVerticalLayoutCode += ".add(" + getParallelGroupCode(componentNames.get(i)) + ")";
			if (i + 1 != componentNames.size())
				startVerticalLayoutCode += ".addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)";
		}
		startVerticalLayoutCode += ".addContainerGap(90, Short.MAX_VALUE)));";
		return startVerticalLayoutCode;
	}
	
	protected String getVerticalLayoutCode2(ArrayList<ArrayList<PanelFieldInfo>> components){
		String startVerticalLayoutCode = "		layout.setVerticalGroup(\n" + 
				"				layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)\n" + 
				"				.add(\n" + 
				"					layout.createSequentialGroup()\n" + 
				"						.addContainerGap()\n";
		for (int i = 0; i < components.size(); i++){
			startVerticalLayoutCode += ".add(" + getParallelGroupCode(components.get(i)) + ")";
			if (i + 1 != components.size())
				startVerticalLayoutCode += ".addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)";
		}
		startVerticalLayoutCode += ".addContainerGap(30, Short.MAX_VALUE)));";
		return startVerticalLayoutCode;
	}
	
	protected String getEndofInitComponents(){
		String end = "	}// </editor-fold>\n" + 
				"	//GEN-END:initComponents";
		return end;
	}
	
	protected String getComponentFields(HashMap<String, String> componentTypeFldNameMap){
		String componentFields = "//GEN-BEGIN:variables\n" + 
				"		// Variables declaration - do not modify\n";
		for(String key : componentTypeFldNameMap.keySet()){
			componentFields += "private " + componentTypeFldNameMap.get(key) + " " + key +";\n";  
		}
		componentFields += "		// End of variables declaration//GEN-END:variables";
		return componentFields;
	}
	
	protected String getComponentFields(ArrayList<PanelFieldInfo> fieldInfos){
		String componentFields = "//GEN-BEGIN:variables\n" + 
				"		// Variables declaration - do not modify\n";
		for(PanelFieldInfo info : fieldInfos){
			componentFields += "private " + info.instanceFullTypeName + " " + info.instanceName +";\n";  
		}
		componentFields += "		// End of variables declaration//GEN-END:variables";
		return componentFields;
	}
	
	protected String getHorizontalLayoutXML(ArrayList<String[]> componentNames){
		String xml = "    <DimensionLayout dim=\"0\">\n" + 
					"<Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n" + 
					"          <Group type=\"102\" attributes=\"0\">\n" + 
					"              <EmptySpace max=\"-2\" attributes=\"0\"/>\n" + 
					"              <Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n";
		for (int i = 0; i < componentNames.size(); i++){
			xml += getHorizontalXML(componentNames.get(i));
		}
		xml += "</Group>\n " + 
				"              <EmptySpace pref=\"15\" max=\"32767\" attributes=\"0\"/>\n" + 
				"          </Group>\n" + 
				"      </Group>\n" + 
				"    </DimensionLayout>";
		return xml;
	}
	
	protected String getHorizontalLayoutXML2(ArrayList<ArrayList<PanelFieldInfo>> components){
		String xml = "    <DimensionLayout dim=\"0\">\n" + 
					"<Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n" + 
					"          <Group type=\"102\" attributes=\"0\">\n" + 
					"              <EmptySpace max=\"-2\" attributes=\"0\"/>\n" + 
					"              <Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n";
		for (int i = 0; i < components.size(); i++){
			xml += getHorizontalXML(components.get(i));
		}
		xml += "</Group>\n " + 
				"              <EmptySpace pref=\"15\" max=\"32767\" attributes=\"0\"/>\n" + 
				"          </Group>\n" + 
				"      </Group>\n" + 
				"    </DimensionLayout>";
		return xml;
	}
	
	protected String getVerticalLayoutXML(ArrayList<String[]> componentNames){
		String xml = "<DimensionLayout dim=\"1\">\n" + 
				"      <Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n" + 
				"          <Group type=\"102\" alignment=\"0\" attributes=\"0\">\n";
		for (int i = 0; i < componentNames.size(); i++){
			xml += getVerticalXML(componentNames.get(i));
		}
		xml += " <EmptySpace pref=\"90\" max=\"32767\" attributes=\"0\"/>\n" + 
				"          </Group>\n" + 
				"      </Group>\n" + 
				"    </DimensionLayout>\n";
		return xml;
	}
	
	protected String getVerticalLayoutXML2(ArrayList<ArrayList<PanelFieldInfo>> components){
		String xml = "<DimensionLayout dim=\"1\">\n" + 
				"      <Group type=\"103\" groupAlignment=\"0\" attributes=\"0\">\n" + 
				"          <Group type=\"102\" alignment=\"0\" attributes=\"0\">\n";
		for (int i = 0; i < components.size(); i++){
			xml += getVerticalXML(components.get(i));
		}
		xml += " <EmptySpace pref=\"90\" max=\"32767\" attributes=\"0\"/>\n" + 
				"          </Group>\n" + 
				"      </Group>\n" + 
				"    </DimensionLayout>\n";
		return xml;
	}
	
	protected String getParallelGroupCode(String[] componentNames){
		String code = "layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)\n";
		for (int i = 0; i < componentNames.length; i++){
//			if (i == 0)
//				code += "				.add(" + componentNames[i] + ")\n";
//			else
				code += "				.add(" + componentNames[i] + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,\n" + 
//				"					org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,\n" +
				"					100,\n" +
				"					org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)\n";
		}
		return code;
	}
	
	protected String getParallelGroupCode(ArrayList<PanelFieldInfo> fieldInfos){
		String code = "layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)\n";
		for (int i = 0; i < fieldInfos.size(); i++){
			if (i == 0)
				code += "				.add(" + fieldInfos.get(i).instanceName + ")\n";
			else
				code += "				.add(" + fieldInfos.get(i).instanceName + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,\n" + 
				"					" + "org.jdesktop.layout.GroupLayout.DEFAULT_SIZE" + ",\n" + 
				"					org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)\n";
		}
		return code;
	}
	
	protected String getWidthForComponentType(String pFullComponentType){
		//TODO:Use existing values to override defaults
		if (pFullComponentType.equals(GuiCodeConstants.jLabelFullTypeName))
			return "100";
		else if (pFullComponentType.equals(GuiCodeConstants.jComboBoxFullTypeName))
			return "120";
		else if (pFullComponentType.equals(GuiCodeConstants.jTxtFieldFullTypeName))
			return "120";
		return "org.jdesktop.layout.GroupLayout.DEFAULT_SIZE";
	}
	
	protected String getSequentalGroupCode(String[] componentNames){
		String code = "layout.createSequentialGroup()\n"; 
		for (int i = 0; i < componentNames.length; i++){
			if (i == 0)
				code += "	.add(" + componentNames[i] + ")\n" ;
			else
				code += "	.add("+  componentNames[i]  + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)";
			// add this anyway
			code += "	.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)\n";
		}
		return code;
	}
	
	protected String getSequentalGroupCode(ArrayList<PanelFieldInfo> fieldInfos){
		String code = "layout.createSequentialGroup()\n"; 
		for (int i = 0; i < fieldInfos.size(); i++){
//			if (i == 0)
//				code += "	.add(" + fieldInfos.get(i).instanceName + ")\n" ;
//			else
//				code += "	.add("+  fieldInfos.get(i).instanceName  + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)";
			code += "	.add("+  fieldInfos.get(i).instanceName  + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE," + getWidthForComponentType(fieldInfos.get(i).instanceFullTypeName)  + ",org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)";
			// add this anyway
			code += "	.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)\n";
		}
		return code;
	}
	
	protected String getHorizontalXML(String[] componentNames){
		String xml = "<Group type=\"102\" alignment=\"0\" attributes=\"0\">\n"; 
		for (int i = 0; i < componentNames.length; i++){
			if (i + 1 != componentNames.length){
				xml += "                      <Component id=\"" + componentNames[i] + "\" min=\"-2\" max=\"-2\" attributes=\"0\"/>\n";
				xml += "                      <EmptySpace max=\"-2\" attributes=\"0\"/>\n";
			}
			else
				xml += "                      <Component id=\"" + componentNames[i] + "\" min=\"-2\" max=\"-2\" attributes=\"0\"/>\n";
		}
		xml += 				"                  </Group>\n";
		return xml;
	}
	
	protected String getHorizontalXML(ArrayList<PanelFieldInfo> fieldInfos){
		String xml = "<Group type=\"102\" alignment=\"0\" attributes=\"0\">\n"; 
		for (int i = 0; i < fieldInfos.size(); i++){
			if (i + 1 != fieldInfos.size()){
				xml += "                      <Component id=\"" + fieldInfos.get(i).instanceName + "\" min=\"-2\" max=\"-2\" " + getWidthXMLAttrForComponentType(fieldInfos.get(i).instanceFullTypeName) + " attributes=\"0\"/>\n";
				xml += "                      <EmptySpace max=\"-2\" attributes=\"0\"/>\n";
			}
			else
				xml += "                      <Component id=\"" + fieldInfos.get(i).instanceName + "\" min=\"-2\" max=\"-2\" " + getWidthXMLAttrForComponentType(fieldInfos.get(i).instanceFullTypeName) + " attributes=\"0\"/>\n";
		}
		xml += 				"                  </Group>\n";
		return xml;
	}
	
	protected String getVerticalXML(String[] componentNames){
		String xml = "<EmptySpace max=\"-2\" attributes=\"0\"/>\n" + 
		"              <Group type=\"103\" groupAlignment=\"3\" attributes=\"0\">\n"; 
		for (int i = 0; i < componentNames.length; i++){
				
				xml += "                  <Component id=\""+ componentNames[i] +"\" alignment=\"3\" min=\"-2\" max=\"-2\" attributes=\"0\"/>\n";
		}
		xml += "</Group>";
		return xml;
	}
	
	protected String getVerticalXML(ArrayList<PanelFieldInfo> components){
		String xml = "<EmptySpace max=\"-2\" attributes=\"0\"/>\n" + 
		"              <Group type=\"103\" groupAlignment=\"3\" attributes=\"0\">\n"; 
		for (int i = 0; i < components.size(); i++){
				
				xml += "                  <Component id=\""+ components.get(i).instanceName +"\" alignment=\"3\" min=\"-2\" max=\"-2\"  attributes=\"0\"/>\n";
		}
		xml += "</Group>";
		return xml;
	}
	
	protected String getWidthXMLAttrForComponentType(String pInstanceFullTypeName) {
		//TODO:Use existing values to override defaults
		if (pInstanceFullTypeName.equals(GuiCodeConstants.jLabelFullTypeName))
			return "pref=\"100\"";
		else if (pInstanceFullTypeName.equals(GuiCodeConstants.jComboBoxFullTypeName))
			return "pref=\"100\"";
		else if (pInstanceFullTypeName.equals(GuiCodeConstants.jTxtFieldFullTypeName))
			return "pref=\"100\"";
		else if (pInstanceFullTypeName.equals(GuiCodeConstants.jCheckBoxFulltypeName))
			return "pref=\"100\"";
		return "org.jdesktop.layout.GroupLayout.DEFAULT_SIZE";
	}

	protected String getComponentXML(HashMap<String, String> instncNameTypeMap, HashMap<String, String> instanceNameValueMap){
		String xml = "";
		for(String instanceName : instncNameTypeMap.keySet()){
			xml += "    <Component class=\"" + instncNameTypeMap.get(instanceName) + "\" name=\"" + instanceName + "\">\n" + 
				"      <Properties>\n" + 
				"        <Property name=\"text\" type=\"java.lang.String\" value=\"" + instanceNameValueMap.get(instanceName) + "\"/>\n" +
				"      </Properties>\n" + 
				"    </Component>\n";
		}
		return xml;
	}
	
	protected String getComponentXML(ArrayList<PanelFieldInfo> components){
		String xml = "";
		for(PanelFieldInfo info : components){
			xml += "    <Component class=\"" + info.instanceFullTypeName + "\" name=\"" + info.instanceName + "\">\n";
			//all components do not have properties, check value
			if(info.instanceValue != null){
				xml += "      <Properties>\n" + 
				"        <Property name=\"text\" type=\"java.lang.String\" value=\"" + info.instanceValue + "\"/>\n" +
				"      </Properties>\n";
			}
			if (info.customCodeForXML != null){
				xml += info.customCodeForXML + "\n";
			}
			xml += "    </Component>\n";
		}
		return xml;
	}
	
	protected void generateXML(){
		String xml = "";
		xml += fromStartToBeginningOfLayoutXML;
		//component names as tuples in an arraylist
		ArrayList<String[]> componentNames = new ArrayList<String[]>();
		String[] arr1 = new String[]{"lbl1", "txt1"};
		componentNames.add(arr1);
		String[] arr2 = new String[]{"lbl2", "txt2", "lbl3"};
		componentNames.add(arr2);
		xml += getHorizontalLayoutXML(componentNames);
		xml += getVerticalLayoutXML(componentNames);
		xml += fromEndOfLayoutToBegOfSubComponents;
		
		//instances and their types in a map
		HashMap<String, String> instncNameTypeMap = new HashMap<String, String>();
		instncNameTypeMap.put("lbl1", "javax.swing.JLabel");
		instncNameTypeMap.put("txt1", "javax.swing.JTextField");		
		instncNameTypeMap.put("lbl2", "javax.swing.JLabel");
		instncNameTypeMap.put("txt2", "javax.swing.JTextField");
		instncNameTypeMap.put("lbl3", "javax.swing.JLabel");
		//instances and their values
		HashMap<String, String> instanceNameValueMap = new HashMap<String, String>();
		instanceNameValueMap.put("lbl1", "I am lbl1!!!");
		instanceNameValueMap.put("txt1", "I am txt1!!!");
		instanceNameValueMap.put("lbl2", "I am lbl2!!!");
		instanceNameValueMap.put("txt2", "I am txt2!!!");
		instanceNameValueMap.put("lbl3", "I am lbl3!!!");
		xml += getComponentXML(instncNameTypeMap, instanceNameValueMap);
		xml += fromEndOfSubCompToEndOfForm;				
	}
	
	protected ArrayList<ArrayList<PanelFieldInfo>> getDummyFieldInfos(){
		ArrayList<ArrayList<PanelFieldInfo>> arrLOfArrL = new ArrayList<ArrayList<PanelFieldInfo>>();
		
		PanelFieldInfo infoLbl1 = new PanelFieldInfo("lbl1", GuiCodeConstants.createNewLabelStatement, "lbl1.setText(\"I am lbl1\")","javax.swing.JLabel", "I am lbl1");
		PanelFieldInfo infoTxt1 = new PanelFieldInfo("txt1", GuiCodeConstants.createNewTextFieldStatement, "txt1.setText(\"I am txt1\")","javax.swing.JTextField", "I am txt1");
		ArrayList<PanelFieldInfo> row1 = new ArrayList<PanelFieldInfo>();
		row1.add(infoLbl1);
		row1.add(infoTxt1);
		arrLOfArrL.add(row1);
		
		PanelFieldInfo infoLbl2 = new PanelFieldInfo("lbl2", GuiCodeConstants.createNewLabelStatement, "lbl2.setText(\"I am lbl2\")","javax.swing.JLabel", "I am lbl2");
		PanelFieldInfo infoTxt2 = new PanelFieldInfo("txt2", GuiCodeConstants.createNewTextFieldStatement, "txt2.setText(\"I am txt2\")","javax.swing.JTextField", "I am txt2");
		ArrayList<PanelFieldInfo> row2 = new ArrayList<PanelFieldInfo>();
		row2.add(infoLbl2);
		row2.add(infoTxt2);
		arrLOfArrL.add(row2);
		
		PanelFieldInfo infoEmbeddedPnl = new PanelFieldInfo("pnl2", GuiCodeConstants.createNewGeneratedPanelStatement, null,"uk.ac.ucl.chime.gui.GeneratedPanel", null);
		ArrayList<PanelFieldInfo> row4 = new ArrayList<PanelFieldInfo>();
		row4.add(infoEmbeddedPnl);
		arrLOfArrL.add(row4);
		
		PanelFieldInfo infoLbl3 = new PanelFieldInfo("lbl3", GuiCodeConstants.createNewLabelStatement, "lbl2.setText(\"I am lbl3\")","javax.swing.JLabel", "I am lbl3");
		ArrayList<PanelFieldInfo> row3 = new ArrayList<PanelFieldInfo>();
		row3.add(infoLbl3);
		arrLOfArrL.add(row3);
		
		return arrLOfArrL;
	}
	
	public String generatePanelXML(ArrayList<ArrayList<PanelFieldInfo>> rows){
		String xml = "";
		xml += fromStartToBeginningOfLayoutXML;
		//component names as tuples in an arraylist
		ArrayList<PanelFieldInfo> fieldInfos = getFlattenedPanelFieldInfos(rows);
		xml += getHorizontalLayoutXML2(rows);
		xml += getVerticalLayoutXML2(rows);
		xml += fromEndOfLayoutToBegOfSubComponents;
		xml += getComponentXML(fieldInfos);
		xml += fromEndOfSubCompToEndOfForm;
		return xml;
	}
	
	public String generatePanel(ArrayList<ArrayList<PanelFieldInfo>> rows){
		//first generate java code
		String classCode = packageName + imports + getClassBeginningToInitComponents();
		//generate a flat arraylist of all panelfieldinfos
		ArrayList<PanelFieldInfo> fieldInfos = getFlattenedPanelFieldInfos(rows);
		//the field name init code
		classCode += getComponentInitCodes(fieldInfos);
		//the field name value code
		classCode += getComponentValues(fieldInfos);
		//component names, in a way, layout, rows to be displayed
		classCode += layoutSetupCode;
		classCode += getHorizontalLayoutCode2(rows);
		classCode += getVerticalLayoutCode2(rows);
		classCode += getEndofInitComponents();
		classCode += getComponentFields(fieldInfos);		
		classCode += endOfClass;
		System.out.println(classCode);
		return classCode;
	}

	protected ArrayList<PanelFieldInfo> getFlattenedPanelFieldInfos(
			ArrayList<ArrayList<PanelFieldInfo>> rows) {
		ArrayList<PanelFieldInfo> fieldInfos = new ArrayList<PanelFieldInfo>();
		for(ArrayList<PanelFieldInfo> row : rows){
			for(PanelFieldInfo fieldInfo : row){
				fieldInfos.add(fieldInfo);
			}
		}
		return fieldInfos;
	}
	
	protected void generate(){
		String classCode = packageName + imports + getClassBeginningToInitComponents();
		//the field name init code
		HashMap<String, String> initcodes = new HashMap<String, String>();
		initcodes.put("lbl1", "new JLabel(\"I am lbl1\")");
		initcodes.put("txt1", "new JTextField(\"I am txt1\")");
		initcodes.put("lbl2", "new JLabel(\"I am lbl2\")");
		initcodes.put("txt2", "new JTextField(\"I am txt2\")");
		initcodes.put("lbl3", "new JLabel(\"I am lbl3\")");
		
		classCode += getComponentInitCodes(initcodes);
		
		//the field name value code
		HashMap<String, String> valueCodes = new HashMap<String, String>();
		valueCodes.put("lbl1", "lbl1.setText(\"I am lbl1!!!\")");
		valueCodes.put("txt1", "txt1.setText(\"I am txt1!!!\")");
		valueCodes.put("lbl2", "lbl2.setText(\"I am lbl2!!!\")");
		valueCodes.put("txt2", "txt2.setText(\"I am txt2!!!\")");
		valueCodes.put("lbl3", "lbl2.setText(\"I am lbl3!!!\")");
		
		classCode += getComponentValues(valueCodes);
		
		//component names, in a way, layout, rows to be displayed
		ArrayList<String[]> componentNames = new ArrayList<String[]>();
		String[] arr1 = new String[]{"lbl1", "txt1"};
		componentNames.add(arr1);
		String[] arr2 = new String[]{"lbl2", "txt2", "lbl3"};
		componentNames.add(arr2);
		
		classCode += layoutSetupCode;
		classCode += getHorizontalLayoutCode(componentNames);
		classCode += getVerticalLayoutCode(componentNames);
		classCode += getEndofInitComponents();
		
		//the field declerations , field name as key, and type as value..
		HashMap<String, String> fields = new HashMap<String, String>();
		fields.put("lbl1", "javax.swing.JLabel");
		fields.put("txt1", "javax.swing.JTextField");		
		fields.put("lbl2", "javax.swing.JLabel");
		fields.put("txt2", "javax.swing.JTextField");
		fields.put("lbl3", "javax.swing.JLabel");
		classCode += getComponentFields(fields);
		classCode += endOfClass;
	}	
}
