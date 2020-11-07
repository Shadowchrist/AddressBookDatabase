package AddressBookDatabase;

import java.util.Scanner;

public class UserInput {
	
	public static int flag;
	public static String getFirstName()
	{	
		String fname = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid first name: \n (First character should be in upper case and should be at least 3 characters long) \n");
			fname= sc.nextLine();
			if(!UserRegistration.checkFirstName(fname))
				System.out.println("Invalid First Name!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return fname;
	}	
		
	public static String getLastName()	
	{
		String lname = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid last name: \n (First character should be in upper case and should be at least 3 characters long) \n");
			lname= sc.nextLine();
			if(!UserRegistration.checkLastName(lname))
				System.out.println("Invalid Last Name!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return lname;
	}
	
	public static String getEmail()	
	{
		String email = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid email ID: \n");
			email= sc.nextLine();
			if(!UserRegistration.checkEmail(email))
				System.out.println("Invalid email ID!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return email;
	}
	
	public static String getPhoneNumber() 
	{
		String phoneNumber = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid phone number: \n (Country code followed by a space and then the 10 digit number) \n");
			phoneNumber= sc.nextLine();
			if(!UserRegistration.checkPhoneNumber(phoneNumber))
				System.out.println("Invalid number!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return phoneNumber;
	}
	
	public static String getPassword() 
	{
		String password = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid password: \n "
					+ "Criteria: \n "
					+ "1. Should be at least 8 characters long \n "
					+ "2. Should have at least 1 Upper Case letter \n "
					+ "3. Should have at least 1 number \n "
					+ "4. Should have exactly 1 special character \n");
			password= sc.nextLine();
			if(!UserRegistration.checkPassword(password))
				System.out.println("Invalid number!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return password;
	}

	public static String getState() 
	{
		String sname = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid State name: \n (First character should be in upper case and should be at least 3 characters long) \n");
			sname= sc.nextLine();
			if(!UserRegistration.checkStateName(sname))
				System.out.println("Invalid State Name!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return sname;
	}

	public static String getCity() 
	{
		String cname = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid city name: \n (First character should be in upper case and should be at least 3 characters long) \n");
			cname= sc.nextLine();
			if(!UserRegistration.checkCityName(cname))
				System.out.println("Invalid City Name!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return cname;
	}

	public static String getAddress() 
	{
		String aname = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid address: (First character should be in upper case and should be at least 3 characters long) \n");
			aname= sc.nextLine();
			if(!UserRegistration.checkAddress(aname))
				System.out.println("Invalid Address!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return aname;
	}
	
	public static int getZipcode() 
	{
		int zip=0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		flag=0;
		while(flag==0)
		{
			System.out.print("Enter a valid zipcode: \n");
			zip= sc.nextInt();
			if(!UserRegistration.checkZipcode(zip))
				System.out.println("Invalid Zipcode!!! Try Again...");
			else
			{	
				System.out.println("You can proceed.");
				flag=1;
				break;
			}
		}
		return zip;
	}


	
}