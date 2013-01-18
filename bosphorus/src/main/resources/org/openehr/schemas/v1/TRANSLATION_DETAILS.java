/**
 * TRANSLATION_DETAILS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class TRANSLATION_DETAILS  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE language;

    private org.openehr.schemas.v1.StringDictionaryItem[] author;

    private java.lang.String accreditation;

    private org.openehr.schemas.v1.StringDictionaryItem[] other_details;

    public TRANSLATION_DETAILS() {
    }

    public TRANSLATION_DETAILS(
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.StringDictionaryItem[] author,
           java.lang.String accreditation,
           org.openehr.schemas.v1.StringDictionaryItem[] other_details) {
           this.language = language;
           this.author = author;
           this.accreditation = accreditation;
           this.other_details = other_details;
    }


    /**
     * Gets the language value for this TRANSLATION_DETAILS.
     * 
     * @return language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this TRANSLATION_DETAILS.
     * 
     * @param language
     */
    public void setLanguage(org.openehr.schemas.v1.CODE_PHRASE language) {
        this.language = language;
    }


    /**
     * Gets the author value for this TRANSLATION_DETAILS.
     * 
     * @return author
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getAuthor() {
        return author;
    }


    /**
     * Sets the author value for this TRANSLATION_DETAILS.
     * 
     * @param author
     */
    public void setAuthor(org.openehr.schemas.v1.StringDictionaryItem[] author) {
        this.author = author;
    }

    public org.openehr.schemas.v1.StringDictionaryItem getAuthor(int i) {
        return this.author[i];
    }

    public void setAuthor(int i, org.openehr.schemas.v1.StringDictionaryItem _value) {
        this.author[i] = _value;
    }


    /**
     * Gets the accreditation value for this TRANSLATION_DETAILS.
     * 
     * @return accreditation
     */
    public java.lang.String getAccreditation() {
        return accreditation;
    }


    /**
     * Sets the accreditation value for this TRANSLATION_DETAILS.
     * 
     * @param accreditation
     */
    public void setAccreditation(java.lang.String accreditation) {
        this.accreditation = accreditation;
    }


    /**
     * Gets the other_details value for this TRANSLATION_DETAILS.
     * 
     * @return other_details
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getOther_details() {
        return other_details;
    }


    /**
     * Sets the other_details value for this TRANSLATION_DETAILS.
     * 
     * @param other_details
     */
    public void setOther_details(org.openehr.schemas.v1.StringDictionaryItem[] other_details) {
        this.other_details = other_details;
    }

    public org.openehr.schemas.v1.StringDictionaryItem getOther_details(int i) {
        return this.other_details[i];
    }

    public void setOther_details(int i, org.openehr.schemas.v1.StringDictionaryItem _value) {
        this.other_details[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TRANSLATION_DETAILS)) return false;
        TRANSLATION_DETAILS other = (TRANSLATION_DETAILS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              java.util.Arrays.equals(this.author, other.getAuthor()))) &&
            ((this.accreditation==null && other.getAccreditation()==null) || 
             (this.accreditation!=null &&
              this.accreditation.equals(other.getAccreditation()))) &&
            ((this.other_details==null && other.getOther_details()==null) || 
             (this.other_details!=null &&
              java.util.Arrays.equals(this.other_details, other.getOther_details())));
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
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getAuthor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccreditation() != null) {
            _hashCode += getAccreditation().hashCode();
        }
        if (getOther_details() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOther_details());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOther_details(), i);
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
        new org.apache.axis.description.TypeDesc(TRANSLATION_DETAILS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSLATION_DETAILS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "accreditation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
