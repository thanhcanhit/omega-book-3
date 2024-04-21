package entity;

import java.util.Date;

import enums.CustomerRank;
import enums.DiscountType;
import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "PromotionForOrder.findAll", query = "SELECT po FROM PromotionForOrder po"),
	@NamedQuery(name = "PromotionForOrder.findByPromotionID", query = "SELECT po FROM PromotionForOrder po WHERE po.promotionID like :promotionID"),
})
public class PromotionForOrder extends Promotion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.ORDINAL)
	private CustomerRank condition;

	

	public PromotionForOrder(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount, CustomerRank condition) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
		setCondition(condition);
	}

	public PromotionForOrder() {
		super();
	}

	public PromotionForOrder(String promotionID, Date startedDate, Date endedDate, DiscountType typeDiscount,
			double discount) throws Exception {
		super(promotionID, startedDate, endedDate, typeDiscount, discount);
	}

	public PromotionForOrder(String promotionID) throws Exception {
		super(promotionID);
	}
	

	public CustomerRank getCondition() {
		return condition;
	}

	public void setCondition(CustomerRank condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "PromotionForOrder [condition=" + condition + ", promotionID=" + promotionID + ", startedDate="
				+ startedDate + ", endedDate=" + endedDate + ", typeDiscount=" + typeDiscount + ", discount=" + discount
				+ "]";
	}	
}
