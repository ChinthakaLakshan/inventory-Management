package com.example.inventorymanagement;

public class PurchasesSearchModel {
    private  Integer purchase_id;
    private  Integer employee_id;
    private Integer customer_id;
    private Integer product_id;
    private Integer quantity;
    private Integer total_price;



    public PurchasesSearchModel(Integer purchase_id, Integer employee_id, Integer customer_id, Integer product_id, Integer quantity,Integer total_price){
        this.purchase_id = purchase_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.employee_id = employee_id;
        this.total_price=total_price;
    }

    public Integer getPurchase_id() {
        return purchase_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setPurchase_id(Integer purchase_id) {
        this.purchase_id = purchase_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }
}
