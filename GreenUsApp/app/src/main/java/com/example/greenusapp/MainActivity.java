package com.example.greenusapp;

import android.net.Uri;
import android.os.Bundle;

import com.example.greenusapp.dummy.ArticleList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import articlejar.Article;

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnListFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener {

    private Fragment articleFragment;
    private Fragment videoFragment;
    private Fragment podcastFagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Initializing fragments
        articleFragment = new ArticleFragment();
        videoFragment = new VideoFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_articles:
                    openFragment(articleFragment);
                    return true;
                case R.id.navigation_videos:
                    openFragment(videoFragment);
                    return true;
                case R.id.navigation_podcast:
                    return true;
            }
            return false;
        }
    };

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Article article) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
