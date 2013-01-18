/**
 * AUTHORED_RESOURCE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class AUTHORED_RESOURCE  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE original_language;

    private java.lang.Boolean is_controlled;

    private org.openehr.schemas.v1.RESOURCE_DESCRIPTION description;

    private org.openehr.schemas.v1.TRANSLATION_DETAILS[] translations;

    private org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history;

    public AUTHORED_RESOURCE() {
    }

    public AUTHORED_RESOURCE(
           org.openehr.schemas.v1.CODE_PHRASE original_language,
           java.lang.Boolean is_controlled,
           org.openehr.schemas.v1.RESOURCE_DESCRIPTION description,
           org.openehr.schemas.v1.TRANSLATION_DETAILS[] translations,
           org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history) {
           this.original_language = original_language;
           this.is_controlled = is_controlled;
           this.description = description;
           this.translations = translations;
           this.revision_history = revision_history;
    }


    /**
     * Gets the original_language value for this AUTHORED_RESOURCE.
     * 
     * @return original_language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getOriginal_language() {
        return original_language;
    }


    /**
     * Sets the original_language value for this AUTHORED_RESOURCE.
     * 
     * @param original_language
     */
    public void setOriginal_language(org.openehr.schemas.v1.CODE_PHRASE original_language) {
        this.original_language = original_language;
    }


    /**
     * Gets the is_controlled value for this AUTHORED_RESOURCE.
     * 
     * @return is_controlled
     */
    public java.lang.Boolean getIs_controlled() {
        return is_controlled;
    }


    /**
     * Sets the is_controlled value for this AUTHORED_RESOURCE.
     * 
     * @param is_controlled
     */
    public void setIs_controlled(java.lang.Boolean is_controlled) {
        this.is_controlled = is_controlled;
    }


    /**
     * Gets the description value for this AUTHORED_RESOURCE.
     * 
     * @return description
     */
    public org.openehr.schemas.v1.RESOURCE_DESCRIPTION getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AUTHORED_RESOURCE.
     * 
     * @param description
     */
    public void setDescription(org.openehr.schemas.v1.RESOURCE_DESCRIPTION description) {
        this.description = description;
    }


    /**
     * Gets the translations value for this AUTHORED_RESOURCE.
     * 
     * @return translations
     */
    public org.openehr.schemas.v1.TRANSLATION_DETAILS[] getTranslations() {
        return translations;
    }


    /**
     * Sets the translations value for this AUTHORED_RESOURCE.
     * 
     * @param translations
     */
    public void setTranslations(org.openehr.schemas.v1.TRANSLATION_DETAILS[] translations) {
        this.translations = translations;
    }

    public org.openehr.schemas.v1.TRANSLATION_DETAILS getTranslations(int i) {
        return this.translations[i];
    }

    public void setTranslations(int i, org.openehr.schemas.v1.TRANSLATION_DETAILS _value) {
        this.translations[i] = _value;
    }


    /**
     * Gets the revision_history value for this AUTHORED_RESOURCE.
     * 
     * @return revision_history
     */
    public org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] getRevision_history() {
        return revision_history;
    }


    /**
     * Sets the revision_history value for this AUTHORED_RESOURCE.
     * 
     * @param revision_history
     */
    public void setRevision_history(org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history) {
        this.revision_history = revision_history;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AUTHORED_RESOURCE)) return false;
        AUTHORED_RESOURCE other = (AUTHORED_RESOURCE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.original_language==null && other.getOriginal_language()==null) || 
             (this.original_language!=null &&
              this.original_language.equals(other.getOriginal_language()))) &&
            ((this.is_controlled==null && other.getIs_controlled()==null) || 
             (this.is_controlled!=null &&
              this.is_controlled.equals(other.getIs_controlled()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.translations==null && other.getTranslations()==null) || 
             (this.translations!=null &&
              java.util.Arrays.equals(this.translations, other.getTranslations()))) &&
            ((this.revision_history==null && other.getRevision_history()==null) || 
             (this.revision_history!=null &&
              java.util.Arrays.equals(this.revision_history, other.getRevision_history())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOriginal_language() != null) {
            _hashCode += getOriginal_language().hashCode();
        }
        if (getIs_controlled() != null) {
            _hashCode += getIs_controlled().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getTranslations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTranslations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTranslations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRevision_history() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRevision_history());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRevision_history(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AUTHORED_RESOURCE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUTHORED_RESOURCE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "original_language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_controlled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_controlled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("translations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "translations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSLATION_DETAILS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revision_history");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "revision_history"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items"));
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
