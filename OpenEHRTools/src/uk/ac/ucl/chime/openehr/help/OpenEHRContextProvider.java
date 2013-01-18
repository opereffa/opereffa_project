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
package uk.ac.ucl.chime.openehr.help;

import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

public class OpenEHRContextProvider implements IContextProvider {
	private String _contextId;
	private IStructuredSelection _selection;
	
	public OpenEHRContextProvider(String pContextId, IStructuredSelection pStructuredSelection){
		_contextId = pContextId;
		_selection = pStructuredSelection;
	}
	
	@Override
	public IContext getContext(Object target) {
		if (_contextId == null || _selection == null)
			return null;
		IContext context = HelpSystem.getContext(_contextId);
		if (!_selection.isEmpty()) {
			context = new OpenEHRSelectionContext(context, _selection);
		}
		return context;
	}

	@Override
	public int getContextChangeMask() {
		return SELECTION;
	}

	@Override
	public String getSearchExpression(Object target) {
		// TODO Auto-generated method stub
		return null;
	}

}
