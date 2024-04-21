/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

import bus.ViewCashCountSheetList_BUS;
import dao.CashCountSheet_DAO;
import entity.CashCountSheet;
import utilities.CashCountSheetPrinter;

/**
 *
 * @author Hoàng Khang
 */
public class ViewCashCountSheetList_BUSImpl extends UnicastRemoteObject implements ViewCashCountSheetList_BUS {

    public ViewCashCountSheetList_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 5010136432970475895L;
	private CashCountSheet_DAO cashDAO = new CashCountSheet_DAO();

    public List<CashCountSheet> getAll() throws RemoteException{
        List<CashCountSheet> list = cashDAO.getAll();
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public void GeneratePDF(CashCountSheet cash) throws RemoteException{
        CashCountSheetPrinter printer = new CashCountSheetPrinter(cash);
        printer.generatePDF();

    }

    public CashCountSheet getOne(String id) throws RemoteException{
        return cashDAO.getOne(id);
    }

}
