package test;

import connection.OracleConnection;
import domain.Customer;
import domain.CustomerAccountException;
import service.CustomerAccountService;

public class TestSelect {

	public static void main(String[] args) 
	{
		try
		{
			Customer customer = new Customer();
			
			OracleConnection oracon = new OracleConnection();
			
			CustomerAccountService customerAccountService = new CustomerAccountService(oracon.getConnection());
		
		    customerAccountService.getCustomer(customer);
			
			System.out.println(customer);
			
			oracon.close();
		}
		catch(CustomerAccountException e)
		{
			System.out.println(e.getMessage());
		}

	}

}
