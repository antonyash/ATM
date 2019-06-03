package model;

import javax.persistence.*;

@Entity
@Table(name="PaymentSystem")
public class PaymentSystem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public PaymentSystem(String name, int startWith) {
    this.name = name;
    this.startWith = startWith;
  }

  public PaymentSystem() {
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStartWith() {
    return startWith;
  }

  private String name;

  private int startWith;
}
