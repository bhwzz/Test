import org.omg.CORBA.ORB;
import HelloApp.HelloPOA;

public class PersistentHelloServant extends HelloPOA {
    private ORB orb;

    public PersistentHelloServant( ORB orb ) {
        this.orb = orb;
    }

    /**
     *  sayHello() method implementation returns a simple message.
     */
    public String sayHello( ) {
        return "Hello From Persistent Server...";
    }

    /**
     *  shutdown() method shuts down the Persistent Server.
     *  See NOTE below.
     */
    public void shutdown( ) {
        orb.shutdown( false );
    }
}
