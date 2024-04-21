/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import bus.PromotionManagement_BUS;
import dao.ProductPromotionDetail_DAO;
import dao.Product_DAO;
import dao.Promotion_DAO;
import entity.Product;
import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.PromotionForOrder;
import entity.PromotionForProduct;
import enums.DiscountType;
import enums.PromotionType;

/**
 *
 * @author Như Tâm
 */

public class PromotionManagement_BUSImpl implements PromotionManagement_BUS{
    private Promotion_DAO promotion_DAO = new Promotion_DAO();

    public PromotionManagement_BUSImpl() {
    }
    
    public ArrayList<Promotion> getAllPromotion() throws RemoteException{
        ArrayList<Promotion> promotionList = promotion_DAO.getAll();
        return promotionList;
    }
    public ArrayList<PromotionForOrder> getAllPromotionForOrder()throws RemoteException{
        ArrayList<PromotionForOrder> promotionList = promotion_DAO.getAllForOrder();
        return promotionList;
    }
    public ArrayList<PromotionForProduct> getAllPromotionForProduct() throws RemoteException{
        ArrayList<PromotionForProduct> promotionList = promotion_DAO.getAllForProduct();
        return promotionList;
    }
    
    public Promotion getOne(String promotionID) throws RemoteException{
        return promotion_DAO.getOne(promotionID);
    }
    
    public String generateID(PromotionType promotionType, DiscountType typeDiscount, Date ended) throws RemoteException{
        return promotion_DAO.generateID(promotionType, typeDiscount, ended);
    }
    
//    public boolean createPromotion(Promotion promo) throws Exception {
//        return promotion_DAO.create(promo);
//    }

    public Promotion getPromotion(String promotionID) throws RemoteException{
        return promotion_DAO.getOne(promotionID);

    }

    public ArrayList<PromotionForOrder> searchByIdOrder(String searchQuery) throws RemoteException{
        return promotion_DAO.getForOrder(searchQuery);
    }

//    public ArrayList<Promotion> filter(int type, int status) {
//        return promotion_DAO.filter(type, status);
//    }

//    public boolean addNewPromotion(Promotion newPromotion) {
//        return promotion_DAO.createForProduct(newPromotion);
//    }

//    public boolean removePromotion(String promotionID) {
//        return promotion_DAO.updateDate(promotionID);
//    }

    public Product searchProductById(String searchQuery) throws RemoteException{
        return new Product_DAO().getOne(searchQuery);
    }
//    public ArrayList<Promotion> searchForOrderById(String searchQuery) {
//        return promotion_DAO.findForOrderById(searchQuery);
//    }

    public Product getProduct(String productID) throws RemoteException{
        return new Product_DAO().getOne(productID);
    }

    public void createProductPromotionDetail(PromotionForProduct newPromotion, ArrayList<ProductPromotionDetail> cart)throws RemoteException {
        for (ProductPromotionDetail productPromotionDetail : cart) {
            productPromotionDetail.setPromotionForProduct(newPromotion);
            new ProductPromotionDetail_DAO().create(productPromotionDetail);
        }
    }

    public boolean removeProductPromotionDetail(String promotionID) throws RemoteException{
        return new ProductPromotionDetail_DAO().delete(promotionID);
    }

    public boolean removeProductPromotionOther(Promotion pm) throws RemoteException{
        return promotion_DAO.updateDateStart(pm);
    }

    public boolean removeOrderPromotionOther(Promotion pm) throws RemoteException{
        return promotion_DAO.updateDateStart(pm);
    }

    public boolean addNewOrderPromotion(PromotionForOrder newPromotion) throws RemoteException{
        return promotion_DAO.createForOrder(newPromotion);
    }

//    public ArrayList<Promotion> filterForProduct(int type, int status) {
//        return promotion_DAO.filterForProduct(type, status);
//    }
//
//    public ArrayList<Promotion> filterForOrder(int type, int status) {
//        return promotion_DAO.filterForOrder(type, status);
//    }

    public Product getOneProduct(String productID) throws RemoteException{
        return new Product_DAO().getOne(productID);
    }

	@Override
	public ArrayList<Promotion> filter(int type, int status) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewPromotion(Promotion newPromotion) throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePromotion(String promotionID) throws RemoteException{
		return promotion_DAO.update(promotionID);
	}

	public ArrayList<PromotionForOrder> searchForOrderById(String searchQuery) throws RemoteException{
		return promotion_DAO.getForOrder(searchQuery);
	}

	@Override
	public ArrayList<PromotionForProduct> filterForProduct(int type, int status) throws RemoteException{
		return promotion_DAO.filterForProduct(type, status);
	}

	@Override
	public ArrayList<PromotionForOrder> filterForOrder(int type, int status) throws RemoteException{
		return promotion_DAO.filterForOrder(type, status);
	}

	public PromotionForOrder getPromotionForOrder(String promotionID) throws RemoteException{
		return promotion_DAO.getOneForOrder(promotionID);
	}

	@Override
	public ArrayList<Promotion> searchById(String searchQuery) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	public PromotionForProduct getPromotionForProduct(String promotionID) throws RemoteException{
		return promotion_DAO.getForProduct(promotionID);
	}

	@Override
	public void createProductPromotionDetail(Promotion newPromotion, ArrayList<ProductPromotionDetail> cart) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	public boolean addNewPromotionForProduct(PromotionForProduct newPromotion) throws RemoteException {
		return promotion_DAO.createForProduct(newPromotion);
	}


    
}
