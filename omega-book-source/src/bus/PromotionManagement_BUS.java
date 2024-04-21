package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.PromotionForOrder;
import entity.PromotionForProduct;
import enums.DiscountType;
import enums.PromotionType;

public interface PromotionManagement_BUS extends Remote{
    
    public ArrayList<Promotion> getAllPromotion() throws RemoteException;
    public ArrayList<PromotionForOrder> getAllPromotionForOrder()throws RemoteException;
    public ArrayList<PromotionForProduct> getAllPromotionForProduct()throws RemoteException ;
    
    public Promotion getOne(String promotionIDthrows)throws RemoteException;
    
    public String generateID(PromotionType promotionType, DiscountType typeDiscount, Date ended)throws RemoteException ;

    public Promotion getPromotion(String promotionID)throws RemoteException ;

    public ArrayList<Promotion> searchById(String searchQuery)throws RemoteException;

    public ArrayList<Promotion> filter(int type, int status)throws RemoteException;

    public boolean addNewPromotion(Promotion newPromotion)throws RemoteException;

    public boolean removePromotion(String promotionID)throws RemoteException ;

    public Product searchProductById(String searchQuery)throws RemoteException ;
    public ArrayList<PromotionForOrder> searchForOrderById(String searchQuery)throws RemoteException;

    public Product getProduct(String productID)throws RemoteException ;

    public void createProductPromotionDetail(Promotion newPromotion, ArrayList<ProductPromotionDetail> cart)throws RemoteException;

    public boolean removeProductPromotionDetail(String promotionID)throws RemoteException ;

    public boolean removeProductPromotionOther(Promotion pm)throws RemoteException ;

    public boolean removeOrderPromotionOther(Promotion pm)throws RemoteException ;

    public boolean addNewOrderPromotion(PromotionForOrder newPromotion)throws RemoteException ;

    public ArrayList<PromotionForProduct> filterForProduct(int type, int status)throws RemoteException;

    public ArrayList<PromotionForOrder> filterForOrder(int type, int status)throws RemoteException;

    public Product getOneProduct(String productID) throws RemoteException;
}
