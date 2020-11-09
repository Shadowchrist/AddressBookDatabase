package AddressBookDatabase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookTest {

	@Test
	public void readingFromDB_NoOfEntries_ShouldMatchActual() {
		List<AddressBookMain> contactList = AddressBookDictionary.initializeList();
		boolean result = contactList.size() == 7 ? true : false;
		Assert.assertTrue(result);
	}

	@Test
	public void givenPhoneNumber_UpdatinginDB_UsingPreparedStatement_ShouldMatch() {
		DatabaseOperations addressBookService = new DatabaseOperations();
		addressBookService.updateContactPhoneNumber("Shubham", "Prasad" , "91 8083028650");
		addressBookService.updatePhoneNumber("Shubham", "Prasad" , "91 8083028650");
		boolean result = addressBookService.checkIfDBInSync("Shubham", "Prasad");
		Assert.assertTrue(result);
	}

	@Test
	public void readingFromDB_NoOfEntries_ByGivenCity_ShouldMatchActual() {
		DatabaseOperations addressBookService = new DatabaseOperations();
		int noOfAddressBookMains = addressBookService.getNoOfContactsByCity("New York City");
		boolean result = noOfAddressBookMains == 3 ? true : false;
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenMultipleAddressBookMains_WhenAddedToAddressbookDB_ShouldBeInSync() {
		DatabaseOperations addressBookService = new DatabaseOperations();	
		AddressBookMain[] contacts = {
				new AddressBookMain("Friends", "Tony", "Stark", "Stark Tower", "New York City",
				"New York", 113735, "", "tonystark@gmail.com"),
				new AddressBookMain("Family","Clark","Kent","Hall of Justice",
				"Metropolis","Columbia",153342,"45 8768678668","clarkkent@gmail.com")
		};
		Instant start = Instant.now();
		List<AddressBookMain> entries = addressBookService.addMultipleAddressBookMains(Arrays.asList(contacts));
		Instant end = Instant.now();
		System.out.println("Duration with Thread : " + Duration.between(start, end));
		System.out.println(entries.size());
		boolean result = entries.size() == 9 ? true : false;
		Assert.assertTrue(result);
	}
	
	@Before
	public void setup()
	{
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.port = 3000;
	}
	
	public AddressBookMain[] getContacts()
	{
		Response response = RestAssured.get("/Contacts");
		System.out.println("Contacts: \n" + response.asString());
		AddressBookMain[] contacts = new Gson().fromJson(response.asString(),AddressBookMain[].class);
		return contacts;
	}
	
	public Response addContactToJSONServer(AddressBookMain contact)
	{
		String contactJSON = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJSON);
		return request.post("/Contacts");
	}
	
	public AddressBookMain getContactData(String firstName, String lastName) {
		List<AddressBookMain> contacts = Arrays.asList(getContacts());
		return contacts.stream()
					   .filter((c)->c.firstName.equals(firstName) && c.lastName.equals(lastName))
					   .findFirst()
					   .orElse(null);
	}
	
	@Test
	public void GivenContactsFromJSONServer_ShouldMatchCount()
	{
		AddressBookMain[] contacts = getContacts();
		Assert.assertEquals(10, contacts.length);
	}
	
	@Test
	public void givenNewContactWhenAdded_shouldGetBack201Response()
	{
		AddressBookMain contact = new AddressBookMain("Family","Ray","Palmer","Hall of Justice",
				"Metropolis","Columbia",153342,"45 8768678668","clarkkent@gmail.com");
		Assert.assertEquals(201, addContactToJSONServer(contact).getStatusCode());
		List<AddressBookMain> contacts = Arrays.asList(getContacts());
		Assert.assertEquals(10, contacts.size());
	}
	
	@Test
	public void givenListOfContactsWhenAdded_shouldGetBack201Response()
	{
		AddressBookMain[] contacts = {
				new AddressBookMain("Friends", "Tony", "Stark", "Stark Tower", "New York City",
				"New York", 113735, "76 8726268787", "tonystark@gmail.com"),
				new AddressBookMain("Family","Clark","Kent","Hall of Justice",
				"Metropolis","Columbia",153342,"45 8768673454","clarkkent@gmail.com")
		};
		for(AddressBookMain contact: contacts)
		{
			Assert.assertEquals(201, addContactToJSONServer(contact).getStatusCode());
		}
		List<AddressBookMain> newContactList = Arrays.asList(getContacts());
		Assert.assertEquals(10, newContactList.size());
	}
	
	@Test
	public void ContactWhenUpdatedWithInfo_shouldGetBack200Response()
	{
		DatabaseOperations addressBookService = new DatabaseOperations();
		addressBookService.updatePhoneNumber("Ray", "Palmer" , "45 8768675332");
		AddressBookMain contact = getContactData("Ray","Palmer");
		String contactJSON = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJSON);
		Response response = request.put("/Contacts/"+contact.phoneNumber);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	@Test
	public void ContactWhenDeleted_ShouldGetBack200Response()
	{
		AddressBookMain contact = getContactData("Tony","Stark");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.delete("/Contacts/"+contact.phoneNumber);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
}
