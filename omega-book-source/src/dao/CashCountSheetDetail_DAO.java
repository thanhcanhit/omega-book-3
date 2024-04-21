/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import entity.CashCountSheetDetail;
import jakarta.persistence.EntityManager;
import utilities.AccessDatabase;

/**
 *
 * @author Hoàng Khang
 */
public class CashCountSheetDetail_DAO implements interfaces.DAOBase<CashCountSheetDetail> {

	// private Employee_DAO emp_DAO = new Employee_DAO();
	EntityManager entityManager;

	public CashCountSheetDetail_DAO() {
		entityManager = AccessDatabase.getInstance();
	}

	@Override
	public CashCountSheetDetail getOne(String cashCountSheetID) {
		CashCountSheetDetail cashCountSheetDetail = null;

		try {
			cashCountSheetDetail = entityManager
					.createQuery("SELECT c FROM CashCountSheetDetail c WHERE c.cashCountSheet.id = :cashCountSheetID", CashCountSheetDetail.class)
					.setParameter("cashCountSheetID", cashCountSheetID)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cashCountSheetDetail;
	}

	@Override
	public ArrayList<CashCountSheetDetail> getAll() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

//	@NamedQuery(name = "CashCountSheetDetail.findAllByCashCountSheetID", query = "SELECT c FROM CashCountSheetDetail c WHERE c.cashCountSheet.id = :cashCountSheetID")
	public ArrayList<CashCountSheetDetail> getAllCashCountSheetDetailInCashCountSheet(String cashCountSheetID) {
//		ArrayList<CashCountSheetDetail> cashCountSheetDetails = new ArrayList<>();
//
//		try {
//			String sql = "SELECT * FROM CashCountSheetDetail WHERE cashCountSheetID = ?";
//			PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//			preparedStatement.setString(1, cashCountSheetID);
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//				boolean index = resultSet.getBoolean("auditorIndex");
//				String employeeID = resultSet.getString("employeeID");
//
//				Employee employee = emp_DAO.getOne(employeeID);
//				CashCountSheet cashCountSheet = new CashCountSheet(cashCountSheetID);
//
//				CashCountSheetDetail cashCountSheetDetail = new CashCountSheetDetail(index, employee, cashCountSheet);
//				cashCountSheetDetails.add(cashCountSheetDetail);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return cashCountSheetDetails;
		
		return (ArrayList<CashCountSheetDetail>) entityManager
				.createNamedQuery("CashCountSheetDetail.findAllByCashCountSheetID", CashCountSheetDetail.class)
				.setParameter("cashCountSheetID", cashCountSheetID).getResultList();
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean create(CashCountSheetDetail cashCountSheetDetail) {
//		try {
//			String sql = "INSERT INTO CashCountSheetDetail (auditorIndex, cashCountSheetID, employeeID) VALUES (?, ?, ?)";
//			PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//
//			preparedStatement.setBoolean(1, cashCountSheetDetail.isChecker());
//			preparedStatement.setString(2, cashCountSheetDetail.getCashCountSheet().getCashCountSheetID());
//			preparedStatement.setString(3, cashCountSheetDetail.getEmployee().getEmployeeID());
//			int rowsAffected = preparedStatement.executeUpdate();
//			if (rowsAffected > 0) {
//				return true; // Thành công
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false; // Thất bại
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cashCountSheetDetail);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}
	}

	@Override
	public Boolean update(String id, CashCountSheetDetail newObject) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
