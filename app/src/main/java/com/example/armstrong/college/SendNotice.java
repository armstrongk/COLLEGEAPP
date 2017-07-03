package com.example.armstrong.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SendNotice extends AppCompatActivity {

    private static WebView webView;
    private static EditText editText;
    private static Button button_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        openurl();
    }
    public void openurl(){
        webView = (WebView)findViewById(R.id.webView);
        editText = (EditText)findViewById(R.id.editText);
        button_sub = (Button)findViewById(R.id.button);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl("file:///android_asset/mypage.html");
                // webView.loadUrl(url);
            }
        });

    }
}
