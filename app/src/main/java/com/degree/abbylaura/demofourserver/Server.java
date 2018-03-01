package com.degree.abbylaura.demofourserver;


import android.content.Context;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by abbylaura on 18/02/2018.
 */

public class Server{

    public Server(){
        super();
    }



    public static void main(String[] args) {

        System.out.println("server three");

        //this.context = context;

        //create a server and client sockets
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ServerRequests requests = new ServerRequests(null, new ArrayList<String>());

        int portNumber = 9002; //TO BE CHANGED


        //setup server socket
        try{
            System.out.println("server four");

            serverSocket = new ServerSocket(portNumber);
            System.out.println("Socket set up");

            while(true) {

                System.out.println("thread lopp");

                //accept connection with clients socket
                //open reader and writer for communication
                try {

                    System.out.println("thread loop try");

                    //connection accepted
                    clientSocket = serverSocket.accept();
                    //System.out.println("client accepted");
                    System.out.println("thread accept");


                    //create a new multi-server-thread object to handle new client
                    //pass it the socket returned from the accept and start the thread
                    new ServerThreads(clientSocket, requests).start();

                    //carry on listening for new connections forever

                } catch (IOException e) {
                    System.err.println("IO error " + e.getMessage());
                }

            }


        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try{
                serverSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }

        }

    }


   /* protected void startServing(){

        new Threads().start();
    }

    public class Threads extends Thread{


        public Threads(){
            super();

            System.out.println("thread constructor");
        }

        public void run(){


        }
    }*/
}
