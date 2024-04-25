/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

import javax.management.Notification;

import entity.AcountingVoucher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import utilities.AccessDatabase;

/**
 *
 * @author Hoàng Khang
 */
public class AcountingVoucher_DAO implements interfaces.DAOBase<AcountingVoucher> {
	private static EntityManager entityManager;


    public AcountingVoucher_DAO() {
    	 entityManager = AccessDatabase.getInstance();
    }

    @Override
    public AcountingVoucher getOne(String acountingVoucherID) {
    	return entityManager.find(AcountingVoucher.class, acountingVoucherID);
    }

    public String getMaxSequence(String code) {
        try {
            String hql = "FROM AcountingVoucher av WHERE av.accountingVoucherID LIKE :code ORDER BY av.accountingVoucherID DESC";
            Query query = entityManager.createQuery(hql);
            query.setParameter("code", code + "%");
            query.setMaxResults(1);
            AcountingVoucher result = (AcountingVoucher) query.getSingleResult();
            return result != null ? result.getAcountingVoucherID() : null;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public ArrayList<AcountingVoucher> getAll() {
        return (ArrayList<AcountingVoucher>) entityManager.createNamedQuery("AcountingVoucher.findAll", AcountingVoucher.class).getResultList();
    }

    @Override
    public String generateID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public synchronized Boolean create(AcountingVoucher acountingVoucher) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(acountingVoucher);
			entityManager.getTransaction().commit();
			System.out.println("tạo phiếu kế toán mới!");
			return true;
		} catch (Exception e) {
			System.out.println("Không thể tạo phiếu kế toán mới!");
			entityManager.getTransaction().rollback();
//			entityManager.clear();
		}
		return false;
    }

    @Override
    public synchronized Boolean update(String id, AcountingVoucher newObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public synchronized Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
