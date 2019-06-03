package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
  private static SessionFactory sessionFactory;

  private HibernateSessionFactoryUtil() {}

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(MoneyCase.class);

        configuration.addAnnotatedClass(Bank.class);
        configuration.addAnnotatedClass(BankAccount.class);
        configuration.addAnnotatedClass(Card.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(ClientBankAccount.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyBankAccount.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Encoder.class);
        configuration.addAnnotatedClass(Mobile.class);
        configuration.addAnnotatedClass(Atm.class);
        configuration.addAnnotatedClass(PaymentSystem.class);
        configuration.addAnnotatedClass(Transaction.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

      } catch (Exception e) {
        System.out.println("Исключение!" + e);
      }
    }
    return sessionFactory;
  }
}