/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

import entity.Account;
import entity.Employee;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author thanhcanhit
 */
public class Employee_DAO implements DAOBase<Employee> {
	public EntityManager em;

	public Employee_DAO() {
		em = AccessDatabase.getInstance();
	}

	public static String getMaxSequence(String prefix) {
		try {
			prefix += "%";
			String hql = "FROM Employee WHERE employeeID LIKE :prefix ORDER BY employeeID DESC";
			Query query = AccessDatabase.getInstance().createQuery(hql);
			query.setParameter("prefix", prefix);
			query.setMaxResults(1);
			Employee result = (Employee) query.getSingleResult();
			if (result != null) {
				return result.getEmployeeID();
			}
		} catch (NoResultException e) {
			// Không tìm thấy kết quả nào phù hợp
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getOne(String id) {
		return em.find(Employee.class, id);
	}

	@Override
	public ArrayList<Employee> getAll() {
		ArrayList<Employee> result = new ArrayList<>();

		em.createNamedQuery("Employee.getAll", Employee.class).getResultList().forEach((employee) -> {
			result.add(employee);
		});

		return result;
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public synchronized Boolean create(Employee object) {
		em.getTransaction().begin();

		em.persist(object);

		em.getTransaction().commit();

		return em.find(Employee.class, object.getEmployeeID()) != null;
	}

	/**
	 * UPDATE Employee SET citizenIdentification = :citizenIdentification, role =
	 * :role, status = :status, name = :name, phoneNumber = :phoneNumber, gender =
	 * :gender, dateOfBirth = :dateOfBirth, address = :address WHERE employeeID =
	 * :employeeID
	 * 
	 */
	@Override
	public synchronized Boolean update(String id, Employee newObject) {
		boolean isUpdated = false;
		try {
			em.getTransaction().begin();
			em.persist(newObject);
			em.getTransaction().commit();
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return isUpdated;
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public ArrayList<Employee> findById(String searchQuery) {
		ArrayList<Employee> result = new ArrayList<>();
		String hql = "FROM Employee WHERE employeeID LIKE :searchQuery";
		try {
			em.createQuery(hql, Employee.class).setParameter("searchQuery", searchQuery)
			.getResultStream().forEach(employee -> result.add(employee));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Employee> filter(int role, int status) {
		ArrayList<Employee> result = new ArrayList<>();
		String hql = "FROM Employee WHERE name LIKE :name";
		if (role != 0)
			hql += " AND role = :role";
		if (status != 0)
			hql += " AND status = :status";
		try {
			TypedQuery<Employee> query = em.createQuery(hql, Employee.class);
			query.setParameter("name", "%");
			if (role == 1)
				query.setParameter("role", "Nhân Viên Bán Hàng");
			else if (role == 2)
				query.setParameter("role", "Cửa Hàng Trưởng");
			else if (role == 3)
				query.setParameter("role", "Giám Sát Viên");
			if (status == 1)
				query.setParameter("status", true);
			else if (status == 2)
				query.setParameter("status", false);

			query.getResultStream().forEach(employee -> result.add((Employee) employee));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public synchronized boolean createAccount(Employee employee) throws Exception {
		Account_DAO account_dao = new Account_DAO();
		Account account = new Account(employee);
		if (account_dao.create(account))
			return true;
		return false;
	}
}
