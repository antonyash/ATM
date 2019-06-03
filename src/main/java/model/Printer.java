package model;

public class Printer {

  private String printCheck(Atm atm,Transaction transaction) {
    String result = "";

    result+="\t"+atm.getBank().getName();
    result+=transaction.getDate();
    result+=transaction.getDate().getTime();
    result+=transaction.getId();
    result+=transaction.getFrom().getNumber();
    result+=transaction.getValue();
    result+=transaction.getComission();
    result+=transaction.getTo();

    return result;
  }

  public Printer() {
  }
}
