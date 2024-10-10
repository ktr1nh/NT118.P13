package com.example.lab2_nt118;

import androidx.annotation.NonNull;

public class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName (){
        return name;
    }

    // Phương thức tính lương mặc định
    public double tinhLuong(){
        return 0.0;
    }

    // Phương thức xuất thông tin (không có loainv)
    @Override
    public String toString() {
        return id + " - " + name + "-->";
    }



}
