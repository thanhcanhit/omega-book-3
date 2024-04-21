package bus;

<<<<<<< HEAD
import java.io.IOException;
import java.rmi.Remote;
=======
import java.rmi.Remote;
import java.rmi.RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;

public interface ViewAccoutingVoucherList_BUS extends Remote{
<<<<<<< HEAD
	public AcountingVoucher getOne(String acountingVoucherID) throws IOException;

    public ArrayList<AcountingVoucher> getAll() throws IOException;
    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) throws IOException;
=======
	public AcountingVoucher getOne(String acountingVoucherID) throws RemoteException;

    public ArrayList<AcountingVoucher> getAll() throws RemoteException;
    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) throws RemoteException;
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb
}
