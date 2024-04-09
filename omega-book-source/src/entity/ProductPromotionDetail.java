/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

import jakarta.persistence.*;

/**
 *
 * @author Như Tâm
 */
@IdClass(Promotion.class)
public class ProductPromotionDetail {
	@ManyToOne
	@JoinColumn(name = "promotionID")
    private Promotion promotion;
	@ManyToOne
	@JoinColumn(name = "productID")
    private Product product;

    public ProductPromotionDetail() {
    }

    public ProductPromotionDetail(Product product) {
        this.product = product;
    }

    public ProductPromotionDetail(Promotion promotion, Product product) {
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

    public Promotion getPromotion() {
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

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ProductPromotionDetail{" + "promotion=" + promotion + ", product=" + product + '}';
    }
    
    
}
