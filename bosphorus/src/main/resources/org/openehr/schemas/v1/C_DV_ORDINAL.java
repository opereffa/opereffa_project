/**
 * C_DV_ORDINAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_DV_ORDINAL  extends org.openehr.schemas.v1.C_DOMAIN_TYPE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_ORDINAL assumed_value;

    private org.openehr.schemas.v1.DV_ORDINAL[] list;

    public C_DV_ORDINAL() {
    }

    public C_DV_ORDINAL(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.DV_ORDINAL assumed_value,
           org.openehr.schemas.v1.DV_ORDINAL[] list) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.assumed_value = assumed_value;
        this.list = list;
    }


    /**
     * Gets the assumed_value value for this C_DV_ORDINAL.
     * 
     * @return assumed_value
     */
    public org.openehr.schemas.v1.DV_ORDINAL getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_DV_ORDINAL.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(org.openehr.schemas.v1.DV_ORDINAL assumed_value) {
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the list value for this C_DV_ORDINAL.
     * 
     * @return list
     */
    public org.openehr.schemas.v1.DV_ORDINAL[] getList() {
        return list;
    }


    /**
     * Sets the list value for this C_DV_ORDINAL.
     * 
     * @param list
     */
    public void setList(org.openehr.schemas.v1.DV_ORDINAL[] list) {
        this.list = list;
    }

    public org.openehr.schemas.v1.DV_ORDINAL getList(int i) {
        return this.list[i];
    }

    public void setList(int i, org.openehr.schemas.v1.DV_ORDINAL _value) {
        this.list[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_DV_ORDINAL)) return false;
        C_DV_ORDINAL other = (C_DV_ORDINAL) obj;
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
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              java.util.Arrays.equals(this.list, other.getList())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_DV_ORDINAL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DV_ORDINAL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDINAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDINAL"));
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
