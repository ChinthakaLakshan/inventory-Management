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

public class BranchesController implements Initializable {

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
    @FXML
    private Label recordAddedMessageLabel;
    @FXML
    private Label recordUpdatedMessageLabel;
    @FXML
    private Label recordRemovedMessageLabel;

    //ADD
    @FXML
    private TextField addBranchID;
    @FXML
    private TextField addLocation;
    @FXML
    private TextField addBranchName;

    //Update
    @FXML
    private TextField updateBranchID;
    @FXML
    private TextField updateBranchName;
    @FXML
    private TextField updateLocation;

    //Remove
    @FXML
    private TextField removeBranchID;

    //TableView Part

    @FXML
    private TableView<BranchesSearchModel> branchTableView;
    @FXML
    private TableColumn<BranchesSearchModel,Integer> branchIDTableColumn;

    @FXML
    private TableColumn<BranchesSearchModel,String> locationTableColumn;
    @FXML
    private TableColumn<BranchesSearchModel,String> branchNameTableColumn;

    @FXML
    private TextField keywordTextField;

    private ObservableList<BranchesSearchModel> ObservableList = FXCollections.observableArrayList() ;


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

    public void updateButtonOnAction(){
        updateARecord();
        refreshTable();
    }

    public void addButtonOnAction(){
        addARecord();
        refreshTable();
    }
    public void removeButtonOnAction(){
        removeARecord();
        refreshTable();
    }


    private void updateARecord(){
        if(updateBranchID.getText()==""){
            System.out.println("branch id not entered");
            recordUpdatedMessageLabel.setText("Enter a valid branch_id");
        }else{

            System.out.println("branch id is "+updateBranchID.getText());


            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String branchID= updateBranchID.getText();
            String location= updateLocation.getText();
            String branchName= updateBranchName.getText();

            if(updateBranchName.getText()!=""){
                String updateFields= "UPDATE branches SET branch_name ='";
                String updateValues = branchName +"'"+" WHERE " + "branch_id ="+ branchID+";";
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
            if(updateLocation.getText()!=""){
                String updateFields= "UPDATE branches SET location ='";
                String updateValues = location +"'"+" WHERE " + "branch_id ="+ branchID+";";
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
    private void removeARecord(){
        if(removeBranchID.getText()==""){
            System.out.println("branch id not entered");
            recordRemovedMessageLabel.setText("Enter a valid branch_id");
        }else{
            System.out.println("branch id is "+updateBranchID.getText());

            var connectNow= new DatabaseConnector();
            Connection  connectDB = connectNow.getConnection();

            String branchID= removeBranchID.getText();

            String removeFields ="DELETE FROM branches WHERE branch_id="+branchID+";" ;

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

    private void addARecord(){
        var connectNow= new DatabaseConnector();
        Connection  connectDB = connectNow.getConnection();

        String branchID= addBranchID.getText();
        String location= addLocation.getText();
        String branchName= addBranchName.getText();

        String insertFields= "INSERT INTO branches( branch_id,branch_name,location) VALUES ('";

        String insertValues= branchID+"','"+branchName+"','"+location+"');";
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
        String branchesViewQuery= "SELECT branch_id, branch_name, location FROM branches;";
        System.out.println(branchesViewQuery);
        try{
            Statement statement= connectDB1.createStatement();
            ResultSet queryOutput= statement.executeQuery(branchesViewQuery);
            System.out.println("success sql");
            while(queryOutput.next()){
                Integer queryBranchID=queryOutput.getInt("branch_id");
                String queryBranchName=queryOutput.getString("branch_name");
                String queryLocation=queryOutput.getString("location");

                //populate the observableList
                ObservableList.add(new BranchesSearchModel(queryBranchID,queryBranchName,queryLocation));
                System.out.println("success populate");

            }

            //PropertyValueFactory corresponds to the new productSearchModelFields
            //tHE TABLE COLUMN IS THE ONE you annotate above

            branchIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
            branchNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("branch_name"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));


            branchTableView.setItems(ObservableList);

            FilteredList<BranchesSearchModel> filteredList = new FilteredList<>(ObservableList, b-> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredList.setPredicate(branchSearchModel -> {
                    //if no search values do nothing
                    if(newValue.isEmpty()|| newValue.isBlank()|| newValue==null){
                        return true;
                    }
                    String searchKeyWord= newValue.toLowerCase();
                    if(branchSearchModel.getBranch_name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(branchSearchModel.getBranch_id().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    }else if(branchSearchModel.getLocation().toString().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;//found a match
                    } else {
                        return false;//no matches
                    }
                });
            });

            SortedList<BranchesSearchModel> sortedList= new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(branchTableView.comparatorProperty());

            branchTableView.setItems(sortedList);

        }catch (SQLException exception
        ){
            // ERROR
            Logger.getLogger(BranchesController.class.getName()).log(Level.SEVERE,null,exception);
            exception.printStackTrace();
        }
    }

    private void refreshTable() {
        ObservableList.clear();
        viewTable();
    }

}
