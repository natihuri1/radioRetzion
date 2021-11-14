package com.mysmarthome.android.retzion;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mysmarthome.android.retzion.Backendless.RadioBroadcasts;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class LibraryActivity extends AppCompatActivity implements BradcastServiesInterface {
    Context context;
    ListView lstLibrary;
    List<BroadcastModel> libraryList;
    BroadcastServies service;
    TextView songName;
    String vodName1;

    final String BASE_URL = "http://be.repoai.com:5080/WebRTCAppEE/streams/home/";
    ExoPlayer.EventListener eventListener = new ExoPlayer.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            Log.i(TAG, "onTimelineChanged");
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            Log.i(TAG, "onTracksChanged");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.i(TAG, "onLoadingChanged");
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            Log.i(TAG, "onPlayerStateChanged: playWhenReady = " + String.valueOf(playWhenReady)
                    + " playbackState = " + playbackState);
            switch (playbackState) {
                case ExoPlayer.STATE_ENDED:
                    Log.i(TAG, "Playback ended!");
                    //Stop playback and return to start position
                    setPlayPause(false);
                    exoPlayer.seekTo(0);
                    break;
                case ExoPlayer.STATE_READY:
                    Log.i(TAG, "ExoPlayer ready! pos: " + exoPlayer.getCurrentPosition()
                            + " max: " + stringForTime((int) exoPlayer.getDuration()));
                    setProgress();
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    Log.i(TAG, "Playback buffering!");
                    break;
                case ExoPlayer.STATE_IDLE:
                    Log.i(TAG, "ExoPlayer idle!");
                    break;
            }
        }


        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.i(TAG, "onPlaybackError: " + error.getMessage());
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.i(TAG, "onPositionDiscontinuity");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        setpointer();
        initializeMenu();
    }

    private void setpointer() {
        lstLibrary = findViewById(R.id.lstLibrary);
        this.context = this;
        this.service = new BroadcastServies();
        this.service.myInterface = this;
        this.service.execute();

        songName = findViewById(R.id.songName);
        btnPlay = findViewById(R.id.play_button_main);

        btnPlay.setImageResource(android.R.drawable.ic_media_play);

    }

    @Override

    public void broadcastReturn() {

        final AdapterLibrary adapterLibrary = new AdapterLibrary(this.service.mylist, this);
        lstLibrary.setAdapter(adapterLibrary);
        lstLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vodName1 = (String) adapterLibrary.getItem(position);

                if (exoPlayer != null) exoPlayer.release();
                prepareExoPlayerFromURL(Uri.parse(BASE_URL + vodName1));
                setPlayPause(true);
            }
        });
    }


    SeekBar seekPlayerProgress;
    Handler handler;
    static ImageButton btnPlay;
    TextView txtCurrentTime, txtEndTime;
    boolean isPlaying = false;

    final String TAG = "MainActivity";
    SimpleExoPlayer exoPlayer;


    public void prepareExoPlayerFromURL(Uri uri) {


        TrackSelector trackSelector = new DefaultTrackSelector();

        LoadControl loadControl = new DefaultLoadControl();

        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "exoplayer2example"), null);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource audioSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
        exoPlayer.addListener(eventListener);

        exoPlayer.prepare(audioSource);
        initMediaControls();
    }

    private void initMediaControls() {
        initPlayButton();
        initSeekBar();
        initTxtTime();

    }


    private void initPlayButton() {
        btnPlay.requestFocus();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayPause(!isPlaying);
            }
        });
    }

    public void pausePlaYer() {
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.getPlaybackState();

    }

    public void startPlaYer() {


        exoPlayer.setPlayWhenReady(true);
        exoPlayer.getPlaybackState();
        btnPlay.setImageResource(android.R.drawable.ic_media_pause);


    }


    private void setPlayPause(boolean play) {
        isPlaying = play;

        if (isPlaying) {

            startPlaYer();
            songName.setText(vodName1);

        } else {
            setProgress();
            pausePlaYer();
            btnPlay.setImageResource(android.R.drawable.ic_media_play);


        }
    }

    private void initTxtTime() {
        txtCurrentTime = findViewById(R.id.endTime);
    }

    private String stringForTime(int timeMs) {
        StringBuilder mFormatBuilder;
        Formatter mFormatter;
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    private void setProgress() {
        seekPlayerProgress.setProgress(0);
        seekPlayerProgress.setMax((int) exoPlayer.getDuration() / 1000);
        txtCurrentTime.setText(stringForTime((int) exoPlayer.getCurrentPosition()));

        if (handler == null) handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (exoPlayer != null && isPlaying) {
                    seekPlayerProgress.setMax((int) exoPlayer.getDuration() / 1000);
                    int mCurrentPosition = (int) exoPlayer.getCurrentPosition() / 1000;
                    seekPlayerProgress.setProgress(mCurrentPosition);
                    txtCurrentTime.setText(stringForTime((int) exoPlayer.getCurrentPosition()));

                    handler.postDelayed(this, 1000);
                }
            }
        });
    }


    private void initSeekBar() {
        seekPlayerProgress = findViewById(R.id.seekBar3);
        seekPlayerProgress.requestFocus();

        seekPlayerProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) {

                    return;
                }

                exoPlayer.seekTo(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekPlayerProgress.setMax(0);
        seekPlayerProgress.setMax((int) exoPlayer.getDuration() / 1000);

    }

    public void initializeMenu() {
        context = this;
        Toolbar toolbar = findViewById(R.id.toolBar);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        (findViewById(R.id.toolbar_drawer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.btnMenuLibrary:
                        startActivity(new Intent(context, LibraryActivity.class));
                        exoPlayer.stop();
                        break;
                    case R.id.btnMenuBlog:
                        startActivity(new Intent(context, BlogActivity.class));
                       // exoPlayer.stop();
                        if (exoPlayer.getPlayWhenReady()){
                            exoPlayer.stop();
                        }

                        break;
                    case R.id.btnMenuAboutUs:
                        startActivity(new Intent(context, AboutUsActivity.class));
                        exoPlayer.stop();

                        break;
                    case R.id.btnMenuEvents:
                        startActivity(new Intent(context, Events.class));
                        exoPlayer.stop();

                        break;
                    case R.id.btnShare:
                        shareWithFriends();
                        break;

                    case R.id.btnMenuExit:
                        exoPlayer.stop();
                        finish();
                        break;
                    default:
                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }
    public void shareWithFriends() {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try Retzion radio for your android ");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Im using this radio for Android and I recommend it. Click here: http://www.retzion.com");

        Intent chooserIntent = Intent.createChooser(shareIntent, "Share with");
        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }
    public void onBackPressed() {
        exoPlayer.stop();
        finish();
    }



}
