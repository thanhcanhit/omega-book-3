package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;

public interface ReturnOrderManagement_BUS {
	public Order getOrder(String orderID) ;

    public ReturnOrder getReturnOrder(String returnOrderID);
    public ArrayList<ReturnOrder> getAllReturnOrder() ;

    public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID);

    public boolean updateReturnOder(ReturnOrder newReturnOrder);

    public ArrayList<ReturnOrder> searchById(String returnOrderID) ;

    public ArrayList<ReturnOrder> filter(int type, int status);

    public ArrayList<OrderDetail> getAllOrderDetail(String orderID) ;
    public ArrayList<Order> getAllOrder() ;

    public String getNameProduct(String productID);
    
    public String generateID(Date returnDate) ;

    public boolean createNew(ReturnOrder newReturnOrder);

    public Order searchByOrderId(String orderID) ;

    public Product getProduct(String productID);

    public void createReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> cart) ;

    public void updateReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> listDetail);
    

    public boolean isExist(Order order) ;

    public Promotion getDiscount(String orderID) ;
}
