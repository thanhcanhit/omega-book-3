/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

import entity.Account;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import utilities.AccessDatabase;

/**
 *
 * @author thanhcanhit
 */
public class Account_DAO implements DAOBase<Account> {
	EntityManager em;

	public Account_DAO() {
		em = AccessDatabase.getInstance();
	}

	@Override
	public Account getOne(String id) {
		return em.find(Account.class, id);
	}

	public boolean validateAccount(String id, String password) {
		Account result = em.createNamedQuery("Account.validate", Account.class).setParameter("employeeID", id)
				.setParameter("password", password).getSingleResult();
		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Account> getAll() {
		throw new UnsupportedOperationException("Not szggupported yet."); // Generated from
																			// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public synchronized Boolean create(Account object) {
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();

		return em.find(Account.class, object.getEmployee().getEmployeeID()) != null;
	}

	@Override
	public synchronized Boolean update(String id, Account newObject) {
		int n = 0;
		n = em.createNamedQuery("Account.changePassword").setParameter("password", newObject.getPassword())
				.setParameter("employeeID", id).executeUpdate();
		return n > 0;
	}

	public synchronized Boolean updatePass(String id, String newPass) {
		int n = 0;
		n = em.createNamedQuery("Account.changePassword").setParameter("password", newPass)
				.setParameter("employeeID", id).executeUpdate();
		return n > 0;
	}

	@Override
	public synchronized Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
