package javatcpclient;

import articlejar.Article;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author thoma
 */
public class JavaTCPClient {

    public static void main(String[] args) {
        Socket socket = null;
        try {
//            InetAddress address = InetAddress.getLocalHost();                                                                 //For when the server is on the same computer
//            InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, (byte) 1, (byte) 159,});        //For when the server is on a different computer
            InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 80, (byte) 210, (byte) 71, (byte) 238,});        //Public IP
            socket = new Socket(address, 360);

            System.out.println("Connected...\n");

            //Reads files from server
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                try {
                    while (true) {
                        Article article = (Article) inputStream.readObject();
                        System.out.println("\n\n" + article.getHeadline() + "\n");
                        System.out.println(article.getBodyText());
                    }
                } catch (EOFException ex) {
                    System.out.println("End of transmission.");
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Problem reading file from inputStream: " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
