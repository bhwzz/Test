// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMICallback/Solution/TimeServer.java#2 $
import java.rmi.*;

// Create the interface TimeServer.
// It contains one method registerTimeMonitor.
// This method accepts a parameter of type TimeMonitor
// and returns void.

public interface TimeServer extends java.rmi.Remote {
    public void registerTimeMonitor( TimeMonitor tm ) throws RemoteException;
}
