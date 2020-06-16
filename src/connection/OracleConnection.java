package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import domain.CustomerAccountException;



public class OracleConnection
{
	private Connection connection;
	
	public OracleConnection() throws CustomerAccountException
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			open();
		}
		catch (ClassNotFoundException e)
		{
			throw new CustomerAccountException("Couldn't find database driver!");
		}
	}
	
	public void open() throws CustomerAccountException
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:ORCL", "DEMO", "oracle");
		}
		catch (SQLException e)
		{
			throw new CustomerAccountException("Couldn't open database connection!");
		}
	}
	
	public void close() throws CustomerAccountException
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			throw new CustomerAccountException("Couldn't close database connection!");
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
}







