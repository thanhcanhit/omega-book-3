
package entity;

import java.util.Objects;

/**
 *
 * @author Nhu Tam
 */
public class Supplier {
    
    /* Hằng báo lỗi*/
    public static final String ID_EMPTY = "Mã nhà cung cấp không được phép rỗng";
    public static final String NAME_EMPTY = "Tên nhà cung cấp không được phép rỗng";
    public static final String ADDRESS_EMPTY = "Địa chỉ không được phép rỗng";
    
    private String supplierID;
    private String name; 
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
