/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.ConnectDB;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.OrderDetail;
import entity.Promotion;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilities.AccessDatabase;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author KienTran
 */
public class Order_DAO implements DAOBase<Order> {

	EntityManager entityManager;
	public Order_DAO() {
		entityManager = AccessDatabase.getEntityManager();
	}
    @Override
    public Order getOne(String id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public ArrayList<Order> getAll() {
    	List<Order> list= entityManager.createNamedQuery("Order.getAll", Order.class).getResultList();
    	ArrayList<Order> result = new ArrayList<>(list);
        return  result;
    }

    @Override
    public String generateID() {
    	String result = "HD";
        LocalDate time = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        result += dateFormatter.format(time);

        try {
            entityManager.getTransaction().begin();

            String lastID = (String) entityManager.createNamedQuery("Order.generateID", Order.class).setParameter("prefix", result + "%")
                    .setMaxResults(1).getSingleResult().toString();
            if (lastID != null) {
                String sNumber = lastID.substring(lastID.length() - 2);
                int num = Integer.parseInt(sNumber) + 1;
                result += String.format("%04d", num);
            } else {
                result += String.format("%04d", 0);
            }

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    @Override
    public Boolean create(Order object) {

    	entityManager.getTransaction().begin();
    	entityManager.persist(object);
    	entityManager.getTransaction().commit();
    	return entityManager.find(Order.class, object.getOrderID()) != null;
    }

    @Override
    public Boolean update(String id, Order newObject) {
    	int n = 0;
    	n = entityManager.createNamedQuery("Order.update", Order.class)
    	.setParameter("payment", newObject.isPayment())
    	.setParameter("status", newObject.isStatus())
    	.setParameter("orderAt", newObject.getOrderAt())
    	.setParameter("employeeID", newObject.getEmployee().getEmployeeID())
    	.setParameter("customerID", newObject.getCustomer().getCustomerID())
    	.setParameter("promotionID", newObject.getPromotion().getPromotionID())
    	.setParameter("totalDue", newObject.getTotalDue())
    	.setParameter("subTotal", newObject.getSubTotal())
    	.setParameter("moneyGiven", newObject.getMoneyGiven())
    	.setParameter("orderID", id).executeUpdate();
    	return n > 0;
    }

    @Override
    public Boolean delete(String id) {
    	entityManager.remove(entityManager.find(Order.class, id));
    	return entityManager.find(Order.class, id) == null;
    }

    public int getLength() {
        return (int) entityManager.createNamedQuery("Order.getLength").getSingleResult();
    }

    public ArrayList<Order> getPage(int page) {
    	List<Order> list = new ArrayList<>();
       list= entityManager.createNamedQuery("Order.getAll",Order.class).setFirstResult(page).setMaxResults(50).getResultList();
       ArrayList<Order> result = new ArrayList<>(list);
       return result;
    }

    /**
     * @param acountingVoucherID Mã phiếu kết toán
     * @return ArrayList<Order> Danh sách hóa đơn đã được kết toán trong
     * phiếu kết toán
     * @author Hoàng Khang
     */
    public ArrayList<Order> getAllOrderInAcountingVoucher(String accountingVoucherID) {
        ArrayList<Order> result = new ArrayList<>();

        try {
            String hql = "SELECT o FROM Order o " +
                         "WHERE o.acountingVoucherID = :accountingVoucherID";
         

            List<Order> orders = entityManager.createQuery(hql,Order.class)
                    .setParameter("accountingVoucherID", accountingVoucherID).getResultList();
            result.addAll(orders);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    /**
     * Phương thức thực hiện việc cập nhật mã phiếu kết toán cho một
     * hóa đơn
     *
     * @param orderID Mã hóa đơn
     * @param acountingVoucherID Mã phiếu kết toán
     * @author Hoàng Khang
     */
    public boolean updateOrderAcountingVoucher(String orderID, String acountingVoucherID) {
    	

        try {
           entityManager.getTransaction().begin();

            // Sử dụng câu truy vấn HQL để cập nhật trường acountingVoucherID của Order
            String hql = "UPDATE Order o SET o.acountingVoucherID = :acountingVoucherID " +
                         "WHERE o.orderID = :orderID";
            int updatedEntities = entityManager.createQuery(hql)
                    .setParameter("acountingVoucherID", acountingVoucherID)
                    .setParameter("orderID", orderID)
                    .executeUpdate();

            entityManager.getTransaction().commit();
            return updatedEntities > 0;

        } catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
            	entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Phương thức thực hiện việc lấy ra các hoá đơn theo mã hoá đơn
     *
     * @param orderID Mã hóa đơn
     * @author Như Tâm
     */
    public ArrayList<Order> findById(String orderID) {
    		List<Order> list = new ArrayList<>();

    	    try {

    	        String hql = "SELECT o FROM Order o WHERE o.orderID LIKE :orderIDPattern";

    	        list = entityManager.createQuery(hql,Order.class)
    	    	        .setParameter("orderIDPattern", orderID + "%").getResultList();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        if (entityManager != null) {
    	            entityManager.close();
    	        }
    	    }

    	    ArrayList<Order> result = new ArrayList<>(list);
    	    return result;
    }

    private Order getOrderData(ResultSet rs) throws Exception {
        Order result = null;

        //Lấy thông tin tổng quát của lớp order
        String orderID = rs.getString("orderID");
        String employeeID = rs.getString("employeeID");
        String customerID = rs.getString("customerID");
        Date orderAt = rs.getDate("orderAt");
        boolean status = rs.getBoolean("status");
        boolean payment = rs.getBoolean("payment");
        double subTotal = rs.getDouble("subTotal");
        double totalDue = rs.getDouble("totalDue");

        Double moneyGiven = rs.getDouble("moneyGiven");
        ArrayList<OrderDetail> orderDetailList = new OrderDetail_DAO().getAll(orderID);
        Employee employee = new Employee(employeeID);
        Customer customer = new Customer(customerID);
        result = new Order(orderID, orderAt, status, subTotal, totalDue, payment, employee, customer, orderDetailList, moneyGiven);
        return result;
    }

    public double[] getToTalInMonth(int month, int year) {
        double[] result = new double[31]; // Mảng kết quả, tối đa 31 ngày trong một tháng

        try {
            Arrays.fill(result, 0);

             String hql = "SELECT DAY(o.orderAt) AS dayOfMonth, SUM(o.totalDue) " +
                         "FROM Order o " +
                         "WHERE FUNCTION('YEAR', o.orderAt) = :year " +
                         "AND FUNCTION('MONTH', o.orderAt) = :month " +
                         "AND o.status = 1 " +
                         "GROUP BY DAY(o.orderAt)";
           
            List<Object[]> resultList = entityManager.createQuery(hql,Object[].class)
                    .setParameter("year", year)
                    .setParameter("month", month).getResultList();
            for (Object[] row : resultList) {
                int dayOfMonth = (int) row[0];
                double totalDue = (double) row[1];
                result[dayOfMonth - 1] = totalDue;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    public int getNumberOfOrderInMonth(int month, int year) {
    	
        int result = 0;

        try {

            String hql = "SELECT COUNT(o.orderID) FROM Order o " +
                         "WHERE FUNCTION('YEAR', o.orderAt) = :year " +
                         "AND FUNCTION('MONTH', o.orderAt) = :month " +
                         "AND o.status = 1";

            List<Integer> resultList = entityManager.createQuery(hql,Integer.class)
            		.setParameter("year", year)
            		.setParameter("month", month).getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                result = resultList.get(0).intValue(); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    public double getTotalInMonth(int month, int year) {
    	double result = 0;

        try {
            List<Double> resultList = entityManager.createNamedQuery("Order.getTotalInMonth",Double.class)
            		.setParameter("year", year)
                    .setParameter("month", month).getResultList();
            if (resultList != null && !resultList.isEmpty() && resultList.get(0) != null) {
                result = resultList.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return result;
    }

    public void clearExpiredOrderSaved() {
        try {
        	OrderDetail_DAO od_DAO = new OrderDetail_DAO();
           List<Order> resultSet=entityManager.createNamedQuery("Order.clearExpiredOrderSaved",Order.class).getResultList();
           resultSet.forEach(x->{
        	   od_DAO.delete(x.getOrderID());
           });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getNotCompleteOrder() {
        ArrayList<Order> result = new ArrayList<>();
//        Xóa các hóa đơn lưu tạm quá 24 giờ  không còn dùng tới
        clearExpiredOrderSaved();
        String query = "SELECT * FROM [dbo].[Order] "
                       + "where status = 0";
        try {

            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    result.add(getOrderData(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public Promotion getDiscount(String orderID) {
        Order order = getOne(orderID);
        if (order.getPromotion() == null) {
            return null;
        }
        return new Promotion_DAO().getOne(order.getPromotion().getPromotionID());
    }

    public int getQuantityOrderSaved() {
        return (int) entityManager.createNamedQuery("Order.getQuantityOrderSaved").getSingleResult();
    }
}
