/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Hoàng Khang
 */
@Entity
public class CashCountSheetDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@Id
	@ManyToOne
	@JoinColumn(name = "cashCountSheetID")
	private CashCountSheet cashCountSheet;

	private boolean isChecker;

	public CashCountSheetDetail() {
	}

	public CashCountSheetDetail(boolean isChecker, Employee employee, CashCountSheet cashCountSheet) {
		this.isChecker = isChecker;
		this.employee = employee;
		this.cashCountSheet = cashCountSheet;
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
		final CashCountSheetDetail other = (CashCountSheetDetail) obj;
		if (!Objects.equals(this.employee, other.employee)) {
			return false;
		}
		return Objects.equals(this.cashCountSheet, other.cashCountSheet);
	}

	public CashCountSheet getCashCountSheet() {
		return cashCountSheet;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.employee);
		hash = 37 * hash + Objects.hashCode(this.cashCountSheet);
		return hash;
	}

	public void setCashCountSheet(CashCountSheet cashCountSheet) {
		this.cashCountSheet = cashCountSheet;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isChecker() {
		return isChecker;
	}

	public void setChecker(boolean isChecker) {
		this.isChecker = isChecker;
	}

	@Override
	public String toString() {
		return "CashCountSheetDetail{" + "employee=" + employee + ", cashCountSheet=" + cashCountSheet + '}';
	}

}
