/**
 * ARCHETYPE_INTERNAL_REF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ARCHETYPE_INTERNAL_REF  extends org.openehr.schemas.v1.C_OBJECT  implements java.io.Serializable {
    private java.lang.String target_path;

    public ARCHETYPE_INTERNAL_REF() {
    }

    public ARCHETYPE_INTERNAL_REF(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           java.lang.String target_path) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.target_path = target_path;
    }


    /**
     * Gets the target_path value for this ARCHETYPE_INTERNAL_REF.
     * 
     * @return target_path
     */
    public java.lang.String getTarget_path() {
        return target_path;
    }


    /**
     * Sets the target_path value for this ARCHETYPE_INTERNAL_REF.
     * 
     * @param target_path
     */
    public void setTarget_path(java.lang.String target_path) {
        this.target_path = target_path;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ARCHETYPE_INTERNAL_REF)) return false;
        ARCHETYPE_INTERNAL_REF other = (ARCHETYPE_INTERNAL_REF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.target_path==null && other.getTarget_path()==null) || 
             (this.target_path!=null &&
              this.target_path.equals(other.getTarget_path())));
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
        if (getTarget_path() != null) {
            _hashCode += getTarget_path().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ARCHETYPE_INTERNAL_REF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_INTERNAL_REF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target_path");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "target_path"));
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
