package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import entity.Employee;
import entity.Store;

public interface EmployeeManagement_BUS extends Remote{
	public String generateID(boolean gender, Date dateOfBirth, Date dateStart) throws RemoteException;
    public ArrayList<Employee> getAllEmployee() throws RemoteException;
    public Employee getEmployee(String employeeID) throws RemoteException;
    public ArrayList<Employee> searchById(String searchQuery) throws RemoteException;
    public ArrayList<Employee> filter(int role, int status) throws RemoteException ;

    public boolean addNewEmployee(Employee employee)  throws RemoteException;

    public boolean createNewAccount(Employee employee) throws Exception ;

    public boolean updateEmployee(Employee newEmployee) throws RemoteException ;

    public boolean updatePassword(String id) throws RemoteException ;

    public Store getStore(String storeID) throws RemoteException;
    
}
