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


    static void changeToLoadingScr(String nick, String address, String port){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loadingScr.fxml"));
        try {
            Parent parent = loader.load();

            LoadingController controller = loader.getController();
            controller.setInfoServerClient(nick , address, port);

            scene.setRoot(parent);
//            scene.getStylesheets().add("styles/loading.css");
        } catch (IOException e){
            System.err.println(e);
        }

    }


    static Object getController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main/resources/org.example/launchSrc" + ".fxml"));
        fxmlLoader.load();
        return fxmlLoader.getController();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}