import java.rmi.*;
import java.net.*;
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
public class CityClient {
    private static final String HOST="localhost";
    private static final String SERVICE="cityFactory";
    public static void main(String[] args) {
        try {
            CityFactory cityFactory = (CityFactory) Naming.lookup("rmi://" +
                    HOST + "/" + SERVICE);
            City beijing=cityFactory.getCity("Beijing");
            City kunming=cityFactory.getCity("Kunming");
            int popBJ=beijing.getPopulation();
            int TmpBJ=beijing.getTemperature();
            int popKM=kunming.getPopulation();
            int TmpKM=kunming.getTemperature();
            System.out.println("Population of Beijing: "+popBJ);
            System.out.println("Temperature of Beijing: "+TmpBJ);
            System.out.println("Population of Kunming: "+popKM);
            System.out.println("Temperature of Kunming: "+TmpKM);
        } catch (RemoteException ex) {
        } catch (MalformedURLException ex) {
        } catch (NotBoundException ex) {
        }
    }
}
