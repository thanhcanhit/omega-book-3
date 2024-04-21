package bus;

<<<<<<< HEAD
import java.io.IOException;
import java.rmi.Remote;
=======
import java.rmi.Remote;
import java.rmi.RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
import java.util.*;

import entity.Supplier;

public interface SupplierManagement_BUS extends Remote{

<<<<<<< HEAD
    public List<Supplier> getAll() throws IOException;
    public boolean update(String id, Supplier supplier) throws IOException;
    public boolean create(String Name, String address) throws Exception ;
    public List<Supplier> search(String id) throws IOException;
=======
    public List<Supplier> getAll() throws RemoteException;
    public boolean update(String id, Supplier supplier) throws RemoteException;
    public boolean create(String Name, String address) throws RemoteException, Exception ;
    public List<Supplier> search(String id) throws RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
}
