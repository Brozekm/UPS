package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.requests.ReqType;
import org.example.requests.Request;


public class ExitConfController{
    @FXML
    Button exitStageButton;

    @FXML
    private void exitApp(){
        Request req = new Request(ReqType.DISCONNECT, String.valueOf(User.getInstance().getId()),User.getInstance().getNick());
        Connection.getInstance().sendToServer(req);
        Stage stage = (Stage) exitStageButton.getScene().getWindow();
        stage.close();
        Connection.getInstance().disconnect();
        Platform.exit();
        System.exit(0);
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
