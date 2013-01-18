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
package uk.ac.ucl.chime.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import uk.ac.ucl.chime.db.ArchetypeData;
import uk.ac.ucl.chime.main.ReportingAdapter;

public class TestBean {

	protected String val;
	private String testVal;
	private String lastSavedArchetypeId;
	private ArrayList<Object> selectedVals = new ArrayList<Object>();
	private ArrayList<SelectItem> mulSelectValues = new ArrayList<SelectItem>();
	private String randomGUID = "";
	
	public TestBean() {
		SelectItem item1 = new SelectItem("1", "Bir");
		mulSelectValues.add(item1);
		SelectItem item2 = new SelectItem("2", "Iki");
		mulSelectValues.add(item2);
		SelectItem item3 = new SelectItem("3", "Uc");
		mulSelectValues.add(item3);
	}
	
	public ArrayList<SelectItem> getMulSelectValues(){
		return mulSelectValues;
	}
	
	public Object[] getSelectedVals(){
		return selectedVals.toArray();
	}
	
	public void setSelectedVals(Object[] pSelectedVals){
		if (pSelectedVals == null || pSelectedVals.length < 1)
			return;
		selectedVals.clear();
		for(Object o : pSelectedVals){
			selectedVals.add(o);
		}
	}
	
	public String getVal(){
		return val;
	}
	
	public void setVal(String pVal){
		val = pVal;
	}
	
	public String getJavaScript(){
		String script = "<script type=\"text/javascript\"> \n" +
						"//<![CDATA[\n" +  	
						"alert('test changing? that\\'s great!!!');\n" + 
						"//]]>\n" +  
						"</script>";
		return script;
	}

	public void setTestVal(String testVal) {
		this.testVal = testVal;
	}

	public String getTestVal() {
		return testVal;
	}

	public void setLastSavedArchetypeId(String lastSavedArchetypeId) {
		this.lastSavedArchetypeId = lastSavedArchetypeId;
	}

	public String getLastSavedArchetypeId() {
		return lastSavedArchetypeId;
	}
	
	public void validateTest(FacesContext context, UIComponent toValidate, Object value) {		
			((UIInput)toValidate).setValid(false);
			FacesMessage message = new FacesMessage("olmaz kardesim iste");
			context.addMessage(toValidate.getClientId(context), message);			
	}

	//tolven version
//	public String getRecordSummary(){
//		try {
//			System.out.println("in record summary");
////			DBManager manager = new DBManager();
////			ArrayList<OpenEHRDataContainer> allContainers = manager.getOpenEHRRecords("test");
////			StringBuffer summary = new StringBuffer();
////			for(OpenEHRDataContainer container : allContainers){
////				summary.append("Created At:" + container.getCreationDate().toString() + "\n" + container.getArchetypeText() + "\n" + "<hr>");
////			}
////			return summary.toString().replaceAll("\n", "<br>");
//			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//			String contextId = request.getParameter("contextId");
//			String tolvenContextId = request.getParameter("tolvenContextId");
//			System.out.println("contextId: " + contextId);
//			if (contextId == null || contextId == "")
//				return "Could not get a context Id for patient summary";
//			ReportingAdapter adapter = new ReportingAdapter();
//			System.out.println("creatd reporting adapter");
//			return adapter.getPatientSummary(contextId, tolvenContextId).replaceAll("\n", "<br>");
////			ArrayList<ArrayList<ArchetypeData>> nodesofArchetypes = reportingAdapter.load();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	public String getRecordSummary(){
		try {
//			DBManager manager = new DBManager();
//			ArrayList<OpenEHRDataContainer> allContainers = manager.getOpenEHRRecords("test");
//			StringBuffer summary = new StringBuffer();
//			for(OpenEHRDataContainer container : allContainers){
//				summary.append("Created At:" + container.getCreationDate().toString() + "\n" + container.getArchetypeText() + "\n" + "<hr>");
//			}
//			return summary.toString().replaceAll("\n", "<br>");
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String contextId = request.getParameter("contextId");
			if (contextId == null || contextId == "")
				return "Could not get a context Id for patient summary";
			ReportingAdapter adapter = new ReportingAdapter();
			return adapter.getPatientSummary(contextId).replaceAll("\n", "<br>");
//			ArrayList<ArrayList<ArchetypeData>> nodesofArchetypes = reportingAdapter.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String getGuid(){
		return UUID.randomUUID().toString();
	}
	
	public void setGuid(String dummy){
		
	}
	
	public String getInstanceGUID(){
		if (randomGUID.equals(""))
			randomGUID = getGuid();
		return randomGUID;
	}
	
	public String getRecordSummaryFromDB(String pContextId){
		return null;
	}
	
	public String getCurrentDateTime(){
		long l = Calendar.getInstance().getTimeInMillis();
		return String.valueOf(l);
	}
	 
	
//	public void setCurrentDateTime(String dummyParam){
//		
//	}

}
