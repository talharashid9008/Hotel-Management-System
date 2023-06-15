package business_Logic;

import java.io.Serializable;

public class Address implements Serializable {
    public Address(String city, String country, String street_address, int postal_code) {
        super();
        this.city = city;
        this.country = country;
        this.street_address = street_address;
        this.postal_code = postal_code;
    }



    private String city;
    private String country;
    private String street_address;
    private int postal_code;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getStreet_address() {
        return street_address;
    }
    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }
    public int getPostal_code() {
        return postal_code;
    }
    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public void setAddress(String city, String country, String address,int postal_code)
    {
        this.city=city;
        this.country=country;
        this.street_address=address;
        this.postal_code=postal_code;
    }

}
