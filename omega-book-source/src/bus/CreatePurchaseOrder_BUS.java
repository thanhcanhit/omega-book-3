package bus;

import java.util.ArrayList;

import entity.Product;
import entity.PurchaseOrder;
import entity.Supplier;

public interface CreatePurchaseOrder_BUS {
	public Product getProduct(String id);
	public ArrayList<Supplier> getAllSuplier();
	public PurchaseOrder createNewPurchaseOrder() throws Exception;
	public boolean saveToDatabase(PurchaseOrder order);
}
