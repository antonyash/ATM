package view;

import controller.AtmController;
import controller.AuthConroller;
import controller.MainController;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtmView {
  boolean check;

  public void menu(Client client) {
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);
    check = true;

    System.out.println("\nВыберите банкомат: ");
    List<Atm> atms = mainController.getAllAtms();
    for (int i = 0; i < atms.size(); i++) {
      System.out.println(i + "." + atms.get(i).getId() + " " + atms.get(i).getBank().getName());
    }

    Atm atm = atms.get(in.nextInt());

    System.out.println("\nДобро пожаловать!");

    Card card = auth(atm, client);

    while (check) {

      System.out.println("\nТекущий пользователь:" +
              client.getName() + " " + client.getSurname() +
              " Банкомат " + atm.getId() + " " + atm.getBank().getName() +
              "\n1.Cнять деньги\n" +
              "2.Положить деньги\n" +
              "3.Перевести деньги\n" +
              "4.Оплатить счет\n" +
              "5.Пополнить мобильный\n" +
              "6.Узнать баланс\n" +
              "0.Выход\n");

      int input = in.nextInt();

      switch (input) {
        case 1: {
          popMoney(atm, card);
          break;
        }

        case 2: {
          pushMoney(atm, card);
          break;
        }

        case 3: {
          transferMoney(atm, card);
          break;
        }

        case 4: {
          payCheck(atm, card);
          break;
        }

        case 5: {
          payMobile(atm, card);
          break;
        }

        case 6: {
          System.out.println(card.getClientBankAccount().getBalance());
          break;
        }
        case 0: {
          check = false;
          break;
        }
      }
    }
  }

  private Card auth(Atm atm, Client client) {
    AuthConroller conroller = new AuthConroller();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);

    System.out.println("Выберите карту:");

    for (int i = 0; i < client.getAccounts().size(); i++) {
      System.out.println(i + ". " + client.getAccounts().get(i).getCard().getNumber());
    }

    Card card = client.getAccounts().get(in.nextInt()).getCard();
    System.out.println("PIN: ");

  if(!conroller.checkCard(atm, card))
  {
    System.out.println("Карта недействительна");
    check= false;
  }
  else if(!conroller.checkPin(atm, card, in.nextInt()))
  {
    System.out.println("Неверный PIN");
    check = false;
  }
    return card;

  }

  private void popMoney(Atm atm, Card card) {
    AtmController atmController = new AtmController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);
    System.out.println("Введите сумму: ");
    if (atmController.popMoney(atm, card, in.nextInt()))
      System.out.println("Деньги выданы");
    else System.out.println("Произошла ошибка");

  }

  private void pushMoney(Atm atm, Card card) {
    AtmController atmController = new AtmController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);

    System.out.println("Введите кол-во каждой купюры: ");

    List<Bill> bills = new ArrayList<>();

    for (BillType type : BillType.values()
    ) {
      System.out.println(type.getValue());
      int count = in.nextInt();

      for (int i = 0; i < count; i++) {
        bills.add(new Bill(type));
      }
    }

    if (atmController.pushMoney(atm, bills, card))
      System.out.println("Деньги зачислены на счет");
    else System.out.println("Произошла ошибка");
  }

  private void transferMoney(Atm atm, Card card) {
    AtmController atmController = new AtmController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);

    String id;
    System.out.println("Введите номер карты: ");
    id = in.nextLine();
    System.out.println("Сумма: ");
    int sum = in.nextInt();
    if (atmController.transferMoney(atm, card, sum, id))
      System.out.println("Деньги доставлены");
    else System.out.println("Произошла ошибка");
  }

  private void payCheck(Atm atm, Card card) {
    AtmController atmController = new AtmController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);
    int id;
    System.out.println("Введите номер счета: ");
    id = in.nextInt();
    System.out.println("Сумма: ");
    int sum = in.nextInt();
    if (atmController.payCheck(atm, card, sum, id))
      System.out.println("Оплата прошла успешно");
    else System.out.println("Произошла ошибка");
  }

  private void payMobile(Atm atm, Card card) {
    AtmController atmController = new AtmController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);
    String mobileNumber;
    System.out.println("Введите номер: ");
    mobileNumber = in.nextLine();
    System.out.println("Сумма: ");
    int sum = in.nextInt();
    if (atmController.payMobile(atm, card, sum, mobileNumber))
      System.out.println("Деньги зачислены");
    else System.out.println("Произошла ошибка");
  }

}
