package database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConnect {
	public static EntityManager createEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_MSSQL_OMEGABOOK3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	public static void main(String[] args) {
		EntityManager entityManager = createEntityManager();
		entityManager.close();
	}
}
