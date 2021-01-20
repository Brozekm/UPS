package org.example;

public class User {
    private static final User user = new User();
    private String nick;
    private int id;
    private String oppName = "";


    public static User getInstance(){return user;}

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public int getId() {
        return id;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }
}
