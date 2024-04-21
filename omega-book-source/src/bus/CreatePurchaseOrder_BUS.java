package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import entity.Product;
import entity.PurchaseOrder;
import entity.Supplier;

public interface CreatePurchaseOrder_BUS extends Remote{
	public Product getProduct(String id)throws IOException;
	public ArrayList<Supplier> getAllSuplier() throws IOException;
	public PurchaseOrder createNewPurchaseOrder() throws IOException;
	public boolean saveToDatabase(PurchaseOrder order) throws IOException;
}
