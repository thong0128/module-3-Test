package service.classRoom;

import config.ConnectionJBDC;
import model.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO implements IClassroomDAO{

    public static final String SELECT_NAME_FROM_CLASSROOM_WHERE_ID = "select name from classroom where id = ?";
    public static final String SELECT_ALL_CLASSROOM = "select * from classroom";
    public static final String SELECT_ID_FROM_CLASSROOM_WHERE_NAME_LIKE = "select id from classroom where name like ?";
    Connection connection = ConnectionJBDC.getConnection();
    @Override
    public List<Classroom> getAll() {
        List<Classroom> classroomList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLASSROOM);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Classroom classroom = new Classroom(id, name);
                classroomList.add(classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classroomList;
    }

    @Override
    public Classroom findByID(int id) {
        return null;
    }

    @Override
    public void save(Classroom classroom) {

    }

    @Override
    public void delete(Classroom classroom) {

    }

    @Override
    public void update(Classroom classroom) {

    }

    @Override
    public String getNameById(int id) {
        String name = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_NAME_FROM_CLASSROOM_WHERE_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public int getIdByName(String name) {
        int id=0;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ID_FROM_CLASSROOM_WHERE_NAME_LIKE);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
