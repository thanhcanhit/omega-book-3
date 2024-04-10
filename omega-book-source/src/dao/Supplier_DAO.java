package dao;

import entity.*;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;
import java.util.*;

/**
 *
 * @author Như Tâm
 */
public class Supplier_DAO implements DAOBase<Supplier>{
	EntityManager entityManager;
	public Supplier_DAO() {
		entityManager = AccessDatabase.getEntityManager();
	}

    @Override
    public Supplier getOne(String id) {
        return entityManager.createNamedQuery("Supplier.findBySupplierID", Supplier.class).setParameter("supplierID", id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public ArrayList<Supplier> getAll() {
        return (ArrayList<Supplier>) entityManager.createNamedQuery("Supplier.findAll").getResultList();
    }

    @Override
    public String generateID() {
        String result = "NCC";

        String query = """
                       select top 1 * from Supplier
                       where supplierID like ?
                       order by supplierID desc
                       """;

        try {
			TypedQuery<Supplier> query1 = entityManager.createQuery(query, Supplier.class);
			query1.setParameter(1, result + "%");
			List<Supplier> rs = query1.getResultList();

			if (rs.size() > 0) {
				String lastID = rs.get(0).getSupplierID();
				String sNumber = lastID.substring(lastID.length() - 2);
				int num = Integer.parseInt(sNumber) + 1;
				result += String.format("%04d", num);
			} else {
				result += String.format("%04d", 0);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Boolean create(Supplier supplier) {
        int n = 0;
        try {
        	entityManager.getTransaction().begin();
            entityManager.persist(supplier);
            entityManager.getTransaction().commit();
            n = 1;
            } catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
        return n > 0;
    }

    @Override
    public Boolean update(String id, Supplier supplier) {
		int n = 0;
		try {
		     entityManager.getTransaction().begin();
		        Supplier s = entityManager.find(Supplier.class, id);
		        s.setName(supplier.getName());
		        s.setAddress(supplier.getAddress());
		        entityManager.getTransaction().commit();
		        n = 1;
		    } catch (Exception e) {
		        entityManager.getTransaction().rollback();
		        e.printStackTrace();
		    }
		return n > 0;
    }

    @Override
    public Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
