package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Card")

public class Card {
  @Id
  private String number;

  private int cvv;

  private String cardholdrName;

  private Date expDate;

  private int dailyPopLimit;

  public ClientBankAccount getClientBankAccount() {
    return clientBankAccount;
  }

  @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private ClientBankAccount clientBankAccount;

  public Card(String bankNumber, int cvv, String cardholdrName, Date expDate, int dailyPopLimit, int monthlyPopLimit, PaymentSystem paymentSystem) {
    this.number = generateNumber(paymentSystem, bankNumber);
    this.cvv = cvv;
    this.cardholdrName = cardholdrName;
    this.expDate = expDate;
    this.dailyPopLimit = dailyPopLimit;
    this.monthlyPopLimit = monthlyPopLimit;
    this.paymentSystem = paymentSystem;
    System.out.println("Выдана новая карта "+ this.number);
  }

  private int monthlyPopLimit;

 // private boolean isBlocked;

 // public boolean isBlocked() {
  //  return isBlocked;
  //}

  private String generateNumber(PaymentSystem paymentSystem, String bankNumber) {

    String result = paymentSystem.getStartWith() + bankNumber;

    int sum = 0;

    for (int i = 0; i < result.length(); i += 2) {
      if ((int) result.charAt(i) * 2 > 9)
        sum += (int) result.charAt(i) * 2 - 9;
      else sum += (int) result.charAt(i) * 2;
    }
    for (int i = 1; i < result.length(); i += 2) {
      sum += (int) result.charAt(i);
    }

    sum = 10 - sum % 10;

    result += sum;
    return result;
  }

  /*public void setBlocked(boolean blocked) {
    isBlocked = blocked;
  }*/

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "paymentSystemId")
  private PaymentSystem paymentSystem;

  public String getNumber() {
    return number;
  }

  public int getCvv() {
    return cvv;
  }

  public String getCardholdrName() {
    return cardholdrName;
  }

  public Date getExpDate() {
    return expDate;
  }

  public int getDailyPopLimit() {
    return dailyPopLimit;
  }

  public int getMonthlyPopLimit() {
    return monthlyPopLimit;
  }

  public PaymentSystem getPaymentSystem() {
    return paymentSystem;
  }

  public Card() {
  }
}
