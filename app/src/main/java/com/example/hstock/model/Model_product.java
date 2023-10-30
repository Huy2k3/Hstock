package com.example.hstock.model;

import java.io.Serializable;

public class Model_product  {
    int id,brand_filter_facet;
    String product_name;
    String search_image;
    int price;
    int product_detail_id,discount_id,inventory;


    public Model_product(int id, int brand_filter_facet, String product_name, String search_image, int price,int product_detail_id) {
        this.id = id;
        this.brand_filter_facet = brand_filter_facet;
        this.product_name = product_name;
        this.search_image = search_image;
        this.price = price;
        this.product_detail_id=product_detail_id;
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrand_filter_facet() {
        return brand_filter_facet;
    }

    public void setBrand_filter_facet(int brand_filter_facet) {
        this.brand_filter_facet = brand_filter_facet;
    }

    public String getSearch_image() {
        return search_image;
    }

    public void setSearch_image(String search_image) {
        this.search_image = search_image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProduct_detail_id() {
        return product_detail_id;
    }

    public void setProduct_detail_id(int product_detail_id) {
        this.product_detail_id = product_detail_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Model_product{" +
                "id=" + id +
                ", brand_filter_facet='" + brand_filter_facet + '\'' +
                ", search_image='" + search_image + '\'' +
                ", price=" + price +
                ", product_detail_id=" + product_detail_id +
                ", discount_id=" + discount_id +
                ", inventory=" + inventory +
                '}';
    }
}
