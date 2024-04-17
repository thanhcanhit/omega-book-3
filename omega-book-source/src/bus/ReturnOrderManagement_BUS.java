package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;

public interface ReturnOrderManagement_BUS {
	public Bill getOrder(String orderID) ;

    public ReturnOrder getReturnOrder(String returnOrderID);
    public ArrayList<ReturnOrder> getAllReturnOrder() ;

    public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID);

    public boolean updateReturnOder(ReturnOrder newReturnOrder);

    public ArrayList<ReturnOrder> searchById(String returnOrderID) ;

    public ArrayList<ReturnOrder> filter(int type, int status);

    public ArrayList<OrderDetail> getAllOrderDetail(String orderID) ;
    public ArrayList<Bill> getAllOrder() ;

    public String getNameProduct(String productID);
    
    public String generateID(Date returnDate) ;

    public boolean createNew(ReturnOrder newReturnOrder);

    public Bill searchByOrderId(String orderID) ;

    public Product getProduct(String productID);

    public boolean createReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> cart) ;

    public void updateReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> listDetail);
    

    public boolean isExist(Bill order) ;

    public Promotion getDiscount(String orderID) ;
}
