package org.example.requests;

import org.example.User;

import java.util.List;

public class ReqModifier {

    public static String reqForServe(Request req) {
        StringBuilder request = new StringBuilder(String.valueOf(req.getType().getValue()));
        for (String param : req.getReqParams()) {
            request.append("|");
            request.append(param);
        }
        request.append("\n");
        return request.toString();
    }

//        switch (req.getType()){
//            case PLAY:
//                return playRequest(req.getReqParams());
//            case LOGIN:
//                return loginRequest(req.getType().getValue(),req.getReqParams());
//            case LOGOUT:
//                return logoutRequest(req.getReqParams());
//            case GET_INIT_GAME_DATA:
//                return getInitDataRequest(req.getType().getValue(),req.getReqParams());
//            default:
//                System.out.println("Request failed");
//                return null;
//        }
//    }
//
//    private static String getInitDataRequest(int type, List<String> reqParams) {
//        return type + "|" + reqParams.get(0) + "|" + reqParams.get(1) + "\n";
//    }
//
//    private static String logoutRequest(List<String> reqParams) {
//        return null;
//    }
//
//    private static String playRequest(List<String> reqParams) {
//        return null;
//    }
//
//    private static String loginRequest(int type,List<String> reqParams) {
//        StringBuilder request = new StringBuilder(type+"|0");
//        for (String param: reqParams){
//            request.append("|");
//            request.append(param);
//        }
//        request.append("\n");
//        System.out.println("Connection request: "+ request.toString());
//        return request.toString();
//    }

}
