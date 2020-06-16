package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.CustomerAccount;
import domain.CustomerAccountException;

public class CustomerAccountRepository 
{
    private final static String SQL_SELECT_ALL =
    		"SELECT CUSTOMER_ID,\n" +
    	    "   EMAIL,\n" +
    	    "   FIRSTNAME,\n" +
    	    "  LASTNAME,\n" +
    	    "   GENDER,\n" +
    	    "  BIRTHDAY,\n" +
    	    " CREDITS\n" +
        "	FROM CUSTOMER";    
     private PreparedStatement selectAllStatement;
     
     public List<CustomerAccount> selectAll(Connection con) throws CustomerAccountException
     {
    	 try
    	 {
    		 if(selectAllStatement == null)
    		 {
    			 selectAllStatement = con.prepareStatement(SQL_SELECT_ALL);
    		 }
    	 
             	 ResultSet resultSet =selectAllStatement.executeQuery(); 
    	 
		    	 if(resultSet != null)
		    	 {
		    		 List<CustomerAccount> accounts = new ArrayList<>();
		    		 while(resultSet.next())
		    		 {
		    			 Long customer_id =  resultSet.getLong(1);
		    			 String email = resultSet.getString(2);
		    			 String firstname = resultSet.getString(3);
		    			 String lastname = resultSet.getString(4);
		    			 String gender = resultSet.getString(5);
		    			 LocalDateTime birthday = resultSet.getTimestamp(6).toLocalDateTime();
		    			 Double credits = resultSet.getDouble(7);
		    			 accounts.add(new CustomerAccount(customer_id ,email,firstname,lastname,gender,birthday,credits));
		    		 }
		    		 
		    	 
		    	 return accounts;
    		 } 
	    	 else
	    	 {
	    		 throw new CustomerAccountException("Could not read data from database");
	    	 }
         }
	     catch(SQLException e)
	     {
	    	 throw new CustomerAccountException("Could not execute SQL statement");
	     }
		
}
     }
