/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author KienTran
 */
@Entity
public final class Shift implements Serializable{

    private static final long serialVersionUID = -7638876334557551590L;
	private static final String ID_EMPTY = "ID không được rỗng !";
    private static final String STARTEDAT_ERROR = "StartedAt không được rỗng !";
    private static final String ENDEDAD_ERROR = "EndedAt Không được rỗng !";
    private static final String ACCOUNT_ERROR = "Account không được rỗng !";
    @Id
    private String shiftID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endedAt;
    @ManyToOne
    @JoinColumn(name="accountID")
    private Account account;

    public Shift() {
    }

    public Shift(String shiftID) {
        this.shiftID = shiftID;
    }

    public Shift(String shiftID, Date startedAt, Account account) {
        this.shiftID = shiftID;
        this.startedAt = startedAt;
        this.account = account;
    }

    public Shift(String shiftID, Date startedAt, Date endedAt, Account account) throws Exception {
        setAccount(account);
        setStartedAt(startedAt);
        setEndedAt(endedAt);
        setShiftID(shiftID);
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
        final Shift other = (Shift) obj;
        return Objects.equals(this.shiftID, other.shiftID);
    }

    public Account getAccount() {
        return account;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public String getShiftID() {
        return shiftID;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.shiftID);
        return hash;
    }

    public void setAccount(Account account) throws Exception {
        if (account != null) {
            this.account = account;
        } else {
            throw new Exception(ACCOUNT_ERROR);
        }
    }

    public void setEndedAt(Date endedAt){
//        if (endedAt != null) {
//            this.endedAt = endedAt;
//        } else {
//            throw new Exception(ENDEDAD_ERROR);
//        }
    	        this.endedAt = endedAt;
    }

    public void setShiftID(String shiftID) throws Exception {
        String regex = "^[P][H]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(shiftID).matches()) {
            this.shiftID = shiftID;
        } else {
            throw new Exception(ID_EMPTY);
        }

    }

    public void setStartedAt(Date startedAt) throws Exception {
        if (startedAt != null) {
            this.startedAt = startedAt;
        } else {
            throw new Exception(STARTEDAT_ERROR);
        }
    }

    @Override
    public String toString() {
        return "Shift{" + "STARTEDAT_ERROR=" + STARTEDAT_ERROR + ", ENDEDAD_ERROR=" + ENDEDAD_ERROR + ", ACCOUNT_ERROR=" + ACCOUNT_ERROR + ", shiftID=" + shiftID + ", startedAt=" + startedAt + ", endedAt=" + endedAt + ", account=" + account + '}';
    }

}
