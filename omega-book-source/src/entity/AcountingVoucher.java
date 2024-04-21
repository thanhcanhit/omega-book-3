/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Hoàng Khang
 */
@Entity
@NamedQuery(name = "AcountingVoucher.findAll", query = "SELECT a FROM AcountingVoucher a")
public class AcountingVoucher implements Comparable<AcountingVoucher>{
	@Id
    private String accountingVoucherID;
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
    private Date endedDate;
    private double sale;
    private double payViaATM;
    private double withDraw;
    private double difference;
    
    @OneToOne
    @JoinColumn(name = "cashCountSheetID")
    private CashCountSheet cashCountSheet;
    
    @OneToMany(mappedBy = "accountingVoucher",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> orderList;

    public AcountingVoucher() {
    }

    public AcountingVoucher(Date endedDate) {
        this.endedDate = endedDate;
    }

    public AcountingVoucher(String accountingVoucherID) {
        this.accountingVoucherID = accountingVoucherID;
    }

    public AcountingVoucher(String accountingVoucherID, Date createdDate, Date endedDate, CashCountSheet cashCountSheet, ArrayList<Bill> orderList) {
        setAcountingVoucherID(accountingVoucherID);
        setCreatedDate(createdDate);
        setEndedDate(endedDate);
        setCashCountSheet(cashCountSheet);
        setOrderList(orderList);
        setSale();
        setPayViaATM();
        setWithDraw();
        setDifference();

        

    }

//    @Override
//    public int compareTo(AcountingVoucher o) {
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
        final AcountingVoucher other = (AcountingVoucher) obj;
        return Objects.equals(this.accountingVoucherID, other.accountingVoucherID);
    }

    public String getAcountingVoucherID() {
        return accountingVoucherID;
    }

    public CashCountSheet getCashCountSheet() {
        return cashCountSheet;
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

    public List<Bill> getOrderList() {
        return orderList;
    }

    public double getPayViaATM() {
        return payViaATM;
    }

    public double getSale() {
        return this.sale;
    }

    public double getWithDraw() {
        return withDraw;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.accountingVoucherID);
        return hash;
    }

    public void setAcountingVoucherID(String accountingVoucherID) {
        this.accountingVoucherID = accountingVoucherID;
    }

    public void setCashCountSheet(CashCountSheet cashCountSheet) {
        this.cashCountSheet = cashCountSheet;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    private void setDifference() {
        this.difference = cashCountSheet.getTotal() - withDraw - 1765000;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    public void setOrderList(ArrayList<Bill> orderList) {
        this.orderList = orderList;
    }

    public void setPayViaATM() {
        double sum = 0;
        for (Bill order : orderList) {
            if (order.isPayment()) {
                sum += order.getTotalDue();
            }
        }
        this.payViaATM = sum;
    }
    private void setSale() {
        double sum = 0;
        for (Bill order : orderList) {
            sum += order.getTotalDue();
        }
        this.sale = sum;
    }
    
    

    public String getAccountingVoucherID() {
		return accountingVoucherID;
	}

	public void setAccountingVoucherID(String accountingVoucherID) {
		this.accountingVoucherID = accountingVoucherID;
	}

	public void setWithDraw() {
        this.withDraw = this.sale - this.payViaATM;
    }

    @Override
    public String toString() {
        return "AccountingVoucher{" + "accountingVoucherID=" + accountingVoucherID + ", createdDate=" + createdDate + ", endedDate=" + endedDate + ", sale=" + sale + ", payViaATM=" + payViaATM + ", withDraw=" + withDraw + ", difference=" + difference + ", cashCountSheet=" + cashCountSheet + ", accoutinVoucherDetailList=" + '}';
    }

	@Override
	public int compareTo(AcountingVoucher o) {
		// TODO Auto-generated method stub
		 return this.createdDate.compareTo(o.createdDate);
	}

}
