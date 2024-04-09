package entity;

import enums.DiscountType;
import enums.CustomerRank;
import enums.PromotionType;
import jakarta.persistence.*;
import java.util.*;
import java.util.regex.Pattern;

import enums.CustomerRank;
import enums.DiscountType;
import enums.PromotionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Như Tâm
 */
@Entity
public final class Promotion {
    public final String PROMOTIONID_ERROR="Mã chương trình khuyến mãi không hợp lệ!";
    public final String STARTEDDATE_ERROR="Ngày tạo chương trình khuyến mãi không được rỗng!";
    public final String ENDEDDATE_ERROR="Ngày kết thúc phải sau ngày tạo chương trình khuyến mãi!";
    public final String DISCOUNT_ERROR="Giảm giá phải là số dương!";
    
    @Id
    private String promotionID; 
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endedDate;
    @Enumerated(EnumType.ORDINAL)
    private PromotionType typePromotion;
    @Enumerated(EnumType.ORDINAL)
    private DiscountType typeDiscount;
    private double discount;
    @Enumerated(EnumType.ORDINAL)
    private CustomerRank condition;
    @OneToMany(mappedBy = "promotion")
    private ArrayList<ProductPromotionDetail> listDetail;

	@Id
	private String promotionID;
	@Temporal(TemporalType.DATE)
	private Date startedDate;
	@Temporal(TemporalType.DATE)
	private Date endedDate;
	@Enumerated(EnumType.ORDINAL)
	private PromotionType typePromotion;
	@Enumerated(EnumType.ORDINAL)
	private DiscountType typeDiscount;
	private double discount;
	@Enumerated(EnumType.ORDINAL)
	private CustomerRank condition;

	@ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
	private Set<Product> products;

	public Promotion() {
	}

	// constructor theo mã khuyến mãi
	public Promotion(String promotionID) throws Exception {
		setPromotionID(promotionID);
	}

	public Promotion(String promotionID, Date startedDate, Date endedDate, PromotionType typePromotion,
			DiscountType typeDiscount, double discount) throws Exception {
		setPromotionID(promotionID);
		setStartedDate(startedDate);
		setEndedDate(endedDate);
		setTypePromotion(typePromotion);
		setTypeDiscount(typeDiscount);
		setDiscount(discount);
	}

	// constructor khuyến mãi theo sản phẩm
	public Promotion(String promotionID, Date startedDate, Date endedDate, PromotionType typePromotion,
			DiscountType typeDiscount, double discount, Set<Product> listDetail) throws Exception {
		setPromotionID(promotionID);
		setStartedDate(startedDate);
		setEndedDate(endedDate);
		setTypePromotion(typePromotion);
		setTypeDiscount(typeDiscount);
		setDiscount(discount);
		setProducts(listDetail);
	}

	// constructor khuyến mãi theo hoá đơn
	public Promotion(String promotionID, Date startedDate, Date endedDate, PromotionType typePromotion,
			DiscountType typeDiscount, double discount, CustomerRank condition) throws Exception {
		setPromotionID(promotionID);
		setStartedDate(startedDate);
		setEndedDate(endedDate);
		setTypePromotion(typePromotion);
		setTypeDiscount(typeDiscount);
		setDiscount(discount);
		setCondition(condition);
	}

	// constructor đầy đủ
	public Promotion(String promotionID, Date startedDate, Date endedDate, PromotionType typePromotion,
			DiscountType typeDiscount, double discount, CustomerRank condition, Set<Product> listDetail)
			throws Exception {
		setPromotionID(promotionID);
		setStartedDate(startedDate);
		setEndedDate(endedDate);
		setTypePromotion(typePromotion);
		setTypeDiscount(typeDiscount);
		setDiscount(discount);
		setCondition(condition);
		setProducts(listDetail);
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

	public CustomerRank getCondition() {
		return condition;
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

	public PromotionType getTypePromotion() {
		return typePromotion;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.promotionID);
		return hash;
	}

	public void setCondition(CustomerRank condition) {
		this.condition = condition;
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

	public void setTypePromotion(PromotionType typePromotion) {
		this.typePromotion = typePromotion;
	}

	@Override
	public String toString() {
		return "Promotion{" + "PROMOTIONID_ERROR=" + PROMOTIONID_ERROR + ", STARTEDDATE_ERROR=" + STARTEDDATE_ERROR
				+ ", ENDEDDATE_ERROR=" + ENDEDDATE_ERROR + ", DISCOUNT_ERROR=" + DISCOUNT_ERROR + ", promotionID="
				+ promotionID + ", startedDate=" + startedDate + ", endedDate=" + endedDate + ", typePromotion="
				+ typePromotion + ", typeDiscount=" + typeDiscount + ", discount=" + discount + ", condition="
				+ condition;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
