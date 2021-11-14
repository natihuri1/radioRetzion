package com.mysmarthome.android.retzion;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AboutUsActivity extends AppCompatActivity {
private WebView webView;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_usctivity);

        setPointer();
    }

    private void setPointer() {
         context=this;
        webView=findViewById(R.id.webViewAboutUs);
       WebSettings webSettings = webView.getSettings();
       webSettings.setJavaScriptEnabled(true);
       webView.loadUrl("https://www.etzion.besite.org.il/about");
    }


}
