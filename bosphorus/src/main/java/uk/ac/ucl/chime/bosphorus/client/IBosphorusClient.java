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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBElement;

import org.openehr.schemas.v1.ARCHETYPE;

import uk.ac.ucl.chime.bosphorus.messages.ArchetypesList;

public interface IBosphorusClient {
	
	@GET
	@Path("getarchetype")
	@Produces("application/xml")
	String getBloodPressure();	
	
	
	@GET
    @Path("getarchetypeslist")
    @Produces("application/xml")
    public ArchetypesList getArchetypesList();
	
	@GET
    @Path("getarchetype")
    @Produces("application/xml")
    public JAXBElement<org.openehr.schemas.v1.ARCHETYPE> getArchetype(@QueryParam("archetypeName") String pArchetypeName);
}
