package bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;
import entity.Bill;
import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;

public interface StatementAccounting_BUS {
	 public AcountingVoucher getAcountingByID(String id) throws IOException;

	    public AcountingVoucher getLastAcounting() throws IOException;
	    public String generateID(Date date) throws IOException;

	    public void createAcountingVoucher(CashCountSheet cashCountSheet, Date end) throws IOException;

	    public Employee getEmployeeByID(String id) throws IOException;

	    public ArrayList<Bill> getAllOrderInAcounting(Date start, Date end) throws IOException;

	    public double getSale(ArrayList<Bill> list) throws IOException;

	    public double getPayViaATM(ArrayList<Bill> list) throws IOException;

	    public double getTotal(ArrayList<CashCount> list) throws IOException;

	    public void generatePDF(AcountingVoucher acounting) throws IOException;
}
