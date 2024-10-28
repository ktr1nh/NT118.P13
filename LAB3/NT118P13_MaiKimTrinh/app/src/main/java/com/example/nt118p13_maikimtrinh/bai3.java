package com.example.nt118p13_maikimtrinh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class bai3 extends AppCompatActivity implements StudentAdapter.OnStudentClickListener {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<StudentData> studentList;
    private StudentDatabaseHandler db;

    private EditText etStudentId, etStudentName, etStudentBirthday, etStudentClassId, etStudentPhone;
    private Button btnAddStudent, btnDeleteStudent, btnEditStudent;

    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai3);

        db = new StudentDatabaseHandler(this);
        studentList = db.getAllStudents();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter = new StudentAdapter(this, studentList, this);
        recyclerView.setAdapter(studentAdapter);

        etStudentId = findViewById(R.id.et_student_id);
        etStudentName = findViewById(R.id.et_student_name);
        etStudentBirthday = findViewById(R.id.et_student_birthday);
        etStudentClassId = findViewById(R.id.et_student_classid); // Thay đổi ở đây
        etStudentPhone = findViewById(R.id.et_student_phone);

        btnAddStudent = findViewById(R.id.btn_add_student);
        btnDeleteStudent = findViewById(R.id.btn_delete_student);
        btnEditStudent = findViewById(R.id.btn_edit_student);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent();
            }
        });

        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editStudent();
            }
        });
    }

    private void addStudent() {
        String studentId = etStudentId.getText().toString().trim();
        String name = etStudentName.getText().toString().trim();
        String birthday = etStudentBirthday.getText().toString().trim();
        String classId = etStudentClassId.getText().toString().trim(); // Lấy classId từ EditText
        String phone = etStudentPhone.getText().toString().trim();

        if (studentId.isEmpty() || name.isEmpty() || birthday.isEmpty() || classId.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please insert full information!", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentData newStudent = new StudentData(studentId, name, birthday, classId, phone); // Sử dụng classId
        db.addStudent(newStudent);
        studentList.add(newStudent);
        studentAdapter.notifyDataSetChanged();

        resetForm();
        Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show();
    }

    private void deleteStudent() {
        if (selectedPosition >= 0) {
            StudentData studentToDelete = studentList.get(selectedPosition);
            db.deleteStudent(studentToDelete);
            studentList.remove(selectedPosition);
            studentAdapter.notifyDataSetChanged();
            selectedPosition = -1;
            resetForm();
            Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Choose a student to delete!", Toast.LENGTH_SHORT).show();
        }
    }

    private void editStudent() {
        if (selectedPosition >= 0) {
            StudentData studentToEdit = studentList.get(selectedPosition);
            studentToEdit.setId(etStudentId.getText().toString().trim());
            studentToEdit.setName(etStudentName.getText().toString().trim());
            studentToEdit.setBirthDate(etStudentBirthday.getText().toString().trim());
            studentToEdit.setClassId(etStudentClassId.getText().toString().trim()); // Cập nhật classId
            studentToEdit.setPhone(etStudentPhone.getText().toString().trim());

            db.updateStudent(studentToEdit);
            studentAdapter.notifyDataSetChanged();
            selectedPosition = -1;
            resetForm();
            Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Choose a student to edit!", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetForm() {
        etStudentId.setText("");
        etStudentName.setText("");
        etStudentBirthday.setText("");
        etStudentClassId.setText(""); // Reset classId
        etStudentPhone.setText("");
    }

    @Override
    public void onStudentClick(int position) {
        selectedPosition = position;
        StudentData clickedStudent = studentList.get(position);
        etStudentId.setText(clickedStudent.getId());
        etStudentName.setText(clickedStudent.getName());
        etStudentBirthday.setText(clickedStudent.getBirthDate());
        etStudentClassId.setText(clickedStudent.getClassId()); // Cập nhật để lấy classId
        etStudentPhone.setText(clickedStudent.getPhone());
    }

    @Override
    public void onStudentLongClick(int position) {
        StudentData studentToDelete = studentList.get(position);
        db.deleteStudent(studentToDelete);
        studentList.remove(position);
        studentAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Student deleted: " + studentToDelete.getName(), Toast.LENGTH_SHORT).show();
    }
}
