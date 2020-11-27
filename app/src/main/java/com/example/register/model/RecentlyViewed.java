package com.example.register.model;

public class RecentlyViewed {

    String name;
/*    String description;
    String price;
    String quantity;
    String unit;*/
    Integer imageUrl;
    Integer id;

    public RecentlyViewed(String name, int imageUrl, int id) {
        this.name = name;
  /*      this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;*/
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
*/
    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageurl(Integer Imageurl) {
        this.imageUrl = imageUrl;
    }
}
