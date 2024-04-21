package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;

public interface StatementCashCount_BUS extends Remote{
	 public Employee getEmployeeByID(String id) throws RemoteException;

	    public CashCountSheet getOne(String id) throws RemoteException;
	    public void createCashCountSheet(ArrayList<CashCount> cashCountList, ArrayList<Employee> employees, Date start) throws RemoteException;

	    public String generateID(Date date) throws RemoteException;
}
