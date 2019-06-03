package controller;

import model.*;
import service.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class CardRegistrationController {
  private Service<Department,Integer> departmentService = new Service<>(Department.class);
  public void addNewCard(Client client,Department department,int pin, PaymentSystem paymentSystem){
    try{
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.add(Calendar.YEAR,+3);

      String bankNumber = "" + department.getBank().getId()+department.getId();

      if(department.getAccounts().size()>0){
        ClientBankAccount account = (ClientBankAccount)department.getAccounts().get(department.getAccounts().size()-1);
        String temp = account.getCard().getNumber();
        temp = temp.substring(bankNumber.length(),temp.length()-2);
        bankNumber+= (int)(Double.parseDouble(temp)+1);
      }
      else {
        while (bankNumber.length()<13){
          bankNumber+=0;
        }
        bankNumber+=1;
      }

      Card card = new Card(bankNumber,new Random().nextInt(1000),client.getName()+client.getSurname(),calendar.getTime(),40000,250000,paymentSystem);
      ClientBankAccount clientBankAccount = new ClientBankAccount(0,department,new ArrayList<Transaction>(),card,department.getBank().getEncoder().encodePin(pin),client);

      departmentService.update(department);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }

  }
}
