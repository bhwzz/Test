import HelloApp.*;
//import org.omg.CosNaming.*;
//import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

public class HelloClient {
    public static final String FILE_NAME = "object.txt";
    static Hello helloImpl;

    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            FileInputStream finput=new FileInputStream(FILE_NAME);
            FileReader freader=new FileReader(FILE_NAME);
            BufferedReader reader=new BufferedReader(freader);
            //java.io.DataInputStream  di=new java.io.DataInputStream(finput);
            String ior=reader.readLine();
            reader.close();
            helloImpl = HelloHelper.narrow(orb.string_to_object(ior));

            System.out.println("Obtained a handle on server object: " +
                               helloImpl);
            System.out.println(helloImpl.sayHello());
            helloImpl.shutdown();

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

}
