package bus;

import java.io.IOException;
import java.rmi.Remote;

import enums.Type;

public interface StatisticSales_BUS extends Remote{
	 public int getTotalNumberOrder(int month, int year) throws IOException;
	    public int getTotalNumberPurchaseOrder(int month, int year) throws IOException;
	    public int getTotalNumberReturnOrder(int month, int year) throws IOException;
	    public double getTotalInMonth(int month, int year) throws IOException;
	    public double getTargetInMonth(int month, int year) throws IOException;
	    

	    public double[] getTotalPerDay(int month, int year)throws IOException;
	    public int getQuantityProductType(Type type, int month, int year) throws IOException;
	    
}
