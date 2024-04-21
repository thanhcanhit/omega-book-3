package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Brand;
import entity.Product;

public interface ProductManagement_BUS extends Remote{
	public ArrayList<Product> getDataOfPage(int page) throws RemoteException;

    public Product getProduct(String ID)throws RemoteException;

    public int getLastPage()throws RemoteException ;

    public ArrayList<Product> searchById(String searchQuery)throws RemoteException ;
    public boolean updateProduct(String id, Product product)throws RemoteException;
    public boolean createProduct(Product productWithoutId)throws RemoteException;

    public ArrayList<Product> filter(String name, Boolean isEmpty, int type, int detailType)throws RemoteException ;

    public ArrayList<Brand> getAllBrand()throws RemoteException;

    public ArrayList<Product> filter(int type, int detailType) throws RemoteException;

    public ArrayList<Product> getAll()throws RemoteException ;
}
