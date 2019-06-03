package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import service.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
class AtmControllerTest {

  private AtmController atmController = new AtmController();
  private BankRegistrationController bankRegistrationController = new BankRegistrationController();
  private UserRegistrationController userRegistrationControllerontroller = new UserRegistrationController();
  private CompanyRegistrationController companyRegistrationController = new CompanyRegistrationController();
  private Service<PaymentSystem,Integer> paymentSystemIntegerService = new Service<PaymentSystem,Integer>(PaymentSystem.class);
  private Service<Bank, Integer> bankService = new Service<Bank, Integer>(Bank.class);
  private Service<CompanyBankAccount,Integer> companyBankAccountService = new Service<CompanyBankAccount,Integer>(CompanyBankAccount.class);
  private Service<ClientBankAccount,Integer> clientBankAccountService = new Service<ClientBankAccount,Integer>(ClientBankAccount.class);
  private Service<Company,Integer> companyService = new Service<Company,Integer>(Company.class);
  private Service<BankAccount,Integer> bankAccountIntegerService = new Service<BankAccount,Integer>(BankAccount.class);
  private Service<Mobile,String> mobileService = new Service<Mobile,String>(Mobile.class);


  @BeforeEach
  void setUp() {
    List<Bank> banks = bankService.getAll();
    for (Bank bank: banks
    ) {
      bankService.delete(bank);
    }

    List<Company> companies = companyService.getAll();
    for (Company company: companies
    ) {
      companyService.delete(company);
    }

    bankRegistrationController.addNewBank("Sberbank",3);
    Bank bank = bankService.getAll().get(0);
    List<PaymentSystem> paymentSystems = paymentSystemIntegerService.getAll();
    userRegistrationControllerontroller.addNewClient(bank.getDepartments().get(0),"Anton","Medvedev","79217769813",1337,paymentSystems.get(0));
    userRegistrationControllerontroller.addNewClient(bank.getDepartments().get(0),"Arina","Oskina","79766661337",1263,paymentSystems.get(0));
    userRegistrationControllerontroller.addNewClient(bank.getDepartments().get(0),"Bill","Gates","79777771137",1263,paymentSystems.get(0));
    bank.getDepartments().get(0).getAccounts().get(0).pushMoney(2000);
    bank.getDepartments().get(0).getAccounts().get(1).pushMoney(3000);
    bank.getDepartments().get(0).getAccounts().get(2).pushMoney(30000000);
    bankService.update(bank);
    companyRegistrationController.addNewCompany("ETU",bank.getDepartments().get(0));


    Atm.setDispancer(new Dispancer(40));
    Atm.setBillReciever(new BillReciever(40));
  }

  @Test
  void simplePopMoneyTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertTrue(atmController.popMoney(atm,card,1000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(1000,account.getBalance());


  }

  @Test
  void zeroSumPopTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.popMoney(atm,card,0));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());


  }

  @Test
  void invalidSumPopTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.popMoney(atm,card,1323));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());


  }

  @Test
  void largeSumPopTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.popMoney(atm,card,3000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());


  }

  @Test
  void overFourthyBiilsPopTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(2);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.popMoney(atm,card,205000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());

    assertEquals(30000000,account.getBalance());


  }

  @Test
  void overDayLimitPopTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(2);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.popMoney(atm,card,60000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());

    assertEquals(30000000,account.getBalance());


  }

  @Test
  void noSuchBillsPopMoneyTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(2);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    for (MoneyCase moneyCase:atm.getCases()
         ) {
      moneyCase.setOccupancy(2);
    }

    assertFalse(atmController.popMoney(atm,card,20000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());

    assertEquals(30000000,account.getBalance());


  }

  @Test
  void popMoneyWithOtherBankCardTest() {
    bankRegistrationController.addNewBank("VTB",5);
    Bank bank = bankService.getAll().get(1);
    ClientBankAccount account = clientBankAccountService.getAll().get(1);
    Card card = account.getCard();

    Atm atm = bank.getAtm();


    assertTrue(atmController.popMoney(atm,card,2500));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());

    assertEquals(400,account.getBalance());


  }

  @Test
  void popMoneyWithOtherBankCardOver10kTest() {
    bankRegistrationController.addNewBank("VTB",5);
    Bank bank = bankService.getAll().get(1);
    ClientBankAccount account = clientBankAccountService.getAll().get(2);
    Card card = account.getCard();

    Atm atm = bank.getAtm();


    assertTrue(atmController.popMoney(atm,card,35000));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());

    assertEquals(29964650,account.getBalance());


  }

  @Test
  void simplePushMoneyTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();

    List<Bill> bills = new ArrayList<Bill>();
    bills.add(new Bill(BillType.FIFTY));

    assertTrue(atmController.pushMoney(atm,bills,card));

    bank = bankService.getAll().get(0);
    account = (ClientBankAccount)bank.getDepartments().get(0).getAccounts().get(0);

    assertEquals(7000,account.getBalance());
  }

  @Test
  void overFourthyBillsPushTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();

    List<Bill> bills = new ArrayList<Bill>();
    for(int i = 0;i<41;i++)
    bills.add(new Bill(BillType.FIFTY));

    assertFalse(atmController.pushMoney(atm,bills,card));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());
  }

  @Test
  void overflowPushTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    atm.getMoneyCaseByType(BillType.ANY).setOccupancy(2495);
    List<Bill> bills = new ArrayList<Bill>();
    for(int i = 0;i<30;i++)
      bills.add(new Bill(BillType.FIFTY));

    assertFalse(atmController.pushMoney(atm,bills,card));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());
  }

  @Test
  void simplePayCheckTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    CompanyBankAccount companyBankAccount = companyBankAccountService.getAll().get(0);


    assertTrue(atmController.payCheck(atm,card,2000,companyBankAccount.getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);
    companyBankAccount = companyBankAccountService.getAll().get(0);

    assertEquals(0,account.getBalance());
    assertEquals(2000,companyBankAccount.getBalance());
  }

  @Test
  void invalidSumPayCheck() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    CompanyBankAccount companyBankAccount = companyBankAccountService.getAll().get(0);


    assertFalse(atmController.payCheck(atm,card,0,companyBankAccount.getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);
    companyBankAccount = companyBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());
    assertEquals(0,companyBankAccount.getBalance());
  }

  @Test
  void soLargeSumPayCheckTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    CompanyBankAccount companyBankAccount = companyBankAccountService.getAll().get(0);


    assertFalse(atmController.payCheck(atm,card,3000,companyBankAccount.getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);
    companyBankAccount = companyBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());
    assertEquals(0,companyBankAccount.getBalance());
  }

  @Test
  void payCheckWithCommisionTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    bankRegistrationController.addNewBank("VTB",5);
    bank = bankService.getAll().get(1);
    companyRegistrationController.addNewCompany("SPB",bank.getDepartments().get(0));
    bank = bankService.getAll().get(1);

    CompanyBankAccount companyBankAccount = (CompanyBankAccount) bank.getDepartments().get(0).getAccounts().get(0);


    assertTrue(atmController.payCheck(atm,card,1500,companyBankAccount.getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(account.getNumber());
    companyBankAccount = companyBankAccountService.get(companyBankAccount.getNumber());

    assertEquals(425,account.getBalance());
    assertEquals(1500,companyBankAccount.getBalance());
  }


  @Test
  void invalidTargetPayCheckTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();



    assertFalse(atmController.payCheck(atm,card,2000,0));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.getAll().get(0);


    assertEquals(2000,account.getBalance());
  }

  @Test
  void simpleTransferMoneyTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    int accountid = account.getNumber();
    Card card = account.getCard();
    Atm atm = bank.getAtm();
     ClientBankAccount sendTo = clientBankAccountService.getAll().get(1);
     int sendToId = sendTo.getNumber();


    assertTrue(atmController.transferMoney(atm,card,2000,sendTo.getCard().getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(accountid);
    sendTo = clientBankAccountService.get(sendToId);

    assertEquals(0,account.getBalance());
    assertEquals(5000,sendTo.getBalance());
  }

  @Test
  void soLargeSumTotransferTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    int accountid = account.getNumber();
    Card card = account.getCard();
    Atm atm = bank.getAtm();

    ClientBankAccount sendTo = clientBankAccountService.getAll().get(1);
    int sendToId = sendTo.getNumber();


    assertFalse(atmController.transferMoney(atm,card,3000,sendTo.getCard().getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(accountid);
    sendTo = clientBankAccountService.get(sendToId);

    assertEquals(2000,account.getBalance());
    assertEquals(3000,sendTo.getBalance());
  }

  @Test
  void transferMoneyWithCommisionTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    int accountid = account.getNumber();
    Card card = account.getCard();
    Atm atm = bank.getAtm();
    bankRegistrationController.addNewBank("VTB",5);
    bank = bankService.getAll().get(1);
    userRegistrationControllerontroller.addNewClient(bank.getDepartments().get(0),"Alex","Ermakov","79273254658",8956,paymentSystemIntegerService.getAll().get(0));

    bank = bankService.getAll().get(1);
    ClientBankAccount sendTo = (ClientBankAccount) bank.getDepartments().get(0).getAccounts().get(0);
    int sendToId = sendTo.getNumber();


    assertTrue(atmController.transferMoney(atm,card,1500,sendTo.getCard().getNumber()));

    bank = bankService.getAll().get(0);
    account = clientBankAccountService.get(accountid);
    sendTo = clientBankAccountService.get(sendToId);

    assertEquals(425,account.getBalance());
    assertEquals(1500,sendTo.getBalance());
  }

  @Test
  void simplePayMobileTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertTrue(atmController.payMobile(atm,card,1000,"79766661337"));

    account = clientBankAccountService.getAll().get(0);

    assertEquals(999,account.getBalance());
    Mobile mobile = mobileService.get("79766661337");
    assertEquals(1000,mobile.getBalance());
  }

  @Test
  void soLargeSumPayMobileTest() {
    Bank bank = bankService.getAll().get(0);
    ClientBankAccount account = clientBankAccountService.getAll().get(0);
    Card card = account.getCard();
    Atm atm = bank.getAtm();


    assertFalse(atmController.payMobile(atm,card,5000,"79766661337"));

    account = clientBankAccountService.getAll().get(0);

    assertEquals(2000,account.getBalance());
    Mobile mobile = mobileService.get("79766661337");
    assertEquals(0,mobile.getBalance());
  }
}