package controller;

import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationControllerTest {

  private UserRegistrationController controller = new UserRegistrationController();

  private Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);

  private Service<ClientBankAccount,Integer> clientService = new Service<ClientBankAccount,Integer>(ClientBankAccount.class);

  private Service<PaymentSystem,Integer> paymentSystemIntegerService = new Service<PaymentSystem,Integer>(PaymentSystem.class);

  private List<PaymentSystem> paymentSystems;


  @BeforeEach
  void setUp(){
    List<Bank> banks = bankService.getAll();
    for (Bank bank: banks
         ) {
      bankService.delete(bank);
    }
paymentSystems = paymentSystemIntegerService.getAll();
    Bank bank = new Bank("Sber",new Encoder(3));
    Department department = new Department(bank,"SaintP");
    bankService.create(bank);
  }

  @Test
  void simpleAddClientTest() {
    Bank bank = bankService.getAll().get(0);
    Department department = bank.getDepartments().get(0);

    controller.addNewClient(department,"Anton","Medvedev","79217769813",1337,paymentSystems.get(1));

    bank=bankService.getAll().get(0);
    department= bank.getDepartments().get(0);
    ClientBankAccount account = (ClientBankAccount)department.getAccounts().get(0);

    assertEquals("Anton",account.getClient().getName());
    assertEquals("Medvedev",account.getClient().getSurname());

  }

  @Test
  void incorrectNameAddClientTest() {
    Bank bank = bankService.getAll().get(0);
    Department department = bank.getDepartments().get(0);

    controller.addNewClient(department,"Ant85977on","Medve84../dev","79217769813",1337,paymentSystems.get(1));

    bank=bankService.getAll().get(0);
    department= bank.getDepartments().get(0);
    ClientBankAccount account = (ClientBankAccount)department.getAccounts().get(0);

    assertEquals("Anton",account.getClient().getName());
    assertEquals("Medvedev",account.getClient().getSurname());

  }

  @Test
  void noNameAddClientTest() {
    Bank bank = bankService.getAll().get(0);
    Department department = bank.getDepartments().get(0);

    controller.addNewClient(department,"","Medvedev","79217769813",1337,paymentSystems.get(1));

    bank=bankService.getAll().get(0);
    department= bank.getDepartments().get(0);


    assertTrue(department.getAccounts().size()==0);

  }

  @Test
  void invalidMobileNumberAddClientTest() {
    Bank bank = bankService.getAll().get(0);
    Department department = bank.getDepartments().get(0);

    controller.addNewClient(department,"","Medvedev","7921769813",1337,paymentSystems.get(1));

    bank=bankService.getAll().get(0);
    department= bank.getDepartments().get(0);


    assertTrue(department.getAccounts().size()==0);

  }

}