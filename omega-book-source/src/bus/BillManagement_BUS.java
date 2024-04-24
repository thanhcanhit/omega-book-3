package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Bill;
import entity.Customer;
import entity.Employee;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;

public interface BillManagement_BUS extends Remote{
	public Promotion getPromotion(String promotionID) throws RemoteException;
    public ArrayList<Bill> getDataOfPage(int page)throws RemoteException;
    public ArrayList<Bill> getAll()throws RemoteException;
    public Employee getEmployee(String emplpyeeID) throws RemoteException;
;
    public Product getProduct(String productID)throws RemoteException;

    public Bill getOrder(String ID) throws RemoteException, Exception;

    public ArrayList<OrderDetail> getOrderDetailList(String orderID)throws RemoteException;

    public int getLastPage() throws RemoteException;
    public Customer getCustomer(String customerID)throws RemoteException;
    public ArrayList<Bill> orderListWithFilter(String orderID, String customerID, String phoneNumber, String priceFrom, String priceTo, Date orderFrom, Date orderTo) throws RemoteException;
}
