package entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author Nhu Tam
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Supplier.findBySupplierID", query = "SELECT s FROM Supplier s WHERE s.supplierID = :supplierID"),
		@NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s") 
})
public class Supplier{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Hằng báo lỗi*/
    public static final String ID_EMPTY = "Mã nhà cung cấp không được phép rỗng";
    public static final String NAME_EMPTY = "Tên nhà cung cấp không được phép rỗng";
    public static final String ADDRESS_EMPTY = "Địa chỉ không được phép rỗng";
    
    @Id
    private String supplierID;
	@Column(columnDefinition = "nvarchar(max)")
    private String name; 
	@Column(columnDefinition = "nvarchar(max)")
    private String address;

    public Supplier() {
    }

    public Supplier(String supplierID) throws Exception {
        setSupplierID(supplierID);
    }

    public Supplier(String supplierID, String name, String address) throws Exception {
        setSupplierID(supplierID);
        setName(name);
        setAddress(address);
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
        final Supplier other = (Supplier) obj;
        return Objects.equals(this.supplierID, other.supplierID);
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getSupplierID() {
        return supplierID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.supplierID);
        return hash;
    }

    public void setAddress(String address) throws Exception {
        if(address.trim().equals("")) 
            throw new Exception(ADDRESS_EMPTY);
        this.address = address;
    }

    public void setName(String name) throws Exception {
        if(name.trim().equals("")) 
            throw new Exception(NAME_EMPTY);
        this.name = name;
    }

    public void setSupplierID(String supplierID) throws Exception {
        if(supplierID.trim().equals("")) 
            throw new Exception(ID_EMPTY);
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return supplierID + "," + name + "," + address;
    }
    
}
