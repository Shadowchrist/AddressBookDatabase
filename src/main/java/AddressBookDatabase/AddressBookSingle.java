package AddressBookDatabase;
import java.util.*;
import java.io.*;

public class AddressBookSingle {
		
	public String name;
	public HashMap<String, AddressBookMain> addressbook;
	public AddressBookSingle(String bookName, HashMap<String, AddressBookMain> book) 
	{
		this.name=bookName;
		this.addressbook=book;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public static void addDetails(AddressBookSingle book)
	{
		String firstName=UserInput.getFirstName();
		String lastName=UserInput.getLastName();
		String index=firstName+" "+lastName+"-"+book.name;
		index=index.toUpperCase();
		String phoneNumber=UserInput.getPhoneNumber();
		if(!book.addressbook.containsKey(index))
		{	
			String address=UserInput.getAddress();
			String city=UserInput.getCity();
			String state=UserInput.getState();
			int zip=UserInput.getZipcode();
			String email=UserInput.getEmail();
			AddressBookMain contact = new AddressBookMain(book.name,firstName,lastName,address,city,
									  state,zip,phoneNumber,email);
			book.addressbook.put(index,contact);
			DatabaseOperations.addDetailinBook(book.name,contact,phoneNumber);
			System.out.println("New contact added.");
			book.addressbook.get(index).Display();
		}
		else
			System.out.println("Contact details already present.");
	}
	public static void editDetails(AddressBookSingle book)
	{
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String firstName=UserInput.getFirstName();
		String lastName=UserInput.getLastName();
		String index=firstName+" "+lastName;
		index=index.toUpperCase();
		
		if(!book.addressbook.isEmpty()&&book.addressbook.containsKey(index))
		{
			int flag=0;
			while(flag==0)
			{
				System.out.println("What do you want to edit? \n"
						+ "1. Address \n"
						+ "2. City \n"
						+ "3. State \n"
						+ "4. Zipcode \n"
						+ "5. Phone Number \n"
						+ "6. Email ID \n"
						+ "7. Password \n"
						+ "8. Editing finished.");
				System.out.println("Enter your choice: \n");
				int ch=sc.nextInt();
				switch(ch)
				{
					case 1:
						String newaddress=UserInput.getAddress();
						book.addressbook.get(index).address=newaddress;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newaddress, 1);
						break;
					case 2:
						String newcity=UserInput.getCity();
						book.addressbook.get(index).city=newcity;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newcity, 2);
						break;
					case 3:
						String newstate=UserInput.getState();
						book.addressbook.get(index).state=newstate;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newstate, 3);
						break;
					case 4:
						int newzip=UserInput.getZipcode();
						book.addressbook.get(index).zip=newzip;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newzip, 4);
						break;
					case 5:
						String newnumber=UserInput.getPhoneNumber();
						book.addressbook.get(index).phoneNumber=newnumber;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newnumber, 5);
						break;
					case 6:
						String newemail=UserInput.getEmail();
						book.addressbook.get(index).email=newemail;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newemail, 6);
						break;
					case 7:
						String newpassword=UserInput.getPassword();
						book.addressbook.get(index).password=newpassword;
						DatabaseOperations.editDetailsinBook(book, book.addressbook.get(index), index, newpassword, 7);
						break;
					case 8:
						System.out.println("Changes saved.");
						flag=1;
						break;
					default:
						System.out.println("Invalid choice!!!");
				}
			}
			System.out.println(" Updated Contact details: ");
			book.addressbook.get(index).Display();
		}	
		else
			System.out.println("Contact does not exist!!!");
	}
	
	public static void removeDetails(AddressBookSingle book)
	{
		String firstName=UserInput.getFirstName();
		String lastName=UserInput.getLastName();
		String index=firstName+" "+lastName;
		index=index.toUpperCase();
		String phoneNumber=UserInput.getPhoneNumber();
		if(!book.addressbook.isEmpty()&&book.addressbook.containsKey(index)&&book.addressbook.get(index).phoneNumber==phoneNumber)
		{
			book.addressbook.remove(index);
			System.out.println("Contact removed.");
		}
		else
			System.out.println("Contact does not exist!!!");
	}
	
	public static void displayAll(AddressBookSingle book)
	{
		for(AddressBookMain contact: book.addressbook.values())
		{
			contact.Display();
		}
	}
	
	public static void displayDetails(AddressBookSingle book)
	{
		String firstName=UserInput.getFirstName();
		String lastName=UserInput.getLastName();
		String index=firstName+" "+lastName;
		index=index.toUpperCase();
		if(!book.addressbook.isEmpty()&&book.addressbook.containsKey(index))
		{
			book.addressbook.get(index).Display();
		}
		else
			System.out.println("Contact does not exist!!!"); 
	}
	
	public static void bookOperations(AddressBookSingle book)
	{
		while(true)
		{
			System.out.println("Active Book: "+book.name);
			
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Enter 1 to add a contact detail.");
			System.out.println("Enter 2 to edit a contact detail.");
			System.out.println("Enter 3 to remove a contact detail.");
			System.out.println("Enter 4 to display a contact detail.");
			System.out.println("Enter 5 to display all contact details.");
			System.out.println("Enter 6 to return.");
			System.out.println("Enter your choice:");
			
			int ch=sc.nextInt();
			
			switch(ch)
			{
				case 1:
					addDetails(book);
					break;
				case 2:
					editDetails(book);
					break;
				case 3:					
					removeDetails(book);
					break;
				case 4:
					displayDetails(book);
					break;
				case 5:
					displayAll(book);
				default:
					return;
			}
		}
	}
}	
