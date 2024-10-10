package com.example.lab2_nt118;

public class EmployeeFulltime extends Employee {

    public EmployeeFulltime(String id, String name) {
        super(id, name);
    }

    // Ghi đè phương thức TinhLuong để tính lương cho nhân viên toàn thời gian
    @Override
    public double tinhLuong() {
        return 500.0;
    }

    // Ghi đè phương thức toString để xuất thông tin đầy đủ
    @Override
    public String toString() {
        return super.toString() + " FullTime = " + tinhLuong();

    }
}

