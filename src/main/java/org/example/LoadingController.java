package org.example;


import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController extends MainController implements Initializable {

    @FXML
    Circle circle1;
    @FXML
    Circle circle2;
    @FXML
    Circle circle3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition ft1 = new FadeTransition(Duration.millis(1200), circle1);
        ft1.setFromValue(1.0);
        ft1.setToValue(0.1);
        ft1.setCycleCount(Timeline.INDEFINITE);
        ft1.setAutoReverse(true);
        ft1.play();

        FadeTransition ft2 = new FadeTransition(Duration.millis(1200), circle2);
        ft2.setDelay(Duration.millis(400));
        ft2.setFromValue(1.0);
        ft2.setToValue(0.1);
        ft2.setCycleCount(Timeline.INDEFINITE);
        ft2.setAutoReverse(true);
        ft2.play();

        FadeTransition ft3 = new FadeTransition(Duration.millis(1200), circle3);
        ft3.setDelay(Duration.millis(800));
        ft3.setFromValue(1.0);
        ft3.setToValue(0.1);
        ft3.setCycleCount(Timeline.INDEFINITE);
        ft3.setAutoReverse(true);
        ft3.play();
    }
}

