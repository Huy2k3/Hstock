package com.example.hstock.model;

import java.io.Serializable;


public class Model_ProductDetail implements Serializable {
    private int id;
    private int Retailprice,Price;
    private String imgProductDetail;
    private String Productname;
    private String desc;
    private String style;
    private String colorway;
    private String Releasedate;
    private String size;

    public Model_ProductDetail(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Model_ProductDetail(int price) {
        Price = price;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }



    public Model_ProductDetail(int id, int retailprice, String imgProductDetail, String productname, String desc, String style, String colorway, String releasedate) {
        this.id = id;
        Retailprice = retailprice;
        this.imgProductDetail = imgProductDetail;
        Productname = productname;
        this.desc = desc;
        this.style = style;
        this.colorway = colorway;
        this.Releasedate = releasedate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRetailprice() {
        return Retailprice;
    }

    public void setRetailprice(int retailprice) {
        Retailprice = retailprice;
    }

    public String getImgProductDetail() {
        return imgProductDetail;
    }

    public void setImgProductDetail(String imgProductDetail) {
        this.imgProductDetail = imgProductDetail;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getReleaseDate() {
        return Releasedate;
    }

    public void setReleaseDate(String releasedate) {
        this.Releasedate = releasedate;
    }

    @Override
    public String toString() {
        return "Model_ProductDetail{" +
                "id=" + id +
                ", Retailprice=" + Retailprice +
                ", Price=" + Price +
                ", imgProductDetail='" + imgProductDetail + '\'' +
                ", Productname='" + Productname + '\'' +
                ", desc='" + desc + '\'' +
                ", style='" + style + '\'' +
                ", colorway='" + colorway + '\'' +
                ", Releasedate='" + Releasedate + '\'' +
                ",size='"+size+'\''+
                '}';
    }


}

