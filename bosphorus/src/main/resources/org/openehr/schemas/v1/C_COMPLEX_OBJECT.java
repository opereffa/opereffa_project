/**
 * C_COMPLEX_OBJECT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_COMPLEX_OBJECT  extends org.openehr.schemas.v1.C_DEFINED_OBJECT  implements java.io.Serializable {
    private org.openehr.schemas.v1.C_ATTRIBUTE[] attributes;

    public C_COMPLEX_OBJECT() {
    }

    public C_COMPLEX_OBJECT(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.C_ATTRIBUTE[] attributes) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.attributes = attributes;
    }


    /**
     * Gets the attributes value for this C_COMPLEX_OBJECT.
     * 
     * @return attributes
     */
    public org.openehr.schemas.v1.C_ATTRIBUTE[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this C_COMPLEX_OBJECT.
     * 
     * @param attributes
     */
    public void setAttributes(org.openehr.schemas.v1.C_ATTRIBUTE[] attributes) {
        this.attributes = attributes;
    }

    public org.openehr.schemas.v1.C_ATTRIBUTE getAttributes(int i) {
        return this.attributes[i];
    }

    public void setAttributes(int i, org.openehr.schemas.v1.C_ATTRIBUTE _value) {
        this.attributes[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_COMPLEX_OBJECT)) return false;
        C_COMPLEX_OBJECT other = (C_COMPLEX_OBJECT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.attributes==null && other.getAttributes()==null) || 
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes())));
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
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributes(), i);
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
        new org.apache.axis.description.TypeDesc(C_COMPLEX_OBJECT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_COMPLEX_OBJECT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_ATTRIBUTE"));
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
