package utilities;

public class RMIService {
	public static int port = 7878;
	public static String url = "rmi://localhost:";

	public static void setPU(int port, String url) {
		RMIService.port = port;
		RMIService.url = url;
	}
	
	public static String billBus = url + port + "/BillManagement_BUS";
	public static String brandBus = url + port + "/BrandManagement_BUS";
	public static String createPurchaseOrderBus = url + port + "/CreatePurchaseOrder_BUS";
	public static String customerBus = url + port + "/CustomerManagement_BUS";
	public static String employeeBus = url + port + "/EmployeeManagement_BUS";
	public static String loginBus = url + port + "/Login_BUS";
	public static String productBus = url + port + "/ProductManagement_BUS";
	public static String promotionBus = url + port + "/PromotionManagement_BUS";
	public static String purchaseOrderBus = url + port + "/PurchaseOrderManagement_BUS";
	public static String returnOrderBus = url + port + "/ReturnOrderManagement_BUS";
	public static String salesBus = url + port + "/Sales_BUS";
	public static String shiftBus = url + port + "/ShiftsManagement_BUS";
	public static String statementAccountingBus = url + port + "/StatementAccounting_BUS";
	public static String statementCashCountBus = url + port + "/StatementCashCount_BUS";
	public static String statisticCustomerBus = url + port + "/StatisticCustomer_BUS";
	public static String statisticProductBus = url + port + "/StatisticProduct_BUS";
	public static String statisticSalesBus = url + port + "/StatisticSales_BUS";
	public static String supplierBus = url + port + "/SupplierManagement_BUS";
	public static String viewAccountingVoucherListBus = url + port + "/ViewAccoutingVoucherList_BUS";
	public static String viewCashCountSheetListBus = url + port + "/ViewCashCountSheetList_BUS";
	

}
