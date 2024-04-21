/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.Product_DAO;
import entity.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bus.StatisticProduct_BUS;

/**
 *
 * @author KienTran
 */
public class StatisticProduct_BUSImpl extends UnicastRemoteObject implements StatisticProduct_BUS{
    public StatisticProduct_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 2831516427398097475L;
	Product_DAO productDAO = new Product_DAO();
    
    public double getTotalProduct(String productID, String date) throws RemoteException{
        return productDAO.getTotalProduct(productID, date);
    }
    public int getQuantitySale(String productID,String date) throws RemoteException{
        return productDAO.getQuantitySale(productID, date);
    }
    public ArrayList<Product> getTop10Product(String date) throws RemoteException{
        return productDAO.getTop10Product(date);
    }
    public ArrayList<Product> getTopProductInDay(String date) throws RemoteException{
        return productDAO.getTopProductInDay(date);
    }
    public Product getProduct(String ID) throws RemoteException{
        return productDAO.getOne(ID);
    }
}
