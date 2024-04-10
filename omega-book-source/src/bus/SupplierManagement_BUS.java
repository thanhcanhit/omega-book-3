package bus;

import java.util.*;

import entity.Supplier;

public interface SupplierManagement_BUS {

    public List<Supplier> getAll();
    public boolean update(String id, Supplier supplier);
    public boolean create(String Name, String address) throws Exception ;
    public List<Supplier> search(String id);
}
