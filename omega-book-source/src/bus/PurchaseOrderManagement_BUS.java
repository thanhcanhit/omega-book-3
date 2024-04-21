package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import entity.Employee;
import entity.Product;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import entity.Supplier;

public interface PurchaseOrderManagement_BUS extends Remote{
	 public Employee getEmployee(String emplpyeeID) throws IOException;
	    
	    public Supplier getSupplier(String supplierID) throws IOException;


	    public Product getProduct(String productID) throws IOException;

	    public PurchaseOrder getPurchaseOrder(String ID) throws Exception;
	    public ArrayList<PurchaseOrder> getAll() throws IOException;

	    public ArrayList<PurchaseOrderDetail> getPurchaseOrderDetailList(String purchaseOrderID) throws IOException ;
	    public Boolean updateStatus(String id, int status) throws IOException;
}
