package com.example.nt118p13_maikimtrinh;

public class StudentData {
    private String id;
    private String name;
    private String birthDate; // Thay đổi từ age sang birthDate
    private String classId;
    private String phone;

    public StudentData() {}

    public StudentData(String id, String name, String birthDate, String classId, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate; // Sử dụng birthDate
        this.classId = classId;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() { // Phương thức getter cho birthDate
        return birthDate;
    }

    public String getClassId() { return classId;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) { // Phương thức setter cho birthDate
        this.birthDate = birthDate;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
