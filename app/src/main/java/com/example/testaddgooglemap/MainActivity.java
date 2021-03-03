package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout hoctap,mangXH,tintuc,bando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        ClickItemEvents();

    }

    private void ClickItemEvents() {
        hoctap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HocTapActivity.class);
                startActivity(intent);
            }
        });

        mangXH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MangXaHoiActivity.class);
                startActivity(intent);
            }
        });

        tintuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DocBaoActivity.class);
                startActivity(intent);
            }
        });

        bando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        hoctap = findViewById(R.id.menu_hoctap);
        mangXH = findViewById(R.id.menu_mangxahoi);
        tintuc = findViewById(R.id.menu_tintuc);
        bando = findViewById(R.id.menu_bando);
    }

}