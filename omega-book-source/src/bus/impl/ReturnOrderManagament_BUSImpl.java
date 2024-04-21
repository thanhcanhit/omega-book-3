package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import bus.ReturnOrderManagement_BUS;
import dao.OrderDetail_DAO;
import dao.Bill_DAO;
import dao.Product_DAO;
import dao.ReturnOrderDetail_DAO;
import dao.ReturnOrder_DAO;
import entity.Bill;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;

/**
 *
 * @author Như Tâm
 */
public class ReturnOrderManagament_BUSImpl extends UnicastRemoteObject implements ReturnOrderManagement_BUS{
    public ReturnOrderManagament_BUSImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3968029429425643254L;
	private ReturnOrder_DAO dao = new ReturnOrder_DAO();
    private ReturnOrderDetail_DAO detail_dao = new ReturnOrderDetail_DAO();
    private Bill_DAO order_dao = new Bill_DAO();

    public Bill getOrder(String orderID) throws RemoteException{
        return order_dao.getOne(orderID);
    }

    public ReturnOrder getReturnOrder(String returnOrderID) throws RemoteException {
        return dao.getOne(returnOrderID);
    }
    public ArrayList<ReturnOrder> getAllReturnOrder() throws RemoteException{
        return dao.getAll();
    }

    public ArrayList<ReturnOrderDetail> getAllReturnOrderDetail(String returnOrderID) throws RemoteException{
        return detail_dao.getAllForOrderReturnID(returnOrderID);
    }

    public boolean updateReturnOder(ReturnOrder newReturnOrder) throws RemoteException{
        return dao.update(newReturnOrder.getReturnOrderID(), newReturnOrder);
    }

    public ArrayList<ReturnOrder> searchById(String returnOrderID) throws RemoteException{
        return dao.findById(returnOrderID);
    }
//
//    public ArrayList<ReturnOrder> filter(int type, int status) {
//        return dao.filter(type, status);
//    }

    public ArrayList<OrderDetail> getAllOrderDetail(String orderID) throws RemoteException{
        return new OrderDetail_DAO().getAll(orderID);
    }
    public ArrayList<Bill> getAllOrder() throws RemoteException{
        return new Bill_DAO().getAll();
    }

    public String getNameProduct(String productID) throws RemoteException{
        return new Product_DAO().getOne(productID).getName();
    }
    
    public String generateID(Date returnDate) throws RemoteException{
    	return dao.generateID(returnDate);
    }

    public boolean createNew(ReturnOrder newReturnOrder) throws RemoteException{
        return dao.create(newReturnOrder);
    }

    public Bill searchByOrderId(String orderID) throws RemoteException{
        return new Bill_DAO().getOne(orderID);
    }

    public Product getProduct(String productID) throws RemoteException{
        return new Product_DAO().getOne(productID);
    }

    public boolean createReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> cart) throws RemoteException{
    	boolean rs = false;
        for (ReturnOrderDetail returnOrderDetail : cart) {
            try {
                returnOrderDetail.setReturnOrder(newReturnOrder);
                if( detail_dao.create(returnOrderDetail))
                	rs = true;
				else
					rs = false;
               
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rs;
    }

    public void updateReturnOrderDetail(ReturnOrder newReturnOrder, ArrayList<ReturnOrderDetail> listDetail) throws RemoteException{
        if(newReturnOrder.getStatus().getValue() == 1) {
            if(!newReturnOrder.isType()) {
                for (ReturnOrderDetail returnOrderDetail : listDetail) {
                    detail_dao.updateProduct(returnOrderDetail.getProduct().getProductID(), returnOrderDetail.getQuantity());
                }
            }
            else {
                newReturnOrder.setRefund();
            }
            
        }
        
    }

    public boolean isExist(Bill order) throws RemoteException{
        return dao.getOneForOrderID(order.getOrderID()) != null;
    }

    public Promotion getDiscount(String orderID)throws RemoteException {
        return order_dao.getDiscount(orderID);
    }

	@Override
	public ArrayList<ReturnOrder> filter(int type, int status) throws RemoteException{
		return dao.filter(type, status);
	}
}