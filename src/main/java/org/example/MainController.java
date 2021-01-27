package org.example;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class MainController {

    @FXML
    private void exitApp(){
        Connection conn = Connection.getInstance();
        if (conn.isConnected()){
            conn.disconnect();
        }
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void showExitConf() {
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exitConfScr.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
