package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;

public interface StatementCashCount_BUS extends Remote{
	 public Employee getEmployeeByID(String id) throws IOException;

	    public CashCountSheet getOne(String id) throws IOException;
	    public void createCashCountSheet(ArrayList<CashCount> cashCountList, ArrayList<Employee> employees, Date start) throws IOException;

	    public String generateID(Date date) throws IOException;
}
