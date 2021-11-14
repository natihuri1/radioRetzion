package com.mysmarthome.android.retzion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mysmarthome.android.retzion.Backendless.Blog;

import java.util.Date;

public class NewStoryActivity extends AppCompatActivity {

    private Context context;
    Blog blog;
    public TextView namePublisher,newDesc,newStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        setPointer();
    }

    private void setPointer() {
        this.context = this;
        blog = new Blog();
        Button btnSubmit, btnCancel;
        namePublisher = findViewById(R.id.inputTitle);
        newDesc = findViewById(R.id.inputDesc);
        newStory = findViewById(R.id.inputStory);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        //set actions
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blog.setName(namePublisher.getText().toString());
                blog.setDescription(newDesc.getText().toString());
                blog.setStory(newStory.getText().toString());
                blog.setDateOfStory(blog.getCreated());
                blog.saveAsync(new AsyncCallback<Blog>() {
                    @Override
                    public void handleResponse(Blog response) {
                        Toast.makeText(context, "upload succeeded", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(context, "An error occurred,try again!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "cancel pressed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}


