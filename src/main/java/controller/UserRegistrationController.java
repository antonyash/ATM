package controller;

import model.*;
import service.Service;

import java.util.*;

public class UserRegistrationController {
  private Service<Department, Integer> departmentService = new Service<Department, Integer>(Department.class);

  public void addNewClient(Department department,String name, String surname, String mobileNumber,int pin,PaymentSystem paymentSystem){
    name = validateName(name);
    surname=validateName(surname);
    try{
      if(name!=""&&surname!=""){
        Client client = new Client(name,surname,new ArrayList<Mobile>());
        client.addMobilePhone(new Mobile(mobileNumber,client,0));
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
      } else throw new Exception("Некорректное имя");
    }
   catch (Exception e){
     System.out.println(e.getMessage());
   }


  }

  private String validateName(String name){
    return name.replaceAll("[^a-zA-Z]", "");
  }
}
