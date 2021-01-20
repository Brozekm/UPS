package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.example.requests.ReqType;
import org.example.requests.Request;

public class EndOfGameController extends MainController{

    @FXML
    Label names, score, serverAddLab, labInfoLoad;

    @FXML
    private void findNewGame(){
        Request request = new Request(ReqType.FIND_GAME, String.valueOf(User.getInstance().getId()), User.getInstance().getNick());
        Connection.getInstance().sendToServer(request);
    }

    public void setInfoServerClient(String myScore, String oppScore, String opponentName, String infoText) {
        names.setText(User.getInstance().getNick() +" vs. "+ opponentName);
        score.setText(myScore+" : "+oppScore);
        serverAddLab.setText(Server.getInstance().getAddress()+":"+Server.getInstance().getPort());
        if(infoText.charAt(0) == 'V'){
            labInfoLoad.setTextFill(Color.rgb(0,185,199));
        }else{
            labInfoLoad.setTextFill(Color.rgb(255,103,0));
        }
        labInfoLoad.setText(infoText);
    }

}
