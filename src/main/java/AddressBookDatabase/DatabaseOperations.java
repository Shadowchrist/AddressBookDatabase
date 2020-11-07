package AddressBookDatabase;

import java.sql.*;
import java.util.*;

public class DatabaseOperations {

	private static PreparedStatement contactStatement;
	private static PreparedStatement bookStatement;

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver created.");
		} catch (ClassNotFoundException c) {
			throw new IllegalStateException("Cannot find driver", c);
		}
		listDrivers();
		System.out.println("Connection successful at: " + connect());
		initializeDictionary();
		AddressBookDictionary.displayAllBooks();
	}

	private static Connection connect() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/addressbooksystem?characterEncoding=utf8";
		String username = "root";
		String password = "Shubham@1998";
		Connection connection;
		connection = DriverManager.getConnection(jdbcURL, username, password);
		return connection;
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println(driverClass.getClass().getName());
		}
	}

	public static int countNumberOfBooks() {
		int count = 0;
		try (Connection connection = DatabaseOperations.connect();) {
			DatabaseMetaData metaData = connection.getMetaData();
			String[] types = { "TABLE" };
			ResultSet tables = metaData.getTables(null, null, "%", types);
			while (tables.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void initializeDictionary() {
		String sql = "select distinct Book_Name from addressbooks order by Book_Name;";
		HashMap<String, AddressBookSingle> mapBooks = new HashMap<>();
		try (Connection connection = DatabaseOperations.connect();) {
			Statement statement = connection.createStatement();
			ResultSet books = statement.executeQuery(sql);
			while (books.next()) {
				String bookName = books.getString("Book_Name");
				mapBooks.put(bookName.toUpperCase(), readData(bookName, connection));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new AddressBookDictionary(mapBooks);
	}

	public static AddressBookSingle readData(String bookName, Connection connection) throws SQLException {
		String sql = String.format(
				"select First_Name,Last_Name,Address,City,State,Zip,Email,contacts.Phone_Number, Book_Name from contacts "
						+ "join addressbooks on addressbooks.Phone_Number=contacts.Phone_Number where Book_Name='%s' "
						+ "order by First_Name, Last_Name",bookName);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		HashMap<String, AddressBookMain> map = new HashMap<>();
		AddressBookSingle book = new AddressBookSingle(bookName, map);
		while (result.next()) {
			AddressBookMain contact = new AddressBookMain(result.getString("First_Name"), result.getString("Last_Name"),
					result.getString("Address"), result.getString("City"), result.getString("State"),
					result.getInt("Zip"), result.getString("Phone_Number"), result.getString("Email"), result.getString("Book_Name"));
			String index = contact.toString();
			book.addressbook.put(index, contact);
		}
		return book;
	}

	public synchronized static void addDetailinBook(AddressBookSingle book, AddressBookMain contact, String index) {
		
		Connection[] connection = new Connection[1];
		try {
			connection[0] = DatabaseOperations.connect();
			connection[0].setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<Integer, Boolean> threadStatus=new HashMap<>();
		threadStatus.put(1, false);
		
		Runnable task1 = () -> {
			String contactSql = String.format(
					"INSERT INTO contacts(First_Name, Last_Name, Address, City, State, Zip, Phone_Number, Email) "
				  + "values ('%s', '%s', '%s', '%s', '%s', %d, '%s','%s')",
				     contact.firstName, contact.lastName, contact.address, contact.city, contact.state, contact.zip,
					 contact.phoneNumber, contact.email);
			try {	
				contactStatement = connection[0].prepareStatement(contactSql);
				contactStatement.executeUpdate(contactSql);
			} catch (SQLException s) {
				s.printStackTrace();
				try {
					connection[0].rollback();
				} catch (SQLException sq) {
					sq.printStackTrace();
				}
			}
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
			String bookSql = String.format("INSERT INTO addressbooks(Book_Name, Phone_Number) values ('%s', '%s');",
					book.name, contact.phoneNumber);
			try {
				bookStatement = connection[0].prepareStatement(bookSql);
				bookStatement.executeUpdate(bookSql);
			} catch (SQLException s) {
				s.printStackTrace();
				try {
					connection[0].rollback();
				} catch (SQLException sq) {
					sq.printStackTrace();
				}
			}
			threadStatus.put(2, true);
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
		
		try {
			connection[0].commit();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection[0].close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	public static void createBook(String bookName) {

	}

	public static void editDetailsinBook(AddressBookSingle book, AddressBookMain addressBookMain, String index, int i) {

	}
}
