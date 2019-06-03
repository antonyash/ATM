package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispancerTest {
  public Atm atm;

  @BeforeEach
  void setUp() {
  atm = new Atm();
  Atm.setDispancer(new Dispancer(40));
    for (BillType billType: BillType.values()
    ) {
      if(billType!=BillType.ANY)
        atm.addMoneyCase(new MoneyCase(atm,billType,2500,40));
      else
        atm.addMoneyCase(new MoneyCase(atm,billType,2500,0));
    }
  }

  @Test
  void simpleGiveMoneyTest() {
    Atm.getDispancer().giveMoney(25000,atm);
    assertEquals(35,atm.getMoneyCaseByType(BillType.FIFTY).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TWENTY).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TEN).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.FIVE).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TWO).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.ONE).getOccupancy());

  }

  @Test
  void simpleIncorrectSumTest(){
    Atm.getDispancer().giveMoney(25341,atm);
    assertEquals(40,atm.getMoneyCaseByType(BillType.FIFTY).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TWENTY).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TEN).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.FIVE).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.TWO).getOccupancy());
    assertEquals(40,atm.getMoneyCaseByType(BillType.ONE).getOccupancy());
  }

  @Test
  void soMuchMoneyGiveTest(){
    Exception e = assertThrows(Exception.class,()->Atm.getDispancer().giveMoney(3000000,atm));
    assertTrue(e.getMessage().contains("Не хватает купюр!"));

  }

  @Test
  void overFourtyBillsGiveTest(){
    Atm.getDispancer().giveMoney(200100,atm);
    assertEquals(2500,atm.getMoneyCaseByType(BillType.FIFTY).getOccupancy());
    assertEquals(2500,atm.getMoneyCaseByType(BillType.TWENTY).getOccupancy());
    assertEquals(2500,atm.getMoneyCaseByType(BillType.TEN).getOccupancy());
    assertEquals(2500,atm.getMoneyCaseByType(BillType.FIVE).getOccupancy());
    assertEquals(2500,atm.getMoneyCaseByType(BillType.TWO).getOccupancy());
    assertEquals(2500,atm.getMoneyCaseByType(BillType.ONE).getOccupancy());
  }

}