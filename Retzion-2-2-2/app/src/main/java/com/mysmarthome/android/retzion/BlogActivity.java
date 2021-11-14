

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mysmarthome.android.retzion.Backendless.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogActivity extends AppCompatActivity {
    private Context context;
    private ListView listView;
    List<Blog> stories;
    private PresentStoryDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        setPointer();
        initializeMenu();
    }

    private void setPointer() {
        this.context = this;
        this.listView = findViewById(R.id.listView);
        this.dialog = new PresentStoryDialog();
        TextView textButton = findViewById(R.id.textButton);
        Backendless.Data.of(Blog.class).find(new AsyncCallback<List<Blog>>() {
            @Override
            public void handleResponse(List<Blog> response) {
                stories = response;
            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
        StoriesListAdapter myAdapter = new StoriesListAdapter(context);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //creating extras
                Bundle extras = new Bundle();
                extras.putString("projectId", stories.get(position).getObjectId());
                extras.putString("name", stories.get(position).getName());
                extras.putString("description", stories.get(position).getDescription());
                extras.putString("story", stories.get(position).getStory());
                dialog.setArguments(extras);
                dialog.show(getSupportFragmentManager(), "story_dialog");
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "text clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, NewStoryActivity.class);
                startActivity(intent);
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
}


