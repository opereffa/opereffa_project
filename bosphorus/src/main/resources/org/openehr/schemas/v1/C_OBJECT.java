/**
 * C_OBJECT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class C_OBJECT  extends org.openehr.schemas.v1.ARCHETYPE_CONSTRAINT  implements java.io.Serializable {
    private java.lang.String rm_type_name;

    private org.openehr.schemas.v1.IntervalOfInteger occurrences;

    private java.lang.String node_id;

    public C_OBJECT() {
    }

    public C_OBJECT(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id) {
        this.rm_type_name = rm_type_name;
        this.occurrences = occurrences;
        this.node_id = node_id;
    }


    /**
     * Gets the rm_type_name value for this C_OBJECT.
     * 
     * @return rm_type_name
     */
    public java.lang.String getRm_type_name() {
        return rm_type_name;
    }


    /**
     * Sets the rm_type_name value for this C_OBJECT.
     * 
     * @param rm_type_name
     */
    public void setRm_type_name(java.lang.String rm_type_name) {
        this.rm_type_name = rm_type_name;
    }


    /**
     * Gets the occurrences value for this C_OBJECT.
     * 
     * @return occurrences
     */
    public org.openehr.schemas.v1.IntervalOfInteger getOccurrences() {
        return occurrences;
    }


    /**
     * Sets the occurrences value for this C_OBJECT.
     * 
     * @param occurrences
     */
    public void setOccurrences(org.openehr.schemas.v1.IntervalOfInteger occurrences) {
        this.occurrences = occurrences;
    }


    /**
     * Gets the node_id value for this C_OBJECT.
     * 
     * @return node_id
     */
    public java.lang.String getNode_id() {
        return node_id;
    }


    /**
     * Sets the node_id value for this C_OBJECT.
     * 
     * @param node_id
     */
    public void setNode_id(java.lang.String node_id) {
        this.node_id = node_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_OBJECT)) return false;
        C_OBJECT other = (C_OBJECT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.rm_type_name==null && other.getRm_type_name()==null) || 
             (this.rm_type_name!=null &&
              this.rm_type_name.equals(other.getRm_type_name()))) &&
            ((this.occurrences==null && other.getOccurrences()==null) || 
             (this.occurrences!=null &&
              this.occurrences.equals(other.getOccurrences()))) &&
            ((this.node_id==null && other.getNode_id()==null) || 
             (this.node_id!=null &&
              this.node_id.equals(other.getNode_id())));
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
        if (getRm_type_name() != null) {
            _hashCode += getRm_type_name().hashCode();
        }
        if (getOccurrences() != null) {
            _hashCode += getOccurrences().hashCode();
        }
        if (getNode_id() != null) {
            _hashCode += getNode_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_OBJECT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_OBJECT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rm_type_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "rm_type_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occurrences");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "occurrences"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("node_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "node_id"));
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
