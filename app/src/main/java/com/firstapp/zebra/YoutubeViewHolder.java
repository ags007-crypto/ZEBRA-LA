package com.firstapp.zebra;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {

    WebView webView;
    Button button;
    public YoutubeViewHolder(View itemView){
        super(itemView);
        webView= (WebView) itemView.findViewById(R.id.video_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }
}

