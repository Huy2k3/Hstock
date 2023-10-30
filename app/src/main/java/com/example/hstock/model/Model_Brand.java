package com.example.hstock.model;

public class Model_Brand {
    int id;
    String brand_filter_facet,image_brand;

    public Model_Brand(int id, String brand_filter_facet, String image_brand) {
        this.id = id;
        this.brand_filter_facet = brand_filter_facet;
        this.image_brand = image_brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_filter_facet() {
        return brand_filter_facet;
    }

    public void setBrand_filter_facet(String brand_filter_facet) {
        this.brand_filter_facet = brand_filter_facet;
    }

    public String getImage() {
        return image_brand;
    }

    public void setImage(String image) {
        this.image_brand = image;
    }

    @Override
    public String toString() {
        return "Model_Brand{" +
                "id=" + id +
                ", brand_filter_facet='" + brand_filter_facet + '\'' +
                ", image='" + image_brand + '\'' +
                '}';
    }
}
