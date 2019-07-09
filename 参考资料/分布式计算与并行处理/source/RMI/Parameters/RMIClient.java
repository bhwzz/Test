// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMIParameters/Solution/RMIClient.java#2 $
import java.net.*;
import java.io.*;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

/**
 * RMIClient02 utilizes the remote "Hello" object.
 *
 * RMIClient02 relies on RMIServer to do command line processing.
 */

public class RMIClient {
    private static final int PORT = 10002;
    private static final String HOST_NAME = "localhost";

    public RMIClient() {
        try {
            Hello h = (Hello) Naming.lookup("rmi://" + HOST_NAME + ":" + PORT +
                                            "/HelloService");
            System.out.println("HelloService lookup successful");
            String helloString = h.sayHello();
            System.out.println("The server says: " + helloString);
            MessageObject mo;
            for (int i = 0; i < 10; i++) {
                mo = h.getMessageObject();
                System.out.println("MessageObject: Class Number is #" +
                                   mo.getNumberFromClass() +
                                   " Object Number is #" +
                                   mo.getNumberFromObject());
            }
        } catch (java.rmi.UnknownHostException uhe) {
            System.out.println("The host computer name you have specified, " +
                               HOST_NAME +
                               " does not match your real computer name.");
        } catch (RemoteException re) {
            System.out.println(
                "A Remote Exception was thrown when requesting the HelloService");
            System.out.println("" + re);
        } catch (MalformedURLException mURLe) {
            System.out.println(
                "There is a problem with the rmi: URL you are using");
            System.out.println("" + mURLe);
        } catch (NotBoundException nbe) {
            System.out.println("" + nbe);
        }

    }

    public static void main(String[] args) {
       RMIClient rmi = new RMIClient();
    } // main

} // class RMIClient
