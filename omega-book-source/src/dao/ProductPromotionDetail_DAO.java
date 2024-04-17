package dao;

import java.util.ArrayList;

import entity.ProductPromotionDetail;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class ProductPromotionDetail_DAO implements DAOBase<ProductPromotionDetail> {
	EntityManager em;
	public ProductPromotionDetail_DAO() {
		em = AccessDatabase.getEntityManager();
	}

    public ProductPromotionDetail getOne(String promotionID, String productID) {
        return em.createNamedQuery("ProductPromotionDetail.findByPromotionAndProduct", ProductPromotionDetail.class)
        		.setParameter("pd.promotion.promotionID", promotionID)
        		.setParameter("pd.promotion.productID", productID)
        		.getSingleResult();
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
        return (ArrayList<ProductPromotionDetail>) em.createQuery(query, ProductPromotionDetail.class).setParameter(1, productID).getResultList();
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
