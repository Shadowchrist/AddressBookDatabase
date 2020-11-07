package AddressBookDatabase;

import java.io.*;
import java.util.*;

import com.google.gson.*;

public class JSONFileOperations {
	public static String jsonFilePath = "C:\\Users\\DELL\\Desktop\\Practice\\Eclipse\\JAVA\\AddressBookDatabase\\src\\main\\resources\\contacts.json";

	public static void writeDataInJSONFile(List<AddressBookMain> contacts) throws IOException {
		FileWriter writer = new FileWriter(jsonFilePath);
		Gson gson = new Gson();
		String jsonString = gson.toJson(contacts);
		writer.write(jsonString);
		writer.close();
	}
	
	/*public List<AddressBookMain> readDataFromJSONFile() throws IOException {

		Reader reader = Files.newBufferedReader(jsonFilePath); 
		Gson gson = new Gson();
		AddressBookMain[] contactObject = gson.fromJson(reader, AddressBookMain[].class);
		List<AddressBookMain> addressBook = Arrays.asList(contactObject);
		return addressBook;
	}*/
}
