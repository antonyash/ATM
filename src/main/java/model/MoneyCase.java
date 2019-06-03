package model;

import javax.persistence.*;

@Entity
@Table(name="MoneyCase")
public class MoneyCase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "atmId")
  private Atm atm;

  public MoneyCase() {
  }

  public MoneyCase(Atm atm, BillType billType, int capacity,int occupancy) {
    this.atm = atm;
    this.billType = billType;
    this.Capacity = capacity;
    this.Occupancy = occupancy;
  }

  public void setAtm(Atm atm) {
    this.atm = atm;
  }

  public void setBillType(BillType billType) {
    this.billType = billType;
  }

  public void setOccupancy(int occupancy) {
    Occupancy = occupancy;
  }

  public int getId() {
    return id;
  }

  public Atm getAtm() {
    return atm;
  }

  public BillType getBillType() {
    return billType;
  }

  public int getCapacity() {
    return Capacity;
  }

  public int getOccupancy() {
    return Occupancy;
  }

  public void popMoney(int count){
    this.Occupancy-=count;
  }

  public void pushMoney(int count){
    this.Occupancy+=count;
  }

  @Enumerated(EnumType.ORDINAL)
  private BillType billType;

  private int Capacity;

  private int Occupancy;


}
