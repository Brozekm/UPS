package org.example.serverResponse;

import javafx.application.Platform;
import org.example.*;
import org.example.requests.ReqType;
import org.example.requests.Request;

public class ResponseParser {
    public static void parseResponse(String response){
        String[] responseArr = response.split("\\|");
        int respCode = Integer.parseInt(responseArr[0]);
        if (respCode < 300){
            successfulResponseHandler(responseArr);
        }else{
            errorResponseHandler(responseArr);
        }

    }

    private static void errorResponseHandler(String[] responseArr) {
        int respCode = Integer.parseInt(responseArr[0]);
        if(respCode == ResponseCode.WRONG_PROTOCOL.getValue()){
            System.err.println("Wrong protocol");
        }else if (respCode == ResponseCode.UNKNOWN_REQUEST.getValue()){
            System.err.println("Unknown request");
        }else if (respCode == ResponseCode.UNKNOWN_MOVE.getValue()){
            System.err.println("Unknown move");
        }else if (respCode == ResponseCode.WRONG_PLAYER_ID.getValue()){
            System.err.println("Wrong id");
        }else if (respCode == ResponseCode.INVALID_NICK.getValue()){
            System.err.println("Invalid nickname");
        }else if (respCode == ResponseCode.PLAYER_HAVE_NO_GAME.getValue()){
            System.err.println("Player is not connected to any game");
        }else if (respCode == ResponseCode.UNSUCCESSFUL_LOGOUT.getValue()){
            System.err.println("Unsuccessful logout");
        }else if (respCode == ResponseCode.UNSUCCESSFUL_SURR.getValue()){
            System.err.println("Surrender unsuccessful");
        }else if (respCode == ResponseCode.PLAY_STILL_IN_GAME.getValue()){
            System.err.println("Player is still in the game");
        }else if (respCode == ResponseCode.PLAYER_NO_LONGER_EXITS.getValue()){
            System.err.println("Player no longer exits");
        }else{
            System.err.println("Unknown response from server");
        }
    }

    private static void successfulResponseHandler(String[] responseArr) {
        int respCode = Integer.parseInt(responseArr[0]);
        if(respCode == ResponseCode.NEW_PLAYER_CREATED.getValue()){
            login(responseArr);
        }else if (respCode == ResponseCode.RECONNECTED.getValue()){

        }else if (respCode == ResponseCode.SUCCESSFUL_LOGOUT.getValue()){
            Platform.runLater(App::changeLaunchLogout);
        }else if (respCode == ResponseCode.GAME_DATA.getValue()){
            gameDataResponse(responseArr);
        }else if (respCode == ResponseCode.WAIT_FOR_OPP_MOVE.getValue()){
            waitForOppMove();
        }else if (respCode == ResponseCode.LOSE.getValue()){
            endHandler(responseArr);
        }else if (respCode == ResponseCode.WIN.getValue()){
            endHandler(responseArr);
        }else if (respCode == ResponseCode.WIN_SURR.getValue()){
            endHandler(responseArr);
        }else if (respCode == ResponseCode.READY_FOR_PLAY.getValue()){
            readyForPlay(responseArr);
        }else if (respCode == ResponseCode.NICK_IS_USED.getValue()){
            App.nickIsUsed();
        }else if (respCode == ResponseCode.LOOKING_FOR_NEW_GAME.getValue()){
            findNewGame();
        }else{
            System.err.println("Unknown response from server");
        }
    }

    private static void findNewGame() {
        Platform.runLater(() -> App.changeToLoadingScr(User.getInstance().getNick(), Server.getInstance().getAddress(), Server.getInstance().getPort(), "Looking for opponent"));
    }

    private static void endHandler(String[] responseArr) {
        int respCode = Integer.parseInt(responseArr[0]);
        String opponent = User.getInstance().getOppName();
        User.getInstance().setOppName("");
        if (respCode == ResponseCode.LOSE.getValue()){
            Platform.runLater(() -> App.changeToEndScreen(responseArr[1],responseArr[2], opponent, "Defeat!"));
        } else if (respCode == ResponseCode.WIN.getValue()) {
            Platform.runLater(() -> App.changeToEndScreen(responseArr[1],responseArr[2], opponent, "Victory!"));
        }else if (respCode == ResponseCode.WIN_SURR.getValue()){
            Platform.runLater(() -> App.changeToEndScreen(responseArr[1],responseArr[2], opponent, "Victory via surrender."));
        }
    }

    private static void readyForPlay(String[] responseArr) {
        Platform.runLater(() -> App.changeToGameScreen(responseArr[1],responseArr[2],User.getInstance().getOppName()));
    }

    private static void waitForOppMove() {
        Platform.runLater(() -> App.changeToLoadingScr(User.getInstance().getNick(), Server.getInstance().getAddress(), Server.getInstance().getPort(), "Waiting for opponent to play"));
    }

    private static void gameDataResponse(String[] responseArr) {
        User.getInstance().setOppName(responseArr[3]);
        Platform.runLater(() -> App.changeToGameScreen(responseArr[1], responseArr[2], User.getInstance().getOppName()));
    }

    private static void login(String[] responseArr) {
        int id = Integer.parseInt(responseArr[1]);
        User.getInstance().setId(id);
        int state = Integer.parseInt(responseArr[2]);
        if(state == 0){
            Platform.runLater(() -> App.changeToLoadingScr(User.getInstance().getNick(), Server.getInstance().getAddress(), Server.getInstance().getPort(), "Looking for opponent"));
        }else {
            Request req = new Request(ReqType.GET_INIT_GAME_DATA, String.valueOf(User.getInstance().getId()), User.getInstance().getNick());
            Connection.getInstance().sendToServer(req);
        }
    }
}
