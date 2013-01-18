/**
 * C_ATTRIBUTE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class C_ATTRIBUTE  extends org.openehr.schemas.v1.ARCHETYPE_CONSTRAINT  implements java.io.Serializable {
    private java.lang.String rm_attribute_name;

    private org.openehr.schemas.v1.IntervalOfInteger existence;

    private org.openehr.schemas.v1.C_OBJECT[] children;

    public C_ATTRIBUTE() {
    }

    public C_ATTRIBUTE(
           java.lang.String rm_attribute_name,
           org.openehr.schemas.v1.IntervalOfInteger existence,
           org.openehr.schemas.v1.C_OBJECT[] children) {
        this.rm_attribute_name = rm_attribute_name;
        this.existence = existence;
        this.children = children;
    }


    /**
     * Gets the rm_attribute_name value for this C_ATTRIBUTE.
     * 
     * @return rm_attribute_name
     */
    public java.lang.String getRm_attribute_name() {
        return rm_attribute_name;
    }


    /**
     * Sets the rm_attribute_name value for this C_ATTRIBUTE.
     * 
     * @param rm_attribute_name
     */
    public void setRm_attribute_name(java.lang.String rm_attribute_name) {
        this.rm_attribute_name = rm_attribute_name;
    }


    /**
     * Gets the existence value for this C_ATTRIBUTE.
     * 
     * @return existence
     */
    public org.openehr.schemas.v1.IntervalOfInteger getExistence() {
        return existence;
    }


    /**
     * Sets the existence value for this C_ATTRIBUTE.
     * 
     * @param existence
     */
    public void setExistence(org.openehr.schemas.v1.IntervalOfInteger existence) {
        this.existence = existence;
    }


    /**
     * Gets the children value for this C_ATTRIBUTE.
     * 
     * @return children
     */
    public org.openehr.schemas.v1.C_OBJECT[] getChildren() {
        return children;
    }


    /**
     * Sets the children value for this C_ATTRIBUTE.
     * 
     * @param children
     */
    public void setChildren(org.openehr.schemas.v1.C_OBJECT[] children) {
        this.children = children;
    }

    public org.openehr.schemas.v1.C_OBJECT getChildren(int i) {
        return this.children[i];
    }

    public void setChildren(int i, org.openehr.schemas.v1.C_OBJECT _value) {
        this.children[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_ATTRIBUTE)) return false;
        C_ATTRIBUTE other = (C_ATTRIBUTE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.rm_attribute_name==null && other.getRm_attribute_name()==null) || 
             (this.rm_attribute_name!=null &&
              this.rm_attribute_name.equals(other.getRm_attribute_name()))) &&
            ((this.existence==null && other.getExistence()==null) || 
             (this.existence!=null &&
              this.existence.equals(other.getExistence()))) &&
            ((this.children==null && other.getChildren()==null) || 
             (this.children!=null &&
              java.util.Arrays.equals(this.children, other.getChildren())));
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
        if (getRm_attribute_name() != null) {
            _hashCode += getRm_attribute_name().hashCode();
        }
        if (getExistence() != null) {
            _hashCode += getExistence().hashCode();
        }
        if (getChildren() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChildren());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChildren(), i);
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
        new org.apache.axis.description.TypeDesc(C_ATTRIBUTE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_ATTRIBUTE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rm_attribute_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "rm_attribute_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "existence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("children");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "children"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_OBJECT"));
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
