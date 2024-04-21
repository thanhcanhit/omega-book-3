package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;

public interface ViewAccoutingVoucherList_BUS extends Remote{
	public AcountingVoucher getOne(String acountingVoucherID) throws IOException;

    public ArrayList<AcountingVoucher> getAll() throws IOException;
    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) throws IOException;
}
