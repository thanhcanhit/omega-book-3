package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

/**
 *
 * @author KienTran
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o ORDER BY o.orderAt DESC"),
				@NamedQuery(name="Order.update", query="UPDATE Order o SET o.payment = :payment, " +
             "o.status = :status, " +
             "o.orderAt = :orderAt, " +
             "o.employee.employeeID = :employeeID, " +
             "o.customer.customerID = :customerID, " +
             "o.promotion.promotionID = :promotionID, " +
             "o.totalDue = :totalDue, " +
             "o.subTotal = :subTotal, " +
             "o.moneyGiven = :moneyGiven " +
             "WHERE o.orderID = :orderID"),
				@NamedQuery(name="Order.generateID", query="SELECT o.orderID FROM Order o WHERE o.orderID LIKE :prefix ORDER BY o.orderID DESC"),
				@NamedQuery(name="Order.getLength", query="SELECT COUNT(o) FROM Order o"),
				@NamedQuery(name="Order.getQuantityOrderSaved", query="SELECT COUNT(o) FROM Order o WHERE o.status = false"),
				@NamedQuery(name="Order.clearExpiredOrderSaved", query="SELECT o FROM Order o WHERE o.status = false AND " +
	                     "FUNCTION('DATEDIFF', DAY, o.orderAt, FUNCTION('CURRENT_DATE')) > 1"),
				@NamedQuery(name="Order.getTotalInMonth",query="SELECT SUM(o.totalDue) FROM Order o " +
                         "WHERE FUNCTION('YEAR', o.orderAt) = :year " +
                         "AND FUNCTION('MONTH', o.orderAt) = :month " +
                         "AND o.status = true")
})
@Table(name = "Bill")
public final class Order {

	private static final String ORDERID_ERROR = "Mã hoá đơn không hợp lệ !";
	private static final String ORDERAT_ERROR = "Ngày tạo hoá đơn không hợp lệ !";
	private static final String PROMOTION_ERROR = "Khuyến mãi không được rỗng !";
	private static final String EMPLOYEE_ERROR = "Nhân viên không được rỗng !";
	private static final String CUSTOMER_ERROR = "Khách hàng không được rỗng !";
	private static final String ORDERDETAIL_ERROR = "Chi tiết hoá đơn không được rỗng !";

	@Id
	private String orderID;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderAt;
	private boolean status;
	private double subTotal;
	private double totalDue;
	private boolean payment;
	@ManyToOne
	@JoinColumn(name = "promotionID")
	private Promotion promotion;
	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "customerID")
	private Customer customer;
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetail;
	private double moneyGiven;
	@ManyToOne
	@JoinColumn(name = "acountingVoucherID")
	private AcountingVoucher acountingVoucher;

	public Order() {
	}

	public Order(String orderID) {
		this.orderID = orderID;
	}

	public Order(String orderID, Date orderAt, boolean payment, boolean status, Employee employee, Customer customer,
			ArrayList<OrderDetail> orderDetail, double subTotal, double toTalDue, double moneyGiven) {
		this.orderID = orderID;
		this.orderAt = orderAt;
		this.status = status;
		this.subTotal = subTotal;
		this.totalDue = toTalDue;
		this.payment = payment;
		this.employee = employee;
		this.customer = customer;
		this.orderDetail = orderDetail;
		this.moneyGiven = moneyGiven;
	}

	public Order(String orderID, Date orderAt, boolean payment, boolean status, Promotion promotion, Employee employee,
			Customer customer, ArrayList<OrderDetail> orderDetail, double moneyGiven) throws Exception {
		setStatus(status);
		setOrderAt(orderAt);
		setCustomer(customer);
		setOrderDetail(orderDetail);
		setPayment(payment);
		setPromotion(promotion);
		setOrderID(orderID);
		setMoneyGiven(moneyGiven);
		setSubTotal();
		setTotalDue();

	}

	public Order(String orderID, Date orderAt, boolean payment, boolean status, Promotion promotion, Employee employee,
			Customer customer, ArrayList<OrderDetail> orderDetail, double subTotal, double toTalDue,
			double moneyGiven) {
		this.orderID = orderID;
		this.orderAt = orderAt;
		this.status = status;
		this.subTotal = subTotal;
		this.totalDue = toTalDue;
		this.promotion = promotion;
		this.payment = payment;
		this.employee = employee;
		this.customer = customer;
		this.orderDetail = orderDetail;
		this.moneyGiven = moneyGiven;
	}

	public Order(String orderID, Date orderAt, boolean status, double subTotal, double totalDue, boolean payment,
			Employee employee, Customer customer, ArrayList<OrderDetail> orderDetail, double moneyGiven)
			throws Exception {
		setOrderID(orderID);
		setOrderAt(orderAt);
		setStatus(status);
		setPayment(payment);
		setEmployee(employee);
		setCustomer(customer);
		setOrderDetail(orderDetail);
		setSubTotal();
		setTotalDue();
		setMoneyGiven(moneyGiven);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Order other = (Order) obj;
		return Objects.equals(this.orderID, other.orderID);
	}

	public Customer getCustomer() {
		return customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public double getMoneyGiven() {
		return moneyGiven;
	}

	public Date getOrderAt() {
		return orderAt;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public String getOrderID() {
		return orderID;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public double getTotalDue() {
		return totalDue;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 19 * hash + Objects.hashCode(this.orderID);
		return hash;
	}

	public boolean isPayment() {
		return payment;
	}

	public boolean isStatus() {
		return status;
	}

	public void setCustomer(Customer customer) throws Exception {
		if (customer != null) {
			this.customer = customer;
		} else {
			throw new Exception(CUSTOMER_ERROR);
		}
	}

	public void setEmployee(Employee employee) throws Exception {
		if (employee != null) {
			this.employee = employee;
		} else {
			throw new Exception(EMPLOYEE_ERROR);
		}
	}

	public void setMoneyGiven(double moneyGiven) {
		this.moneyGiven = moneyGiven;
	}

	public void setOrderAt(Date orderAt) throws Exception {
		if (orderAt != null) {
			this.orderAt = orderAt;
		} else {
			throw new Exception(ORDERAT_ERROR);
		}
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) throws Exception {
		if (!orderDetail.isEmpty()) {
			this.orderDetail = orderDetail;
		} else {
			throw new Exception(ORDERDETAIL_ERROR);
		}
		setSubTotal();
		setTotalDue();
	}

	public void setOrderID(String orderID) throws Exception {
		if (orderID.trim().length() > 0)
			this.orderID = orderID;
		else
			throw new Exception(ORDERID_ERROR);

	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public void setPromotion(Promotion promotion) throws Exception {
		if (promotion != null)
			this.promotion = promotion;
		else
			throw new Exception(PROMOTION_ERROR);
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private void setSubTotal() {
		double result = 0;
		for (OrderDetail cthd : orderDetail) {
			result += cthd.getLineTotal();
		}
		this.subTotal = result;
	}

	/**
	 * Tiền thanh toán = Tổng hóa đơn – Khuyến mãi + Phần trăm thuế*(Tổng hóa
	 * đơn-Khuyến mãi)
	 */
	private void setTotalDue() {
		if (promotion == null) {
			this.totalDue = this.subTotal;
			return;
		}
		this.totalDue = subTotal - ((promotion.getTypeDiscount() == promotion.getTypeDiscount().PERCENT)
				? (promotion.getDiscount() / 100 * (subTotal))
				: promotion.getDiscount());
	}

	@Override
	public String toString() {
		return "Order{" + "orderID=" + orderID + ", orderAt=" + orderAt + ", status=" + status + ", subTotal="
				+ subTotal + ", totalDue=" + totalDue + ", payment=" + payment + ", promotion=" + promotion
				+ ", employee=" + employee + ", customer=" + customer + ", orderDetail=" + orderDetail + '}';
	}
}
