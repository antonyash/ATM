package model;

import java.util.List;

public class BillReciever {
  private int input;

  public int getMoney(List<Bill> bundle, Atm atm) {
    int value = 0;
    try {
      if (bundle.size() > input) throw new Exception("Банкомат не принимат больше "+ input+" банкнот");
      atm.getMoneyCaseByType(BillType.ANY).pushMoney(bundle.size());
      for (Bill bill : bundle
      ) {
        value += bill.getBillType().getValue();
      }


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return value;
  }

  public BillReciever() {
  }

  public BillReciever(int input) {
    this.input = input;
  }

  public int getInput() {
    return input;
  }

  public void setInput(int input) {
    this.input = input;
  }
}
