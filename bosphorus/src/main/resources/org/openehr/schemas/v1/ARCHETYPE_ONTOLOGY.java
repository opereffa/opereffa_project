/**
 * ARCHETYPE_ONTOLOGY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ARCHETYPE_ONTOLOGY  implements java.io.Serializable {
    private org.openehr.schemas.v1.StringDictionaryItem[][][] term_definitions;

    private org.openehr.schemas.v1.StringDictionaryItem[][][] constraint_definitions;

    private org.openehr.schemas.v1.TERM_BINDING_ITEM[][] term_bindings;

    private org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[][] constraint_bindings;

    public ARCHETYPE_ONTOLOGY() {
    }

    public ARCHETYPE_ONTOLOGY(
           org.openehr.schemas.v1.StringDictionaryItem[][][] term_definitions,
           org.openehr.schemas.v1.StringDictionaryItem[][][] constraint_definitions,
           org.openehr.schemas.v1.TERM_BINDING_ITEM[][] term_bindings,
           org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[][] constraint_bindings) {
           this.term_definitions = term_definitions;
           this.constraint_definitions = constraint_definitions;
           this.term_bindings = term_bindings;
           this.constraint_bindings = constraint_bindings;
    }


    /**
     * Gets the term_definitions value for this ARCHETYPE_ONTOLOGY.
     * 
     * @return term_definitions
     */
    public org.openehr.schemas.v1.StringDictionaryItem[][][] getTerm_definitions() {
        return term_definitions;
    }


    /**
     * Sets the term_definitions value for this ARCHETYPE_ONTOLOGY.
     * 
     * @param term_definitions
     */
    public void setTerm_definitions(org.openehr.schemas.v1.StringDictionaryItem[][][] term_definitions) {
        this.term_definitions = term_definitions;
    }

    public org.openehr.schemas.v1.StringDictionaryItem[][] getTerm_definitions(int i) {
        return this.term_definitions[i];
    }

    public void setTerm_definitions(int i, org.openehr.schemas.v1.StringDictionaryItem[][] _value) {
        this.term_definitions[i] = _value;
    }


    /**
     * Gets the constraint_definitions value for this ARCHETYPE_ONTOLOGY.
     * 
     * @return constraint_definitions
     */
    public org.openehr.schemas.v1.StringDictionaryItem[][][] getConstraint_definitions() {
        return constraint_definitions;
    }


    /**
     * Sets the constraint_definitions value for this ARCHETYPE_ONTOLOGY.
     * 
     * @param constraint_definitions
     */
    public void setConstraint_definitions(org.openehr.schemas.v1.StringDictionaryItem[][][] constraint_definitions) {
        this.constraint_definitions = constraint_definitions;
    }

    public org.openehr.schemas.v1.StringDictionaryItem[][] getConstraint_definitions(int i) {
        return this.constraint_definitions[i];
    }

    public void setConstraint_definitions(int i, org.openehr.schemas.v1.StringDictionaryItem[][] _value) {
        this.constraint_definitions[i] = _value;
    }


    /**
     * Gets the term_bindings value for this ARCHETYPE_ONTOLOGY.
     * 
     * @return term_bindings
     */
    public org.openehr.schemas.v1.TERM_BINDING_ITEM[][] getTerm_bindings() {
        return term_bindings;
    }


    /**
     * Sets the term_bindings value for this ARCHETYPE_ONTOLOGY.
     * 
     * @param term_bindings
     */
    public void setTerm_bindings(org.openehr.schemas.v1.TERM_BINDING_ITEM[][] term_bindings) {
        this.term_bindings = term_bindings;
    }

    public org.openehr.schemas.v1.TERM_BINDING_ITEM[] getTerm_bindings(int i) {
        return this.term_bindings[i];
    }

    public void setTerm_bindings(int i, org.openehr.schemas.v1.TERM_BINDING_ITEM[] _value) {
        this.term_bindings[i] = _value;
    }


    /**
     * Gets the constraint_bindings value for this ARCHETYPE_ONTOLOGY.
     * 
     * @return constraint_bindings
     */
    public org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[][] getConstraint_bindings() {
        return constraint_bindings;
    }


    /**
     * Sets the constraint_bindings value for this ARCHETYPE_ONTOLOGY.
     * 
     * @param constraint_bindings
     */
    public void setConstraint_bindings(org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[][] constraint_bindings) {
        this.constraint_bindings = constraint_bindings;
    }

    public org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[] getConstraint_bindings(int i) {
        return this.constraint_bindings[i];
    }

    public void setConstraint_bindings(int i, org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[] _value) {
        this.constraint_bindings[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ARCHETYPE_ONTOLOGY)) return false;
        ARCHETYPE_ONTOLOGY other = (ARCHETYPE_ONTOLOGY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.term_definitions==null && other.getTerm_definitions()==null) || 
             (this.term_definitions!=null &&
              java.util.Arrays.equals(this.term_definitions, other.getTerm_definitions()))) &&
            ((this.constraint_definitions==null && other.getConstraint_definitions()==null) || 
             (this.constraint_definitions!=null &&
              java.util.Arrays.equals(this.constraint_definitions, other.getConstraint_definitions()))) &&
            ((this.term_bindings==null && other.getTerm_bindings()==null) || 
             (this.term_bindings!=null &&
              java.util.Arrays.equals(this.term_bindings, other.getTerm_bindings()))) &&
            ((this.constraint_bindings==null && other.getConstraint_bindings()==null) || 
             (this.constraint_bindings!=null &&
              java.util.Arrays.equals(this.constraint_bindings, other.getConstraint_bindings())));
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
        if (getTerm_definitions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTerm_definitions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTerm_definitions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getConstraint_definitions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConstraint_definitions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConstraint_definitions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTerm_bindings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTerm_bindings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTerm_bindings(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getConstraint_bindings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConstraint_bindings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConstraint_bindings(), i);
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
        new org.apache.axis.description.TypeDesc(ARCHETYPE_ONTOLOGY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ONTOLOGY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("term_definitions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "term_definitions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CodeDefinitionSet"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("constraint_definitions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "constraint_definitions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CodeDefinitionSet"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("term_bindings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "term_bindings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TermBindingSet"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("constraint_bindings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "constraint_bindings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ConstraintBindingSet"));
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
