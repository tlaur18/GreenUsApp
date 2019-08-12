package com.example.greenusapp.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.greenusapp.MainActivity.ARTICLE_DIRECTORY_URL;

public class ArticleContentLoader extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        String headLine = (String) objects[0];
        String bodyText = "";
        try {
            //Create a URL for the desired article
            URL url = new URL(ARTICLE_DIRECTORY_URL + "/" + headLine.replace(" ", "%20") + ".txt");
            //First open the connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000); // timing out in a minute

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            while ((str = in.readLine()) != null) {
                bodyText += str + System.getProperty("line.separator");
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bodyText;
    }
}
