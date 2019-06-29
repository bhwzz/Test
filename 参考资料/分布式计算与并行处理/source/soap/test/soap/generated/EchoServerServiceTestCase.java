/**
 * EchoServerServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package soap.generated;

public class EchoServerServiceTestCase extends junit.framework.TestCase {
    public EchoServerServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testEchoServerWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new soap.generated.EchoServerServiceLocator().getEchoServerAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new soap.generated.EchoServerServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1EchoServerEchoString() throws Exception {
        soap.generated.EchoServerSoapBindingStub binding;
        try {
            binding = (soap.generated.EchoServerSoapBindingStub)
                          new soap.generated.EchoServerServiceLocator().getEchoServer();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.echoString(new java.lang.String());
        // TBD - validate results
    }

}
