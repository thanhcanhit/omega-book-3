package main;

import java.time.*;
import java.sql.Date;

import dao.Promotion_DAO;
import enums.DiscountType;
import enums.PromotionType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_MSSQL_OMEGABOOK3");
//		EntityManager em = emf.createEntityManager();
//
//		EntityTransaction tr = em.getTransaction();
//		try {
//			tr.begin();
//			tr.commit();
//			System.out.println("Da tao database");
//		} catch (Exception e) {
//			e.printStackTrace();
//			tr.rollback();
//		}
		Promotion_DAO dao = new Promotion_DAO();
		System.out.println(dao.getAll());
		System.out.println(dao.generateID(PromotionType.ORDER, DiscountType.PRICE, Date.valueOf(LocalDate.now())));
		
	}

}
