package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import entity.Employee;
import entity.Store;

public interface EmployeeManagement_BUS extends Remote{
	public String generateID(boolean gender, Date dateOfBirth, Date dateStart) throws IOException;
    public ArrayList<Employee> getAllEmployee() throws IOException;
    public Employee getEmployee(String employeeID) throws IOException;
    public ArrayList<Employee> searchById(String searchQuery) throws IOException;
    public ArrayList<Employee> filter(int role, int status) throws IOException ;

    public boolean addNewEmployee(Employee employee)  throws IOException;

    public boolean createNewAccount(Employee employee) throws Exception ;

    public boolean updateEmployee(Employee newEmployee) throws IOException ;

    public boolean updatePassword(String id) throws IOException ;

    public Store getStore(String storeID);
    
}
