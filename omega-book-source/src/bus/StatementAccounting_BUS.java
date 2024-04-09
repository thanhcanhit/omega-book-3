package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;
import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;
import entity.Order;

public interface StatementAccounting_BUS {
	 public AcountingVoucher getAcountingByID(String id) ;

	    public AcountingVoucher getLastAcounting() ;
	    public String generateID(Date date) ;

	    public void createAcountingVoucher(CashCountSheet cashCountSheet, Date end) ;

	    public Employee getEmployeeByID(String id);

	    public ArrayList<Order> getAllOrderInAcounting(Date start, Date end);

	    public double getSale(ArrayList<Order> list) ;

	    public double getPayViaATM(ArrayList<Order> list);

	    public double getTotal(ArrayList<CashCount> list) ;

	    public void generatePDF(AcountingVoucher acounting);
}
