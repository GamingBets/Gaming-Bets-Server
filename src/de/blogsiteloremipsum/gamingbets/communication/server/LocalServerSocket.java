package de.blogsiteloremipsum.gamingbets.communication.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */

//TODO Server sends Package to Client!

public class LocalServerSocket implements ServerMethods{

    public final static int port = 4567;
    private PrintWriter out;
    private BufferedReader in;
    private boolean exit;
    private int clientNumber;
    private ServerSocket serverSocket;




    public LocalServerSocket(){

        exit = false;
        clientNumber = 0;

        try {
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            System.out.println("Fehler beim Verbinden mit Port: "+ port);
            System.exit(-1);
        }

        while(!exit){
           try{
               //Warten bis eine Anfrage kommt
               System.out.println("Warten auf nächsten ClientThread");
               Socket clientsocket = serverSocket.accept();

               //Neuer Thread, Anfrage DA
               System.out.println("ClientThread gefunden!");
               Thread thread = new Thread(new ClientThread(clientsocket));
               thread.start();



           }catch(IOException e){
               e.printStackTrace();
           }
        }


    }

    public static void main(String args[]){

        LocalServerSocket server = new LocalServerSocket();
    }

   public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

}
