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

  public Mobile(String number, double balance) throws Exception{
    if(number.length()!=11) throw new Exception("Неверный номер");
    this.number = number;
    this.client = client;
    this.balance = balance;
  }

  public void setClient(Client client) {
    this.client = client;
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
