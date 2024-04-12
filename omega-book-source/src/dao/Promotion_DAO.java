package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Product;
import entity.Promotion;
import entity.PromotionForOrder;
import entity.PromotionForProduct;
import enums.DiscountType;
import enums.PromotionType;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utilities.AccessDatabase;

/**
 *
 * @author Như Tâm
 */
public class Promotion_DAO implements DAOBase<Promotion> {
	EntityManager em;
	public Promotion_DAO() {
		em = AccessDatabase.getEntityManager();
	}

    @Override
    public Promotion getOne(String id) {
		return em.createNamedQuery("Promotion.findByPromotionID", Promotion.class).setParameter("promotionID", id)
				.getSingleResult();
    }
    
    public PromotionForOrder getOneForOrder(String id) {
    	 return em.createNamedQuery("PromotionForOrder.findByPromotionID", PromotionForOrder.class).setParameter("promotionID", id)
                .getSingleResult();
    }

	public PromotionForProduct getForProduct(String promotionID) {
		return em.createNamedQuery("PromotionForProduct.findById", PromotionForProduct.class).setParameter("promotionID", promotionID).getSingleResult();
	}
    
	public ArrayList<Promotion> findById(String searchQuery) {
		String query = "SELECT p FROM Promotion where promotionID LIKE :id";
		return (ArrayList<Promotion>) em.createQuery(query, Promotion.class).setParameter(1, searchQuery).getResultList();
	}
	
	public ArrayList<PromotionForOrder> getForOrder(String id) {
   	 return (ArrayList<PromotionForOrder>) em.createNamedQuery("PromotionForOrder.findByPromotionID", PromotionForOrder.class).setParameter("promotionID", "%" + id + "%")
               .getResultList();
	}
	
    @Override
    public ArrayList<Promotion> getAll() {
    	return (ArrayList<Promotion>) em.createNamedQuery("Promotion.findAll", Promotion.class).getResultList();
    }
    
    public ArrayList<PromotionForOrder> getAllForOrder() {
    	return (ArrayList<PromotionForOrder>) em.createNamedQuery("PromotionForOrder.findAll", PromotionForOrder.class)
    			                .getResultList();
    }

    public ArrayList<Promotion> getAllForOrderFilterRank(int rank) {
    	return (ArrayList<Promotion>) em.createNamedQuery("Promotion.findByCondition", Promotion.class).setParameter("condition", rank).getResultList();
    }

    public ArrayList<PromotionForProduct> getAllForProduct() {
    	return (ArrayList<PromotionForProduct>) em.createNamedQuery("PromotionForProduct.findAll", PromotionForProduct.class)
                .getResultList();
    }

//    public ArrayList<Promotion> getAllForProductFilterProduct(String productID) {
//        ArrayList<Promotion> result = new ArrayList<>();
//        try {
//            Statement st = ConnectDB.conn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM Promotion WHERE promotionType = 0");
//
//            while (rs.next()) {
//                String promotionID = rs.getString("promotionID");
//                int typeDiscount = rs.getInt("typeDiscount");
//                int promotionType = rs.getInt("promotionType");
//                double discount = rs.getDouble("discount");
//                Date startedDate = rs.getDate("startedDate");
//                Date endedDate = rs.getDate("endedDate");
//                ArrayList<ProductPromotionDetail> listDetail = new ProductPromotionDetail_DAO().getAllForProduct(productID);
//                Promotion promo = new Promotion(promotionID, startedDate, endedDate, PromotionType.fromInt(promotionType), DiscountType.fromInt(typeDiscount), discount, listDetail);
//                result.add(promo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }



    @Override
    public String generateID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delete(String id) {
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean create(Promotion promo) {
        int n = 0;
        try {
			em.getTransaction().begin();
			em.persist(promo);
			em.getTransaction().commit();
			n = 1;
		} catch (Exception e) {
			 e.printStackTrace();
			em.getTransaction().rollback();
        }
        return n > 0;
    }

//    public Boolean createForProduct(Promotion promo) {
//        int n = 0;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into Promotion"
//                    + "([promotionID], [typeDiscount], [promotionType], [discount], [startedDate], [endedDate])"
//                    + "VALUES(?, ?, ?, ?, ?, ?)");
//            st.setString(1, promo.getPromotionID());
//            st.setInt(2, promo.getTypeDiscount().getValue());
//            st.setInt(3, promo.getTypePromotion().getValue());
//            st.setDouble(4, promo.getDiscount());
//            st.setDate(5, new java.sql.Date(promo.getStartedDate().getTime()));
//            st.setDate(6, new java.sql.Date(promo.getEndedDate().getTime()));
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }

    @Override
    public Boolean update(String id, Promotion newObject) {
    	throw new UnsupportedOperationException("Not supported yet.");
    }
    public Boolean update(String id) {
        int n = 0;
        try {
			em.getTransaction().begin();
			Promotion promo = em.find(Promotion.class, id);
			promo.setEndedDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			em.getTransaction().commit();
			n = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

//    @SuppressWarnings("unchecked")


//    private Promotion getPromotionData(ResultSet rs) throws SQLException, Exception {
//        Promotion result = null;
//
//        //Lấy thông tin tổng quát của lớp promotion
//        String promotionID = rs.getString("promotionID");
//        int typePromotion = rs.getInt("promotionType");
//        int typeDiscount = rs.getInt("typeDiscount");
//        double discount = rs.getDouble("discount");
//        Date startedDate = rs.getDate("startedDate");
//        Date endedDate = rs.getDate("endedDate");
//        int rankCustomer = rs.getInt("condition");
//        ArrayList<ProductPromotionDetail> listDetail = new ProductPromotionDetail_DAO().getAllForPromotion(promotionID);
//
//        result = new Promotion(promotionID, startedDate, endedDate, PromotionType.fromInt(typePromotion), DiscountType.fromInt(typeDiscount), discount, CustomerRank.fromInt(rankCustomer), listDetail);
//        return result;
//    }

//    public ArrayList<Promotion> filter(int type, int status) {
//        ArrayList<Promotion> result = new ArrayList<>();
////        Index tự động tăng phụ thuộc vào số lượng biến số có
//        int index = 1;
//        String query = "select * from Promotion WHERE promotionID like '%'";
////        Xét loại khuyến mãi
//        if (type != 0) {
//            query += " and typeDiscount = ?";
//        }
////            Xét trạng thái khuyến mãi
//        if (status == 1) {
//            query += " and endedDate > GETDATE()";
//        } else if (status == 2) {
//            query += " and endedDate < GETDATE()";
//        }
//        try {
//        	
//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
//
//            if (type == 1) {
//                st.setInt(index++, 1);
//            } else if (type == 2) {
//                st.setInt(index++, 0);
//            }
//
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                if (rs != null) {
//                    result.add(getPromotionData(rs));
//                }
//            }
//        	
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

//    public ArrayList<Promotion> findForOrderById(String searchQuery) {
//        ArrayList<Promotion> result = new ArrayList<>();
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM Promotion WHERE promotionType = 1 "
//                    + "and promotionID like '%?'");
//            st.setString(1, searchQuery);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                String promotionID = rs.getString("promotionID");
//                int typeDiscount = rs.getInt("typeDiscount");
//                double discount = rs.getDouble("discount");
//                Date startedDate = rs.getDate("startedDate");
//                Date endedDate = rs.getDate("endedDate");
//                int rankCustomer = rs.getInt("condition");
//                Promotion promo = new Promotion(promotionID, startedDate, endedDate, PromotionType.ORDER, DiscountType.fromInt(typeDiscount), discount, CustomerRank.fromInt(rankCustomer));
//                result.add(promo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public ArrayList<Promotion> getPromotionOrderAvailable(int rank) {
//    	"select * from Promotion where promotionType = 1 and endedDate > GETDATE() and condition <= ?"
        String query = "SELECT p FROM Promotion p WHERE p.typePromotion = 1 AND p.endedDate > :date AND p.condition <= :rank";
        Date now = java.sql.Timestamp.valueOf(LocalDateTime.now());
        return (ArrayList<Promotion>) em.createQuery(query, Promotion.class).setParameter("date", now).setParameter("rank", rank).getResultList();
    }

    public boolean updateDateStart(Promotion pm) {
        int n = 0;
        try {
           em.getTransaction().begin();
           Promotion promo = em.find(Promotion.class, pm.getPromotionID());
           promo.setStartedDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
           em.getTransaction().commit();
           n = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

//    private boolean updateDateEnd(Promotion pm) {
//        int n = 0;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareCall("UPDATE Promotion "
//                    + "SET endedDate = GETDATE()"
//                    + "WHERE promotionID = ?");
//            st.setString(1, pm.getPromotionID());
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }

    public boolean createForOrder(Promotion promo) {
        int n = 0;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into Promotion"
//                    + "([promotionID], [typeDiscount], [promotionType], [discount], [startedDate], [endedDate], [condition])"
//                    + "VALUES(?, ?, ?, ?, ?, ?, ?)");
//            st.setString(1, promo.getPromotionID());
//            st.setInt(2, promo.getTypeDiscount().getValue());
//            st.setInt(3, promo.getTypePromotion().getValue());
//            st.setDouble(4, promo.getDiscount());
//            st.setDate(5, new java.sql.Date(promo.getStartedDate().getTime()));
//            st.setDate(6, new java.sql.Date(promo.getEndedDate().getTime()));
//            st.setInt(7, promo.getCondition().getValue());
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return n > 0;
    }

	public String generateID(PromotionType promotionType, DiscountType typeDiscount, Date ended) {
        String prefix = "KM";
        if(typeDiscount.compare(1))
            prefix += 1;
        else
            prefix += 0;
        if(promotionType.compare(1))
            prefix += 1;
        else
            prefix += 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("ddMMyyyy");
        String formatEnded = simpleDateFormat.format(ended);
        
        prefix += formatEnded;
        String hql = "from Promotion where promotionID like :id order by promotionID desc";

		try {
			Query query = em.createQuery(hql);
			query.setParameter("id", prefix + "%");
			query.setMaxResults(1);
			Promotion promotion = (Promotion) query.getSingleResult();

			if (promotion != null) {
				String lastID = promotion.getPromotionID();
				String sNumber = lastID.substring(lastID.length() - 2);
				int num = Integer.parseInt(sNumber) + 1;
				prefix += String.format("%04d", num);
			} else {
				prefix += String.format("%04d", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return prefix;
	}


//    public ArrayList<Promotion> filterForProduct(int type, int status) {
//        ArrayList<Promotion> result = new ArrayList<>();
////        Index tự động tăng phụ thuộc vào số lượng biến số có
//        int index = 1;
//        String query = "select * from Promotion WHERE promotionType = 0 and promotionID like '%'";
////        Xét loại khuyến mãi
//        if (type != 0) {
//            query += " and typeDiscount = ?";
//        }
////            Xét trạng thái khuyến mãi
//        if (status == 1) {
//            query += " and endedDate > GETDATE()";
//        } else if (status == 2) {
//            query += " and endedDate < GETDATE()";
//        }
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
//
//            if (type == 1) {
//                st.setInt(index++, 1);
//            } else if (type == 2) {
//                st.setInt(index++, 0);
//            }
//
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                if (rs != null) {
//                    result.add(getPromotionData(rs));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

//    public ArrayList<Promotion> filterForOrder(int type, int status) {
//        ArrayList<Promotion> result = new ArrayList<>();
////        Index tự động tăng phụ thuộc vào số lượng biến số có
//        int index = 1;
//        String query = "select * from Promotion WHERE promotionType = 1 and promotionID like '%'";
////        Xét loại khuyến mãi
//        if (type != 0) {
//            query += " and typeDiscount = ?";
//        }
////            Xét trạng thái khuyến mãi
//        if (status == 1) {
//            query += " and endedDate > GETDATE()";
//        } else if (status == 2) {
//            query += " and endedDate < GETDATE()";
//        }
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
//
//            if (type == 1) {
//                st.setInt(index++, 1);
//            } else if (type == 2) {
//                st.setInt(index++, 0);
//            }
//
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                if (rs != null) {
//                    result.add(getPromotionData(rs));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
}