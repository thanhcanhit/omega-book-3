package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import entity.Product;

public interface StatisticProduct_BUS extends Remote{

    public double getTotalProduct(String productID, String date) throws IOException;
    public int getQuantitySale(String productID,String date) throws IOException;
    public ArrayList<Product> getTop10Product(String date) throws IOException;
    public ArrayList<Product> getTopProductInDay(String date) throws IOException;
    public Product getProduct(String ID) throws IOException;
}
