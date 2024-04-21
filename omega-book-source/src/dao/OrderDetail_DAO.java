/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import entity.OrderDetail;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author KienTran
 */
public class OrderDetail_DAO implements DAOBase<OrderDetail> {
	EntityManager em;

	public OrderDetail_DAO() {
		em = AccessDatabase.getInstance();
	}

	@Override
	public OrderDetail getOne(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public ArrayList<OrderDetail> getAll() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public ArrayList<OrderDetail> getAll(String id) {
		ArrayList<OrderDetail> result = new ArrayList<>();

		try {
			String hql = "from OrderDetail where order.id = :id";
			TypedQuery<OrderDetail> query = em.createQuery(hql, OrderDetail.class);
			query.setParameter("id", id);

			List<OrderDetail> orderDetails = query.getResultList();

			for (OrderDetail orderDetail : orderDetails) {
				if (orderDetail != null) {
					result.add(orderDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean create(OrderDetail object) {
		int n = 0;
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		return n > 0;
	}

	@Override
	public Boolean update(String id, OrderDetail newObject) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Boolean delete(String id) {
		int n = 0;
		em.getTransaction().begin();
		OrderDetail orderDetail = em.find(OrderDetail.class, id);
		em.remove(orderDetail);
		em.getTransaction().commit();
		return n > 0;
	}

}
