/**
 * ARCHETYPE_SLOT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ARCHETYPE_SLOT  extends org.openehr.schemas.v1.C_OBJECT  implements java.io.Serializable {
    private org.openehr.schemas.v1.ASSERTION[] includes;

    private org.openehr.schemas.v1.ASSERTION[] excludes;

    public ARCHETYPE_SLOT() {
    }

    public ARCHETYPE_SLOT(
           java.lang.String rm_type_name,
           org.openehr.schemas.v1.IntervalOfInteger occurrences,
           java.lang.String node_id,
           org.openehr.schemas.v1.ASSERTION[] includes,
           org.openehr.schemas.v1.ASSERTION[] excludes) {
        super(
            rm_type_name,
            occurrences,
            node_id);
        this.includes = includes;
        this.excludes = excludes;
    }


    /**
     * Gets the includes value for this ARCHETYPE_SLOT.
     * 
     * @return includes
     */
    public org.openehr.schemas.v1.ASSERTION[] getIncludes() {
        return includes;
    }


    /**
     * Sets the includes value for this ARCHETYPE_SLOT.
     * 
     * @param includes
     */
    public void setIncludes(org.openehr.schemas.v1.ASSERTION[] includes) {
        this.includes = includes;
    }

    public org.openehr.schemas.v1.ASSERTION getIncludes(int i) {
        return this.includes[i];
    }

    public void setIncludes(int i, org.openehr.schemas.v1.ASSERTION _value) {
        this.includes[i] = _value;
    }


    /**
     * Gets the excludes value for this ARCHETYPE_SLOT.
     * 
     * @return excludes
     */
    public org.openehr.schemas.v1.ASSERTION[] getExcludes() {
        return excludes;
    }


    /**
     * Sets the excludes value for this ARCHETYPE_SLOT.
     * 
     * @param excludes
     */
    public void setExcludes(org.openehr.schemas.v1.ASSERTION[] excludes) {
        this.excludes = excludes;
    }

    public org.openehr.schemas.v1.ASSERTION getExcludes(int i) {
        return this.excludes[i];
    }

    public void setExcludes(int i, org.openehr.schemas.v1.ASSERTION _value) {
        this.excludes[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ARCHETYPE_SLOT)) return false;
        ARCHETYPE_SLOT other = (ARCHETYPE_SLOT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.includes==null && other.getIncludes()==null) || 
             (this.includes!=null &&
              java.util.Arrays.equals(this.includes, other.getIncludes()))) &&
            ((this.excludes==null && other.getExcludes()==null) || 
             (this.excludes!=null &&
              java.util.Arrays.equals(this.excludes, other.getExcludes())));
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
        if (getIncludes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIncludes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIncludes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExcludes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExcludes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExcludes(), i);
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
        new org.apache.axis.description.TypeDesc(ARCHETYPE_SLOT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_SLOT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "includes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "excludes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION"));
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
