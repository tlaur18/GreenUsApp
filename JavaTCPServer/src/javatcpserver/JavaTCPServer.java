package javatcpserver;

import articlejar.Article;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class JavaTCPServer {

    private static final int SERVER_PORT = 360;

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

                //Writes to client
                try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                    List<Article> allArticles = getArticles();
                    for (Article article : allArticles) {
                        oos.writeObject(article);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Server Connection error: " + ex.getMessage());
        }
    }

    private static List<Article> getArticles() {
        List<Article> allArticles = new ArrayList<>();

        File articleDirectory = new File("./src/resource/articles");
        File[] articleFiles = articleDirectory.listFiles();

        int id = 0;
        for (File articleFile : articleFiles) {
            String articleHeader = articleFile.getName().substring(0, articleFile.getName().lastIndexOf(".txt"));
            String articleBodyText = "";

            try (Scanner reader = new Scanner(articleFile)) {
                while (reader.hasNext()) {
                    articleBodyText += reader.nextLine() + "\n";
                }
            } catch (FileNotFoundException ex) {
                System.err.println("ArticleFile not found: " + ex.getMessage());
            }
            allArticles.add(new Article(articleHeader, articleBodyText, id++));
        }

        return allArticles;
    }
}
