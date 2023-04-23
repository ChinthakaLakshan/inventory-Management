package com.example.inventorymanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class LoginController implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/images/Proceed.png");
        var brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockImageFile = new File("src/images/Lock.png");
        var lockImage = new Image(lockImageFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }

    public void exitButtonAction(ActionEvent event) {
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonAction(ActionEvent event) {
        createAccountForm( );
    }


    public void loginButtonOnAction(ActionEvent event) {
        if ((usernameTextField.getText().isBlank() == false) && (enterPasswordField.getText().isBlank() == false)) {
//            loginMessageLabel.setText("You try to login");
           validateLogin();
        } else {
            loginMessageLabel.setText("Please enter Username & Password");

        }

    }

    public void validateLogin( ) {
        var connectNow = new DatabaseConnector();
        Connection connectDB=connectNow.getConnection();

        String verifyLogin =" SELECT count(1) FROM user_account WHERE userName='"+usernameTextField.getText()+ "'AND password='"+enterPasswordField.getText()+"';";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("Congratulations!");
                    //open menu
                    displayMenu( );

                }else{
                    loginMessageLabel.setText("Invalid login. Please try again!");
                }
            }


        }catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }

    }

    public void createAccountForm( ) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 462, 704);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }
        catch(Exception exception){
            exception.printStackTrace();
            exception.getCause();

        }
    }
    public void displayMenu( ) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
            Stage menuStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
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