/**
 * C_TIME.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_TIME  extends org.openehr.schemas.v1.C_PRIMITIVE  implements java.io.Serializable {
    private java.lang.String pattern;

    private org.openehr.schemas.v1.VALIDITY_KIND timezone_validity;

    private org.openehr.schemas.v1.IntervalOfTime range;

    private java.lang.String assumed_value;

    public C_TIME() {
    }

    public C_TIME(
           java.lang.String pattern,
           org.openehr.schemas.v1.VALIDITY_KIND timezone_validity,
           org.openehr.schemas.v1.IntervalOfTime range,
           java.lang.String assumed_value) {
        this.pattern = pattern;
        this.timezone_validity = timezone_validity;
        this.range = range;
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the pattern value for this C_TIME.
     * 
     * @return pattern
     */
    public java.lang.String getPattern() {
        return pattern;
    }


    /**
     * Sets the pattern value for this C_TIME.
     * 
     * @param pattern
     */
    public void setPattern(java.lang.String pattern) {
        this.pattern = pattern;
    }


    /**
     * Gets the timezone_validity value for this C_TIME.
     * 
     * @return timezone_validity
     */
    public org.openehr.schemas.v1.VALIDITY_KIND getTimezone_validity() {
        return timezone_validity;
    }


    /**
     * Sets the timezone_validity value for this C_TIME.
     * 
     * @param timezone_validity
     */
    public void setTimezone_validity(org.openehr.schemas.v1.VALIDITY_KIND timezone_validity) {
        this.timezone_validity = timezone_validity;
    }


    /**
     * Gets the range value for this C_TIME.
     * 
     * @return range
     */
    public org.openehr.schemas.v1.IntervalOfTime getRange() {
        return range;
    }


    /**
     * Sets the range value for this C_TIME.
     * 
     * @param range
     */
    public void setRange(org.openehr.schemas.v1.IntervalOfTime range) {
        this.range = range;
    }


    /**
     * Gets the assumed_value value for this C_TIME.
     * 
     * @return assumed_value
     */
    public java.lang.String getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_TIME.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(java.lang.String assumed_value) {
        this.assumed_value = assumed_value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_TIME)) return false;
        C_TIME other = (C_TIME) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.pattern==null && other.getPattern()==null) || 
             (this.pattern!=null &&
              this.pattern.equals(other.getPattern()))) &&
            ((this.timezone_validity==null && other.getTimezone_validity()==null) || 
             (this.timezone_validity!=null &&
              this.timezone_validity.equals(other.getTimezone_validity()))) &&
            ((this.range==null && other.getRange()==null) || 
             (this.range!=null &&
              this.range.equals(other.getRange()))) &&
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
        if (getPattern() != null) {
            _hashCode += getPattern().hashCode();
        }
        if (getTimezone_validity() != null) {
            _hashCode += getTimezone_validity().hashCode();
        }
        if (getRange() != null) {
            _hashCode += getRange().hashCode();
        }
        if (getAssumed_value() != null) {
            _hashCode += getAssumed_value().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_TIME.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_TIME"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pattern");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "pattern"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timezone_validity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "timezone_validity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "VALIDITY_KIND"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("range");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "range"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
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
