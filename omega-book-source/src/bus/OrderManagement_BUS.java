package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Employee;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;

public interface OrderManagement_BUS {
	public Promotion getPromotion(String promotionID);
    public ArrayList<Order> getDataOfPage(int page);
    public ArrayList<Order> getAll();
    public Employee getEmployee(String emplpyeeID) ;
;
    public Product getProduct(String productID);

    public Order getOrder(String ID) throws Exception ;

    public ArrayList<OrderDetail> getOrderDetailList(String orderID);

    public int getLastPage() ;

    public ArrayList<Order> orderListWithFilter(String orderID, String customerID, String phoneNumber, String priceFrom, String priceTo, Date orderFrom, Date orderTo) ;
}
