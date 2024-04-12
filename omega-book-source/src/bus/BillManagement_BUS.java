package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Employee;
import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;

public interface BillManagement_BUS {
	public Promotion getPromotion(String promotionID);
    public ArrayList<Bill> getDataOfPage(int page);
    public ArrayList<Bill> getAll();
    public Employee getEmployee(String emplpyeeID) ;
;
    public Product getProduct(String productID);

    public Bill getOrder(String ID) throws Exception ;

    public ArrayList<OrderDetail> getOrderDetailList(String orderID);

    public int getLastPage() ;

    public ArrayList<Bill> orderListWithFilter(String orderID, String customerID, String phoneNumber, String priceFrom, String priceTo, Date orderFrom, Date orderTo) ;
}
