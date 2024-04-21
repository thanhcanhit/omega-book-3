package bus;

import java.io.IOException;
import java.rmi.Remote;

public interface StatisticCustomer_BUS extends Remote {
	public int getNumberCus() throws IOException;

	public int sumCustomer() throws IOException;

	public int countMaleCustomers() throws IOException;

	public int[] filterCustomers() throws IOException;
}
