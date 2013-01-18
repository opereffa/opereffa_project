/**
 * EXPR_LEAF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXPR_LEAF  extends org.openehr.schemas.v1.EXPR_ITEM  implements java.io.Serializable {
    private java.lang.Object item;

    private java.lang.String reference_type;

    public EXPR_LEAF() {
    }

    public EXPR_LEAF(
           java.lang.String type,
           java.lang.Object item,
           java.lang.String reference_type) {
        super(
            type);
        this.item = item;
        this.reference_type = reference_type;
    }


    /**
     * Gets the item value for this EXPR_LEAF.
     * 
     * @return item
     */
    public java.lang.Object getItem() {
        return item;
    }


    /**
     * Sets the item value for this EXPR_LEAF.
     * 
     * @param item
     */
    public void setItem(java.lang.Object item) {
        this.item = item;
    }


    /**
     * Gets the reference_type value for this EXPR_LEAF.
     * 
     * @return reference_type
     */
    public java.lang.String getReference_type() {
        return reference_type;
    }


    /**
     * Sets the reference_type value for this EXPR_LEAF.
     * 
     * @param reference_type
     */
    public void setReference_type(java.lang.String reference_type) {
        this.reference_type = reference_type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXPR_LEAF)) return false;
        EXPR_LEAF other = (EXPR_LEAF) obj;
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
              this.item.equals(other.getItem()))) &&
            ((this.reference_type==null && other.getReference_type()==null) || 
             (this.reference_type!=null &&
              this.reference_type.equals(other.getReference_type())));
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
        if (getReference_type() != null) {
            _hashCode += getReference_type().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXPR_LEAF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_LEAF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference_type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "reference_type"));
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
