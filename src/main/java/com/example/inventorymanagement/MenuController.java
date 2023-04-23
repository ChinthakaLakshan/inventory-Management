package com.example.inventorymanagement;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
public class MenuController implements Initializable{

    @FXML
    private Button exitButton;
    @FXML
    private Button branchesButton;
    @FXML
    private Button purchaseButton;
    @FXML
    private Button customersButton;
    @FXML
    private Button suppliersButton;
    @FXML
    private Button productsButton;
    @FXML
    private Button employeesButton;
    @FXML
    private ImageView shieldImageView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/images/shield.png");
        var shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }


    public void exitButtonOnAction(){
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    public void branchesButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("branches.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 820, 776);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }

    }

    public void customersButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customers.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1026, 850);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }
    }

    public void suppliersButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("suppliers.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 858, 776);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }
    }

    public void productsButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("products.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 820, 776);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }
    }

    public void purchaseButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("purchases.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 858, 776);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }

    }

    public void employeesButtonOnAction(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employees.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 840, 850);
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }
    }


}
