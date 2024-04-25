/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import dao.Customer_DAO;
import dao.OrderDetail_DAO;
import dao.Bill_DAO;
import dao.ProductPromotionDetail_DAO;
import dao.Product_DAO;
import dao.Promotion_DAO;
import entity.Customer;
import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.PromotionForProduct;
import enums.CustomerRank;
import enums.DiscountType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bus.Sales_BUS;

/**
 *
 * @author thanhcanhit
 */
public class Sales_BUSImpl extends UnicastRemoteObject implements Sales_BUS {
	public Sales_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 463925661216328438L;
	private final Bill_DAO orderDAO = new Bill_DAO();
	private final OrderDetail_DAO orderDetailDAO = new OrderDetail_DAO();
	private final Product_DAO productDAO = new Product_DAO();
	private final Customer_DAO customerDAO = new Customer_DAO();
	private final ProductPromotionDetail_DAO productPromotionDetail_DAO = new ProductPromotionDetail_DAO();
	private final Promotion_DAO promotionDAO = new Promotion_DAO();

	public Product getProduct(String id) throws RemoteException{
		return productDAO.getOne(id);
	}

	public Customer getCustomerByPhone(String phone) throws RemoteException{
		return customerDAO.getByPhone(phone);
	}

	public Bill createNewOrder() throws RemoteException,Exception {
		Bill order = new Bill(orderDAO.generateID());
		order.setStatus(false);
//        Chỉ hiển thị ngày lập, khi lưu sẽ lấy thời gian tại lúc bấm thanh toán
		LocalDate now = LocalDate.now();
		order.setOrderAt(Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return order;
	}

	public synchronized boolean saveToDatabase(Bill order) throws RemoteException{
		for (OrderDetail detail : order.getOrderDetail()) {
			if(productDAO.getOne(detail.getProduct().getProductID()).getInventory()<detail.getQuantity()) {
				return false;
			}
		}
		
		if (!orderDAO.create(order)) {
			return false;
		}

		for (OrderDetail detail : order.getOrderDetail()) {

			if (order.isStatus()) {
//                Giảm số lượng tồn kho nếu trạng thái đã thành công
				decreaseProductInventory(detail.getProduct(), detail.getQuantity());
			}
		}

//        Tăng điểm thành viên nếu không phải là tài khoản mặc định và đã thanh toán
		if (order.isStatus() && !order.getCustomer().getCustomerID().equals("KH000000000")) {
			Long newPoint = Math.round(order.getTotalDue() / 100);
			customerDAO.increatePoint(order.getCustomer().getCustomerID(), newPoint.intValue());
		}

		return true;
	}

	public synchronized boolean updateInDatabase(Bill order) throws RemoteException{
		for (OrderDetail detail : order.getOrderDetail()) {
			if(productDAO.getOne(detail.getProduct().getProductID()).getInventory()<detail.getQuantity()) {
				return false;
			}
		}
		if (!orderDAO.update(order.getOrderID(), order)) {
			return false;
		}

//        Xóa hết detail cũ vì nếu cập nhật từng thành phần sẽ rất mất thời gian (cập nhật, thêm vào detail mới, xóa detail cũ,...)
		if (!orderDetailDAO.delete(order.getOrderID())) {
			return false;
		}

		for (OrderDetail detail : order.getOrderDetail()) {
			if (!orderDetailDAO.create(detail)) {
				return false;
			} else {
//                Giảm số lượng tồn kho nếu trạng thái đã thành công
				if (order.isStatus()) {
					decreaseProductInventory(detail.getProduct(), detail.getQuantity());
				}
			}
		}
		// Tăng điểm thành viên nếu không phải là tài khoản mặc định và đã thanh toán
		if (order.isStatus() && !order.getCustomer().getCustomerID().equals("KH000000000")) {
			Long newPoint = Math.round(order.getTotalDue() / 100);
			customerDAO.increatePoint(order.getCustomer().getCustomerID(), newPoint.intValue());
		}

		return true;
	}

	public boolean decreaseProductInventory(Product product, int quantity) throws RemoteException{
		int newInventory = product.getInventory() - quantity;
		return productDAO.updateInventory(product.getProductID(), newInventory);
	}

	public boolean increaseProductInventory(Product product, int quantity) throws RemoteException{
		int newInventory = product.getInventory() + quantity;
		return productDAO.updateInventory(product.getProductID(), newInventory);
	}

	public ArrayList<ProductPromotionDetail> getListProductPromotionAvailable(String productID) throws RemoteException{
		ArrayList<ProductPromotionDetail> result = new ArrayList<>();

		return result;
	}

//    Handle saved Order
	public ArrayList<Bill> getSavedOrders() throws RemoteException{
		ArrayList<Bill> result = orderDAO.getNotCompleteOrder();

//        Lấy thông tin khách hàng của hóa đơn
		for (Bill item : result) {
			try {
				Customer fullInfoCustomer = customerDAO.getOne(item.getCustomer().getCustomerID());
				item.setCustomer(fullInfoCustomer);

			} catch (Exception ex) {
				Logger.getLogger(Sales_BUSImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}

	public Bill getOrder(String id) throws RemoteException{
		Bill result = orderDAO.getOne(id);
		// System.out.println("LOG" + result);
		try {
//Lấy thông tin khách hàng
			Customer fullInfoCustomer = customerDAO.getOne(result.getCustomer().getCustomerID());
			result.setCustomer(fullInfoCustomer);

//Lấy thông tin sản phẩm
			for (OrderDetail item : result.getOrderDetail()) {
				item.setProduct(productDAO.getOne(item.getProduct().getProductID()));
			}
		} catch (Exception ex) {
			Logger.getLogger(Sales_BUSImpl.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return result;
	}

	public boolean deleteOrder(String id) throws RemoteException{
		return orderDAO.delete(id);
	}

//    Promotion
	public ArrayList<ProductPromotionDetail> getPromotionOfProductAvailable(String productID) throws RemoteException{
		ArrayList<ProductPromotionDetail> result = productPromotionDetail_DAO.getAllForProductAndAvailable(productID);

//        Get full data
		for (ProductPromotionDetail item : result) {
			PromotionForProduct promotionFullData = promotionDAO.getForProduct(item.getPromotion().getPromotionID());
			Product productFullData = productDAO.getOne(item.getProduct().getProductID());
			item.setProduct(productFullData);
			item.setPromotionForProduct(promotionFullData);
		}

		return result;
	}

	public double getBestProductPromotionDiscountAmountAvailable(String productID) throws RemoteException{
		ArrayList<ProductPromotionDetail> promotionList = getPromotionOfProductAvailable(productID);

//        Nếu không có khuyến mãi nào thì trả về null
		if (promotionList.isEmpty()) {
			return 0;
		}

//        Mặc định là thằng đầu tiên
		double bestDiscount = 0;

		for (ProductPromotionDetail item : promotionList){
			DiscountType discountType = item.getPromotion().getTypeDiscount();
			double discountAmount = item.getPromotion().getDiscount();
			double discountValue = discountType == DiscountType.PERCENT
					? discountAmount / 100 * item.getProduct().getPrice()
					: discountAmount;
			if (discountValue > bestDiscount) {
				bestDiscount = discountValue;
			}
		}

		return bestDiscount;
	}

	public ArrayList<Promotion> getPromotionOfOrderAvailable(int customerRank) throws RemoteException{
		return promotionDAO.getPromotionOrderAvailable(CustomerRank.fromInt(customerRank));
	}

	public Promotion getPromotion(String promotionID) throws RemoteException{
		return promotionDAO.getOne(promotionID);
	}

	public int getSavedOrderQuantity() throws RemoteException{
		return orderDAO.getQuantityOrderSaved();
	}
}
