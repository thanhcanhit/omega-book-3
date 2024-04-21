package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

import entity.Supplier;

public interface SupplierManagement_BUS extends Remote{

    public List<Supplier> getAll() throws RemoteException;
    public boolean update(String id, Supplier supplier) throws RemoteException;
    public boolean create(String Name, String address) throws RemoteException, Exception ;
    public List<Supplier> search(String id) throws RemoteException;
}
