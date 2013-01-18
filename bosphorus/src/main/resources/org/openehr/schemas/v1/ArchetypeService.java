/**
 * ArchetypeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public interface ArchetypeService extends javax.xml.rpc.Service {
    public java.lang.String getBPAddress();

    public org.openehr.schemas.v1.BP getBP() throws javax.xml.rpc.ServiceException;

    public org.openehr.schemas.v1.BP getBP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
