package com.example.hstock.model;

public class Model_Cart {
    int cartid,size,price,soluong,user_id;
    String product_name,product_img;

    public Model_Cart() {
    }

    public Model_Cart(int size, int price, int soluong, int user_id, String product_name, String product_img) {
        this.size = size;
        this.price = price;
        this.soluong = soluong;
        this.user_id = user_id;
        this.product_name = product_name;
        this.product_img = product_img;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}
