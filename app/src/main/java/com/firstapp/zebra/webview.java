package com.firstapp.zebra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView=(WebView)findViewById(R.id.webview);
        //WebSettings webSettings = webView.getSettings();
       // webSettings.setDomStorageEnabled(true);
       // webSettings.setJavaScriptEnabled(true);
        //webSettings.setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://zebraspokenhindi.com/");



    }
}