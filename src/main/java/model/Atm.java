package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Atm")
public class Atm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bankId")
  private Bank bank;

  public static void setBillReciever(BillReciever billReciever) {
    Atm.billReciever = billReciever;
  }

  public static void setCardReciever(CardReciever cardReciever) {
    Atm.cardReciever = cardReciever;
  }

  public static void setDispancer(Dispancer dispancer) {
    Atm.dispancer = dispancer;
  }

  public static void setPrinter(Printer printer) {
    Atm.printer = printer;
  }

  @OneToMany(mappedBy = "atm", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<MoneyCase> cases;

  private static BillReciever billReciever;


  private static CardReciever cardReciever;

  private static Dispancer dispancer;

  private static Printer printer;

  public Bank getBank() {
    return bank;
  }

  public List<MoneyCase> getCases() {
    return cases;
  }

  public static BillReciever getBillReciever() {
    return billReciever;
  }

  public static CardReciever getCardReciever() {
    return cardReciever;
  }

  public static Dispancer getDispancer() {
    return dispancer;
  }

  public static Printer getPrinter() {
    return printer;
  }

  public Atm(Bank bank) {
    this.bank = bank;
    this.cases = new ArrayList<MoneyCase>();
    for (BillType billType: BillType.values()
         ) {
      if(billType!=BillType.ANY)
      addMoneyCase(new MoneyCase(this,billType,2500,2500));
      else
        addMoneyCase(new MoneyCase(this,billType,2500,0));
    }
  }

  public Atm() {
    this.cases = new ArrayList<MoneyCase>();
  }

  public int getId() {
    return id;
  }

  public void addMoneyCase(MoneyCase moneyCase){
    this.cases.add(moneyCase);
    moneyCase.setAtm(this);
  }

  public MoneyCase getMoneyCaseByType(BillType type){
    for (MoneyCase moneyCase:cases
         ) {
      if(moneyCase.getBillType()==type)
        return moneyCase;
    }
    return null;
  }
}
