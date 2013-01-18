/**
 * C_REAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_REAL  extends org.openehr.schemas.v1.C_PRIMITIVE  implements java.io.Serializable {
    private float[] list;

    private org.openehr.schemas.v1.IntervalOfReal range;

    private java.lang.Float assumed_value;

    public C_REAL() {
    }

    public C_REAL(
           float[] list,
           org.openehr.schemas.v1.IntervalOfReal range,
           java.lang.Float assumed_value) {
        this.list = list;
        this.range = range;
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the list value for this C_REAL.
     * 
     * @return list
     */
    public float[] getList() {
        return list;
    }


    /**
     * Sets the list value for this C_REAL.
     * 
     * @param list
     */
    public void setList(float[] list) {
        this.list = list;
    }

    public float getList(int i) {
        return this.list[i];
    }

    public void setList(int i, float _value) {
        this.list[i] = _value;
    }


    /**
     * Gets the range value for this C_REAL.
     * 
     * @return range
     */
    public org.openehr.schemas.v1.IntervalOfReal getRange() {
        return range;
    }


    /**
     * Sets the range value for this C_REAL.
     * 
     * @param range
     */
    public void setRange(org.openehr.schemas.v1.IntervalOfReal range) {
        this.range = range;
    }


    /**
     * Gets the assumed_value value for this C_REAL.
     * 
     * @return assumed_value
     */
    public java.lang.Float getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_REAL.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(java.lang.Float assumed_value) {
        this.assumed_value = assumed_value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_REAL)) return false;
        C_REAL other = (C_REAL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              java.util.Arrays.equals(this.list, other.getList()))) &&
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
        if (getList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(C_REAL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_REAL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("range");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "range"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfReal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
