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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ObservationArchetypeWrapper;

public class ArchetypeResolver extends ELResolver {

	protected String nodePath = "";
	protected HttpServletRequest currentReq;
	protected ObservationArchetypeWrapper archeWrapper;

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
		if (property.equals("archeTypeManager") || property.equals("archetypeNode")){
			context.setPropertyResolved(true);	
			return String.class;
		}
		else{
			System.out.println("unknown property: " + property.toString());
			return null;
		}
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
//		if (! 
//				(property.equals("archeTypeManager") ||
//				property.equals("archetypeNode") || 
//				( (base != null) && (base instanceof ArchetypeNodeManager)) )
//			)
//			return null;
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//let's make sure that the objects we have created are not persisted longer than a single request
		if (currentReq == null || currentReq != request){					    
			currentReq = request;						
			if (archeWrapper != null)
				archeWrapper = null;
		}
//		for (Object o : request.getParameterMap().keySet()){
//			//System.out.println(o.toString());
//		}
		if (base == null){//this is the variable resolving case
			if (property == null)
				return null;
			if (! (property.equals("archeTypeManager"))
				)
				return null;
			if (property.equals("archeTypeManager")){
				if (archeWrapper == null)
				{
					context.setPropertyResolved(true);//set to true regardless of what happens (for now)		 
					//return new ArcheTypeManager();
					if (archeWrapper == null){
						archeWrapper = getTestWrapper();
					}
					return archeWrapper;
				}
				else{//we are now resolving within an archetype			
						context.setPropertyResolved(true);
						return archeWrapper;				//TODO: ??????
				}
			}
			
						
		}
		else{// base != null -> an object and a property has arrived
			if (base instanceof ArchetypeWrapper){
				if(property.equals("archetypeNode")){					
					context.setPropertyResolved(true);
					ArchetypeNodeManager adapter = new ArchetypeNodeManager(archeWrapper);
					return adapter;
				}
			}
			else if (base instanceof ArchetypeNodeManager){
				//we must be looking for a node, 
				//TODO: enhance archetype adapter, or use other classes
				//for other operations on node path. The type of the base is the only way
				//of understanding the goal at this point!!!
				context.setPropertyResolved(true);					
				Object o = ((ArchetypeNodeManager)base).getValueFromNode(property.toString());
				return o;
			}
			else//this is a base we are not resolving, but how can we reach this???? 
				return null;			
		}
		return null;
	}


	private ObservationArchetypeWrapper getTestWrapper() {
//		try {
//			  
//			  File f = new File("q:\\archetypes\\tony\\openEHR-EHR-OBSERVATION.SOAP_Investigations_SA.v3draft.adl");
//				ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
////			  ADLParser parser = new ADLParser(f);
////				Archetype arc = parser.parse();			
//				factory.loadFromFile(pFullFilePath)
//				ObservationArchetypeWrapper oaw = new ObservationArchetypeWrapper(arc);
//				//Object o = oaw.getRootWrapper().getTestInstance();		
//				return oaw;
//				
//			} catch (Exception e) {
//				System.out.println(e.toString());
//				return null;
//			}
		return null;
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property,
			Object value) {
		//TODO: VERY IMPORTANT: IF YOU ACCESS A BEAN (REQUEST BEAN TESTED FOR THE MOMENT) FROM THIS METHOD, YOU HAVE TO SET ITS VALUES WITHIN THIS RESOLVER. 
		//ANY BEAN TOUCHED IN THIS METHOD CAN'T BE SET BY NORMAL JSF RESOLVERS ANYMORE. SO EITHER DON'T TOUCH THE BEAN, OR MAKE SURE YOU SET THE VALUES
		//THROUGH THIS RESOLVER. IT IS ALL OR NOTHING.
//		if (base instanceof ArcheTypeManager && property.toString().equals("val"))
//			context.setPropertyResolved(true);
		//ArcheTypeManager aManager  = (ArcheTypeManager) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "archeTypeManager");
//		aManager.setVal("hadi bakalim");
		int a = 3;
	}

}

