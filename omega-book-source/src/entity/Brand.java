/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;
import java.util.regex.Pattern;
import jakarta.persistence.*;

/**
 *
 * @author thanhcanhit
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Brand.findByBrandID", query = "SELECT b FROM Brand b WHERE b.brandID = :brandID"),
	@NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b") 
})
public final class Brand {

	// Hằng báo lỗi
	public static final String BRAND_ID_INVALID = "Brand id không hợp lệ";
	public static final String NAME_EMPTY = "Brand name không được rỗng";
	public static final String COUNTRY_EMPTY = "Brand country không được rỗng";

	@Id
	private String brandID;
	@Column(columnDefinition = "nvarchar(max)")
	private String name;
	@Column(columnDefinition = "nvarchar(max)")
	private String country;

	public Brand() {
	}

	public Brand(String brandID) {
		this.brandID = brandID;
	}

	public Brand(String brandID, String name, String country) throws Exception {
		setBrandID(brandID);
		setName(name);
		setCountry(country);
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
		final Brand other = (Brand) obj;
		return Objects.equals(this.brandID, other.brandID);
	}

	public String getBrandID() {
		return brandID;
	}

	public String getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + Objects.hashCode(this.brandID);
		return hash;
	}

	public void setBrandID(String brandID) throws Exception {
		String regex = "^TH[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);

		if (!pattern.matcher(brandID).matches()) {
			throw new Exception(BRAND_ID_INVALID);
		}
		this.brandID = brandID;
	}

	public void setCountry(String country) throws Exception {
		if (country.isBlank()) {
			throw new Exception(COUNTRY_EMPTY);
		}
		this.country = country;
	}

	public void setName(String name) throws Exception {
		if (name.isBlank()) {
			throw new Exception(NAME_EMPTY);
		}
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("(%s) %s", brandID, name);
	}

}
