package com.example.lab2_nt118;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bai_6 extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbIsManager;
    private Button btnAdd;
    private RecyclerView rvEmployees;
    private EmployeeRecyclerAdapter employeeAdapter;
    private ArrayList<Employee> employeeList;
    private ArrayList<Boolean> isManagerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_6);

        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbIsManager = findViewById(R.id.cb_isManager);
        btnAdd = findViewById(R.id.btn_add);
        rvEmployees = findViewById(R.id.rv_employees);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        isManagerList = new ArrayList<>();

        // Khởi tạo adapter cho RecyclerView
        employeeAdapter = new EmployeeRecyclerAdapter(this, employeeList, isManagerList);

        // Đặt LayoutManager cho RecyclerView
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        // Gán adapter cho RecyclerView
        rvEmployees.setAdapter(employeeAdapter);

        // Xử lý sự kiện khi nhấn nút Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String fullName = etFullName.getText().toString();
                boolean isManager = cbIsManager.isChecked();

                // Tạo đối tượng Employee mới
                Employee newEmployee = new EmployeeFulltime(id, fullName); // Hoặc EmployeePartTime
                employeeList.add(newEmployee);
                isManagerList.add(isManager);
                employeeAdapter.notifyDataSetChanged(); // Cập nhật adapter

                // Xóa các trường nhập
                etId.setText("");
                etFullName.setText("");
                cbIsManager.setChecked(false);
            }
        });
    }
}
