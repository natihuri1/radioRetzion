package com.mysmarthome.android.retzion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button ButtonLibrary, myStory, aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPointer();
        initializeMenu();
    }

    private void setPointer() {
        context = this;
        //Backendless
        Backendless.setUrl(Defaults.SERVER_URL);
        //init the backendless by APP ID, API KEY
        Backendless.initApp(context, Defaults.APPLICATION_ID, Defaults.API_KEY);
        final LibraryActivity libraryActivity=new LibraryActivity();

        ButtonLibrary = findViewById(R.id.buttonLibrary);
        ButtonLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LibraryActivity.class));
            }
        });
        myStory = findViewById(R.id.myStory);
        myStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, BlogActivity.class));
            }
        });
        aboutUs = findViewById(R.id.aboutUs);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AboutUsActivity.class));
            }
        });
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
                        break;
                    case R.id.btnMenuBlog:
                        startActivity(new Intent(context, BlogActivity.class));
                        break;
                    case R.id.btnMenuAboutUs:
                        startActivity(new Intent(context, AboutUsActivity.class));
                        break;
                    case R.id.btnMenuEvents:
                        startActivity(new Intent(context, Events.class));
                        break;
                    case R.id.btnShare:
                        shareWithFriends();
                        break;
                    case R.id.btnMenuExit:
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}







