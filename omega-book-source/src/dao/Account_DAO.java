/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.ConnectDB;
import entity.Account;
import entity.Employee;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import utilities.AccessDatabase;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanhcanhit
 */
public class Account_DAO implements DAOBase<Account> {
	EntityManager em;

	public Account_DAO() {
		em = AccessDatabase.getEntityManager();
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
	public Boolean create(Account object) {
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();

		return em.find(Account.class, object.getEmployee().getEmployeeID()) != null;
	}

	@Override
	public Boolean update(String id, Account newObject) {
		int n = 0;
		n = em.createNamedQuery("Account.changePassword").setParameter("password", newObject.getPassword())
				.setParameter("employeeID", id).executeUpdate();
		return n > 0;
	}

	public Boolean updatePass(String id, String newPass) {
		int n = 0;
		n = em.createNamedQuery("Account.changePassword").setParameter("password", newPass)
				.setParameter("employeeID", id).executeUpdate();
		return n > 0;
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
