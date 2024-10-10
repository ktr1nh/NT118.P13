package com.example.lab1_nt118;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main); // Liên kết với file activity_second.xml

        Button OpenLinearLayout = findViewById(R.id.bai1);
        OpenLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, LinearActivity.class);
                startActivity(intent);

            }
        });
        Button RelativeLayout = findViewById(R.id.bai2);
        RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, RelativeActivity.class);
                startActivity(intent);
            }
        });
        Button ConstraintLayout = findViewById(R.id.bai3);
        ConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ConstraintActivity.class);
                startActivity(intent);
            }
        });
    }
}

