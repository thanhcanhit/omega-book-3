package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.*;

import entity.Supplier;

public interface SupplierManagement_BUS extends Remote{

    public List<Supplier> getAll() throws IOException;
    public boolean update(String id, Supplier supplier) throws IOException;
    public boolean create(String Name, String address) throws Exception ;
    public List<Supplier> search(String id) throws IOException;
}
