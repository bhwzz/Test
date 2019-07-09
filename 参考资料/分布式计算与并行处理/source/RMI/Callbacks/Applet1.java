// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMICallback/Solution/Applet1.java#2 $
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Date;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;

public class Applet1 extends Applet implements TimeMonitor {
    // Change this to your computer's name
    private final static String HOST_NAME = "localhost";

    public void init() {
        super.init();
        uiInit();
        TimeServer ts = null;
        try {
            System.out.println( "Exporting the Applet" );
            UnicastRemoteObject.exportObject( this );
            URL base = getDocumentBase();
            String hostName = base.getHost();
            if ( 0 == hostName.length() ) {
                hostName = HOST_NAME;
            }
            String serverName = "rmi://" + hostName + ":" +
                getParameter( "registryPort" ) + "/TimeServer";
            System.out.println( "Looking up TimeService at: " + serverName );
            try {
                ts = ( TimeServer ) Naming.lookup( serverName );
            }
            catch ( Exception e ) {
                System.out.println( "" + e );
            }
            ts.registerTimeMonitor( this );
            System.out.println( "We have been registered!" );
        }
        catch ( RemoteException re ) {
            System.out.println( "" + re );
        }
    }

    public void tellMeTheTime( Date d ) {
        textArea1.append( d.toString() + "\n" );
    }

    public void uiInit() {
        setLayout( null );
        resize( 456, 266 );
        textArea1 = new java.awt.TextArea();
        textArea1.setBounds( 36, 24, 252, 170 );
        add( textArea1 );

        button1 = new java.awt.Button( "Clear" );
        button1.setBounds( 324, 36, 72, 24 );
        add( button1 );

        button1.addActionListener(
            new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                textArea1.setText( "" );
            }
        }
        );

    }

    java.awt.TextArea textArea1;
    java.awt.Button button1;
}
