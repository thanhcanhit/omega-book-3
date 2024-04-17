/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.TypedQuery;
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
			length = Integer.parseInt(query.getSingleResult().toString());
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
			TypedQuery<Product> query = em.createQuery(hql, Product.class);
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
			TypedQuery<Product> query = em.createQuery(hql, Product.class);
			query.setParameter("productID", result + "%");
			query.setMaxResults(1);
			Product product = query.getSingleResult();

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
		System.out.println(result);

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
		boolean isUpdated = false;

		try {
			em.getTransaction().begin();

			// Merge the state of the given object into the current persistence context.
			em.merge(newObject);

			em.getTransaction().commit();
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		return isUpdated;
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
			TypedQuery<Product> query = em.createQuery(hql, Product.class);
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

		String hql = "FROM Product p WHERE p.name LIKE :name";
		if (isEmpty) {
			hql += " AND p.inventory = 0";
		}
		if (type != null) {
			hql += " AND p.type = :type";
			if (bookType != null) {
				hql += " AND p.bookCategory = :bookType";
			}
			if (stationeryType != null) {
				hql += " AND p.stationeryType = :stationeryType";
			}
		}

		try {
			TypedQuery<Product> query = em.createQuery(hql, Product.class);
			query.setParameter("name", name + "%");
			if (type != null) {
				query.setParameter("type", type);
				if (bookType != null) {
					query.setParameter("bookType", bookType);
				}
				if (stationeryType != null) {
					query.setParameter("stationeryType", stationeryType);
				}
			}

			query.getResultList().forEach(result::add);
			;
		} catch (Exception e) {
			e.printStackTrace();
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
	@SuppressWarnings("unused")
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
		int productType = rs.getInt("type");
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
//
//	/**
//	 *
//	 * @param object Đối tượng mang thông tin để truyền vào truy vấn
//	 * @param st     Câu truy vấn cần truyền tham số
//	 * @return int index hiện tại
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unused")
//	private int setParams(Product object, PreparedStatement st) throws SQLException, Exception {
//		if (object.getType() == Type.BOOK) {
//			Book book = (Book) object;
//			st.setString(1, book.getProductID());
//			st.setInt(2, book.getType());
//			st.setInt(3, book.getBookOrigin());
//			st.setInt(4, book.getBookCategory());
//			st.setString(5, book.getName());
//			st.setString(6, book.getAuthor());
//			st.setDouble(7, book.getPrice());
//			st.setDouble(8, book.getCostPrice());
//			st.setBytes(9, book.getImage());
//			st.setInt(10, book.getPublishYear());
//			st.setString(11, book.getPublisher());
//			st.setInt(12, book.getPageQuantity());
//			st.setBoolean(13, book.getIsHardCover());
//			st.setString(14, book.getDescription());
//			st.setString(15, book.getLanguage());
//			st.setString(16, book.getTranslator());
//			st.setDouble(17, book.getVAT());
//			st.setInt(18, book.getInventory());
//			return 18;
//		} else if (object.getType() == Type.STATIONERY) {
//			Stationery stationery = (Stationery) object;
//			st.setString(1, stationery.getProductID());
//			st.setInt(2, stationery.getType());
//			st.setInt(3, stationery.getStationeryType());
//			st.setString(4, stationery.getName());
//			st.setDouble(5, stationery.getPrice());
//			st.setDouble(6, stationery.getCostPrice());
//			st.setBytes(7, stationery.getImage());
//			st.setDouble(8, stationery.getWeight());
//			st.setString(9, stationery.getColor());
//			st.setString(10, stationery.getMaterial());
//			st.setString(11, stationery.getOrigin());
//			st.setString(12, stationery.getBrand().getBrandID());
//			st.setDouble(13, stationery.getVAT());
//			st.setInt(14, stationery.getInventory());
//			return 14;
//		}
//		return 0;
//	}

	public ArrayList<Product> getTop10Product(String date) {
		ArrayList<Product> result = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng

		// Chuyển đổi từ String sang LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);

		try {
			String hql = "select od.product.productID from OrderDetail as od join od.order as o "
					+ "where month(o.orderAt) = month(:date) and year(o.orderAt) = year(:date) "
					+ "group by od.product.productID order by count(od.product.productID) desc";
			TypedQuery<String> query = em.createQuery(hql, String.class);
			query.setParameter("date", localDate);
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng

		// Chuyển đổi từ String sang LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);

		try {
			String hql = "select od.product.productID from OrderDetail as od join od.order as o "
					+ "where cast(o.orderAt as date) = :date " + "group by od.product.productID";
			TypedQuery<String> query = em.createQuery(hql, String.class);
			query.setParameter("date", localDate);

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng

		// Chuyển đổi từ String sang LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);

		try {
			String hql = "select sum(od.quantity) as sl " + "from OrderDetail as od join od.order as o "
					+ "where cast(o.orderAt as date) = :date and od.product.productID = :productID "
					+ "group by od.product.productID " + "order by sum(od.quantity) desc";
			TypedQuery<Long> query = em.createQuery(hql, Long.class);
			query.setParameter("date", localDate);
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng

		// Chuyển đổi từ String sang LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);

		try {
			String hql = "select sum(od.lineTotal) " + "from OrderDetail as od join od.order as o "
					+ "where od.product.productID = :productID and month(o.orderAt) = month(:date) and year(o.orderAt) = year(:date) "
					+ "group by od.product.productID";
			TypedQuery<Double> query = em.createQuery(hql, Double.class);
			query.setParameter("productID", productID);
			query.setParameter("date", localDate);

			List<Double> sums = query.getResultList();

			if (!sums.isEmpty()) {
				result = sums.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getQuantityProductType(Type type, int month, int year) {
		int result = 0;

		try {
			String hql = "select sum(od.quantity) as sl "
					+ "from OrderDetail as od join od.order as o join od.product as p "
					+ "where month(o.orderAt) = :month and year(o.orderAt) = :year and p.type = :type "
					+ "group by p.type";
			TypedQuery<Long> query = em.createQuery(hql, Long.class);
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
