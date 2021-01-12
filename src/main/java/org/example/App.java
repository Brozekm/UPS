package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    double xOffset,yOffset;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("launchScr"));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
        moveScreen(stage);
    }

    private void moveScreen(Stage stage) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX()-xOffset);
                stage.setY(mouseEvent.getScreenY()-yOffset);
            }
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void changeToLoading(){
        scene.getStylesheets().clear();
        scene.getStylesheets().add("styles/loading.css");
        System.out.println("Change to Loading screen");
    }
    static void changeToPlayMode(){
        scene.getStylesheets().clear();
        scene.getStylesheets().add("styles/game.css");
    }

    static void addStyleSheet(String sheet){
        scene.getStylesheets().add("styles/"+sheet);
    }

    static void removeStyleSheet(String sheet){
        scene.getStylesheets().remove("styles/"+sheet);
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}