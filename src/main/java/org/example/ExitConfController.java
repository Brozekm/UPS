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
import org.example.requests.ReqType;
import org.example.requests.Request;

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
        Request req = new Request(ReqType.LOGOUT, String.valueOf(User.getInstance().getId()),User.getInstance().getNick());
        Connection.getInstance().sendToServer(req);
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitStage(){
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
    }

}
