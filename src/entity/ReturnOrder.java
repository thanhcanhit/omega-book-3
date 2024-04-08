package entity;

import enums.ReturnOrderStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author KienTran
 */
public class ReturnOrder {
    private final String ORDER_ERROR="Order không được rỗng";
    private final String EMPLOYEE_ERROR="Employee không được rỗng";
    private final String REASON_EMPTY = "Lý do không được rỗng";
    private final String TYPE_EMPTY = "Loại đơn đổi trả không được rỗng";
    private final String RETURNORDERID_VALID = "Mã đơn đổi trả không đúng cú pháp";

    
    
    private Date orderDate;
    private ReturnOrderStatus status;
    private String returnOrderID;
    private Employee employee;
    private Order order;
    private boolean type;
    private double refund;
    private ArrayList<ReturnOrderDetail> listDetail;
    private String reason;

    public ReturnOrder() {
    }

    public ReturnOrder(Date orderDate, ReturnOrderStatus status, String returnOrderID, Employee employee, Order order, boolean type, double refund, ArrayList<ReturnOrderDetail> listDetail, String reason) throws Exception {
        setOrderDate(orderDate);
        setStatus(status);
        setReturnOrderID(returnOrderID);
        setEmployee(employee);
        setOrder(order);
        setType(type);
        setListDetail(listDetail);
        setReason(reason);
        setRefund();
    }

    public ReturnOrder(String returnOrderID) {
        this.returnOrderID = returnOrderID;
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
        final ReturnOrder other = (ReturnOrder) obj;
        return Objects.equals(this.returnOrderID, other.returnOrderID);
    }

    public Employee getEmployee() {
        return employee;
    }

    public ArrayList<ReturnOrderDetail> getListDetail() {
        return listDetail;
    }

    public Order getOrder() {
        return order;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getReason() {
        return reason;
    }

    public double getRefund() {
        return refund;
    }

    public String getReturnOrderID() {
        return returnOrderID;
    }

    public ReturnOrderStatus getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.returnOrderID);
        return hash;
    }

    public boolean isType() {
        return type;
    }

    public void setEmployee(Employee employee) throws Exception{
        if(employee!=null)
            this.employee = employee;
        else
            throw new Exception(EMPLOYEE_ERROR);
    }

    public void setListDetail(ArrayList<ReturnOrderDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setOrder(Order order) throws Exception{
        if(order!=null)
            this.order = order;
        else
            throw new Exception(ORDER_ERROR);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setReason(String reason) throws Exception {
        if(reason.isBlank())
            throw new Exception(REASON_EMPTY);
        this.reason = reason;
    }

    public void setRefund() {
        if(this.type == false
                )
            this.refund = 0;
        else {
            for (ReturnOrderDetail returnOrderDetail : listDetail) {
                this.refund += returnOrderDetail.getPrice();
            }
        }
    }

    public void setReturnOrderID(String returnOrderID) throws Exception {
        String pattern = "^(HDT){1}[0-9]{12}$";
        if(!Pattern.matches(pattern, returnOrderID))
            throw new Exception(RETURNORDERID_VALID);
        this.returnOrderID = returnOrderID;
    }

    public void setStatus(ReturnOrderStatus status) {
        this.status = status;
    }

    public void setType(boolean type) throws Exception {
        if(type != true && type != false)
            throw new Exception(TYPE_EMPTY);
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "ReturnOrder{" + "ORDER_ERROR=" + ORDER_ERROR + ", EMPLOYEE_ERROR=" + EMPLOYEE_ERROR + ", orderDate=" + orderDate + ", status=" + status + ", returnOrderID=" + returnOrderID + ", employee=" + employee + ", order=" + order + ", type=" + type + ", refund=" + refund + ", listDetail=" + listDetail + ", reason=" + reason + '}';
    }
    
}
