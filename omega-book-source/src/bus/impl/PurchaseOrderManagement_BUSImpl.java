/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.Employee_DAO;
import dao.Product_DAO;
import dao.PurchaseOrderDetail_DAO;
import dao.PurchaseOrder_DAO;
import dao.Supplier_DAO;
import entity.Employee;
import entity.Product;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import entity.Supplier;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bus.PurchaseOrderManagement_BUS;

/**
 *
 * @author KienTran
 */
public class PurchaseOrderManagement_BUSImpl extends UnicastRemoteObject implements PurchaseOrderManagement_BUS{
    public PurchaseOrderManagement_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4098337098271224574L;
	private final PurchaseOrder_DAO purchaseOrderDAO = new PurchaseOrder_DAO();
    private final PurchaseOrderDetail_DAO orderDetailDAO = new PurchaseOrderDetail_DAO();
 
    private final Product_DAO productDAO = new Product_DAO();
    private final Employee_DAO employeeDAO = new Employee_DAO();
    private final Supplier_DAO supplierDAO = new Supplier_DAO();



    public Employee getEmployee(String emplpyeeID) {
        return employeeDAO.getOne(emplpyeeID);
    }
    
    public Supplier getSupplier(String supplierID) {
        return supplierDAO.getOne(supplierID);
    }


    public Product getProduct(String productID) {
        return productDAO.getOne(productID);
    }

    public PurchaseOrder getPurchaseOrder(String ID) throws Exception {
        PurchaseOrder purchaseOrder = purchaseOrderDAO.getOne(ID);
        Supplier supplier = supplierDAO.getOne(purchaseOrder.getSupplier().getSupplierID());
        purchaseOrder.setSupplier(supplier);
        Employee employee = employeeDAO.getOne(purchaseOrder.getEmployee().getEmployeeID());
        purchaseOrder.setEmployee(employee);
        
        return purchaseOrder;
    }
    public ArrayList<PurchaseOrder> getAll() throws RemoteException{
        return purchaseOrderDAO.getAll();
    }

    public ArrayList<PurchaseOrderDetail> getPurchaseOrderDetailList(String purchaseOrderID) throws RemoteException{

        return orderDetailDAO.getAll(purchaseOrderID);
    }
    public Boolean updateStatus(String id, int status) throws RemoteException{
        return purchaseOrderDAO.updateStatus(id, status);
    }
}
