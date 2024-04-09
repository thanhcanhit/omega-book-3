package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Customer;

public interface CustomerManagement_BUS {
	 public ArrayList<Customer> getAllCustomer();
	 public String generateID(Date date, boolean gender);
	 public ArrayList<Customer> getCustomersByRank();
	 public Customer getOne(String customerID);
	 public boolean createCustomer(String name, Date dateOfBirth, String numberPhone, String address, Boolean gender) throws Exception;
	 public void updateCustomer(Customer customer, String customerID) throws Exception;
	 public Customer searchByPhoneNumber(String phoneNumber);
	 public ArrayList<Customer> filterCustomer(String gender, String rank, String age, String phone);
	 public int getAge(Date dateOfBirth);
}
