package service;

import java.sql.Connection;

import domain.Customer;
import domain.CustomerAccountException;
import persistence.CustomerAccountRepository;

public class CustomerAccountService 
{
  private Connection con;
  private CustomerAccountRepository customerAccountRepository;
  
  public CustomerAccountService(Connection con)
  {
	  this.con = con;
	  this.customerAccountRepository = new CustomerAccountRepository();
  }
  public void getCustomer(Customer customer) throws CustomerAccountException
  {
	  customer.clear();
	  customer.addAll(customerAccountRepository.selectAll(con));
  }
}
