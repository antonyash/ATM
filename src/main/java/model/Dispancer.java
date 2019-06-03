package model;

public class Dispancer {
  private int output;

  public Dispancer() {
  }

  public Dispancer(int output) {
    this.output = output;
  }

  public int getOutput() {
    return output;
  }

  public boolean giveMoney(int value,Atm atm){
    boolean result = false;
    int counter=0;
    try{
      if(value%100!=0) throw new Exception("Сумма не кратна 100");
      

        for (MoneyCase moneyCase:atm.getCases()
             ) {
          while(value-moneyCase.getBillType().getValue()>=0&&moneyCase.getOccupancy()>0&&moneyCase.getBillType()!=BillType.ANY&&value!=0){
            moneyCase.popMoney(1);
            counter++;
            value-=moneyCase.getBillType().getValue();
          }
        }
      if(value!=0) throw new Exception("Не хватает купюр!");
      if(counter>output) throw new Exception("Сумма слишком велика");
      result = true;
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
    finally {
      return result;
    }
    
  }

}
