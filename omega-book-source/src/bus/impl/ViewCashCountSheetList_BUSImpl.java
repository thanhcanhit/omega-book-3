/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.util.ArrayList;
import java.util.Collections;

import bus.ViewCashCountSheetList_BUS;
import dao.CashCountSheet_DAO;
import entity.CashCountSheet;
import utilities.CashCountSheetPrinter;

/**
 *
 * @author Hoàng Khang
 */
public class ViewCashCountSheetList_BUSImpl implements ViewCashCountSheetList_BUS {

    private CashCountSheet_DAO cashDAO = new CashCountSheet_DAO();

    public ArrayList<CashCountSheet> getAll() {
        ArrayList<CashCountSheet> list = (ArrayList<CashCountSheet>) cashDAO.getAll();
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public void GeneratePDF(CashCountSheet cash) {
        CashCountSheetPrinter printer = new CashCountSheetPrinter(cash);
        printer.generatePDF();

    }

    public CashCountSheet getOne(String id) {
        return cashDAO.getOne(id);
    }

}
