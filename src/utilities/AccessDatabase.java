package utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AccessDatabase {
	private static final String MSSQL_UNIT = "JPA_MSSQL_OMEGABOOK3";

	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(MSSQL_UNIT);
		return emf.createEntityManager();
	}
}
