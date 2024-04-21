package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Employee;
import entity.Product;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import entity.Supplier;

public interface PurchaseOrderManagement_BUS extends Remote{
	 public Employee getEmployee(String emplpyeeID) throws RemoteException;
	    
	    public Supplier getSupplier(String supplierID) throws RemoteException;


	    public Product getProduct(String productID) throws RemoteException;

	    public PurchaseOrder getPurchaseOrder(String ID) throws Exception;
	    public ArrayList<PurchaseOrder> getAll() throws RemoteException;

	    public ArrayList<PurchaseOrderDetail> getPurchaseOrderDetailList(String purchaseOrderID) throws RemoteException ;
	    public Boolean updateStatus(String id, int status) throws RemoteException;
}
