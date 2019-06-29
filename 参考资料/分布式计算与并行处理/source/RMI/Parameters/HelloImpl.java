// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMIParameters/Solution/HelloImpl.java#2 $
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    public HelloImpl() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    public MessageObject getMessageObject() throws RemoteException {
        return new MessageObject();
    }
}
