package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Product;

public interface StatisticProduct_BUS extends Remote{

    public double getTotalProduct(String productID, String date) throws RemoteException;
    public int getQuantitySale(String productID,String date) throws RemoteException;
    public ArrayList<Product> getTop10Product(String date) throws RemoteException;
    public ArrayList<Product> getTopProductInDay(String date) throws RemoteException;
    public Product getProduct(String ID) throws RemoteException;
}
