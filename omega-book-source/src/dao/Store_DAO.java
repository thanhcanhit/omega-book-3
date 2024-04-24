package dao;

import java.util.*;
import entity.Store;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class Store_DAO implements DAOBase<Store>{
	EntityManager em;
	public Store_DAO() {
		em = AccessDatabase.getInstance();
	}

	@Override
    public Store getOne(String id) {
		return em.find(Store.class, id);
	}

    @Override
    public ArrayList<Store> getAll() {
        return (ArrayList<Store>) em.createNamedQuery("Store.findAll", Store.class).getResultList();
    }

    @Override
    public String generateID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public synchronized Boolean create(Store store) {
        int n = 0;
        try {
			em.getTransaction().begin();
			em.persist(store);
			em.getTransaction().commit();
			n = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public synchronized Boolean update(String id, Store newObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public synchronized Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
