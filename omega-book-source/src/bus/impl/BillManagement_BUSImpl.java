/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import bus.BillManagement_BUS;
import dao.Bill_DAO;
import dao.Customer_DAO;
import dao.Employee_DAO;
import dao.OrderDetail_DAO;
import dao.Product_DAO;
import dao.Promotion_DAO;
import entity.Bill;
import entity.Customer;
import entity.Employee;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;

/**
 *
 * @author KienTran
 */
public class BillManagement_BUSImpl extends UnicastRemoteObject implements BillManagement_BUS{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4955945117204405086L;

	public BillManagement_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private final Bill_DAO orderDAO = new Bill_DAO();
    private final OrderDetail_DAO orderDetailDAO = new OrderDetail_DAO();
    private final Customer_DAO customerDAO = new Customer_DAO();
    private final Product_DAO productDAO = new Product_DAO();
    private final Employee_DAO employeeDAO = new Employee_DAO();
    private final Promotion_DAO promotionDAO = new Promotion_DAO();

    public Promotion getPromotion(String promotionID) throws RemoteException{
        return promotionDAO.getOne(promotionID);
    }

    public ArrayList<Bill> getDataOfPage(int page) throws RemoteException {
        ArrayList<Bill> list = orderDAO.getPage(page);
        
        return list;
    }
    public ArrayList<Bill> getAll() throws RemoteException{
        return orderDAO.getAll();
    }
    public Employee getEmployee(String emplpyeeID) throws RemoteException{
        return employeeDAO.getOne(emplpyeeID);
    }

    public Customer getCustomer(String customerID)throws RemoteException {
        return customerDAO.getOne(customerID);

    }

    public Product getProduct(String productID) throws RemoteException{
        return productDAO.getOne(productID);
    }

    public Bill getOrder(String ID) throws Exception{
        Bill order = orderDAO.getOne(ID);
        Customer customer = getCustomer(order.getCustomer().getCustomerID());
        order.setCustomer(customer);
        Employee employee = getEmployee(order.getEmployee().getEmployeeID());
        order.setEmployee(employee);
        if(order.getPromotion()!=null){
             Promotion promotion = getPromotion(order.getPromotion().getPromotionID());
             order.setPromotion(promotion);
        }
       
        return order;
    }

    public ArrayList<OrderDetail> getOrderDetailList(String orderID) throws RemoteException{
        return orderDetailDAO.getAll(orderID);
    }

    public int getLastPage() throws RemoteException{

        int result = (int) Math.ceil(Double.valueOf(orderDAO.getLength()) / 50);
        return result;
    }

    public ArrayList<Bill> orderListWithFilter(String orderID, String customerID, String phoneNumber, String priceFrom, String priceTo, Date orderFrom, Date orderTo)throws RemoteException {
        ArrayList<Bill> list = orderDAO.getAll();
        //for(Order order:list){
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).isStatus()==false)
            {
                list.remove(i);
            }
        }
        ArrayList<Bill> xoa = new ArrayList<>();
       
        if (phoneNumber.trim().length() > 0) {
            for (Bill order : list) {
                Bill od = orderDAO.getOne(order.getOrderID());
                Customer ct = customerDAO.getOne(od.getCustomer().getCustomerID());
                if (!ct.getPhoneNumber().equals(phoneNumber)) {
                    xoa.add(order);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (orderID.trim().length() > 0) {
            for (Bill order : list) {
                if (!order.getOrderID().equals(orderID)) {
                    xoa.add(order);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (customerID.trim().length() > 0) {

            for (Bill order : list) {
                if (!order.getCustomer().getCustomerID().equals(customerID)) {
                    xoa.add(order);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (priceFrom.trim().length() > 0) {
            for (Bill order : list) {
                if (order.getSubTotal() < Double.parseDouble(priceFrom)) {
                    xoa.add(order);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (priceTo.trim().length() > 0) {
            for (Bill order : list) {
                if (order.getSubTotal() > Double.parseDouble(priceTo)) {
                    xoa.add(order);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();

        for (Bill order : list) {
            if (order.getOrderAt().before(orderFrom)) {
                xoa.add(order);
            }
        }
        list.removeAll(xoa);

        xoa.clear();

        for (Bill order : list) {
            if (order.getOrderAt().after(orderTo) ) {
                xoa.add(order);
            }
        }
        xoa.clear();
        list.removeAll(xoa);

        return list;
    }
}
