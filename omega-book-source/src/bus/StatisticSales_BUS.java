package bus;

<<<<<<< HEAD
import java.io.IOException;
import java.rmi.Remote;
=======
import java.rmi.Remote;
import java.rmi.RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb

import enums.Type;

public interface StatisticSales_BUS extends Remote{
<<<<<<< HEAD
	 public int getTotalNumberOrder(int month, int year) throws IOException;
	    public int getTotalNumberPurchaseOrder(int month, int year) throws IOException;
	    public int getTotalNumberReturnOrder(int month, int year) throws IOException;
	    public double getTotalInMonth(int month, int year) throws IOException;
	    public double getTargetInMonth(int month, int year) throws IOException;
	    

	    public double[] getTotalPerDay(int month, int year)throws IOException;
	    public int getQuantityProductType(Type type, int month, int year) throws IOException;
=======
	 public int getTotalNumberOrder(int month, int year) throws RemoteException;
	    public int getTotalNumberPurchaseOrder(int month, int year) throws RemoteException;
	    public int getTotalNumberReturnOrder(int month, int year) throws RemoteException;
	    public double getTotalInMonth(int month, int year) throws RemoteException;
	    public double getTargetInMonth(int month, int year) throws RemoteException;
	    

	    public double[] getTotalPerDay(int month, int year) throws RemoteException;
	    public int getQuantityProductType(Type type, int month, int year) throws RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
	    
}
