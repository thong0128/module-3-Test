package service.classRoom;

import model.Classroom;
import service.IServiceDAO;

public interface IClassroomDAO extends IServiceDAO<Classroom> {
    String getNameById(int id);
    int getIdByName(String name);
}
