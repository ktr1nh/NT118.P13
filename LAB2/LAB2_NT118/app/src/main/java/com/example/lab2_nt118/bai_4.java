package com.example.lab2_nt118;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class bai_4 extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbIsManager;
    private Button btnAdd;
    private ListView lvEmployees;
    private EmployeeAdapter employeeAdapter;
    private ArrayList<Employee> employeeList;
    private ArrayList<Boolean> isManagerList; // Danh sách để lưu trạng thái isManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_4);

        // Khởi tạo các view
        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbIsManager = findViewById(R.id.cb_manager);
        btnAdd = findViewById(R.id.btn_add);
        lvEmployees = findViewById(R.id.lv_person);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        isManagerList = new ArrayList<>(); // Khởi tạo danh sách isManager
        employeeAdapter = new EmployeeAdapter(this, R.layout.item_employee, employeeList, isManagerList);
        lvEmployees.setAdapter(employeeAdapter);

        // Xử lý sự kiện khi nhấn nút Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String fullName = etFullName.getText().toString();
                boolean isManager = cbIsManager.isChecked(); // Lấy giá trị từ checkbox

                // Tạo đối tượng Employee mới
                Employee newEmployee = new EmployeeFulltime(id, fullName); // Hoặc EmployeePartTime
                employeeList.add(newEmployee);
                isManagerList.add(isManager); // Thêm trạng thái isManager vào danh sách
                employeeAdapter.notifyDataSetChanged(); // Cập nhật adapter

                // Xóa các trường nhập
                etId.setText("");
                etFullName.setText("");
                cbIsManager.setChecked(false);
            }
        });
    }
}

