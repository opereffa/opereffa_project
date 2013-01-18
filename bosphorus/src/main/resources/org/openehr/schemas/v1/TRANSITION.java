/**
 * TRANSITION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class TRANSITION  implements java.io.Serializable {
    private java.lang.String event;

    private java.lang.String action;

    private java.lang.String guard;

    private org.openehr.schemas.v1.STATE next_state;

    public TRANSITION() {
    }

    public TRANSITION(
           java.lang.String event,
           java.lang.String action,
           java.lang.String guard,
           org.openehr.schemas.v1.STATE next_state) {
           this.event = event;
           this.action = action;
           this.guard = guard;
           this.next_state = next_state;
    }


    /**
     * Gets the event value for this TRANSITION.
     * 
     * @return event
     */
    public java.lang.String getEvent() {
        return event;
    }


    /**
     * Sets the event value for this TRANSITION.
     * 
     * @param event
     */
    public void setEvent(java.lang.String event) {
        this.event = event;
    }


    /**
     * Gets the action value for this TRANSITION.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this TRANSITION.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the guard value for this TRANSITION.
     * 
     * @return guard
     */
    public java.lang.String getGuard() {
        return guard;
    }


    /**
     * Sets the guard value for this TRANSITION.
     * 
     * @param guard
     */
    public void setGuard(java.lang.String guard) {
        this.guard = guard;
    }


    /**
     * Gets the next_state value for this TRANSITION.
     * 
     * @return next_state
     */
    public org.openehr.schemas.v1.STATE getNext_state() {
        return next_state;
    }


    /**
     * Sets the next_state value for this TRANSITION.
     * 
     * @param next_state
     */
    public void setNext_state(org.openehr.schemas.v1.STATE next_state) {
        this.next_state = next_state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TRANSITION)) return false;
        TRANSITION other = (TRANSITION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.event==null && other.getEvent()==null) || 
             (this.event!=null &&
              this.event.equals(other.getEvent()))) &&
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.guard==null && other.getGuard()==null) || 
             (this.guard!=null &&
              this.guard.equals(other.getGuard()))) &&
            ((this.next_state==null && other.getNext_state()==null) || 
             (this.next_state!=null &&
              this.next_state.equals(other.getNext_state())));
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
        if (getEvent() != null) {
            _hashCode += getEvent().hashCode();
        }
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getGuard() != null) {
            _hashCode += getGuard().hashCode();
        }
        if (getNext_state() != null) {
            _hashCode += getNext_state().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TRANSITION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSITION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("event");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "event"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "guard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("next_state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "next_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "STATE"));
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
