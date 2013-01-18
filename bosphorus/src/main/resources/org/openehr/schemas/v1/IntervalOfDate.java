/**
 * IntervalOfDate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class IntervalOfDate  extends org.openehr.schemas.v1.Interval  implements java.io.Serializable {
    private java.lang.String lower;

    private java.lang.String upper;

    public IntervalOfDate() {
    }

    public IntervalOfDate(
           java.lang.Boolean lower_included,
           java.lang.Boolean upper_included,
           boolean lower_unbounded,
           boolean upper_unbounded,
           java.lang.String lower,
           java.lang.String upper) {
        super(
            lower_included,
            upper_included,
            lower_unbounded,
            upper_unbounded);
        this.lower = lower;
        this.upper = upper;
    }


    /**
     * Gets the lower value for this IntervalOfDate.
     * 
     * @return lower
     */
    public java.lang.String getLower() {
        return lower;
    }


    /**
     * Sets the lower value for this IntervalOfDate.
     * 
     * @param lower
     */
    public void setLower(java.lang.String lower) {
        this.lower = lower;
    }


    /**
     * Gets the upper value for this IntervalOfDate.
     * 
     * @return upper
     */
    public java.lang.String getUpper() {
        return upper;
    }


    /**
     * Sets the upper value for this IntervalOfDate.
     * 
     * @param upper
     */
    public void setUpper(java.lang.String upper) {
        this.upper = upper;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntervalOfDate)) return false;
        IntervalOfDate other = (IntervalOfDate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lower==null && other.getLower()==null) || 
             (this.lower!=null &&
              this.lower.equals(other.getLower()))) &&
            ((this.upper==null && other.getUpper()==null) || 
             (this.upper!=null &&
              this.upper.equals(other.getUpper())));
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
        if (getLower() != null) {
            _hashCode += getLower().hashCode();
        }
        if (getUpper() != null) {
            _hashCode += getUpper().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntervalOfDate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfDate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
