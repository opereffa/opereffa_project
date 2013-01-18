/**
 * RESOURCE_DESCRIPTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class RESOURCE_DESCRIPTION  implements java.io.Serializable {
    private org.openehr.schemas.v1.StringDictionaryItem[] original_author;

    private java.lang.String[] other_contributors;

    private java.lang.String lifecycle_state;

    private java.lang.String resource_package_uri;

    private org.openehr.schemas.v1.StringDictionaryItem[] other_details;

    private org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM[] details;

    private org.openehr.schemas.v1.AUTHORED_RESOURCE parent_resource;

    public RESOURCE_DESCRIPTION() {
    }

    public RESOURCE_DESCRIPTION(
           org.openehr.schemas.v1.StringDictionaryItem[] original_author,
           java.lang.String[] other_contributors,
           java.lang.String lifecycle_state,
           java.lang.String resource_package_uri,
           org.openehr.schemas.v1.StringDictionaryItem[] other_details,
           org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM[] details,
           org.openehr.schemas.v1.AUTHORED_RESOURCE parent_resource) {
           this.original_author = original_author;
           this.other_contributors = other_contributors;
           this.lifecycle_state = lifecycle_state;
           this.resource_package_uri = resource_package_uri;
           this.other_details = other_details;
           this.details = details;
           this.parent_resource = parent_resource;
    }


    /**
     * Gets the original_author value for this RESOURCE_DESCRIPTION.
     * 
     * @return original_author
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getOriginal_author() {
        return original_author;
    }


    /**
     * Sets the original_author value for this RESOURCE_DESCRIPTION.
     * 
     * @param original_author
     */
    public void setOriginal_author(org.openehr.schemas.v1.StringDictionaryItem[] original_author) {
        this.original_author = original_author;
    }

    public org.openehr.schemas.v1.StringDictionaryItem getOriginal_author(int i) {
        return this.original_author[i];
    }

    public void setOriginal_author(int i, org.openehr.schemas.v1.StringDictionaryItem _value) {
        this.original_author[i] = _value;
    }


    /**
     * Gets the other_contributors value for this RESOURCE_DESCRIPTION.
     * 
     * @return other_contributors
     */
    public java.lang.String[] getOther_contributors() {
        return other_contributors;
    }


    /**
     * Sets the other_contributors value for this RESOURCE_DESCRIPTION.
     * 
     * @param other_contributors
     */
    public void setOther_contributors(java.lang.String[] other_contributors) {
        this.other_contributors = other_contributors;
    }

    public java.lang.String getOther_contributors(int i) {
        return this.other_contributors[i];
    }

    public void setOther_contributors(int i, java.lang.String _value) {
        this.other_contributors[i] = _value;
    }


    /**
     * Gets the lifecycle_state value for this RESOURCE_DESCRIPTION.
     * 
     * @return lifecycle_state
     */
    public java.lang.String getLifecycle_state() {
        return lifecycle_state;
    }


    /**
     * Sets the lifecycle_state value for this RESOURCE_DESCRIPTION.
     * 
     * @param lifecycle_state
     */
    public void setLifecycle_state(java.lang.String lifecycle_state) {
        this.lifecycle_state = lifecycle_state;
    }


    /**
     * Gets the resource_package_uri value for this RESOURCE_DESCRIPTION.
     * 
     * @return resource_package_uri
     */
    public java.lang.String getResource_package_uri() {
        return resource_package_uri;
    }


    /**
     * Sets the resource_package_uri value for this RESOURCE_DESCRIPTION.
     * 
     * @param resource_package_uri
     */
    public void setResource_package_uri(java.lang.String resource_package_uri) {
        this.resource_package_uri = resource_package_uri;
    }


    /**
     * Gets the other_details value for this RESOURCE_DESCRIPTION.
     * 
     * @return other_details
     */
    public org.openehr.schemas.v1.StringDictionaryItem[] getOther_details() {
        return other_details;
    }


    /**
     * Sets the other_details value for this RESOURCE_DESCRIPTION.
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


    /**
     * Gets the details value for this RESOURCE_DESCRIPTION.
     * 
     * @return details
     */
    public org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this RESOURCE_DESCRIPTION.
     * 
     * @param details
     */
    public void setDetails(org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM[] details) {
        this.details = details;
    }

    public org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM getDetails(int i) {
        return this.details[i];
    }

    public void setDetails(int i, org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM _value) {
        this.details[i] = _value;
    }


    /**
     * Gets the parent_resource value for this RESOURCE_DESCRIPTION.
     * 
     * @return parent_resource
     */
    public org.openehr.schemas.v1.AUTHORED_RESOURCE getParent_resource() {
        return parent_resource;
    }


    /**
     * Sets the parent_resource value for this RESOURCE_DESCRIPTION.
     * 
     * @param parent_resource
     */
    public void setParent_resource(org.openehr.schemas.v1.AUTHORED_RESOURCE parent_resource) {
        this.parent_resource = parent_resource;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RESOURCE_DESCRIPTION)) return false;
        RESOURCE_DESCRIPTION other = (RESOURCE_DESCRIPTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.original_author==null && other.getOriginal_author()==null) || 
             (this.original_author!=null &&
              java.util.Arrays.equals(this.original_author, other.getOriginal_author()))) &&
            ((this.other_contributors==null && other.getOther_contributors()==null) || 
             (this.other_contributors!=null &&
              java.util.Arrays.equals(this.other_contributors, other.getOther_contributors()))) &&
            ((this.lifecycle_state==null && other.getLifecycle_state()==null) || 
             (this.lifecycle_state!=null &&
              this.lifecycle_state.equals(other.getLifecycle_state()))) &&
            ((this.resource_package_uri==null && other.getResource_package_uri()==null) || 
             (this.resource_package_uri!=null &&
              this.resource_package_uri.equals(other.getResource_package_uri()))) &&
            ((this.other_details==null && other.getOther_details()==null) || 
             (this.other_details!=null &&
              java.util.Arrays.equals(this.other_details, other.getOther_details()))) &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails()))) &&
            ((this.parent_resource==null && other.getParent_resource()==null) || 
             (this.parent_resource!=null &&
              this.parent_resource.equals(other.getParent_resource())));
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
        if (getOriginal_author() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOriginal_author());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOriginal_author(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOther_contributors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOther_contributors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOther_contributors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLifecycle_state() != null) {
            _hashCode += getLifecycle_state().hashCode();
        }
        if (getResource_package_uri() != null) {
            _hashCode += getResource_package_uri().hashCode();
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
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getParent_resource() != null) {
            _hashCode += getParent_resource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RESOURCE_DESCRIPTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "original_author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_contributors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_contributors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifecycle_state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lifecycle_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resource_package_uri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "resource_package_uri"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_resource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "parent_resource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUTHORED_RESOURCE"));
        elemField.setMinOccurs(0);
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
