package gm.model;

import java.util.Date;

public class Client {
    private final String id;
    private String firstName;
    private String lastName;
    private String postalCode;
    private String city;
    private String province;
    private String phoneNumber;
    private String street;
    private String number;
    private String floor;
    private String additionalNote;
    private final Date registerDate;

    // Constructor
    public Client(String id, String firstName, String lastName, String street, String number,
                  String floor, String postalCode, String city, String province,
                  String phoneNumber, String additionalNote, Date registerDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.phoneNumber = phoneNumber;
        this.additionalNote = additionalNote;
        this.registerDate = registerDate;
    }

    // Getter only for id and registerDate
    public String getId() {
        return id;
    }

    public Date getRegisterDate() {
        return registerDate;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
