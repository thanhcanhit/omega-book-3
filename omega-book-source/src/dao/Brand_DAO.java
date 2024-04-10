/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Brand;
import entity.Supplier;
import interfaces.DAOBase;
import jakarta.persistence.*;
import utilities.AccessDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Như Tâm
 */
public class Brand_DAO implements DAOBase<Brand> {
	EntityManager em;
	public Brand_DAO() {
		em = AccessDatabase.getEntityManager();
	}
    @Override
    public Brand getOne(String id) {
        return em.createNamedQuery("Brand.findByBrandID", Brand.class).setParameter("brandID", id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public ArrayList<Brand> getAll() {
        return (ArrayList<Brand>) em.createNamedQuery("Brand.findAll").getResultList();
    }

    @Override
    public String generateID() {
        String result = "TH";

        String query = "select top 1 * from Brand "
                       + "where brandID like ? "
                      + "order by brandID desc ";

        try {
            TypedQuery<Brand> query1 = em.createQuery(query, Brand.class);
            query1.setParameter(1, result + "%");
            List<Brand> rs = query1.getResultList();
            if(rs.size() > 0) {
                String lastID = rs.get(0).getBrandID();
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
    public Boolean create(Brand brand) {
		int n = 0;
		try {
			em.getTransaction().begin();
			em.persist(brand);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
    }

    @Override
    public Boolean update(String id, Brand brand) {
    	int n = 0;
		try {
			em.getTransaction().begin();
			Brand b = em.find(Brand.class, id);
			b.setName(brand.getName());
			b.setCountry(brand.getCountry());
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
		    e.printStackTrace();
		}
        return n > 0;
    }

    @Override
    public Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
