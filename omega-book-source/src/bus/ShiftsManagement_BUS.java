package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Shift;

public interface ShiftsManagement_BUS {
	 public Shift getOne(String id) ;

	    public ArrayList<Shift> getAll();

	    public boolean createShifts(Shift shift) ;
	    public String renderID() ;

	    public ArrayList<Shift> getShiftsByDate(Date date) ;

	    public ArrayList<Shift> filter(String emloyeeID, String role, Date date);
}
