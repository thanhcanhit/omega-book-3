package bus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Brand;

public interface BrandManagement_BUS extends Remote{
	public ArrayList<Brand> getALLBrand() throws RemoteException;
	public Brand getOne(String brandID)throws RemoteException;
	public ArrayList<Brand> search(String id)throws RemoteException;
	public void create(String name, String country) throws Exception;
	public void update(Brand brand, String brandID) throws Exception;
}
