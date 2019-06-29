// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMIParameters/Solution/RMIServer.java#2 $
import java.net.*;
import java.io.*;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
 * RMIServer acts as a server for the remote "HelloService" service.
 *
 * RMIServer starts execution at the standard entry point "public static void main";
 * It creates an instance of itself and continues processing in the constructor.
 *
 */

public class RMIServer {
    private static final int PORT = 10002;
    private static final String HOST_NAME = "localhost";

    public RMIServer() throws RemoteException, MalformedURLException,
        NotBoundException {
        LocateRegistry.createRegistry(PORT);
        System.out.println("Registry created on host computer " + HOST_NAME +
                           " on port " + Integer.toString(PORT));

        Hello h = new HelloImpl();
        System.out.println("Remote HelloService implementation object created");

        String urlString = "//" + HOST_NAME + ":" + PORT + "/" + "HelloService";
        Naming.rebind(urlString, h);
        System.out.println("Bindings Finished, waiting for client requests.");
    }

    public static void main(String[] args) {
        // We need to set the security manager to the RMISecurityManager
        System.setSecurityManager(new RMISecurityManager());

        try {
            RMIServer rmi = new RMIServer();
        } catch (java.rmi.UnknownHostException uhe) {
            System.out.println("The host computer name you have specified, " +
                               HOST_NAME +
                               " does not match your real computer name.");

        } catch (RemoteException re) {
            System.out.println("Error starting service");
            System.out.println("" + re);
        } catch (MalformedURLException mURLe) {
            System.out.println("Internal error" + mURLe);
        } catch (NotBoundException nbe) {
            System.out.println("Not Bound");
            System.out.println("" + nbe);
        }
    } // main
} // class RMIServer
