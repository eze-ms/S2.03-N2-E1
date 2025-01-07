package gm.model;

public class Product {
    private final String id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category; // For pizzas, category can change throughout the year

    // Constructor
    public Product(String id, String name, String description, String imageUrl,
                   double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    // Getter only for id
    public String getId() {
        return id;
    }

    // Standard Getters and Setters for the rest
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
