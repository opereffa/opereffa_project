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
package uk.ac.ucl.chime.mappers;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.openehr.schemas.v1.ARCHETYPE;
import org.openehr.schemas.v1.ARCHETYPEID;
import org.openehr.schemas.v1.ARCHETYPEINTERNALREF;
import org.openehr.schemas.v1.ARCHETYPEONTOLOGY;
import org.openehr.schemas.v1.ARCHETYPESLOT;
import org.openehr.schemas.v1.AUTHOREDRESOURCE;
import org.openehr.schemas.v1.CARDINALITY;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CDATE;
import org.openehr.schemas.v1.CDATETIME;
import org.openehr.schemas.v1.CDEFINEDOBJECT;
import org.openehr.schemas.v1.CDOMAINTYPE;
import org.openehr.schemas.v1.CDURATION;
import org.openehr.schemas.v1.CINTEGER;
import org.openehr.schemas.v1.CMULTIPLEATTRIBUTE;
import org.openehr.schemas.v1.COBJECT;
import org.openehr.schemas.v1.CODEPHRASE;
import org.openehr.schemas.v1.CONSTRAINTREF;
import org.openehr.schemas.v1.CPRIMITIVE;
import org.openehr.schemas.v1.CPRIMITIVEOBJECT;
import org.openehr.schemas.v1.CREAL;
import org.openehr.schemas.v1.CSINGLEATTRIBUTE;
import org.openehr.schemas.v1.CSTRING;
import org.openehr.schemas.v1.CTIME;
import org.openehr.schemas.v1.DVAMOUNT;
import org.openehr.schemas.v1.DVCODEDTEXT;
import org.openehr.schemas.v1.DVCOUNT;
import org.openehr.schemas.v1.DVDATE;
import org.openehr.schemas.v1.DVDATETIME;
import org.openehr.schemas.v1.DVDURATION;
import org.openehr.schemas.v1.DVINTERVAL;
import org.openehr.schemas.v1.DVORDERED;
import org.openehr.schemas.v1.DVORDINAL;
import org.openehr.schemas.v1.DVPROPORTION;
import org.openehr.schemas.v1.DVQUANTITY;
import org.openehr.schemas.v1.DVTEMPORAL;
import org.openehr.schemas.v1.DVTEXT;
import org.openehr.schemas.v1.DVTIMESPECIFICATION;
import org.openehr.schemas.v1.DVURI;
import org.openehr.schemas.v1.EXPRBINARYOPERATOR;
import org.openehr.schemas.v1.EXPRITEM;
import org.openehr.schemas.v1.EXPRLEAF;
import org.openehr.schemas.v1.EXPRUNARYOPERATOR;
import org.openehr.schemas.v1.GENERICID;
import org.openehr.schemas.v1.HIEROBJECTID;
import org.openehr.schemas.v1.IntervalOfDate;
import org.openehr.schemas.v1.IntervalOfDateTime;
import org.openehr.schemas.v1.IntervalOfDuration;
import org.openehr.schemas.v1.IntervalOfInteger;
import org.openehr.schemas.v1.IntervalOfReal;
import org.openehr.schemas.v1.IntervalOfTime;
import org.openehr.schemas.v1.OBJECTID;
import org.openehr.schemas.v1.OBJECTVERSIONID;
import org.openehr.schemas.v1.PARTYPROXY;
import org.openehr.schemas.v1.PARTYRELATED;
import org.openehr.schemas.v1.PARTYSELF;
import org.openehr.schemas.v1.REFERENCERANGE;
import org.openehr.schemas.v1.RESOURCEDESCRIPTION;
import org.openehr.schemas.v1.REVISIONHISTORY;
import org.openehr.schemas.v1.TEMPLATEID;
import org.openehr.schemas.v1.TERMINOLOGYID;
import org.openehr.schemas.v1.TRANSLATIONDETAILS;

import bosphorus.Aom.ARCHETYPETERM;
import bosphorus.Aom.ASSERTION;
import bosphorus.Aom.ASSERTIONVARIABLE;
import bosphorus.Aom.AUDITDETAILS;
import bosphorus.Aom.CATTRIBUTEALTERNATIVES;
import bosphorus.Aom.CBOOLEAN;
import bosphorus.Aom.CCODEPHRASE;
import bosphorus.Aom.CDVORDINAL;
import bosphorus.Aom.CDVQUANTITY;
import bosphorus.Aom.COBJECTALTERNATIVES;
import bosphorus.Aom.CONSTRAINTBINDINGITEM;
import bosphorus.Aom.CPRIMITIVEALTERNATIVES;
import bosphorus.Aom.CQUANTITYITEM;
import bosphorus.Aom.RAWDATA;
import bosphorus.Aom.CSTRING.Builder;
import bosphorus.Aom.CodeDefinitionSet;
import bosphorus.Aom.ConstraintBindingSet;
import bosphorus.Aom.DVIDENTIFIER;
import bosphorus.Aom.DVORDEREDALTERNATIVES;
import bosphorus.Aom.DVTIME;
import bosphorus.Aom.EXPRITEMALTERNATIVES;
import bosphorus.Aom.PARTYPROXYALTERNATIVES;
import bosphorus.Aom.RESOURCEDESCRIPTIONITEM;
import bosphorus.Aom.REVISIONHISTORYITEM;
import bosphorus.Aom.StringDictionaryItem;
import bosphorus.Aom.TERMBINDINGITEM;
import bosphorus.Aom.TERMMAPPING;
import bosphorus.Aom.TermBindingSet;
import bosphorus.OBJECTIDALTERNATIVES;
import bosphorus.PARTYIDENTIFIED;
import bosphorus.PARTYREF;

/*
 * This is a simple class to copy values from protocol buffer objects
 * to XSD generated objects. it uses simplest copy method possible, so 
 * it creates references to some of its values, such as String instances. 
 * This may lead to instances of this class not being garbage collected
 * until the xsd generated class instance is ready for GC. In the context of REST 
 * based web services this should be OK, but for more complex logic and longer
 * life cycle, a better copier which creates new instances using the values of 
 * the protocol buffer class instances should be used.
 */
public class ArchetypeMapper {
	
	public ARCHETYPE copyArchetype(bosphorus.Aom.ARCHETYPE pArchetype){
		ARCHETYPE archetype = new ARCHETYPE();
		
		//adlversion
		if(pArchetype.hasAdlversion())
			archetype.setAdlVersion(pArchetype.getAdlversion());
		//archetypeid
		if(pArchetype.hasArchetypeid())
			archetype.setArchetypeId(copyArchetypeId(pArchetype.getArchetypeid()));		
		//concept
		if(pArchetype.hasConcept())
			archetype.setConcept(pArchetype.getConcept());
		//definition
		if(pArchetype.hasDefinition())
			archetype.setDefinition(copyCComplexObject(pArchetype.getDefinition()));		
		//description
		if(pArchetype.hasDescription())
			archetype.setDescription(copyResourceDescription(pArchetype.getDescription()));		
		//invariants
		for(ASSERTION assertion : pArchetype.getInvariantsList())
			archetype.getInvariants().add(copyAssertion(assertion));
		//iscontrolled
		if(pArchetype.hasIscontrolled())
			archetype.setIsControlled(pArchetype.getIscontrolled());
		//ontology
		if(pArchetype.hasOntology()){
			ARCHETYPEONTOLOGY ao = copyArchetypeOntology(pArchetype.getOntology());
			archetype.setOntology(ao);
		}
		//originallanguage
		if(pArchetype.hasOriginallanguage())
			archetype.setOriginalLanguage(copyCodePhrase(pArchetype.getOriginallanguage()));
		//parentarchetypeid
		if(pArchetype.hasParentarchetypeid())
			archetype.setParentArchetypeId(copyArchetypeId(pArchetype.getParentarchetypeid()));
		//revisionhistory
		if(pArchetype.hasRevisionhistory())
			archetype.setRevisionHistory(copyRevisionHistory(pArchetype.getRevisionhistory()));
		//translations
		for(bosphorus.Aom.TRANSLATIONDETAILS td : pArchetype.getTranslationsList())
			archetype.getTranslations().add(copyTranslationDetails(td));
		//uid
		if(pArchetype.hasUid())
			archetype.setUid(copyHierObjectID(pArchetype.getUid()));
		
		return archetype; 
	}

	private RESOURCEDESCRIPTION copyResourceDescription(bosphorus.Aom.RESOURCEDESCRIPTION description) {
		RESOURCEDESCRIPTION result = new RESOURCEDESCRIPTION();
		//details
		for(RESOURCEDESCRIPTIONITEM rdi : description.getDetailsList())
			result.getDetails().add(copyResourceDescriptionItem(rdi));
		//lifecyclestate
		if(description.hasLifecyclestate())
			result.setLifecycleState(description.getLifecyclestate());
		//originalauthor
		for(StringDictionaryItem sdi : description.getOriginalauthorList())
			result.getOriginalAuthor().add(copyStringDictionaryItem(sdi));
		//othercontributions
		for(String s : description.getOthercontributorsList())
			result.getOtherContributors().add(s);
		//otherdetails
		for(StringDictionaryItem sdi : description.getOtherdetailsList())
			result.getOtherDetails().add(copyStringDictionaryItem(sdi));
		//parentresource
		if(description.hasParentresource())
			result.setParentResource(copyAuthoredResource(description.getParentresource()));
		//resourcepackageuri
		return result;
	}

	private AUTHOREDRESOURCE copyAuthoredResource(bosphorus.Aom.AUTHOREDRESOURCE parentresource) {
		AUTHOREDRESOURCE result = new AUTHOREDRESOURCE();
		//description
		if(parentresource.hasDescription())
			result.setDescription(copyResourceDescription(parentresource.getDescription()));
		//iscontrolled
		if(parentresource.hasIscontrolled())
			result.setIsControlled(parentresource.getIscontrolled());
		//originallangauge
		if(parentresource.hasOriginallanguage())
			result.setOriginalLanguage(copyCodePhrase(parentresource.getOriginallanguage()));
		//revisionhistory
		if(parentresource.hasRevisionhistory())
			result.setRevisionHistory(copyRevisionHistory(parentresource.getRevisionhistory()));
		//translations
		for(bosphorus.Aom.TRANSLATIONDETAILS td : parentresource.getTranslationsList())
			result.getTranslations().add(copyTranslationDetails(td));
		
		return result;
	}

	private org.openehr.schemas.v1.RESOURCEDESCRIPTIONITEM copyResourceDescriptionItem(RESOURCEDESCRIPTIONITEM rdi) {
		org.openehr.schemas.v1.RESOURCEDESCRIPTIONITEM  result = new org.openehr.schemas.v1.RESOURCEDESCRIPTIONITEM();
		//copyright
		if(rdi.hasCopyright())
			result.setCopyright(rdi.getCopyright());
		//keywords
		for(String s: rdi.getKeywordsList())
			result.getKeywords().add(s);
		//language
		if(rdi.hasLanguage())
			result.setLanguage(copyCodePhrase(rdi.getLanguage()));
		//misuse
		if(rdi.hasMisuse())
			result.setMisuse(rdi.getMisuse());
		//originalresourceuri
		for(StringDictionaryItem sdi : rdi.getOriginalresourceuriList())
			result.getOriginalResourceUri().add(copyStringDictionaryItem(sdi));
		//otherdetails
		for(StringDictionaryItem sdi :  rdi.getOtherdetailsList())
			result.getOtherDetails().add(copyStringDictionaryItem(sdi));
		//purpose
		if(rdi.hasPurpose())
			result.setPurpose(rdi.getPurpose());
		//use
		if(rdi.hasUse())
			result.setUse(rdi.getUse());
		
		return result;
	}

	private TRANSLATIONDETAILS copyTranslationDetails(bosphorus.Aom.TRANSLATIONDETAILS td) {
		TRANSLATIONDETAILS result = new TRANSLATIONDETAILS();
		//accreditation
		if(td.hasAccreditation())
			result.setAccreditation(td.getAccreditation());
		//author
		for(StringDictionaryItem sdi : td.getAuthorList())
			result.getAuthor().add(copyStringDictionaryItem(sdi));
		//language
		if(td.hasLanguage())
			result.setLanguage(copyCodePhrase(td.getLanguage()));
		//otherdetails
		for(StringDictionaryItem sdi : td.getOtherdetailsList())
			result.getOtherDetails().add(copyStringDictionaryItem(sdi));
		
		return result;
	}

	private REVISIONHISTORY copyRevisionHistory(bosphorus.Aom.REVISIONHISTORY revisionhistory) {
		REVISIONHISTORY result = new REVISIONHISTORY();
		//items
		for(REVISIONHISTORYITEM hi : revisionhistory.getItemsList())
			result.getItems().add(copyRevisionHistoryItem(hi));
		
		return result;
	}

	private org.openehr.schemas.v1.REVISIONHISTORYITEM copyRevisionHistoryItem(REVISIONHISTORYITEM hi) {
		org.openehr.schemas.v1.REVISIONHISTORYITEM result = new org.openehr.schemas.v1.REVISIONHISTORYITEM();
		//audits
		for(AUDITDETAILS ad : hi.getAuditsList())
			result.getAudits().add(copyAuditDetails(ad));
		//versionId
		if(hi.hasVersionid())
			result.setVersionId(copyObjectVersionId(hi.getVersionid()));
		return result;
	}

	private org.openehr.schemas.v1.AUDITDETAILS copyAuditDetails(AUDITDETAILS ad) {
		org.openehr.schemas.v1.AUDITDETAILS result = new org.openehr.schemas.v1.AUDITDETAILS();
		//changetype
		if(ad.hasChangetype())
			result.setChangeType(copyDvCodedText(ad.getChangetype()));
		//committer
		if(ad.hasCommitter())
			result.setCommitter(copyPartyProxy(ad.getCommitter()));
		//description
		if(ad.hasDescription())
			result.setDescription(copyDvText(ad.getDescription()));
		//systemid
		if(ad.hasSystemid())
			result.setSystemId(ad.getSystemid());
		//timecommitted
		if(ad.hasTimecommitted())
			result.setTimeCommitted(copyDvDateTime(ad.getTimecommitted()));
		return result;
	}

	private DVDATETIME copyDvDateTime(bosphorus.Aom.DVDATETIME timecommitted) {
		DVDATETIME result = new DVDATETIME();
		//accuracy
		if(timecommitted.hasAccuracy())
			result.setAccuracy(copyDvDuration(timecommitted.getAccuracy()));
		//magnitudestatus
		if(timecommitted.hasMagnitudestatus())
			result.setMagnitudeStatus(timecommitted.getMagnitudestatus());
		//normalrange
		if(timecommitted.hasNormalrange())
			result.setNormalRange(copyDVInterval(timecommitted.getNormalrange()));
		//normalstatus
		if(timecommitted.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(timecommitted.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : timecommitted.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//value
		if(timecommitted.hasValue())
			result.setValue(timecommitted.getValue());
		
		return result;
	}

	private DVINTERVAL copyDVInterval(bosphorus.Aom.DVINTERVAL normalrange) {
		DVINTERVAL result = new DVINTERVAL();
		//lower
		if(normalrange.hasLower())
			result.setLower(copyDvOrdered(normalrange.getLower()));//todo: float represented as string
		//lowerincluded
		if(normalrange.hasLowerincluded())
			result.setLowerIncluded(normalrange.getLowerincluded());
		//upper
		if(normalrange.hasUpper())
			result.setUpper(copyDvOrdered(normalrange.getUpper()));
		//upperincluded
		if(normalrange.hasUpperincluded())
			result.setUpperIncluded(normalrange.getUpperincluded());
		//lowerunbounded
		if(normalrange.hasLowerunbounded())
			result.setLowerUnbounded(normalrange.getLowerunbounded());
		//upperunbounded
		if(normalrange.hasUpperunbounded())
			result.setUpperUnbounded(normalrange.getUpperunbounded());
		
		return result;
	}

	private DVORDERED copyDvOrdered(DVORDEREDALTERNATIVES lower) {
		DVORDERED result = null;
		if(lower.hasDvamountfield()){
			result = copyDvAmount(lower.getDvamountfield());
		}
		else if(lower.hasDvcountfield()){
			result = copyDvCount(lower.getDvcountfield());
		}
		else if(lower.hasDvdatefield()){
			result = copyDvDate(lower.getDvdatefield());
		}
		else if(lower.hasDvdatetimefield()){
			result = copyDvDateTime(lower.getDvdatetimefield());
		}
		else if(lower.hasDvdurationfield()){
			result = copyDvDuration(lower.getDvdurationfield());
		}
		else if(lower.hasDvordinalfield()){
			result = copyDvOrdinal(lower.getDvordinalfield());
		}
		else if(lower.hasDvproportionfield()){
			result = copyDvProportion(lower.getDvproportionfield());
		}
		else if(lower.hasDvquantityfield()){
			result = copyDvQuantity(lower.getDvquantityfield());
		}
		else if(lower.hasDvtemporalfield()){
			result = copyDvTemporal(lower.getDvtemporalfield());
		}
		else if(lower.hasDvtimefield()){
			result = copyDvTime(lower.getDvtimefield());
		}
		
		return result;
	}

	
	
private org.openehr.schemas.v1.DVTIME copyDvTime(DVTIME dvtimefield) {
		org.openehr.schemas.v1.DVTIME result = new org.openehr.schemas.v1.DVTIME();
		
		//accuracy
		if(dvtimefield.hasAccuracy())
			result.setAccuracy(copyDvDuration(dvtimefield.getAccuracy()));				
		//magnitudestatus
		if(dvtimefield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvtimefield.getMagnitudestatus());
		//normalrange
		if(dvtimefield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvtimefield.getNormalrange()));
		//normalstatus
		if(dvtimefield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvtimefield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvtimefield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//value
		if(dvtimefield.hasValue())
			result.setValue(dvtimefield.getValue());
		
		return result;
	}

private DVTEMPORAL copyDvTemporal(bosphorus.Aom.DVTEMPORAL dvtemporalfield) {
		DVTEMPORAL result = new DVTEMPORAL();
		//accuracy
		if(dvtemporalfield.hasAccuracy())
			result.setAccuracy(copyDvDuration(dvtemporalfield.getAccuracy()));				
		//magnitudestatus
		if(dvtemporalfield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvtemporalfield.getMagnitudestatus());
		//normalrange
		if(dvtemporalfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvtemporalfield.getNormalrange()));
		//normalstatus
		if(dvtemporalfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvtemporalfield.getNormalstatus()));
		
		return result;
	}

	private DVQUANTITY copyDvQuantity(bosphorus.Aom.DVQUANTITY dvquantityfield) {
		DVQUANTITY result = new DVQUANTITY();
		//accuracy
		if(dvquantityfield.hasAccuracy())
			result.setAccuracy(new Float(dvquantityfield.getAccuracy()));//todo: float represented with string
		//accuracyispercent
		if(dvquantityfield.hasAccuracyispercent())
			result.setAccuracyIsPercent(dvquantityfield.getAccuracyispercent());
		//magnitude
		if(dvquantityfield.hasMagnitude())
			result.setMagnitude(Double.valueOf(dvquantityfield.getMagnitude()));//todo: double represented as string		
		//magnitudestatus
		if(dvquantityfield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvquantityfield.getMagnitudestatus());
		//normalrange
		if(dvquantityfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvquantityfield.getNormalrange()));
		//normalstatus
		if(dvquantityfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvquantityfield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvquantityfield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//precision
		if(dvquantityfield.hasPrecision())
			result.setPrecision(dvquantityfield.getPrecision());
		//units
		if(dvquantityfield.hasUnits())
			result.setUnits(dvquantityfield.getUnits());
		
		return result;
	}

	private DVPROPORTION copyDvProportion(bosphorus.Aom.DVPROPORTION dvproportionfield) {
		DVPROPORTION  result = new DVPROPORTION();
		
		//accuracy
		if(dvproportionfield.hasAccuracy())
			result.setAccuracy(new Float(dvproportionfield.getAccuracy()));//todo: float represented with string
		//accuracyispercent
		if(dvproportionfield.hasAccuracyispercent())
			result.setAccuracyIsPercent(dvproportionfield.getAccuracyispercent());
		//denominator
		if(dvproportionfield.hasDenominator())
			result.setDenominator(new Float(dvproportionfield.getDenominator()));//todo: float represented with string
		//magnitudestatus
		if(dvproportionfield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvproportionfield.getMagnitudestatus());
		//normalrange
		if(dvproportionfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvproportionfield.getNormalrange()));
		//normalstatus
		if(dvproportionfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvproportionfield.getNormalstatus()));
		//numerator
		if(dvproportionfield.hasNumerator())
			result.setNumerator(new Float(dvproportionfield.getNumerator()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvproportionfield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//precision
		if(dvproportionfield.hasPrecision())
			result.setPrecision(dvproportionfield.getPrecision());
		//type
		if(dvproportionfield.hasType())
			result.setType(ProportionKindMapper.getProportionKind(dvproportionfield.getType()));
		
		return result;
	}

	private DVORDINAL copyDvOrdinal(bosphorus.Aom.DVORDINAL dvordinalfield) {
		DVORDINAL result = new DVORDINAL();
		//normalrange
		if(dvordinalfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvordinalfield.getNormalrange()));
		//normalstatus
		if(dvordinalfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvordinalfield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvordinalfield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//symbol
		if(dvordinalfield.hasSymbol())
			result.setSymbol(copyDvCodedText(dvordinalfield.getSymbol()));
		//value
		if(dvordinalfield.hasValue())
			result.setValue(dvordinalfield.getValue());
		return result;
	}

	private DVDATE copyDvDate(bosphorus.Aom.DVDATE dvdatefield) {
		DVDATE result = new DVDATE();		
		//accuracy
		if(dvdatefield.hasAccuracy())
			result.setAccuracy(copyDvDuration(dvdatefield.getAccuracy()));
		//magnitudestatus
		if(dvdatefield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvdatefield.getMagnitudestatus());
		//normalrange
		if(dvdatefield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvdatefield.getNormalrange()));
		//normalstatus
		if(dvdatefield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvdatefield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvdatefield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		//value
		if(dvdatefield.hasValue())
			result.setValue(dvdatefield.getValue());
		
		return result;
	}

	private DVCOUNT copyDvCount(bosphorus.Aom.DVCOUNT dvcountfield) {
		DVCOUNT  result = new DVCOUNT();

		//accuracy
		if(dvcountfield.hasAccuracy())
			result.setAccuracy(new Float(dvcountfield.getAccuracy()));//todo: float represented with string
		//accuracyispercent
		if(dvcountfield.hasAccuracyispercent())
			result.setAccuracyIsPercent(dvcountfield.getAccuracyispercent());
		//magnitude
		if(dvcountfield.hasMagnitude())
			result.setMagnitude(dvcountfield.getMagnitude());// int used to represent long
		//magnitudestatus
		if(dvcountfield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvcountfield.getMagnitudestatus());
		//normalrange
		if(dvcountfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvcountfield.getNormalrange()));
		//normalstatus
		if(dvcountfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvcountfield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvcountfield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		
		return result;
	}

	private DVAMOUNT copyDvAmount(bosphorus.Aom.DVAMOUNT dvamountfield) {
		DVAMOUNT result = new DVAMOUNT();
		//accuracy
		if(dvamountfield.hasAccuracy())
			result.setAccuracy(new Float(dvamountfield.getAccuracy()));//todo: float represented with string
		//accuracyispercent
		if(dvamountfield.hasAccuracyispercent())
			result.setAccuracyIsPercent(dvamountfield.getAccuracyispercent());
		//magnitudestatus
		if(dvamountfield.hasMagnitudestatus())
			result.setMagnitudeStatus(dvamountfield.getMagnitudestatus());
		//normalrange
		if(dvamountfield.hasNormalrange())
			result.setNormalRange(copyDVInterval(dvamountfield.getNormalrange()));
		//normalstatus
		if(dvamountfield.hasNormalstatus())
			result.setNormalStatus(copyCodePhrase(dvamountfield.getNormalstatus()));
		//otherreferenceranges
		for(bosphorus.Aom.REFERENCERANGE refr : dvamountfield.getOtherreferencerangesList())
			result.getOtherReferenceRanges().add(copyReferenceRange(refr));
		return result;
	}

	private REFERENCERANGE copyReferenceRange(bosphorus.Aom.REFERENCERANGE refr) {
		REFERENCERANGE result = new REFERENCERANGE();
		//meaning
		if(refr.hasMeaning())
			result.setMeaning(copyDvText(refr.getMeaning()));
		//range
		if(refr.hasRange())
			result.setRange(copyDVInterval(refr.getRange()));
		
		return result;
	}

	private DVDURATION copyDvDuration(bosphorus.Aom.DVDURATION accuracy) {
		DVDURATION result = new DVDURATION();
		
		return result;
	}

	private DVTEXT copyDvText(bosphorus.Aom.DVTEXT description) {
		DVTEXT result = new DVTEXT();
		//encoding
		if(description.hasEncoding())
			result.setEncoding(copyCodePhrase(description.getEncoding()));
		//formatting
		if(description.hasFormatting())
			result.setFormatting(description.getFormatting());		
		//hyperlink
		if(description.hasHyperlink())
			result.setHyperlink(copyDvUri(description.getHyperlink()));
		//language
		if(description.hasLanguage())
			result.setLanguage(copyCodePhrase(description.getLanguage()));
		//mappings
		for(TERMMAPPING tm :  description.getMappingsList())
			result.getMappings().add(copyTermMapping(tm));
		//value
		if(description.hasValue())
			result.setValue(description.getValue());
		
		return result;
	}

	private PARTYPROXY copyPartyProxy(PARTYPROXYALTERNATIVES committer) {
		PARTYPROXY result = null;
		if(committer.hasPartyidentifiedfield()){
			result = copyPartyIdentified(committer.getPartyidentifiedfield());
		}
		else if(committer.hasPartyrelatedfield()){
			result = copyPartyRelated(committer.getPartyrelatedfield());
		}
		else if(committer.hasPartyselffield()){
			result = copyPartySelf(committer.getPartyselffield());
		}
		
		return result;
	}

private PARTYSELF copyPartySelf(bosphorus.Aom.PARTYSELF partyselffield) {
		PARTYSELF result = new PARTYSELF();
		if(partyselffield.hasExternalref())
			result.setExternalRef(copyPartyRef(partyselffield.getExternalref()));
		
		return result;
	}

private PARTYRELATED copyPartyRelated(bosphorus.Aom.PARTYRELATED partyrelatedfield) {
		PARTYRELATED result = new PARTYRELATED();
		//externalref
		if(partyrelatedfield.hasExternalref())
			result.setExternalRef(copyPartyRef(partyrelatedfield.getExternalref()));
		//identifiers
		for(DVIDENTIFIER dvi : partyrelatedfield.getIdentifiersList())
			result.getIdentifiers().add(copyDvIdentifier(dvi));
		//name
		if(partyrelatedfield.hasName())
			result.setName(partyrelatedfield.getName());
		//relationship
		if(partyrelatedfield.hasRelationship())
			result.setRelationship(copyDvCodedText(partyrelatedfield.getRelationship()));
		
		return result;
	}

	private org.openehr.schemas.v1.PARTYIDENTIFIED copyPartyIdentified(bosphorus.Aom.PARTYIDENTIFIED partyidentifiedfield) {
		org.openehr.schemas.v1.PARTYIDENTIFIED result = new org.openehr.schemas.v1.PARTYIDENTIFIED();
		//externalref
		if(partyidentifiedfield.hasExternalref())
			result.setExternalRef(copyPartyRef(partyidentifiedfield.getExternalref()));
		//identifiers
		for(DVIDENTIFIER dvi : partyidentifiedfield.getIdentifiersList())
			result.getIdentifiers().add(copyDvIdentifier(dvi));
		//name
		if(partyidentifiedfield.hasName())
			result.setName(partyidentifiedfield.getName());
		
		return result;
	}

	private org.openehr.schemas.v1.DVIDENTIFIER copyDvIdentifier(DVIDENTIFIER dvi) {
		org.openehr.schemas.v1.DVIDENTIFIER result = new org.openehr.schemas.v1.DVIDENTIFIER();
		//assigner
		if(dvi.hasAssigner())
			result.setAssigner(dvi.getAssigner());
		//id
		if(dvi.hasId())
			result.setId(dvi.getId());
		//issuer
		if(dvi.hasIssuer())
			result.setIssuer(dvi.getIssuer());
		//type
		if(dvi.hasType())
			result.setType(dvi.getType());
		
		return result;
	}

	private org.openehr.schemas.v1.PARTYREF copyPartyRef(bosphorus.Aom.PARTYREF externalref) {
		org.openehr.schemas.v1.PARTYREF result = new org.openehr.schemas.v1.PARTYREF();
		//id
		if(externalref.hasId())
			result.setId(copyObjectId(externalref.getId()));
		//namespace
		if(externalref.hasNamespace())
			result.setNamespace(externalref.getNamespace());
		//type
		if(externalref.hasType())
			result.setType(externalref.getType());
		
		return result;
	}

	private OBJECTID copyObjectId(bosphorus.Aom.OBJECTIDALTERNATIVES id) {
		OBJECTID result = null;
		//value
		if(id.hasArchetypeidfield()){
			result = copyArchtypeId(id.getArchetypeidfield());
		}
		else if(id.hasGenericidfield()){
			result = copyGenericId(id.getGenericidfield());
		}
		else if(id.hasHierobjectidfield()){
			result = copyHierObjectID(id.getHierobjectidfield());
		}
		else if(id.hasObjectversionidfield()){
			result = copyObjectVersionId(id.getObjectversionidfield());
		}
		else if(id.hasTemplateidfield()){
			result = copyTemplateId(id.getTemplateidfield());
		}
		else if(id.hasTerminologyidfield()){
			result = copyTerminologyId(id.getTerminologyidfield());
		}
		
		return result;
	}

	private TEMPLATEID copyTemplateId(bosphorus.Aom.TEMPLATEID templateidfield) {
		TEMPLATEID result = new TEMPLATEID();
		//value
		if(templateidfield.hasValue())
			result.setValue(templateidfield.getValue());
		
		return result;
	}

	private OBJECTVERSIONID copyObjectVersionId(bosphorus.Aom.OBJECTVERSIONID objectversionidfield) {
		OBJECTVERSIONID result = new OBJECTVERSIONID();
		//value
		if(objectversionidfield.hasValue())
			result.setValue(objectversionidfield.getValue());
		
		return result;
	}

	private GENERICID copyGenericId(bosphorus.Aom.GENERICID genericidfield) {
		GENERICID result = new GENERICID();
		//scheme
		if(genericidfield.hasScheme())
			result.setScheme(result.getScheme());
		//value
		if(genericidfield.hasValue())
			result.setValue(genericidfield.getValue());
		
		return result;
	}

	private ARCHETYPEID copyArchtypeId(bosphorus.Aom.ARCHETYPEID archetypeidfield) {
		ARCHETYPEID result = new ARCHETYPEID();
		//value
		if(archetypeidfield.hasValue())
			result.setValue(archetypeidfield.getValue());
		
		return result;
	}

	private DVCODEDTEXT copyDvCodedText(bosphorus.Aom.DVCODEDTEXT changetype) {
		DVCODEDTEXT result = new DVCODEDTEXT();
		//definingcode
		if(changetype.hasDefiningcode())
			result.setDefiningCode(copyCodePhrase(changetype.getDefiningcode()));
		//encoding
		if(changetype.hasEncoding())
			result.setEncoding(copyCodePhrase(changetype.getEncoding()));
		//formatting
		if(changetype.hasFormatting())
			result.setFormatting(changetype.getFormatting());		
		//hyperlink
		if(changetype.hasHyperlink())
			result.setHyperlink(copyDvUri(changetype.getHyperlink()));
		//language
		if(changetype.hasLanguage())
			result.setLanguage(copyCodePhrase(changetype.getLanguage()));
		//mappings
		for(TERMMAPPING tm :  changetype.getMappingsList())
			result.getMappings().add(copyTermMapping(tm));
		//value
		if(changetype.hasValue())
			result.setValue(changetype.getValue());
		return result;
	}

	private org.openehr.schemas.v1.TERMMAPPING copyTermMapping(TERMMAPPING tm) {
		org.openehr.schemas.v1.TERMMAPPING result = new org.openehr.schemas.v1.TERMMAPPING();
		//match
		if(tm.hasMatch())
			result.setMatch(tm.getMatch());
		//purpose
		if(tm.hasPurpose())
			result.setPurpose(copyDvCodedText(tm.getPurpose()));
		//target
		if(tm.hasTarget())
			result.setTarget(copyCodePhrase(tm.getTarget()));
		
		return result;
	}

	private DVURI copyDvUri(bosphorus.Aom.DVURI hyperlink) {
		DVURI result = new DVURI();
		//value
		if(hyperlink.hasValue())
			result.setValue(hyperlink.getValue());
		
		return result;
	}

	private HIEROBJECTID copyHierObjectID(bosphorus.Aom.HIEROBJECTID uid) {
		HIEROBJECTID result = new HIEROBJECTID ();
		//value
		if(uid.hasValue())
			result.setValue(uid.getValue());
		return result;
	}

	private ARCHETYPEONTOLOGY copyArchetypeOntology(bosphorus.Aom.ARCHETYPEONTOLOGY ontology) {
		ARCHETYPEONTOLOGY ao = new ARCHETYPEONTOLOGY();
		//constraintbindings
		for(ConstraintBindingSet cbs : ontology.getConstraintbindingsList())
			ao.getConstraintBindings().add(copyConstraintBindingSet(cbs));
		//constraintdefinitions
		for(CodeDefinitionSet cds : ontology.getConstraintdefinitionsList())
			ao.getConstraintDefinitions().add(copyCodeDefinitionSet(cds));
		//termbindings
		for(TermBindingSet tbs : ontology.getTermbindingsList())
			ao.getTermBindings().add(copyTermBindingSet(tbs));
		//termdefinitions
		for(CodeDefinitionSet cds : ontology.getTermdefinitionsList())
			ao.getTermDefinitions().add(copyCodeDefinitionSet(cds));
		return ao;
	}

	private org.openehr.schemas.v1.TermBindingSet copyTermBindingSet(TermBindingSet tbs) {
		org.openehr.schemas.v1.TermBindingSet result = new org.openehr.schemas.v1.TermBindingSet();
		//items
		for(TERMBINDINGITEM tbitem :  tbs.getItemsList())
			result.getItems().add(copyTermBindingItem(tbitem));
		//terminology
		if(tbs.hasTerminology())
			result.setTerminology(tbs.getTerminology());
		
		return result;
	}

	private org.openehr.schemas.v1.TERMBINDINGITEM copyTermBindingItem(TERMBINDINGITEM tbitem) {
		org.openehr.schemas.v1.TERMBINDINGITEM  result = new org.openehr.schemas.v1.TERMBINDINGITEM ();
		//code
		if(tbitem.hasCode())
			result.setCode(tbitem.getCode());
		//value
		if(tbitem.hasValue())
			result.setValue(copyCodePhrase(tbitem.getValue()));
		
		return result;
	}

	private CODEPHRASE copyCodePhrase(bosphorus.Aom.CODEPHRASE value) {
		CODEPHRASE result = new CODEPHRASE();
		//codestring
		if(value.hasCodestring())
			result.setCodeString(value.getCodestring());
		//terminologyid
		if(value.hasTerminologyid())
			result.setTerminologyId(copyTerminologyId(value.getTerminologyid()));
		
		return result;
	}

	private TERMINOLOGYID copyTerminologyId(bosphorus.Aom.TERMINOLOGYID terminologyid) {
		TERMINOLOGYID result = new TERMINOLOGYID();
		if(terminologyid.hasValue())
			result.setValue(terminologyid.getValue());
		
		return result;
	}

	private org.openehr.schemas.v1.CodeDefinitionSet copyCodeDefinitionSet(CodeDefinitionSet cds) {
		org.openehr.schemas.v1.CodeDefinitionSet result = new org.openehr.schemas.v1.CodeDefinitionSet();
		//items
		for(ARCHETYPETERM at : cds.getItemsList())
			result.getItems().add(copyArchetypeTerm(at));
		//language
		if(cds.hasLanguage())
			result.setLanguage(cds.getLanguage());
		return result;
	}

	private org.openehr.schemas.v1.ARCHETYPETERM copyArchetypeTerm(ARCHETYPETERM at) {
		org.openehr.schemas.v1.ARCHETYPETERM result = new org.openehr.schemas.v1.ARCHETYPETERM();
		//code
		if(at.hasCode())
			result.setCode(at.getCode());
		//items
		for(StringDictionaryItem si : at.getItemsList())
			result.getItems().add(copyStringDictionaryItem(si));
		return result;
	}

	private org.openehr.schemas.v1.StringDictionaryItem copyStringDictionaryItem(StringDictionaryItem si) {
		org.openehr.schemas.v1.StringDictionaryItem result = new org.openehr.schemas.v1.StringDictionaryItem();
		//id
		if(si.hasId())
			result.setId(si.getId());
		//value
		if(si.hasValue())
			result.setValue(si.getValue());
		return result;
	}

	private org.openehr.schemas.v1.ConstraintBindingSet copyConstraintBindingSet(ConstraintBindingSet cbs) {
		org.openehr.schemas.v1.ConstraintBindingSet cbset = new org.openehr.schemas.v1.ConstraintBindingSet();
		//items
		for(CONSTRAINTBINDINGITEM cbitem : cbs.getItemsList())
			cbset.getItems().add(copyConstraintBindingItem(cbitem));
		//terminology
		if(cbs.hasTerminology())
			cbset.setTerminology(cbs.getTerminology());
		
		return cbset;
	}

	private org.openehr.schemas.v1.CONSTRAINTBINDINGITEM copyConstraintBindingItem(CONSTRAINTBINDINGITEM cbitem) {
		org.openehr.schemas.v1.CONSTRAINTBINDINGITEM result = new org.openehr.schemas.v1.CONSTRAINTBINDINGITEM();
		//code
		if(cbitem.hasCode())
			result.setCode(cbitem.getCode());
		//value
		if(cbitem.hasValue())
			result.setValue(cbitem.getValue());
		return result;
	}

	private CCOMPLEXOBJECT copyCComplexObject(bosphorus.Aom.CCOMPLEXOBJECT definition) {
		CCOMPLEXOBJECT result = new CCOMPLEXOBJECT();
		//attributes
		for(CATTRIBUTEALTERNATIVES atrAlternatives : definition.getAttributesList()){
			if(atrAlternatives.hasCmultipleattributefield()){
				//multipleattribute
				CMULTIPLEATTRIBUTE cmultiatr = copyMultipleAttribute(atrAlternatives.getCmultipleattributefield());				
				result.getAttributes().add(cmultiatr);
			}
			else{
				//singleattribute
				CSINGLEATTRIBUTE csingleatr = copyCSingleAttribute(atrAlternatives.getCsingleattributefield());
				result.getAttributes().add(csingleatr);
			}
		}
		//nodeid
		if(definition.hasNodeid())
			result.setNodeId(definition.getNodeid());
		//occurences
		if(definition.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(definition.getOccurrences()));
		//rmtypename
		if(definition.hasRmtypename())
			result.setRmTypeName(definition.getRmtypename());
		
		return result;
	}

	private CSINGLEATTRIBUTE copyCSingleAttribute(bosphorus.Aom.CSINGLEATTRIBUTE csingleattributefield) {
		org.openehr.schemas.v1.CSINGLEATTRIBUTE result = new org.openehr.schemas.v1.CSINGLEATTRIBUTE();
		//children
//		for(bosphorus.Aom.COBJECTALTERNATIVES child : csingleattributefield.getChildrenList())
//			result.getChildren().add(copyCObject(child));
		org.openehr.schemas.v1.COBJECT child = null;
		for(COBJECTALTERNATIVES cobjectalts : csingleattributefield.getChildrenList()){
			if(cobjectalts.hasArchetypeinternalreffield())
				child = copyArchetypeInternalRef(cobjectalts.getArchetypeinternalreffield());							
			else if(cobjectalts.hasArchetypeslotfield())				
				child = copyArchetypeSlot(cobjectalts.getArchetypeslotfield());							
			else if(cobjectalts.hasCcomplexobjectfield())
				child = copyCComplexObject(cobjectalts.getCcomplexobjectfield());			
			else if(cobjectalts.hasCdefinedobjectfield())
				child = copyCDefinedObject(cobjectalts.getCdefinedobjectfield());
			else if(cobjectalts.hasCcodephrasefield())//go over cdomaintype subtypes
				child = copyCCodePhrase(cobjectalts.getCcodephrasefield());
			else if (cobjectalts.hasCdvordinalfield())
				child = copyCDVOrdinal(cobjectalts.getCdvordinalfield());
			else if(cobjectalts.hasCdvquantityfield())
				child = copyCDVQuantity(cobjectalts.getCdvquantityfield());
////			else if(coa.hasCdvstatefield()) //TODO: DV_STATE MUST BE FIXED ON THE EIFFEL SIDE FIRST, BEFORE IT CAN BE USED HERE
////				child = copyCDVState(coa.getCdvstatefield());			
			else if(cobjectalts.hasConstraintreffield())
				child = copyConstraintRef(cobjectalts.getConstraintreffield());			
			else if(cobjectalts.hasCprimitiveobjectfield())
				child = copyCPrimitiveObject(cobjectalts.getCprimitiveobjectfield());			
			if(child != null)
				result.getChildren().add(child);
		}
		//existence
		if(csingleattributefield.hasExistence())
			result.setExistence(copyIntervalOfInteger(csingleattributefield.getExistence()));
		//rmattributename
		if(csingleattributefield.hasRmattributename())
			result.setRmAttributeName(csingleattributefield.getRmattributename());
		
		return result;
	}	

	private COBJECT copyCObject(COBJECTALTERNATIVES coa) {
		COBJECT result = null;
		if(coa.hasArchetypeinternalreffield())
			result = copyArchetypeInternalRef(coa.getArchetypeinternalreffield());
		else if(coa.hasArchetypeslotfield())
			result = copyArchetypeSlot(coa.getArchetypeslotfield());
		else if(coa.hasCcomplexobjectfield())
			result = copyCComplexObject(coa.getCcomplexobjectfield());
		else if(coa.hasCdefinedobjectfield())
			result = copyCDefinedObject(coa.getCdefinedobjectfield());
		else if(coa.hasCcodephrasefield())//go over cdomaintype subtypes
			result = copyCCodePhrase(coa.getCcodephrasefield());
		else if (coa.hasCdvordinalfield())
			result = copyCDVOrdinal(coa.getCdvordinalfield());
		else if(coa.hasCdvquantityfield())
			result = copyCDVQuantity(coa.getCdvquantityfield());
//		else if(coa.hasCdvstatefield()) //TODO: DV_STATE MUST BE FIXED ON THE EIFFEL SIDE FIRST, BEFORE IT CAN BE USED HERE
//			result = copyCDVState(coa.getCdvstatefield());
		else if(coa.hasConstraintreffield())
			result = copyConstraintRef(coa.getConstraintreffield());
		else if(coa.hasCprimitiveobjectfield())
			result = copyCPrimitiveObject(coa.getCprimitiveobjectfield());		
		if(result != null)
			return result;
		else{
			System.out.println("null COBJECT IN COPYCOBJECT");
			return result;
		}
	}

	private COBJECT copyCDVQuantity(CDVQUANTITY cdvquantityfield) {
		org.openehr.schemas.v1.CDVQUANTITY result = new org.openehr.schemas.v1.CDVQUANTITY();
		//nodeid
		if(cdvquantityfield.hasNodeid())
			result.setNodeId(cdvquantityfield.getNodeid());
		//occurences
		if(cdvquantityfield.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(cdvquantityfield.getOccurrences()));
		//rmtypename
		if(cdvquantityfield.hasRmtypename())
			result.setRmTypeName(cdvquantityfield.getRmtypename());
		//assumedvalue
		if(cdvquantityfield.hasAssumedvalue())
			result.setAssumedValue(copyDvQuantity(cdvquantityfield.getAssumedvalue()));
		//property
		if(cdvquantityfield.hasProperty())
			result.setProperty(copyCodePhrase(cdvquantityfield.getProperty()));
		//list
		for(CQUANTITYITEM qitem : cdvquantityfield.getListList()){
			result.getList().add(copyCQuantityItem(qitem));
		}
		return result;
	}

	private org.openehr.schemas.v1.CQUANTITYITEM copyCQuantityItem(CQUANTITYITEM qitem) {
		org.openehr.schemas.v1.CQUANTITYITEM result = new org.openehr.schemas.v1.CQUANTITYITEM();
		//magnitude
		result.setMagnitude(copyIntervalOfReal(qitem.getMagnitude()));
		//precision
		result.setPrecision(copyIntervalOfInteger(qitem.getPrecision()));
		//units
		result.setUnits(qitem.getUnits());
		
		return result;
	}

	private org.openehr.schemas.v1.CDVORDINAL copyCDVOrdinal(CDVORDINAL cdvordinalfield) {
		org.openehr.schemas.v1.CDVORDINAL result = new org.openehr.schemas.v1.CDVORDINAL();
		//nodeid
		if(cdvordinalfield.hasNodeid())
			result.setNodeId(cdvordinalfield.getNodeid());
		//occurences
		if(cdvordinalfield.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(cdvordinalfield.getOccurrences()));
		//rmtypename
		if(cdvordinalfield.hasRmtypename())
			result.setRmTypeName(cdvordinalfield.getRmtypename());
		//assumedvalue
		if(cdvordinalfield.hasAssumedvalue())
			result.setAssumedValue(copyDvOrdinal(cdvordinalfield.getAssumedvalue()));
		//list
		for(bosphorus.Aom.DVORDINAL dvordinal : cdvordinalfield.getListList())
			result.getList().add(copyDvOrdinal(dvordinal));
		return result;
	}

	private org.openehr.schemas.v1.CCODEPHRASE copyCCodePhrase(CCODEPHRASE ccodephrasefield) {
		org.openehr.schemas.v1.CCODEPHRASE result = new org.openehr.schemas.v1.CCODEPHRASE();
		//nodeid
		if(ccodephrasefield.hasNodeid())
			result.setNodeId(ccodephrasefield.getNodeid());
		//occurences
		if(ccodephrasefield.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(ccodephrasefield.getOccurrences()));
		//rmtypename
		if(ccodephrasefield.hasRmtypename())
			result.setRmTypeName(ccodephrasefield.getRmtypename());
		//assumedvalue
		if(ccodephrasefield.hasAssumedvalue())
			result.setAssumedValue(copyCodePhrase(ccodephrasefield.getAssumedvalue()));
		//terminologyid
		if(ccodephrasefield.hasTerminologyid())
			result.setTerminologyId(copyTerminologyId(ccodephrasefield.getTerminologyid()));
		//codelist
		for(String cl : ccodephrasefield.getCodelistList())
			result.getCodeList().add(cl);
		return result;
	}

	private CMULTIPLEATTRIBUTE copyMultipleAttribute(bosphorus.Aom.CMULTIPLEATTRIBUTE cmultipleattributefield) {
		CMULTIPLEATTRIBUTE result = new CMULTIPLEATTRIBUTE();
		//cardinality
		if(cmultipleattributefield.hasCardinality())
			result.setCardinality(copyCardinality(cmultipleattributefield.getCardinality()));
		//children
		COBJECT child = null;
		for(COBJECTALTERNATIVES cobjectalts : cmultipleattributefield.getChildrenList()){
			if(cobjectalts.hasArchetypeinternalreffield())
				child = copyArchetypeInternalRef(cobjectalts.getArchetypeinternalreffield());							
			else if(cobjectalts.hasArchetypeslotfield())				
				child = copyArchetypeSlot(cobjectalts.getArchetypeslotfield());							
			else if(cobjectalts.hasCcomplexobjectfield())
				child = copyCComplexObject(cobjectalts.getCcomplexobjectfield());			
			else if(cobjectalts.hasCdefinedobjectfield())
				child = copyCDefinedObject(cobjectalts.getCdefinedobjectfield());
			else if(cobjectalts.hasCcodephrasefield())//go over cdomaintype subtypes
				child = copyCCodePhrase(cobjectalts.getCcodephrasefield());
			else if (cobjectalts.hasCdvordinalfield())
				child = copyCDVOrdinal(cobjectalts.getCdvordinalfield());
			else if(cobjectalts.hasCdvquantityfield())
				child = copyCDVQuantity(cobjectalts.getCdvquantityfield());
//			else if(coa.hasCdvstatefield()) //TODO: DV_STATE MUST BE FIXED ON THE EIFFEL SIDE FIRST, BEFORE IT CAN BE USED HERE
//				child = copyCDVState(coa.getCdvstatefield());			
			else if(cobjectalts.hasConstraintreffield())
				child = copyConstraintRef(cobjectalts.getConstraintreffield());			
			else if(cobjectalts.hasCprimitiveobjectfield())
				child = copyCPrimitiveObject(cobjectalts.getCprimitiveobjectfield());			
			if(child != null)
				result.getChildren().add(child);
		}
		//existence
		if(cmultipleattributefield.hasExistence())
			result.setExistence(copyIntervalOfInteger(cmultipleattributefield.getExistence()));
		//rmattributename
		if(cmultipleattributefield.hasRmattributename())
			result.setRmAttributeName(cmultipleattributefield.getRmattributename());
		return result;
	}

	private CPRIMITIVEOBJECT copyCPrimitiveObject(bosphorus.Aom.CPRIMITIVEOBJECT cprimitiveobjectfield) {
		CPRIMITIVEOBJECT cpo = new CPRIMITIVEOBJECT();
		//item
		if (cprimitiveobjectfield.hasItem())
			cpo.setItem(copyCPrimitive(cprimitiveobjectfield.getItem()));
		//nodeid
		if(cprimitiveobjectfield.hasNodeid())
			cpo.setNodeId(cprimitiveobjectfield.getNodeid());
		//occurrences
		if(cprimitiveobjectfield.hasOccurrences())
			cpo.setOccurrences(copyIntervalOfInteger(cprimitiveobjectfield.getOccurrences()));
		//rmtypename
		if(cprimitiveobjectfield.hasRmtypename())
			cpo.setRmTypeName(cprimitiveobjectfield.getRmtypename());
		return cpo;
	}

	private CPRIMITIVE copyCPrimitive(CPRIMITIVEALTERNATIVES item) {
		CPRIMITIVE result = null;
		if(item.hasCbooleanfield()){
			result = copyCBoolean(item.getCbooleanfield());
		}
		else if(item.hasCdatefield()){
			result = copyCDate(item.getCdatefield());
		}
		else if(item.hasCdatetimefield()){
			result = copyCDateTime(item.getCdatetimefield()); 
		}
		else if(item.hasCdurationfield()){
			result = copyCDuration(item.getCdurationfield());
		}
		else if(item.hasCintegerfield()){
			result = copyCIntegerField(item.getCintegerfield());
		}
		else if(item.hasCrealfield()){
			result = copyCReal(item.getCrealfield());
		}
		else if(item.hasCstringfield()){
			result = copyCString(item.getCstringfield());
		}
		else if(item.hasCtimefield()){
			result = copyCTime(item.getCtimefield());
		}
		return result;
	}

	private CTIME copyCTime(bosphorus.Aom.CTIME ctimefield) {
		CTIME ct = new CTIME();
		//assumevalued
		if(ctimefield.hasAssumedvalue())
			ct.setAssumedValue(ctimefield.getAssumedvalue());
		//pattern
		if(ctimefield.hasPattern())
			ct.setPattern(ctimefield.getPattern());
		//range
		if(ctimefield.hasRange())
			ct.setRange(copyIntervalofTime(ctimefield.getRange()));
		//timezonevalidity
		if(ctimefield.hasTimezonevalidity())
			ct.setTimezoneValidity(ValidityKindMapper.getValidityKind(ctimefield.getTimezonevalidity()));
		return ct;
	}

	private IntervalOfTime copyIntervalofTime(bosphorus.Aom.IntervalOfTime range) {
		IntervalOfTime iot = new IntervalOfTime();
		//lower
		if(range.hasLower())
			iot.setLower(range.getLower());//todo: float represented as string
		//lowerincluded
		if(range.hasLowerincluded())
			iot.setLowerIncluded(range.getLowerincluded());
		//upper
		if(range.hasUpper())
			iot.setUpper(range.getUpper());//todo: float represented as string
		//upperincluded
		if(range.hasUpperincluded())
			iot.setUpperIncluded(range.getUpperincluded());
		//lowerunbounded
		if(range.hasLowerunbounded())
			iot.setLowerUnbounded(range.getLowerunbounded());
		//upperunbounded
		if(range.hasUpperunbounded())
			iot.setUpperUnbounded(range.getUpperunbounded());
		return iot;
	}

	private CSTRING copyCString(bosphorus.Aom.CSTRING cstringfield) {
		CSTRING cs = new CSTRING();
		//assumedvalue
		if(cstringfield.hasAssumedvalue())
			cs.setAssumedValue(cstringfield.getAssumedvalue());
		//list
		for(String s : cstringfield.getListList())
			cs.getList().add(s);
		//listopen
		if(cstringfield.hasListopen())
			cs.setListOpen(cstringfield.getListopen());
		//pattern
		if(cstringfield.hasPattern())
			cs.setPattern(cstringfield.getPattern());
		return cs;
	}

	private CREAL copyCReal(bosphorus.Aom.CREAL crealfield) {
		CREAL cr = new CREAL();
		//assumedvalue
		if(crealfield.hasAssumedvalue())
			cr.setAssumedValue(new Float(crealfield.getAssumedvalue()));//todo: float represented as string
		//list
		for(String f : crealfield.getListList())
			cr.getList().add(new Float(f));//todo: float represented as string
		//range
		if(crealfield.hasRange())
			cr.setRange(copyIntervalOfReal(crealfield.getRange()));
		return cr;
	}

	private IntervalOfReal copyIntervalOfReal(bosphorus.Aom.IntervalOfReal range) {
		IntervalOfReal ior = new IntervalOfReal();
		//lower
		if(range.hasLower())
			ior.setLower(new Float(range.getLower()));//todo: float represented as string
		//lowerincluded
		if(range.hasLowerincluded())
			ior.setLowerIncluded(range.getLowerincluded());
		//upper
		if(range.hasUpper())
			ior.setUpper(new Float(range.getUpper()));//todo: float represented as string
		//upperincluded
		if(range.hasUpperincluded())
			ior.setUpperIncluded(range.getUpperincluded());
		//lowerunbounded
		if(range.hasLowerunbounded())
			ior.setLowerUnbounded(range.getLowerunbounded());
		//upperunbounded
		if(range.hasUpperunbounded())
			ior.setUpperUnbounded(range.getUpperunbounded());
		
		return ior;
	}

	private CINTEGER copyCIntegerField(bosphorus.Aom.CINTEGER cintegerfield) {
		CINTEGER ci = new CINTEGER();
		//assumedvalue
		if(cintegerfield.hasAssumedvalue())
			ci.setAssumedValue(cintegerfield.getAssumedvalue());
		//list
		for(Integer i : cintegerfield.getListList())
			ci.getList().add(i);
		//range
		if(cintegerfield.hasRange())
			ci.setRange(copyIntervalOfInteger(cintegerfield.getRange()));
		return ci;
	}

	private CDURATION copyCDuration(bosphorus.Aom.CDURATION cdurationfield) {
		CDURATION cd = new CDURATION();
		//assumed value
		if(cdurationfield.hasAssumedvalue())
			cd.setAssumedValue(cdurationfield.getAssumedvalue());
		//pattern
		if(cdurationfield.hasPattern())
			cd.setPattern(cdurationfield.getPattern());
		//range
		if(cdurationfield.hasRange())
			cd.setRange(copyIntervalOfDuration(cdurationfield.getRange()));
		return cd;
	}

	private IntervalOfDuration copyIntervalOfDuration(bosphorus.Aom.IntervalOfDuration range) {
		IntervalOfDuration  iod = new IntervalOfDuration();
		//lower
		if(range.hasLower())
			iod.setLower(range.getLower());
		//lowerincluded
		if(range.hasLowerincluded())
			iod.setLowerIncluded(range.getLowerincluded());
		//upper
		if(range.hasUpper())
			iod.setUpper(range.getUpper());
		//upperincluded
		if(range.hasUpperincluded())
			iod.setUpperIncluded(range.getUpperincluded());
		//lowerunbounded
		if(range.hasLowerunbounded())
			iod.setLowerUnbounded(range.getLowerunbounded());
		//upperunbounded
		if(range.hasUpperunbounded())
			iod.setUpperUnbounded(range.getUpperunbounded());
		return iod;
	}

	private CDATETIME copyCDateTime(bosphorus.Aom.CDATETIME cdatetimefield) {
		CDATETIME cdt = new CDATETIME();
		//assumed value
		if(cdatetimefield.hasAssumedvalue())
			cdt.setAssumedValue(cdatetimefield.getAssumedvalue());
		//pattern
		if(cdatetimefield.hasPattern())
			cdt.setPattern(cdatetimefield.getPattern());
		//range
		if(cdatetimefield.hasRange())
			cdt.setRange(copyIntervalOfDateTime(cdatetimefield.getRange()));
		//timezonevalidty
		if(cdatetimefield.hasTimezonevalidity())
			cdt.setTimezoneValidity(ValidityKindMapper.getValidityKind(cdatetimefield.getTimezonevalidity()));
		return cdt;
	}

	private IntervalOfDateTime copyIntervalOfDateTime(bosphorus.Aom.IntervalOfDateTime range) {
		IntervalOfDateTime iodt = new IntervalOfDateTime();
		//lower
		if(range.hasLower())
			iodt.setLower(range.getLower());
		//lowerincluded
		if(range.hasLowerincluded())
			iodt.setLowerIncluded(range.getLowerincluded());
		//lowerunbounded
		if(range.hasLowerunbounded())
			iodt.setLowerUnbounded(range.getLowerunbounded());
		//upper
		if(range.hasUpper())
			iodt.setUpper(range.getUpper());
		//upperincluded
		if(range.hasUpperincluded())
			iodt.setUpperIncluded(range.getUpperincluded());
		///upperunbounded
		if(range.hasUpperunbounded())
			iodt.setUpperUnbounded(range.getUpperunbounded());
		return iodt;
	}

	private CDATE copyCDate(bosphorus.Aom.CDATE cdatefield) {
		CDATE cd = new CDATE();
		//asumed value
		if(cdatefield.hasAssumedvalue())
			cd.setAssumedValue(cdatefield.getAssumedvalue());
		//pattern
		if(cdatefield.hasPattern())
			cd.setPattern(cdatefield.getPattern());
		//range
		if(cdatefield.hasRange())
			cd.setRange(copyIntervalOfDate(cdatefield.getRange()));
		//timezonevalidty
		if(cdatefield.hasTimezonevalidity())
			cd.setTimezoneValidity(ValidityKindMapper.getValidityKind(cdatefield.getTimezonevalidity()));
		return cd;
	}

	private IntervalOfDate copyIntervalOfDate(bosphorus.Aom.IntervalOfDate range) {
		IntervalOfDate iod = new IntervalOfDate();
		//lower
		if(range.hasLower())
			iod.setLower(range.getLower());
		//lowerincluded
		if(range.hasLowerincluded())
			iod.setLowerIncluded(range.getLowerincluded());
		//lowerunbounded
		if(range.hasLowerunbounded())
			iod.setLowerUnbounded(range.getLowerunbounded());
		//upper
		if(range.hasUpper())
			iod.setUpper(range.getUpper());
		//upperincluded
		if(range.hasUpperincluded())
			iod.setUpperIncluded(range.getUpperincluded());
		//upperunbounded
		if(range.hasUpperunbounded())
			iod.setUpperUnbounded(range.getUpperunbounded());
		
		return iod;
	}

	private org.openehr.schemas.v1.CBOOLEAN copyCBoolean(CBOOLEAN cbooleanfield) {
		org.openehr.schemas.v1.CBOOLEAN cb = new org.openehr.schemas.v1.CBOOLEAN();
		//assumed value
		if(cbooleanfield.hasAssumedvalue())
			cb.setAssumedValue(cbooleanfield.getAssumedvalue());
		//false valid
		if(cbooleanfield.hasFalsevalid())
			cb.setFalseValid(cbooleanfield.getFalsevalid());
		//true valid
		if(cbooleanfield.hasTruevalid())
			cb.setTrueValid(cbooleanfield.getTruevalid());
		return cb;
	}

	private CONSTRAINTREF copyConstraintRef(bosphorus.Aom.CONSTRAINTREF constraintreffield) {
		CONSTRAINTREF cref = new CONSTRAINTREF();
		//nodeid
		if(constraintreffield.hasNodeid())
			cref.setNodeId(constraintreffield.getNodeid());
		//occurrences
		if(constraintreffield.hasOccurrences())
			cref.setOccurrences(copyIntervalOfInteger(constraintreffield.getOccurrences()));
		//reference
		if(constraintreffield.hasReference())
			cref.setReference(constraintreffield.getReference());
		//rmtypename
		if(constraintreffield.hasRmtypename())
			cref.setRmTypeName(constraintreffield.getRmtypename());
		
		return cref;
	}

	private CDOMAINTYPE copyCDomainType(final bosphorus.Aom.CDOMAINTYPE cdomaintypefield) {
		CDOMAINTYPE cdomType = new CDOMAINTYPE() {//TODO: IS THERE A BETTER WAY OF DOING THIS? what about GC issues?
			//an instance initializer is the best we can do, since no constr. allowed in anonymous classes
			{
				if(cdomaintypefield.hasNodeid())
					nodeId = cdomaintypefield.getNodeid();
				if(cdomaintypefield.hasOccurrences())
					occurrences = copyIntervalOfInteger(cdomaintypefield.getOccurrences());
				if(cdomaintypefield.hasRmtypename())
					rmTypeName = cdomaintypefield.getRmtypename();
			}
			@Override
			public String getNodeId() {
				// TODO Auto-generated method stub
				return super.getNodeId();
			}
			@Override
			public void setNodeId(String value) {
				// TODO Auto-generated method stub
				super.setNodeId(value);
			}
		};
		
		return cdomType;
	}

	private CDEFINEDOBJECT copyCDefinedObject(bosphorus.Aom.CDEFINEDOBJECT cdefinedobjectfield) {
		CDEFINEDOBJECT cdefobj = new CDEFINEDOBJECT();
		//nodeid
		if(cdefinedobjectfield.hasNodeid())
			cdefobj.setNodeId(cdefinedobjectfield.getNodeid());
		//occurences
		if(cdefinedobjectfield.hasOccurrences())
			cdefobj.setOccurrences(copyIntervalOfInteger(cdefinedobjectfield.getOccurrences()));
		//rmtypename
		if(cdefinedobjectfield.hasRmtypename())
			cdefobj.setRmTypeName(cdefinedobjectfield.getRmtypename());
		
		return cdefobj;
	}

	private ARCHETYPESLOT copyArchetypeSlot(bosphorus.Aom.ARCHETYPESLOT archetypeslotfield) {
		ARCHETYPESLOT result = new ARCHETYPESLOT();
		//excludes
		for(ASSERTION exclude : archetypeslotfield.getExcludesList()){
			result.getExcludes().add(copyAssertion(exclude));
		}
		//includes
		for(ASSERTION include : archetypeslotfield.getIncludesList()){
			result.getIncludes().add(copyAssertion(include));
		}
		//nodeid
		if(archetypeslotfield.hasNodeid())
			result.setNodeId(archetypeslotfield.getNodeid());
		//occurences
		if(archetypeslotfield.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(archetypeslotfield.getOccurrences()));
		//rmtypename
		if(archetypeslotfield.hasRmtypename())
			result.setRmTypeName(archetypeslotfield.getRmtypename());
		
		return result;
	}

	private org.openehr.schemas.v1.ASSERTION copyAssertion(ASSERTION exclude) {
		org.openehr.schemas.v1.ASSERTION result = new org.openehr.schemas.v1.ASSERTION();
		//expression
		if(exclude.hasExpression())
			result.setExpression(copyExpressionItem(exclude.getExpression()));
		//stringexpression
		if(exclude.hasStringexpression())
			result.setStringExpression(exclude.getStringexpression());
		//tag
		if(exclude.hasTag())
			result.setTag(exclude.getTag());
		//variables
		for(ASSERTIONVARIABLE assertionVar : exclude.getVariablesList()){
			org.openehr.schemas.v1.ASSERTIONVARIABLE asVarXsd = copyAssertionVariable(assertionVar);
			result.getVariables().add(asVarXsd);
		}
			
		return result;
	}

	private org.openehr.schemas.v1.ASSERTIONVARIABLE copyAssertionVariable(ASSERTIONVARIABLE assertionVar) {
		org.openehr.schemas.v1.ASSERTIONVARIABLE asVar = new org.openehr.schemas.v1.ASSERTIONVARIABLE();
		//definition
		if(assertionVar.hasDefinition())
			asVar.setDefinition(assertionVar.getDefinition());
		//name
		if(assertionVar.hasName())
			asVar.setName(assertionVar.getName());
		return asVar;
	}

	private EXPRITEM copyExpressionItem(EXPRITEMALTERNATIVES expression) {
		if(expression.hasExprunaryoperatorfield()){
			EXPRUNARYOPERATOR unaryOperator = new EXPRUNARYOPERATOR();
			//operand
			if(expression.getExprunaryoperatorfield().hasOperand())
				unaryOperator.setOperand(copyExpressionItem(expression.getExprunaryoperatorfield().getOperand()));			
			//operator
			if(expression.getExprunaryoperatorfield().hasOperator())
				unaryOperator.setOperator(OperatorKindMapper.getOperatorType(expression.getExprunaryoperatorfield().getOperator()));
			//precedencesoverrided
			if(expression.getExprunaryoperatorfield().hasPrecedenceoverridden())
				unaryOperator.setPrecedenceOverridden(expression.getExprunaryoperatorfield().getPrecedenceoverridden());
			//type
			if(expression.getExprunaryoperatorfield().hasType())
				unaryOperator.setType(expression.getExprunaryoperatorfield().getType());
			
			return unaryOperator;
		}
		else if(expression.hasExprbinaryoperatorfield()){
			EXPRBINARYOPERATOR binaryOperator = new EXPRBINARYOPERATOR();
			//leftoperand
			if(expression.getExprbinaryoperatorfield().hasLeftoperand())
				binaryOperator.setLeftOperand(copyExpressionItem(expression.getExprbinaryoperatorfield().getLeftoperand()));
			//operator
			if(expression.getExprbinaryoperatorfield().hasOperator())
				binaryOperator.setOperator(OperatorKindMapper.getOperatorType(expression.getExprbinaryoperatorfield().getOperator()));
			//precedenceoverriden
			if(expression.getExprbinaryoperatorfield().hasPrecedenceoverridden())
				binaryOperator.setPrecedenceOverridden(expression.getExprbinaryoperatorfield().getPrecedenceoverridden());
			//rightoperand
			if(expression.getExprbinaryoperatorfield().hasRightoperand())
				binaryOperator.setRightOperand(copyExpressionItem(expression.getExprbinaryoperatorfield().getRightoperand()));
			//type
			if(expression.getExprbinaryoperatorfield().hasType())
				binaryOperator.setType(expression.getExprbinaryoperatorfield().getType());
			return binaryOperator;			
		}
		else if(expression.hasExprleaffield()){
			EXPRLEAF leaf = new EXPRLEAF();
			//item
			if(expression.getExprleaffield().hasItem()){
				String contentType = expression.getExprleaffield().getItem().getTypeinfo();				
				
				if(contentType.equals(RawProtocolBufferDataType.C_STRING)){					
					byte[] bytes = expression.getExprleaffield().getItem().getMsgdata().toByteArray();
					bosphorus.Aom.CSTRING.Builder cstringBuilder = bosphorus.Aom.CSTRING.newBuilder();
					try{
						cstringBuilder.mergeFrom(bytes);
						leaf.setItem(copyCString(cstringBuilder.build()));
					}catch(Exception ex){
						leaf.setItem("ERROR: " + ex.toString());
					}					
				}
				else if(contentType.equals(RawProtocolBufferDataType.STRING)){									
					leaf.setItem(expression.getExprleaffield().getItem().getStringvalue());
				}
				else if(contentType.equals(RawProtocolBufferDataType.UNKNOWN)){
					leaf.setItem("ERROR: embedded protocol buffer message with unknown type");
					System.out.println(leaf.getItem());
				}
				else{
					leaf.setItem("Unhandled content type for embedded protocol buffer message");
					System.out.println(leaf.getItem());
				}
					
			}
			//referencetype
			if(expression.getExprleaffield().hasReferencetype())
				leaf.setReferenceType(expression.getExprleaffield().getReferencetype());
			//type
			if(expression.getExprleaffield().hasType())
				leaf.setType(expression.getExprleaffield().getType());
			return leaf;
		}
		return null;//Technically we should not reach here
	}

	private ARCHETYPEINTERNALREF copyArchetypeInternalRef(bosphorus.Aom.ARCHETYPEINTERNALREF archetypeinternalreffield) {
		ARCHETYPEINTERNALREF result = new ARCHETYPEINTERNALREF();
		//nodeid
		if(archetypeinternalreffield.hasNodeid())
			result.setNodeId(archetypeinternalreffield.getNodeid());
		//occurrences
		if(archetypeinternalreffield.hasOccurrences())
			result.setOccurrences(copyIntervalOfInteger(archetypeinternalreffield.getOccurrences()));
		//rmtypename
		if(archetypeinternalreffield.hasRmtypename())
			result.setRmTypeName(archetypeinternalreffield.getRmtypename());
		//targetpath
		if(archetypeinternalreffield.hasTargetpath())
			result.setTargetPath(archetypeinternalreffield.getTargetpath());
		return result;
	}

	private CARDINALITY copyCardinality(bosphorus.Aom.CARDINALITY cardinality) {
		CARDINALITY result = new CARDINALITY();
		//interval
		if(cardinality.hasInterval())
			result.setInterval(copyIntervalOfInteger(cardinality.getInterval()));
		//isordered
		if(cardinality.hasIsordered())
			result.setIsOrdered(cardinality.getIsordered());
		//isunique
		if(cardinality.hasIsunique())
			result.setIsUnique(cardinality.getIsunique());
		return result;
	}

	private IntervalOfInteger copyIntervalOfInteger(bosphorus.Aom.IntervalOfInteger interval) {
		IntervalOfInteger result = new IntervalOfInteger();
		//lower
		if(interval.hasLower())
			result.setLower(interval.getLower());
		//lowerincluded
		if(interval.hasLowerincluded())
			result.setLowerIncluded(interval.getLowerincluded());
		//lowerunbounded
		if(interval.hasLowerunbounded())
			result.setLowerUnbounded(interval.getLowerunbounded());
		//upper
		if(interval.hasUpper())
			result.setUpper(interval.getUpper());
		//upperincluded
		if(interval.hasUpperincluded())
			result.setUpperIncluded(interval.getUpperincluded());
		//upperunbounded
		if(interval.hasUpperunbounded())
			result.setUpperUnbounded(interval.getUpperunbounded());
		return result;
	}

	private ARCHETYPEID copyArchetypeId(bosphorus.Aom.ARCHETYPEID archetypeid) {
		ARCHETYPEID result = new ARCHETYPEID();
		//value
		result.setValue(archetypeid.getValue());
		return result;
	}
}
