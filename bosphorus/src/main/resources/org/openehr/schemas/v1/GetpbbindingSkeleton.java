/**
 * GetpbbindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class GetpbbindingSkeleton implements org.openehr.schemas.v1.BP, org.apache.axis.wsdl.Skeleton {
    private org.openehr.schemas.v1.BP impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetype"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE"), org.openehr.schemas.v1.ARCHETYPE.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBP", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBP"));
        _oper.setSoapAction("http://mycompany.com/getarchetype");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBP") == null) {
            _myOperations.put("getBP", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBP")).add(_oper);
    }

    public GetpbbindingSkeleton() {
        this.impl = new org.openehr.schemas.v1.GetpbbindingImpl();
    }

    public GetpbbindingSkeleton(org.openehr.schemas.v1.BP impl) {
        this.impl = impl;
    }
    public void getBP(org.openehr.schemas.v1.ARCHETYPE ARCHETYPE) throws java.rmi.RemoteException
    {
        impl.getBP(ARCHETYPE);
    }

}
