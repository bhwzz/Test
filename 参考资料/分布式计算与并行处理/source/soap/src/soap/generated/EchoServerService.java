/**
 * EchoServerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package soap.generated;

public interface EchoServerService extends javax.xml.rpc.Service {
    public java.lang.String getEchoServerAddress();

    public soap.generated.EchoServer getEchoServer() throws javax.xml.rpc.ServiceException;

    public soap.generated.EchoServer getEchoServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
