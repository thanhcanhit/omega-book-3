package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Customer;

public interface CustomerManagement_BUS extends Remote{
	 public ArrayList<Customer> getAllCustomer() throws RemoteException;
	 public String generateID(Date date, boolean gender) throws RemoteException;
	 public ArrayList<Customer> getCustomersByRank()throws RemoteException;
	 public Customer getOne(String customerID)throws RemoteException;
	 public boolean createCustomer(String name, Date dateOfBirth, String numberPhone, String address, Boolean gender) throws Exception;
	 public void updateCustomer(Customer customer, String customerID) throws Exception;
	 public Customer searchByPhoneNumber(String phoneNumber) throws RemoteException;
	 public ArrayList<Customer> filterCustomer(String gender, String rank, String age, String phone) throws RemoteException;
	 public int getAge(Date dateOfBirth) throws RemoteException;
}
