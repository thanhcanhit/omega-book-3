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
import utilities.AccessDatabase;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    public ArrayList<Order> getAllOrderInAcountingVoucher(String acountingVoucherID) {
        ArrayList<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Order] WHERE acountingVoucherID = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, acountingVoucherID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String orderID = resultSet.getString("orderID");
                boolean status = resultSet.getBoolean("status");
                Date orderAt = resultSet.getDate("orderAt");
                boolean payment = resultSet.getBoolean("payment");
                String employeeID = resultSet.getString("employeeID");
                String customerID = resultSet.getString("customerID");
                String promotionID = resultSet.getString("promotionID");
                Double totalDue = resultSet.getDouble("totalDue");
                Double subTotal = resultSet.getDouble("subTotal");
                Double moneyGiven = resultSet.getDouble("moneyGiven");

                Order order = null;
                if (promotionID != null) {
                    order = new Order(orderID, orderAt, payment, status, new Promotion_DAO().getOne(promotionID), new Employee_DAO().getOne(employeeID), new Customer_DAO().getOne(customerID), new OrderDetail_DAO().getAll(orderID), subTotal, totalDue, moneyGiven);
                } else {
                    order = new Order(orderID, orderAt, payment, status, new Employee_DAO().getOne(employeeID), new Customer_DAO().getOne(customerID), new OrderDetail_DAO().getAll(orderID), subTotal, totalDue, moneyGiven);
                }

                result.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            String sql = "UPDATE [Order] SET acountingVoucherID = ? WHERE orderID = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, acountingVoucherID);
            preparedStatement.setString(2, orderID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
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
    public ArrayList<Order> findById(String orderID) {
        ArrayList<Order> result = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Order] "
                        + "where orderID LIKE ? ";
        try {

            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            st.setString(1, orderID + "%");
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
        double[] result = new double[31];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select Day(orderAt) as day, sum(totalDue) as total from [Order] where YEAR(orderAt) = ? and Month(orderAt) = ? and status = 1 group by Day(orderAt)");
            st.setInt(1, year);
            st.setInt(2, month);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int day = rs.getInt("day");
                double total = rs.getDouble("total");

                result[day - 1] = total;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int getNumberOfOrderInMonth(int month, int year) {
        int result = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select count(orderID) as sl from [Order] where YEAR(orderAt) = ? and Month(orderAt) = ? and status=1 ");
            st.setInt(1, year);
            st.setInt(2, month);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sl = rs.getInt("sl");
                result = sl;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public double getTotalInMonth(int month, int year) {
        double result = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select sum(totalDue) as total from [Order] where YEAR(orderAt) = ? and Month(orderAt) = ? and status=1");
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

    public void clearExpiredOrderSaved() {
        ArrayList<Order> result = new ArrayList<>();
        try {
            Statement statement = ConnectDB.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from [Order] where status = 0 and  DATEDIFF(dd, orderAt, GETDATE()) > 1");

            while (resultSet.next()) {
                String orderID = resultSet.getString("orderID");
                new OrderDetail_DAO().delete(orderID);
                this.delete(orderID);
            }
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
