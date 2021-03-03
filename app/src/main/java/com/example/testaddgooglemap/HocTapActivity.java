package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HocTapActivity extends AppCompatActivity {
    Button button_dangkyhoc, button_lichhoc, button_lichthi;
    Toolbar toolbar;
    ImageView button_back;
    TextView textView_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_tap);

        anhxa();

        textView_title.setText("Học tập");

        ClickButtonEvent();
    }

    private void ClickButtonEvent() {
        button_dangkyhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocTapActivity.this, DangKyHocActivity.class));
            }
        });

        button_lichhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocTapActivity.this, XemLichHocActivity.class));
            }
        });

        button_lichthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocTapActivity.this, XemLichThiActivity.class));
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocTapActivity.this, MainActivity.class));
            }
        });

    }

    private void anhxa() {
        textView_title = findViewById(R.id.textView_appbar_title);
        button_back = findViewById(R.id.button_back);
        button_dangkyhoc = findViewById(R.id.button_dangkyhoc);
        button_lichhoc = findViewById(R.id.button_xemlichhoc);
        button_lichthi = findViewById(R.id.button_xemlichthi);
    }
}