package controller;

import model.*;
import service.Service;

import java.util.GregorianCalendar;
import java.util.List;

public class AtmController {
  private Service<ClientBankAccount,Integer> clientService = new Service<ClientBankAccount, Integer>(ClientBankAccount.class);
  private Service<CompanyBankAccount,Integer> companyService = new Service<CompanyBankAccount, Integer>(CompanyBankAccount.class);
  private Service<Mobile,String> mobileService = new Service<Mobile, String>(Mobile.class);
  private Service<Department, Integer> departmentService = new Service<Department, Integer>(Department.class);
  private Service<Transaction,Integer> transactionService = new Service<Transaction,Integer>(Transaction.class);
  private Service<Card,String > cardService = new Service<Card,String>(Card.class);

  public boolean popMoney(Atm atm, Card card,int sum){
    int comission = atm.getBank().validPopMoney(atm,card,sum);
if(comission!=-1){
  if (Atm.getDispancer().giveMoney(sum,atm)){
    card.getClientBankAccount().popMoney(sum+comission);
    clientService.update(card.getClientBankAccount());
    return true;
  }
  return false;
}
return false;
  }

  public boolean pushMoney(Atm atm, List<Bill> billList, Card card){

    if(billList.size()>Atm.getBillReciever().getInput()||billList.size()>atm.getMoneyCaseByType(BillType.ANY).getCapacity()-atm.getMoneyCaseByType(BillType.ANY).getOccupancy())
      return false;
    int sum=0;

    for (Bill bill:billList
         ) {
      sum+=bill.getBillType().getValue();
    }

    atm.getMoneyCaseByType(BillType.ANY).pushMoney(billList.size());

    card.getClientBankAccount().pushMoney(sum);
    clientService.update(card.getClientBankAccount());
    return true;

  }

  public boolean payCheck(Atm atm, Card card,int sum,int id){
    try{
      CompanyBankAccount to = companyService.get(id);

      Transaction transaction = new Transaction(new GregorianCalendar().getTime(),card.getClientBankAccount(),to,sum);
      Department department = departmentService.get(card.getClientBankAccount().getDepartment().getId());
      int comission =  department.getBank().validTransaction(transaction);
      transaction.setComission(comission);
      if(comission==-1) throw new Exception("Ошибка транзакции");

      transaction.getFrom().popMoney(transaction.getValue()+transaction.getComission());
      clientService.update((ClientBankAccount) transaction.getFrom());
      transaction = transactionService.get(transaction.getId());
      transaction.getTo().pushMoney(transaction.getValue());
      companyService.update((CompanyBankAccount) transaction.getTo());
      //transactionService.create(transaction);


      return true;
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean transferMoney(Atm atm,Card card,int sum,String id){
    try{
      ClientBankAccount to = cardService.get(id).getClientBankAccount();

      Transaction transaction = new Transaction(new GregorianCalendar().getTime(),card.getClientBankAccount(),to,sum);

      int comission = card.getClientBankAccount().getDepartment().getBank().validTransaction(transaction);
      if(comission==-1) throw new Exception("Ошибка транзакции");
      //transaction.getFrom().getDepartment().getBank().commitTransaction(transaction);
      transaction.setComission(comission);
      transaction.getFrom().popMoney(transaction.getValue()+transaction.getComission());
      clientService.update((ClientBankAccount) transaction.getFrom());
      transaction = transactionService.get(transaction.getId());
      transaction.getTo().pushMoney(transaction.getValue());
      clientService.update((ClientBankAccount) transaction.getTo());
      return true;
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      return false;
    }

  }

  public boolean payMobile(Atm atm,Card card,int sum,String mobileNumber){

    try{
      Mobile mobile = mobileService.get(mobileNumber);
      int comission = card.getClientBankAccount().getDepartment().getBank().validPayMobile(atm,card,mobile,sum);
      if(comission==-1) throw new Exception("Ошибка транзакции");
      card.getClientBankAccount().getDepartment().getBank().commitPayMobile(card,mobile,sum,comission);
      clientService.update(card.getClientBankAccount());
      mobileService.update(mobile);
      return true;
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      return false;
    }


  }



}
