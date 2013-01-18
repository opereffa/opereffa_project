/**
 * CARDINALITY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class CARDINALITY  implements java.io.Serializable {
    private boolean is_ordered;

    private boolean is_unique;

    private org.openehr.schemas.v1.IntervalOfInteger interval;

    public CARDINALITY() {
    }

    public CARDINALITY(
           boolean is_ordered,
           boolean is_unique,
           org.openehr.schemas.v1.IntervalOfInteger interval) {
           this.is_ordered = is_ordered;
           this.is_unique = is_unique;
           this.interval = interval;
    }


    /**
     * Gets the is_ordered value for this CARDINALITY.
     * 
     * @return is_ordered
     */
    public boolean isIs_ordered() {
        return is_ordered;
    }


    /**
     * Sets the is_ordered value for this CARDINALITY.
     * 
     * @param is_ordered
     */
    public void setIs_ordered(boolean is_ordered) {
        this.is_ordered = is_ordered;
    }


    /**
     * Gets the is_unique value for this CARDINALITY.
     * 
     * @return is_unique
     */
    public boolean isIs_unique() {
        return is_unique;
    }


    /**
     * Sets the is_unique value for this CARDINALITY.
     * 
     * @param is_unique
     */
    public void setIs_unique(boolean is_unique) {
        this.is_unique = is_unique;
    }


    /**
     * Gets the interval value for this CARDINALITY.
     * 
     * @return interval
     */
    public org.openehr.schemas.v1.IntervalOfInteger getInterval() {
        return interval;
    }


    /**
     * Sets the interval value for this CARDINALITY.
     * 
     * @param interval
     */
    public void setInterval(org.openehr.schemas.v1.IntervalOfInteger interval) {
        this.interval = interval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CARDINALITY)) return false;
        CARDINALITY other = (CARDINALITY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.is_ordered == other.isIs_ordered() &&
            this.is_unique == other.isIs_unique() &&
            ((this.interval==null && other.getInterval()==null) || 
             (this.interval!=null &&
              this.interval.equals(other.getInterval())));
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
        _hashCode += (isIs_ordered() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIs_unique() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInterval() != null) {
            _hashCode += getInterval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CARDINALITY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CARDINALITY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_unique");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_unique"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "interval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfInteger"));
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
