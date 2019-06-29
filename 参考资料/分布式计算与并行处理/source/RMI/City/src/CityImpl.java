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
import java.rmi.server.*;
import java.rmi.RemoteException;

public class CityImpl extends UnicastRemoteObject implements City{
    private String cityName;
    public CityImpl() throws RemoteException {
    }
    public CityImpl(String name) throws RemoteException{
        this();
        this.cityName=name;
    }

    public int getPopulation() throws RemoteException {
        if(cityName.equalsIgnoreCase("Kunming")){
            return 3000000;
        }else if (cityName.equalsIgnoreCase("Beijing")){
            return 10000000;
        }else{
            return 0;
        }
    }

    public int getTemperature() throws RemoteException {
        if(cityName.equalsIgnoreCase("Kunming")){
            return 28;
        }else if (cityName.equalsIgnoreCase("Beijing")){
            return 38;
        }else{
            return 0;
        }

    }
}
