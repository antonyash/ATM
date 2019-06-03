package controller;

import model.Bank;
import model.Department;
import model.Encoder;
import service.Service;

import java.util.List;

public class BankRegistrationController {
  private Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);

  public void addNewBank(String name,int encodeValue){
    try{
      if(validateName(name)&&name!=""){
        if(encodeValue<=0) throw new Exception("Значение должно быть больше нуля");
        else {
          Bank bank = new Bank(name,new Encoder(encodeValue));
          bankService.create(bank);
          addNewDepartment(bank,"Moscow");
        }

      }else throw new Exception("Банк с таким названием уже существует");
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }

  }

  public void addNewDepartment(Bank bank,String region){
    Department department = new Department(bank,region);
    bankService.update(bank);
  }

  private boolean validateName(String name){
    List<Bank> banks = bankService.getAll();

    for (Bank bank:banks
         ) {
      if(bank.getName().equals(name))
        return false;
    }
    return true;
  }
}
