package dao;

import java.text.SimpleDateFormat;
import java.util.*;
import entity.Bill;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderStatus;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class ReturnOrder_DAO implements DAOBase<ReturnOrder>{
	EntityManager em;
	ReturnOrderDetail_DAO detail_dao = new ReturnOrderDetail_DAO();
	public ReturnOrder_DAO() {
		em = AccessDatabase.getInstance();
	}

	@Override
	public ReturnOrder getOne(String id) {
		return em.createNamedQuery("ReturnOrder.findByReturnOrderID", ReturnOrder.class)
				.setParameter("returnOrderID", id).getSingleResult();
	}

	@Override
	public ArrayList<ReturnOrder> getAll() {
		return (ArrayList<ReturnOrder>) em.createNamedQuery("ReturnOrder.findAll", ReturnOrder.class).getResultList();
	}
	
	public int getNumberOfReturnOrderInMonth(int month, int year){
		String query = "select count(returnOrderID) as sl from [dbo].[ReturnOrder] ro where YEAR(ro.orderDate) = :year and Month(ro.orderDate) = :month ";
		int result = em.createNativeQuery(query).setParameter("year", year).setParameter("month", month).getFirstResult();
		return result;
	}
	public double getTotalReturnOrderInMonth(int month, int year) {
		double result;
		String query = "select sum(ro.refund) as total from ReturnOrder ro where YEAR(ro.order.orderAt) = :year and Month(ro.order.orderAt) = :month and ro.status = 1";
		if(em.createQuery(query,Double.class).setParameter("year", year).setParameter("month", month).getSingleResult()!=null)
			result = em.createQuery(query,Double.class).setParameter("year", year).setParameter("month", month).getSingleResult().doubleValue();
		else
			result=0;
		return result;
	}

	public ArrayList<ReturnOrder> findById(String returnOrderID) {
		String query = "SELECT ro FROM ReturnOrder ro where ro.returnOrderID LIKE :id";
		return (ArrayList<ReturnOrder>) em.createQuery(query, ReturnOrder.class).setParameter("id", "%" + returnOrderID + "%").getResultList();
	}

	public ReturnOrder getOneForOrderID(String orderID) {
		String query = "SELECT ro FROM ReturnOrder ro WHERE ro.order.orderID = :id";
		ReturnOrder rs = null;
		try {
			rs = em.createNamedQuery(query, ReturnOrder.class).setParameter("id", orderID).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public String generateID(Date returnDate) {
		String result = "HDT";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("ddMMyyyy");
		String formatDate = simpleDateFormat.format(returnDate);  
		result += formatDate;
		String query = "select ro from ReturnOrder ro where ro.returnOrderID like :id order by ro.returnOrderID desc";
		ReturnOrder rs = null;
		try {
			rs = em.createNamedQuery(query, ReturnOrder.class).setParameter("id", result + "%").getSingleResult();
		} catch (Exception e) {
			rs = null;
		}
		if (rs != null) {
			String lastID = rs.getReturnOrderID();
			String sNumber = lastID.substring(lastID.length() - 2);
			int num = Integer.parseInt(sNumber) + 1;
			result += String.format("%04d", num);
		} else {
			result += String.format("%04d", 0);
		}
		return result;
	}

	@Override
	public synchronized Boolean create(ReturnOrder returnOrder) {
		int n = 0;
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		try {
			em.getTransaction().begin();
			em.persist(returnOrder);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}
		return n > 0;
	}

	@Override
	public synchronized Boolean update(String id, ReturnOrder returnOrder) {
		int n = 0;
		try {
			em.getTransaction().begin();
			em.merge(returnOrder);
			em.getTransaction().commit();
//			if (returnOrder.getStatus().getValue() == 1 && returnOrder.isType() == false) {
//				detail_dao.update(returnOrder.getListDetail());
//			}
//			else if(returnOrder.getStatus().getValue() == 1 && returnOrder.isType() == true) {
//				returnOrder.setRefund();
//			}
			
			n = 1;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return n > 0;
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public synchronized Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public Bill getOrder(String orderID) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID) {
		return new ReturnOrderDetail_DAO().getAllForOrderReturnID(returnOrderID);
	}


	//    private ReturnOrder getReturnOrderData(ResultSet rs) throws SQLException {
	//        ReturnOrder result = null;
	//        try {
	//            //Lấy thông tin tổng quát của lớp ReturnOrder
	//            String returnOderID = rs.getString("returnOrderID");
	//            boolean type = rs.getBoolean("type");
	//            int status = rs.getInt("status");
	//            Date orderDate = rs.getDate("orderDate");
	//            String orderID = rs.getString("orderID");
	//            String employeeID = rs.getString("employeeID");
	//            double refund = rs.getDouble("refund");
	//            String reason = rs.getString("reason");
	//            Order order = new Order(orderID);
	//            Employee employee = new Employee(employeeID);
	//            ArrayList<ReturnOrderDetail> listDetail = getAllReturnOrderDetail(returnOderID);
	//            result = new ReturnOrder(orderDate, ReturnOrderStatus.fromInt(status), returnOderID, employee, order, type, refund, listDetail, reason);
	//        } catch (Exception ex) {
	//            ex.printStackTrace();
	//        }
	//        return result;
	//    }

	    public ArrayList<ReturnOrder> filter(int type, int status) {
	        ArrayList<ReturnOrder> result = new ArrayList<>();
	        String query = "select ro from ReturnOrder ro WHERE ro.returnOrderID like :id ";
	        if (type != 0)
	            query += " and ro.type = :type";
	        if (status != 0)
	            query += " and ro.status = :status";
	        try {
	        	TypedQuery<ReturnOrder> st = (TypedQuery<ReturnOrder>) em.createQuery(query, ReturnOrder.class);
	        	st.setParameter("id", "%");
	        	if(type != 0) {
	        		if(type == 1)
	        			st.setParameter("type", false);
	        		else
	        			st.setParameter("type", true);
	        	}
	        	if(status != 0) {
	        		if(status == 1)
	        			st.setParameter("status", ReturnOrderStatus.PENDING);
	        		else if(status == 2)
	        			st.setParameter("status", ReturnOrderStatus.SUCCESS);
	        		else 
	        			st.setParameter("status", ReturnOrderStatus.CANCEL);
	        	}
	        	st.getResultStream().forEach(returnOrder-> result.add((ReturnOrder) returnOrder));
	        	//result = (ArrayList<ReturnOrder>) st.getResultList();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return result;
	    }


}
