package javatcpserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author thoma
 */
public class JavaTCPServer {

    public static final int SERVER_PORT = 360;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started.");
            System.out.println("Listening for connections on port " + SERVER_PORT + "...\n");

            //We listen until user halts server execution
            while (true) {
                //Waits for connection
                Socket socket = serverSocket.accept();

                System.out.println("Connection established.");

                //Writes to the client
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject("Bnag");
                oos.close();
            }
        } catch (IOException ex) {
            System.err.println("Server Connection error: " + ex.getMessage());
        }
    }
}
