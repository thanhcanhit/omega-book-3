package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;

public interface ReturnOrderManagement_BUS extends Remote{
	public Bill getOrder(String orderID) throws IOException;

    public ReturnOrder getReturnOrder(String returnOrderID)throws IOException;
    public ArrayList<ReturnOrder> getAllReturnOrder() throws IOException;

    public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID)throws IOException;

    public boolean updateReturnOder(ReturnOrder newReturnOrder)throws IOException;

    public ArrayList<ReturnOrder> searchById(String returnOrderID) throws IOException;

    public ArrayList<ReturnOrder> filter(int type, int status)throws IOException;

    public ArrayList<OrderDetail> getAllOrderDetail(String orderID)throws IOException ;
    public ArrayList<Bill> getAllOrder() throws IOException;

    public String getNameProduct(String productID)throws IOException;
    
    public String generateID(Date returnDate) throws IOException;

    public boolean createNew(ReturnOrder newReturnOrder)throws IOException;

    public Bill searchByOrderId(String orderID) throws IOException;

    public Product getProduct(String productID)throws IOException;

    public boolean createReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> cart) throws IOException;

    public void updateReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> listDetail)throws IOException;
    

    public boolean isExist(Bill order) throws IOException;

    public Promotion getDiscount(String orderID) throws IOException;
}
