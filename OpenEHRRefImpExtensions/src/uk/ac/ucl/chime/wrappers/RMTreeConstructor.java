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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import org.apache.commons.lang.NotImplementedException;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.build.SystemValue;
import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class RMTreeConstructor {
	//Internal copy of the passed in Archetype
	private Archetype _archetype;
	private RMObjectBuilder _builder;
	private JPanel _panelToFill;
	private int _verticalGap = 10;
	private int _lastY = 10;
	private int _lastX = 10;
	private int _defaultHeight = 25;
	
	protected static CodePhrase lang = new CodePhrase("ISO_639-1", "en");
	protected static CodePhrase charset = new CodePhrase("IANA_character-sets","UTF-8");
	protected static TerminologyService ts;
	protected static MeasurementService ms;

	static {
		try {
			ts = SimpleTerminologyService.getInstance();
			ms = SimpleMeasurementService.getInstance();
		} catch (Exception e) {
			throw new RuntimeException(
					"failed to start terminology or measure service");
		}
	}
	
	/*
	 * Construct a reference model based tree using the archetype passed in
	 * @param arc
	 */
	public RMTreeConstructor(Archetype arc, JPanel pnlToFill) throws Exception{
		_archetype = arc;
		_panelToFill = pnlToFill;		
	}
	
	private void addLabel(String lblContent){
		JLabel lblClusterName = new JLabel(lblContent);
		_panelToFill.add(lblClusterName );
		lblClusterName.setBounds(_lastX, _lastY, 250, _defaultHeight);
		_lastY += _defaultHeight + _verticalGap;
		_panelToFill.revalidate();
	}
	
	private void addTextBox(String content){
		JTextField fld = new JTextField();
		_panelToFill.add(fld);
		fld.setBounds(_lastX + 30 + 200, _lastY - ( _defaultHeight + _verticalGap), 150, _defaultHeight);
		fld.setText(content);
		_panelToFill.revalidate();
	}		
}
