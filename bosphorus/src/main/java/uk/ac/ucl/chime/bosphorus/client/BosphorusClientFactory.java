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
package uk.ac.ucl.chime.bosphorus.client;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class BosphorusClientFactory {
	
	private static IBosphorusClient _instance;
	
	public static IBosphorusClient getInstance(){
		
		if(_instance == null){
			RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
			_instance = ProxyFactory.create(IBosphorusClient.class, "http://localhost/bosphorus/resteasy/openehr");
		}
		return _instance;
	}
	
	private BosphorusClientFactory(){}

	

}
