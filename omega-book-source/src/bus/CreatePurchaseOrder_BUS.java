package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Product;
import entity.PurchaseOrder;
import entity.Supplier;

public interface CreatePurchaseOrder_BUS extends Remote{
	public Product getProduct(String id)throws RemoteException;
	public ArrayList<Supplier> getAllSuplier() throws RemoteException;
	public PurchaseOrder createNewPurchaseOrder() throws Exception;
	public boolean saveToDatabase(PurchaseOrder order) throws RemoteException;
}
