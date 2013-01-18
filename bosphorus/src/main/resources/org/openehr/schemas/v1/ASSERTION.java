/**
 * ASSERTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ASSERTION  implements java.io.Serializable {
    private java.lang.String tag;

    private java.lang.String string_expression;

    private org.openehr.schemas.v1.EXPR_ITEM expression;

    private org.openehr.schemas.v1.ASSERTION_VARIABLE[] variables;

    public ASSERTION() {
    }

    public ASSERTION(
           java.lang.String tag,
           java.lang.String string_expression,
           org.openehr.schemas.v1.EXPR_ITEM expression,
           org.openehr.schemas.v1.ASSERTION_VARIABLE[] variables) {
           this.tag = tag;
           this.string_expression = string_expression;
           this.expression = expression;
           this.variables = variables;
    }


    /**
     * Gets the tag value for this ASSERTION.
     * 
     * @return tag
     */
    public java.lang.String getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this ASSERTION.
     * 
     * @param tag
     */
    public void setTag(java.lang.String tag) {
        this.tag = tag;
    }


    /**
     * Gets the string_expression value for this ASSERTION.
     * 
     * @return string_expression
     */
    public java.lang.String getString_expression() {
        return string_expression;
    }


    /**
     * Sets the string_expression value for this ASSERTION.
     * 
     * @param string_expression
     */
    public void setString_expression(java.lang.String string_expression) {
        this.string_expression = string_expression;
    }


    /**
     * Gets the expression value for this ASSERTION.
     * 
     * @return expression
     */
    public org.openehr.schemas.v1.EXPR_ITEM getExpression() {
        return expression;
    }


    /**
     * Sets the expression value for this ASSERTION.
     * 
     * @param expression
     */
    public void setExpression(org.openehr.schemas.v1.EXPR_ITEM expression) {
        this.expression = expression;
    }


    /**
     * Gets the variables value for this ASSERTION.
     * 
     * @return variables
     */
    public org.openehr.schemas.v1.ASSERTION_VARIABLE[] getVariables() {
        return variables;
    }


    /**
     * Sets the variables value for this ASSERTION.
     * 
     * @param variables
     */
    public void setVariables(org.openehr.schemas.v1.ASSERTION_VARIABLE[] variables) {
        this.variables = variables;
    }

    public org.openehr.schemas.v1.ASSERTION_VARIABLE getVariables(int i) {
        return this.variables[i];
    }

    public void setVariables(int i, org.openehr.schemas.v1.ASSERTION_VARIABLE _value) {
        this.variables[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ASSERTION)) return false;
        ASSERTION other = (ASSERTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tag==null && other.getTag()==null) || 
             (this.tag!=null &&
              this.tag.equals(other.getTag()))) &&
            ((this.string_expression==null && other.getString_expression()==null) || 
             (this.string_expression!=null &&
              this.string_expression.equals(other.getString_expression()))) &&
            ((this.expression==null && other.getExpression()==null) || 
             (this.expression!=null &&
              this.expression.equals(other.getExpression()))) &&
            ((this.variables==null && other.getVariables()==null) || 
             (this.variables!=null &&
              java.util.Arrays.equals(this.variables, other.getVariables())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getString_expression() != null) {
            _hashCode += getString_expression().hashCode();
        }
        if (getExpression() != null) {
            _hashCode += getExpression().hashCode();
        }
        if (getVariables() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVariables());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVariables(), i);
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
        new org.apache.axis.description.TypeDesc(ASSERTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "tag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("string_expression");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "string_expression"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expression");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "expression"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_ITEM"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variables");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "variables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION_VARIABLE"));
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
