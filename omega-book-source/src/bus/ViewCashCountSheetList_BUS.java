package bus;

<<<<<<< HEAD
import java.io.IOException;
import java.rmi.Remote;
=======
import java.rmi.Remote;
import java.rmi.RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
import java.util.List;

import entity.CashCountSheet;

public interface ViewCashCountSheetList_BUS extends Remote{
<<<<<<< HEAD
	public List<CashCountSheet> getAll() throws IOException;

	public void GeneratePDF(CashCountSheet cash) throws IOException;

	public CashCountSheet getOne(String id) throws IOException;
=======
	public List<CashCountSheet> getAll() throws RemoteException;

	public void GeneratePDF(CashCountSheet cash) throws RemoteException;

	public CashCountSheet getOne(String id) throws RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
}
