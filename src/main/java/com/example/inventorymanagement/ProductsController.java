package com.example.inventorymanagement;


import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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

public class ProductsController implements Initializable {

    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button exitButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private Label recordRemovedMessageLabel;
    @FXML
    private Label recordAddedMessageLabel;
    @FXML
    private Label recordUpdatedMessageLabel;

    //ADD
    @FXML
    private TextField addProductID;
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addSupplierID;
    @FXML
    private TextField addAmountInStock;
    @FXML
    private TextField addUnitPrice;

    //Update
    @FXML
    private TextField updateProductID;
    @FXML
    private TextField updateProductName;
    @FXML
    private TextField updateSupplierID;
    @FXML
    private TextField updateAmountInStock;
    @FXML
    private TextField updateUnitPrice;

    //Remove
    @FXML
    private TextField removeProductID;


    //TableView Part

    @FXML
    private TableView<ProductSearchModel>productTableView;
    @FXML
    private TableColumn<ProductSearchModel,Integer>productIDTableColumn;
    @FXML
    private TableColumn<ProductSearchModel,Integer>supplierIDTableColumn;
    @FXML
    private TableColumn<ProductSearchModel,Integer>unitPriceTableColumn;
    @FXML
    private TableColumn<ProductSearchModel,Integer>amountInStockTableColumn;
    @FXML
    private TableColumn<ProductSearchModel,String>productNameTableColumn;
    @FXML
    private TextField keywordTextField;

    private ObservableList<ProductSearchModel> ObservableList =FXCollections.observableArrayList() ;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewTable();
        File shieldFile = new File("src/images/shield.png");
        var shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }


    private void viewTable() {
        var connectNow1= new DatabaseConnector();
        Connection  connectDB1 = connectNow1.getConnection();

        //SQL
        String productViewQuery= "SELECT product_id, product_name, supplier_id, amount_in_stock, unit_price FROM product;";
        System.out.println(productViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(productViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer queryProductID=queryOutput.getInt("product_id");
                String queryProductName=queryOutput.getString("product_name");
                Integer querySupplierID=queryOutput.getInt("supplier_id");
                Integer queryAmountInStock=queryOutput.getInt("amount_in_stock");
                Integer queryUnitPrice=queryOutput.getInt("unit_price");

                //populate the observableList
                ObservableList.add(new ProductSearchModel(queryProductID,queryProductName,querySupplierID,queryAmountInStock,queryUnitPrice));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
            supplierIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
            amountInStockTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount_in_stock"));
            unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("unit_price"));


            productTableView.setItems(ObservableList);

            FilteredList<ProductSearchModel>filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(productSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }
                    String searchKeyWord= newValue.toLowerCase();
                    if(productSearchModel.getProduct_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(productSearchModel.getProduct_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(productSearchModel.getSupplier_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(productSearchModel.getUnit_price().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(productSearchModel.getAmount_in_stock().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }
                    else {
                        return false;//no matches
                    }
                });
            });

            SortedList<ProductSearchModel>sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(productTableView.comparatorProperty());

            productTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void refreshTable() {
        ObservableList.clear();
        viewTable();
    }


    public void exitButtonOnAction(){
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }
    public void updateButtonOnAction(){
        updateARecord();
        refreshTable();

    }
    public void addButtonOnAction (){
        addARecord();
        refreshTable();
    }
    public void removeButtonOnAction(){
        removeARecord();
        refreshTable();

    }

    private void updateARecord(){
        if(updateProductID.getText()==""){
            System.out.println("ProductID  not entered");
            recordUpdatedMessageLabel.setText("Enter a valid ProductID");
        }else{

            System.out.println("ProductID id is "+updateProductID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String productID= updateProductID.getText();
            String productName= updateProductName.getText();
            String unitPrice= updateUnitPrice.getText();
            String amountInStock= updateAmountInStock.getText();
            String supplierID= updateSupplierID.getText();

            if(updateProductName.getText()!=""){
                String updateFields= "UPDATE product SET product_name ='";
                String updateValues = productName +"'"+" WHERE " + "product_id ="+ productID+";";
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

            if(updateUnitPrice.getText()!=""){
                String updateFields= "UPDATE product SET unit_price ='";
                String updateValues = unitPrice +"'"+" WHERE " + "product_id ="+ productID+";";
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
            if(updateAmountInStock.getText()!=""){
                String updateFields= "UPDATE product SET amount_in_stock ='";
                String updateValues = amountInStock +"'"+" WHERE " + "product_id ="+ productID+";";
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
            if(updateSupplierID.getText()!=""){
                String updateFields= "UPDATE product SET supplier_id ='";
                String updateValues = supplierID +"'"+" WHERE " + "product_id ="+ productID+";";
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
    private void addARecord(){
        var connectNow= new DatabaseConnector();
        Connection  connectDB = connectNow.getConnection();

        String productID= addProductID.getText();
        String productName= addProductName.getText();
        String unitPrice= addUnitPrice.getText();
        String amountInStock= addAmountInStock.getText();
        String supplierID= addSupplierID.getText();

        String insertFields= "INSERT INTO product( product_id,product_name,supplier_id,amount_in_stock,unit_price) VALUES ('";

        String insertValues= productID+"','"+productName+"','"+supplierID+"','"+amountInStock+"','"+unitPrice+"'"+");";

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
    private void removeARecord(){
        if(removeProductID.getText()==""){
            System.out.println("ProductID not entered");
            recordRemovedMessageLabel.setText("Enter a valid Product ID");
        }else{
            System.out.println("ProductID is "+removeProductID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String productID= removeProductID.getText();

            String removeFields ="DELETE FROM product WHERE product_id="+productID+";" ;

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

}
