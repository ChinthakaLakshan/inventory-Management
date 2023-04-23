package com.example.inventorymanagement;

public class EmployeesSearchModel {
    private  Integer employee_id,report_to,salary,bonus,branch_id;
  private  String first_name, last_name, job, employee_email,hire_date, terminate_date, telephone_number;

    public EmployeesSearchModel(Integer employee_id,String first_name,String last_name,String job,String employee_email, Integer report_to,String  hire_date,String terminate_date,String telephone_number,Integer salary,Integer bonus, Integer branch_id) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.employee_email = employee_email;
        this.report_to = report_to;
        this.hire_date = hire_date;
        this.terminate_date = terminate_date;
        this.telephone_number = telephone_number;
        this.salary = salary;
        this.bonus = bonus;
        this.branch_id = branch_id;

    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public Integer getReport_to() {
        return report_to;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getJob() {
        return job;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public String getHire_date() {
        return hire_date;
    }

    public String getTerminate_date() {
        return terminate_date;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public void setReport_to(Integer report_to) {
        this.report_to = report_to;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public void setTerminate_date(String terminate_date) {
        this.terminate_date = terminate_date;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
}

