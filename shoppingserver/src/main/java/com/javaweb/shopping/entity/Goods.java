package com.javaweb.shopping.entity;

public class Goods {
    private int id;
    private String name;
    private double price;
    private  String imageURL;
    private int quantity;
    private String description;

    public Goods() {
    }

    public Goods(int id, String name, double price, String imageURL,
                 int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageURL='" + imageURL + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
