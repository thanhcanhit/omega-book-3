package utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class AccessDatabase {
	private static final String MSSQL_UNIT = "JPA_MSSQL_OMEGABOOK3";

	private static EntityManager instance = null;

	public static EntityManager getInstance() {
		if (instance == null) {
			instance = Persistence.createEntityManagerFactory(MSSQL_UNIT).createEntityManager();
		}
		return instance;
	}
}
