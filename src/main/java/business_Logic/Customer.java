package business_Logic;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private Address address;
    private String CNIC;
    private String phone_no;
    private String dob;
    public Customer(String name, Address address, String cNIC, String phone_no, String dob) {
        super();
        this.name = name;
        this.address = address;
        CNIC = cNIC;
        this.phone_no = phone_no;
        this.dob=dob;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getCNIC() {
        return CNIC;
    }
    public void setCNIC(String cNIC) {
        CNIC = cNIC;
    }
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }


}
