package com.mysmarthome.android.retzion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.mysmarthome.android.retzion.Backendless.Blog;

import java.util.List;

public class PresentStoryDialog extends DialogFragment {
    View dialogView;
    Bundle extras;
    Context context;
    TextView textTitle, textAuthorDetails, textStory;
    ImageButton btnLike, btnComments, btnShare;
    Blog blog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        extras = getArguments();
        //creating the builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //creating customized dialog view
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.story_dialog, null);
        //building the dialog fragments with the builder

        builder.setView(dialogView);
        //id initializes
        viewInitializes();
        propertiesInitializes();

        return builder.create();
    }

    private void viewInitializes() {
        // initializes
        textTitle = dialogView.findViewById(R.id.textTitle);
        textAuthorDetails = dialogView.findViewById(R.id.textAuthorDetails);
        textStory = dialogView.findViewById(R.id.textStory);
        btnLike = dialogView.findViewById(R.id.idLikeImg);
        btnComments = dialogView.findViewById(R.id.idCommentsImg);
        btnShare = dialogView.findViewById(R.id.idShareImg);
        //Backendless Intilialzes
        Backendless.initApp(getContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);


        String sqlStatment = "name" + textTitle + "";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(sqlStatment);


    }

    private void propertiesInitializes() {
        textTitle.setText(extras.getString("name"));

        textStory.setText(extras.getString("story"));
        textAuthorDetails.setText(extras.getString("decsription"));
        textStory.setMovementMethod(new ScrollingMovementMethod());
        //textStory.setText();
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogView.findViewById(R.id.textStory).getVisibility() == View.VISIBLE) {
                    //invisible
                    dialogView.findViewById(R.id.textStory).setVisibility(View.INVISIBLE);
                    dialogView.findViewById(R.id.textAuthorDetails).setVisibility(View.GONE);
                    //visible
                    dialogView.findViewById(R.id.commentsList).setVisibility(View.VISIBLE);
                    dialogView.findViewById(R.id.newCommentContainer).setVisibility(View.VISIBLE);
                } else {
                    //visible
                    dialogView.findViewById(R.id.textStory).setVisibility(View.VISIBLE);
                    dialogView.findViewById(R.id.textAuthorDetails).setVisibility(View.VISIBLE);
                    //invisible
                    dialogView.findViewById(R.id.commentsList).setVisibility(View.INVISIBLE);
                    dialogView.findViewById(R.id.newCommentContainer).setVisibility(View.GONE);
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try Retzion radio for your android ");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Im using this radio for Android and I recommend it. Click here: http://www.retzion.com");

                Intent chooserIntent = Intent.createChooser(shareIntent, "Share with");
                chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(chooserIntent);
            }

        });
    }
}
