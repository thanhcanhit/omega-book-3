/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.Shift_DAO;
import entity.Shift;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import bus.ShiftsManagement_BUS;

/**
 *
 * @author Hoàng Khang
 */
public class ShiftsManagement_BUSImpl extends UnicastRemoteObject implements ShiftsManagement_BUS{

    public ShiftsManagement_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -5094553891891076328L;
	private Shift_DAO shift_DAO = new Shift_DAO();

    public Shift getOne(String id) throws RemoteException{
        return shift_DAO.getOne(id);
    }

    public ArrayList<Shift> getAll() throws RemoteException{
        return shift_DAO.getAll();
    }

    public boolean createShifts(Shift shift) throws RemoteException{
        return shift_DAO.create(shift);
    }

    public String renderID() throws RemoteException{
        return shift_DAO.generateID();
    }

    public ArrayList<Shift> getShiftsByDate(Date date) throws RemoteException{
        return shift_DAO.getShiftsByDate(date);
    }

    public ArrayList<Shift> filter(String emloyeeID, String role, Date date) throws RemoteException{
        ArrayList<Shift> list = shift_DAO.getShiftsByDate(date);
        ArrayList<Shift> listRemove = new ArrayList<>();

        if (!emloyeeID.equals("")) {
            for (Shift shift : list) {
                if (!shift.getAccount().getEmployee().getEmployeeID().equals(emloyeeID)) {
                    listRemove.add(shift);
                }
                list.removeAll(listRemove);
                listRemove.clear();
            }
        }


        if (!role.equals("Tất Cả")) {
            for (Shift shift : list) {
                if (!shift.getAccount().getEmployee().getRole().equals(role)) {
                    listRemove.add(shift);
                }
            }
            list.removeAll(listRemove);
        }

        return list;
    }
}
