package com.example.inventorymanagement;

public class CustomerSearchModel {

    private Integer customer_id, points;
    private String  first_name, last_name, address, city, telephone, customer_email;


    public CustomerSearchModel (Integer customer_id, String first_name,String last_name,String address,String city,String telephone,Integer points,String customer_email){
        this.first_name=first_name;
        this.last_name=last_name;
        this.address=address;
        this.city=city;
        this.telephone =telephone;
        this.customer_email=customer_email;
        this.customer_id=customer_id;
        this.points=points;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Integer getPoints() {
        return points;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
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

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
}
