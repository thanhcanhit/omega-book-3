package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import enums.DiscountType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Như Tâm
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
	@NamedQuery(name = "Promotion.findByStartedDate", query = "SELECT p FROM Promotion p WHERE p.startedDate = :startedDate"),
	@NamedQuery(name = "Promotion.findByEndedDate", query = "SELECT p FROM Promotion p WHERE p.endedDate = :endedDate"),
	@NamedQuery(name = "Promotion.findByTypeDiscount", query = "SELECT p FROM Promotion p WHERE p.typeDiscount = :typeDiscount"), 
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "promotionType", discriminatorType = DiscriminatorType.STRING)
public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PROMOTIONID_ERROR = "Mã chương trình khuyến mãi không hợp lệ!";
	public static final String STARTEDDATE_ERROR = "Ngày tạo chương trình khuyến mãi không được rỗng!";
	public static final String ENDEDDATE_ERROR = "Ngày kết thúc phải sau ngày tạo chương trình khuyến mãi!";
	public static final String DISCOUNT_ERROR = "Giảm giá phải là số dương!";

	@Id
	protected String promotionID;
	@Temporal(TemporalType.DATE)
	protected Date startedDate;
	@Temporal(TemporalType.DATE)
	protected Date endedDate;
	@Enumerated(EnumType.ORDINAL)
	protected DiscountType typeDiscount;
	protected double discount;

	public Promotion() {
	}

	// constructor theo mã khuyến mãi
	public Promotion(String promotionID) throws Exception {
		setPromotionID(promotionID);
	}

	public Promotion(String promotionID, Date startedDate, Date endedDate,
			DiscountType typeDiscount, double discount) throws Exception {
		setPromotionID(promotionID);
		setStartedDate(startedDate);
		setEndedDate(endedDate);
		setTypeDiscount(typeDiscount);
		setDiscount(discount);
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
		final Promotion other = (Promotion) obj;
		return Objects.equals(this.promotionID, other.promotionID);
	}

	public double getDiscount() {
		return discount;
	}

	public Date getEndedDate() {
		return endedDate;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public DiscountType getTypeDiscount() {
		return typeDiscount;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.promotionID);
		return hash;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setEndedDate(Date endedDate) throws Exception {
		if (endedDate.before(startedDate))
			throw new Exception(ENDEDDATE_ERROR);
		this.endedDate = endedDate;
	}

	public void setPromotionID(String promotionID) throws Exception {
		String pattern = "^(KM)[0-9]{1}[0-9]{1}[0-9]{8}[0-9]{4}$";
		if (!Pattern.matches(pattern, promotionID))
			throw new Exception(PROMOTIONID_ERROR);
		this.promotionID = promotionID;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}

	public void setTypeDiscount(DiscountType typeDiscount) throws Exception {
		if (discount < 0)
			throw new Exception(DISCOUNT_ERROR);
		this.typeDiscount = typeDiscount;
	}

	@Override
	public String toString() {
		return "Promotion [promotionID=" + promotionID + ", startedDate=" + startedDate + ", endedDate=" + endedDate
				+ ", typeDiscount=" + typeDiscount + ", discount=" + discount + "]";
	}



}
