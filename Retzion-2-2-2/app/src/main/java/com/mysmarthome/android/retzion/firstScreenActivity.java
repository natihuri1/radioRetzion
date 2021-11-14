package com.mysmarthome.android.retzion;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class firstScreenActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(firstScreenActivity.this,MainActivity.class);
                firstScreenActivity.this.startActivity(mainIntent);
                firstScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
