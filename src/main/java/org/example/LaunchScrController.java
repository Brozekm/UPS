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
    private Label labNickError;

    @FXML
    private Label labServerError;

    @FXML
    private JFXTextField inpServerPort;

    @FXML
    private JFXTextField inpServerAdr;

    @FXML
    private JFXTextField inpNick;


    @FXML
    private void startTheGame() throws IOException {
        if(isServerAddressValid(inpServerAdr.getText(),inpServerPort.getText())){
            if (isNameValid(inpNick.getText())) {
//            App.setRoot("primary");
//            App.setRoot("gameScr");
                App.setRoot("loadingScr");
                App.changeToLoading();
                System.out.println("Change of screen to game screne");
            }
        }

    }

    private boolean isServerAddressValid(String serAddress, String port) {
        labServerError.setText("");
        labNickError.setText("");
        if(serAddress.length()!=0){
            labServerError.setText("");
            if(port.length()!=0){
                labServerError.setText("");
                return serIsCorrectFormat(serAddress,port);

            }else{
                labServerError.setText("Port is not filled");
                return false;
            }
        }else{
            labServerError.setText("Address is not filled");
            return false;
        }
    }

    private boolean serIsCorrectFormat(String serAddress, String port) {


        return true;
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
            labNickError.setText("Special chars detected");
            return false;
        } else {
            return true;
        }
    }

    private boolean correctLength(String nick) {
        if (nick.length() < 4) {
            labNickError.setText("Nickname is too short");
            return false;
        } else if (nick.length() > 32) {
            labNickError.setText("Nickname is too long");
            return false;
        } else {
            return true;
        }
    }


}
