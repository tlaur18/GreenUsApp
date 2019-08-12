package com.example.greenusapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.greenusapp.network.ArticleContentLoader;

import java.util.concurrent.ExecutionException;

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
        try {
            String articleBodyText = (String) new ArticleContentLoader().execute(article.getHeadline()).get();
            article.setBodyText(articleBodyText);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Capture the layout's Toolbar and set the headline to the headline of the article
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(article.getHeadline());
        setSupportActionBar(toolbar);

        // Capture the layout's TextView and set the body text as its text
        TextView textView = findViewById(R.id.article_body);
        textView.setText(article.getBodyText());
    }
}
