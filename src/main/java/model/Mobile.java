package model;

import javax.persistence.*;

@Entity
@Table(name="Mobile")
public class Mobile {
  @Id
  private String number;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="clientId")
  private Client client;

  public Mobile() {
  }

  public Mobile(String number, Client client, double balance) throws Exception{
    if(number.length()!=11) throw new Exception("Неверный номер");
    this.number = number;
    this.client = client;
    client.addMobilePhone(this);
    this.balance = balance;
  }


  public String getNumber() {
    return number;
  }

  public Client getClient() {
    return client;
  }

  public double getBalance() {
    return balance;
  }

  public void pushMoney(int money){
    this.balance+=money;
  }
  private double balance;
}
