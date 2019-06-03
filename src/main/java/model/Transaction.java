package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;


  private Date date;

  public Transaction() {
  }

  public Transaction(Date date, BankAccount from, BankAccount to, int value) {
    this.date = date;
    this.from = from;
    from.addTransaction(this);
    this.to = to;
    this.value = value;
  }

  public int getId() {
    return id;
  }

  public void setComission(int comission) {
    Comission = comission;
  }

  public Date getDate() {
    return date;
  }

  public BankAccount getFrom() {
    return from;
  }

  public BankAccount getTo() {
    return to;
  }

  public int getValue() {
    return value;
  }

  public int getComission() {
    return Comission;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fromId")
  private BankAccount from;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "toId")
  private BankAccount to;

  private int value;

  private int Comission;

}
