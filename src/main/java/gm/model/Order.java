package gm.model;

import java.util.Map;

public class Order {
    private final String id;
    private String clientId;
    private String storeId;
    private String dateTime;
    private String deliveryType; // "home" or "pickup"
    private Map<String, Integer> productsQuantity;
    private double totalPrice;
    private String additionalNote;
    private String deliveryEmployeeId;
    private String deliveryDateTime;

    // Constructor
    public Order(String id, String clientId, String storeId, String dateTime,
                 String deliveryType, Map<String, Integer> productsQuantity,
                 double totalPrice, String additionalNote, String deliveryEmployeeId,
                 String deliveryDateTime) {
        this.id = id;
        this.clientId = clientId;
        this.storeId = storeId;
        this.dateTime = dateTime;
        this.deliveryType = deliveryType;
        this.productsQuantity = productsQuantity;
        this.totalPrice = totalPrice;
        this.additionalNote = additionalNote;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.deliveryDateTime = deliveryDateTime;
    }

    // Getter only for id
    public String getId() {
        return id;
    }

    // Standard Getters and Setters for the rest
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Map<String, Integer> getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(Map<String, Integer> productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(String deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }
}
