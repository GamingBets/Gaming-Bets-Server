package de.blogsiteloremipsum.gamingbets.communication.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;

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
            PrintWriter out = new PrintWriter(this.clientsocket.getOutputStream(), true);

            ObjectInputStream in = new ObjectInputStream(clientsocket.getInputStream());
            CommunicationPackage cp = (CommunicationPackage) in.readObject();

            

            //Handle Package and return result
            CommunicationPackageHandler cph = new CommunicationPackageHandler(cp);
            out.println(cph.handle());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
