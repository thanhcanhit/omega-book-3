package server;

import java.rmi.RemoteException;
import java.rmi.registry.*;

import javax.naming.*;

import bus.*;
import bus.impl.*;
import utilities.RMIService;

public class Server {
	public static void main(String[] args) throws NamingException, RemoteException {
		Context context = new InitialContext();
		BillManagement_BUS billBus = new BillManagement_BUSImpl();
		BrandManagement_BUS brandBus = new BrandManagement_BUSImpl();
		CreatePurchaseOrder_BUS createPurchaseOrderBus = new CreatePurchaseOrder_BUSImpl();
		CustomerManagement_BUS customerBus = new CustomerManagement_BUSImpl();
		EmployeeManagement_BUS employeeBus = new EmployeeManagement_BUSImpl();
		Login_BUS loginBus = new Login_BUSImpl();
		ProductManagement_BUS productBus = new ProductManagement_BUSImpl();
		PromotionManagement_BUS promotionBus = new PromotionManagement_BUSImpl();
		PurchaseOrderManagement_BUS purchaseOrderBus = new PurchaseOrderManagement_BUSImpl();
		ReturnOrderManagement_BUS returnOrderBus = new ReturnOrderManagement_BUSImpl();
		Sales_BUS salesBus = new Sales_BUSImpl();
		ShiftsManagement_BUS shiftBus = new ShiftsManagement_BUSImpl();
		StatementAccounting_BUS statementAccountingBus = new StatementAccounting_BUSImpl();
		StatementCashCount_BUS statementCashCountBus = new StatementCashCount_BUSImpl();
		StatisticCustomer_BUS statisticCustomerBus = new StatisticCustomer_BUSImpl();
		StatisticProduct_BUS statisticProductBus = new StatisticProduct_BUSImpl();
		StatisticSales_BUS statisticSalesBus = new StatisticSales_BUSImpl();
		SupplierManagement_BUS supplierBus = new SupplierManagement_BUSImpl();
		ViewAccoutingVoucherList_BUS viewAccountingVoucherListBus = new ViewAcountingVoucherList_BUSImpl();
		ViewCashCountSheetList_BUS viewCashCountSheetListBus = new ViewCashCountSheetList_BUSImpl();

		LocateRegistry.createRegistry(7878);
		RMIService.setPU(7878, "rmi://localhost:");
		context.rebind(RMIService.billBus, billBus);
		context.rebind(RMIService.brandBus, brandBus);
		context.rebind(RMIService.createPurchaseOrderBus, createPurchaseOrderBus);
		context.rebind(RMIService.employeeBus, customerBus);
		context.rebind(RMIService.employeeBus, employeeBus);
		context.rebind(RMIService.loginBus, loginBus);
		context.rebind(RMIService.productBus, productBus);
		context.rebind(RMIService.promotionBus, promotionBus);
		context.rebind(RMIService.purchaseOrderBus, purchaseOrderBus);
		context.rebind(RMIService.returnOrderBus, returnOrderBus);
		context.rebind(RMIService.salesBus, salesBus);
		context.rebind(RMIService.shiftBus, shiftBus);
		context.rebind(RMIService.statementAccountingBus, statementAccountingBus);
		context.rebind(RMIService.statementCashCountBus, statementCashCountBus);
		context.rebind(RMIService.statisticCustomerBus, statisticCustomerBus);
		context.rebind(RMIService.statisticProductBus, statisticProductBus);
		context.rebind(RMIService.statisticSalesBus, statisticSalesBus);
		context.rebind(RMIService.supplierBus, supplierBus);
		context.rebind(RMIService.viewAccountingVoucherListBus, viewAccountingVoucherListBus);
		context.rebind(RMIService.viewCashCountSheetListBus, viewCashCountSheetListBus);

		System.out.println("Server is running...");

		/*
		 * context.bind("rmi://localhost:7878/BillManagement_BUS", billBus);
		 * context.bind("rmi://localhost:7878/BrandManagement_BUS", brandBus);
		 * context.bind("rmi://localhost:7878/CreatePurchaseOrder_BUS",
		 * createPurchaseOrderBus);
		 * context.bind("rmi://localhost:7878/EmployeeManagement_BUS", employeeBus);
		 * context.bind("rmi://localhost:7878/Login_BUS", loginBus);
		 * context.bind("rmi://localhost:7878/ProductManagement_BUS", productBus);
		 * context.bind("rmi://localhost:7878/PromotionManagement_BUS", promotionBus);
		 * context.bind("rmi://localhost:7878/PurchaseOrderManagement_BUS",
		 * purchaseOrderBus);
		 * context.bind("rmi://localhost:7878/ReturnOrderManagement_BUS",
		 * returnOrderBus); context.bind("rmi://localhost:7878/Sales_BUS", salesBus);
		 * context.bind("rmi://localhost:7878/ShiftsManagement_BUS", shiftBus);
		 * context.bind("rmi://localhost:7878/StatementAccounting_BUS",
		 * statementAccountingBus);
		 * context.bind("rmi://localhost:7878/StatementCashCount_BUS",
		 * statementCashCountBus);
		 * context.bind("rmi://localhost:7878/StatisticCustomer_BUS",
		 * statisticCustomerBus);
		 * context.bind("rmi://localhost:7878/StatisticSales_BUS", statisticSalesBus);
		 * context.bind("rmi://localhost:7878/SupplierManagement_BUS", supplierBus);
		 * context.bind("rmi://localhost:7878/ViewAccoutingVoucherList_BUS",
		 * viewAccountingVoucherListBus);
		 * context.bind("rmi://localhost:7878/ViewCashCountSheetList_BUS",
		 * viewCashCountSheetListBus);
		 */

	}
}
