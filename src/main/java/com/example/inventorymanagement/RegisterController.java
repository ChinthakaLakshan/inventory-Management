package com.example.inventorymanagement;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;


public class RegisterController implements Initializable{
    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField setEmailField;
    @FXML
    private TextField jobTextField;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/images/shield.png");
        var shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }


    public void exitButtonAction(){
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
//        Platform.exit();
    }

    public void submitButtonOnAction(ActionEvent event) {
        if(setPasswordField.getText().equals(confirmPasswordField.getText()) && setPasswordField.getText() != "" ){
            registerUser();
            confirmPasswordLabel.setText("");

            if(firstnameTextField.getText() != "" && lastnameTextField.getText() != "" && usernameTextField.getText() != "" &&
            setEmailField.getText() != "" && jobTextField.getText() != "")
            {
                registerUser();
            }else{
                registrationMessageLabel.setText("Please enter the required fields");
            }
        }else{
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void registerUser() {
     var connectNow= new DatabaseConnector();
     Connection  connectDB = connectNow.getConnection();

     String firstname= firstnameTextField.getText();
     String lastname= lastnameTextField.getText();
     String username= usernameTextField.getText();
     String email= setEmailField.getText();
     String job= jobTextField.getText();
     String password= setPasswordField.getText();

     String insertFields= "INSERT INTO user_account( firstName,lastName,userName,user_email,password,job) VALUES ('";


     String insertValues= firstname+"','"+lastname+"','"+username+ "','"+ email +"','"+password +"','"+job+"');";
     String insertToRegister= insertFields+insertValues;
     System.out.println(insertToRegister);


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("User registered Successfully");
            System.out.println("so far success");

        }catch(Exception exception){
//            need to fixed
//            exception.printStackTrace();
////            exception.getCause();
        }

    }


}
