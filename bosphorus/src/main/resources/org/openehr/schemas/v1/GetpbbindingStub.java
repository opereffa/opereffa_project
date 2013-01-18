/**
 * GetpbbindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class GetpbbindingStub extends org.apache.axis.client.Stub implements org.openehr.schemas.v1.BP {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetype"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE"), org.openehr.schemas.v1.ARCHETYPE.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

    }

    public GetpbbindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public GetpbbindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public GetpbbindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ACCESS_GROUP_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ACCESS_GROUP_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_CONSTRAINT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE_CONSTRAINT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_INTERNAL_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE_INTERNAL_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ONTOLOGY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE_ONTOLOGY.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_SLOT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ARCHETYPE_SLOT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_TERM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.StringDictionaryItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetypeNodeId");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ASSERTION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ASSERTION_VARIABLE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ASSERTION_VARIABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "atCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ATTESTATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.ATTESTATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUDIT_DETAILS");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.AUDIT_DETAILS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUTHORED_RESOURCE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.AUTHORED_RESOURCE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_ATTRIBUTE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_ATTRIBUTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_BOOLEAN");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_BOOLEAN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_CODE_PHRASE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_CODE_PHRASE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_COMPLEX_OBJECT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_COMPLEX_OBJECT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DATE_TIME");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DATE_TIME.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DEFINED_OBJECT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DEFINED_OBJECT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DOMAIN_TYPE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DOMAIN_TYPE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DURATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DURATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DV_ORDINAL");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DV_ORDINAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DV_QUANTITY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DV_QUANTITY.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_DV_STATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_DV_STATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_INTEGER");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_INTEGER.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_MULTIPLE_ATTRIBUTE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_MULTIPLE_ATTRIBUTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_OBJECT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_OBJECT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_PRIMITIVE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_PRIMITIVE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_PRIMITIVE_OBJECT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_PRIMITIVE_OBJECT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_QUANTITY_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_QUANTITY_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_REAL");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_REAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_SINGLE_ATTRIBUTE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_SINGLE_ATTRIBUTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_STRING");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_STRING.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "C_TIME");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.C_TIME.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CARDINALITY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.CARDINALITY.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.CODE_PHRASE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CodeDefinitionSet");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.StringDictionaryItem[][].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_TERM");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CONSTRAINT_BINDING_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CONSTRAINT_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.CONSTRAINT_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ConstraintBindingSet");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.CONSTRAINT_BINDING_ITEM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CONSTRAINT_BINDING_ITEM");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DATA_VALUE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DATA_VALUE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DateConstraintPattern");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DateTimeConstraintPattern");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DurationConstraintPattern");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_AMOUNT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_AMOUNT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_BOOLEAN");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_BOOLEAN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_CODED_TEXT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_COUNT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_COUNT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_DATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_DATE_TIME.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DURATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_DURATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_EHR_URI");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_EHR_URI.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ENCAPSULATED");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_ENCAPSULATED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_GENERAL_TIME_SPECIFICATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_GENERAL_TIME_SPECIFICATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_IDENTIFIER");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_IDENTIFIER.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_INTERVAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_MULTIMEDIA");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_MULTIMEDIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDERED");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_ORDERED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDINAL");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_ORDINAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PARAGRAPH");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_PARAGRAPH.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PARSABLE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_PARSABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PERIODIC_TIME_SPECIFICATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_PERIODIC_TIME_SPECIFICATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PROPORTION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_PROPORTION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_QUANTIFIED");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_QUANTIFIED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_QUANTITY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_QUANTITY.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_STATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_STATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEMPORAL");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_TEMPORAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_TEXT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TIME");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_TIME.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TIME_SPECIFICATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_TIME_SPECIFICATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_URI");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.DV_URI.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_BINARY_OPERATOR");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.EXPR_BINARY_OPERATOR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.EXPR_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_LEAF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.EXPR_LEAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_OPERATOR");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.EXPR_OPERATOR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXPR_UNARY_OPERATOR");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.EXPR_UNARY_OPERATOR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.FEEDER_AUDIT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT_DETAILS");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "GENERIC_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.GENERIC_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.HIER_OBJECT_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Interval");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.Interval.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfDate");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfDateTime");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfDateTime.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfDuration");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfDuration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfInteger");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfInteger.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfReal");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfReal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IntervalOfTime");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.IntervalOfTime.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Iso8601Date");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Iso8601DateTime");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Iso8601Duration");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "Iso8601Time");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "LOCATABLE_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.LOCATABLE_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "matchString");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "NON_TERMINAL_STATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.NON_TERMINAL_STATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.OBJECT_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.OBJECT_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_VERSION_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.OBJECT_VERSION_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OPERATOR_KIND");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.OPERATOR_KIND.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTICIPATION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTICIPATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTY_IDENTIFIED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTY_PROXY.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_REF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTY_REF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_RELATED");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTY_RELATED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_SELF");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PARTY_SELF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PROPORTION_KIND");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.PROPORTION_KIND.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REFERENCE_RANGE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.REFERENCE_RANGE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.RESOURCE_DESCRIPTION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "RESOURCE_DESCRIPTION_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.RESOURCE_DESCRIPTION_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.REVISION_HISTORY_ITEM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY_ITEM");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.REVISION_HISTORY_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "STATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.STATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "STATE_MACHINE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.STATE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "STATE");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "states");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "StringDictionaryItem");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.StringDictionaryItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TEMPLATE_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TEMPLATE_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_BINDING_ITEM");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TERM_BINDING_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_MAPPING");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TERM_MAPPING.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TermBindingSet");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TERM_BINDING_ITEM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_BINDING_ITEM");
            qName2 = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERMINAL_STATE");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TERMINAL_STATE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERMINOLOGY_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TERMINOLOGY_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TimeConstraintPattern");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSITION");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TRANSITION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TRANSLATION_DETAILS");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.TRANSLATION_DETAILS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "UID_BASED_ID");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.UID_BASED_ID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "VALIDITY_KIND");
            cachedSerQNames.add(qName);
            cls = org.openehr.schemas.v1.VALIDITY_KIND.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void getBP(org.openehr.schemas.v1.ARCHETYPE ARCHETYPE) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://mycompany.com/getarchetype");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBP"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {ARCHETYPE});

    }

}
