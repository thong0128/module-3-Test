package service.student;

import model.Student;
import service.IServiceDAO;

import java.util.List;

public interface IStudentDAO extends IServiceDAO<Student> {
    void deleteById(int id);

    List<Student> findByName(String name);
}
