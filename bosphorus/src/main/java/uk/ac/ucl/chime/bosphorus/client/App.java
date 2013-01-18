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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jboss.resteasy.client.ClientRequest;
import org.openehr.schemas.v1.ARCHETYPE;
import org.xml.sax.helpers.DefaultHandler;


import uk.ac.ucl.chime.bosphorus.messages.ArchetypesList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
//        	testXMLValidationFromFile();
//        	testRestServiceCall();
//        	testManualServiceCall();
        	testManualServiceCallForArchetype();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
    }
    

    
    private static void testManualServiceCall() {
		try{
			ClientRequest request = new ClientRequest("http://localhost:8080/bosphorus/resteasy/openehr/getarchetypeslistjson");
			

//			StringBuilder sb = new StringBuilder();
//			sb.append("<user id=\"0\">");
//			sb.append("   <username>Test User</username>");
//			sb.append("   <email>test.user@test.com</email>");
//			sb.append("</user>");
//
//
//			String xmltext = sb.toString();

			request.accept("application/json");//.body( MediaType.APPLICATION_XML, "");

			String response = request.getTarget( String.class); //get response and automatically unmarshall to a string.
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			ArchetypesList list = gson.fromJson(response, ArchetypesList.class);
//			String gsonString = gson.toJson(list);
//			System.out.println(gsonString);

			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
    
    private static void testManualServiceCallForArchetype() {
		try{
			ClientRequest request = new ClientRequest("http://localhost:8080/bosphorus/resteasy/openehr/getarchetypejson");
			

//			StringBuilder sb = new StringBuilder();
//			sb.append("<user id=\"0\">");
//			sb.append("   <username>Test User</username>");
//			sb.append("   <email>test.user@test.com</email>");
//			sb.append("</user>");
//
//
//			String xmltext = sb.toString();

			request.accept("application/json").queryParameter("archetypeName", "openEHR-EHR-CLUSTER.address.v1");//.body( MediaType.APPLICATION_XML, "");

			String response = request.getTarget( String.class); //get response and automatically unmarshall to a string.
			
			String formatted = new org.json.JSONObject(response).toString(2); 

			System.out.println(formatted);

			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}



	private static void testRestServiceCall() {
    	IBosphorusClient client = BosphorusClientFactory.getInstance();
    	ArchetypesList list = client.getArchetypesList();
		int i = 0;
    	
    		Iterator<String> namesIterator = list.getName().iterator();
    		while(namesIterator.hasNext()){
        		String name = namesIterator.next();
//          		System.out.println("calling parser for: " + name);
//        		ARCHETYPE archetype = client.getArchetype(name);
        		JAXBElement<org.openehr.schemas.v1.ARCHETYPE> archetypeElement = client.getArchetype(name + "salkfdjalkf");
        		if(archetypeElement == null)
           			System.out.println("call returned null for " + name + "\n");

//        		ARCHETYPE archetype = archetypeElement.getValue();
          		
//           		validateJAXBObject(archetype);
        	}
	}



	private static void testXMLValidationFromFile() throws Exception{
    	
    	JAXBContext context = JAXBContext.newInstance("org.openehr.schemas.v1");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File("xsd/Openehrprofile.xsd"));
		unmarshaller.setSchema(schema);
		
//		ARCHETYPE archetype = ( (JAXBElement<org.openehr.schemas.v1.ARCHETYPE>) unmarshaller.unmarshal(new File("xml/getarchetype.xml"))).getValue();
//		ARCHETYPE archetype = ( (JAXBElement<org.openehr.schemas.v1.ARCHETYPE>) unmarshaller.unmarshal(new File("C:/tmp/openEHR-EHR-OBSERVATION.blood_pressure.v1.xml"))).getValue();
		Object o = unmarshaller.unmarshal(new File("C:/tmp/openEHR-EHR-OBSERVATION.blood_pressure.v1.xml"));
				
//		System.out.println(archetype.getArchetypeId().getValue());
		System.out.println("Done");
    }
    
    private static void validateJAXBObject(ARCHETYPE pArchetype){
    	try {
    		JAXBContext context = JAXBContext.newInstance("org.openehr.schemas.v1");
        	Marshaller marshaller = context.createMarshaller();
        	SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
    		Schema schema = sf.newSchema(new File("xsd/Openehrprofile.xsd"));
    		marshaller.setSchema(schema);
    		
    		marshaller.marshal(pArchetype, new File("xml_output/" + pArchetype.getArchetypeId().getValue() + ".xml"));
    		System.out.println("Validation succesfull for " + pArchetype.getArchetypeId().getValue());
		} catch (Exception e) {
			System.out.println("Validation failed for " + pArchetype.getArchetypeId().getValue());
			System.out.println(e.toString());
		}
		
    }
}
