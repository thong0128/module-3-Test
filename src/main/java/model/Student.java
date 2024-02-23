package model;

public class Student {
    private int id;
    private String name;
    private String dob;
    private String email;
    private String address;
    private String Phone;
    private String classroom;

    public Student() {
    }

    public Student(int id, String name, String dob, String email, String address, String phone, String classroom) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        Phone = phone;
        this.classroom = classroom;
    }

    public Student(String name, String dob, String email, String address, String phone, String classroom) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        Phone = phone;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
