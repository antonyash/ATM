package controller;

import dao.GenericDao;
import model.Atm;
import model.Bank;
import model.Card;
import service.Service;

import java.util.Date;
import java.util.GregorianCalendar;

public class AuthConroller {

  private Service<Atm,Integer> atmService = new Service<Atm, Integer>(Atm.class);
  private Service<Card,String> cardService =  new Service<Card, String>(Card.class);


  public boolean auth(Atm atm,String number){
    Card card = cardService.get(number);
    if(checkCard(atm,card)&&checkCard(atm,card))
      return true;
    return false;
  }

  public boolean checkCard(Atm atm,Card card){
    Date date = new GregorianCalendar().getTime();
    if(!card.getExpDate().after(date))
      return false;
    /*if(card.isBlocked())
      return false;*/
    return true;
  }

  public boolean checkPin(Atm atm,Card card,int pin){
    Bank bank = card.getClientBankAccount().getDepartment().getBank();
    if(bank.validPin(card,pin))
      return true;
    return false;
  }


}
