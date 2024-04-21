package entity;

import java.io.Serializable;
import java.util.*;

import enums.DiscountType;
import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "PromotionForProduct.findById", query = "SELECT pp FROM PromotionForProduct pp WHERE pp.promotionID = :id"),
	@NamedQuery(name = "PromotionForProduct.findAll", query = "SELECT pp FROM PromotionForProduct pp")
})
public class PromotionForProduct extends Promotion implements Serializable{
	private static final long serialVersionUID = -5000246354599993538L;
	/**
	 * 
	 */
	
	@OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
	private List<ProductPromotionDetail> details;

	

	public PromotionForProduct(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount, List<ProductPromotionDetail> details) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
		setDetails(details);
	}
	

	public PromotionForProduct() {
		super();
	}

	public PromotionForProduct(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
	}

	public PromotionForProduct(String promotionID) throws Exception {
		super(promotionID);
	}
	

	public List<ProductPromotionDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ProductPromotionDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "PromotionForProduct [details=" + details + ", promotionID=" + promotionID + ", startedDate="
				+ startedDate + ", endedDate=" + endedDate + ", typeDiscount=" + typeDiscount + ", discount=" + discount
				+ "]";
	}
	
}
