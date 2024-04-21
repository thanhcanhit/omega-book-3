/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import bus.CustomerManagement_BUS;
import dao.Customer_DAO;
import entity.Customer;

/**
 *
 * @author Hoàng Khang
 */
public class CustomerManagement_BUSImpl extends UnicastRemoteObject implements CustomerManagement_BUS{

    public CustomerManagement_BUSImpl() throws RemoteException {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 229822782660670951L;
	private Customer_DAO customer_DAO = new Customer_DAO();

    public ArrayList<Customer> getAllCustomer() throws RemoteException{
        ArrayList<Customer> customerList = new Customer_DAO().getAll();
        return customerList;
    }

    public Customer getOne(String customerID) throws RemoteException{
        return customer_DAO.getOne(customerID);
    }

    public ArrayList<Customer> getCustomersByRank() throws RemoteException{
        ArrayList<Customer> customerList = new Customer_DAO().getAll();
        return customerList;
    }

    public String generateID(Date date, boolean gender) throws RemoteException{
        //Khởi tạo mã Khách hàng KH
        String prefix = "KH";
        //4 Kí tự kế tiếp là năm sinh khách hàng
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);
        prefix += format;
        //Kí tự tiếp theo đại diện cho giới tính khách hàng
        if (gender) {
            prefix += "1";
        } else {
            prefix += "0";
        }
        //Tìm mã có tiền tố là code và xxxx lớn nhất
        String maxID = customer_DAO.getMaxSequence(prefix);
        if (maxID == null) {
            prefix += "0000";
        } else {
            String lastFourChars = maxID.substring(maxID.length() - 4);
            int num = Integer.parseInt(lastFourChars);
            num++;
            prefix += String.format("%04d", num);
        }
        return prefix;
    }

    public boolean createCustomer(String name, Date dateOfBirth, String numberPhone, String address, Boolean gender) throws Exception {
        Customer customer = new Customer(generateID(dateOfBirth, gender), name, gender, dateOfBirth, 0, numberPhone, address);
        return customer_DAO.create(customer);
    }

    public void updateCustomer(Customer customer, String customerID) throws Exception {
        customer_DAO.update(customerID, customer);
    }

    public Customer searchByPhoneNumber(String phoneNumber) throws RemoteException{
        ArrayList<Customer> list = customer_DAO.getAll();
        for (Customer customer : list) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public ArrayList<Customer> filterCustomer(String gender, String rank, String age, String phone) throws RemoteException{

        if (rank.equals("Chưa có")) {
            rank = "Không";
        }
        ArrayList<Customer> list = customer_DAO.getAll();
        if (!phone.equals("")) {
            ArrayList<Customer> tempList = new ArrayList<>();
            tempList.add(searchByPhoneNumber(phone));
            list = tempList;
        }
        ArrayList<Customer> listRemove = new ArrayList<>();
        if (gender.equals("Nam")) {
            for (Customer customer : list) {
                if (!customer.isGender()) {
                    listRemove.add(customer);
                }
            }
            list.removeAll(listRemove);
        }
        if (gender.equals("Nữ")) {
            for (Customer customer : list) {
                if (customer.isGender()) {
                    listRemove.add(customer);
                }
            }
            list.removeAll(listRemove);
        }
        if (!rank.equals("Tất cả")) {
            for (Customer customer : list) {
                if (!customer.getRank().equals(rank)) {
                    listRemove.add(customer);
                }
            }
            list.removeAll(listRemove);
        }

        if (!age.equals("Tất cả")) {
            if (age.equals("Dưới 18 tuổi")) {
                for (Customer customer : list) {
                    if (getAge(customer.getDateOfBirth()) > 18) {
                        listRemove.add(customer);
                    }
                }
                list.removeAll(listRemove);
            } else if (age.equals("Trên 40 tuổi")) {
                for (Customer customer : list) {
                    if (getAge(customer.getDateOfBirth()) < 40) {
                        listRemove.add(customer);
                    }
                }
                list.removeAll(listRemove);
            } else if (age.equals("Từ 18 đến 40 tuổi")) {
                for (Customer customer : list) {
                    if (getAge(customer.getDateOfBirth()) > 40 || getAge(customer.getDateOfBirth()) < 18) {
                        listRemove.add(customer);
                    }
                }
                list.removeAll(listRemove);
            }

        }

        return list;
    }

    /**
     * Phương thức tính tuổi
     *
     * @param dateOfBirth Ngày sinh của đối tượng
     * @return Số tuổi của đối tượng
     */
    public int getAge(Date dateOfBirth) throws RemoteException{
        long diffInMillies = Math.abs(new Date().getTime() - dateOfBirth.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) (diff / 365);
    }

}
