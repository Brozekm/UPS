package org.example.requests;

public enum ReqType {
    CONNECT(0),
    DISCONNECT(1),
    LOGIN(2),
    LOGOUT(3),
    PLAY(4),
    GET_INIT_GAME_DATA(5),
    SURRENDER(6),
    FIND_GAME(7);
    
    
    
    private final int value;
    
    ReqType(final int typeIntVal){
        value = typeIntVal;
    }

    public int getValue() {
        return value;
    }
}
