package bus;

import entity.Employee;

public interface Login_BUS {
	public Employee login(String id, String password) throws Exception ;

    public boolean changePassword(String id, String pass, String passNew) throws Exception ;
}
