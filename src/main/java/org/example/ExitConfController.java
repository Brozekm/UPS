package org.example;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ExitConfController{
    @FXML
    Button exitStageButton;

    @FXML
    private void exitApp(){
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
        Connection.getInstance().disconnect();
        Platform.exit();
    }

    @FXML
    private void logout(){
        System.out.println("Logout");
//        TODO logout method
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitStage(){
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
    }

}
