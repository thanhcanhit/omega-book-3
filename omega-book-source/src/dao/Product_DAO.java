/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.Book;
import entity.Brand;
import entity.Product;
import entity.Stationery;
import enums.BookCategory;
import enums.BookType;
import enums.StationeryType;
import enums.Type;
import interfaces.DAOBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilities.AccessDatabase;

/**
 *
 * @author thanhcanhit
 */
public class Product_DAO implements DAOBase<Product> {
	EntityManager em;

	public Product_DAO() {
		em = AccessDatabase.getEntityManager();
	}

//    With img
	@Override
	public Product getOne(String id) {
		return em.find(Product.class, id);
	}

	@Override
	public ArrayList<Product> getAll() {
		ArrayList<Product> result = new ArrayList<>();
		String hql = "Select p FROM Product p";
		try {
			em.createQuery(hql, Product.class).getResultStream().forEach(result::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getLength() {
		int length = 0;
		try {
			String hql = "SELECT COUNT(*) FROM Product";
			Query query = em.createQuery(hql);
			length = (int) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}

	public boolean updateInventory(String productID, int quantity) {
		try {
			String hql = "UPDATE Product SET inventory = :quantity WHERE productID = :productID";
			Query query = em.createQuery(hql);
			query.setParameter("quantity", quantity);
			query.setParameter("productID", productID);
			int n = query.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Dùng cho logic chia trang Số phần tử 1 trang là 50
	 *
	 * @param page trang hiện tại (1,...)
	 * @return ArrayList<Product>
	 */
	public ArrayList<Product> getPage(int page) {
		ArrayList<Product> result = new ArrayList<>();
		String hql = "FROM Product ORDER BY productID";
		int offsetQuantity = (page - 1) * 50;
		try {
			Query query = em.createQuery(hql, Product.class);
			query.setFirstResult(offsetQuantity);
			query.setMaxResults(50);
			query.getResultStream().forEach(o -> result.add((Product) o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String generateID() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public String generateID(String prefix) {
		String result = prefix;
		String hql = "from Product where productID like :productID order by productID desc";

		try {
			Query query = em.createQuery(hql);
			query.setParameter("productID", result + "%");
			query.setMaxResults(1);
			Product product = (Product) query.getSingleResult();

			if (product != null) {
				String lastID = product.getProductID();
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
	public Boolean create(Product object) {
		int n = 0;
		em.getTransaction().begin();

		em.persist(object);
		em.getTransaction().commit();
		return n > 0;
	}

	@Override
	public Boolean update(String id, Product newObject) {
		int n = 0;

		try {
			em.getTransaction().begin();

			if (newObject.getType() == Type.BOOK) {
				String hql = "update Product set productID = :productID, productType = :productType, bookType = :bookType, bookCategory = :bookCategory, name = :name, author = :author, price = :price, costPrice = :costPrice, img = :img, publishYear = :publishYear, publisher = :publisher, pageQuantity = :pageQuantity, isHardCover = :isHardCover, description = :description, language = :language, translater = :translater, VAT = :VAT, inventory = :inventory where productID = :id";
				Query query = em.createQuery(hql);
				query.setParameter("productID", newObject.getProductID());
				query.setParameter("productType", newObject.getType().getValue());
				query.setParameter("bookType", ((Book) newObject).getBookOrigin().getValue());
				query.setParameter("bookCategory", ((Book) newObject).getBookCategory().getValue());
				query.setParameter("name", newObject.getName());
				query.setParameter("author", ((Book) newObject).getAuthor());
				query.setParameter("price", newObject.getPrice());
				query.setParameter("costPrice", newObject.getCostPrice());
				query.setParameter("img", newObject.getImage());
				query.setParameter("publishYear", ((Book) newObject).getPublishYear());
				query.setParameter("publisher", ((Book) newObject).getPublisher());
				query.setParameter("pageQuantity", ((Book) newObject).getPageQuantity());
				query.setParameter("isHardCover", ((Book) newObject).getIsHardCover());
				query.setParameter("description", ((Book) newObject).getDescription());
				query.setParameter("language", ((Book) newObject).getLanguage());
				query.setParameter("translater", ((Book) newObject).getTranslator());
				query.setParameter("VAT", newObject.getVAT());
				query.setParameter("inventory", newObject.getInventory());

				n = query.executeUpdate();
			} else if (newObject.getType() == Type.STATIONERY) {
				String hql = "update Product set productID = :productID, productType = :productType, stationeryType = :stationeryType, name = :name, price = :price, costPrice = :costPrice, img = :img, weight = :weight, color = :color, material = :material, origin = :origin, brandID = :brandID, VAT = :VAT, inventory = :inventory where productID = :id";
				Query query = em.createQuery(hql);
				query.setParameter("productID", newObject.getProductID());
				query.setParameter("productType", newObject.getType().getValue());
				query.setParameter("stationeryType", ((Stationery) newObject).getStationeryType().getValue());
				query.setParameter("name", newObject.getName());
				query.setParameter("price", newObject.getPrice());
				query.setParameter("costPrice", newObject.getCostPrice());
				query.setParameter("img", newObject.getImage());
				query.setParameter("weight", ((Stationery) newObject).getWeight());
				query.setParameter("color", ((Stationery) newObject).getColor());
				query.setParameter("material", ((Stationery) newObject).getMaterial());
				query.setParameter("origin", ((Stationery) newObject).getOrigin());
				query.setParameter("brandID", ((Stationery) newObject).getBrand().getBrandID());
				query.setParameter("VAT", newObject.getVAT());
				query.setParameter("inventory", newObject.getInventory());

				n = query.executeUpdate();
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	@Override
	public Boolean delete(String id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public ArrayList<Product> findById(String searchQuery) {
		ArrayList<Product> result = new ArrayList<>();
		String hql = "from Product where productID like :productID";

		try {
			Query query = em.createQuery(hql, Product.class);
			query.setParameter("productID", searchQuery + "%");
			List<Product> products = query.getResultList();

			for (Product product : products) {
				if (product != null) {
					result.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Truyền đủ các tham số để lọc danh sách sản phẩm
	 *
	 * @param name
	 * @param isEmpty
	 * @param type
	 * @param bookType
	 * @param stationeryType
	 * @return Danh sách sản phẩm match với bộ lọc
	 */
	public ArrayList<Product> filter(String name, boolean isEmpty, Type type, BookCategory bookType,
			StationeryType stationeryType) {
		ArrayList<Product> result = new ArrayList<>();
//        Index tự động tăng phụ thuộc vào số lượng biến số có
		int index = 1;
		String query = """
				SELECT [productID]
				     ,[productType]
				     ,[bookType]
				     ,[bookCategory]
				     ,[stationeryType]
				     ,[name]
				     ,[author]
				     ,[price]
				     ,[costPrice]
				     ,[publishYear]
				     ,[publisher]
				     ,[pageQuantity]
				     ,[isHardCover]
				     ,[description]
				     ,[language]
				     ,[translater]
				     ,[weight]
				     ,[color]
				     ,[material]
				     ,[origin]
				     ,[brandID]
				     ,[VAT]
				     ,[inventory]
				 FROM [dbo].[Product]  where name like ?""";
		if (isEmpty) {
			query += " and inventory = ?";
		}

//        Nếu loại sản phẩm là tất cả thì không xét đến 2 phần tử con
		if (type != null) {
			query += " and productType = ?";

//            Xét loại chi tiết
			if (bookType != null) {
				query += " and bookCategory = ?";
			}

			if (stationeryType != null) {
				query += " and stationeryType = ?";
			}
		}

		try {

			PreparedStatement st = ConnectDB.conn.prepareStatement(query);
			st.setString(index++, name + "%");

			if (isEmpty) {
				st.setInt(index++, 0);
			}

			if (type != null) {
				st.setInt(index++, type.getValue());

//                Xét loại chi tiết
				if (bookType != null) {
					st.setInt(index++, bookType.getValue());
				}

				if (stationeryType != null) {
					st.setInt(index++, stationeryType.getValue());
				}
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (rs != null) {
					result.add(getProductData(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 *
	 * @param rs Kết quả truy vấn cần lấy thông tin
	 * @return Thông tin của Product hoặc null
	 * @throws SQLException
	 * @throws Exception
	 */
	private Product getProductData(ResultSet rs) throws SQLException, Exception {
		Product result = null;

		// Lấy thông tin tổng quát của lớp product
		String id = rs.getString("productID");
		String name = rs.getString("name");
		Double costPrice = rs.getDouble("costPrice");
//        Để tránh gây giảm hiệu năng ứng dụng thì chỉ khi đọc 1 phần tử mới lấy img
//        byte[] image = rs.getBytes("img");
		Double VAT = rs.getDouble("VAT");
		Double price = rs.getDouble("price");
		int productType = rs.getInt("productType");
		int inventory = rs.getInt("inventory");

//      Định danh loại sản phẩm để lấy đủ thông tin cần cho đúng loại đối tượng
		if (Type.BOOK.compare(productType)) {
			String author = rs.getString("author");
			String publisher = rs.getString("publisher");
			int publishYear = rs.getInt("publishYear");
			String desc = rs.getString("description");
			int pageQuantity = rs.getInt("pageQuantity");
			boolean isHardCover = rs.getBoolean("isHardCover");
			String language = rs.getString("language");
			String translator = rs.getString("translater");
			int bookCategory = rs.getInt("bookCategory");
			int bookType = rs.getInt("bookType");

			result = new Book(author, publisher, publishYear, desc, pageQuantity, isHardCover, language, translator,
					BookType.fromInt(bookType), BookCategory.fromInt(bookCategory), id, name, costPrice, price, null,
					VAT, inventory, Type.BOOK);
		} else if (Type.STATIONERY.compare(productType)) {
			String color = rs.getString("color");
			Double weight = rs.getDouble("weight");
			String material = rs.getString("material");
			String origin = rs.getString("origin");
			String brandID = rs.getString("brandID");
			int stationeryType = rs.getInt("stationeryType");

			result = new Stationery(color, weight, material, origin, StationeryType.fromInt(stationeryType),
					new Brand(brandID), id, name, costPrice, price, null, VAT, inventory, Type.STATIONERY);
		}

		return result;
	}

	/**
	 *
	 * @param object Đối tượng mang thông tin để truyền vào truy vấn
	 * @param st     Câu truy vấn cần truyền tham số
	 * @return int index hiện tại
	 * @throws SQLException
	 * @throws Exception
	 */
	private int setParams(Product object, PreparedStatement st) throws SQLException, Exception {
		if (object.getType() == Type.BOOK) {
			Book book = (Book) object;
			st.setString(1, book.getProductID());
			st.setInt(2, book.getType().getValue());
			st.setInt(3, book.getBookOrigin().getValue());
			st.setInt(4, book.getBookCategory().getValue());
			st.setString(5, book.getName());
			st.setString(6, book.getAuthor());
			st.setDouble(7, book.getPrice());
			st.setDouble(8, book.getCostPrice());
			st.setBytes(9, book.getImage());
			st.setInt(10, book.getPublishYear());
			st.setString(11, book.getPublisher());
			st.setInt(12, book.getPageQuantity());
			st.setBoolean(13, book.getIsHardCover());
			st.setString(14, book.getDescription());
			st.setString(15, book.getLanguage());
			st.setString(16, book.getTranslator());
			st.setDouble(17, book.getVAT());
			st.setInt(18, book.getInventory());
			return 18;
		} else if (object.getType() == Type.STATIONERY) {
			Stationery stationery = (Stationery) object;
			st.setString(1, stationery.getProductID());
			st.setInt(2, stationery.getType().getValue());
			st.setInt(3, stationery.getStationeryType().getValue());
			st.setString(4, stationery.getName());
			st.setDouble(5, stationery.getPrice());
			st.setDouble(6, stationery.getCostPrice());
			st.setBytes(7, stationery.getImage());
			st.setDouble(8, stationery.getWeight());
			st.setString(9, stationery.getColor());
			st.setString(10, stationery.getMaterial());
			st.setString(11, stationery.getOrigin());
			st.setString(12, stationery.getBrand().getBrandID());
			st.setDouble(13, stationery.getVAT());
			st.setInt(14, stationery.getInventory());
			return 14;
		}
		return 0;
	}

	public ArrayList<Product> getTop10Product(String date) {
		ArrayList<Product> result = new ArrayList<>();

		try {
			String hql = "select od.productID from OrderDetail as od join od.order as o "
					+ "where month(o.orderAt) = month(:date) and year(o.orderAt) = year(:date) "
					+ "group by od.productID order by count(od.productID) desc";
			Query query = em.createQuery(hql);
			query.setParameter("date", date);
			query.setMaxResults(10);

			List<String> productIDs = query.getResultList();

			for (String productID : productIDs) {
				Product product = em.find(Product.class, productID);
				if (product != null) {
					result.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Product> getTopProductInDay(String date) {
		ArrayList<Product> result = new ArrayList<>();

		try {
			String hql = "select od.productID from OrderDetail as od join od.order as o "
					+ "where cast(o.orderAt as date) = :date " + "group by od.productID";
			Query query = em.createQuery(hql);
			query.setParameter("date", date);

			List<String> productIDs = query.getResultList();

			for (String productID : productIDs) {
				Product product = em.find(Product.class, productID);
				if (product != null) {
					result.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getQuantitySale(String productID, String date) {
		int result = 0;

		try {
			String hql = "select sum(od.quantity) as sl " + "from OrderDetail as od join od.order as o "
					+ "where cast(o.orderAt as date) = :date and od.productID = :productID " + "group by od.productID "
					+ "order by sum(od.quantity) desc";
			Query query = em.createQuery(hql);
			query.setParameter("date", date);
			query.setParameter("productID", productID);

			List<Long> quantities = query.getResultList();

			if (!quantities.isEmpty()) {
				result = quantities.get(0).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public double getTotalProduct(String productID, String date) {
		double result = 0;

		try {
			String hql = "select sum(od.lineTotal) " + "from OrderDetail as od join od.order as o "
					+ "where od.productID = :productID and month(o.orderAt) = month(:date) and year(o.orderAt) = year(:date) "
					+ "group by od.productID";
			Query query = em.createQuery(hql);
			query.setParameter("productID", productID);
			query.setParameter("date", date);

			List<Double> sums = query.getResultList();

			if (!sums.isEmpty()) {
				result = sums.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getQuantityProductType(int type, int month, int year) {
		int result = 0;

		try {
			String hql = "select sum(od.quantity) as sl "
					+ "from OrderDetail as od join od.order as o join od.product as p "
					+ "where month(o.orderAt) = :month and year(o.orderAt) = :year and p.productType = :type "
					+ "group by p.productType";
			Query query = em.createQuery(hql);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setParameter("type", type);

			List<Long> quantities = query.getResultList();

			if (!quantities.isEmpty()) {
				result = quantities.get(0).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
