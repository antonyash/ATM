package main;

import dao.GenericDao;
import model.*;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;
import view.MainView;

import java.util.LinkedList;
import java.util.List;

public class Main {
  private Client currentClient;
  public static void main(String[] args){
    Atm.setBillReciever(new BillReciever(40));
    Atm.setDispancer(new Dispancer(40));
    Atm.setCardReciever(new CardReciever());
    Atm.setPrinter(new Printer());
    new MainView().mainMenu();
  }
}
