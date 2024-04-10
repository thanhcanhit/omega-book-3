package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;

public interface StatementCashCount_BUS {
	 public Employee getEmployeeByID(String id) ;

	    public CashCountSheet getOne(String id) ;
	    public void createCashCountSheet(ArrayList<CashCount> cashCountList, ArrayList<Employee> employees, Date start);

	    public String generateID(Date date) ;
}
