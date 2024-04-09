/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 *
 * @author KienTran
 */
@Entity
public class Account {
    
    private static final String PASSWORD_ERROR = "Mật khẩu phải ít nhất 8 kí tự (Bao gồm chữ hoa, chữ thường và số)!";
    private static final String EMPLOYEE_ERROR = "Employee không được rỗng !";

  
    private String passWord;
    @Id
    @OneToOne
    @JoinColumn(name="employeeID")
    private Employee employee;

    public Account() {
    }

    public Account(Employee employee) throws Exception {
        setEmployee(employee);
    }

    public Account(String passWord, Employee employee) throws Exception {
        setPassWord(passWord);
        setEmployee(employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPassWord() {
        return passWord;
    }
    
    public void setEmployee(Employee employee) throws Exception{
//        if(employee!=null)
            this.employee = employee;
//        else
//            throw new Exception(EMPLOYEE_ERROR);
    }

    public void setPassWord(String passWord) throws Exception {
//        String regex = "^[A-Z][a-zA-Z0-9.,@&*^]{7,}.*\\d.*";
//        if(Pattern.matches(regex, passWord))
            this.passWord = passWord;
//        else
//            throw new Exception(PASSWORD_ERROR);
    }

    @Override
    public String toString() {
        return "Account{" + "PASSWORD_ERROR=" + PASSWORD_ERROR + ", EMPLOYEE_ERROR=" + EMPLOYEE_ERROR + ", passWord=" + passWord + ", employee=" + employee + '}';
    }
    
    
    
}
