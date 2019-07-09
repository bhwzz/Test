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

public class HelloClient {
   public static void main(String [] args) {
       try {
           String endpoint =
                    "http://localhost:8082/SimpleService/services/HelloWorld";
           Service  service = new Service();
           Call     call    = (Call) service.createCall();
           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
           call.setOperationName(new QName("http://soapinterop.org/", "sayHello") );
           String ret = (String) call.invoke( new Object[] {} );
           System.out.println(ret);
       } catch (Exception e) {
           System.err.println(e.toString());
       }
   }
}
