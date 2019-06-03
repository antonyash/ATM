package service;

import dao.GenericDao;

import java.io.Serializable;
import java.util.List;

public class Service<T, P extends Serializable> {
  public Service(Class type) {
    this.dao = new GenericDao<T, P>(type);
  }

  private GenericDao<T, P> dao;

  public T get(P id) {
    return dao.getByid(id);
  }

  public void create(T t){
    dao.create(t);
  }

  public void update(T t){
    dao.update(t);
  }

  public void delete(T t){
    dao.delete(t);
  }

  public List<T> getAll(){
    return dao.getAll();
  }
}
