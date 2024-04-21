package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import entity.Brand;
import entity.Product;

public interface ProductManagement_BUS extends Remote{
	public ArrayList<Product> getDataOfPage(int page) throws IOException;

    public Product getProduct(String ID)throws IOException;

    public int getLastPage()throws IOException ;

    public ArrayList<Product> searchById(String searchQuery)throws IOException ;
    public boolean updateProduct(String id, Product product)throws IOException;
    public boolean createProduct(Product productWithoutId)throws IOException;

    public ArrayList<Product> filter(String name, Boolean isEmpty, int type, int detailType)throws IOException ;

    public ArrayList<Brand> getAllBrand()throws IOException;

    public ArrayList<Product> filter(int type, int detailType) throws IOException;

    public ArrayList<Product> getAll()throws IOException ;
}
