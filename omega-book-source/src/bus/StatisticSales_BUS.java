package bus;

import enums.Type;

public interface StatisticSales_BUS {
	 public int getTotalNumberOrder(int month, int year);
	    public int getTotalNumberPurchaseOrder(int month, int year);
	    public int getTotalNumberReturnOrder(int month, int year);
	    public double getTotalInMonth(int month, int year);
	    public double getTargetInMonth(int month, int year);
	    

	    public double[] getTotalPerDay(int month, int year);
	    public int getQuantityProductType(Type type, int month, int year);
	    
}
