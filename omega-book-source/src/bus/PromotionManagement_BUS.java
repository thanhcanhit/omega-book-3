package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.PromotionForOrder;
import entity.PromotionForProduct;
import enums.DiscountType;
import enums.PromotionType;

public interface PromotionManagement_BUS {
    
    public ArrayList<Promotion> getAllPromotion();
    public ArrayList<PromotionForOrder> getAllPromotionForOrder();
    public ArrayList<PromotionForProduct> getAllPromotionForProduct() ;
    
    public Promotion getOne(String promotionID);
    
    public String generateID(PromotionType promotionType, DiscountType typeDiscount, Date ended) ;

    public Promotion getPromotion(String promotionID) ;

    public ArrayList<Promotion> searchById(String searchQuery);

    public ArrayList<Promotion> filter(int type, int status);

    public boolean addNewPromotion(Promotion newPromotion);

    public boolean removePromotion(String promotionID) ;

    public Product searchProductById(String searchQuery) ;
    public ArrayList<PromotionForOrder> searchForOrderById(String searchQuery);

    public Product getProduct(String productID) ;

    public void createProductPromotionDetail(Promotion newPromotion, ArrayList<ProductPromotionDetail> cart);

    public boolean removeProductPromotionDetail(String promotionID) ;

    public boolean removeProductPromotionOther(Promotion pm) ;

    public boolean removeOrderPromotionOther(Promotion pm) ;

    public boolean addNewOrderPromotion(PromotionForOrder newPromotion) ;

    public ArrayList<PromotionForProduct> filterForProduct(int type, int status);

    public ArrayList<PromotionForOrder> filterForOrder(int type, int status);

    public Product getOneProduct(String productID) ;
}
