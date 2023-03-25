package com.cicdez.networktest1.giy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Server
public class Server {

    //Port
    private final int port;

    //Max Users count
    private final int maxUsers;

    //Count of users now
    private int nowUsers;

    //Server Socket
    private final ServerSocket server;

    //List of all accepted Client
    private final List<Socket> clients = new ArrayList<>();

    //Map 'Client-IOStream'
    private final Map<Socket, ClientIO> clientsIO = new HashMap<>();

    //Constructor
    public Server(int port, int maxUsers) {
        this.port = port;
        this.maxUsers = maxUsers;

        try {
            //Create Server
            this.server = new ServerSocket(port, maxUsers);
            System.out.println("Server Created {port=" + port + ", maxUsers=" + maxUsers + "}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Accept all possible users
    public void acceptAllUsers() {
        acceptUsers(maxUsers);
    }

    //Accept certain number of Clients
    public void acceptUsers(int count) {
        if (count > maxUsers) throw new IllegalStateException("Count > MaxCount!");

        for (int i = 0; i < count; i++) {
            try {
                //Accept client
                Socket socket = server.accept();

                //Create ClientIO
                ClientIO clientIO = new ClientIO(socket);

                //Add accepted client to List
                clients.add(socket);

                //Put Client with A Pair of Output- and Input- Streams
                clientsIO.put(socket, clientIO);

                //Increase now Users count
                nowUsers++;

                System.out.println("User #" + i + " connected");
            } catch (IOException e) {
                System.out.println("Can't accept User #" + i);
                throw new RuntimeException(e);
            }
        }
    }

    //Class contains the Pair of Output- and Input- Streams of Client
    public static final class ClientIO {
        public OutputStream outputStream;
        public InputStream inputStream;

        public ClientIO(Socket clientSocket) throws IOException {
            this.outputStream = clientSocket.getOutputStream();
            this.inputStream = clientSocket.getInputStream();
        }
    }

    public int getPort() {
        return port;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getNowUsers() {
        return nowUsers;
    }

    public List<Socket> getClients() {
        return clients;
    }

    public Map<Socket, ClientIO> getClientsIO() {
        return clientsIO;
    }
}
