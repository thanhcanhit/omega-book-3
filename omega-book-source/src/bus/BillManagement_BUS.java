package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.Bill;
import entity.Employee;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;

public interface BillManagement_BUS extends Remote{
	public Promotion getPromotion(String promotionID) throws IOException;
    public ArrayList<Bill> getDataOfPage(int page)throws IOException;
    public ArrayList<Bill> getAll()throws IOException;
    public Employee getEmployee(String emplpyeeID) throws IOException;
;
    public Product getProduct(String productID)throws IOException;

    public Bill getOrder(String ID) throws IOException, Exception;

    public ArrayList<OrderDetail> getOrderDetailList(String orderID)throws IOException;

    public int getLastPage() throws IOException;

    public ArrayList<Bill> orderListWithFilter(String orderID, String customerID, String phoneNumber, String priceFrom, String priceTo, Date orderFrom, Date orderTo) throws IOException;
}
