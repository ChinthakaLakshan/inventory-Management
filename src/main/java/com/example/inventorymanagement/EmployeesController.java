package com.example.inventorymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeesController implements Initializable{
    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private Button exitButton;

    //add
    @FXML
    private TextField addEmployeeID;
    @FXML
    private TextField addFirstName;
    @FXML
    private TextField addLastName;
    @FXML
    private TextField addJob;
    @FXML
    private TextField addEmployeeEmail;
    @FXML
    private TextField addReportTo;
    @FXML
    private TextField addHireDate;
    @FXML
    private TextField addTerminateDate;
    @FXML
    private TextField addTelephoneNumber;
    @FXML
    private TextField addSalary;
    @FXML
    private TextField addBonus;
    @FXML
    private TextField addBranch;
    @FXML
    private Label recordAddedMessageLabel;

    //update
    @FXML
    private TextField updateEmployeeID;
    @FXML
    private TextField updateFirstName;
    @FXML
    private TextField updateLastName;
    @FXML
    private TextField updateJob;
    @FXML
    private TextField updateEmployeeEmail;
    @FXML
    private TextField updateReportTo;
    @FXML
    private TextField updateHireDate;
    @FXML
    private TextField updateTerminateDate;
    @FXML
    private TextField updateTelephoneNumber;
    @FXML
    private TextField updateSalary;
    @FXML
    private TextField updateBonus;
    @FXML
    private TextField updateBranch;
    @FXML
    private Label recordUpdatedMessageLabel;


    //remove
    @FXML
    private Label recordRemovedMessageLabel;
    @FXML
    private TextField removeEmployeeID;

    //TableView Part
    @FXML
    private TableView<EmployeesSearchModel> employeeTableView;
    @FXML
    private TableColumn<EmployeesSearchModel,Integer> employeeIDTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,Integer> reportToTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,Integer> salaryTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,Integer> bonusTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,Integer> branchIDTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> telephoneTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> emailTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> jobTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> terminateDateTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> hireDateTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> lastNameTableColumn;
    @FXML
    private TableColumn<EmployeesSearchModel,String> firstNameTableColumn;
    @FXML
    private TextField keywordTextField;
    private ObservableList<EmployeesSearchModel> ObservableList = FXCollections.observableArrayList() ;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewTable();
        File shieldFile = new File("src/images/shield.png");
        var shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    public void exitButtonOnAction(){
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    public void removeButtonOnAction(){
        removeARecord();
        refreshTable();

    }
    public void removeARecord(){
        if(removeEmployeeID.getText()==""){
            System.out.println("EmployeeID not entered");
            recordRemovedMessageLabel.setText("Enter a valid EmployeeID");
        }else{
            System.out.println("EmployeeID is "+ removeEmployeeID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String EmployeeID= removeEmployeeID.getText();

            String removeFields ="DELETE FROM employee WHERE employee_id="+EmployeeID+";" ;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(removeFields);
                recordRemovedMessageLabel.setText("Record removed Successfully");

                System.out.println("so far success");

            }catch(Exception exception){
                recordRemovedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
            }

        }
    }

    public void updateButtonOnAction(){
        updateARecord();
        refreshTable();

    }
    public void updateARecord(){

        if(updateEmployeeID.getText()==""){
            System.out.println("updateEmployeeID not entered");
            recordUpdatedMessageLabel.setText("Enter a valid updateEmployeeID");
        }else{

            System.out.println("updateEmployeeID is "+ updateEmployeeID.getText());


            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String firstName= updateFirstName.getText();
            String lastName= updateLastName.getText();
            String employeeID= updateEmployeeID.getText();
            String job= updateJob.getText();
            String employeeEmail= updateEmployeeEmail.getText();
            String hireDate= updateHireDate.getText();
            String reportTo= updateReportTo.getText();
            String bonus= updateBonus.getText();
            String branch= updateBranch.getText();
            String terminateDate= updateTerminateDate.getText();
            String telephoneNumber= updateTelephoneNumber.getText();
            String salary= updateSalary.getText();





            if(updateLastName.getText()!=""){
                String updateFields= "UPDATE employee SET last_name ='";
                String updateValues = lastName +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                System.out.println(updateDB);

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateFirstName.getText()!=""){
                String updateFields= "UPDATE employee SET first_name ='";
                String updateValues = firstName +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateJob.getText()!=""){
                String updateFields= "UPDATE employee SET job ='";
                String updateValues = job +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateEmployeeEmail.getText()!=""){
                String updateFields= "UPDATE employee SET employee_email ='";
                String updateValues = employeeEmail +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateHireDate.getText()!=""){
                String updateFields= "UPDATE employee SET hire_date ='";
                String updateValues = hireDate +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateTerminateDate.getText()!=""){
                String updateFields= "UPDATE employee SET terminate_date ='";
                String updateValues = terminateDate +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateBranch.getText()!=""){
                String updateFields= "UPDATE employee SET branch_id ='";
                String updateValues = branch +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateReportTo.getText()!=""){
                String updateFields= "UPDATE employee SET report_to ='";
                String updateValues = reportTo +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateSalary.getText()!=""){
                String updateFields= "UPDATE employee SET salary ='";
                String updateValues = salary +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateTelephoneNumber.getText()!=""){
                String updateFields= "UPDATE employee SET telephone_number ='";
                String updateValues = telephoneNumber +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }
            if(updateBonus.getText()!=""){
                String updateFields= "UPDATE employee SET bonus ='";
                String updateValues = bonus +"'"+" WHERE " + "employee_id ="+ employeeID+";";
                String updateDB=updateFields+updateValues;

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB);
                    recordUpdatedMessageLabel.setText("Record updated Successfully");

                    System.out.println("so far success");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
            }


        }






    }

    public void addButtonOnAction(){
        addARecord();
        refreshTable();
    }
    public void addARecord(){

        var connectNow= new DatabaseConnector();
        Connection  connectDB = connectNow.getConnection();

        String firstName= addFirstName.getText();
        String lastName= addLastName.getText();
        String employeeID= addEmployeeID.getText();
        String job= addJob.getText();
        String employeeEmail= addEmployeeEmail.getText();
        String reportTo= addReportTo.getText();
        String bonus= addBonus.getText();
        String branch= addBranch.getText();
        String hireDate= addHireDate.getText();
        String terminateDate= addTerminateDate.getText();
        String telephoneNumber= addTelephoneNumber.getText();
        String salary= addSalary.getText();

    if(terminateDate=="" || terminateDate=="'0000-00-00'" || terminateDate=="0000-00-00" ){
        terminateDate="0000-00-00";
    }

        String insertFields= "INSERT INTO employee( employee_id,first_name,last_name,job,employee_email,report_to,hire_date,terminate_date,telephone_number,salary,bonus,branch_id) VALUES ('";

        String insertValues= employeeID+"','"+firstName+"','"+lastName+"','"+job+"','"+employeeEmail+"','"+reportTo+"','"+hireDate+"','"+terminateDate+"','"+telephoneNumber+"','"+salary+"','"+bonus+"','"+branch+"');";
        String insertToAdd= insertFields+insertValues;
        System.out.println(insertToAdd);


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToAdd);
            recordAddedMessageLabel.setText("Record added Successfully");

            System.out.println("so far success");

        }catch(Exception exception){
            recordAddedMessageLabel.setText("Error!.Please check the inputs");

//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
        }





    }


    private void viewTable() {
        var connectNow1= new DatabaseConnector();
        Connection  connectDB1 = connectNow1.getConnection();
        //SQL
        String employeesViewQuery= "SELECT employee_id, first_name, last_name, job, employee_email, report_to, hire_date, terminate_date, telephone_number, salary, bonus, branch_id FROM employee;";
        System.out.println(employeesViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(employeesViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer queryEmployeeID=queryOutput.getInt("employee_id");
                Integer querySalary=queryOutput.getInt("salary");
                Integer queryReportTo=queryOutput.getInt("report_to");
                Integer queryBonus=queryOutput.getInt("bonus");
                Integer queryBranchID=queryOutput.getInt("branch_id");
                String queryFirstname=queryOutput.getString("first_name");
                String queryHireDate=queryOutput.getString("hire_date");
                String queryTerminateDate=queryOutput.getString("terminate_date");
                String queryTelephone=queryOutput.getString("telephone_number");
                String queryEmployeeEmail=queryOutput.getString("employee_email");
                String queryLastName=queryOutput.getString("last_name");
                String queryJob=queryOutput.getString("job");

                //populate the observableList
                ObservableList.add(new EmployeesSearchModel(queryEmployeeID,queryFirstname,queryLastName,queryJob,queryEmployeeEmail,queryReportTo,queryHireDate,queryTerminateDate,queryTelephone,querySalary,queryBonus,queryBranchID));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
            telephoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
            hireDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
            terminateDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("terminate_date"));
            jobTableColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
            bonusTableColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));
            emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_email"));
            salaryTableColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
            branchIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
            reportToTableColumn.setCellValueFactory(new PropertyValueFactory<>("report_to"));

            employeeTableView.setItems(ObservableList);
            FilteredList<EmployeesSearchModel> filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(EmployeesSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }

                    //Search Model
                    String searchKeyWord= newValue.toLowerCase();
                    if(EmployeesSearchModel.getEmployee_id().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getBranch_id().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getBonus().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getSalary().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getReport_to().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }
                    else if(EmployeesSearchModel.getHire_date().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    } else if(EmployeesSearchModel.getFirst_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getLast_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getTelephone_number().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getEmployee_email().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(EmployeesSearchModel.getJob().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;}
                    else if(EmployeesSearchModel.getTerminate_date().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;}
                    else {
                        return false;//no matches
                    }
                });
            });

            SortedList<EmployeesSearchModel> sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(employeeTableView.comparatorProperty());

            employeeTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void refreshTable() {
        ObservableList.clear();
        viewTable();
    }
}
