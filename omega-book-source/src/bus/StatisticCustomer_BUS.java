package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface StatisticCustomer_BUS extends Remote {
	public int getNumberCus() throws RemoteException;

	public int sumCustomer() throws RemoteException;

	public int countMaleCustomers() throws RemoteException;

	public int[] filterCustomers() throws RemoteException;
	
	public int getAge(Date dateOfBirth) throws RemoteException;
}
