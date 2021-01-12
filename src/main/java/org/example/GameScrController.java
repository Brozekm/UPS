package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameScrController extends MainController {

    @FXML
    private Label playerNick;

    @FXML
    private void rock() {
        System.out.println("rock");
////        if (App.canSendRequest()) {
//            App.changeToLoading();
//        } else System.out.println("Not your turn");
    }

    @FXML
    private void paper() {
        System.out.println("paper");
        App.changeToPlayMode();
    }

    @FXML
    private void scissors() {
        System.out.println("scissors");
    }

    private void sendMove(String move) {

    }
}
