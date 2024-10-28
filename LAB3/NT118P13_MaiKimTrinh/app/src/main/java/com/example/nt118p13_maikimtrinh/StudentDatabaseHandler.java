package com.example.nt118p13_maikimtrinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseHandler extends SQLiteOpenHelper {
    // Phiên bản database và tên
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsManager";

    // Tên bảng và các cột
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_CLASS_ID = "class_id"; // Class ID thêm vào đây
    private static final String KEY_PHONE = "phone";

    public StudentDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_BIRTHDAY + " TEXT,"
                + KEY_CLASS_ID + " TEXT," // Thêm class_id vào bảng
                + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    // Cập nhật phiên bản database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Tạo lại bảng
        onCreate(db);
    }

    // Thêm một sinh viên mới
    public void addStudent(StudentData student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_BIRTHDAY, student.getBirthDate());
        values.put(KEY_CLASS_ID, student.getClassId());
        values.put(KEY_PHONE, student.getPhone());

        // Chèn dòng dữ liệu vào bảng
        db.insert(TABLE_STUDENTS, null, values);
        db.close(); // Đóng kết nối với database
    }

    // Lấy tất cả sinh viên
    public List<StudentData> getAllStudents() {
        List<StudentData> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StudentData student = new StudentData(
                        cursor.getString(0), // ID
                        cursor.getString(1), // Name
                        cursor.getString(2), // BirthDate
                        cursor.getString(3), // Class ID
                        cursor.getString(4)  // Phone
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }

    // Lấy một sinh viên theo ID
    public StudentData getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{KEY_ID, KEY_NAME, KEY_BIRTHDAY, KEY_CLASS_ID, KEY_PHONE},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        StudentData student = new StudentData(
                cursor.getString(0), // ID
                cursor.getString(1), // Name
                cursor.getString(2), // BirthDate
                cursor.getString(3), // Class ID
                cursor.getString(4)  // Phone
        );
        cursor.close();
        db.close();
        return student;
    }

    // Cập nhật một sinh viên
    public int updateStudent(StudentData student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_BIRTHDAY, student.getBirthDate());
        values.put(KEY_CLASS_ID, student.getClassId());
        values.put(KEY_PHONE, student.getPhone());

        return db.update(TABLE_STUDENTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }

    // Xóa một sinh viên
    public void deleteStudent(StudentData student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }

    // Lấy số lượng sinh viên
    public int getStudentsCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
}
