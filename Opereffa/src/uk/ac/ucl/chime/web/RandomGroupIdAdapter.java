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

import gov.nih.nci.cagrid.gums.client.GetGridProxy;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.hssf.record.formula.Ptg;

import uk.ac.ucl.chime.utilities.TextValueInfo;
import uk.ac.ucl.chime.utils.RMDataTypeAdapter;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;

/*
 * This class descends from RMDataTypeAdapter to use its syntax resolving mechanism
 * it is not an adapter for a data type operation, instead it provides access to a groupId
 * using the node path as key. so same nodeId from a set of components gets back a random guid
 * everytime the request level bean is initialized. this is not a nice trick, but JSF does not leave much choice in this case.
 * 
 */
public class RandomGroupIdAdapter extends RMDataTypeAdapter {
	
	public RandomGroupIdAdapter(ArchetypeWrapper pArchetypeWrapper) {
		archetypeWrapper = pArchetypeWrapper;
	}

	@Override
	protected Object getValue() throws Exception {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	ConnectorBean connector = (ConnectorBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "connectorBean");
    	return connector.getGroupGUID(targetNodePath);
	}

	@Override
	protected void setValue(String nodePath, Object value) throws Exception {
		//simply ignore set value

	}

}

