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

import java.beans.FeatureDescriptor;
import java.io.File;
import java.sql.Timestamp;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.openehr.am.archetype.Archetype;
import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.InstructionArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ObservationArchetypeWrapper;

public class CustomResolver extends ELResolver {

	//used to store an archetype wrapper so that it is not read 
	//again in each different method call in the SAME REQUEST
	//protected ArchetypeWrapper archetypeWrapper;
	protected HttpServletRequest currentReq;
		
	
	public CustomResolver(){
		super();
	}
	
	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context,
			Object base) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		//the property is the nodepath we'll be setting
		//we'll be passing the value as string, so return that type 
		//which will be used to call setvalue SAME FOR OTHERS TOO
		if (base instanceof QuantityAdapter ||
				base instanceof CodedTextAdapter ||
				base instanceof TextAdapter ||
				base instanceof BooleanAdapter){
			context.setPropertyResolved(true);
			return String.class;
		}
		
		return null;
	}
	
	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		try {
			System.out.println("base " + (base == null ? "null" : base.toString()));
			System.out.println("property "  + (property == null ? "null" : property.toString()));
			//in each request, make sure only one archetype wrapper is used, and is is refreshed for new requests
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//			if (currentReq == null || currentReq != request){					    
//				currentReq = request;
//				if (archetypeWrapper != null)//each new request should reset archetype wrapper
//					archetypeWrapper = null;
//			}
			if (base != null){//object + property
				if (base instanceof ArchetypeWrapper){ //we are here to resolve a property of an archetype
					if(property.equals("boolean")){
						context.setPropertyResolved(true);					
						//archetype node managers can be created multiple times, they are just used to communicate 
						//to archetype wrappers.
						return new BooleanAdapter(getArchetypeWrapper(request));
					}
					else if (property.equals("dvquantity")){
						context.setPropertyResolved(true);
						return new QuantityAdapter(getArchetypeWrapper(request));	
					}
					else if (property.equals("dvcodedtext")){
						context.setPropertyResolved(true);
						return new CodedTextAdapter(getArchetypeWrapper(request));	
					}
					else if (property.equals("dvtext")){
						context.setPropertyResolved(true);
						return new TextAdapter(getArchetypeWrapper(request));	
					}
					else if (property.equals("randomGroupId")){
						context.setPropertyResolved(true);
						return new RandomGroupIdAdapter(getArchetypeWrapper(request));
					}
					//performAction is automatically called by jsf, no need to handle it here
//					else if (property.equals("performAction")){
//						context.setPropertyResolved(true);
//						return null;
//					}
				}
//				else if (base instanceof ArchetypeNodeManager){
//					//the property is the node path we'll be resolving...
//					context.setPropertyResolved(true);					
//					Object o = ((ArchetypeNodeManager)base).getValueFromNode(property.toString());
//					return o;//this is the object that contains value to get from archetype
//				}
				else if (base instanceof BooleanAdapter){
					context.setPropertyResolved(true);				
					return ((BooleanAdapter)base).getValue(property.toString());
				}
				else if (base instanceof QuantityAdapter){
					context.setPropertyResolved(true);				
					return ((QuantityAdapter)base).getValue(property.toString());
				}
				else if (base instanceof CodedTextAdapter){
					context.setPropertyResolved(true);
					Object codedVal = ((CodedTextAdapter)base).getValue(property.toString());
					System.out.println("codedtextadapter returning " + codedVal.toString());
					return codedVal;
				}
				else if (base instanceof TextAdapter){
					context.setPropertyResolved(true);				
					return ((TextAdapter)base).getValue(property.toString());
				}
				else if (base instanceof RandomGroupIdAdapter){
					context.setPropertyResolved(true);				
					return ((RandomGroupIdAdapter)base).getValue(property.toString());
				}
				return null;
			}
			else{//variable, this is called first, since we're trying to find the base object
				if (property.equals("archetypeBinder")){
					context.setPropertyResolved(true);
//					//we can reach this point during saving of values from UI, let's keep the archetype wrapper
//					if (archetypeWrapper == null)
//						archetypeWrapper = getArchetypeWrapper(request);
					return getArchetypeWrapper(request); //-> 
				} 
				else if (property.equals("archetypeValidator")){
					context.setPropertyResolved(true);
					//TODO: IMPLEMENTING ARCHETYPE BASED VALIDATION BEHIND JSF VALIDATORS
//					if (archetypeWrapper == null)
//						archetypeWrapper = getArchetypeWrapper(request);
					return new ArchetypeValueValidator(getArchetypeWrapper(request)); //-> 
				}				
				return null;//-> this is looking for a variable that we don't resolve
			}
		} catch (Exception e) {
			// TODO: handle exception -> VIA MESSAGE MECHANISM
			System.out.println(e.toString());
			return null;
		}
	}
	
	public ArchetypeWrapper getArchetypeWrapper(HttpServletRequest pRequest) throws Exception{
		String fileName = pRequest.getRequestURI().substring(pRequest.getRequestURI().lastIndexOf("/") + 1).replaceAll(".jsf", ".adl");
		ConnectorBean connectorBean = getRequestBean();		
		return connectorBean.getArchetypeWrapper(fileName);
	}

	 protected ConnectorBean getRequestBean(){
	    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	    	ConnectorBean connector = (ConnectorBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "connectorBean");
	    	return connector;
	    }
	 
	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return false;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value) {
		try {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String operationContext = request.getParameter("operation");
			//String sessionId = request.getParameter("sessionId");
			String contextId = request.getParameter("contextId");
			String createdAt = request.getParameter("createdAt");
			if (value == null || property == null || property.toString().length() < 1){
				context.setPropertyResolved(true);
				return;
			}
			//set context ids
			if (base instanceof RMDataTypeAdapter){
				//((RMDataTypeAdapter)base).getArchetypeWrapper().setPersistenceSessionId(sessionId);
				((RMDataTypeAdapter)base).getArchetypeWrapper().setContextId(contextId);
				Timestamp createdAtTS = new Timestamp(Long.parseLong(String.valueOf(createdAt)));
				((RMDataTypeAdapter)base).getArchetypeWrapper().setArchetypeCreationDate(createdAtTS);
			}
			if (base instanceof QuantityAdapter){				
				((QuantityAdapter)base).setValue(property.toString(), value.toString());
				context.setPropertyResolved(true);
				}
			else if (base instanceof BooleanAdapter){
				((BooleanAdapter)base).setValue(property.toString(), value.toString());
				context.setPropertyResolved(true);
			}
			else if (base instanceof CodedTextAdapter){				
//				if (operationContext != null && operationContext.equals("add")){
//					//this is a web based access to archetypewrapper, so lets invoke 
//					//db based persistence. 
//					((CodedTextAdapter)base).getArchetypeWrapper().setDelayedDBPersistence(false);
//					((CodedTextAdapter)base).addValue(property.toString(), value.toString());
//				}
//				if (operationContext != null && operationContext.equals("delete")){
//					//this is a web based access to archetypewrapper, so lets invoke 
//					//db based persistence. 
//					((CodedTextAdapter)base).getArchetypeWrapper().setDelayedDBPersistence(false);
//					((CodedTextAdapter)base).removeValue(property.toString(), value.toString());
//				}
//				else{
//					((CodedTextAdapter)base).setValue(property.toString(), value.toString());
//				}
				((CodedTextAdapter)base).setValue(property.toString(), value);
				context.setPropertyResolved(true);
			}
			else if (base instanceof TextAdapter){
				((TextAdapter)base).setValue(property.toString(), value.toString());
				context.setPropertyResolved(true);
			}
			else if (base instanceof RandomGroupIdAdapter){
				//no need to set value in backing bean, simply set resolved to true
				context.setPropertyResolved(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.setPropertyResolved(false);
			((FacesContext)context.getContext(FacesContext.class)).addMessage(null, new FacesMessage(e.toString()));
		}
		}
	
	private ArchetypeWrapper getTestWrapper() {
//		try {
//			  
////			  File f = new File("q:\\archetypes\\tony\\openEHR-EHR-OBSERVATION.SOAP_Investigations_SA.v3draft.adl");
////			File f = new File("Q:\\archetypes\\tony\\openEHR-EHR-INSTRUCTION.SOAP_Plan_aspect.v1draft.adl");
//			File f = new File("q:\\archetypes\\tony\\openEHR-EHR-OBSERVATION.SOAP_Examinationsa.v1draft.adl");
//				ADLParser parser = new ADLParser(f);
//				Archetype arc = parser.parse();			
//				String archetypeClass = arc.getDefinition().getRmTypeName();
//				ArchetypeWrapper wrapper = null;
//				if (archetypeClass.equals("OBSERVATION"))
//					wrapper = new ObservationArchetypeWrapper(arc);				
//				else if (archetypeClass.equals("INSTRUCTION"))
//					wrapper = new InstructionArchetypeWrapper(arc);
//				else if (archetypeClass.equals("OBSERVATION"))
//					wrapper = new ObservationArchetypeWrapper(arc);
//				
//				return wrapper;								
//			} catch (Exception e) {
//				System.out.println(e.toString());
//				return null;
//			}
		return null;
	}
		

}
