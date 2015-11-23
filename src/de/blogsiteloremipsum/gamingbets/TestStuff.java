package de.blogsiteloremipsum.gamingbets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

/**
 * Created by Felix on 18.11.2015.
 */
public class TestStuff {
    public static void main(String args[]){
        try {
            System.out.println(InetAddress.getLocalHost());
            System.out.println(InetAddress.getByName("www.google.de").isReachable(5000));



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
