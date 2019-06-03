package controller;

import model.Atm;
import model.Bank;
import model.Client;
import model.PaymentSystem;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class MainController {
  public List<Bank> getAllBanks(){
    Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);
    return bankService.getAll();
  }

  public List<Client> getAllClients(){
    Service<Client,Integer> clientService = new Service<Client, Integer>(Client.class);
    return clientService.getAll();
  }

  public List<Atm> getAllAtms(){
    Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);
    List<Atm> atms = new ArrayList<>();
    for (Bank bank:bankService.getAll()
         ) {
      atms.add(bank.getAtm());
    }
    return atms;
  }

  public List<PaymentSystem> getAllPS(){
    Service<PaymentSystem,Integer> paymentSystemService = new Service<PaymentSystem,Integer>(PaymentSystem.class);
    return paymentSystemService.getAll();

  }

}
