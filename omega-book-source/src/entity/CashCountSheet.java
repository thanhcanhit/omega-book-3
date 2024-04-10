/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;

/**
 *
 * @author Hoàng Khang
 */
@Entity
public class CashCountSheet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2549476946541562785L;

	@Id
    private String cashCountSheetID;
	
	@OneToMany(mappedBy = "cashCountSheet")
    private ArrayList<CashCount> cashCountList;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
    private Date endedDate;
    private double total;
    private double difference;

    @OneToMany(mappedBy = "cashCountSheet")
    private ArrayList<CashCountSheetDetail> cashCountSheetDetailList;

    public CashCountSheet() {
    }
    public CashCountSheet(String cashCountSheetID) {
        this.cashCountSheetID = cashCountSheetID;
    }

    public CashCountSheet(String cashCountSheetID, ArrayList<CashCount> cashCountList, ArrayList<CashCountSheetDetail> cashCountSheetDetailList, Date createdDate, Date endedDate) {
        this.cashCountSheetID = cashCountSheetID;
        this.cashCountList = cashCountList;
        this.createdDate = createdDate;
        this.endedDate = endedDate;
        setTotal();
        setDifference();
        this.cashCountSheetDetailList = cashCountSheetDetailList;
    }

//    @Override
//    public int compareTo(CashCountSheet o) {
//        return this.createdDate.compareTo(o.createdDate);
//    }

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
        final CashCountSheet other = (CashCountSheet) obj;
        return Objects.equals(this.cashCountSheetID, other.cashCountSheetID);
    }

    public List<CashCount> getCashCountList() {
        return cashCountList;
    }

    public ArrayList<CashCountSheetDetail> getCashCountSheetDetailList() {
        return cashCountSheetDetailList;
    }

    public String getCashCountSheetID() {
        return cashCountSheetID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public double getDifference() {
        return difference;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.cashCountSheetID);
        return hash;
    }

    public void setCashCountList(ArrayList<CashCount> cashCountList) {
        this.cashCountList = cashCountList;
        setTotal();
        setDifference();
    }

    public void setCashCountSheetDetailList(ArrayList<CashCountSheetDetail> cashCountSheetDetailList) {
        this.cashCountSheetDetailList = cashCountSheetDetailList;
    }

    public void setCashCountSheetID(String cashCountSheetID) {
        this.cashCountSheetID = cashCountSheetID;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    
    public void setDifference() {
        difference = total - 1765000;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    private void setTotal() {
        double sum = 0;
        for (CashCount cashCount : cashCountList) {
            sum += cashCount.getTotal();
        }
        this.total = sum;
    }

    @Override
    public String toString() {
        return "CashCountSheet{" + "cashCountSheetID=" + cashCountSheetID + ", cashCountList=" + cashCountList + ", createdDate=" + createdDate + ", endedDate=" + endedDate + ", total=" + total + '}';
    }
    
    

}
