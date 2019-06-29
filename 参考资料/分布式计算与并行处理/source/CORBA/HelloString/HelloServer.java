import HelloApp.*;
//import org.omg.CosNaming.*;
//import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.io.*;

import java.util.Properties;

class HelloImpl extends HelloPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    // implement sayHello() method
    public String sayHello() {
        return "\nHello world !!\n";
    }

    // implement shutdown() method
    public void shutdown() {
        orb.shutdown(false);
    }
}


public class HelloServer {
    private static final String FILE_NAME = "object.txt";

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references(
                    "RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            HelloImpl helloImpl = new HelloImpl();
            helloImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            Hello href = HelloHelper.narrow(ref);

            // write string object reference to file
            String ior = orb.object_to_string(href);
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(ior);
            writer.close();

            System.out.println("HelloServer ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("HelloServer Exiting ...");
    }
}
