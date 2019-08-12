package com.example.greenusapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import articlejar.Article;

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnListFragmentInteractionListener, VideoFragment.OnPlayButtonPressedListener {

    private Fragment articleFragment;
    private Fragment videoFragment;
    private Fragment podcastFagment;

    public static final String ARTICLE_TO_BE_READ = "com.example.greenusapp.ARTICLE";

    public static final String ARTICLE_DIRECTORY_URL = "http://80.210.71.238:8081/Content/Articles";
    //    public static final String ARTICLE_DIRECTORY_URL = "http://192.168.1.159:8081/Content/Articles";
    public static final String VIDEO_DIRECTORY_URL = "http://80.210.71.238:8081/Content/Videos";
//    public static final String VIDEO_DIRECTORY_URL = "http://192.168.1.159:8081/Content/Articles";

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
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof VideoFragment) {
            VideoFragment videoFragment = (VideoFragment) fragment;
            videoFragment.setOnPlayButtonPressedListener(this);
        }
    }

    @Override
    public void onListFragmentInteraction(Article article) {
        Intent intent = new Intent(this, ReadArticleActivity.class);
        intent.putExtra(ARTICLE_TO_BE_READ, article);
        startActivity(intent);
    }

    @Override
    public void onPlayButtonPressed(Uri uri) {
        String URL = VIDEO_DIRECTORY_URL + "/samplevid.mp4";
//        setContentView(R.layout.mediaplayer);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }

        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(URL);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();
    }
}
