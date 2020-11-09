package AddressBookDatabase;

import java.io.IOException;
import java.util.*;

public class AddressBookDictionary {
	public static HashMap<String, AddressBookSingle> dictionary = new HashMap<>();

	public AddressBookDictionary(HashMap<String, AddressBookSingle> dictionary) {
		AddressBookDictionary.dictionary = dictionary;
	}

	public static void displayBookNames() {
		for (String book : dictionary.keySet()) {
			System.out.println(book);
		}
	}

	public static void displayAllBooks() {
		for (String book : dictionary.keySet()) {
			System.out.println(book + " contains: \n");
			for (AddressBookMain contact : dictionary.get(book).addressbook.values()) {
				contact.Display();
			}
		}
	}

	public static List<AddressBookMain> initializeList() {
		List<AddressBookMain> contacts = new ArrayList<>();
		for (AddressBookSingle book : dictionary.values()) {
			for (AddressBookMain contact : book.addressbook.values()) {
				contacts.add(contact);
			}
		}
		return contacts;
	}
	
	public static List<AddressBookSingle> initializeBooks() {
		List<AddressBookSingle> books = new ArrayList<>();
		for (AddressBookSingle book : dictionary.values())
			books.add(book);
		return books;
	}

	private synchronized static void initialization() throws IOException {

		HashMap<Integer, Boolean> threadStatus = new HashMap<>();
		threadStatus.put(1, false);

		Runnable task1 = () -> {
			DatabaseOperations.initializeDictionary();
			threadStatus.put(1, true);
		};
		Thread thread1 = new Thread(task1);
		thread1.start();

		while (threadStatus.containsValue(false)) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threadStatus.put(2, false);
		Runnable task2 = () -> {
			try {
				JSONFileOperations.writeDataInJSONFile(initializeList());
				threadStatus.put(2, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Thread thread2 = new Thread(task2);
		thread2.start();

		while (threadStatus.containsValue(false)) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		while (true) {
			initialization();
			System.out.println("1. Create and Add AddressBook");
			System.out.println("2. Select AddressBook");
			System.out.println("3. Delete AddressBook");
			System.out.println("4. Exit");
			System.out.println("Enter your choice : ");
			@SuppressWarnings("resource")
			Scanner ob1 = new Scanner(System.in);
			char choice = ob1.next().charAt(0);

			switch (choice) {
			case '1':
				System.out.println("Enter name of Address Book to be created: ");
				Scanner ob2 = new Scanner(System.in);
				String bookName = ob2.nextLine();
				bookName = bookName.toUpperCase();
				DatabaseOperations.createBook(bookName);
				HashMap<String, AddressBookMain> book = new HashMap<>();
				dictionary.put(bookName, new AddressBookSingle(bookName, book));
				break;
			case '2':
				System.out.println("Enter name of Address Book to be selected: ");
				displayBookNames();
				Scanner ob3 = new Scanner(System.in);
				String current = ob3.nextLine();
				current = current.toUpperCase();
				AddressBookSingle activeBook = dictionary.get(current);
				AddressBookSingle.bookOperations(activeBook);
				break;
			case '3':
				System.out.println("Enter name of Address Book to be removed: ");
				Scanner ob4 = new Scanner(System.in);
				String rem = ob4.nextLine();
				rem = rem.toUpperCase();
				dictionary.remove(rem);
				System.out.println("Address Book Deleted");
				break;
			case '4':
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

	public static void displayCompleteContactList(List<AddressBookMain> readDataFromJSONFile) {
		for(AddressBookMain contact: readDataFromJSONFile)
		{
			contact.toString();
		}
	}
}
