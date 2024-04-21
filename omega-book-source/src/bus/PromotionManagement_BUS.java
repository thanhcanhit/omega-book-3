package bus;

import java.io.IOException;
import java.rmi.Remote;
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
    
    public ArrayList<Promotion> getAllPromotion() throws IOException;
    public ArrayList<PromotionForOrder> getAllPromotionForOrder()throws IOException;
    public ArrayList<PromotionForProduct> getAllPromotionForProduct()throws IOException ;
    
    public Promotion getOne(String promotionIDthrows)throws IOException;
    
    public String generateID(PromotionType promotionType, DiscountType typeDiscount, Date ended)throws IOException ;

    public Promotion getPromotion(String promotionID)throws IOException ;

    public ArrayList<Promotion> searchById(String searchQuery)throws IOException;

    public ArrayList<Promotion> filter(int type, int status)throws IOException;

    public boolean addNewPromotion(Promotion newPromotion)throws IOException;

    public boolean removePromotion(String promotionID)throws IOException ;

    public Product searchProductById(String searchQuery)throws IOException ;
    public ArrayList<PromotionForOrder> searchForOrderById(String searchQuery)throws IOException;

    public Product getProduct(String productID)throws IOException ;

    public void createProductPromotionDetail(Promotion newPromotion, ArrayList<ProductPromotionDetail> cart)throws IOException;

    public boolean removeProductPromotionDetail(String promotionID)throws IOException ;

    public boolean removeProductPromotionOther(Promotion pm)throws IOException ;

    public boolean removeOrderPromotionOther(Promotion pm)throws IOException ;

    public boolean addNewOrderPromotion(PromotionForOrder newPromotion)throws IOException ;

    public ArrayList<PromotionForProduct> filterForProduct(int type, int status)throws IOException;

    public ArrayList<PromotionForOrder> filterForOrder(int type, int status)throws IOException;

    public Product getOneProduct(String productID) throws IOException;
}
