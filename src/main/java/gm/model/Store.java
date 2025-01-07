package gm.model;

public class Store {
    private final String id;
    private String address;
    private String postalCode;
    private String city;
    private String province;

    // Constructor
    public Store(String id, String address, String postalCode, String city, String province) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    // Getter only for id
    public String getId() {
        return id;
    }

    // Standard Getters and Setters for the rest
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
