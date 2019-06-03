package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return id;
  }

  public Client(String name, String surname, List<Mobile> mobilePhones) {
    Name = name;
    Surname = surname;
    this.mobilePhones = mobilePhones;
    System.out.println("Добавлен новый клиент!");
  }

  public String getName() {
    return Name;
  }

  public List<ClientBankAccount> getAccounts() {
    return accounts;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getSurname() {
    return Surname;
  }

  public void setSurname(String surname) {
    Surname = surname;
  }

  public List<Mobile> getMobilePhones() {
    return mobilePhones;
  }

  public void addMobilePhone(Mobile mobile){
    mobilePhones.add(mobile);
  }

  private String Name;

  private String Surname;

  @OneToMany( mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<Mobile> mobilePhones;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<ClientBankAccount> accounts;

  public Client() {
  }
}
