package de.blogsiteloremipsum.gamingbets.communication.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;
import de.blogsiteloremipsum.gamingbets.communication.communication_types;

/**
 * Created by Felix on 17.11.2015.
 */
public class ClientThread implements Runnable{

    Socket clientsocket;

    public ClientThread(Socket clientsocket) {
        this.clientsocket = clientsocket;

    }

    @Override
    public void run() {

        try {
            

            ObjectInputStream in = new ObjectInputStream(clientsocket.getInputStream());
            CommunicationPackage cp = (CommunicationPackage) in.readObject();
            CommunicationPackageHandler cph = new CommunicationPackageHandler(cp);
            if(cp.getType().equals(communication_types.SENDLEADERBOARD)||cp.getType().equals(communication_types.SENDTICKETS)||cp.getType().equals(communication_types.SENDUSERS)||cp.getType().equals(communication_types.SENDUSER)){
            	ObjectOutputStream out1 = new ObjectOutputStream(this.clientsocket.getOutputStream());
            	out1.writeObject(cph.handleObject());
            	out1.close();
            }else{
            	PrintWriter out = new PrintWriter(this.clientsocket.getOutputStream(), true);
            	out.println(cph.handle());
            	
            }
           
            

            //Handle Package and return result
            
            
           
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
