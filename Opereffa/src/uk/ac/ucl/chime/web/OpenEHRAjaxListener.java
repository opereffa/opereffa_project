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

import javax.el.ELContext;
import javax.faces.component.ContextCallback;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

import java.util.Iterator;
import java.util.Map;

public class OpenEHRAjaxListener implements PhaseListener {
	
	public static final ContextCallback ComponentProcessor = new ContextCallback() {
		   public void invokeContextCallback(FacesContext ctx, UIComponent c) {
			   //use this to select the document to leave enabled/rendered
			   HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			   String fieldToSave = request.getParameter("fieldToProcess");
			   if (fieldToSave.equals("allfields")){
				   c.setRendered(true);
			   }
			   else{
				   String exprString = c.getValueExpression("value").getExpressionString();
				   if (exprString.indexOf("055") > 0)
				   {
					   int dummy = 0;
				   }
				   System.out.println(exprString);
				   if (!c.getClientId(ctx).equals(fieldToSave)){
					   System.out.println("killing valueexpression for: " + c.getId());
					   //c.setValueExpression("value", null);
					   c.setRendered(false);
				   }
				   else
					   c.setRendered(true);
					   
			   		}
			   }			   
		};

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    public void beforePhase(PhaseEvent e) {
    	System.out.print("BEFORE " + e.getPhaseId() + "in process validation phase listener");
    	//just before we leave, if this is an update, synch archetype to its saved form
		try {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String operationContext = request.getParameter("mainform:mode");
//		if (operationContext != null && operationContext != "" && operationContext.equals(ArchetypeWrapper.MODE_UPDATE)){
//			//load archetype data now
//			archetypeWrapper.loadFromDB();
//			
//		}

//			String sessionId = request.getParameter("sessionId");
//			String contextId = request.getParameter("contextId");
			
//			String sessionId = "90ad5d75-380b-4ada-8648-33f4b1c7e0ce";
//			String contextId = "Test patient Id";
//			String createdAt = request.getParameter("createdAt");
//			ArchetypeWrapper archetypeWrapper = getArchetypeWrapper(request);
//			archetypeWrapper.setContextId(contextId);
//			archetypeWrapper.setPersistenceSessionId(sessionId);
//			archetypeWrapper.loadFromDB();
		} catch (Exception e1) {
			System.out.println("Could not load persisted archetype data before validation phase");
			e1.printStackTrace();
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

    public void afterPhase(PhaseEvent e) {
        System.out.print(e.getPhaseId());
        FacesContext ctx = e.getFacesContext();
        Map headers = ctx.getExternalContext().getRequestHeaderMap();
        if ("openehr".equals(headers.get("Ajax-Request"))) {
            System.out.println("Auto-Save");
            disableElementsInTree(ctx, ctx.getViewRoot(), 0);
        } else
            System.out.println("Submit");
    }

    public void disableElementsInTree(FacesContext ctx, UIComponent comp, int level) {
        if (comp == null)
            return;
        Object value = null;
        if (comp instanceof EditableValueHolder){
        	String clientId = comp.getClientId(ctx);
            ctx.getViewRoot().invokeOnComponent(ctx, clientId , ComponentProcessor);
        }
        Iterator children = comp.getChildren().iterator();
        while (children.hasNext()) {
            UIComponent child = (UIComponent) children.next();
            disableElementsInTree(ctx, child, level + 1);
        }
    }

}
