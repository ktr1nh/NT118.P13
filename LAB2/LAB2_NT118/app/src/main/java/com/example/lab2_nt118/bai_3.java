package com.example.lab2_nt118;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_nt118.Employee;
import com.example.lab2_nt118.EmployeeFulltime;
import com.example.lab2_nt118.EmployeeParttime;
import com.example.lab2_nt118.R;

import java.util.ArrayList;

public class bai_3 extends AppCompatActivity {
    private EditText et_ma_nv, et_ten_nv;
    private RadioGroup rg_loainv;
    private Button btn_nhapnv;
    private ListView lvPerson;
    private TextView tvSelected;

    // Danh sách lưu trữ nhân viên
    private ArrayList<Employee> employees;
    private ArrayAdapter<String> adapter; // Adapter để hiển thị nhân viên dưới dạng chuỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_3);

        // Khởi tạo các thành phần
        et_ma_nv = findViewById(R.id.et_manv);
        et_ten_nv = findViewById(R.id.et_nhap);
        btn_nhapnv = findViewById(R.id.btn_nhapten);
        rg_loainv = findViewById(R.id.rg_loainv);
        lvPerson = findViewById(R.id.lv_person);
        tvSelected = findViewById(R.id.tv_selected);

        // Khởi tạo danh sách nhân viên và adapter
        employees = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        lvPerson.setAdapter(adapter);

        // Sự kiện khi nhấn vào nút "Nhập"
        btn_nhapnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee(); // Gọi hàm để thêm nhân viên mới
            }
        });

        // Sự kiện khi chọn một nhân viên trong danh sách
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String selectedItem = arr.get(i);
                tvSelected.setText("Position: " + i + " - Value: " + selectedItem);
            }
        });
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arr.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    // Phương thức thêm nhân viên mới
    public void addNewEmployee() {
        // Lấy ra ID của Radio Button được checked
        int radId = rg_loainv.getCheckedRadioButtonId();
        String id = et_ma_nv.getText().toString();
        String name = et_ten_nv.getText().toString();

        // Kiểm tra nếu thông tin không đầy đủ hoặc loại nhân viên chưa chọn, thoát mà không có thông báo lỗi
        if (id.isEmpty() || name.isEmpty() || radId == -1) {
            return;
        }

        Employee employee;
        if (radId == R.id.rb_chinhthuc) {
            // Tạo instance là FullTime
            employee = new EmployeeFulltime(id, name);
        } else if (radId == R.id.rb_thoivu) {
            // Tạo instance là PartTime
            employee = new EmployeeParttime(id, name);
        } else {
            return;
        }

        // Thêm nhân viên vào danh sách
        employees.add(employee);

        // Cập nhật danh sách hiển thị
        adapter.add(employee.toString()); // Chuyển đổi đối tượng thành chuỗi và thêm vào adapter
        adapter.notifyDataSetChanged(); // Cập nhật giao diện

        // Xóa thông tin trong EditText
        et_ma_nv.setText("");
        et_ten_nv.setText("");
        rg_loainv.clearCheck(); // Xóa lựa chọn RadioButton
    }
}

