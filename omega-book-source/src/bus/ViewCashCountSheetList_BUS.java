package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CashCountSheet;

public interface ViewCashCountSheetList_BUS extends Remote{
	public List<CashCountSheet> getAll() throws RemoteException;

	public void GeneratePDF(CashCountSheet cash) throws RemoteException;

	public CashCountSheet getOne(String id) throws RemoteException;
}
