package service;

import java.util.List;

public interface IServiceDAO<T> {
    List<T> getAll();
    T findByID(int id);
    void save(T t);
    void delete(T t);
    void update(T t);
}
