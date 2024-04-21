package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Bill;
import entity.Customer;
import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;

public interface Sales_BUS extends Remote{
	public Product getProduct(String id) throws IOException;

    public Customer getCustomerByPhone(String phone) throws IOException;

    public Bill createNewOrder() throws RemoteException,Exception ;

    public boolean saveToDatabase(Bill order) throws IOException;
    public boolean updateInDatabase(Bill order) throws IOException;

    public boolean decreaseProductInventory(Product product, int quantity) throws IOException;

    public boolean increaseProductInventory(Product product, int quantity) throws IOException;

    public ArrayList<ProductPromotionDetail> getListProductPromotionAvailable(String productID)throws IOException;
    public ArrayList<Bill> getSavedOrders() throws IOException;
    public Bill getOrder(String id) throws IOException;

    public boolean deleteOrder(String id) throws IOException;
    public ArrayList<ProductPromotionDetail> getPromotionOfProductAvailable(String productID) throws IOException;

    public double getBestProductPromotionDiscountAmountAvailable(String productID) throws IOException;

    public ArrayList<Promotion> getPromotionOfOrderAvailable(int customerRank)throws IOException;

    public Promotion getPromotion(String promotionID)throws IOException;
    
    public int getSavedOrderQuantity() throws IOException;
}
