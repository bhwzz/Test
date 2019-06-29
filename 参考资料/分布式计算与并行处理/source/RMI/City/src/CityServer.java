import java.rmi.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.net.MalformedURLException;

public class CityServer {
    private static final String HOST="localhost";
    private static final String SERVICE="cityFactory";
    public static void main(String[] args) {
        try {
            CityFactory cityFactory = new CityFactoryImpl();
            Naming.rebind("rmi://"+HOST+"/"+SERVICE,cityFactory);
            System.out.println("City server is ready!");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }
}
