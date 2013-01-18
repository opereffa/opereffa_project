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

import java.util.HashMap;
import java.util.Map;

import org.openehr.build.RMObjectBuilder;
import org.openehr.build.SystemValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class BuilderFactory {
	public static RMObjectBuilder getNewBuilder(){
		return new BuilderFactory().getBuilder();
	}
	protected	RMObjectBuilder builder;
	public	static CodePhrase lang = new CodePhrase("ISO_639-1", "en");
	public static	CodePhrase charset = new CodePhrase("IANA_character-sets","UTF-8");
	protected	TerminologyService ts;
	protected	MeasurementService ms;
	protected	String rmTypeName;
	
	public RMObjectBuilder getBuilder(){
		return builder;
	}

	public BuilderFactory(){
		try {
			ts = SimpleTerminologyService.getInstance();
			ms = SimpleMeasurementService.getInstance();
			setUpBuilder();
		} catch (Exception e) {
			throw new RuntimeException(
					"failed to start terminology or measure service");
		}
	}
	
	protected void setUpBuilder() throws Exception {
		Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
		values.put(SystemValue.LANGUAGE, lang);
		values.put(SystemValue.CHARSET, charset);
		values.put(SystemValue.ENCODING, charset);
		values.put(SystemValue.TERMINOLOGY_SERVICE, ts);
		values.put(SystemValue.MEASUREMENT_SERVICE, ms);
		builder = new RMObjectBuilder(values);
	}

}
