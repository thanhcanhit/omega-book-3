/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

import enums.StationeryType;
import enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author thanhcanhit
 */
@Entity
public final class Stationery extends Product {
	// Hằng báo lỗi
	public static final String COLOR_EMPTY = "Stationery color không được rỗng";
	public static final String WEIGHT_LOWER_ZERO = "Stationery weight không được rỗng";
	public static final String MATERIAL_EMPTY = "Stationery material không được rỗng";
	public static final String ORIGIN_EMPTY = "Stationery origin không được rỗng";
	private String color;
	private Double weight;

	private String material;
	private String origin;
	@Enumerated(EnumType.ORDINAL)
	private StationeryType stationeryType;
	@ManyToOne
	@JoinColumn(name = "brandID")
	private Brand brand;

	public Stationery() {
	}

	public Stationery(String productID) throws Exception {
		super(productID);
	}

	// Không truyền price, dùng khi khởi tạo đối tượng
	public Stationery(String color, Double weight, String material, String origin, StationeryType stationeryType,
			Brand brand, String productID, String name, Double costPrice, byte[] image, Double VAT, Integer inventory,
			Type type) throws Exception {
		super(productID, name, costPrice, image, VAT, inventory, type);
		setColor(color);
		setWeight(weight);
		setMaterial(material);
		setOrigin(origin);
		setStationeryType(stationeryType);
		setBrand(brand);
	}

	public Stationery(String color, Double weight, String material, String origin, StationeryType stationeryType,
			Brand brand, String productID, String name, Double costPrice, Double price, byte[] image, Double VAT,
			Integer inventory, Type type) throws Exception {
		super(productID, name, costPrice, price, image, VAT, inventory, type);
		setColor(color);
		setWeight(weight);
		setMaterial(material);
		setOrigin(origin);
		setStationeryType(stationeryType);
		setBrand(brand);
	}

	public Brand getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public String getMaterial() {
		return material;
	}

	public String getOrigin() {
		return origin;
	}

	public StationeryType getStationeryType() {
		return stationeryType;
	}

	public Double getWeight() {
		return weight;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setColor(String color) throws Exception {
		if (color.isBlank()) {
			throw new Exception(COLOR_EMPTY);
		}
		this.color = color;
	}

	public void setMaterial(String material) throws Exception {
		if (material.isBlank()) {
			throw new Exception(MATERIAL_EMPTY);
		}
		this.material = material;
	}

	public void setOrigin(String origin) throws Exception {
		if (origin == null || origin.isBlank()) {
			throw new Exception(ORIGIN_EMPTY);
		}
		this.origin = origin;
	}

	public void setStationeryType(StationeryType stationeryType) {
		this.stationeryType = stationeryType;
	}

	public void setWeight(Double weight) throws Exception {
		if (weight < 0) {
			throw new Exception(WEIGHT_LOWER_ZERO);
		}
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Stationery{" + "color=" + color + ", weight=" + weight + ", material=" + material + ", origin=" + origin
				+ ", stationeryType=" + stationeryType + ", brand=" + brand + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(brand, color, material, origin, stationeryType, weight);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stationery other = (Stationery) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(color, other.color)
				&& Objects.equals(material, other.material) && Objects.equals(origin, other.origin)
				&& stationeryType == other.stationeryType && Objects.equals(weight, other.weight);
	}

}
