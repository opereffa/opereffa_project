/**
 * C_STRING.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class C_STRING  extends org.openehr.schemas.v1.C_PRIMITIVE  implements java.io.Serializable {
    private java.lang.String pattern;

    private java.lang.String[] list;

    private java.lang.Boolean list_open;

    private java.lang.String assumed_value;

    public C_STRING() {
    }

    public C_STRING(
           java.lang.String pattern,
           java.lang.String[] list,
           java.lang.Boolean list_open,
           java.lang.String assumed_value) {
        this.pattern = pattern;
        this.list = list;
        this.list_open = list_open;
        this.assumed_value = assumed_value;
    }


    /**
     * Gets the pattern value for this C_STRING.
     * 
     * @return pattern
     */
    public java.lang.String getPattern() {
        return pattern;
    }


    /**
     * Sets the pattern value for this C_STRING.
     * 
     * @param pattern
     */
    public void setPattern(java.lang.String pattern) {
        this.pattern = pattern;
    }


    /**
     * Gets the list value for this C_STRING.
     * 
     * @return list
     */
    public java.lang.String[] getList() {
        return list;
    }


    /**
     * Sets the list value for this C_STRING.
     * 
     * @param list
     */
    public void setList(java.lang.String[] list) {
        this.list = list;
    }

    public java.lang.String getList(int i) {
        return this.list[i];
    }

    public void setList(int i, java.lang.String _value) {
        this.list[i] = _value;
    }


    /**
     * Gets the list_open value for this C_STRING.
     * 
     * @return list_open
     */
    public java.lang.Boolean getList_open() {
        return list_open;
    }


    /**
     * Sets the list_open value for this C_STRING.
     * 
     * @param list_open
     */
    public void setList_open(java.lang.Boolean list_open) {
        this.list_open = list_open;
    }


    /**
     * Gets the assumed_value value for this C_STRING.
     * 
     * @return assumed_value
     */
    public java.lang.String getAssumed_value() {
        return assumed_value;
    }


    /**
     * Sets the assumed_value value for this C_STRING.
     * 
     * @param assumed_value
     */
    public void setAssumed_value(java.lang.String assumed_value) {
        this.assumed_value = assumed_value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof C_STRING)) return false;
        C_STRING other = (C_STRING) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.pattern==null && other.getPattern()==null) || 
             (this.pattern!=null &&
              this.pattern.equals(other.getPattern()))) &&
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              java.util.Arrays.equals(this.list, other.getList()))) &&
            ((this.list_open==null && other.getList_open()==null) || 
             (this.list_open!=null &&
              this.list_open.equals(other.getList_open()))) &&
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
        if (getPattern() != null) {
            _hashCode += getPattern().hashCode();
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
        if (getList_open() != null) {
            _hashCode += getList_open().hashCode();
        }
        if (getAssumed_value() != null) {
            _hashCode += getAssumed_value().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(C_STRING.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_STRING"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pattern");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "pattern"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list_open");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "list_open"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assumed_value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "assumed_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
