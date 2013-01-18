/**
 * C_BOOLEAN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_BOOLEAN  extends org.openehr.schemas.v1.C_PRIMITIVE  implements java.io.Serializable {
    private boolean true_valid;

    private boolean false_valid;

    private java.lang.Boolean assumed_value;

    public C_BOOLEAN() {
    }

    public C_BOOLEAN(
           boolean true_valid,
           boolean false_valid,
           java.lang.Boolean assumed_value) {
        this.true_valid = true_valid;
        this.false_valid = false_valid;
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the true_valid value for this C_BOOLEAN.
     * 
     * @return true_valid
     */
    public boolean isTrue_valid() {
        return true_valid;
    }


    /**
     * Sets the true_valid value for this C_BOOLEAN.
     * 
     * @param true_valid
     */
    public void setTrue_valid(boolean true_valid) {
        this.true_valid = true_valid;
    }


    /**
     * Gets the false_valid value for this C_BOOLEAN.
     * 
     * @return false_valid
     */
    public boolean isFalse_valid() {
        return false_valid;
    }


    /**
     * Sets the false_valid value for this C_BOOLEAN.
     * 
     * @param false_valid
     */
    public void setFalse_valid(boolean false_valid) {
        this.false_valid = false_valid;
    }


    /**
     * Gets the assumed_value value for this C_BOOLEAN.
     * 
     * @return assumed_value
     */
    public java.lang.Boolean getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_BOOLEAN.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(java.lang.Boolean assumed_value) {
        this.assumed_value = assumed_value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_BOOLEAN)) return false;
        C_BOOLEAN other = (C_BOOLEAN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.true_valid == other.isTrue_valid() &&
            this.false_valid == other.isFalse_valid() &&
            ((this.assumed_value==null && other.getAssumed_value()==null) || 
             (this.assumed_value!=null &&
              this.assumed_value.equals(other.getAssumed_value())));
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
        _hashCode += (isTrue_valid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isFalse_valid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAssumed_value() != null) {
            _hashCode += getAssumed_value().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_BOOLEAN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_BOOLEAN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("true_valid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "true_valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("false_valid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "false_valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
