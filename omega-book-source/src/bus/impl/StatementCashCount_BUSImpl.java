/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bus.StatementCashCount_BUS;
import dao.CashCountSheetDetail_DAO;
import dao.CashCountSheet_DAO;
import dao.CashCount_DAO;
import dao.Employee_DAO;
import entity.CashCount;
import entity.CashCountSheet;
import entity.CashCountSheetDetail;
import entity.Employee;
import utilities.CashCountSheetPrinter;

/**
 *
 * @author Hoàng Khang
 */
public class StatementCashCount_BUSImpl extends UnicastRemoteObject implements StatementCashCount_BUS {

    public StatementCashCount_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -7471920136342737504L;
	private Employee_DAO employee_DAO = new Employee_DAO();
    @SuppressWarnings("unused")
	private CashCount_DAO cashCount_DAO = new CashCount_DAO();
    private CashCountSheet_DAO cashCountSheet_DAO = new CashCountSheet_DAO();
    @SuppressWarnings("unused")
	private CashCountSheetDetail_DAO cashCountSheetDetail_DAO;

    public Employee getEmployeeByID(String id) throws RemoteException{
        return employee_DAO.getOne(id);
    }

    public CashCountSheet getOne(String id) throws RemoteException{
        return cashCountSheet_DAO.getOne(id);
    }

    public void createCashCountSheet(ArrayList<CashCount> cashCountList, ArrayList<Employee> employees, Date start) throws RemoteException{
        Date end = new Date();
        CashCountSheet cashCountSheet = new CashCountSheet(generateID(start));
        //Set danh sách CashCount
        cashCountSheet.setCashCountList(cashCountList);
        //Khởi tạo CashCountSheetDetal
        ArrayList<CashCountSheetDetail> cashCountSheetDetails = new ArrayList<>();
        cashCountSheetDetails.add(new CashCountSheetDetail(true, employees.get(0), cashCountSheet));
        cashCountSheetDetails.add(new CashCountSheetDetail(false, employees.get(1), cashCountSheet));

        cashCountSheet.setCashCountSheetDetailList(cashCountSheetDetails);
        cashCountSheet.setCreatedDate(start);
        cashCountSheet.setEndedDate(end);


        cashCountSheet_DAO.create(cashCountSheet);
        GeneratePDF(cashCountSheet);

    }

    public String generateID(Date date) throws RemoteException{
        //Khởi tạo mã Khách hàng KH
        String prefix = "KTI";
        //8 Kí tự tiếp theo là ngày và giờ bắt đầu kiểm tiền
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String format = dateFormat.format(date);
        prefix += format;
        String maxID = cashCountSheet_DAO.getMaxSequence(prefix);
        if (maxID == null) {
            prefix += "0000";
        } else {
            String lastFourChars = maxID.substring(maxID.length() - 4);
            int num = Integer.parseInt(lastFourChars);
            num++;
            prefix += String.format("%04d", num);
        }
        return prefix;
    }

    public void GeneratePDF(CashCountSheet cash) throws RemoteException{
        CashCountSheetPrinter printer = new CashCountSheetPrinter(cash);
        printer.generatePDF();

    }
    
      public double getTotal(ArrayList<CashCount> list) throws RemoteException{
        double sum = 0;
        for (CashCount cashCount : list) {
            sum += cashCount.getTotal();
        }
        return sum;
    }
}
