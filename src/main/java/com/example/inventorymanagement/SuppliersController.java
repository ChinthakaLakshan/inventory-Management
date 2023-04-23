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


public class SuppliersController implements Initializable{

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
    private TextField addSupplierID;
    @FXML
    private TextField addSupplierName;
    @FXML
    private TextField addAddress;
    @FXML
    private TextField addCity;
    @FXML
    private TextField addTelephone;
    @FXML
    private TextField addEmail;
    @FXML
    private Label recordAddedMessageLabel;

    //update
    @FXML
    private TextField updateSupplierID;
    @FXML
    private TextField updateSupplierName;
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updateCity;
    @FXML
    private TextField updateTelephone;
    @FXML
    private TextField updateEmail;
    @FXML
    private Label recordUpdatedMessageLabel;


    //remove
    @FXML
    private Label recordRemovedMessageLabel;
    @FXML
    private TextField removeSupplierID;

    //TableView Part

    @FXML
    private TableView<SupplierSearchModel> supplierTableView;
    @FXML
    private TableColumn<SupplierSearchModel,Integer> supplierIDTableColumn;
    @FXML
    private TableColumn<SupplierSearchModel,String> addressTableColumn;
    @FXML
    private TableColumn<SupplierSearchModel,String> emailTableColumn;
    @FXML
    private TableColumn<SupplierSearchModel,String> cityTableColumn;
    @FXML
    private TableColumn<SupplierSearchModel,String> telephoneTableColumn;
    @FXML
    private TableColumn<SupplierSearchModel,String> supplierNameTableColumn;
    @FXML
    private TextField keywordTextField;
    private ObservableList<SupplierSearchModel> ObservableList = FXCollections.observableArrayList() ;


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
        if(removeSupplierID.getText()==""){
            System.out.println("SupplierID not entered");
            recordRemovedMessageLabel.setText("Enter a valid SupplierID");
        }else{
            System.out.println("SupplierID is "+removeSupplierID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String supplierID= removeSupplierID.getText();

            String removeFields ="DELETE FROM supplier WHERE supplier_id="+supplierID+";" ;

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

        if(updateSupplierID.getText()==""){
            System.out.println("SupplierID not entered");
            recordUpdatedMessageLabel.setText("Enter a valid SupplierID");
        }else{

            System.out.println("SupplierID is "+updateSupplierID.getText());


            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String SupplierID= updateSupplierID.getText();
            String address= updateAddress.getText();
            String supplierName= updateSupplierName.getText();
            String city= updateCity.getText();
            String telephone= updateTelephone.getText();
            String email= updateEmail.getText();

            if(updateAddress.getText()!=""){
                String updateFields= "UPDATE supplier SET address ='";
                String updateValues = address +"'"+" WHERE " + "supplier_id ="+ SupplierID+";";
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
            if(updateSupplierName.getText()!=""){
                String updateFields= "UPDATE supplier SET supplier_name ='";
                String updateValues = supplierName +"'"+" WHERE " + "supplier_id ="+ SupplierID+";";
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
                String updateFields= "UPDATE supplier SET city ='";
                String updateValues = city +"'"+" WHERE " + "supplier_id ="+ SupplierID+";";
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
                String updateFields= "UPDATE supplier SET telephone ='";
                String updateValues = telephone +"'"+" WHERE " + "supplier_id ="+ SupplierID+";";
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
                String updateFields= "UPDATE supplier SET supplier_email ='";
                String updateValues = email +"'"+" WHERE " + "supplier_id ="+ SupplierID+";";
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

        String supplierID= addSupplierID.getText();
        String address= addAddress.getText();
        String supplierName= addSupplierName.getText();
        String city= addCity.getText();
        String telephone= addTelephone.getText();
        String email= addEmail.getText();

        String insertFields= "INSERT INTO supplier( supplier_id,supplier_name,address,city,telephone,supplier_email) VALUES ('";

        String insertValues= supplierID+"','"+supplierName+"','"+address+"','"+city+"','"+telephone+"','"+email+"');";
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
        String suppliersViewQuery= "SELECT supplier_id, supplier_name, address, city, telephone,supplier_email FROM supplier;";
        System.out.println(suppliersViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(suppliersViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer querySupplierID=queryOutput.getInt("supplier_id");
                String querySupplierName=queryOutput.getString("supplier_name");
                String queryAddress=queryOutput.getString("address");
                String queryCity=queryOutput.getString("city");
                String queryTelephone=queryOutput.getString("telephone");
                String querySupplierEmail=queryOutput.getString("supplier_email");

                //populate the observableList
                ObservableList.add(new SupplierSearchModel(querySupplierID,querySupplierName,queryAddress,queryCity,queryTelephone,querySupplierEmail));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            supplierNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
            supplierIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
            addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            telephoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            cityTableColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
            emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_email"));


            supplierTableView.setItems(ObservableList);
            FilteredList<SupplierSearchModel> filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(SupplierSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }
                    String searchKeyWord= newValue.toLowerCase();
                    if(SupplierSearchModel.getSupplier_id().toString().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(SupplierSearchModel.getAddress().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(SupplierSearchModel.getSupplier_email().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(SupplierSearchModel.getSupplier_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(SupplierSearchModel.getCity().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(SupplierSearchModel.getTelephone().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;}//found a match
                    else {
                        return false;//no matches
                    }
                });
            });

            SortedList<SupplierSearchModel> sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(supplierTableView.comparatorProperty());

            supplierTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void refreshTable() {
        ObservableList.clear();
        viewTable();
    }




}
