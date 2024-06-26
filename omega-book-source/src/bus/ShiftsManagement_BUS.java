package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Account;
import entity.Employee;
import entity.Shift;

public interface ShiftsManagement_BUS extends Remote{
	 public Shift getOne(String id) throws RemoteException;

	    public ArrayList<Shift> getAll() throws RemoteException;

	    public boolean createShifts(Shift shift) throws RemoteException;
	    public String renderID() throws RemoteException;

	    public ArrayList<Shift> getShiftsByDate(Date date) throws RemoteException;

	    public ArrayList<Shift> filter(String emloyeeID, String role, Date date) throws RemoteException;
	    public void updateShift(Shift shift) throws RemoteException;
	    public Shift getAccount(String employeeID) throws RemoteException;
	    public Account findAccount(Employee employee) throws RemoteException;
//	    Lấy lần đăng nhập đầu tiên trong ngày
	    public Shift getFirstLogin(Employee employee) throws RemoteException;
}
