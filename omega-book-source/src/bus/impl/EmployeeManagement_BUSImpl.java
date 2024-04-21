/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bus.EmployeeManagement_BUS;
import dao.Account_DAO;
import dao.Employee_DAO;
import dao.Store_DAO;
import entity.Employee;
import entity.Store;

/**
 *
 * @author Như Tâm
 */
<<<<<<< HEAD:omega-book-source/src/bus/impl/EmployeeManagament_BUSImpl.java
public class EmployeeManagament_BUSImpl extends UnicastRemoteObject implements EmployeeManagement_BUS{
    public EmployeeManagament_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4765156524097160811L;
	private Employee_DAO dao = new Employee_DAO();
=======
public class EmployeeManagement_BUSImpl implements EmployeeManagement_BUS{
    private Employee_DAO dao = new Employee_DAO();
>>>>>>> ddc6b436ec355db02ea7d7fa653c6440e65974fb:omega-book-source/src/bus/impl/EmployeeManagement_BUSImpl.java
    private Account_DAO accountDAO = new Account_DAO();
    private Store_DAO store_dao = new Store_DAO();
    
    public String generateID(boolean gender, Date dateOfBirth, Date dateStart) throws RemoteException{
        //Khởi tạo mã nhâm viên NV
        String prefix = "NV";
        //Kí tự tiếp theo là giới tính
        if(gender)
            prefix += 1;
        else
            prefix += 0;
        //4 kí tự tiếp theo là ngày sinh của nhân viên
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String formatBirth = simpleDateFormat.format(dateOfBirth);
        String formatStart = simpleDateFormat.format(dateStart);
        
        prefix += formatBirth;
        //4 kí tự tiếp theo là ngày tháng 
        prefix += formatStart;
        //Tìm mã có tiền tố là code và xxx lớn nhất
        String maxID = Employee_DAO.getMaxSequence(prefix);
        if (maxID == null) {
            prefix += "000";
        } else {
            String lastFourChars = maxID.substring(maxID.length() - 4);
            int num = Integer.parseInt(lastFourChars);
            num++;
            prefix += String.format("%04d", num);
        }
        return prefix;
    }
    public ArrayList<Employee> getAllEmployee() throws RemoteException{
        ArrayList<Employee> employeeList = new Employee_DAO().getAll();
        return employeeList;
    }
    public Employee getEmployee(String employeeID) throws RemoteException{
        return dao.getOne(employeeID);
    }
    public ArrayList<Employee> searchById(String searchQuery) throws RemoteException{
        return dao.findById(searchQuery);
    }
    public ArrayList<Employee> filter(int role, int status) throws RemoteException{
        return dao.filter(role, status);
    }

    public boolean addNewEmployee(Employee employee) throws RemoteException{
        return dao.create(employee);
    }

//    public String getPassword(Employee employee) throws Exception {
//        return dao.getPassword(employee);
//    }

    public boolean createNewAccount(Employee employee) throws Exception {
        return dao.createAccount(employee);
    }

    public boolean updateEmployee(Employee newEmployee) throws RemoteException{
        return dao.update(newEmployee.getEmployeeID(), newEmployee);
    }

    public boolean updatePassword(String id) throws RemoteException{
        return accountDAO.updatePass(id, "985441048ea529312dfb141f8a9e6de3");
    }

    public Store getStore(String storeID) throws RemoteException{
        return store_dao.getOne(storeID);
    }
}
