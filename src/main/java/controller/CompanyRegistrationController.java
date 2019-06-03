package controller;

import model.*;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class CompanyRegistrationController {
  private Service<Department, Integer> departmentService = new Service<Department, Integer>(Department.class);
  private Service<Company,Integer> companyService = new Service<Company,Integer>(Company.class);

  public void addNewCompany(String name, Department department){

    try{
      if(validateName(name)){
        if(name=="") throw new Exception("Не введено название");
        else {
          Company company = new Company(name,new ArrayList<CompanyBankAccount>());
          company.getBankAccounts().add(new CompanyBankAccount(0,department,new ArrayList<Transaction>(),company));
          companyService.create(company);
        }
      }
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  private boolean validateName(String name){
    List<Company> companies = companyService.getAll();

    for (Company company:companies
    ) {
      if(company.getName().equals(name))
        return false;
    }
    return true;
  }
}
