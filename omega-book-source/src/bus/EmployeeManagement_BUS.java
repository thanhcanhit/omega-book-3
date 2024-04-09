package bus;

import java.util.ArrayList;
import java.util.Date;

import entity.Employee;
import entity.Store;

public interface EmployeeManagement_BUS {
	public String generateID(boolean gender, Date dateOfBirth, Date dateStart) ;
    public ArrayList<Employee> getAllEmployee();
    public Employee getEmployee(String employeeID) ;
    public ArrayList<Employee> searchById(String searchQuery);
    public ArrayList<Employee> filter(int role, int status) ;

    public boolean addNewEmployee(Employee employee) ;



    public boolean createNewAccount(Employee employee) throws Exception ;

    public boolean updateEmployee(Employee newEmployee) ;

    public boolean updatePassword(String id) ;

    public Store getStore(String storeID);
    
}
