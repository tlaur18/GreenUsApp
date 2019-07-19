package com.example.greenusapp.persistence;

import android.os.AsyncTask;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import articlejar.Article;

public class JavaTCPClient extends AsyncTask<String, Void, List<Article>> {

    @Override
    protected List<Article> doInBackground(String... strings) {
        List<Article> articles = new ArrayList<>();

        Socket socket = establishConnection();

        //Reads files from server
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            try {
                while (true) {
                    Article article = (Article) inputStream.readObject();
                    articles.add(article);
                }
            } catch (EOFException ex) {
                System.out.println("End of transmission.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Problem reading file from inputStream: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Problem creating ObjectInputStream" + ex.getMessage());
        }

        return articles;
    }

    private Socket establishConnection() {
        Socket socket = null;

        try {
//            InetAddress address = InetAddress.getLocalHost();                                                                 //For when the server is on the same computer
//            InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, (byte) 1, (byte) 159,});        //For when the server is on a different computer
            InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 80, (byte) 210, (byte) 71, (byte) 238,});        //Public IP
            socket = new Socket(address, 360);

            System.out.println("Connected...\n");
        } catch (IOException ex) {
            System.out.println("Problem establishing connection" + ex.getMessage());
        }

        return socket;
    }
}
