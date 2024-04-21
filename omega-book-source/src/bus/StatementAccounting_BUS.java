package bus;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;
import entity.Bill;
import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;

public interface StatementAccounting_BUS {
	 public AcountingVoucher getAcountingByID(String id) throws RemoteException;

	    public AcountingVoucher getLastAcounting() throws RemoteException;
	    public String generateID(Date date) throws RemoteException;

	    public void createAcountingVoucher(CashCountSheet cashCountSheet, Date end) throws RemoteException;

	    public Employee getEmployeeByID(String id) throws RemoteException;

	    public ArrayList<Bill> getAllOrderInAcounting(Date start, Date end) throws RemoteException;

	    public double getSale(ArrayList<Bill> list) throws RemoteException;

	    public double getPayViaATM(ArrayList<Bill> list) throws RemoteException;

	    public double getTotal(ArrayList<CashCount> list) throws RemoteException;

	    public void generatePDF(AcountingVoucher acounting) throws RemoteException;
}
