package com.example.inventorymanagement;

public class ProductSearchModel {

     private Integer purchase_id;
    private String product_name;
    private Integer supplier_id;
    private Integer amount_in_stock;
    private Integer unit_price;


    public ProductSearchModel(Integer product_id, String product_name, Integer supplier_id, Integer amount_in_stock, Integer unit_price){
        this.purchase_id = product_id;
        this.supplier_id = supplier_id;
        this.amount_in_stock = amount_in_stock;
        this.unit_price = unit_price;
        this.product_name = product_name;

    }
//GETTERS

    public Integer getProduct_id() {
        return purchase_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public Integer getAmount_in_stock() {
        return amount_in_stock;
    }

    public Integer getUnit_price() {
        return unit_price;
    }


//SETTERS

    public void setProduct_id(Integer product_id) {
        this.purchase_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setAmount_in_stock(Integer amount_in_stock) {
        this.amount_in_stock = amount_in_stock;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }
}
