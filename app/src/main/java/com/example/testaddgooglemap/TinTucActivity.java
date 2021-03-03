package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TinTucActivity extends AppCompatActivity {
    WebView webView;
    ImageView button_back;
    TextView textView_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        webView = findViewById(R.id.webview1);
        textView_title = findViewById(R.id.textView_appbar_title);
        button_back = findViewById(R.id.button_back);

        textView_title.setText("Tin tức");

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TinTucActivity.this, DocBaoActivity.class));
            }
        });

        Intent intent = getIntent();
        String linktin = intent.getStringExtra("linktintuc");
        Toast.makeText(this, linktin, Toast.LENGTH_SHORT).show();
        webView.loadUrl(linktin);
    }
}