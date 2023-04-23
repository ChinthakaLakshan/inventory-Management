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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PurchasesController implements Initializable {

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
    private TextField addPurchaseID;
    @FXML
    private TextField addEmployeeID;
    @FXML
    private TextField addCustomerID;
    @FXML
    private TextField addProductID;
    @FXML
    private TextField addQuantity;
    @FXML
    private TextField addTotalPrice;
    @FXML
    private Label recordAddedMessageLabel;

    //update
    @FXML
    private TextField updatePurchaseID;
    @FXML
    private TextField updateEmployeeID;
    @FXML
    private TextField updateCustomerID;
    @FXML
    private TextField updateProductID;
    @FXML
    private TextField updateQuantity;
    @FXML
    private TextField updateTotalPrice;
    @FXML
    private Label recordUpdatedMessageLabel;


    //remove
    @FXML
    private Label recordRemovedMessageLabel;
    @FXML
    private TextField removePurchaseID;

    //TableView Part
    @FXML
    private TableColumn<netSalesSearchModel,Integer>salesIDTableColumn;
    @FXML
    private TableView<netSalesSearchModel> netSalesTableView;
    @FXML
    private TableView<PurchasesSearchModel> purchaseTableView;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer> purchaseIDTableColumn;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer>employeeIDTableColumn;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer>totalPriceTableColumn;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer>quantityTableColumn;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer>customerIDTableColumn;
    @FXML
    private TableColumn<PurchasesSearchModel,Integer>productIDTableColumn;
    @FXML
    private TextField keywordTextField;
    private ObservableList<PurchasesSearchModel> ObservableList =FXCollections.observableArrayList() ;
    private ObservableList<netSalesSearchModel> ObservableList2 =FXCollections.observableArrayList() ;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/images/shield.png");
        viewTable();
        viewTable2();
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
        if(removePurchaseID.getText()==""){
            System.out.println("PurchaseID not entered");
            recordRemovedMessageLabel.setText("Enter a valid PurchaseID");
        }else{
            System.out.println("PurchaseID is "+ removePurchaseID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String purchaseID= removePurchaseID.getText();

            String removeFields ="DELETE FROM purchase WHERE purchase_id="+purchaseID+";" ;

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

        if(updatePurchaseID.getText()==""){
            System.out.println("PurchaseID not entered");
            recordUpdatedMessageLabel.setText("Enter a valid PurchaseID");
        }else{

            System.out.println("PurchaseID is "+ updatePurchaseID.getText());


            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String purchaseID= updatePurchaseID.getText();
            String customerID= updateCustomerID.getText();
            String employeeID= updateEmployeeID.getText();
            String productID= updateProductID.getText();
            String quantity= updateQuantity.getText();
            String totalPrice= updateTotalPrice.getText();

            String amountInStock="amount_in_stock-"+quantity;

            if(updateCustomerID.getText()!=""){
                String updateFields= "UPDATE purchase SET customer_id ='";
                String updateValues = customerID +"'"+" WHERE " + "purchase_id ="+ purchaseID+";";
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
            if(updateEmployeeID.getText()!=""){
                String updateFields= "UPDATE purchase SET employee_id ='";
                String updateValues = employeeID +"'"+" WHERE " + "purchase_id ="+ purchaseID+";";
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
            if(updateProductID.getText()!=""){
                String updateFields= "UPDATE purchase SET product_id ='";
                String updateValues = productID +"'"+" WHERE " + "purchase_id ="+ purchaseID+";";
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
            if(updateQuantity.getText()!=""){
                String updateFields= "UPDATE purchase SET quantity ='";
                String updateValues = quantity +"'"+" WHERE " + "purchase_id ="+ purchaseID+";";
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

                ///updating related product table

                String updateFields2= "UPDATE product SET amount_in_stock =";
                String updateValues2 = amountInStock +""+" WHERE " + "product_id ="+ productID+";";
                String updateDB2=updateFields2+updateValues2;

                System.out.println(updateDB2);
                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateDB2);
                    System.out.println("so far success2");

                }catch(Exception exception){
                    recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
                }
//////////////////////////////////////////////////////////////////////////////////////////


            }
            if(updateTotalPrice.getText()!=""){
                String updateFields= "UPDATE purchase SET total_price ='";
                String updateValues = totalPrice +"'"+" WHERE " + "purchase_id ="+ purchaseID+";";
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

        String purchaseID= addPurchaseID.getText();
        String customerID= addCustomerID.getText();
        String employeeID= addEmployeeID.getText();
        String productID= addProductID.getText();
        String quantity= addQuantity.getText();
        String totalPrice= addTotalPrice.getText();
        String amountInStock = "amount_in_stock-"+quantity;



        String insertFields= "INSERT INTO purchase( purchase_id,employee_id,customer_id,product_id,quantity) VALUES ('";

        String insertValues= purchaseID+"','"+employeeID+"','"+customerID+"','"+productID+"','"+quantity+"');";

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

        ///updating related product table

        String updateFields2= "UPDATE product SET amount_in_stock =";
        String updateValues2 = amountInStock +""+" WHERE " + "product_id ="+ productID+";";
        String updateDB2=updateFields2+updateValues2;

        System.out.println(updateDB2);
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateDB2);
            System.out.println("so far success2");

        }catch(Exception exception){
            recordUpdatedMessageLabel.setText("Error!.Please check the inputs");
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
        }
//////////////////////////////////////////////////////////////////////////////////////////




    }

    private void viewTable() {
        var connectNow1= new DatabaseConnector();
        Connection  connectDB1 = connectNow1.getConnection();
        //SQL
        String PurchaseViewQuery= "SELECT purchase_id, employee_id, customer_id, product_id, quantity,total_price FROM purchase;";
        System.out.println(PurchaseViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(PurchaseViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer queryPurchaseID=queryOutput.getInt("purchase_id");
                Integer queryEmployeeID=queryOutput.getInt("employee_id");
                Integer queryCustomerID=queryOutput.getInt("customer_id");
                Integer queryProductID=queryOutput.getInt("product_id");
                Integer queryQuantity=queryOutput.getInt("quantity");
                Integer queryTotalPrice=queryOutput.getInt("total_price");

                //populate the observableList
                ObservableList.add(new PurchasesSearchModel(queryPurchaseID,queryEmployeeID,queryCustomerID,queryProductID,queryQuantity,queryTotalPrice));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            purchaseIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("purchase_id"));
            employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
            customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
            quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            totalPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("total_price"));


            purchaseTableView.setItems(ObservableList);
            FilteredList<PurchasesSearchModel>filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(PurchasesSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }
                    String searchKeyWord= newValue.toLowerCase();
                    if(PurchasesSearchModel.getPurchase_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(PurchasesSearchModel.getProduct_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(PurchasesSearchModel.getCustomer_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(PurchasesSearchModel.getQuantity().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(PurchasesSearchModel.getEmployee_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(PurchasesSearchModel.getTotal_price().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;}//found a match
                    else {
                        return false;//no matches
                    }
                });
            });

            SortedList<PurchasesSearchModel>sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(purchaseTableView.comparatorProperty());

            purchaseTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(PurchasesController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void viewTable2() {
        var connectNow1= new DatabaseConnector();
        Connection  connectDB1 = connectNow1.getConnection();
        //SQL
        String netSalesViewQuery= "SELECT net_amount FROM sales;";
//        String netSalesViewQuery2= "Update sales set net_amount=400 where id=1;";

        String netSalesViewQuery2= "Update sales set net_amount= (SELECT SUM(total_price) FROM purchase)where id=1;";



        System.out.println(netSalesViewQuery);
        try{
            Statement statement2= connectDB1.createStatement();
            statement2.executeUpdate(netSalesViewQuery2);

            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(netSalesViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){

                Integer queryNetAmount=queryOutput.getInt("net_amount");

                //populate the observableList
                ObservableList2.add(new netSalesSearchModel(queryNetAmount));
                System.out.println("success populate");

            }

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(PurchasesController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }

        salesIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("net_amount"));



        netSalesTableView.setItems(ObservableList2);
        FilteredList<netSalesSearchModel>filteredList = new FilteredList<>(ObservableList2, b-> true);



    }



    private void refreshTable() {
        ObservableList.clear();
        refreshValues();
        viewTable();
        viewTable2();
    }

    //New Update
    public void refreshValues() {

        //populating total column
        var connectNow1= new DatabaseConnector();
        Connection  connectDB1 = connectNow1.getConnection();

        String ViewQuery= "Update purchase AS pu set total_price= quantity*(SELECT unit_price FROM product AS pr where pu.product_id= pr.product_id)where purchase_id>0;";


        System.out.println(ViewQuery);
        try{
            Statement statement2= connectDB1.createStatement();
            statement2.executeUpdate(ViewQuery);

            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(ViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){

                Integer queryNetAmount=queryOutput.getInt("net_amount");

                //populate the observableList
                ObservableList2.add(new netSalesSearchModel(queryNetAmount));
                System.out.println("success populate");

            }

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(PurchasesController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }

    }

    //populating net Sales column



}
