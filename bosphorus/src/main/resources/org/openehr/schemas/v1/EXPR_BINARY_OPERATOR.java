/**
 * EXPR_BINARY_OPERATOR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXPR_BINARY_OPERATOR  extends org.openehr.schemas.v1.EXPR_OPERATOR  implements java.io.Serializable {
    private org.openehr.schemas.v1.EXPR_ITEM left_operand;

    private org.openehr.schemas.v1.EXPR_ITEM right_operand;

    public EXPR_BINARY_OPERATOR() {
    }

    public EXPR_BINARY_OPERATOR(
           java.lang.String type,
           org.openehr.schemas.v1.OPERATOR_KIND operator,
           boolean precedence_overridden,
           org.openehr.schemas.v1.EXPR_ITEM left_operand,
           org.openehr.schemas.v1.EXPR_ITEM right_operand) {
        super(
            type,
            operator,
            precedence_overridden);
        this.left_operand = left_operand;
        this.right_operand = right_operand;
    }


    /**
     * Gets the left_operand value for this EXPR_BINARY_OPERATOR.
     * 
     * @return left_operand
     */
    public org.openehr.schemas.v1.EXPR_ITEM getLeft_operand() {
        return left_operand;
    }


    /**
     * Sets the left_operand value for this EXPR_BINARY_OPERATOR.
     * 
     * @param left_operand
     */
    public void setLeft_operand(org.openehr.schemas.v1.EXPR_ITEM left_operand) {
        this.left_operand = left_operand;
    }


    /**
     * Gets the right_operand value for this EXPR_BINARY_OPERATOR.
     * 
     * @return right_operand
     */
    public org.openehr.schemas.v1.EXPR_ITEM getRight_operand() {
        return right_operand;
    }


    /**
     * Sets the right_operand value for this EXPR_BINARY_OPERATOR.
     * 
     * @param right_operand
     */
    public void setRight_operand(org.openehr.schemas.v1.EXPR_ITEM right_operand) {
        this.right_operand = right_operand;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXPR_BINARY_OPERATOR)) return false;
        EXPR_BINARY_OPERATOR other = (EXPR_BINARY_OPERATOR) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.left_operand==null && other.getLeft_operand()==null) || 
             (this.left_operand!=null &&
              this.left_operand.equals(other.getLeft_operand()))) &&
            ((this.right_operand==null && other.getRight_operand()==null) || 
             (this.right_operand!=null &&
              this.right_operand.equals(other.getRight_operand())));
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
        if (getLeft_operand() != null) {
            _hashCode += getLeft_operand().hashCode();
        }
        if (getRight_operand() != null) {
            _hashCode += getRight_operand().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXPR_BINARY_OPERATOR.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_BINARY_OPERATOR"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("left_operand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "left_operand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_ITEM"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("right_operand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "right_operand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_ITEM"));
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
