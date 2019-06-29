package soap;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author ShangZhenhong
 * @version 1.0
 */
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;

public class EchoClient {
    public static void main(String[] args) {
        try {
            String endpoint =
                    "http://localhost:8080/SimpleService/services/EchoServer";

            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(new QName("http://soapinterop.org/",
                                            "echoString"));
            String ret = (String) call.invoke(new Object[] {"Hello!"});

            System.out.println("Sent 'Hello!', got '" + ret + "'");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
