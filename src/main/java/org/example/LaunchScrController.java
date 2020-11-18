package org.example;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LaunchScrController extends MainController{

    @FXML
    private Label labError;

    @FXML
    private JFXTextField inpNick;


    @FXML
    private void startTheGame() throws IOException {
        if (isNameValid(inpNick.getText())) {
//            App.setRoot("primary");
            App.setRoot("gameScr");
            System.out.println("Change of screen to game screne");
        }
    }

    private boolean isNameValid(String nick) {
        if (correctLength(nick)) {
            if (areThereSpecChars(nick)) {
                return !isNickUsed(nick);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isNickUsed(String nick) {
//        TODO ask server if nick is used, if not return false
//        labError.setText("Nickname is used");
        return false;
    }

    private boolean areThereSpecChars(String nick) {
        Pattern p = Pattern.compile("([a-zA-Z0-9]*)");
        Matcher m = p.matcher(nick);
        if (!m.matches()) {
            labError.setText("Special chars detected");
            return false;
        } else {
            return true;
        }
    }

    private boolean correctLength(String nick) {
        if (nick.length() < 4) {
            labError.setText("Nickname is too short");
            return false;
        } else if (nick.length() > 32) {
            labError.setText("Nickname is too long");
            return false;
        } else {
            return true;
        }
    }


}
