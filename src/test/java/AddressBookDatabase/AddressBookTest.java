package AddressBookDatabase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookTest {

	@Test
	public void readingFromDB_NoOfEntries_ShouldMatchActual() {
		List<AddressBookMain> contactList = AddressBookDictionary.initializeList();
		boolean result = contactList.size() == 10 ? true : false;
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
				new AddressBookMain("Tony", "Stark", "Stark Tower", "New York City",
				"New York", 113735, "", "tonystark@gmail.com", "Friends"),
				new AddressBookMain("Clark","Kent","Hall of Justice",
				"Metropolis","Columbia",153342,"","clarkkent@gmail.com","Family")
		};
		Instant start = Instant.now();
		List<AddressBookMain> entries = addressBookService.addMultipleAddressBookMains(Arrays.asList(contacts));
		Instant end = Instant.now();
		System.out.println("Duration with Thread : " + Duration.between(start, end));
		System.out.println(entries.size());
		boolean result = entries.size() == 9 ? true : false;
		Assert.assertTrue(result);
	}

}
