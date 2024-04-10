package bus;

import java.util.ArrayList;

import entity.Supplier;

public interface SupplierManagement_BUS {

    public ArrayList<Supplier> getAll();
    public boolean update(String id, Supplier supplier);
    public boolean create(String Name, String address) throws Exception ;
    public ArrayList<Supplier> search(String id);
}
