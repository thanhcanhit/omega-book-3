package entity;

import java.util.Objects;

import jakarta.persistence.*;

/**
 *
 * @author Như Tâm
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "ProductPromotionDetail.findAll", query = "SELECT pd FROM ProductPromotionDetail pd"),
	@NamedQuery(name = "ProductPromotionDetail.findByPromotion", query = "SELECT pd FROM ProductPromotionDetail pd WHERE pd.promotion.promotionID = :promotionID"),
	@NamedQuery(name = "ProductPromotionDetail.findByProduct", query = "SELECT pd FROM ProductPromotionDetail pd WHERE pd.product.productID = :productID"),
	@NamedQuery(name = "ProductPromotionDetail.findByPromotionAndProduct", query = "SELECT pd FROM ProductPromotionDetail pd WHERE pd.promotion.promotionID = :promotionID AND pd.product.productID = :productID")
})
public class ProductPromotionDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "promotionID")
    private PromotionForProduct promotion;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "productID")
    private Product product;

    public ProductPromotionDetail() {
    }

    public ProductPromotionDetail(Product product) {
        this.product = product;
    }

    public ProductPromotionDetail(PromotionForProduct promotion, Product product) {
        this.promotion = promotion;
        this.product = product;
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
        final ProductPromotionDetail other = (ProductPromotionDetail) obj;
        if (!Objects.equals(this.promotion, other.promotion)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    public Product getProduct() {
        return product;
    }

    public PromotionForProduct getPromotion() {
        return promotion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.promotion);
        hash = 11 * hash + Objects.hashCode(this.product);
        return hash;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPromotionForProduct(PromotionForProduct promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ProductPromotionDetail{" + "promotion=" + promotion + ", product=" + product + '}';
    }
    
    
}
