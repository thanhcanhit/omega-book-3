/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import enums.PurchaseOrderStatus;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import utilities.AccessDatabase;

/**
 *
 * @author KienTran + QUuanfKhang đẹp tright
 */
public class PurchaseOrder_DAO implements DAOBase<PurchaseOrder> {

	EntityManager entityManager;

	public PurchaseOrder_DAO() {
//		super();
		entityManager = AccessDatabase.getInstance();
	}

	@Override
	public PurchaseOrder getOne(String id) {
//        PurchaseOrder result = null;
//
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM PurchaseOrder where PurchaseOrderID = ?");
//            st.setString(1, id);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//
//                Date orderDate = rs.getDate("orderDate");
//                Date receiveDate = rs.getDate("receiveDate");
//                int status = rs.getInt("status");
//                String note = rs.getString("note");
//                String supplierID = rs.getString("supplierID");
//                String employeeID = rs.getString("employeeID");
//
//                ArrayList<PurchaseOrderDetail> purchaseOrderDetail = new PurchaseOrderDetail_DAO().getAll(id);
//
//
//                result = new PurchaseOrder(id, orderDate, receiveDate, note, PurchaseOrderStatus.fromInt(status),new Supplier(supplierID), new Employee(employeeID),purchaseOrderDetail);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
		return entityManager.find(PurchaseOrder.class, id);
	}

	@Override
	/**
	 * Get all PurchaseOrder
	 */
	public ArrayList<PurchaseOrder> getAll() {
//        ArrayList<PurchaseOrder> result = new ArrayList<>();
//
//        try {
//            Statement st = ConnectDB.conn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM PurchaseOrder ");
//
//            while (rs.next()) {
//                String purchaseOrderID = rs.getString("purchaseOrderID");
//
//                Date orderDate = rs.getDate("orderDate");
//                Date receiveDate = rs.getDate("receiveDate");
//                int status = rs.getInt("status");
//                String note = rs.getString("note");
//                String supplierID = rs.getString("supplierID");
//                String employeeID = rs.getString("employeeID");
//
//                ArrayList<PurchaseOrderDetail> purchaseOrderDetail = new PurchaseOrderDetail_DAO().getAll(purchaseOrderID);
//
//
//                result.add(new PurchaseOrder(purchaseOrderID, orderDate, receiveDate, note, PurchaseOrderStatus.fromInt(status),new Supplier_DAO().getOne(supplierID), new Employee_DAO().getOne(employeeID),purchaseOrderDetail));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
		return (ArrayList<PurchaseOrder>) entityManager
				.createQuery("SELECT p FROM PurchaseOrder p", PurchaseOrder.class).getResultList();
	}

	@Override
	public String generateID() {
//        String result = "DN";
//        LocalDate time = LocalDate.now();
//        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("MMyyyy");
//
//        result += dateFormater.format(time);
//
//        String query = """
//                       select top 1 * from PurchaseOrder
//                       where purchaseOrderID like ?
//                       order by purchaseOrderID desc
//                       """;
//
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
//            st.setString(1, result + "%");
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                String lastID = rs.getString("purchaseOrderID");
//                String sNumber = lastID.substring(lastID.length() - 2);
//                int num = Integer.parseInt(sNumber) + 1;
//                result += String.format("%02d", num);
//            } else {
//                result += String.format("%02d", 0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;

		String result = "DN";
		LocalDate time = LocalDate.now();
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("MMyyyy");

		result += dateFormater.format(time);

		try {
			String hql = "FROM PurchaseOrder po WHERE po.purchaseOrderID LIKE :code ORDER BY po.purchaseOrderID DESC";
			Query query = entityManager.createQuery(hql);
			query.setParameter("code", result + "%");
			query.setMaxResults(1);
			PurchaseOrder resultPO = (PurchaseOrder) query.getSingleResult();

			if (resultPO != null) {
				String lastID = resultPO.getPurchaseOrderID();
				String sNumber = lastID.substring(lastID.length() - 2);
				int num = Integer.parseInt(sNumber) + 1;
				result += String.format("%02d", num);
			} else {
				result += String.format("%02d", 0);
			}
		} catch (NoResultException nre) {
			result += String.format("%02d", 0);
		}

		return result;
	}

	@Override
	public synchronized Boolean create(PurchaseOrder object) {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean update(String id, PurchaseOrder newObject) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

	}

	public synchronized Boolean updateStatus(String id, PurchaseOrderStatus status) {
		try {
			String hql = "UPDATE PurchaseOrder po SET po.status = :status WHERE po.purchaseOrderID = :id";
			
			Query query = entityManager.createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("id", id);

			if (status == PurchaseOrderStatus.SUCCESS) {
				PurchaseOrder purchaseOrder = getOne(id);
				for (PurchaseOrderDetail pod : purchaseOrder.getPurchaseOrderDetailList()) {
					new Product_DAO().updateInventory(pod.getProduct().getProductID(), pod.getQuantity());
				}
				entityManager.clear();
			}

			entityManager.getTransaction().begin();
			int n= query.executeUpdate();
			entityManager.getTransaction().commit();
			return n>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public int getNumberOfPurchaseOrderInMonth(int month, int year) {
//        int result=0;
//
//
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("select count(purchaseOrderID) as sl from [PurchaseOrder] where YEAR(receiveDate) = ? and Month(receiveDate) = ? ");
//            st.setInt(1, year);
//            st.setInt(2, month);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                int sl = rs.getInt("sl");
//                result=sl;
//
//                
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
		try {
			String hql = "SELECT COUNT(po.purchaseOrderID) FROM PurchaseOrder po WHERE YEAR(po.receiveDate) = :year AND MONTH(po.receiveDate) = :month";
			Query query = entityManager.createQuery(hql);
			query.setParameter("year", year);
			query.setParameter("month", month);
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} 
	}

}
