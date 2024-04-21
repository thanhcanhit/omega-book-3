/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.Supplier_DAO;
import entity.Supplier;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import bus.SupplierManagement_BUS;

/**
 *
 * @author KienTran
 */
public class SupplierManagement_BUSImpl extends UnicastRemoteObject implements SupplierManagement_BUS{
        public SupplierManagement_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
		private static final long serialVersionUID = 7425108793747931689L;
		private Supplier_DAO supplierDAO = new Supplier_DAO();
        
        public List<Supplier> getAll() throws RemoteException{
            return supplierDAO.getAll();
        }
        public boolean update(String id, Supplier supplier) throws RemoteException{
            return supplierDAO.update(id, supplier);
        }
        public boolean create(String Name, String address) throws RemoteException,Exception{
            String id = supplierDAO.generateID();
            Supplier supplier = new Supplier(id, Name, address);
            return supplierDAO.create(supplier);
        }
        public ArrayList<Supplier> search(String id) throws RemoteException{
            ArrayList<Supplier> list = new ArrayList<>();
            for(Supplier supplier:supplierDAO.getAll()){
                if(supplier.getSupplierID().equals(id)){
                    list.add(supplier);
                }
            }
            return list;
        }
}
