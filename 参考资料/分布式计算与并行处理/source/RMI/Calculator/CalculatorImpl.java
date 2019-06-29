/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Shang Zhenhong
 * @version 1.0
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends
    UnicastRemoteObject implements Calculator {

    // Implementations must have an
    //explicit constructor
    // in order to declare the
    //RemoteException exception
    public CalculatorImpl() throws RemoteException {
        super();
    }

    public long add(long a, long b) throws RemoteException {
        return a + b;
    }

    public long sub(long a, long b) throws RemoteException {
        return a - b;
    }

    public long mul(long a, long b) throws RemoteException {
        return a * b;
    }

    public long div(long a, long b) throws RemoteException {
        return a / b;
    }
}
