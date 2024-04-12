/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Shift;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author Hoàng Khang
 */
public class Shift_DAO implements DAOBase<Shift> {

//    private Employee_DAO employee_DAO = new Employee_DAO();
	EntityManager entityManager;

	public Shift_DAO() {
		entityManager = AccessDatabase.getEntityManager();
	}

	@Override
	public Shift getOne(String id) {
//        Shift shift = null;
//        try {
//            String sql = "SELECT * FROM Shift WHERE shiftID = ?";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//            preparedStatement.setString(1, id);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                String employeeID = rs.getString("employeeID");
//                String shiftID = rs.getString("shiftID");
//                Timestamp startTimestamp = rs.getTimestamp("startedAt");
//                Timestamp endTimestamp = rs.getTimestamp("endedAt");
//
//                Date started = new java.sql.Date(startTimestamp.getTime());
//                Date ended = new java.sql.Date(endTimestamp.getTime());
//
//                shift = new Shift(shiftID, started, ended, new Account(employee_DAO.getOne(employeeID)));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return shift;
		return entityManager.find(Shift.class, id);
	}

	/**
	 * Get all shifts by date
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<Shift> getShiftsByDate(Date date) {
		List<Shift> shifts = new ArrayList<>();

		// Chuyển đổi ngày thành LocalDate để sử dụng trong truy vấn HQL
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date startOfDay = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endOfDay = Date
				.from(localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusSeconds(1).toInstant());

		try {
			String hql = "FROM Shift s WHERE s.startedAt >= :startOfDay AND s.startedAt <= :endOfDay";
			TypedQuery<Shift> query = entityManager.createQuery(hql, Shift.class);
			query.setParameter("startOfDay", startOfDay);
			query.setParameter("endOfDay", endOfDay);

			shifts = query.getResultList();
		} catch (NoResultException nre) {
			shifts = new ArrayList<>();
		} 

		return (ArrayList<Shift>) shifts;
	}

	@Override
	public ArrayList<Shift> getAll() {
//        ArrayList<Shift> result = new ArrayList<>();
//        try {
//            Statement st = ConnectDB.conn.createStatement();
//            ResultSet rs = st.executeQuery("Select * from Shift");
//            while (rs.next()) {
//                String shiftID = rs.getString("shiftID");
//                result.add(getOne(shiftID));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
		return (ArrayList<Shift>) entityManager.createNamedQuery("Shift.findAll", Shift.class).getResultList();
	}

	@Override
	public String generateID() {
		String code = "PH";
		String maxID = "";
		String newID = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String hour = sdf.format(date);
		if (Integer.parseInt(hour) < 14) {
			code += "01";
		} else {
			code += "02";
		}
		SimpleDateFormat datef = new SimpleDateFormat("ddMMyyyy");
		code += datef.format(date);
		newID = code;

		try {
			String hql = "FROM Shift s WHERE s.shiftID LIKE :code ORDER BY s.shiftID DESC";
			Query query = entityManager.createQuery(hql);
			query.setParameter("code", code + "%");
			query.setMaxResults(1);
			Shift result = (Shift) query.getSingleResult();
			maxID = result != null ? result.getShiftID() : "";
		} catch (NoResultException nre) {
			maxID = "";
		}

		if (maxID.equals("")) {
			newID += "0000";
		} else {
			String lastFourChars = maxID.substring(maxID.length() - 4);
			int num = Integer.parseInt(lastFourChars);
			num++;
			newID += String.format("%04d", num);
		}
		return newID;
	}

	@Override
	/**
	 * Create a new shift
	 */
	public Boolean create(Shift shift) {
//        int n = 0;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into Shift(shiftID, startedAt, endedAt, employeeID) "
//                    + " values(?,?,?,?)");
//            st.setString(1, shift.getShiftID());
//
//            Timestamp end = new Timestamp(shift.getStartedAt().getTime());
//            st.setTimestamp(2, end);
//
//            Timestamp start = new Timestamp(shift.getEndedAt().getTime());
//            st.setTimestamp(3, start);
//
//            st.setString(4, shift.getAccount().getEmployee().getEmployeeID());
//
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(shift);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean update(String id, Shift newObject) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
