/**
 * C_DV_QUANTITY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_DV_QUANTITY  extends org.openehr.schemas.v1.C_DOMAIN_TYPE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_QUANTITY assumed_value;

    private org.openehr.schemas.v1.CODE_PHRASE property;

    private org.openehr.schemas.v1.C_QUANTITY_ITEM[] list;

    public C_DV_QUANTITY() {
    }

    public C_DV_QUANTITY(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.DV_QUANTITY assumed_value,
           org.openehr.schemas.v1.CODE_PHRASE property,
           org.openehr.schemas.v1.C_QUANTITY_ITEM[] list) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.assumed_value = assumed_value;
        this.property = property;
        this.list = list;
    }


    /**
     * Gets the assumed_value value for this C_DV_QUANTITY.
     * 
     * @return assumed_value
     */
    public org.openehr.schemas.v1.DV_QUANTITY getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_DV_QUANTITY.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(org.openehr.schemas.v1.DV_QUANTITY assumed_value) {
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the property value for this C_DV_QUANTITY.
     * 
     * @return property
     */
    public org.openehr.schemas.v1.CODE_PHRASE getProperty() {
        return property;
    }


    /**
     * Sets the property value for this C_DV_QUANTITY.
     * 
     * @param property
     */
    public void setProperty(org.openehr.schemas.v1.CODE_PHRASE property) {
        this.property = property;
    }


    /**
     * Gets the list value for this C_DV_QUANTITY.
     * 
     * @return list
     */
    public org.openehr.schemas.v1.C_QUANTITY_ITEM[] getList() {
        return list;
    }


    /**
     * Sets the list value for this C_DV_QUANTITY.
     * 
     * @param list
     */
    public void setList(org.openehr.schemas.v1.C_QUANTITY_ITEM[] list) {
        this.list = list;
    }

    public org.openehr.schemas.v1.C_QUANTITY_ITEM getList(int i) {
        return this.list[i];
    }

    public void setList(int i, org.openehr.schemas.v1.C_QUANTITY_ITEM _value) {
        this.list[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_DV_QUANTITY)) return false;
        C_DV_QUANTITY other = (C_DV_QUANTITY) obj;
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
            ((this.property==null && other.getProperty()==null) || 
             (this.property!=null &&
              this.property.equals(other.getProperty()))) &&
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
        if (getProperty() != null) {
            _hashCode += getProperty().hashCode();
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
        new org.apache.axis.description.TypeDesc(C_DV_QUANTITY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DV_QUANTITY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_QUANTITY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("property");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "property"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_QUANTITY_ITEM"));
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
