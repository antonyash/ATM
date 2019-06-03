package controller;

import model.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthConrollerTest {

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
  void auth() {

  }

  @Test
  void checkCard() {

  }

  @Test
  void checkPin() {

  }
}