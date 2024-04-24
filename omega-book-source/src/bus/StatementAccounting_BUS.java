package bus;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

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

	    public List<Bill> getAllOrderInAcounting(Date start, Date end, String empID) throws RemoteException;

	    public double getSale(List<Bill> list) throws RemoteException;

	    public double getPayViaATM(List<Bill> list) throws RemoteException;

	    public double getTotal(List<CashCount> list) throws RemoteException;

	    public void generatePDF(AcountingVoucher acounting) throws RemoteException;
}
