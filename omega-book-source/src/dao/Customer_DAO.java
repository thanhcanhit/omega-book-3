/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoàng Khang
 */
public class Customer_DAO implements interfaces.DAOBase<Customer> {

	EntityManager entityManager;

	public Customer_DAO() {
		entityManager = AccessDatabase.getInstance();
	}

	@Override
	public Customer getOne(String customerID) {
		Customer customer = null;
		try {
			String hql = "FROM Customer WHERE customerID = :customerID";
			Query query = entityManager.createQuery(hql);
			query.setParameter("customerID", customerID);

			customer = (Customer) query.getSingleResult();
			// System.out.println("Khách hàng: " + customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Customer getByPhone(String phone) {
		Customer customer = null;
		try {
			String hql = "FROM Customer WHERE phoneNumber = :phone";
			Query query = entityManager.createQuery(hql);
			query.setParameter("phone", phone);

			customer = (Customer) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Customer getOneByNumberPhone(String phoneNumber) {
		Customer customer = null;
		try {
			String hql = "FROM Customer WHERE phoneNumber = :phoneNumber";
			Query query = entityManager.createQuery(hql);
			query.setParameter("phoneNumber", phoneNumber);

			customer = (Customer) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

    @SuppressWarnings("unchecked")
	@Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String hql = "FROM Customer";
            Query query = entityManager.createQuery(hql);
            customers = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public String getMaxSequence(String code) {
		try {
			code += "%";
			String hql = "SELECT customerID FROM Customer WHERE customerID LIKE :code ORDER BY customerID DESC";
			Query query = entityManager.createQuery(hql);
			query.setParameter("code", code);
			query.setMaxResults(1);
			String customerID = (String) query.getSingleResult();
			return customerID;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean create(Customer object) {
		try {
			String phoneCheck = "FROM Customer WHERE phoneNumber = :phoneNumber";
			TypedQuery<Customer> phoneQuery = entityManager.createQuery(phoneCheck, Customer.class);
			phoneQuery.setParameter("phoneNumber", object.getPhoneNumber());
			List<Customer> customers = phoneQuery.getResultList();
			if (!customers.isEmpty()) {
				return false;
			}

			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			return false;
		}
	}

	@Override
	public Boolean update(String id, Customer newObject) {
		try {
			String hql = "UPDATE Customer SET name = :name, dateOfBirth = :dob, gender = :gender, phoneNumber = :phone, score = :score, address = :address "
					+ "WHERE customerID = :id";
			Query query = entityManager.createQuery(hql);

			query.setParameter("name", newObject.getName());
			query.setParameter("dob", newObject.getDateOfBirth());
			query.setParameter("gender", newObject.isGender());
			query.setParameter("phone", newObject.getPhoneNumber());
			query.setParameter("score", newObject.getScore());
			query.setParameter("address", newObject.getAddress());
			query.setParameter("id", id);

			entityManager.getTransaction().begin();
			int rowsAffected = query.executeUpdate();
			entityManager.getTransaction().commit();

			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			return false;
		}
	}

//    Tăng điểm thành viên
	public boolean increatePoint(String customerID, int pointAddAmount) {
		try {
			String hql = "UPDATE Customer SET score = score + :pointAddAmount WHERE customerID = :customerID";
			Query query = entityManager.createQuery(hql);

			query.setParameter("pointAddAmount", pointAddAmount);
			query.setParameter("customerID", customerID);

			entityManager.getTransaction().begin();
			int rowsAffected = query.executeUpdate();
			entityManager.getTransaction().commit();

			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			return false;
		}
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public int countMaleCustomers() {
		int count = 0;
		try {
			String hql = "SELECT COUNT(c) FROM Customer c WHERE c.gender = true";
			Query query = entityManager.createQuery(hql);
			Long result = (Long) query.getSingleResult();
			count = result != null ? result.intValue() : 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
