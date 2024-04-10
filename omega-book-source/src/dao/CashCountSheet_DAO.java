/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.ConnectDB;
import entity.CashCount;
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
public class CashCountSheet_DAO implements interfaces.DAOBase<CashCountSheet> {
	
	EntityManager em;

    public CashCountSheet_DAO() {
    	em = AccessDatabase.getEntityManager();
	}

	private CashCount_DAO cashCount_DAO = new CashCount_DAO();
    private CashCountSheetDetail_DAO cashCountSheetDetail_DAO = new CashCountSheetDetail_DAO();

    @Override
    public CashCountSheet getOne(String id) {
        CashCountSheet cashCountSheet = null;

        try {
            String sql = "SELECT * FROM CashCountSheet WHERE cashCountSheetID = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Timestamp startTimestamp = resultSet.getTimestamp("startedDate");
                Timestamp endTimestamp = resultSet.getTimestamp("endedDate");
                
                Date startDate = new Date(startTimestamp.getTime());
                Date endDate = new Date(endTimestamp.getTime());

                cashCountSheet = new CashCountSheet(id, cashCount_DAO.getAll(id), cashCountSheetDetail_DAO.getAllCashCountSheetDetailInCashCountSheet(id), startDate, endDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cashCountSheet;
    }

    @Override
    /**
     * Lấy ra tất cả các CashCountSheet
     */
    public ArrayList<CashCountSheet> getAll() {
//        ArrayList<CashCountSheet> cashCountSheets = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM CashCountSheet";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String cashCountSheetID = resultSet.getString("cashCountSheetID");
//                Timestamp startTimestamp = resultSet.getTimestamp("startedDate");
//                Timestamp endTimestamp = resultSet.getTimestamp("endedDate");
//                
//                Date startDate = new Date(startTimestamp.getTime());
//                Date endDate = new Date(endTimestamp.getTime());
//                CashCountSheet cashCountSheet = new CashCountSheet(cashCountSheetID, new CashCount_DAO().getAll(cashCountSheetID), new CashCountSheetDetail_DAO().getAllCashCountSheetDetailInCashCountSheet(cashCountSheetID), startDate, endDate);
//
//                cashCountSheets.add(cashCountSheet);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return cashCountSheets;
    	return (ArrayList<CashCountSheet>) em.createNamedQuery("CashCountSheet.findAll").getResultList();
    }

    @Override
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
    @Override
    public Boolean create(CashCountSheet cashCountSheet) {
        try {
            em.getTransaction().begin();
            em.persist(cashCountSheet);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Boolean update(String id, CashCountSheet newObject
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delete(String id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
