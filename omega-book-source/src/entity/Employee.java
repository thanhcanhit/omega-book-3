/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;

/**
 *
 * @author Hoàng Khang
 */

@NamedQueries({ @NamedQuery(name = "Employee.getAll", query = "SELECT e FROM Employee e"),
		@NamedQuery(name = "Employee.update", query = "UPDATE Employee SET \r\n"
				+ "citizenIdentification = :citizenIdentification, \r\n" + "role = :role, \r\n"
				+ "status = :status, \r\n" + "name = :name, \r\n" + "phoneNumber = :phoneNumber, \r\n"
				+ "gender = :gender, \r\n" + "dateOfBirth = :dateOfBirth, \r\n" + "address = :address \r\n"
				+ "WHERE employeeID = :employeeID\r\n") })
@Entity
public class Employee implements Serializable {
	@Id
	private String employeeID;
	private String citizenIdentification;
	@Column(columnDefinition = "nvarchar(max)")
	private String role;
	private boolean status;
	@Column(columnDefinition = "nvarchar(max)")
	private String name;
	private String phoneNumber;
	private boolean gender;
	private Date dateOfBirth;
	@Column(columnDefinition = "nvarchar(max)")
	private String address;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private List<Order> order;

	public Employee() {
	}

	public Employee(String employeeID) {
		setEmployeeID(employeeID);
	}

	public Employee(String employeeID, String citizenIdentification, String role, boolean status, String name,
			String phoneNumber, boolean gender, Date dateOfBirth, String address, Store store) {
		setEmployeeID(employeeID);
		setCitizenIdentification(citizenIdentification);
		setRole(role);
		setStatus(status);
		setStore(store);
		setPhoneNumber(phoneNumber);
		setName(name);
		setDateOfBirth(dateOfBirth);
		setAddress(address);
		setGender(gender);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Employee other = (Employee) obj;
		return Objects.equals(this.employeeID, other.employeeID);
	}

	public String getAddress() {
		return address;
	}

	public String getCitizenIdentification() {
		return citizenIdentification;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public Store getStore() {
		return store;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.employeeID);
		return hash;
	}

	public boolean isGender() {
		return gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCitizenIdentification(String citizenIdentification) throws IllegalArgumentException {
		citizenIdentification = citizenIdentification.trim();
		if (!citizenIdentification.matches("\\d{12}")) {
			throw new IllegalArgumentException("Mã định danh phải gồm đúng 12 chữ số!");
		}
		this.citizenIdentification = citizenIdentification;
	}

	public void setDateOfBirth(Date dateOfBirth) throws IllegalArgumentException {
		if (java.sql.Date.valueOf(LocalDate.now()).getYear() - dateOfBirth.getYear() < 18)
			throw new IllegalArgumentException("Nhân viên phải đủ 18 tuổi trở lên");
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setName(String name) throws IllegalArgumentException {
		name = name.trim();
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Họ tên không được rỗng!");
		}
		if (!name.matches("^[\\p{L} ]+$")) {
			throw new IllegalArgumentException("Họ tên chỉ được chứa kí tự chữ và khoảng trắng!");
		}
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
		phoneNumber = phoneNumber.trim();
		if (!phoneNumber.matches("^(02|03|05|07|08|09)\\d{8}$")) {
			throw new IllegalArgumentException("Số điện thoại không hợp lệ!");
		}
		this.phoneNumber = phoneNumber;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return employeeID + " " + name;
	}

}
