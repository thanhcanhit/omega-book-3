package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.Customer;

public interface CustomerManagement_BUS extends Remote{
	 public ArrayList<Customer> getAllCustomer() throws IOException;
	 public String generateID(Date date, boolean gender) throws IOException;
	 public ArrayList<Customer> getCustomersByRank()throws IOException;
	 public Customer getOne(String customerID)throws IOException;
	 public boolean createCustomer(String name, Date dateOfBirth, String numberPhone, String address, Boolean gender) throws Exception;
	 public void updateCustomer(Customer customer, String customerID) throws Exception;
	 public Customer searchByPhoneNumber(String phoneNumber) throws IOException;
	 public ArrayList<Customer> filterCustomer(String gender, String rank, String age, String phone) throws IOException;
	 public int getAge(Date dateOfBirth) throws IOException;
}
