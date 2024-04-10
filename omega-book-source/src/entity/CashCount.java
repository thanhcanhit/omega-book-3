/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Hoàng Khang
 */
@Entity
public class CashCount {
	@Id
	private double value;
	@Id
	@ManyToOne
	@JoinColumn(name = "cashCountSheetID")
	private CashCountSheet cashCountSheet;
    private int quantity;
    private double total;

    @ManyToOne
    @JoinColumn(name = "cashCountSheetID")
    private CashCountSheet cashCountSheet;
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
