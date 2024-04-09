package entity;

import java.util.Objects;

import jakarta.persistence.*;

/**
 *
 * @author Như Tâm
 */
@Entity
public class ReturnOrderDetail {
    
    /* Hằng báo lỗi*/
    public static final String ORDERID_EMPTY = "Hoá đơn không được phép rỗng";
    public static final String PRODUCT_EMPTY = "Sản phẩm không được phép rỗng";
    public static final String QUANTITY_VALID = "Số lượng phải là số dương";

    @ManyToOne
    @JoinColumn(name = "returnOrderID")
    private ReturnOrder returnOrder;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    private int quantity;
    private double price;

    public ReturnOrderDetail() {
    }

    public ReturnOrderDetail(Product product, int quantity, double price) throws Exception {
        setProduct(product);
        setQuantity(quantity);
        setPrice(price);
    }

    public ReturnOrderDetail(ReturnOrder returnOrder, Product product) throws Exception {
        setReturnOrder(returnOrder);
        setProduct(product);        
    }
    

    public ReturnOrderDetail(ReturnOrder returnOrder, Product product, int quantity, double price) throws Exception {
        setReturnOrder(returnOrder);
        setProduct(product);
        setQuantity(quantity);
        setPrice(price);
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
        final ReturnOrderDetail other = (ReturnOrderDetail) obj;
        if (!Objects.equals(this.returnOrder, other.returnOrder)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public ReturnOrder getReturnOrder() {
        return returnOrder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.returnOrder);
        hash = 67 * hash + Objects.hashCode(this.product);
        return hash;
    }

    public void setPrice(double price) {
        this.price = price * quantity;
    }

    public void setProduct(Product product) throws Exception {
        if(product == null)
            throw new Exception(PRODUCT_EMPTY);
        this.product = product;
    }
    

    public void setQuantity(int quantity) throws Exception {
        if(quantity < 0)
            throw new Exception(QUANTITY_VALID);
        this.quantity = quantity;
    }

    public void setReturnOrder(ReturnOrder returnOrder) throws Exception {
        if(returnOrder == null)
            throw new Exception(ORDERID_EMPTY);
        this.returnOrder = returnOrder;
    }

    @Override
    public String toString() {
        return "ReturnOrderDetail{" + "returnOrder=" + returnOrder + ", product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }    
}
