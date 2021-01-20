package org.example;

import javafx.application.Platform;
import org.example.requests.ReqModifier;
import org.example.requests.ReqType;
import org.example.requests.Request;
import org.example.serverResponse.ResponseParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Connection {
    private static final Connection connection = new Connection();
    private Socket socket;
    private boolean isConnected = false;

    private boolean ping = false;

    private Connection(){
    }

    public static Connection getInstance(){
        return connection;
    }


    /**
     * Method for conneting to server
     * @param address server address
     * @param port server port
     * @return boolean signalizing if connection was successful or not
     */
    public boolean connect(String address, String port){
        int portInt;
        try{
            portInt = Integer.parseInt(port);
        }catch (NumberFormatException e){
            return false;
        }

        try {

            socket = new Socket(address, portInt);
            InetAddress add = socket.getInetAddress();
            if(socket.isConnected()){
                System.out.println("Connection to: "+ add.getHostAddress()+" with name: "+add.getHostName());
                isConnected= true;
                new Thread(this::serverListener).start();
                new Thread(this::pingServer).start();
                return true;
            }else{
                isConnected = false;
                return false;
            }


        } catch (IOException e) {
            isConnected = false;
            return false;

        }

    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

    private void pingServer() {
        while(true){
            if (isConnected){
                Request request = new Request(ReqType.PING, "0","Ping");
                sendToServer(request);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ping){
                    ping = false;
                }else{
                    System.out.println("Server is not running");
                    Platform.runLater(Platform::exit);
                    System.exit(0);
                }
            }
        }
    }

    /** Method for disconnecting from server */
    public boolean disconnect(){
        try {
            socket.close();
            isConnected = false;
            System.out.println("Disconnect successful");
            return true;
        } catch (IOException e) {
            System.out.println("Disconnect unsuccessful");
            e.printStackTrace();
            return false;
        }

    }

    public void sendToServer(Request request){
        if (isConnected){
            String sReq = ReqModifier.reqForServe(request);
            OutputStream oStream = getOutputStream();

            try {
                assert oStream != null;
                assert sReq != null;
                oStream.write(sReq.getBytes());
                System.out.println("Request sent successfully");
            } catch (IOException e) {
                System.out.println("Error while sending request to server via output stream");
            }

        }else
            System.out.println("Sending request failed - No connection");
    }


    private void serverListener(){
        InputStream iStream = getInputSteam();
        assert iStream != null;
        while(!socket.isClosed()){
            try {
                if(iStream.available()>0){
//                    byte[] buffer = iStream.
                    String received = new String(iStream.readNBytes(iStream.available()), StandardCharsets.UTF_8);
                    System.out.print("Received: "+received);
                    ResponseParser.parseResponse(received.substring(0,received.length()-1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //GETTERS

    /** getter if is connected **/
    public boolean isConnected() {
        return isConnected;
    }


    /** Getter for socket output stream */
    private OutputStream getOutputStream(){
        if(socket.isConnected()){
            try {
                return socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Fail in acquiring output stream");
                return null;
            }


        }else return null;
    }


    /** Getter for socket input stream */
    private InputStream getInputSteam(){
        if(socket.isConnected()){
            try {
                return socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Fail in acquiring input stream");
                return null;
            }

        }else return null;
    }
}
