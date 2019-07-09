/**
 * MyServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package soap.generated;

public class MyServiceServiceLocator extends org.apache.axis.client.Service implements soap.generated.MyServiceService {

    public MyServiceServiceLocator() {
    }


    public MyServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MyService
    private java.lang.String MyService_address = "http://localhost:8080/SimpleService/services/MyService";

    public java.lang.String getMyServiceAddress() {
        return MyService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MyServiceWSDDServiceName = "MyService";

    public java.lang.String getMyServiceWSDDServiceName() {
        return MyServiceWSDDServiceName;
    }

    public void setMyServiceWSDDServiceName(java.lang.String name) {
        MyServiceWSDDServiceName = name;
    }

    public soap.generated.MyService getMyService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MyService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMyService(endpoint);
    }

    public soap.generated.MyService getMyService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            soap.generated.MyServiceSoapBindingStub _stub = new soap.generated.MyServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMyServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMyServiceEndpointAddress(java.lang.String address) {
        MyService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (soap.generated.MyService.class.isAssignableFrom(serviceEndpointInterface)) {
                soap.generated.MyServiceSoapBindingStub _stub = new soap.generated.MyServiceSoapBindingStub(new java.net.URL(MyService_address), this);
                _stub.setPortName(getMyServiceWSDDServiceName());
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
        if ("MyService".equals(inputPortName)) {
            return getMyService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap", "MyServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap", "MyService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

if ("MyService".equals(portName)) {
            setMyServiceEndpointAddress(address);
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
