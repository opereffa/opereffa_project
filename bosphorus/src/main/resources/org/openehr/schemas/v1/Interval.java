/**
 * Interval.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class Interval  implements java.io.Serializable {
    private java.lang.Boolean lower_included;

    private java.lang.Boolean upper_included;

    private boolean lower_unbounded;

    private boolean upper_unbounded;

    public Interval() {
    }

    public Interval(
           java.lang.Boolean lower_included,
           java.lang.Boolean upper_included,
           boolean lower_unbounded,
           boolean upper_unbounded) {
           this.lower_included = lower_included;
           this.upper_included = upper_included;
           this.lower_unbounded = lower_unbounded;
           this.upper_unbounded = upper_unbounded;
    }


    /**
     * Gets the lower_included value for this Interval.
     * 
     * @return lower_included
     */
    public java.lang.Boolean getLower_included() {
        return lower_included;
    }


    /**
     * Sets the lower_included value for this Interval.
     * 
     * @param lower_included
     */
    public void setLower_included(java.lang.Boolean lower_included) {
        this.lower_included = lower_included;
    }


    /**
     * Gets the upper_included value for this Interval.
     * 
     * @return upper_included
     */
    public java.lang.Boolean getUpper_included() {
        return upper_included;
    }


    /**
     * Sets the upper_included value for this Interval.
     * 
     * @param upper_included
     */
    public void setUpper_included(java.lang.Boolean upper_included) {
        this.upper_included = upper_included;
    }


    /**
     * Gets the lower_unbounded value for this Interval.
     * 
     * @return lower_unbounded
     */
    public boolean isLower_unbounded() {
        return lower_unbounded;
    }


    /**
     * Sets the lower_unbounded value for this Interval.
     * 
     * @param lower_unbounded
     */
    public void setLower_unbounded(boolean lower_unbounded) {
        this.lower_unbounded = lower_unbounded;
    }


    /**
     * Gets the upper_unbounded value for this Interval.
     * 
     * @return upper_unbounded
     */
    public boolean isUpper_unbounded() {
        return upper_unbounded;
    }


    /**
     * Sets the upper_unbounded value for this Interval.
     * 
     * @param upper_unbounded
     */
    public void setUpper_unbounded(boolean upper_unbounded) {
        this.upper_unbounded = upper_unbounded;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Interval)) return false;
        Interval other = (Interval) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lower_included==null && other.getLower_included()==null) || 
             (this.lower_included!=null &&
              this.lower_included.equals(other.getLower_included()))) &&
            ((this.upper_included==null && other.getUpper_included()==null) || 
             (this.upper_included!=null &&
              this.upper_included.equals(other.getUpper_included()))) &&
            this.lower_unbounded == other.isLower_unbounded() &&
            this.upper_unbounded == other.isUpper_unbounded();
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
        if (getLower_included() != null) {
            _hashCode += getLower_included().hashCode();
        }
        if (getUpper_included() != null) {
            _hashCode += getUpper_included().hashCode();
        }
        _hashCode += (isLower_unbounded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUpper_unbounded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Interval.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Interval"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower_included");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower_included"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper_included");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper_included"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower_unbounded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower_unbounded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper_unbounded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper_unbounded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
