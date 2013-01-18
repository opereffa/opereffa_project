/**
 * OPERATOR_KIND.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class OPERATOR_KIND implements java.io.Serializable {
    private java.math.BigInteger _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected OPERATOR_KIND(java.math.BigInteger value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.math.BigInteger _value1 = new java.math.BigInteger("2001");
    public static final java.math.BigInteger _value2 = new java.math.BigInteger("2002");
    public static final java.math.BigInteger _value3 = new java.math.BigInteger("2003");
    public static final java.math.BigInteger _value4 = new java.math.BigInteger("2004");
    public static final java.math.BigInteger _value5 = new java.math.BigInteger("2005");
    public static final java.math.BigInteger _value6 = new java.math.BigInteger("2006");
    public static final java.math.BigInteger _value7 = new java.math.BigInteger("2007");
    public static final java.math.BigInteger _value8 = new java.math.BigInteger("2010");
    public static final java.math.BigInteger _value9 = new java.math.BigInteger("2011");
    public static final java.math.BigInteger _value10 = new java.math.BigInteger("2012");
    public static final java.math.BigInteger _value11 = new java.math.BigInteger("2013");
    public static final java.math.BigInteger _value12 = new java.math.BigInteger("2014");
    public static final java.math.BigInteger _value13 = new java.math.BigInteger("2015");
    public static final java.math.BigInteger _value14 = new java.math.BigInteger("2016");
    public static final java.math.BigInteger _value15 = new java.math.BigInteger("2020");
    public static final java.math.BigInteger _value16 = new java.math.BigInteger("2021");
    public static final java.math.BigInteger _value17 = new java.math.BigInteger("2022");
    public static final java.math.BigInteger _value18 = new java.math.BigInteger("2023");
    public static final java.math.BigInteger _value19 = new java.math.BigInteger("2024");
    public static final OPERATOR_KIND value1 = new OPERATOR_KIND(_value1);
    public static final OPERATOR_KIND value2 = new OPERATOR_KIND(_value2);
    public static final OPERATOR_KIND value3 = new OPERATOR_KIND(_value3);
    public static final OPERATOR_KIND value4 = new OPERATOR_KIND(_value4);
    public static final OPERATOR_KIND value5 = new OPERATOR_KIND(_value5);
    public static final OPERATOR_KIND value6 = new OPERATOR_KIND(_value6);
    public static final OPERATOR_KIND value7 = new OPERATOR_KIND(_value7);
    public static final OPERATOR_KIND value8 = new OPERATOR_KIND(_value8);
    public static final OPERATOR_KIND value9 = new OPERATOR_KIND(_value9);
    public static final OPERATOR_KIND value10 = new OPERATOR_KIND(_value10);
    public static final OPERATOR_KIND value11 = new OPERATOR_KIND(_value11);
    public static final OPERATOR_KIND value12 = new OPERATOR_KIND(_value12);
    public static final OPERATOR_KIND value13 = new OPERATOR_KIND(_value13);
    public static final OPERATOR_KIND value14 = new OPERATOR_KIND(_value14);
    public static final OPERATOR_KIND value15 = new OPERATOR_KIND(_value15);
    public static final OPERATOR_KIND value16 = new OPERATOR_KIND(_value16);
    public static final OPERATOR_KIND value17 = new OPERATOR_KIND(_value17);
    public static final OPERATOR_KIND value18 = new OPERATOR_KIND(_value18);
    public static final OPERATOR_KIND value19 = new OPERATOR_KIND(_value19);
    public java.math.BigInteger getValue() { return _value_;}
    public static OPERATOR_KIND fromValue(java.math.BigInteger value)
          throws java.lang.IllegalArgumentException {
        OPERATOR_KIND enumeration = (OPERATOR_KIND)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static OPERATOR_KIND fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(new java.math.BigInteger(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_.toString();}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OPERATOR_KIND.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OPERATOR_KIND"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
