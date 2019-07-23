package com.example.greenusapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import articlejar.Article;

public class ReadArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        // Get the Intent that started this activity and extract the article
        Intent intent = getIntent();
        final Article article = (Article) intent.getSerializableExtra(MainActivity.ARTICLE_TO_BE_READ);
        // Loads article content from web server
//        AsyncTask asyncTask = new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] objects) {
//                try {
//                    String urlString = ArticleFragment.ARTICLE_DIRECTORY_URL + "/" + article.getHeadline().replace(" ", "%20");
//                    URL url = new URL(urlString);
//
//                    // read text returned by server
//                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//
//                    String bodyText = "";
//                    String line;
//                    while ((line = in.readLine()) != null) {
//                        bodyText += line + "\n";
//                    }
//                    article.setBodyText(bodyText);
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        };
//
//        asyncTask.execute();
//
//        while (asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
//        }

        // Capture the layout's Toolbar and set the headline to the headline of the article
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(article.getHeadline());
        setSupportActionBar(toolbar);

        // Capture the layout's TextView and set the body text as its text
        TextView textView = findViewById(R.id.article_body);
        textView.setText(article.getBodyText());
    }
}
