/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

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
	EntityManager entityManager;

    private CashCountSheet_DAO cashCountSheet_DAO = new CashCountSheet_DAO();

    public AcountingVoucher_DAO() {
    	entityManager = AccessDatabase.getEntityManager();
    }

    @Override
    public AcountingVoucher getOne(String acountingVoucherID) {
//        AcountingVoucher acountingVoucher = null;
//        try {
//            String sql = "SELECT * FROM AcountingVoucher WHERE acountingVoucherID = ?";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//            preparedStatement.setString(1, acountingVoucherID);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//
//                Timestamp startTimestamp = resultSet.getTimestamp("startedDate");
//                Timestamp endTimestamp = resultSet.getTimestamp("endedDate");
//
//                Date startDate = new java.sql.Date(startTimestamp.getTime());
//                Date endDate = new java.sql.Date(endTimestamp.getTime());
//                String cashCountSheetID = resultSet.getString("cashCountSheetID");
//
//                acountingVoucher = new AcountingVoucher(acountingVoucherID, startDate, endDate, cashCountSheet_DAO.getOne(cashCountSheetID), new Order_DAO().getAllOrderInAcountingVoucher(acountingVoucherID));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return acountingVoucher;
    	
    	return entityManager.find(AcountingVoucher.class, acountingVoucherID);
    }

    public String getMaxSequence(String code) {
        try {
            String hql = "FROM AcountingVoucher av WHERE av.acountingVoucherID LIKE :code ORDER BY av.acountingVoucherID DESC";
            Query query = entityManager.createQuery(hql);
            query.setParameter("code", code + "%");
            query.setMaxResults(1);
            AcountingVoucher result = (AcountingVoucher) query.getSingleResult();
            return result != null ? result.getAcountingVoucherID() : null;
        } catch (NoResultException nre) {
            return null;
        } finally {
        	entityManager.close();
        }
    }

    @Override
    public ArrayList<AcountingVoucher> getAll() {
//        ArrayList<AcountingVoucher> acountingVouchers = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM AcountingVoucher";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String acountingVoucherID = resultSet.getString("acountingVoucherID");
//                Timestamp startTimestamp = resultSet.getTimestamp("startedDate");
//                Timestamp endTimestamp = resultSet.getTimestamp("endedDate");
//
//                Date startDate = new java.sql.Date(startTimestamp.getTime());
//                Date endDate = new java.sql.Date(endTimestamp.getTime());
//
//                String cashCountSheetID = resultSet.getString("cashCountSheetID");
//                CashCountSheet cashCountSheet = cashCountSheet_DAO.getOne(cashCountSheetID);
//                
//                 ArrayList<Order> orList = new Order_DAO().getAllOrderInAcountingVoucher(acountingVoucherID);
//                 
//                AcountingVoucher acountingVoucher = new AcountingVoucher(acountingVoucherID, startDate, endDate, cashCountSheet, new Order_DAO().getAllOrderInAcountingVoucher(acountingVoucherID));
//
//                acountingVouchers.add(acountingVoucher);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return (ArrayList<AcountingVoucher>) entityManager.createNamedQuery("AcountingVoucher.findAll", AcountingVoucher.class).getResultList();
    }

    @Override
    public String generateID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean create(AcountingVoucher acountingVoucher) {
//        try {
//            String sql = "INSERT INTO AcountingVoucher (acountingVoucherID, startedDate, endedDate, cashCountSheetID) VALUES (?, ?, ?, ?)";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//
//            preparedStatement.setString(1, acountingVoucher.getAcountingVoucherID());
//            Timestamp end = new Timestamp(acountingVoucher.getEndedDate().getTime());
//            preparedStatement.setTimestamp(3, end);
//
//            Timestamp start = new Timestamp(acountingVoucher.getCreatedDate().getTime());
//            preparedStatement.setTimestamp(2, start);
//
//            preparedStatement.setString(4, acountingVoucher.getCashCountSheet().getCashCountSheetID());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                return true; // Thành công
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false; // Thất bại
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(acountingVoucher);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
    }

    @Override
    public Boolean update(String id, AcountingVoucher newObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
