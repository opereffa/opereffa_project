/**
 * RESOURCE_DESCRIPTION_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class RESOURCE_DESCRIPTION_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE language;

    private java.lang.String purpose;

    private java.lang.String[] keywords;

    private java.lang.String use;

    private java.lang.String misuse;

    private java.lang.String copyright;

    private org.openehr.schemas.v1.StringDictionaryItem[] original_resource_uri;

    private org.openehr.schemas.v1.StringDictionaryItem[] other_details;

    public RESOURCE_DESCRIPTION_ITEM() {
    }

    public RESOURCE_DESCRIPTION_ITEM(
           org.openehr.schemas.v1.CODE_PHRASE language,
           java.lang.String purpose,
           java.lang.String[] keywords,
           java.lang.String use,
           java.lang.String misuse,
           java.lang.String copyright,
           org.openehr.schemas.v1.StringDictionaryItem[] original_resource_uri,
           org.openehr.schemas.v1.StringDictionaryItem[] other_details) {
           this.language = language;
           this.purpose = purpose;
           this.keywords = keywords;
           this.use = use;
           this.misuse = misuse;
           this.copyright = copyright;
           this.original_resource_uri = original_resource_uri;
           this.other_details = other_details;
    }


    /**
     * Gets the language value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param language
     */
    public void setLanguage(org.openehr.schemas.v1.CODE_PHRASE language) {
        this.language = language;
    }


    /**
     * Gets the purpose value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return purpose
     */
    public java.lang.String getPurpose() {
        return purpose;
    }


    /**
     * Sets the purpose value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param purpose
     */
    public void setPurpose(java.lang.String purpose) {
        this.purpose = purpose;
    }


    /**
     * Gets the keywords value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return keywords
     */
    public java.lang.String[] getKeywords() {
        return keywords;
    }


    /**
     * Sets the keywords value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param keywords
     */
    public void setKeywords(java.lang.String[] keywords) {
        this.keywords = keywords;
    }

    public java.lang.String getKeywords(int i) {
        return this.keywords[i];
    }

    public void setKeywords(int i, java.lang.String _value) {
        this.keywords[i] = _value;
    }


    /**
     * Gets the use value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return use
     */
    public java.lang.String getUse() {
        return use;
    }


    /**
     * Sets the use value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param use
     */
    public void setUse(java.lang.String use) {
        this.use = use;
    }


    /**
     * Gets the misuse value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return misuse
     */
    public java.lang.String getMisuse() {
        return misuse;
    }


    /**
     * Sets the misuse value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param misuse
     */
    public void setMisuse(java.lang.String misuse) {
        this.misuse = misuse;
    }


    /**
     * Gets the copyright value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return copyright
     */
    public java.lang.String getCopyright() {
        return copyright;
    }


    /**
     * Sets the copyright value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param copyright
     */
    public void setCopyright(java.lang.String copyright) {
        this.copyright = copyright;
    }


    /**
     * Gets the original_resource_uri value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return original_resource_uri
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getOriginal_resource_uri() {
        return original_resource_uri;
    }


    /**
     * Sets the original_resource_uri value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @param original_resource_uri
     */
    public void setOriginal_resource_uri(org.openehr.schemas.v1.StringDictionaryItem[] original_resource_uri) {
        this.original_resource_uri = original_resource_uri;
    }

    public org.openehr.schemas.v1.StringDictionaryItem getOriginal_resource_uri(int i) {
        return this.original_resource_uri[i];
    }

    public void setOriginal_resource_uri(int i, org.openehr.schemas.v1.StringDictionaryItem _value) {
        this.original_resource_uri[i] = _value;
    }


    /**
     * Gets the other_details value for this RESOURCE_DESCRIPTION_ITEM.
     * 
     * @return other_details
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getOther_details() {
        return other_details;
    }


    /**
     * Sets the other_details value for this RESOURCE_DESCRIPTION_ITEM.
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
        if (!(obj instanceof RESOURCE_DESCRIPTION_ITEM)) return false;
        RESOURCE_DESCRIPTION_ITEM other = (RESOURCE_DESCRIPTION_ITEM) obj;
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
            ((this.purpose==null && other.getPurpose()==null) || 
             (this.purpose!=null &&
              this.purpose.equals(other.getPurpose()))) &&
            ((this.keywords==null && other.getKeywords()==null) || 
             (this.keywords!=null &&
              java.util.Arrays.equals(this.keywords, other.getKeywords()))) &&
            ((this.use==null && other.getUse()==null) || 
             (this.use!=null &&
              this.use.equals(other.getUse()))) &&
            ((this.misuse==null && other.getMisuse()==null) || 
             (this.misuse!=null &&
              this.misuse.equals(other.getMisuse()))) &&
            ((this.copyright==null && other.getCopyright()==null) || 
             (this.copyright!=null &&
              this.copyright.equals(other.getCopyright()))) &&
            ((this.original_resource_uri==null && other.getOriginal_resource_uri()==null) || 
             (this.original_resource_uri!=null &&
              java.util.Arrays.equals(this.original_resource_uri, other.getOriginal_resource_uri()))) &&
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
        if (getPurpose() != null) {
            _hashCode += getPurpose().hashCode();
        }
        if (getKeywords() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeywords());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeywords(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUse() != null) {
            _hashCode += getUse().hashCode();
        }
        if (getMisuse() != null) {
            _hashCode += getMisuse().hashCode();
        }
        if (getCopyright() != null) {
            _hashCode += getCopyright().hashCode();
        }
        if (getOriginal_resource_uri() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOriginal_resource_uri());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOriginal_resource_uri(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(RESOURCE_DESCRIPTION_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION_ITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "purpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keywords");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "keywords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "use"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("misuse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "misuse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copyright");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "copyright"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_resource_uri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "original_resource_uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
