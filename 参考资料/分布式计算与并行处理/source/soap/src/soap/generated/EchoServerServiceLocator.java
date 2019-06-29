/**
 * EchoServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package soap.generated;

public class EchoServerServiceLocator extends org.apache.axis.client.Service implements soap.generated.EchoServerService {

    public EchoServerServiceLocator() {
    }


    public EchoServerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EchoServerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EchoServer
    private java.lang.String EchoServer_address = "http://localhost:8080/SimpleService/services/EchoServer";

    public java.lang.String getEchoServerAddress() {
        return EchoServer_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EchoServerWSDDServiceName = "EchoServer";

    public java.lang.String getEchoServerWSDDServiceName() {
        return EchoServerWSDDServiceName;
    }

    public void setEchoServerWSDDServiceName(java.lang.String name) {
        EchoServerWSDDServiceName = name;
    }

    public soap.generated.EchoServer getEchoServer() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EchoServer_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEchoServer(endpoint);
    }

    public soap.generated.EchoServer getEchoServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            soap.generated.EchoServerSoapBindingStub _stub = new soap.generated.EchoServerSoapBindingStub(portAddress, this);
            _stub.setPortName(getEchoServerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEchoServerEndpointAddress(java.lang.String address) {
        EchoServer_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (soap.generated.EchoServer.class.isAssignableFrom(serviceEndpointInterface)) {
                soap.generated.EchoServerSoapBindingStub _stub = new soap.generated.EchoServerSoapBindingStub(new java.net.URL(EchoServer_address), this);
                _stub.setPortName(getEchoServerWSDDServiceName());
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
        if ("EchoServer".equals(inputPortName)) {
            return getEchoServer();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap", "EchoServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap", "EchoServer"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

if ("EchoServer".equals(portName)) {
            setEchoServerEndpointAddress(address);
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
