/**
 * C_QUANTITY_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_QUANTITY_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.IntervalOfReal magnitude;

    private org.openehr.schemas.v1.IntervalOfInteger precision;

    private java.lang.String units;

    public C_QUANTITY_ITEM() {
    }

    public C_QUANTITY_ITEM(
           org.openehr.schemas.v1.IntervalOfReal magnitude,
           org.openehr.schemas.v1.IntervalOfInteger precision,
           java.lang.String units) {
           this.magnitude = magnitude;
           this.precision = precision;
           this.units = units;
    }


    /**
     * Gets the magnitude value for this C_QUANTITY_ITEM.
     * 
     * @return magnitude
     */
    public org.openehr.schemas.v1.IntervalOfReal getMagnitude() {
        return magnitude;
    }


    /**
     * Sets the magnitude value for this C_QUANTITY_ITEM.
     * 
     * @param magnitude
     */
    public void setMagnitude(org.openehr.schemas.v1.IntervalOfReal magnitude) {
        this.magnitude = magnitude;
    }


    /**
     * Gets the precision value for this C_QUANTITY_ITEM.
     * 
     * @return precision
     */
    public org.openehr.schemas.v1.IntervalOfInteger getPrecision() {
        return precision;
    }


    /**
     * Sets the precision value for this C_QUANTITY_ITEM.
     * 
     * @param precision
     */
    public void setPrecision(org.openehr.schemas.v1.IntervalOfInteger precision) {
        this.precision = precision;
    }


    /**
     * Gets the units value for this C_QUANTITY_ITEM.
     * 
     * @return units
     */
    public java.lang.String getUnits() {
        return units;
    }


    /**
     * Sets the units value for this C_QUANTITY_ITEM.
     * 
     * @param units
     */
    public void setUnits(java.lang.String units) {
        this.units = units;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_QUANTITY_ITEM)) return false;
        C_QUANTITY_ITEM other = (C_QUANTITY_ITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.magnitude==null && other.getMagnitude()==null) || 
             (this.magnitude!=null &&
              this.magnitude.equals(other.getMagnitude()))) &&
            ((this.precision==null && other.getPrecision()==null) || 
             (this.precision!=null &&
              this.precision.equals(other.getPrecision()))) &&
            ((this.units==null && other.getUnits()==null) || 
             (this.units!=null &&
              this.units.equals(other.getUnits())));
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
        if (getMagnitude() != null) {
            _hashCode += getMagnitude().hashCode();
        }
        if (getPrecision() != null) {
            _hashCode += getPrecision().hashCode();
        }
        if (getUnits() != null) {
            _hashCode += getUnits().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_QUANTITY_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_QUANTITY_ITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("magnitude");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "magnitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfReal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "precision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("units");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "units"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
