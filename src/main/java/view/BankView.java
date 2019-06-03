package view;

import controller.CardRegistrationController;
import controller.CompanyRegistrationController;
import controller.MainController;
import controller.UserRegistrationController;
import model.Bank;
import model.Client;
import model.Department;
import model.PaymentSystem;

import java.util.List;
import java.util.Scanner;

public class BankView {


  public void newClient(){
    UserRegistrationController controller = new UserRegistrationController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);


    System.out.println("Выберите банк: ");
    List<Bank> banks = mainController.getAllBanks();
    for(int i = 0;i<banks.size();i++){
      System.out.println(i+"."+banks.get(i).getName());
    }
    List<Department> departments = banks.get(in.nextInt()).getDepartments();
    System.out.println("Выберите отделение: ");

    for(int i = 0;i<departments.size();i++){
      System.out.println(i+"."+departments.get(i).getId()+" "+departments.get(i).getRegion());
    }
    Department department = departments.get(in.nextInt());

    List<PaymentSystem> paymentSystems = mainController.getAllPS();

    System.out.println("Выберите платежную систему: ");
    for (int i = 0;i<paymentSystems.size();i++){
      System.out.println(i+"."+paymentSystems.get(i).getName());
    }

    PaymentSystem paymentSystem = paymentSystems.get(in.nextInt());

    System.out.println("Имя: ");
    in.nextLine();
    String name = in.nextLine();
    System.out.println("Фамилия: ");
    String surname = in.nextLine();
    System.out.println("Номер телефона: ");
    String mobilePhone = in.nextLine();
    System.out.println("PIN: ");
    int pin = in.nextInt();

    controller.addNewClient(department,name,surname,mobilePhone,pin,paymentSystem);
  }

  public void newCompany(){

    CompanyRegistrationController controller = new CompanyRegistrationController();
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);


    System.out.println("Выберите банк: ");
    List<Bank> banks = mainController.getAllBanks();
    for(int i = 0;i<banks.size();i++){
      System.out.println(i+"."+banks.get(i).getName());
    }
    List<Department> departments = banks.get(in.nextInt()).getDepartments();
    System.out.println("Выберите отделение: ");

    for(int i = 0;i<departments.size();i++){
      System.out.println(i+"."+departments.get(i).getId()+" "+departments.get(i).getRegion());
    }
    Department department = departments.get(in.nextInt());

    System.out.println("Название: ");
    in.nextLine();
    String name = in.nextLine();

    controller.addNewCompany(name,department);
  }

  private void newCard(Department department,Client client){
    Scanner in = new Scanner(System.in);
    CardRegistrationController controller = new CardRegistrationController();

    MainController mainController = new MainController();


    List<PaymentSystem> paymentSystems = mainController.getAllPS();

    System.out.println("Выберите платежную систему: ");
    for (int i = 0;i<paymentSystems.size();i++){
      System.out.println(i+"."+paymentSystems.get(i).getName());
    }

    PaymentSystem paymentSystem = paymentSystems.get(in.nextInt());
    in.nextLine();
    System.out.println("PIN: ");
    int pin = in.nextInt();
    controller.addNewCard(client,department,pin,paymentSystem);
  }

  public void menu(Client client){
    MainController mainController = new MainController();
    Scanner in = new Scanner(System.in);
    boolean check = true;

    System.out.println("Выберите банк: ");
    List<Bank> banks = mainController.getAllBanks();
    for(int i = 0;i<banks.size();i++){
      System.out.println(i+"."+banks.get(i).getName());
    }
    List<Department> departments = banks.get(in.nextInt()).getDepartments();
    System.out.println("Выберите отделение: ");

    for(int i = 0;i<departments.size();i++){
      System.out.println(i+"."+departments.get(i).getId()+" "+departments.get(i).getRegion());
    }
    Department department = departments.get(in.nextInt());



    while(check){

      System.out.println("1.Новая карта\n" +
              "0.Выход\n");


      int input = in.nextInt();

      switch (input){
        case 1:{
          newCard(department,client);
          break;
        }

        case 0:{
          check=false;
          break;
        }
      }

    }
  }
}
