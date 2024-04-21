/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bus.StatisticSales_BUS;
import dao.Bill_DAO;
import dao.Product_DAO;
import dao.PurchaseOrder_DAO;
import dao.ReturnOrder_DAO;
import enums.Type;

/**
 *
 * @author KienTran
 */
public class StatisticSales_BUSImpl extends UnicastRemoteObject implements StatisticSales_BUS{
    public StatisticSales_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 8594566161983607041L;
	private final Bill_DAO orderDAO = new Bill_DAO();
    private final PurchaseOrder_DAO purchaseOrderDAO = new PurchaseOrder_DAO();
    private final ReturnOrder_DAO returnOrderDAO = new ReturnOrder_DAO();
    private final Product_DAO productDAO = new Product_DAO();
    
    public int getTotalNumberOrder(int month, int year) throws RemoteException{
        return orderDAO.getNumberOfOrderInMonth(month, year);
    }
    public int getTotalNumberPurchaseOrder(int month, int year) throws RemoteException{
       
        return purchaseOrderDAO.getNumberOfPurchaseOrderInMonth(month, year);
    }
    public int getTotalNumberReturnOrder(int month, int year) throws RemoteException{
        return returnOrderDAO.getNumberOfReturnOrderInMonth(month, year);
    }
    public double getTotalInMonth(int month, int year) throws RemoteException{
       return (orderDAO.getTotalInMonth(month, year) - returnOrderDAO.getTotalReturnOrderInMonth(month, year));
    }
    public double getTargetInMonth(int month, int year) throws RemoteException{
        double result = orderDAO.getTotalInMonth(month, year);
        return (result * 100 / 50000000 );
    }

    public double[] getTotalPerDay(int month, int year) throws RemoteException{
        return orderDAO.getToTalInMonth(month, year);
        
    }
    public int getQuantityProductType(Type type, int month, int year) throws RemoteException{
        return productDAO.getQuantityProductType(type, month, year);
    }
    
}
