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
package uk.ac.ucl.chime.opereffa.termservice.rest;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
/* 
 * author:      "Seref Arikan s.arikan@chime.ucl.ac.uk"
 * license:     "See notice at bottom of class"
 */
import javax.xml.bind.Marshaller;

import org.LexGrid.LexBIG.DataModel.Collections.ModuleDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.SortOptionList;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.ModuleDescription;
import org.LexGrid.LexBIG.Impl.LexBIGServiceImpl;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.Iterators.ResolvedConceptReferencesIterator;
import org.LexGrid.commonTypes.Property;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.jboss.resteasy.plugins.providers.jaxb.json.BadgerContext;
import org.jboss.resteasy.plugins.providers.jaxb.json.JettisonMappedContext;

import uk.ac.ucl.chime.opereffa.termdata.TerminologyItem;
import uk.ac.ucl.chime.opereffa.termdata.TerminologyItemList;

@Path("terminologyrestservice")
public class TerminologyRestService {
	
	private static String ANOTOMICAL_AREA = "anotomicalarea";
	private static String DIAGNOSIS_CODE = "diagnosiscode";
	private static String SNOMED_PREFERRED_NAME = "snomedpreferredname";
	private static String SNOMED_TERM = "snomedterm";
	
	public TerminologyRestService(){
		
	}
	
	@GET
	@Path("codes/badger")
	@Produces("application/json")
	@BadgerFish   
	public TerminologyItemList getTermItemListBadger(@QueryParam("keyword") String pKeyword)
	{
		return getList(pKeyword);
	}

    @GET
    @Path("codes/mapped")
    @Produces("application/json")
	public TerminologyItemList getTermItemListMapped(@QueryParam("keyword") String pKeyword ){
		return getList(pKeyword);
	}
        
    
    private TerminologyItemList getList(String pKeyword){
    	try{
    		if (pKeyword != null && pKeyword != ""){
    			pKeyword = pKeyword.replaceAll("/", "");
    		}
    		ArrayList<TerminologyItem> list = new ArrayList<TerminologyItem>();
    		
    		LexBIGService lbService = LexBIGServiceImpl.defaultInstance();
    		ModuleDescriptionList mdl =  lbService.getMatchAlgorithms();
    		ModuleDescription[] mdlArr = mdl.getModuleDescription();
    		for (ModuleDescription md : mdlArr){
    			System.out.println(md.getName());
    		}
			System.out.println(lbService.getMatchAlgorithms().getModuleDescriptionCount());			
			CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
			csvt.setVersion("1.0");
			CodedNodeSet cns = lbService.getCodingSchemeConcepts("EmergencyCodes", csvt);
			//cns.restrictToMatchingProperties(Constructors.createLocalNameList("snomedpreferredname"), new CodedNodeSet.PropertyType[]{} , pKeyword, "LuceneQuery", "");
			cns.restrictToMatchingProperties(Constructors.createLocalNameList("snomedpreferredname"), new CodedNodeSet.PropertyType[]{} , pKeyword, "contains", "");
			SortOptionList sortCriteria = Constructors.createSortOptionList(new String[]{"matchToQuery", "code" });			
//			ResolvedConceptReferenceList list = cns.resolveToList(sortCriteria,null,null, 0);
//			System.out.println(list.getResolvedConceptReferenceCount());
//			Iterator<ResolvedConceptReference> rcrIterator =  list.iterateResolvedConceptReference();
			ResolvedConceptReferencesIterator rcrIterator = cns.resolve(sortCriteria, null, null);
			while(rcrIterator.hasNext()){
				ResolvedConceptReference conceptReference = rcrIterator.next();
				String anotomicalArea = null;
				String diagnosisCode = null;
				String snomedPreferredName = null;
				ArrayList<String> snomedTerms = new ArrayList<String>();
				Property[] props = conceptReference.getEntity().getAllProperties();
				for(Property prop : props){
					if(prop.getPropertyName().equals(this.ANOTOMICAL_AREA)){
						anotomicalArea =  prop.getValue().getContent();
					}
					else if(prop.getPropertyName().equals(this.DIAGNOSIS_CODE)){
						diagnosisCode =  prop.getValue().getContent();
					}
					else if(prop.getPropertyName().equals(this.SNOMED_PREFERRED_NAME)){
						snomedPreferredName =  prop.getValue().getContent();
					}
					else if(prop.getPropertyName().equals(this.SNOMED_TERM)){
						snomedTerms.add(prop.getValue().getContent());
					}
				}
				TerminologyItem tItem = new TerminologyItem(conceptReference.getCode(), conceptReference.getCode(), anotomicalArea, 
										diagnosisCode, snomedPreferredName, snomedTerms);
				org.LexGrid.commonTypes.Text t = conceptReference.getEntity().getProperty(0).getValue();
				System.out.println(conceptReference.getCode() + " : " + 
						conceptReference.getEntity().getProperty(1).getPropertyName() + " : " + 
						conceptReference.getEntity().getProperty(1).getValue().getContent());
				list.add(tItem);
			}
			return new TerminologyItemList(list);
    	}
    	catch(Exception ex){
    		System.out.println(ex.toString());
    		return null;
    	}    	    							
    }	
}


