package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Department")
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="bankId")
  private Bank bank;

  public Department() {
  }

  public Department(Bank bank, String region) {
    this.bank = bank;
    bank.addDepartment(this);
    this.region = region;
    this.accounts = new ArrayList<BankAccount>();
  }

  public int getId() {
    return id;
  }

  public Bank getBank() {
    return bank;
  }

  public String getRegion() {
    return region;
  }

  public List<BankAccount> getAccounts() {
    return accounts;
  }

  public void addBankAccount(BankAccount bankAccount){
    this.accounts.add(bankAccount);
  }

  private String region;

  @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<BankAccount> accounts;
}
