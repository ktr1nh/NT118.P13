package com.example.nt118p13_maikimtrinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_bai1 = findViewById(R.id.btn_bai1);
        Button btn_bai2 = findViewById(R.id.btn_bai2);
        Button btn_bai3 = findViewById(R.id.btn_bai3);

        btn_bai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bai1.class);
                startActivity(intent);
            }
        });

        btn_bai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bai2.class);
                startActivity(intent);
            }
        });

        btn_bai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bai3.class);
                startActivity(intent);
            }
        });
    }

}