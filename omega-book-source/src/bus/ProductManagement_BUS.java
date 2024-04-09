package bus;

import java.util.ArrayList;
import entity.Brand;
import entity.Product;

public interface ProductManagement_BUS {
	public ArrayList<Product> getDataOfPage(int page) ;

    public Product getProduct(String ID);

    public int getLastPage() ;

    public ArrayList<Product> searchById(String searchQuery) ;
    public boolean updateProduct(String id, Product product);
    public boolean createProduct(Product productWithoutId);

    public ArrayList<Product> filter(String name, Boolean isEmpty, int type, int detailType) ;

    public ArrayList<Brand> getAllBrand();

    public ArrayList<Product> filter(int type, int detailType) ;

    public ArrayList<Product> getAll() ;
}
