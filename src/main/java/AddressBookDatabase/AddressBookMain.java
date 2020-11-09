package AddressBookDatabase;

public class AddressBookMain {

	public String bookName;
	public String firstName;
	public String lastName;
	public String address; 
	public String city;
	public String state;
	public int zip;
	public String phoneNumber;
	public String email;
	
	public AddressBookMain(String bookName, String firstName, String lastName, 
		      	           String address, String city, String state, int zip, 
						   String phoneNumber, String email)
	{
		this.bookName=bookName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.phoneNumber=phoneNumber;
		this.email=email;
	}
	
	@Override
	public String toString()
	{
		return "Book Name: "+ bookName + "\n" + "First Name: "+ firstName + "\n" + "Last Name: "+ lastName + 
			   "\n" + "Address: "+ address + "\n" + "City: "+ city + "\n" + "State: "+ state + "\n" + "Zipcode: "+ zip + 
			   "\n" +"Phone Number: "+ phoneNumber + "\n" + "Email ID: "+ email + "\n";
	}
	
	public void Display()
	{
		System.out.println("Book Name: "+ bookName);
		System.out.println("First Name: "+ firstName);
		System.out.println("Last Name: "+ lastName);
		System.out.println("Address: "+ address);
		System.out.println("City: "+ city);
		System.out.println("State: "+ state);
		System.out.println("Zipcode: "+ zip);
		System.out.println("Phone Number: "+ phoneNumber);
		System.out.println("Email ID: "+ email);
		System.out.println();
	}
	
}
