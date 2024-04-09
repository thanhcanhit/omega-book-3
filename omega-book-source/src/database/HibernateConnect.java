package database;

import jakarta.persistence.*;

public class HibernateConnect {
	public static EntityManager createEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_MSSQL_OMEGABOOK3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
}
