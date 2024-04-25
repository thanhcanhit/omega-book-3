/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bus.StatementAccounting_BUS;
import dao.AcountingVoucher_DAO;
import dao.Bill_DAO;
import dao.CashCountSheet_DAO;
import dao.Employee_DAO;
import dao.Shift_DAO;
import entity.AcountingVoucher;
import entity.Bill;
import entity.CashCount;
import entity.CashCountSheet;
import entity.Employee;
import raven.toast.Notifications;
import utilities.AcountingVoucherPrinter;

/**
 *
 * @author Hoàng Khang
 */
public class StatementAccounting_BUSImpl extends UnicastRemoteObject implements StatementAccounting_BUS {

	public StatementAccounting_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -8532042707476409897L;
	private AcountingVoucher_DAO acountingVoucher_DAO = new AcountingVoucher_DAO();
	private CashCountSheet_DAO cashCountSheet_DAO = new CashCountSheet_DAO();
	private Employee_DAO employee_DAO = new Employee_DAO();
	private Bill_DAO order_DAO = new Bill_DAO();
	private Shift_DAO shift_DAO = new Shift_DAO();
	@SuppressWarnings("unused")
	private StatementCashCount_BUSImpl statementCashCount_BUS = new StatementCashCount_BUSImpl();

	public AcountingVoucher getAcountingByID(String id) throws RemoteException {
		return acountingVoucher_DAO.getOne(id);
	}

	public AcountingVoucher getLastAcounting(Employee employee) throws RemoteException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String format = dateFormat.format(date);
		String code = "KTO" + format;
		AcountingVoucher acountingVoucherLast;
		String lastID = acountingVoucher_DAO.getMaxSequence(code);
		if (lastID != null) {
			acountingVoucherLast = acountingVoucher_DAO.getOne(acountingVoucher_DAO.getMaxSequence(code));
		} else {
			acountingVoucherLast = null;
		}
		if (acountingVoucherLast == null) {
			date = shift_DAO.getFirstLogin(employee).getStartedAt();
			acountingVoucherLast = new AcountingVoucher(date);
		}
		return acountingVoucherLast;
	}

	/**
	 * Phát sinh mã phiếu kết toán
	 *
	 * @param date
	 * @return
	 */
	public String generateID(Date date) throws RemoteException {
		// Khởi tạo mã phiếu kết toán
		String prefix = "KTO";
		// 8 Kí tự tiếp theo là ngày và giờ bắt đầu kết toán
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String format = dateFormat.format(date);
		prefix += format;
		String maxID = acountingVoucher_DAO.getMaxSequence(prefix);
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

	public void createAcountingVoucher(Employee employee, CashCountSheet cashCountSheet, Date end)
			throws RemoteException {
		Date start = getLastAcounting(employee).getEndedDate();
		ArrayList<Bill> list = (ArrayList<Bill>) getAllOrderInAcounting(start, end, employee.getEmployeeID());
		String id = generateID(end);
		System.out.println("===================================================================");

// Create CashCountSheet first
		cashCountSheet_DAO.create(cashCountSheet);

		AcountingVoucher acountingVoucher = new AcountingVoucher(id, start, end, cashCountSheet, list);
		System.out.println(acountingVoucher);
		acountingVoucher_DAO.create(acountingVoucher);

		for (Bill order : list) {
			order_DAO.updateOrderAcountingVoucher(order, acountingVoucher);
		}
		Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tạo phiếu kết toán thành công");

		generatePDF(acountingVoucher_DAO.getOne(id));
	}

	public Employee getEmployeeByID(String id) throws RemoteException {
		return employee_DAO.getOne(id);
	}

	public List<Bill> getAllOrderInAcounting(Date start, Date end, String empID) throws RemoteException {
		return order_DAO.getOrdersInAccountingVoucher(start, end, empID);
	}

	public double getSale(List<Bill> listOrder) throws RemoteException {
		double sum = 0;
		for (Bill order : listOrder) {
			sum += order.getTotalDue();
		}
		return sum;
	}

	public double getPayViaATM(List<Bill> list) throws RemoteException {
		double sum = 0;
		for (Bill order : list) {
			if (order.isPayment()) {
				sum += order.getTotalDue();
			}
		}
		return sum;
	}

	public double getTotal(List<CashCount> list) throws RemoteException {
		double sum = 0;
		for (CashCount cashCount : list) {
			sum += cashCount.getTotal();
		}
		return sum;
	}

	public void generatePDF(AcountingVoucher acounting) throws RemoteException {
//        tạo file pdf và hiển thị + in file pdf đó
		AcountingVoucherPrinter printer = new AcountingVoucherPrinter(acounting);
		printer.generatePDF();
		AcountingVoucherPrinter.PrintStatus status = printer.printFile();
		if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_FILE) {
			Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
		} else if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_PRINTER) {
			Notifications.getInstance().show(Notifications.Type.ERROR,
					"Lỗi không thể in hóa đơn: Không tìm thấy máy in");
		}
	}

}
