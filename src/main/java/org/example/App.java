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

    private static LaunchScrController launchController;

    double xOffset,yOffset;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("launchScr.fxml"));
        try {
            Parent parent = loader.load();

            launchController = loader.getController();
            scene = new Scene(parent);
        } catch (IOException e){
            System.err.println(e);
        }

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

    public static void nickIsUsed(){
        launchController.setNickError();
    }




    public static void changeToLoadingScr(String nick, String address, String port, String infoText){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loadingScr.fxml"));
        try {
            Parent parent = loader.load();

            LoadingController controller = loader.getController();
            controller.setInfoServerClient(nick , address, port, infoText);

            scene.setRoot(parent);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    public static void changeToGameScreen(String myScore, String oppScore, String opponentName){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("gameScr.fxml"));
        try {
            Parent parent = loader.load();

            GameScrController controller = loader.getController();
            controller.setInfoServerClient(myScore, oppScore, opponentName);

            scene.setRoot(parent);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    public static void changeToEndScreen(String myScore, String oppScore, String opponentName, String infoText){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("endOfGameScr.fxml"));
        try {
            Parent parent = loader.load();

            EndOfGameController controller = loader.getController();
            controller.setInfoServerClient(myScore, oppScore, opponentName, infoText);

            scene.setRoot(parent);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    public static void changeLaunchLogout(){
        User.getInstance().setNick("");
        User.getInstance().setId(0);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("launchScr.fxml"));
        try {
            Parent parent = loader.load();

            launchController = loader.getController();
            launchController.setInfoServerClient();

            scene.setRoot(parent);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}