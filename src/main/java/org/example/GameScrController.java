package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.requests.ReqType;
import org.example.requests.Request;

public class GameScrController extends MainController {

    @FXML
    private Label names, score,serverAddLab;

    @FXML
    private void rock() {
        System.out.println("rock");
        Request request = new Request(ReqType.PLAY, String.valueOf(User.getInstance().getId()),"Rock");
        Connection.getInstance().sendToServer(request);
    }

    @FXML
    private void paper() {
        System.out.println("paper");
        Request request = new Request(ReqType.PLAY, String.valueOf(User.getInstance().getId()),"Paper");
        Connection.getInstance().sendToServer(request);
    }

    @FXML
    private void scissors() {
        System.out.println("scissors");
        Request request = new Request(ReqType.PLAY, String.valueOf(User.getInstance().getId()),"Scissors");
        Connection.getInstance().sendToServer(request);
    }

    @FXML
    private void surrender(){
        Request request = new Request(ReqType.SURRENDER, String.valueOf(User.getInstance().getId()), User.getInstance().getNick());
        Connection.getInstance().sendToServer(request);
    }


    public void setInfoServerClient(String myScore, String oppScore, String opponentName) {
        names.setText(User.getInstance().getNick() +" vs. "+ opponentName);
        score.setText(myScore+" : "+oppScore);
        serverAddLab.setText(Server.getInstance().getAddress()+":"+Server.getInstance().getPort());
    }
}
