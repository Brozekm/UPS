package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Connection {
    private static final Connection connection = new Connection();
    private Socket socket;
    private boolean isConnected = false;

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


    private void serverListener(){
        int num = 0;
        while(!socket.isClosed()){
            System.out.println("Connected for "+ num*2+" sec");
            num++;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
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
