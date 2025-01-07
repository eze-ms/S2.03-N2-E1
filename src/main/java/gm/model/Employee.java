package gm.model;

public class Employee {
    private final String id;
    private String firstName;
    private String lastName;
    private String nif;
    private String phone;
    private String role; // "cook" or "delivery"
    private String storeId;

    // Constructor
    public Employee(String id, String firstName, String lastName, String nif,
                    String phone, String role, String storeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.phone = phone;
        this.role = role;
        this.storeId = storeId;
    }

    // Getter only for id
    public String getId() {
        return id;
    }

    // Standard Getters and Setters for the rest
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
