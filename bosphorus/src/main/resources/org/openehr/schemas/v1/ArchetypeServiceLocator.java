/**
 * ArchetypeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ArchetypeServiceLocator extends org.apache.axis.client.Service implements org.openehr.schemas.v1.ArchetypeService {

    public ArchetypeServiceLocator() {
    }


    public ArchetypeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ArchetypeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BP
    private java.lang.String BP_address = "http://localhost:8080/holidayService/";

    public java.lang.String getBPAddress() {
        return BP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BPWSDDServiceName = "BP";

    public java.lang.String getBPWSDDServiceName() {
        return BPWSDDServiceName;
    }

    public void setBPWSDDServiceName(java.lang.String name) {
        BPWSDDServiceName = name;
    }

    public org.openehr.schemas.v1.BP getBP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBP(endpoint);
    }

    public org.openehr.schemas.v1.BP getBP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.openehr.schemas.v1.GetpbbindingStub _stub = new org.openehr.schemas.v1.GetpbbindingStub(portAddress, this);
            _stub.setPortName(getBPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBPEndpointAddress(java.lang.String address) {
        BP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.openehr.schemas.v1.BP.class.isAssignableFrom(serviceEndpointInterface)) {
                org.openehr.schemas.v1.GetpbbindingStub _stub = new org.openehr.schemas.v1.GetpbbindingStub(new java.net.URL(BP_address), this);
                _stub.setPortName(getBPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BP".equals(inputPortName)) {
            return getBP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ArchetypeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "BP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BP".equals(portName)) {
            setBPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
