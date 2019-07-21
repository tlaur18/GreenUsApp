package com.example.greenusapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import articlejar.Article;

public class ReadArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        // Get the Intent that started this activity and extract the article
        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra(MainActivity.ARTICLE_TO_BE_READ);

        // Capture the layout's Toolbar and set the headline to the headline of the article
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(article.getHeadline());
        setSupportActionBar(toolbar);

        // Capture the layout's TextView and set the body text as its text
        TextView textView = findViewById(R.id.article_body);
        textView.setText(article.getBodyText());
    }
}
