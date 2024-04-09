package bus;

import java.util.ArrayList;

import entity.Brand;

public interface BrandManagement_BUS {
	public ArrayList<Brand> getALLBrand();
	public Brand getOne(String brandID);
	public ArrayList<Brand> search(String id);
	public void create(String name, String country) throws Exception;
	public void update(Brand brand, String brandID) throws Exception;
}
