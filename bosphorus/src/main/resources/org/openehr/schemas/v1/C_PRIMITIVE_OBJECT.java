/**
 * C_PRIMITIVE_OBJECT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_PRIMITIVE_OBJECT  extends org.openehr.schemas.v1.C_DEFINED_OBJECT  implements java.io.Serializable {
    private org.openehr.schemas.v1.C_PRIMITIVE item;

    public C_PRIMITIVE_OBJECT() {
    }

    public C_PRIMITIVE_OBJECT(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.C_PRIMITIVE item) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.item = item;
    }


    /**
     * Gets the item value for this C_PRIMITIVE_OBJECT.
     * 
     * @return item
     */
    public org.openehr.schemas.v1.C_PRIMITIVE getItem() {
        return item;
    }


    /**
     * Sets the item value for this C_PRIMITIVE_OBJECT.
     * 
     * @param item
     */
    public void setItem(org.openehr.schemas.v1.C_PRIMITIVE item) {
        this.item = item;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_PRIMITIVE_OBJECT)) return false;
        C_PRIMITIVE_OBJECT other = (C_PRIMITIVE_OBJECT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.item==null && other.getItem()==null) || 
             (this.item!=null &&
              this.item.equals(other.getItem())));
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
        if (getItem() != null) {
            _hashCode += getItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_PRIMITIVE_OBJECT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_PRIMITIVE_OBJECT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_PRIMITIVE"));
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
