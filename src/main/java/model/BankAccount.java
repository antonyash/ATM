package model;

import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BankAccount")
public abstract class BankAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int number;

  protected double balance;

  public BankAccount() {
  }

  public int getNumber() {
    return number;
  }

  public double getBalance() {
    return balance;
  }


  public Department getDepartment() {
    return department;
  }


  public List<Transaction> getTransactions() {
    return transactions;
  }


  public BankAccount(double balance, Department department, List<Transaction> transactions) {
    this.balance = balance;
    this.department = department;
    department.addBankAccount(this);
    this.transactions = transactions;
  }

  public void popMoney(int sum){
    this.balance-=sum;
  }
  public void pushMoney(int sum){
    this.balance+=sum;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "departmentId")
  protected Department department;

  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  protected List<Transaction> transactions;

  public void addTransaction(Transaction transaction){
    this.transactions.add(transaction);
  }

}
