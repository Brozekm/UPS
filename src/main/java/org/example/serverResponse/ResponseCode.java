package org.example.serverResponse;

public enum ResponseCode {
    /** Successful response codes */
    NEW_PLAYER_CREATED (202),
    RECONNECTED(203),
    SUCCESSFUL_LOGOUT(204),
    GAME_DATA(205),
    WAIT_FOR_OPP_MOVE(206),
    LOSE(207),
    WIN(208),
    WIN_SURR(209),
    READY_FOR_PLAY(210),
    LOOKING_FOR_NEW_GAME (211),
    NICK_IS_USED(220),

    /** Error response codes */
    WRONG_PROTOCOL (400),
    UNKNOWN_REQUEST (401),
    UNKNOWN_MOVE (402),
    WRONG_PLAYER_ID (410),
    INVALID_NICK (411),
    PLAYER_HAVE_NO_GAME (412),
    UNSUCCESSFUL_LOGOUT (413),
    UNSUCCESSFUL_SURR (414),
    PLAY_STILL_IN_GAME(415),
    PLAYER_NO_LONGER_EXITS(416);


    private final int value;

    ResponseCode(final int typeIntVal){
        value = typeIntVal;
    }

    public int getValue() {
        return value;
    }

}
