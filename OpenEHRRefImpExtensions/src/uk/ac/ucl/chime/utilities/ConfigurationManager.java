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
package uk.ac.ucl.chime.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import uk.ac.ucl.chime.wrappers.AnalysisElementWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;

public class ConfigurationManager {
	private static Properties properties = null;
	
	/*
	 * The scynchronized method ensures that loadprops is not called more than once,
	 * in the server deployment scenario, where other threads may try to call getrepositorypath before 
	 * loading of properties is complete. 
	 */
	public static  synchronized String getRepositoryPath() throws Exception{
		if (properties == null)
			new ConfigurationManager().loadProps();
		return properties.getProperty("opereffa.archetypeRepository");
	}
		
	
	/*
	 * this is also a synched method to avoid the problem explained in getRepositoryPath
	 */
	public static synchronized Class<? extends ElementWrapper> getElementWrapperType() throws Exception{
		if (properties == null)
			new ConfigurationManager().loadProps();
		if(properties.getProperty("opereffa.useAnalysisElementWrapper") == null)
			throw new Exception("No value found for opereffa.useAnalysisElementWrapper property");
		if(properties.getProperty("opereffa.useAnalysisElementWrapper").toLowerCase().equals("true"))
			return AnalysisElementWrapper.class;
		else if (properties.getProperty("opereffa.useAnalysisElementWrapper").toLowerCase().equals("false"))
			return ElementWrapper.class;
		else 
			throw new Exception("Unknown value found for opereffa.useAnalysisElementWrapper property");
	}
	
	private void loadProps() throws Exception{
		properties = new Properties();
		//FileInputStream in = new FileInputStream("opereffa.properties");
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("opereffa.properties");
		properties.load(in);
	}
}
