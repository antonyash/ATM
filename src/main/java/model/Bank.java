package model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Bank")
public class Bank {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  public Bank() {
  }

  @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Department> departments;

  @OneToOne(mappedBy ="bank" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private Atm atm;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "encoderId")
  private Encoder encoder;

  public Bank(String name,Encoder encoder) {

    this.name = name;
    this.departments = new ArrayList<Department>();
    this.atm = new Atm(this);
    this.encoder = encoder;
    System.out.println("Открыт новый банк!");
  }

  public int getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  public List<Department> getDepartments() {
    return departments;
  }


  public Atm getAtm() {
    return atm;
  }


  public Encoder getEncoder() {
    return encoder;
  }

  public void addDepartment(Department department){
    this.departments.add(department);
  }

  public boolean validPin(Card card,int pin){
    if(card.getClientBankAccount().getPvv()==this.encoder.encodePin(pin))
      return true;
    else
      return false;
  }

  public int validTransaction(Transaction transaction){
    int result = 0;//result == commision in %, result>0 - valid transacion, result==-1 - invalid transaction

    if(transaction.getFrom().getDepartment().getBank()==transaction.getTo().getDepartment().getBank()){
      if(transaction.getFrom().getDepartment().getRegion()!=transaction.getTo().getDepartment().getRegion())
        result=1;
    }
    else result=5;

    if(transaction.getFrom().getBalance()<transaction.getValue()*(1+result/100)||transaction.getValue()==0)
      return -1;

    return result*transaction.getValue()/100;
  }


  public int validPopMoney(Atm atm, Card card, int sum){
    if(sum==0)
      return -1;
    int day = 0;
    int month = 0;
    int result = 0;
    List<Transaction> transactions = new ArrayList<Transaction>();
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DATE,-1);
    Date yesterday = calendar.getTime();
    calendar.add(Calendar.DATE,1);
    calendar.add(Calendar.MONTH,-1);
    Date lastMonth = calendar.getTime();
    transactions = card.getClientBankAccount().getTransactions();

    for (Transaction transaction:transactions
         ) {
      if(transaction.getDate().after(yesterday))
        day+=transaction.getValue();
      if(transaction.getDate().after(lastMonth))
        month+=transaction.getValue();

    }

    if(day+sum>=card.getDailyPopLimit())
      return -1;
    if(month+sum>=card.getMonthlyPopLimit())
      return -1;

    if(atm.getBank().getId()!=card.getClientBankAccount().getDepartment().getBank().getId())
      result=Math.max((sum/100),100);



    if(sum+result>card.getClientBankAccount().getBalance())
      return -1;

    return result;

  }

  public int validPayMobile(Atm atm,Card card,Mobile mobile,int sum){
int result = 0;
    if(atm.getBank()!=card.getClientBankAccount().getDepartment().getBank())
      result = 1;

    if(card.getClientBankAccount().getBalance()<sum||sum==0)
      return -1;

    return result;

  }



  public void commitPayMobile(Card card,Mobile mobile, int sum,int commision){
    card.getClientBankAccount().popMoney(sum+commision);
    mobile.pushMoney(sum);
  }
}
