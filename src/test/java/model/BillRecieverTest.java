package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BillRecieverTest {
  public Atm atm;

  @BeforeEach
  void setUp() {
    atm = new Atm();
    Atm.setBillReciever(new BillReciever(40));
    for (BillType billType: BillType.values()
    ) {
      if(billType!=BillType.ANY)
        atm.addMoneyCase(new MoneyCase(atm,billType,2500,40));
      else
        atm.addMoneyCase(new MoneyCase(atm,billType,2500,0));
    }
  }

  @Test
  void getMoneyTest() {
    ArrayList<Bill> bills = new ArrayList<>();
    bills.add(new Bill(BillType.FIFTY));
    bills.add(new Bill(BillType.FIFTY));
    bills.add(new Bill(BillType.FIFTY));
    bills.add(new Bill(BillType.FIFTY));
    bills.add(new Bill(BillType.FIFTY));
    bills.add(new Bill(BillType.ONE));
    bills.add(new Bill(BillType.ONE));
    bills.add(new Bill(BillType.ONE));
    bills.add(new Bill(BillType.ONE));

    Atm.getBillReciever().getMoney(bills,atm);
    assertEquals(bills.size(),atm.getMoneyCaseByType(BillType.ANY).getOccupancy());
  }

  @Test
  void soMuchMoneyGetTest() {
    ArrayList<Bill> bills = new ArrayList<>();
    for(int i =0;i<10;i++){
      bills.add(new Bill(BillType.FIFTY));
      bills.add(new Bill(BillType.FIFTY));
      bills.add(new Bill(BillType.TWENTY));
      bills.add(new Bill(BillType.TWENTY));
      bills.add(new Bill(BillType.TWENTY));
      bills.add(new Bill(BillType.TEN));
      bills.add(new Bill(BillType.TEN));
      bills.add(new Bill(BillType.TWO));
      bills.add(new Bill(BillType.ONE));
    }

    Exception e = assertThrows(Exception.class,()->Atm.getBillReciever().getMoney(bills,atm));

    assertTrue(e.getMessage().contains("Банкомат не принимат больше "+ Atm.getBillReciever().getInput()+" банкнот"));
  }
}