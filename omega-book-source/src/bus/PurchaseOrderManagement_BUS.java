package bus;

import java.util.ArrayList;

import entity.Employee;
import entity.Product;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import entity.Supplier;

public interface PurchaseOrderManagement_BUS {
	 public Employee getEmployee(String emplpyeeID);
	    
	    public Supplier getSupplier(String supplierID) ;


	    public Product getProduct(String productID) ;

	    public PurchaseOrder getPurchaseOrder(String ID) throws Exception;
	    public ArrayList<PurchaseOrder> getAll();

	    public ArrayList<PurchaseOrderDetail> getPurchaseOrderDetailList(String purchaseOrderID) ;
	    public Boolean updateStatus(String id, int status);
}
