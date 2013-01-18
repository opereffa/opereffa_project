/**
 * EXPR_UNARY_OPERATOR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXPR_UNARY_OPERATOR  extends org.openehr.schemas.v1.EXPR_OPERATOR  implements java.io.Serializable {
    private org.openehr.schemas.v1.EXPR_ITEM operand;

    public EXPR_UNARY_OPERATOR() {
    }

    public EXPR_UNARY_OPERATOR(
           java.lang.String type,
           org.openehr.schemas.v1.OPERATOR_KIND operator,
           boolean precedence_overridden,
           org.openehr.schemas.v1.EXPR_ITEM operand) {
        super(
            type,
            operator,
            precedence_overridden);
        this.operand = operand;
    }


    /**
     * Gets the operand value for this EXPR_UNARY_OPERATOR.
     * 
     * @return operand
     */
    public org.openehr.schemas.v1.EXPR_ITEM getOperand() {
        return operand;
    }


    /**
     * Sets the operand value for this EXPR_UNARY_OPERATOR.
     * 
     * @param operand
     */
    public void setOperand(org.openehr.schemas.v1.EXPR_ITEM operand) {
        this.operand = operand;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXPR_UNARY_OPERATOR)) return false;
        EXPR_UNARY_OPERATOR other = (EXPR_UNARY_OPERATOR) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.operand==null && other.getOperand()==null) || 
             (this.operand!=null &&
              this.operand.equals(other.getOperand())));
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
        if (getOperand() != null) {
            _hashCode += getOperand().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXPR_UNARY_OPERATOR.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_UNARY_OPERATOR"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "operand"));
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
