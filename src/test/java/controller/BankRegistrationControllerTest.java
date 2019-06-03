package controller;

import model.Bank;
import model.Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankRegistrationControllerTest {

  private BankRegistrationController controller = new BankRegistrationController();

  private Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);

  @BeforeEach
  void setUp(){
    List<Bank> banks = bankService.getAll();
    for (Bank bank: banks
    ) {
      bankService.delete(bank);
    }
  }

  @Test
  void simpleAddNewBankTest() {

    controller.addNewBank("Sberbank",3);

    Bank bank = bankService.getAll().get(0);
    assertEquals("Sberbank",bank.getName());
    assertEquals(3,bank.getEncoder().getEncodeValue());

  }

  @Test
  void addBankWithSameNameTest() {

    controller.addNewBank("Sberbank",3);

   Bank bank = bankService.getAll().get(0);
    assertEquals("Sberbank",bank.getName());
    assertEquals(3,bank.getEncoder().getEncodeValue());

    controller.addNewBank("Sberbank",5);

    assertEquals(1,bankService.getAll().size());

  }

  @Test
  void addBankWithoutNameTest() {

    controller.addNewBank("",3);

    assertEquals(0,bankService.getAll().size());

  }

  @Test
  void addBankWithIncorrectEncodeValueTest() {

    controller.addNewBank("Sberbank",0);

    assertEquals(0,bankService.getAll().size());

  }


}