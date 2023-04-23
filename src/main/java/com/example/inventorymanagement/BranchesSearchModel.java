package com.example.inventorymanagement;

public class BranchesSearchModel {

    private Integer branch_id;
    private String branch_name;
    private String location;



    public BranchesSearchModel (Integer branch_id, String branch_name, String location){
        this.branch_id=branch_id;
        this.location=location;
        this.branch_name=branch_name;
    }
//GETTERS

    public Integer getBranch_id() {
        return branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getLocation() {
        return location;
    }

    //SETTERS


    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
