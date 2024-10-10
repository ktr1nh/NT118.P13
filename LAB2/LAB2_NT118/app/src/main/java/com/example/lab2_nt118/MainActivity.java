package com.example.lab2_nt118;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab2_nt118.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Giao diện activity_second

        Button BAI1 = findViewById(R.id.btn_bai1);
        Button BAI2 = findViewById(R.id.btn_bai2);
        Button BAI3 = findViewById(R.id.btn_bai3);
        Button BAI4 = findViewById(R.id.btn_bai4);
        Button BAI5 = findViewById(R.id.btn_bai5);
        Button BAI6 = findViewById(R.id.btn_bai6);

        BAI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_1.class);
             startActivity(intent);}
        });


        BAI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_2.class);
                startActivity(intent);}
        });


        BAI3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_3.class);
                startActivity(intent);}
        });


        BAI4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_4.class);
                startActivity(intent);}
        });


        BAI5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_5.class);
                startActivity(intent);}
        });


        BAI6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bai_6.class);
                startActivity(intent);}
        });
    }
}
