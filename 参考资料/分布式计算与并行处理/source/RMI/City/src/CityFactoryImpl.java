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

public class CityFactoryImpl extends UnicastRemoteObject implements CityFactory {
    public CityFactoryImpl() throws RemoteException {
    }
    public City getCity(String name) throws RemoteException{
        City city=new CityImpl(name);
        return city;
    }
}
