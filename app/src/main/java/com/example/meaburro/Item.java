package com.example.meaburro;

public class Item {

    private int item_img;
    private String item_name;
    private String item_description;
    private int item_price;
    private int item_cantidad;
    private int item_id;

    public Item(int item_img, String item_name, String item_description, int item_price, int item_cantidad, int item_id) {
        this.item_img = item_img;
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_price = item_price;
        this.item_cantidad = item_cantidad;
        this.item_id = item_id;
    }

    public int getItem_img() {
        return item_img;
    }

    public void setItem_img(int item_img) {
        this.item_img = item_img;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getItem_cantidad() {
        return item_cantidad;
    }

    public void setItem_cantidad(int item_cantidad) {
        this.item_cantidad = item_cantidad;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
