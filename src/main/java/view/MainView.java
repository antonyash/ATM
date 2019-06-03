package view;

import controller.BankRegistrationController;
import controller.MainController;
import model.Client;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainView {

  Scanner in = new Scanner(System.in);

  Client currentClient;

  public void mainMenu() {


    boolean check = true;


    while (check) {

      try{
        if (currentClient == null) {
          System.out.println("\n1.Выбрать пользователя\n" +
                  "2.Новый пользователь\n" +
                  "3.Новый банк\n" +
                  "4.Новая компания\n" +
                  "0.Выход\n");

          int input = Integer.parseInt(in.next());

          switch (input) {
            case 1: {
              currentClient = chooseClient();
              break;
            }
            case 2: {
              new BankView().newClient();
              break;
            }
            case 3: {
              newBank();
              break;
            }
            case 4: {
              new BankView().newCompany();
              break;
            }
            case 0: {
              check = false;
              break;
            }

          }
        } else {
          System.out.println("\nТекущий пользователь:" +
                  currentClient.getName() + " " + currentClient.getSurname() +
                  "\n1.Выбрать пользователя\n" +
                  "2.Пойти в банкомат\n" +
                  "3.Пойти в банк\n" +
                  "0.Выход\n");

          int input = in.nextInt();

          switch (input) {
            case 1: {
              currentClient = chooseClient();
              break;
            }
            case 2: {
              new AtmView().menu(currentClient);
              break;
            }
            case 3: {
              new BankView().menu(currentClient);
              break;
            }
            case 0: {
              check = false;
              break;
            }

          }
        }
      }
      catch(Exception e){
        System.out.println(e.getMessage());
      }
    }

  }

  private Client chooseClient() {
    MainController controller = new MainController();

    List<Client> clients = controller.getAllClients();

    for (int i = 0; i < clients.size(); i++) {
      System.out.println(i + "." + clients.get(i).getName() + " " + clients.get(i).getSurname());
    }
    System.out.println("Ваш выбор: ");
    Scanner in = new Scanner(System.in);
    return clients.get(in.nextInt());

  }

  private void newBank() {
    BankRegistrationController controller = new BankRegistrationController();
    System.out.println("Название: ");
    Scanner in = new Scanner(System.in);
    String name = in.nextLine();
    int value = new Random().nextInt(10);

    controller.addNewBank(name,value);
  }


}
