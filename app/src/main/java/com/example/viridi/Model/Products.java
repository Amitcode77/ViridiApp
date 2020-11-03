package com.example.viridi.Model;

public class Products
{
    private String pname,category,description,price,image,pid,date;

    public Products()
    {

    }

    public Products(String pname, String category, String description, String price, String image, String pid, String date) {
        this.pname = pname;
        this.category = category;
        this.description = description;
        this.price = price;
        this.image = image;
        this.pid = pid;
        this.date = date;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
