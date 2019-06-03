package model;

public enum BillType {
  FIFTY(5000),
  TWENTY(2000),
  TEN(1000),
  FIVE(500),
  TWO(200),
  ONE(100),
  ANY(1);

  private int value;
  BillType(int value){
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
