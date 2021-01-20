package org.example;

public class Server {
    private static final Server server = new Server();
    private String address;
    private String port;

    public static Server getInstance(){return server;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
