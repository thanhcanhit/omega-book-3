package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.AcountingVoucher;

public interface ViewAccoutingVoucherList_BUS {
	public AcountingVoucher getOne(String acountingVoucherID);

    public ArrayList<AcountingVoucher> getAll() ;
    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) ;
}
