package bus;

import java.util.ArrayList;

import entity.Product;

public interface StatisticProduct_BUS {

    public double getTotalProduct(String productID, String date);
    public int getQuantitySale(String productID,String date);
    public ArrayList<Product> getTop10Product(String date);
    public ArrayList<Product> getTopProductInDay(String date);
    public Product getProduct(String ID);
}
