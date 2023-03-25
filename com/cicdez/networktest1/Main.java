package com.cicdez.networktest1.giy;

import java.util.Scanner;

//Main class
public class Main {

    //Scanner for reading messages from console
    private static final Scanner SCANNER = new Scanner(System.in);

    //Main method
    public static void main(String[] args) {
        System.out.println("Create or Connect");

        //Read word from console
        String word = SCANNER.nextLine();

        //Check word
        if (word.equalsIgnoreCase("create")) createServer();
        else if (word.equalsIgnoreCase("connect")) connectToServer();
    }

    //Creating Server
    public static void createServer() {
        //Get Port
        String strPort = input("Port: ");
        int port = Integer.parseInt(strPort);

        //Get MaxUsers
        String strMaxUsers = input("Max Users: ");
        int maxUsers = Integer.parseInt(strMaxUsers);

        //Creating Server
        Server server = new Server(port, maxUsers);

        //Get Acceptable users
        String strUsersWait = input("How many Users Server need to Wait: ");
        int usersWait = strUsersWait.equalsIgnoreCase("all") ? maxUsers :
                Integer.parseInt(strUsersWait);

        //Accept users
        server.acceptUsers(usersWait);
    }

    //Connect to server
    public static void connectToServer() {
        //Get Port
        String strPort = input("Port: ");
        int port = Integer.parseInt(strPort);

        //Create client
        Client client = new Client(port);

        //Connect to Server
        client.connect();
    }

    //Some helpful function
    public static String input(String msg) {
        System.out.print(msg);
        return SCANNER.nextLine();
    }
}
