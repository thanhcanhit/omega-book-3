package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import entity.Brand;

public interface BrandManagement_BUS extends Remote{
	public ArrayList<Brand> getALLBrand() throws IOException;
	public Brand getOne(String brandID)throws IOException;
	public ArrayList<Brand> search(String id)throws IOException;
	public void create(String name, String country) throws IOException;
	public void update(Brand brand, String brandID) throws IOException;
}
