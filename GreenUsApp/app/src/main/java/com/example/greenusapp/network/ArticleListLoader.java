package com.example.greenusapp.network;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.greenusapp.MainActivity.ARTICLE_DIRECTORY_URL;

public class ArticleListLoader extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            Document directory = Jsoup.connect(ARTICLE_DIRECTORY_URL).timeout(0).get();
            List<String> articleNames = new ArrayList<>();

            for (Element file : directory.getElementsByAttributeValueEnding("href", ".txt")) {
                String stringToAdd = file.attr("href");
                stringToAdd = stringToAdd.replace("%20", " ");
                stringToAdd = stringToAdd.substring(0, stringToAdd.lastIndexOf(".txt"));
                articleNames.add(stringToAdd);
            }

            return articleNames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
