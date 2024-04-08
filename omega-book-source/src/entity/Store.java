
package entity;
import java.util.*;

/**
 *
 * @author Như Tâm
 */
public class Store {
    
     /* Hằng báo lỗi*/
    public static final String ID_EMPTY = "Mã cửa hàng không được phép rỗng";
    public static final String NAME_EMPTY = "Tên cửa hàng không được phép rỗng";
    public static final String ADDRESS_EMPTY = "Địa chỉ không được phép rỗng";
    
    private String storeID;
    private String name;
    private String address;

    public Store() {
    }

    public Store(String storeID) throws Exception {
        setStoreID(storeID);
    }

    public Store(String storeID, String name, String address) throws Exception {
        setStoreID(storeID);
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
        final Store other = (Store) obj;
        return Objects.equals(this.storeID, other.storeID);
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getStoreID() {
        return storeID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.storeID);
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

    public void setStoreID(String storeID) throws Exception {
        if(storeID.trim().equals("")) 
            throw new Exception(ID_EMPTY);
        this.storeID = storeID;
    }

    @Override
    public String toString() {
        return storeID + "," + name + "," + address;
    }
}
