package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.Shift;

public interface ShiftsManagement_BUS extends Remote{
	 public Shift getOne(String id) throws IOException;

	    public ArrayList<Shift> getAll() throws IOException;

	    public boolean createShifts(Shift shift) throws IOException;
	    public String renderID() throws IOException;

	    public ArrayList<Shift> getShiftsByDate(Date date) throws IOException;

	    public ArrayList<Shift> filter(String emloyeeID, String role, Date date) throws IOException;
}
