package main;

import jakarta.persistence.*;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_MSSQL_OMEGABOOK3");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			tr.commit();
			System.out.println("Da tao database");
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}

}
