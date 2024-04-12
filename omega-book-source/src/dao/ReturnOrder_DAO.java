package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectDB;
import entity.Employee;
import entity.Order;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderStatus;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class ReturnOrder_DAO implements DAOBase<ReturnOrder>{
	EntityManager em;
	public ReturnOrder_DAO() {
		em = AccessDatabase.getEntityManager();
	}

	//    public static String getMaxSequence(String prefix) {
	//        try {
	//        prefix += "%";
	//        String sql = "  SELECT TOP 1  * FROM ReturnOrder WHERE returnOrderID LIKE '"+prefix+"' ORDER BY returnOrderID DESC;";
	//        PreparedStatement st = ConnectDB.conn.prepareStatement(sql);
	//        ResultSet rs = st.executeQuery();
	//        if (rs.next()) {
	//            String returnOrderID = rs.getString("returnOrderID");
	//            return returnOrderID;
	//        }
	//    } catch (SQLException e) {
	//        e.printStackTrace();
	//    }
	//    return null;
	//    }

	@Override
	public ReturnOrder getOne(String id) {
		return em.createNamedQuery("ReturnOrder.findByReturnOrderID", ReturnOrder.class)
				.setParameter("returnOrderID", id).getSingleResult();
	}

	@Override
	public ArrayList<ReturnOrder> getAll() {
		return (ArrayList<ReturnOrder>) em.createNamedQuery("ReturnOrder.findAll", ReturnOrder.class).getResultList();
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String generateID(Date returnDate) {
		String result = "HDT";
		String query = """
				select top 1 * from ReturnOrder
				where returnOrderID like ?
				order by supplierID desc
				""";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("ddMMyyyy");
		String formatDate = simpleDateFormat.format(returnDate);  
		//8 kí tự tiếp theo là ngày tháng năm lập đơn đổi trả
		result += formatDate;
		try {
			TypedQuery<ReturnOrder> query1 = em.createQuery(query, ReturnOrder.class);
			query1.setParameter(1, result + "%");
			List<ReturnOrder> rs = query1.getResultList();
			if (rs.size() > 0) {

				String lastID = rs.get(0).getReturnOrderID();
				String sNumber = lastID.substring(lastID.length() - 2);
				int num = Integer.parseInt(sNumber) + 1;
				result += String.format("%04d", num);
			} else {
				result += String.format("%04d", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean create(ReturnOrder returnOrder) {
		int n = 0;
		try {
			em.getTransaction().begin();
			em.persist(returnOrder);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	@Override
	public Boolean update(String id, ReturnOrder returnOrder) {
		int n = 0;
		try {
			em.getTransaction().begin();
			em.merge(returnOrder);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public Order getOrder(String orderID) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID) {
		return new ReturnOrderDetail_DAO().getAllForOrderReturnID(returnOrderID);
	}

	public ArrayList<ReturnOrder> findById(String returnOrderID) {
		String query = """
				SELECT ro FROM ReturnOrder ro
				where ro.returnOrderID LIKE :id
				""";

		return (ArrayList<ReturnOrder>) em.createQuery(query, ReturnOrder.class).setParameter("id", "%" + returnOrderID + "%").getResultList();
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

	//    public ArrayList<ReturnOrder> filter(int type, int status) {
	//        ArrayList<ReturnOrder> result = new ArrayList<>();
	////        Index tự động tăng phụ thuộc vào số lượng biến số có
	//        int index = 1;
	//        String query = "select * from ReturnOrder WHERE returnOrderID like '%'";
	////        Xét loại đơn đổi trả
	//        if (type != 0)
	//            query += " and type = ?";
	////            Xét trạng thái 
	//        if (status != 0)
	//            query += " and status = ?";
	//        try {
	//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
	//            if(type == 1)
	//                st.setInt(index++, 0);
	//            else if(type == 2)
	//                st.setInt(index++, 1);
	//            if(status == 1)
	//                st.setInt(index++, 0);
	//            else if(status == 2)
	//                st.setInt(index++, 1);
	//            else if(status == 3)
	//                st.setInt(index++, 2);
	//            ResultSet rs = st.executeQuery();
	//            while (rs.next()) {
	//                if (rs != null) {
	//                    result.add(getReturnOrderData(rs));
	//                }
	//            }
	//        } catch (SQLException e) {
	//            e.printStackTrace();
	//        } catch (Exception ex) {
	//            ex.printStackTrace();
	//        }
	//        return result;
	//    }
	public int getNumberOfReturnOrderInMonth(int month, int year){
		//    	("select count(returnOrderID) as sl from [ReturnOrder] where YEAR(orderDate) = ? and Month(orderDate) = ? ");
		String query = "select count(returnOrderID) as sl from [ReturnOrder] ro where YEAR(ro.orderDate) = :year and Month(eo.orderDate) = :month ";
		return (int) em.createNativeQuery(query).setParameter("year", year).setParameter("month", month).getSingleResult();
	}
	public double getTotalReturnOrderInMonth(int month, int year) {
		double result = 0;

		try {
			PreparedStatement st = ConnectDB.conn.prepareStatement("select sum(refund) as total from [ReturnOrder] join [Order] on [Order].orderID=ReturnOrder.orderID where YEAR(orderAt) = ? and Month(orderAt) = ? and [ReturnOrder].status=1");
			st.setInt(1, year);
			st.setInt(2, month);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int total = rs.getInt("total");
				result = total;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ReturnOrder getOneForOrderID(String orderID) {
		ReturnOrder returnOrder = null;
		try {
			PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM ReturnOrder WHERE orderID = ?");
			st.setString(1, orderID);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {                
				String returnOrderID = rs.getString("returnOrderID");
				boolean type = rs.getBoolean("type");
				int status = rs.getInt("status");
				Date orderDate = rs.getDate("orderDate");
				String employeeID = rs.getString("employeeID");
				double refund = rs.getDouble("refund");
				String reason = rs.getString("reason");
				Order order = new Order_DAO().getOne(orderID);
				Employee employee = new Employee(employeeID);
				ArrayList<ReturnOrderDetail> listDetail = getAllReturnOrderDetail(returnOrderID);
				returnOrder = new ReturnOrder(orderDate, ReturnOrderStatus.fromInt(status), returnOrderID, employee, order, type, refund, listDetail, reason);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnOrder;
	}

}
