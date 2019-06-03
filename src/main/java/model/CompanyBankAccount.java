package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CompanyBankAccount")
public class CompanyBankAccount extends BankAccount {
  public CompanyBankAccount() {
  }

  public CompanyBankAccount(int balance, Department department, List<Transaction> transactions, Company company) {
    super(balance, department, transactions);
    this.company = company;
  }

  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @JoinColumn(name = "companyId")
  private Company company;

}
