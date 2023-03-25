package com.cicdez.networktest1.giy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//Client
public class Client {

    //Port of Server
    private final int port;

    //Client Socket
    private Socket client;

    //IO-Streams for interaction with Server
    private OutputStream outputStream;
    private InputStream inputStream;

    //Constructor
    public Client(int port) {
        this.port = port;
    }

    //Connect to Server
    public void connect() {
        try {
            //Create new Client with connection to Server
            client = new Socket("localhost", port);
            System.out.println("Successfully connect to Server!");

            //Setting IO-Stream
            outputStream = client.getOutputStream();
            inputStream = client.getInputStream();
        } catch (IOException e) {
            System.out.println("Can't connect to server!");
            throw new RuntimeException(e);
        }
    }

    public int getPort() {
        return port;
    }

    public Socket getClient() {
        return client;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
