package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public Company() {
  }

  public Company(String name, List<CompanyBankAccount> bankAccounts) {
    Name = name;
    this.bankAccounts = bankAccounts;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return Name;
  }

  public List<CompanyBankAccount> getBankAccounts() {
    return bankAccounts;
  }

  private String Name;

  @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<CompanyBankAccount> bankAccounts;

}
