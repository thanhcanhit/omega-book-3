package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Bill;
import entity.Customer;
import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;

public interface Sales_BUS extends Remote{
	public Product getProduct(String id) throws RemoteException;

    public Customer getCustomerByPhone(String phone) throws RemoteException;

    public Bill createNewOrder() throws Exception ;

    public boolean saveToDatabase(Bill order) throws RemoteException;
    public boolean updateInDatabase(Bill order) throws RemoteException;

    public boolean decreaseProductInventory(Product product, int quantity) throws RemoteException;

    public boolean increaseProductInventory(Product product, int quantity) throws RemoteException;

    public ArrayList<ProductPromotionDetail> getListProductPromotionAvailable(String productID)throws RemoteException;
    public ArrayList<Bill> getSavedOrders() throws RemoteException;
    public Bill getOrder(String id) throws RemoteException;

    public boolean deleteOrder(String id) throws RemoteException;
    public ArrayList<ProductPromotionDetail> getPromotionOfProductAvailable(String productID) throws RemoteException;

    public double getBestProductPromotionDiscountAmountAvailable(String productID) throws RemoteException;

    public ArrayList<Promotion> getPromotionOfOrderAvailable(int customerRank)throws RemoteException;

    public Promotion getPromotion(String promotionID)throws RemoteException;
    
    public int getSavedOrderQuantity() throws RemoteException;
}
