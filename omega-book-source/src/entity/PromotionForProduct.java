package entity;

import java.util.*;

import enums.DiscountType;
import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "PromotionForProduct.findById", query = "SELECT pp FROM PromotionForProduct pp WHERE pp.promotionID = :id"),
	@NamedQuery(name = "PromotionForProduct.findAll", query = "SELECT pp FROM PromotionForProduct pp")
})
public class PromotionForProduct extends Promotion {
	@OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
	private List<ProductPromotionDetail> details;

	

	public PromotionForProduct(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount, List<ProductPromotionDetail> details) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
		this.details = details;
	}
	

	public PromotionForProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromotionForProduct(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
		// TODO Auto-generated constructor stub
	}

	public PromotionForProduct(String promotionID) throws Exception {
		super(promotionID);
		// TODO Auto-generated constructor stub
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
