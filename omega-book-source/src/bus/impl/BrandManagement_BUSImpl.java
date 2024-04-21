/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bus.BrandManagement_BUS;
import dao.Brand_DAO;
import entity.Brand;

/**
 *
 * @author Như Tâm
 */
public class BrandManagement_BUSImpl extends UnicastRemoteObject implements BrandManagement_BUS{
	
    protected BrandManagement_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 818984963360983464L;
	
	
	private Brand_DAO brand_DAO = new Brand_DAO();
    
    public ArrayList<Brand> getALLBrand(){
        ArrayList<Brand> brandList = new Brand_DAO().getAll();
        return brandList;
    }
    
    public Brand getOne(String brandID) {
        return brand_DAO.getOne(brandID);
    }
    
    public ArrayList<Brand> search(String id){
            ArrayList<Brand> list = new ArrayList<>();
            for(Brand brand:brand_DAO.getAll()){
                if(brand.getBrandID().equals(id)){
                    list.add(brand);
                }
            }
            return list;
        }
       
    public void create(String name, String country) throws Exception {
        Brand brand = new Brand(brand_DAO.generateID(), name, country);
        brand_DAO.create(brand);
    }
    
    public void update(Brand brand, String brandID) throws Exception {
        brand_DAO.update(brandID, brand);
    }
}
