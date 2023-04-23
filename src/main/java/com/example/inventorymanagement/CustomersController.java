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

public class CustomersController implements Initializable {

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
    private TextField addFirstName;
    @FXML
    private TextField addLastName;
    @FXML
    private TextField addCustomerID;
    @FXML
    private TextField addAddress;
    @FXML
    private TextField addCity;
    @FXML
    private TextField addTelephone;
    @FXML
    private TextField addPoints;
    @FXML
    private TextField addEmail;
    @FXML
    private Label recordAddedMessageLabel;

    //update
    @FXML
    private TextField updateFirstName;
    @FXML
    private TextField updateLastName;
    @FXML
    private TextField updateCustomerID;
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updateCity;
    @FXML
    private TextField updateTelephone;
    @FXML
    private TextField updatePoints;
    @FXML
    private TextField updateEmail;
    @FXML
    private Label recordUpdatedMessageLabel;


    //remove
    @FXML
    private Label recordRemovedMessageLabel;
    @FXML
    private TextField removeCustomerID;

    //TableView Part

    @FXML
    private TableView<CustomerSearchModel> customerTableView;
    @FXML
    private TableColumn<CustomerSearchModel,Integer> customerIDTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> addressTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> emailTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> cityTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> telephoneTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> pointsTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> lastNameTableColumn;
    @FXML
    private TableColumn<CustomerSearchModel,String> firstNameTableColumn;
    @FXML
    private TextField keywordTextField;
    private ObservableList<CustomerSearchModel> ObservableList = FXCollections.observableArrayList() ;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/images/shield.png");
        viewTable();
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
        if(removeCustomerID.getText()==""){
            System.out.println("CustomerID not entered");
            recordRemovedMessageLabel.setText("Enter a valid CustomerID");
        }else{
            System.out.println("CustomerID is "+ removeCustomerID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String customerID= removeCustomerID.getText();

            String removeFields ="DELETE FROM customer WHERE customer_id="+customerID+";" ;

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

        if(updateCustomerID.getText()==""){
            System.out.println("CustomerID not entered");
            recordUpdatedMessageLabel.setText("Enter a valid CustomerID");
        }
        else{
            System.out.println("CustomerID is "+ updateCustomerID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String firstName= updateFirstName.getText();
            String customerID= updateCustomerID.getText();
            String lastName= updateLastName.getText();
            String address= updateAddress.getText();
            String city= updateCity.getText();
            String telephone= updateTelephone.getText();
            String points= updatePoints.getText();
            String email= updateEmail.getText();

            if(updateFirstName.getText()!=""){
                String updateFields= "UPDATE customer SET first_name ='";
                String updateValues = firstName +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updateLastName.getText()!=""){
                String updateFields= "UPDATE customer SET last_name ='";
                String updateValues = lastName +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updateAddress.getText()!=""){
                String updateFields= "UPDATE customer SET address ='";
                String updateValues = address +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updateCity.getText()!=""){
                String updateFields= "UPDATE customer SET city ='";
                String updateValues = city +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updateTelephone.getText()!=""){
                String updateFields= "UPDATE customer SET telephone ='";
                String updateValues = telephone +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updatePoints.getText()!=""){
                String updateFields= "UPDATE customer SET points ='";
                String updateValues = points +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
            if(updateEmail.getText()!=""){
                String updateFields= "UPDATE customer SET customer_email ='";
                String updateValues = email +"'"+" WHERE " + "customer_id ="+ customerID+";";
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
        String customerID= addCustomerID.getText();
        String lastName= addLastName.getText();
        String address= addAddress.getText();
        String city= addCity.getText();
        String telephone= addTelephone.getText();
        String points= addPoints.getText();
        String email= addEmail.getText();



        String insertFields= "INSERT INTO customer( customer_id,first_name,last_name,address,city,telephone,points,customer_email) VALUES ('";

        String insertValues= customerID+"','"+firstName+"','"+lastName+"','"+address+"','"+city+"','"+telephone+"','"+points+"','"+email+"');";
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
        String customersViewQuery= "SELECT customer_id, first_name, last_name, address, city,telephone,points,customer_email FROM customer;";
        System.out.println(customersViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(customersViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer queryCustomerID=queryOutput.getInt("customer_id");
                Integer queryPoints=queryOutput.getInt("points");
                String queryFirstname=queryOutput.getString("first_name");
                String queryLastname=queryOutput.getString("last_name");
                String queryCity=queryOutput.getString("city");
                String queryTelephone=queryOutput.getString("telephone");
                String queryCustomerEmail=queryOutput.getString("customer_email");
                String queryAddress=queryOutput.getString("address");


                //populate the observableList
                ObservableList.add(new CustomerSearchModel(queryCustomerID,queryFirstname,queryLastname,queryAddress,queryCity,queryTelephone,queryPoints,queryCustomerEmail));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
            addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            telephoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            cityTableColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
            pointsTableColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
            emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_email"));


            customerTableView.setItems(ObservableList);
            FilteredList<CustomerSearchModel> filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(CustomerSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }
                    String searchKeyWord= newValue.toLowerCase();
                    if(CustomerSearchModel.getCustomer_id().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(CustomerSearchModel.getPoints().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }
                    else if(CustomerSearchModel.getFirst_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    } else if(CustomerSearchModel.getAddress().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(CustomerSearchModel.getLast_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(CustomerSearchModel.getTelephone().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(CustomerSearchModel.getCustomer_email().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(CustomerSearchModel.getCity().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;}
                    else {
                        return false;//no matches
                    }
                });
            });

            SortedList<CustomerSearchModel> sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());

            customerTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void refreshTable() {
        ObservableList.clear();
        viewTable();
    }


}
