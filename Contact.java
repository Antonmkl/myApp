package myApp;

public class Contact {
	
	private String firstName;
	private String lastName;
	private int phoneNumber;
	
	Contact (String afirstName, String alastName, int aphoneNumber)
	{
		this.firstName = afirstName;
		this.lastName = alastName;
		this.phoneNumber = aphoneNumber;
	}
	
		public String getFirstName()
		{
			return this.firstName;
		}
		
		public String getLastName()
		{
			return this.lastName;
		}
		
		public int getPhoneNumber()
		{
			return this.phoneNumber;
		}

}
