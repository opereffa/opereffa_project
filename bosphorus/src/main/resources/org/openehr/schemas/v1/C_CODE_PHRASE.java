/**
 * C_CODE_PHRASE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_CODE_PHRASE  extends org.openehr.schemas.v1.C_DOMAIN_TYPE  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE assumed_value;

    private org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id;

    private java.lang.String[] code_list;

    public C_CODE_PHRASE() {
    }

    public C_CODE_PHRASE(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.CODE_PHRASE assumed_value,
           org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id,
           java.lang.String[] code_list) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.assumed_value = assumed_value;
        this.terminology_id = terminology_id;
        this.code_list = code_list;
    }


    /**
     * Gets the assumed_value value for this C_CODE_PHRASE.
     * 
     * @return assumed_value
     */
    public org.openehr.schemas.v1.CODE_PHRASE getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_CODE_PHRASE.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(org.openehr.schemas.v1.CODE_PHRASE assumed_value) {
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the terminology_id value for this C_CODE_PHRASE.
     * 
     * @return terminology_id
     */
    public org.openehr.schemas.v1.TERMINOLOGY_ID getTerminology_id() {
        return terminology_id;
    }


    /**
     * Sets the terminology_id value for this C_CODE_PHRASE.
     * 
     * @param terminology_id
     */
    public void setTerminology_id(org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id) {
        this.terminology_id = terminology_id;
    }


    /**
     * Gets the code_list value for this C_CODE_PHRASE.
     * 
     * @return code_list
     */
    public java.lang.String[] getCode_list() {
        return code_list;
    }


    /**
     * Sets the code_list value for this C_CODE_PHRASE.
     * 
     * @param code_list
     */
    public void setCode_list(java.lang.String[] code_list) {
        this.code_list = code_list;
    }

    public java.lang.String getCode_list(int i) {
        return this.code_list[i];
    }

    public void setCode_list(int i, java.lang.String _value) {
        this.code_list[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_CODE_PHRASE)) return false;
        C_CODE_PHRASE other = (C_CODE_PHRASE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.assumed_value==null && other.getAssumed_value()==null) || 
             (this.assumed_value!=null &&
              this.assumed_value.equals(other.getAssumed_value()))) &&
            ((this.terminology_id==null && other.getTerminology_id()==null) || 
             (this.terminology_id!=null &&
              this.terminology_id.equals(other.getTerminology_id()))) &&
            ((this.code_list==null && other.getCode_list()==null) || 
             (this.code_list!=null &&
              java.util.Arrays.equals(this.code_list, other.getCode_list())));
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
        if (getAssumed_value() != null) {
            _hashCode += getAssumed_value().hashCode();
        }
        if (getTerminology_id() != null) {
            _hashCode += getTerminology_id().hashCode();
        }
        if (getCode_list() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCode_list());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCode_list(), i);
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
        new org.apache.axis.description.TypeDesc(C_CODE_PHRASE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_CODE_PHRASE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminology_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "terminology_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERMINOLOGY_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "code_list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
