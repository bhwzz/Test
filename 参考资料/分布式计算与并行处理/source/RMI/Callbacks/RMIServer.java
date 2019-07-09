// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMICallback/Solution/RMIServer.java#2 $
import java.net.*;
import java.io.*;
import java.util.Date;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

/**
 * RMIServer acts as a server for the remote "TimeServer" service.
 *
 * RMIServer starts execution at the standard entry point "public static void main";
 * It creates an instance of itself and continues processing in the constructor.
 *
 */

public class RMIServer implements TimeServer {
    private static final int PORT = 1099;
    private static final String HOST_NAME = "localhost";

    public static void main( String[] args ) {
        try {
            RMIServer rmi = new RMIServer();
            LocateRegistry.createRegistry( PORT );
            System.out.println( "Registry created" );
            UnicastRemoteObject.exportObject( ( ( TimeServer ) rmi ) );
            Naming.rebind( "//" + HOST_NAME + ":" + PORT + "/" + "TimeServer",
                           rmi );
            System.out.println( "Bindings Finished" );
            System.out.println( "Waiting for Client requests" );
        }
        catch ( java.rmi.UnknownHostException uhe ) {
            System.out.println( "The host computer name you have specified, " +
                                HOST_NAME +
                                " does not match your real computer name." );

        }
        catch ( RemoteException re ) {
            System.out.println( "Error starting service" );
            System.out.println( "" + re );
        }
        catch ( MalformedURLException mURLe ) {
            System.out.println( "Internal error" + mURLe );
        }

    } // main

    public void registerTimeMonitor( TimeMonitor tm ) {
        System.out.println( "Client requesting a connection" );
        TimeTicker tt;
        tt = new TimeTicker( tm );
        tt.start();
        System.out.println( "Timer Started" );
    }

} // class RMIServer

class TimeTicker extends Thread {

    private TimeMonitor tm;
    private boolean run = true;
    TimeTicker( TimeMonitor tm ) {
        this.tm = tm;
    }

    public void run() {
        while ( run ) {
            try {
                sleep( 2000 );
                tm.tellMeTheTime( new Date() );
            }
            catch ( Exception e ) {
                run = false;
            }
        }
    }
}
