/**
 * EXPR_OPERATOR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class EXPR_OPERATOR  extends org.openehr.schemas.v1.EXPR_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.OPERATOR_KIND operator;

    private boolean precedence_overridden;

    public EXPR_OPERATOR() {
    }

    public EXPR_OPERATOR(
           java.lang.String type,
           org.openehr.schemas.v1.OPERATOR_KIND operator,
           boolean precedence_overridden) {
        super(
            type);
        this.operator = operator;
        this.precedence_overridden = precedence_overridden;
    }


    /**
     * Gets the operator value for this EXPR_OPERATOR.
     * 
     * @return operator
     */
    public org.openehr.schemas.v1.OPERATOR_KIND getOperator() {
        return operator;
    }


    /**
     * Sets the operator value for this EXPR_OPERATOR.
     * 
     * @param operator
     */
    public void setOperator(org.openehr.schemas.v1.OPERATOR_KIND operator) {
        this.operator = operator;
    }


    /**
     * Gets the precedence_overridden value for this EXPR_OPERATOR.
     * 
     * @return precedence_overridden
     */
    public boolean isPrecedence_overridden() {
        return precedence_overridden;
    }


    /**
     * Sets the precedence_overridden value for this EXPR_OPERATOR.
     * 
     * @param precedence_overridden
     */
    public void setPrecedence_overridden(boolean precedence_overridden) {
        this.precedence_overridden = precedence_overridden;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXPR_OPERATOR)) return false;
        EXPR_OPERATOR other = (EXPR_OPERATOR) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.operator==null && other.getOperator()==null) || 
             (this.operator!=null &&
              this.operator.equals(other.getOperator()))) &&
            this.precedence_overridden == other.isPrecedence_overridden();
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
        if (getOperator() != null) {
            _hashCode += getOperator().hashCode();
        }
        _hashCode += (isPrecedence_overridden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXPR_OPERATOR.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_OPERATOR"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OPERATOR_KIND"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precedence_overridden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "precedence_overridden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
