/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

import entity.CashCountSheet;
import entity.CashCountSheetDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;


/**
 *
 * @author Hoàng Khang
 */
public class CashCountSheet_DAO {
	
	EntityManager em;

    public CashCountSheet_DAO() {
    	em = AccessDatabase.getInstance();
	}

    public CashCountSheet getOne(String id) {
        CashCountSheet cashCountSheet = null;
    
        try {
            TypedQuery<CashCountSheet> query = em.createQuery(
                "SELECT c FROM CashCountSheet c JOIN FETCH c.cashCountSheetDetailList WHERE c.cashCountSheetID = :id", 
                CashCountSheet.class);
            query.setParameter("id", id);
            cashCountSheet = query.getSingleResult();
            List<CashCountSheetDetail> cashCountSheetDetailList = cashCountSheet.getCashCountSheetDetailList();
            for (CashCountSheetDetail cashCountSheetDetail : cashCountSheetDetailList) {
                cashCountSheetDetail.getEmployee(); // Truy cập vào tất cả các thuộc tính cần thiết
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    
        return cashCountSheet;
    }
    
    /**
     * Lấy ra tất cả các CashCountSheet
     */
    public List<CashCountSheet> getAll() {
    	System.out.println("getAll");
    	return em.createNamedQuery("CashCountSheet.findAll", CashCountSheet.class).getResultList();
    }

    public String generateID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getMaxSequence(String code) {
        try {
            code += "%";
            TypedQuery<String> query = em.createQuery(
                "SELECT c.cashCountSheetID FROM CashCountSheet c WHERE c.cashCountSheetID LIKE :code ORDER BY c.cashCountSheetID DESC", 
                String.class);
            query.setParameter("code", code);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public synchronized Boolean create(CashCountSheet cashCountSheet) {
        try {
            em.getTransaction().begin();
            em.persist(cashCountSheet);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
