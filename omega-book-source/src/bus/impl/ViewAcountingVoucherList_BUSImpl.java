/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.AcountingVoucher_DAO;
import dao.CashCountSheet_DAO;
import entity.AcountingVoucher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import bus.ViewAccoutingVoucherList_BUS;

/**
 *
 * @author Hoàng Khang
 */
public class ViewAcountingVoucherList_BUSImpl extends UnicastRemoteObject implements ViewAccoutingVoucherList_BUS{

    public ViewAcountingVoucherList_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 7530733092222558597L;
	private AcountingVoucher_DAO acountingVoucher_DAO = new AcountingVoucher_DAO();
    private CashCountSheet_DAO cashCountSheet_DAO = new CashCountSheet_DAO();

    public AcountingVoucher getOne(String acountingVoucherID) throws RemoteException{
        return acountingVoucher_DAO.getOne(acountingVoucherID);
    }

    public ArrayList<AcountingVoucher> getAll() throws RemoteException{
        ArrayList<AcountingVoucher> list = new ArrayList<>();
        for (AcountingVoucher acountingVoucher : acountingVoucher_DAO.getAll()) {
            acountingVoucher.setCashCountSheet(cashCountSheet_DAO.getOne(acountingVoucher.getCashCountSheet().getCashCountSheetID()));
            list.add(acountingVoucher);
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<AcountingVoucher> getByDate(Date start, Date end) throws RemoteException{
        ArrayList<AcountingVoucher> list = getAll();
        return list;
    }
}
