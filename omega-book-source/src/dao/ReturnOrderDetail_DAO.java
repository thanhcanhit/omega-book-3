package dao;

import java.util.*;
import entity.Product;
//import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class ReturnOrderDetail_DAO implements DAOBase<ReturnOrderDetail>{
	EntityManager em;
	public ReturnOrderDetail_DAO() {
		em = AccessDatabase.getEntityManager();
	}

	public ReturnOrderDetail getOne(String returnOrderID, String productID) {
		return em.createNamedQuery("ReturnOrderDetail.findByReturnOrderIDAndProductID", ReturnOrderDetail.class)
				.setParameter("returnOrderID", returnOrderID).setParameter("productID", productID).getSingleResult();
	}

	@Override
	public ArrayList<ReturnOrderDetail> getAll() {
		return (ArrayList<ReturnOrderDetail>) em.createNamedQuery("ReturnOrderDetail.findAll", ReturnOrderDetail.class).getResultList();
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Boolean create(ReturnOrderDetail returnOrderDetail) {
		int n = 0;
		if(em.getTransaction().isActive() == true) em.getTransaction().rollback();
		try {
			em.getTransaction().begin();
			em.persist(returnOrderDetail);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return n > 0;
	}
	public Boolean updateProduct(String id, int quantity) {
		int n = 0;
		Product product = new Product_DAO().getOne(id);
		int newQuantity = product.getInventory() - quantity;
		try {
			product.setInventory(newQuantity);
			n = new Product_DAO().update(id, product) ? 1 : 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return n > 0;
	}
	public Boolean updateRefund(ReturnOrderDetail returnOrderDetail) {
		int n = 0;
	
		return n > 0;
	}
	public ArrayList<ReturnOrderDetail> getAllForOrderReturnID(String id) {
		return (ArrayList<ReturnOrderDetail>) em.createNamedQuery("ReturnOrderDetail.findByReturnOrderID", ReturnOrderDetail.class)
				.setParameter("returnOrderID", id).getResultList();
	}

	@Override
	public Boolean update(String id, ReturnOrderDetail newObject) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public ReturnOrderDetail getOne(String id) {
		throw new UnsupportedOperationException("Not supported yet."); 	
	}



}
