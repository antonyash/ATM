package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

public class GenericDao<T, P extends Serializable > {
  public GenericDao(Class<T> type) {
    this.type = type;
  }

  private Class<T> type;

  public T getByid(P id){

    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    T temp = session.get(type, id);
    session.close();
    return temp;

  }

  public void create(T t){
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.save(t);
    tx1.commit();
    session.close();
  }

  public void update(T t){
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.update(t);
    tx1.commit();
    session.close();
  }

  public void delete(T t){
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.delete(t);
    tx1.commit();
    session.close();
  }

  public List<T> getAll(){
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    String name = "from "+type.getName().substring(type.getName().lastIndexOf(".")+1);

    List<T> users = session.createQuery(name).list();
    session.close();
    return users;
  }
}
