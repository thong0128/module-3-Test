package service.student;

import config.ConnectionJBDC;
import model.Student;
import service.classRoom.ClassroomDAO;
import service.classRoom.IClassroomDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO{
    private static final String SELECT_ALL_STUDENT = "select * from student";
    public static final String NEW_STUDENT = "INSERT INTO student (name, birth, email, address, phone,class_id) VALUES (?, ?, ?, ?, ?,?)";
    public static final String DELETE_FROM_STUDENT_WHERE_ID = "delete from student where id = ?";
    public static final String SELECT_FROM_STUDENT_WHERE_ID = "select * from student where id = ?";
    public static final String UPDATE_STUDENT_SET_NAME_EMAIL_BIRTH_ADDRESS_PHONE_CLASS_ID_WHERE_ID = "update student set name = ?, email = ?, birth = ?, address = ?, phone = ?, class_id = ? where id = ?";
    public static final String SELECT_FROM_STUDENT_WHERE_NAME_LIKE = "select * from student where name like ?";
    Connection connection = ConnectionJBDC.getConnection();
    IClassroomDAO classroomDAO = new ClassroomDAO();
    @Override
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("birth");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                int class_id = resultSet.getInt("class_id");
                String classroom = classroomDAO.getNameById(class_id);
                Student student = new Student(id, name, dob, email, address,phone, classroom);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findByID(int id) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_STUDENT_WHERE_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("birth");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                int class_id = resultSet.getInt("class_id");
                String classroom = classroomDAO.getNameById(class_id);
                student = new Student(id, name, dob, email, address,phone, classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void save(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(NEW_STUDENT);
            statement.setString(1, student.getName());
            statement.setString(2,student.getDob());
            statement.setString(3,student.getEmail());
            statement.setString(4,student.getAddress());
            statement.setString(5,student.getPhone());
            statement.setInt(6,classroomDAO.getIdByName(student.getClassroom()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
    }

    @Override
    public void update(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SET_NAME_EMAIL_BIRTH_ADDRESS_PHONE_CLASS_ID_WHERE_ID);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getDob());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getPhone());
            statement.setInt(6, classroomDAO.getIdByName(student.getClassroom()));
            statement.setInt(7,student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_STUDENT_WHERE_NAME_LIKE);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String Sname = resultSet.getString("name");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("birth");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String classroom = classroomDAO.getNameById(resultSet.getInt("class_id"));
                Student student = new Student(id, Sname, dob, email, address,phone, classroom);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_FROM_STUDENT_WHERE_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
