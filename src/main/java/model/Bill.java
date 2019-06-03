package model;

public class Bill {
  private BillType billType;

  public Bill(BillType billType) {
    this.billType = billType;
  }

  public BillType getBillType() {
    return billType;
  }
}
