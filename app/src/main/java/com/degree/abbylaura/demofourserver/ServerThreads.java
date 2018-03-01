package com.degree.abbylaura.demofourserver;
/**
 * Created by abbylaura on 18/02/2018.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ServerThreads extends Thread{

    private Socket clientSocket;
    private ServerRequests requests;

    public ServerThreads(Socket clientSocket, ServerRequests requests){
        super();
        this.clientSocket = clientSocket;

        this.requests = requests;

        System.out.println("Server Threads Constructor");

    }

    public void run(){

        System.out.println("Server Threads Run");


        try {

            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);


            String passedRequest = inFromClient.readLine();
            System.out.println(passedRequest);


            if(passedRequest.equals("sending notice update")){
                outToClient.println("send");

                String clientId = inFromClient.readLine();
                System.out.println("clientid: " + clientId);
                String notice = inFromClient.readLine();
                System.out.println("notice: " + notice);
                String date = inFromClient.readLine();
                System.out.println("date: " + date);



                String size = inFromClient.readLine();
                System.out.println("size: " + size);

                long clientDBSize = Long.parseLong(size);

                requests.addNoticeToDB(clientId, notice, date);

                int diffInDBSize = (int) (requests.getNoticeCount() - clientDBSize);


                String[][] newNotices = new String[diffInDBSize][4];

                newNotices = requests.getMissingRows(diffInDBSize);

                outToClient.println(String.valueOf(diffInDBSize));

            }


            System.out.println(passedRequest);



            //get the complete set of notices
            //ArrayList<String> notices = requests.getNoticeArray();

            //int length = notices.size();






            //let the client know how many readln() to do
            //outToClient.println("NOTICE LENGTH" + String.valueOf(length) );


            /*int loop = length * 2;
            while(loop > 0){
                System.out.println("loop: " + String.valueOf(loop) + " length: " + String.valueOf(length));

                String out = notices.get(length - 1);

                outToClient.println(out);

                loop --;
                length --;
            }*/


            clientSocket.close();
            return;



        } catch (IOException e) {
            e.printStackTrace();


        } finally {
            try{
                clientSocket.close();
            }

            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
