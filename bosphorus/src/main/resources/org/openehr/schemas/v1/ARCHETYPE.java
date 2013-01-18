/**
 * ARCHETYPE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ARCHETYPE  extends org.openehr.schemas.v1.AUTHORED_RESOURCE  implements java.io.Serializable {
    private org.openehr.schemas.v1.HIER_OBJECT_ID uid;

    private org.openehr.schemas.v1.ARCHETYPE_ID archetype_id;

    private java.lang.String adl_version;

    private java.lang.String concept;

    private org.openehr.schemas.v1.ARCHETYPE_ID parent_archetype_id;

    private org.openehr.schemas.v1.C_COMPLEX_OBJECT definition;

    private org.openehr.schemas.v1.ASSERTION[] invariants;

    private org.openehr.schemas.v1.ARCHETYPE_ONTOLOGY ontology;

    public ARCHETYPE() {
    }

    public ARCHETYPE(
           org.openehr.schemas.v1.CODE_PHRASE original_language,
           java.lang.Boolean is_controlled,
           org.openehr.schemas.v1.RESOURCE_DESCRIPTION description,
           org.openehr.schemas.v1.TRANSLATION_DETAILS[] translations,
           org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history,
           org.openehr.schemas.v1.HIER_OBJECT_ID uid,
           org.openehr.schemas.v1.ARCHETYPE_ID archetype_id,
           java.lang.String adl_version,
           java.lang.String concept,
           org.openehr.schemas.v1.ARCHETYPE_ID parent_archetype_id,
           org.openehr.schemas.v1.C_COMPLEX_OBJECT definition,
           org.openehr.schemas.v1.ASSERTION[] invariants,
           org.openehr.schemas.v1.ARCHETYPE_ONTOLOGY ontology) {
        super(
            original_language,
            is_controlled,
            description,
            translations,
            revision_history);
        this.uid = uid;
        this.archetype_id = archetype_id;
        this.adl_version = adl_version;
        this.concept = concept;
        this.parent_archetype_id = parent_archetype_id;
        this.definition = definition;
        this.invariants = invariants;
        this.ontology = ontology;
    }


    /**
     * Gets the uid value for this ARCHETYPE.
     * 
     * @return uid
     */
    public org.openehr.schemas.v1.HIER_OBJECT_ID getUid() {
        return uid;
    }


    /**
     * Sets the uid value for this ARCHETYPE.
     * 
     * @param uid
     */
    public void setUid(org.openehr.schemas.v1.HIER_OBJECT_ID uid) {
        this.uid = uid;
    }


    /**
     * Gets the archetype_id value for this ARCHETYPE.
     * 
     * @return archetype_id
     */
    public org.openehr.schemas.v1.ARCHETYPE_ID getArchetype_id() {
        return archetype_id;
    }


    /**
     * Sets the archetype_id value for this ARCHETYPE.
     * 
     * @param archetype_id
     */
    public void setArchetype_id(org.openehr.schemas.v1.ARCHETYPE_ID archetype_id) {
        this.archetype_id = archetype_id;
    }


    /**
     * Gets the adl_version value for this ARCHETYPE.
     * 
     * @return adl_version
     */
    public java.lang.String getAdl_version() {
        return adl_version;
    }


    /**
     * Sets the adl_version value for this ARCHETYPE.
     * 
     * @param adl_version
     */
    public void setAdl_version(java.lang.String adl_version) {
        this.adl_version = adl_version;
    }


    /**
     * Gets the concept value for this ARCHETYPE.
     * 
     * @return concept
     */
    public java.lang.String getConcept() {
        return concept;
    }


    /**
     * Sets the concept value for this ARCHETYPE.
     * 
     * @param concept
     */
    public void setConcept(java.lang.String concept) {
        this.concept = concept;
    }


    /**
     * Gets the parent_archetype_id value for this ARCHETYPE.
     * 
     * @return parent_archetype_id
     */
    public org.openehr.schemas.v1.ARCHETYPE_ID getParent_archetype_id() {
        return parent_archetype_id;
    }


    /**
     * Sets the parent_archetype_id value for this ARCHETYPE.
     * 
     * @param parent_archetype_id
     */
    public void setParent_archetype_id(org.openehr.schemas.v1.ARCHETYPE_ID parent_archetype_id) {
        this.parent_archetype_id = parent_archetype_id;
    }


    /**
     * Gets the definition value for this ARCHETYPE.
     * 
     * @return definition
     */
    public org.openehr.schemas.v1.C_COMPLEX_OBJECT getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this ARCHETYPE.
     * 
     * @param definition
     */
    public void setDefinition(org.openehr.schemas.v1.C_COMPLEX_OBJECT definition) {
        this.definition = definition;
    }


    /**
     * Gets the invariants value for this ARCHETYPE.
     * 
     * @return invariants
     */
    public org.openehr.schemas.v1.ASSERTION[] getInvariants() {
        return invariants;
    }


    /**
     * Sets the invariants value for this ARCHETYPE.
     * 
     * @param invariants
     */
    public void setInvariants(org.openehr.schemas.v1.ASSERTION[] invariants) {
        this.invariants = invariants;
    }

    public org.openehr.schemas.v1.ASSERTION getInvariants(int i) {
        return this.invariants[i];
    }

    public void setInvariants(int i, org.openehr.schemas.v1.ASSERTION _value) {
        this.invariants[i] = _value;
    }


    /**
     * Gets the ontology value for this ARCHETYPE.
     * 
     * @return ontology
     */
    public org.openehr.schemas.v1.ARCHETYPE_ONTOLOGY getOntology() {
        return ontology;
    }


    /**
     * Sets the ontology value for this ARCHETYPE.
     * 
     * @param ontology
     */
    public void setOntology(org.openehr.schemas.v1.ARCHETYPE_ONTOLOGY ontology) {
        this.ontology = ontology;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ARCHETYPE)) return false;
        ARCHETYPE other = (ARCHETYPE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.uid==null && other.getUid()==null) || 
             (this.uid!=null &&
              this.uid.equals(other.getUid()))) &&
            ((this.archetype_id==null && other.getArchetype_id()==null) || 
             (this.archetype_id!=null &&
              this.archetype_id.equals(other.getArchetype_id()))) &&
            ((this.adl_version==null && other.getAdl_version()==null) || 
             (this.adl_version!=null &&
              this.adl_version.equals(other.getAdl_version()))) &&
            ((this.concept==null && other.getConcept()==null) || 
             (this.concept!=null &&
              this.concept.equals(other.getConcept()))) &&
            ((this.parent_archetype_id==null && other.getParent_archetype_id()==null) || 
             (this.parent_archetype_id!=null &&
              this.parent_archetype_id.equals(other.getParent_archetype_id()))) &&
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              this.definition.equals(other.getDefinition()))) &&
            ((this.invariants==null && other.getInvariants()==null) || 
             (this.invariants!=null &&
              java.util.Arrays.equals(this.invariants, other.getInvariants()))) &&
            ((this.ontology==null && other.getOntology()==null) || 
             (this.ontology!=null &&
              this.ontology.equals(other.getOntology())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getUid() != null) {
            _hashCode += getUid().hashCode();
        }
        if (getArchetype_id() != null) {
            _hashCode += getArchetype_id().hashCode();
        }
        if (getAdl_version() != null) {
            _hashCode += getAdl_version().hashCode();
        }
        if (getConcept() != null) {
            _hashCode += getConcept().hashCode();
        }
        if (getParent_archetype_id() != null) {
            _hashCode += getParent_archetype_id().hashCode();
        }
        if (getDefinition() != null) {
            _hashCode += getDefinition().hashCode();
        }
        if (getInvariants() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInvariants());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInvariants(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOntology() != null) {
            _hashCode += getOntology().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ARCHETYPE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archetype_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetype_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ID"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adl_version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "adl_version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("concept");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "concept"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_archetype_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "parent_archetype_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_COMPLEX_OBJECT"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invariants");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "invariants"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ontology");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ontology"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ONTOLOGY"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
