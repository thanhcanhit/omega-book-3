package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;

public interface ReturnOrderManagement_BUS extends Remote{
	public Bill getOrder(String orderID) throws RemoteException;

    public ReturnOrder getReturnOrder(String returnOrderID)throws RemoteException;
    public ArrayList<ReturnOrder> getAllReturnOrder() throws RemoteException;

    public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID)throws RemoteException;

    public boolean updateReturnOder(ReturnOrder newReturnOrder)throws RemoteException;

    public ArrayList<ReturnOrder> searchById(String returnOrderID) throws RemoteException;

    public ArrayList<ReturnOrder> filter(int type, int status)throws RemoteException;

    public ArrayList<OrderDetail> getAllOrderDetail(String orderID)throws RemoteException ;
    public ArrayList<Bill> getAllOrder() throws RemoteException;

    public String getNameProduct(String productID)throws RemoteException;
    
    public String generateID(Date returnDate) throws RemoteException;

    public boolean createNew(ReturnOrder newReturnOrder)throws RemoteException;

    public Bill searchByOrderId(String orderID) throws RemoteException;

    public Product getProduct(String productID)throws RemoteException;

    public boolean createReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> cart) throws RemoteException;

    public void updateReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> listDetail)throws RemoteException;
    

    public boolean isExist(Bill order) throws RemoteException;

    public Promotion getDiscount(String orderID) throws RemoteException;
}
