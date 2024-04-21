/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Transient;

/**
 *
 * @author Hoàng Khang
 */
@Entity
@NamedQueries({
//	Lấy ra tất cả các CashCount của một CashCountSheet
	@NamedQuery(name = "CashCount.findAllByCashCountSheetID", query = "SELECT c FROM CashCount c WHERE c.cashCountSheet.id = :cashCountSheetID"),
//	Thêm CashCount xuống database
//	@NamedQuery(name = "CashCount.insert", query = "INSERT INTO CashCount (value, quantity, cashCountSheetID) VALUES (:value, :quantity, :cashCountSheetID)"),
})
public class CashCount implements Serializable{
	private static final long serialVersionUID = -427318693942296752L;
	@Id
	private double value;
	@Id
	@ManyToOne
	@JoinColumn(name = "cashCountSheetID")
	private CashCountSheet cashCountSheet;
    private int quantity;
//    Không thêm vào database
    @Transient
    private double total;
    public CashCount() {
    }

    public CashCount(int quantity, double value) {
        setValue(value);
        setQuantity(quantity);
        setTotal();
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
        final CashCount other = (CashCount) obj;
        return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(other.value);
    }

    public int getQuantity() {
        return quantity;
    }


    public double getTotal() {
        return total;
    }

    public double getValue() {
        return value;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

    public void setQuantity(int quantity) throws IllegalArgumentException {
    if (quantity < 0) {
        throw new IllegalArgumentException("Giá trị của quantity phải là số nguyên dương");
    }
    this.quantity = quantity;
}

    private void setTotal() {
        this.total = this.quantity*this.value;
    }

    public void setValue(double value) throws IllegalArgumentException {
//    double[] allowedValues = {1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};
//    if (!Arrays.stream(allowedValues).anyMatch(val -> val == value)) {
//        throw new IllegalArgumentException("Giá trị của value không hợp lệ!");
//    }
    this.value = value;
}

    @Override
    public String toString() {
        return "CashCount{" + "quantity=" + quantity + ", value=" + value + ", total=" + total + '}';
    }
    
    
    
}
