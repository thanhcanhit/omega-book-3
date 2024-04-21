package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;

import enums.Type;

public interface StatisticSales_BUS extends Remote{
	 public int getTotalNumberOrder(int month, int year) throws RemoteException;
	    public int getTotalNumberPurchaseOrder(int month, int year) throws RemoteException;
	    public int getTotalNumberReturnOrder(int month, int year) throws RemoteException;
	    public double getTotalInMonth(int month, int year) throws RemoteException;
	    public double getTargetInMonth(int month, int year) throws RemoteException;
	    

	    public double[] getTotalPerDay(int month, int year) throws RemoteException;
	    public int getQuantityProductType(Type type, int month, int year) throws RemoteException;
	    
}
