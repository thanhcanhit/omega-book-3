/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import entity.CashCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author Hoàng Khang
 */
public class CashCount_DAO implements interfaces.DAOBase<CashCount> {
	EntityManager entityManager;

	public CashCount_DAO() {
		entityManager = AccessDatabase.getEntityManager();
	}

	public CashCount getCashCount(double value, String cashCountSheetID) {
		CashCount cashCount = null;

		try {
			String hql = "SELECT c FROM CashCount c WHERE c.cashCountSheetID = :cashCountSheetID AND c.value = :value";
			TypedQuery<CashCount> query = entityManager.createQuery(hql, CashCount.class);
			query.setParameter("cashCountSheetID", cashCountSheetID);
			query.setParameter("value", value);

			List<CashCount> resultList = query.getResultList();

			if (!resultList.isEmpty()) {
				cashCount = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cashCount;
	}

	@Override
	public CashCount getOne(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public ArrayList<CashCount> getAll() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	/**
	 * Phương thức lấy toàn các CashCount nằm trong CashCountSheet
	 * 
	 * @param cashCountSheetID mã phiếu kết toán cần lấy danh sách kiểm
	 *                         tiền
	 * @return ArrayList<CashCount>
	 */
	public ArrayList<CashCount> getAll(String cashCountSheetID) {
		return (ArrayList<CashCount>) entityManager
				.createNamedQuery("CashCount.findAllByCashCountSheetID", CashCount.class)
				.setParameter("cashCountSheetID", cashCountSheetID).getResultList();
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean create(CashCount cashCount) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	/**
	 * Phương thức thêm một CashCount vào database
	 * 
	 * @param cashCount        CashCount cần thêm
	 * @param cashCountSheetID mã phiếu kết toán cần thêm CashCount
	 * @return Boolean
	 */
	public Boolean create(CashCount cashCount, String cashCountSheetID) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cashCount);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(String id, CashCount newObject) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
