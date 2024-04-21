package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;

public interface ViewAccoutingVoucherList_BUS extends Remote{
	public AcountingVoucher getOne(String acountingVoucherID) throws RemoteException;
    public ArrayList<AcountingVoucher> getAll() throws RemoteException;
    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) throws RemoteException;
}
