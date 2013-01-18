/**
 * NON_TERMINAL_STATE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class NON_TERMINAL_STATE  extends org.openehr.schemas.v1.STATE  implements java.io.Serializable {
    private org.openehr.schemas.v1.TRANSITION[] transitions;

    public NON_TERMINAL_STATE() {
    }

    public NON_TERMINAL_STATE(
           java.lang.String name,
           org.openehr.schemas.v1.TRANSITION[] transitions) {
        super(
            name);
        this.transitions = transitions;
    }


    /**
     * Gets the transitions value for this NON_TERMINAL_STATE.
     * 
     * @return transitions
     */
    public org.openehr.schemas.v1.TRANSITION[] getTransitions() {
        return transitions;
    }


    /**
     * Sets the transitions value for this NON_TERMINAL_STATE.
     * 
     * @param transitions
     */
    public void setTransitions(org.openehr.schemas.v1.TRANSITION[] transitions) {
        this.transitions = transitions;
    }

    public org.openehr.schemas.v1.TRANSITION getTransitions(int i) {
        return this.transitions[i];
    }

    public void setTransitions(int i, org.openehr.schemas.v1.TRANSITION _value) {
        this.transitions[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NON_TERMINAL_STATE)) return false;
        NON_TERMINAL_STATE other = (NON_TERMINAL_STATE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.transitions==null && other.getTransitions()==null) || 
             (this.transitions!=null &&
              java.util.Arrays.equals(this.transitions, other.getTransitions())));
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
        if (getTransitions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransitions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransitions(), i);
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
        new org.apache.axis.description.TypeDesc(NON_TERMINAL_STATE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "NON_TERMINAL_STATE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transitions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "transitions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSITION"));
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
