package com.example.inventorymanagement;

public class SupplierSearchModel {
   private Integer supplier_id;
   private String supplier_name, address, city, telephone, supplier_email;

    public SupplierSearchModel(Integer supplier_id,String supplier_name,String address,String city,String telephone,String supplier_email){
        this.supplier_id=supplier_id;
        this.address=address;
        this.supplier_name=supplier_name;
        this.supplier_email=supplier_email;
        this.city=city;
        this.telephone=telephone;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }
}
