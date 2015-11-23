package de.blogsiteloremipsum.gamingbets.communication.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;
import de.blogsiteloremipsum.gamingbets.communication.communication_types;

/**
 * Created by Felix Morsbach on 16.11.2015.
 */
public class LocalClientSocket implements ClientMethods{

    public final static int port = 4567;
    //TODO right ip
    private final static String serverAdress = "localhost";

    public static void main(String args[]){

        LocalClientSocket lc = new LocalClientSocket();
        System.out.println(lc.register("Felix", "felix@cool.de", "1234", null));


    }

    public boolean send(CommunicationPackage cp){

        String response = "";



        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            Socket socket = new Socket(serverAdress, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeObject(cp);
            response = in.readLine();

            socket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

/*

        try {
            if (InetAddress.getByName(serverAdress).isReachable(5000)) {
                System.out.println(serverAdress + " ist erreichbar!");

            }else{
                System.out.println(serverAdress + " ist NICHT erreichbar!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        //TODO Case if response is not "done", String contains Error Message, handling of this case needs implementation
        return response.equalsIgnoreCase("done");
    }

    @Override
    public boolean login(User user) {
       return send(new CommunicationPackage(communication_types.LOGIN, user, null, null, null));
    }

    @Override
    public boolean logout(User user) {
        return send(new CommunicationPackage(communication_types.LOGOUT, user, null, null, null));
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        return send(new CommunicationPackage(communication_types.REGISTER, null, null, null, new UnregisteredUser(username, email, pw, dob)));
    }

    @Override
    public boolean edit(User user) {
        return send(new CommunicationPackage(communication_types.EDIT, user, null, null, null));
    }

    @Override
    public boolean editAdmin(User user) {
        return send(new CommunicationPackage(communication_types.EDITADMIN, user, null, null, null));
    }

    @Override
    public boolean placeBet(Bet bet) {
        return send(new CommunicationPackage(communication_types.EDIT, null, bet, null, null));
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        return send(new CommunicationPackage(communication_types.EDIT, null, null, ticket, null));
    }


}
