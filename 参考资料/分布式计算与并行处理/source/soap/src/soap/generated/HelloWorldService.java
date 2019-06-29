/**
 * HelloWorldService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package soap.generated;

public interface HelloWorldService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWorldAddress();

    public soap.generated.HelloWorld getHelloWorld() throws javax.xml.rpc.ServiceException;

    public soap.generated.HelloWorld getHelloWorld(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
