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
package uk.ac.ucl.chime.service.bosphorus;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jboss.resteasy.plugins.providers.DataSourceProvider;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.zeromq.ZMQ;

import uk.ac.ucl.chime.bosphorus.messages.ArchetypesList;
import uk.ac.ucl.chime.mappers.ArchetypeMapper;

import bosphorus.Aom.ARCHETYPE;
import bosphorus.Aom.ArchetypeList;
import bosphorus.Aom.Parameters;
import bosphorus.Aom.RequestHeader;
import bosphorus.Aom.ResponseHeader;
import bosphorus.Aom.ResponseHeader.Status;
import bosphoruspojos.TestChild;

import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;





@Path("openehr")
public class BosphorusRestService {
		
    public BosphorusRestService(){    	
    }
    
    @GET
    @Path("gettestchild")
    @Produces("application/xml")
    public TestChild getTestChild(){
    	TestChild c = new TestChild();
    	c.setSomeVal("val that goes to the parent");
    	c.setChildVar("val that goes to the child");
    	return c;
    }
    
    @POST
    @Path("settestchild")
    @Produces("application/xml")
    @Consumes("application/xml")
    public String setTestChild(TestChild pChild){
    	System.out.println(pChild.getSomeVal());
    	return "done";
    	
    }
    
    @GET
    @Path("getarchetypebyname")
    @Produces("application/xml")
    public ARCHETYPE getArchetypeByName(@QueryParam("archetypename") String pArchetypeName){
//    	System.out.println(pArchetypeName);
    	return null;
    }
    
    
    //@QueryParam("archetypename")String pArchetypeName
    
    @GET
    @Path("getarchetypeslist")
    @Produces("application/xml")
    public ArchetypesList getArchetypesList(){
    	DataSourceProvider dp = new DataSourceProvider();
//    	System.out.println("ds provider created successfully");
    	return getArchetypesListJAXB();
    }
    
    @GET
    @Path("getarchetypeslistyaml")
    @Produces("text/x-yaml")
    public ArchetypesList getArchetypesListyaml(){
    	return getArchetypesListJAXB();
    }
    
    @GET
    @Path("testyaml")
    @Produces("text/x-yaml")
    public ArchetypesList testyaml(){
    	return getArchetypesListJAXB();

    }
    
    @GET
    @Path("getarchetypeslistjson")
    @Produces("application/json")
    public ArchetypesList getArchetypesListJson(){
    	return getArchetypesListJAXB();
    }
    
    private ArchetypesList getArchetypesListJAXB(){
    	try{
    		ZMQ.Socket s = ThreadLocalZMQContext.getSocket();
            
            RequestHeader.Builder bld = RequestHeader.newBuilder();
            bld.setUrl("get_archetype_list");
            RequestHeader hd = bld.build();        
            byte[] requestHeader = hd.toByteArray();
          //empty parameter
            Parameters.Builder paramsBld = Parameters.newBuilder();
            byte[] parameters = paramsBld.build().toByteArray();
            
            s.send(requestHeader, ZMQ.SNDMORE);        
            s.send(parameters, 0);
            
            //multipart, so receive both before processing
            byte[] respHeader = s.recv(0);
            byte[] archList = s.recv(0);
            
            ResponseHeader.Builder responseBuilder = ResponseHeader.newBuilder();
            
            ResponseHeader responseHeader = responseBuilder.mergeFrom(respHeader).build();
            if(responseHeader.getResponsestatus().getNumber() != Status.PB_OK_VALUE){
//	    		System.out.println(responseHeader.getContent());
	    		return null;
	    	}	    	
	    	
	        ArchetypeList.Builder objBuilder = ArchetypeList.newBuilder();
	    	ArchetypeList obj = objBuilder.mergeFrom(archList).build();
	    	
	    	bosphoruspojos.ArchetypeList pojoList = new bosphoruspojos.ArchetypeList();
	        ProtobufIOUtil.mergeFrom(obj.toByteArray(), pojoList, bosphoruspojos.ArchetypeList.getSchema());	        	    	

	    	Iterator<String> iterator = obj.getNamesList().iterator();
	    	ArchetypesList alist = new ArchetypesList();
	    	while(iterator.hasNext()){
	    		alist.getName().add(iterator.next());
	    	}
	    	return alist;
    	}
    	catch(Exception e){
//    		System.out.println("cought exception");
//    		e.printStackTrace();
    		return null;
    	}
    }
    
    private boolean archetypeExistsInRepo(String pArchetypeName){
    	ArchetypesList aList = getArchetypesList();
		Iterator<String> namesIterator = aList.getName().iterator();
		while(namesIterator.hasNext()){
			if(namesIterator.next().equals(pArchetypeName))
				return true;
		}
		return false;
    }
    
    @GET
    @Path("getarchetype")
    @Produces("application/xml")
    public org.openehr.schemas.v1.ARCHETYPE getArchetype(@QueryParam("archetypeName") String pArchetypeName){
    	return getArchetypeJAXB(pArchetypeName);
    }
    
    
    @GET
    @Path("getarchetypeyaml")
    @Produces("text/x-yaml")
    public org.openehr.schemas.v1.ARCHETYPE getArchetypeYaml(@QueryParam("archetypeName") String pArchetypeName){
    	return getArchetypeJAXB(pArchetypeName);
    }
    
    @GET
    @Path("getarchetypejson")
    @Produces("application/json")
    public org.openehr.schemas.v1.ARCHETYPE getArchetypeJson(@QueryParam("archetypeName") String pArchetypeName){
    	return getArchetypeJAXB(pArchetypeName);
    }
    
    private org.openehr.schemas.v1.ARCHETYPE getArchetypeJAXB(String pArchetypeName){
    	try{
    		//Check for archetype names which does not exist in the repository.
    		//for now, get all archetype names to check, in the future we'd better use a cache
    		if(!archetypeExistsInRepo(pArchetypeName))
    			return null;
    		
    		
    		ZMQ.Socket s = ThreadLocalZMQContext.getSocket();
    		
            
//    		ZMQ.Socket s = ThreadLocalZMQContext.getSocket();
//    		String bindTo = ThreadLocalZMQContext.getTargetAdr();
//    		s.connect(bindTo);
            
            RequestHeader.Builder bld = RequestHeader.newBuilder();
            bld.setUrl("get_archetype");
            RequestHeader hd = bld.build();        
            byte[] requestHeader = hd.toByteArray();
          //empty parameter
            Parameters.Builder paramsBld = Parameters.newBuilder();
//            paramsBld.addContent("openEHR-EHR-OBSERVATION.blood_pressure.v1");
            paramsBld.addContent(pArchetypeName);
            byte[] parameters = paramsBld.build().toByteArray();
            
            s.send(requestHeader, ZMQ.SNDMORE);        
            s.send(parameters, 0);
            
            //multipart, so receive both before processing
            byte[] respHeader = s.recv(0);
            byte[] archList = s.recv(0);
            
            ResponseHeader.Builder responseBuilder = ResponseHeader.newBuilder();
            
            ResponseHeader responseHeader = responseBuilder.mergeFrom(respHeader).build();
            if(responseHeader.getResponsestatus().getNumber() != Status.PB_OK_VALUE){
	    		System.out.println(responseHeader.getContent());
	    		return null;
	    	}	    	
	    	
//            bosphorus.Aom.CCOMPLEXOBJECT.Builder ccompb = bosphorus.Aom.CCOMPLEXOBJECT.newBuilder();
//            bosphorus.Aom.CCOMPLEXOBJECT resultCcompx = ccompb.mergeFrom(archList).build();
            
            ARCHETYPE.Builder archBuilder = ARCHETYPE.newBuilder();
//            System.out.println("Attempting PB merge for archetype: " + pArchetypeName);
            ARCHETYPE archetype = archBuilder.mergeFrom(archList).build();
//            archetype.writeTo(new FileOutputStream("c:\\tmp\\archetypepb"));
            
//            bosphorus.ResponseHeader respHeaderPojo = new bosphorus.ResponseHeader();
//            ProtobufIOUtil.mergeFrom(respHeader, respHeaderPojo, bosphorus.ResponseHeader.getSchema());
            
            //using protostuff pojo for easier viewing values...
//            bosphorus.ARCHETYPE debugArc  = new bosphorus.ARCHETYPE();
//            ProtobufIOUtil.mergeFrom(archList, debugArc, bosphorus.ARCHETYPE.getSchema());
            
            //now create xsd object using mapper
            ArchetypeMapper mapper = new ArchetypeMapper();
            org.openehr.schemas.v1.ARCHETYPE xmlArchetype = mapper.copyArchetype(archetype);
                            		    		
            
//            CComplexObjectWrapper wrapper = new CComplexObjectWrapper(resultCcompx);
//        	IPBObjectVisitor visitor = new PBObjectVisitor();
//        	wrapper.accept(visitor);
//        	CCOMPLEXOBJECT ccomplexXML = (CCOMPLEXOBJECT)visitor.getTopOfStack();
        	
//          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//          
//          JAXBContext jc = JAXBContext.newInstance("org.openehr.schemas.v1");
          

        //Create marshaller
//        Marshaller m = ThreadLocalZMQContext.getMarshaller();
//        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //Marshal object into file.
//        m.marshal(ccomplexXML, byteArrayOutputStream);

        	
//	        bosphorus.Aom.CComplexObject.Builder objBuilder = bosphorus.Aom.CComplexObject.newBuilder();
//	        bosphorus.Aom.CComplexObject obj = objBuilder.mergeFrom(archList).build();
//	    	System.out.println(resultCcompx.getPath());
	    	
	    		        	    	
//	    	s.close();	 
//	    	ctx = null;
//	    	return byteArrayOutputStream.toString("UTF-8");
        	return xmlArchetype;
//        return "done";
    	}
    	catch(Exception e){
    		System.out.println("cought exception");
    		e.printStackTrace();
    		return null;
    	}
    }
}
