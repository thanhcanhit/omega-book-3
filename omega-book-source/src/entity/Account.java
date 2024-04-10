/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

/**
 *
 * @author KienTran
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Account.validate", query = "SELECT a FROM Account a WHERE a.employee.employeeID = :employeeID AND a.password = :password"),
		@NamedQuery(name = "Account.changePassword", query = "UPDATE Account a SET a.password = :password WHERE a.employee.employeeID = :employeeID"),
		@NamedQuery(name="Account.create", query="INSERT INTO Account (employee.employeeID, password) VALUES (:employee, :password)"),
		
		})
public class Account {

	private static final String PASSWORD_ERROR = "Mật khẩu phải ít nhất 8 kí tự (Bao gồm chữ hoa, chữ thường và số)!";
	private static final String EMPLOYEE_ERROR = "Employee không được rỗng !";

	private String password;
	@Id
	@OneToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	public Account() {
	}

	public Account(Employee employee) throws Exception {
		setEmployee(employee);
	}

	public Account(String passWord, Employee employee) throws Exception {
		setPassword(passWord);
		setEmployee(employee);
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getPassword() {
		return password;
	}

	public void setEmployee(Employee employee) throws Exception {
//        if(employee!=null)
		this.employee = employee;
//        else
//            throw new Exception(EMPLOYEE_ERROR);
	}

	public void setPassword(String passWord) throws Exception {
//        String regex = "^[A-Z][a-zA-Z0-9.,@&*^]{7,}.*\\d.*";
//        if(Pattern.matches(regex, passWord))
		this.password = passWord;
//        else
//            throw new Exception(PASSWORD_ERROR);
	}

	@Override
	public String toString() {
		return "Account{" + "PASSWORD_ERROR=" + PASSWORD_ERROR + ", EMPLOYEE_ERROR=" + EMPLOYEE_ERROR + ", passWord="
				+ password + ", employee=" + employee + '}';
	}

}
