package bus;

import java.util.ArrayList;
import entity.Customer;
import entity.Order;
import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;

public interface Sales_BUS {
	public Product getProduct(String id) ;

    public Customer getCustomerByPhone(String phone) ;

    public Order createNewOrder() throws Exception;

    public boolean saveToDatabase(Order order);
    public boolean updateInDatabase(Order order) ;

    public boolean decreaseProductInventory(Product product, int quantity);

    public boolean increaseProductInventory(Product product, int quantity) ;

    public ArrayList<ProductPromotionDetail> getListProductPromotionAvailable(String productID) ;
    public ArrayList<Order> getSavedOrders();
    public Order getOrder(String id);

    public boolean deleteOrder(String id) ;
    public ArrayList<ProductPromotionDetail> getPromotionOfProductAvailable(String productID) ;

    public double getBestProductPromotionDiscountAmountAvailable(String productID) ;

    public ArrayList<Promotion> getPromotionOfOrderAvailable(int customerRank);

    public Promotion getPromotion(String promotionID);
    
    public int getSavedOrderQuantity();
}
