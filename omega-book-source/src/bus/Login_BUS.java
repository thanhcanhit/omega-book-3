package bus;

import java.rmi.Remote;

import entity.Employee;

public interface Login_BUS extends Remote{
	public Employee login(String id, String password) throws Exception ;

    public boolean changePassword(String id, String pass, String passNew) throws Exception ;
}
