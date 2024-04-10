package dao;

import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;

import java.util.*;
import java.time.LocalDate;

/**
 *
 * @author Như Tâm
 */
public class ProductPromotionDetail_DAO implements DAOBase<ProductPromotionDetail> {
	EntityManager em;
	public ProductPromotionDetail_DAO() {
		em = AccessDatabase.getEntityManager();
	}

    public ProductPromotionDetail getOne(String promotionID, String productID) throws Exception {
        ProductPromotionDetail productPromotionDetail = null;
        Promotion promotion = new Promotion(promotionID);
        Product product = new Product(productID);
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM ProductPromotionDetail "
//                    + "WHERE promotionID = ? and productID = ?");
//            st.setString(1, promotionID);
//            st.setString(2, productID);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {                
//                Promotion promotion = new Promotion_DAO().getOne(promotionID);
//                Product product = new Product_DAO().getOne(productID);
//                productPromotionDetail = new ProductPromotionDetail(promotion, product);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        productPromotionDetail = new ProductPromotionDetail(promotion, product);
        return productPromotionDetail;
    }

    public ArrayList<ProductPromotionDetail> getAllForProduct(String productID) {
		return (ArrayList<ProductPromotionDetail>) em.createNamedQuery("ProductPromotionDetail.findByProduct", ProductPromotionDetail.class)
				.setParameter("product.productID", productID).getResultList();
    }

    public ArrayList<ProductPromotionDetail> getAllForPromotion(String promotionID) {
        return (ArrayList<ProductPromotionDetail>) em.createNamedQuery("ProductPromotionDetail.findByPromotion", ProductPromotionDetail.class)
        		.setParameter("promotion.promotionID", promotionID).getResultList();
    }

    @SuppressWarnings("unchecked")
	public ArrayList<ProductPromotionDetail> getAllForProductAndAvailable(String productID) {        
        String query = """
                       select * 
                       from ProductPromotionDetail as pd join Promotion as p 
                       on pd.promotionID = p.promotionID
                       where endedDate > GETDATE() and productID = ?
                       """;
        return (ArrayList<ProductPromotionDetail>) em.createNativeQuery(query, ProductPromotionDetail.class).setParameter(1, productID).getResultList();
    }

    @Override
    public ProductPromotionDetail getOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ArrayList<ProductPromotionDetail> getAll() {
    	return (ArrayList<ProductPromotionDetail>) em.createNamedQuery("ProductPromotionDetail.findAll", ProductPromotionDetail.class).getResultList();

    }

    @Override
    public String generateID() {
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean create(ProductPromotionDetail productPromotionDetail) {
        int n = 0;
        try {
			em.getTransaction().begin();
			em.persist(productPromotionDetail);
			em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public Boolean update(String id, ProductPromotionDetail newObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
