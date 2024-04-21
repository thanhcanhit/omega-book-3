
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import entity.Customer;
//import entity.Employee;
import entity.Bill;
//import entity.OrderDetail;
import entity.Promotion;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilities.AccessDatabase;

/**
 *
 * @author KienTran
 */
public class Bill_DAO implements DAOBase<Bill> {

	EntityManager entityManager;

	public Bill_DAO() {
		entityManager = AccessDatabase.getEntityManager();
	}

	@Override
	public Bill getOne(String id) {
		return entityManager.find(Bill.class, id);
	}

	@Override
	public ArrayList<Bill> getAll() {
		List<Bill> list = entityManager.createNamedQuery("Bill.getAll", Bill.class).getResultList();
		ArrayList<Bill> result = new ArrayList<>(list);
		return result;
	}

	@Override
	public String generateID() {
		String result = "HD";
		LocalDate time = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		result += dateFormatter.format(time);

		String hql = "from Bill where orderID like :orderID order by orderID desc";

		try {
			Query query = entityManager.createQuery(hql);
			query.setParameter("orderID", result + "%");
			query.setMaxResults(1);
			Object object = null;
			try {
				object = query.getSingleResult();
			} catch (Exception e) {

			}

			if (object != null) {
				Bill order = (Bill) object;
				String lastID = order.getOrderID();
				String sNumber = lastID.substring(lastID.length() - 2);
				int num = Integer.parseInt(sNumber) + 1;
				result += String.format("%04d", num);
			} else {
				result += String.format("%04d", 0);
			}
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
//    	String result = "HD";
//        LocalDate time = LocalDate.now();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
//        result += dateFormatter.format(time);
//
//        try {
//            entityManager.getTransaction().begin();
//
//            String lastID = (String) entityManager.createNamedQuery("Bill.generateID", Bill.class).setParameter("prefix", result + "%")
//                    .setMaxResults(1).getSingleResult().toString();
//            if (lastID != null) {
//                String sNumber = lastID.substring(lastID.length() - 2);
//                int num = Integer.parseInt(sNumber) + 1;
//                result += String.format("%04d", num);
//            } else {
//                result += String.format("%04d", 0);
//            }
//
//            entityManager.getTransaction().commit();
//
//        } catch (Exception e) {
//            if (entityManager != null && entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//
//        return result;
	}

	@Override
	public Boolean create(Bill object) {

		entityManager.getTransaction().begin();
		entityManager.persist(object);
		entityManager.getTransaction().commit();
		return entityManager.find(Bill.class, object.getOrderID()) != null;
	}

	@Override
	public Boolean update(String id, Bill newObject) {
		try {
			if (!newObject.getOrderID().equals(id)) {
				return false;
			}
			entityManager.getTransaction().begin();
			entityManager.merge(newObject);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(String id) {
		int n = 0;
		try {
			Bill bill = entityManager.find(Bill.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(bill);
			entityManager.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return n > 0;
	}

	public int getLength() {
		int length = 0;
		try {
			String hql = "SELECT COUNT(*) FROM Bill";
			Query query = entityManager.createQuery(hql);
			length = Integer.parseInt(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}

	public ArrayList<Bill> getPage(int page) {
		List<Bill> list = new ArrayList<>();
		list = entityManager.createNamedQuery("Bill.getAll", Bill.class).setFirstResult(page).setMaxResults(50)
				.getResultList();
		ArrayList<Bill> result = new ArrayList<>(list);
		return result;
	}

	/**
	 * @param acountingVoucherID Mã phiếu kết toán
	 * @return ArrayList<Order> Danh sách hóa đơn đã được kết toán trong
	 *         phiếu kết toán
	 * @author Hoàng Khang
	 */
	public ArrayList<Bill> getAllOrderInAcountingVoucher(String accountingVoucherID) {
		ArrayList<Bill> result = new ArrayList<>();

		try {
			String hql = "SELECT o FROM Bill o " + "WHERE o.acountingVoucherID = :accountingVoucherID";

			List<Bill> orders = entityManager.createQuery(hql, Bill.class)
					.setParameter("accountingVoucherID", accountingVoucherID).getResultList();
			result.addAll(orders);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Phương thức thực hiện việc cập nhật mã phiếu kết toán cho một hóa
	 * đơn
	 *
	 * @param orderID            Mã hóa đơn
	 * @param acountingVoucherID Mã phiếu kết toán
	 * @author Hoàng Khang
	 */
	public boolean updateOrderAcountingVoucher(String orderID, String acountingVoucherID) {

		try {
			entityManager.getTransaction().begin();

			// Sử dụng câu truy vấn HQL để cập nhật trường acountingVoucherID của Order
			String hql = "UPDATE Bill o SET o.acountingVoucherID = :acountingVoucherID " + "WHERE o.orderID = :orderID";
			int updatedEntities = entityManager.createQuery(hql).setParameter("acountingVoucherID", acountingVoucherID)
					.setParameter("orderID", orderID).executeUpdate();

			entityManager.getTransaction().commit();
			return updatedEntities > 0;

		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * Phương thức thực hiện việc lấy ra các hoá đơn theo mã hoá đơn
	 *
	 * @param orderID Mã hóa đơn
	 * @author Như Tâm
	 */
	public ArrayList<Bill> findById(String orderID) {
		List<Bill> list = new ArrayList<>();

		try {

			String hql = "SELECT o FROM Bill o WHERE o.orderID LIKE :orderIDPattern";

			list = entityManager.createQuery(hql, Bill.class).setParameter("orderIDPattern", orderID + "%")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Bill> result = new ArrayList<>(list);
		return result;
	}

//    private Bill getOrderData(ResultSet rs) throws Exception {
//        Bill result = null;
//
//        //Lấy thông tin tổng quát của lớp order
//        String orderID = rs.getString("orderID");
//        String employeeID = rs.getString("employeeID");
//        String customerID = rs.getString("customerID");
//        Date orderAt = rs.getDate("orderAt");
//        boolean status = rs.getBoolean("status");
//        boolean payment = rs.getBoolean("payment");
//        double subTotal = rs.getDouble("subTotal");
//        double totalDue = rs.getDouble("totalDue");
//
//        Double moneyGiven = rs.getDouble("moneyGiven");
//        ArrayList<OrderDetail> orderDetailList = new OrderDetail_DAO().getAll(orderID);
//        Employee employee = new Employee(employeeID);
//        Customer customer = new Customer(customerID);
//        result = new Bill(orderID, orderAt, status, subTotal, totalDue, payment, employee, customer, orderDetailList, moneyGiven);
//        return result;
//    }

	public double[] getToTalInMonth(int month, int year) {
		double[] result = new double[31]; // Mảng kết quả, tối đa 31 ngày trong một tháng

		try {
			Arrays.fill(result, 0);

			String hql = "SELECT DAY(o.orderAt) AS dayOfMonth, SUM(o.totalDue) " + "FROM Bill o "
					+ "WHERE FUNCTION('YEAR', o.orderAt) = :year " + "AND FUNCTION('MONTH', o.orderAt) = :month "
					+ "AND o.status = true " + "GROUP BY DAY(o.orderAt)";

			List<Object[]> resultList = entityManager.createQuery(hql, Object[].class).setParameter("year", year)
					.setParameter("month", month).getResultList();
			for (Object[] row : resultList) {
				int dayOfMonth = (int) row[0];
				double totalDue = (double) row[1];
				result[dayOfMonth - 1] = totalDue;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }

		return result;
	}

	public int getNumberOfOrderInMonth(int month, int year) {

		int result = 0;

		try {

			String hql = "SELECT COUNT(o.orderID) FROM Bill o " + "WHERE FUNCTION('YEAR', o.orderAt) = :year "
					+ "AND FUNCTION('MONTH', o.orderAt) = :month " + "AND o.status = true";

			result = entityManager.createQuery(hql, Long.class).setParameter("year", year).setParameter("month", month)
					.getSingleResult().intValue();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public double getTotalInMonth(int month, int year) {
		double result = 0;

		try {
			List<Double> resultList = entityManager.createNamedQuery("Bill.getTotalInMonth", Double.class)
					.setParameter("year", year).setParameter("month", month).getResultList();
			if (resultList != null && !resultList.isEmpty() && resultList.get(0) != null) {
				result = resultList.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void clearExpiredOrderSaved() {
//    	String query = "SELECT * FROM Bill "
//    			+ "WHERE o.status = 0 AND "
//    			+ "DATEDIFF(DAY, o.orderAt, CONVERT(date, GETDATE())) < 1";
		try {
			OrderDetail_DAO od_DAO = new OrderDetail_DAO();
			List<Bill> resultSet = entityManager.createNamedQuery("Bill.clearExpiredOrderSaved", Bill.class)
					.getResultList();
//        	@SuppressWarnings("unchecked")
//			List<Bill> resultSet = entityManager.createNativeQuery(query).getResultList();
			resultSet.forEach(x -> {
				od_DAO.delete(x.getOrderID());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> getNotCompleteOrder() {
		ArrayList<Bill> result;
//        Xóa các hóa đơn lưu tạm quá 24 giờ  không còn dùng tới
		clearExpiredOrderSaved();
		String query = "SELECT o FROM Bill o WHERE o.status = false";
		List<Bill> list = entityManager.createQuery(query, Bill.class).getResultList();
		result = new ArrayList<>(list);

		return result;
	}

	public Promotion getDiscount(String orderID) {
		Bill order = getOne(orderID);
		if (order.getPromotion() == null) {
			return null;
		}
		return new Promotion_DAO().getOne(order.getPromotion().getPromotionID());
	}

	public int getQuantityOrderSaved() {
		int n = 0;
		n = Integer.valueOf(
				entityManager.createNamedQuery("Bill.getQuantityOrderSaved", Long.class).getSingleResult().toString());
		return n;
	}
}