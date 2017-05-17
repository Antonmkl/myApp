package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ContactQueries {
	
	// DB connection details
		private static final String URL = "jdbc:mysql://localhost:3306/oopjava";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "";

		private Connection connection = null;
		private PreparedStatement selectAllContacts = null;
		private PreparedStatement insertContact = null;
		
		public ContactQueries()
		{
			try
			{
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
				selectAllContacts = connection.prepareStatement("SELECT * FROM contact");
				
				
				insertContact = connection.prepareStatement("INSERT INTO contact VALUES (?,?,?)");
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
				System.exit(1);
			}
		}
		
		
		public ArrayList<Contact> getAllContacts()
		{
			ArrayList<Contact> results = null;
			ResultSet resultSet = null;
			
			try
			{
				resultSet = selectAllContacts.executeQuery(); 
				results = new ArrayList<Contact>();
			
				while(resultSet.next()) 
				{
					
					results.add(new Contact(
						resultSet.getString("firstName"), 
						resultSet.getString("lastName"), 
						resultSet.getInt("phoneNumber"))); 
				}
			} 

			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			finally
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
			} 
			
			return results;
		} 
		
		public void addContact(String firstName, String lastName, int phoneNumber)
		{
			try
			{
				
				insertContact.setString(1, firstName);
				insertContact.setString(2, lastName);
				insertContact.setInt(3, phoneNumber);
				
				
				int result = insertContact.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}	
			finally
			{
				try
				{
					insertContact.close();
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
			} 
		}

}
