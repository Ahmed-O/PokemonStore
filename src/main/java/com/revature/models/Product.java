package com.revature.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String image;
    private String countryCode;

    public Product() {
    }

    public Product(String name, int quantity, double price, String description, String image, String countryCode) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.countryCode = countryCode;
    }

    public Product(int id, String name, int quantity, double price, String description, String image,
            String countryCode) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.countryCode = countryCode;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + quantity;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", description="
                + description + ", image=" + image + ", countryCode=" + countryCode + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (countryCode == null) {
            if (other.countryCode != null)
                return false;
        } else if (!countryCode.equals(other.countryCode))
            return false;
        return true;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    

    


    

}
