package soap.client;

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
import soap.generated.*;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class HelloUsingStub {
    public static void main(String[] args) throws ServiceException,
            RemoteException {
        HelloWorldService service=new HelloWorldServiceLocator();
        HelloWorld hello=service.getHelloWorld();
        System.out.println( hello.sayHello());
    }
}
