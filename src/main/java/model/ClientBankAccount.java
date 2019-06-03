package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ClientBankAccount")
public class ClientBankAccount extends BankAccount {
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cardNumber")
  private Card card;

  public Card getCard() {
    return card;
  }


  public int getPvv() {
    return pvv;
  }


  public Client getClient() {
    return client;
  }


  private int pvv;

  public ClientBankAccount(int balance, Department department, List<Transaction> transactions,Card card, int pvv, Client client) {
    super(balance, department, transactions);
    this.card = card;
    this.pvv = pvv;
    this.client = client;

  }

  public ClientBankAccount() {
  }

  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @JoinColumn(name = "clientId")
  private Client client;

}
