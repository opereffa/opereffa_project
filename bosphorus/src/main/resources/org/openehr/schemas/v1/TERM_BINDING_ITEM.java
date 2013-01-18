/**
 * TERM_BINDING_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class TERM_BINDING_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE value;

    private java.lang.String code;  // attribute

    public TERM_BINDING_ITEM() {
    }

    public TERM_BINDING_ITEM(
           org.openehr.schemas.v1.CODE_PHRASE value,
           java.lang.String code) {
           this.value = value;
           this.code = code;
    }


    /**
     * Gets the value value for this TERM_BINDING_ITEM.
     * 
     * @return value
     */
    public org.openehr.schemas.v1.CODE_PHRASE getValue() {
        return value;
    }


    /**
     * Sets the value value for this TERM_BINDING_ITEM.
     * 
     * @param value
     */
    public void setValue(org.openehr.schemas.v1.CODE_PHRASE value) {
        this.value = value;
    }


    /**
     * Gets the code value for this TERM_BINDING_ITEM.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this TERM_BINDING_ITEM.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TERM_BINDING_ITEM)) return false;
        TERM_BINDING_ITEM other = (TERM_BINDING_ITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode())));
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
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TERM_BINDING_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_BINDING_ITEM"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("code");
        attrField.setXmlName(new javax.xml.namespace.QName("", "code"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
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
