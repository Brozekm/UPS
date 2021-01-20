package org.example;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.requests.ReqType;
import org.example.requests.Request;

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
    Button connectBtn;

    @FXML
    private void startTheGame() throws IOException {
        if (Connection.getInstance().isConnected()){
            labServerError.setOpacity(0);
            isNameValid(inpNick.getText());
        }else {
            labServerError.setText("You are not connected");
            labServerError.setOpacity(1);
        }
    }

    public void setNickError(){
        labNickError.setText("Nickname is taken");
        labNickError.setOpacity(1);
    }



    public void setInfoServerClient() {
        inpServerAdr.setText(Server.getInstance().getAddress());
        inpServerPort.setText(Server.getInstance().getPort());
        inpServerPort.setDisable(true);
        inpServerAdr.setDisable(true);
        connectBtn.setText("Disconnect");
    }

    @FXML
    private void connectDisconnect(){
        Connection conn = Connection.getInstance();
        if(conn.isConnected()){
            if(conn.disconnect()){
                inpServerPort.setDisable(false);
                inpServerAdr.setDisable(false);
                Server.getInstance().setAddress("");
                Server.getInstance().setPort("");
                connectBtn.setText("Connect");
            }else System.out.println("Disconnect unsuccessful");

        }else {
            if(isServerAddressValid(inpServerAdr.getText(),inpServerPort.getText())){
                if(conn.connect(inpServerAdr.getText(),inpServerPort.getText())){
                    labServerError.setOpacity(0);
                    inpServerPort.setDisable(true);
                    inpServerAdr.setDisable(true);
                    Server.getInstance().setAddress(inpServerAdr.getText());
                    Server.getInstance().setPort(inpServerPort.getText());
                    connectBtn.setText("Disconnect");
                }else {
                    labServerError.setText("Connection unsuccessful");
                    labServerError.setOpacity(1);
                    System.out.println("Connection unsuccessful");
                }
            }
        }

    }

    private boolean isServerAddressValid(String serAddress, String port) {
        labServerError.setOpacity(0);
        labNickError.setOpacity(0);
        if(serAddress.length()!=0){
            labServerError.setOpacity(0);
            if(port.length()!=0){
                labServerError.setOpacity(0);
                return serIsCorrectFormat(serAddress,port);

            }else{
                labServerError.setOpacity(1);
                labServerError.setText("Port is not filled");

                return false;
            }
        }else{
            labServerError.setOpacity(1);
            labServerError.setText("Address is not filled");
            return false;
        }
    }

    private boolean serIsCorrectFormat(String serAddress, String port) {


        return true;
    }

    private boolean isNameValid(String nick) {
        if (correctLength(nick)) {
            labNickError.setOpacity(0);
            if (areThereSpecChars(nick)) {
                labNickError.setOpacity(0);
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
        User.getInstance().setNick(nick);
        Request request = new Request(ReqType.LOGIN, "0",nick);
        Connection.getInstance().sendToServer(request);
        return false;
    }

    private boolean areThereSpecChars(String nick) {
        Pattern p = Pattern.compile("([a-zA-Z0-9]*)");
        Matcher m = p.matcher(nick);
        if (!m.matches()) {
            labNickError.setOpacity(1);
            labNickError.setText("Special chars detected");
            return false;
        } else {
            return true;
        }
    }

    private boolean correctLength(String nick) {
        if (nick.length() < 4) {
            labNickError.setOpacity(1);
            labNickError.setText("Nickname is too short");
            return false;
        } else if (nick.length() > 16) {
            labNickError.setOpacity(1);
            labNickError.setText("Nickname is too long");
            return false;
        } else {
            return true;
        }
    }



}
