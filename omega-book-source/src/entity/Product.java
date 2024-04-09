/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import enums.BookType;
import enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 *
 * @author thanhcanhit
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

	// Hằng số mô tả lỗi
	public static final String PRODUCT_ID_INVALID = "Product id không hợp lệ";
	public static final String NAME_EMPTY = "Product name không thể rỗng";
	public static final String COST_PRICE_LOWER_ZERO = "Product costPrice không được bé hơn 0";
	public static final String PRICE_INVALID = "Product price không được bé hơn 0 và bé hơn costPrice";
	public static final String INVENTORY_LOWER_ZERO = "Product inventory không thể bé hơn 0";
	public static final String VAT_LOWER_ZERO = "Product VAT không thể bé hơn 0";
	@Id
	protected String productID;
	protected String name;

	protected Double costPrice;

	protected byte[] image;
	protected Double VAT;
	protected Integer inventory;
	protected Double price;

	@Enumerated(EnumType.ORDINAL)
	private Type type;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ProductPromotionDetail", joinColumns = @JoinColumn(name = "productID"), inverseJoinColumns = @JoinColumn(name = "promotionID"))
	private Set<Promotion> promotions;

	public Product() {
	}

	public Product(String productID) throws Exception {
		setProductID(productID);
	}

	public Product(String productID, String name, Double costPrice, byte[] image, Double VAT, Integer inventory,
			Type type) throws Exception {
		setProductID(productID);
		setName(name);
		setCostPrice(costPrice);
		setImage(image);
		setVAT(VAT);
		setInventory(inventory);
		setType(type);
		setPrice();
	}

	public Product(String productID, String name, Double costPrice, Double price, byte[] image, Double VAT,
			Integer inventory, Type type) throws Exception {
		setProductID(productID);
		setName(name);
		setCostPrice(costPrice);
		setImage(image);
		setVAT(VAT);
		setInventory(inventory);
		setType(type);
		this.price = price;
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
		final Product other = (Product) obj;
		return Objects.equals(this.productID, other.productID);
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public byte[] getImage() {
		return image;
	}

	public Integer getInventory() {
		return inventory;
	}

	public String getName() {
		return name;
	}

	public final Double getPrice() {
		return this.price;
	}

	public String getProductID() {
		return productID;
	}

	public Type getType() {
		return type;
	}

	public Double getVAT() {
		return VAT;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + Objects.hashCode(this.productID);
		return hash;
	}

	public final void setCostPrice(Double costPrice) throws Exception {
		if (costPrice < 0) {
			throw new Exception(COST_PRICE_LOWER_ZERO);
		}
		this.costPrice = costPrice;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}

	public final void setInventory(Integer inventory) throws Exception {
		if (inventory < 0) {
			throw new Exception(INVENTORY_LOWER_ZERO);
		}
		this.inventory = inventory;
	}

	public final void setName(String name) throws Exception {
		if (name.isBlank()) {
			throw new Exception(NAME_EMPTY);
		}

		this.name = name;
	}

	/**
	 * Hàm tính toán giá bán của sản phẩm dựa trên loại sản phẩm. Tỉ lệ được tính
	 * như sau: Sách 1.4; Sách ngoại văn 1.6; Văn phòng phẩm có giá vốn nhỏ hơn
	 * 20,000 1.6; Văn phòng phẩm có giá vốn lớn hơn 20,000: 1.5
	 *
	 */
	private void setPrice() throws Exception {
		Double rate = 1.0;

//      Xác định tỉ lệ lợi nhuận của sản phẩm
		if (this.type == Type.BOOK) {
			Book bookInstance = (Book) this;
			if (bookInstance.getBookOrigin() == BookType.FOREIGN) {
				rate = 1.6;
			} else {
				rate = 1.4;
			}
		} else if (this.type == Type.STATIONERY) {
			if (costPrice < 20000) {
				rate = 1.6;
			} else {
				rate = 1.5;
			}
		}

//        Kiểm tra dữ liệu hợp lệ không
		Double priceWithRate = costPrice * rate;
		Double priceWithVAT = priceWithRate + (priceWithRate * VAT / 100);
		if (priceWithVAT < 0 || priceWithVAT < costPrice) {
			throw new Exception(PRICE_INVALID);
		}
		this.price = priceWithVAT;
	}

	public void setProductID(String productID) throws Exception {
		String regex = "^SP[1-3]\\d{2}\\d\\d{4}$";
		Pattern pattern = Pattern.compile(regex);

		if (!pattern.matcher(productID).matches()) {
			throw new Exception(PRODUCT_ID_INVALID);
		}
		this.productID = productID;
	}

	public final void setType(Type type) {
		this.type = type;
	}

	public final void setVAT(Double VAT) throws Exception {
		if (VAT < 0) {
			throw new Exception(VAT_LOWER_ZERO);
		}
		this.VAT = VAT;
	}

	@Override
	public String toString() {
		return "Product{" + "productID=" + productID + ", name=" + name + ", costPrice=" + costPrice + ", image="
				+ image + ", VAT=" + VAT + ", inventory=" + inventory + ", price=" + price + ", type=" + type + '}';
	}

}
