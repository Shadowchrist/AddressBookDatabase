package AddressBookDatabase;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.google.gson.*;

public class JSONFileOperations {
	public static String jsonFilePath = "C:\\Users\\DELL\\Desktop\\Practice\\Eclipse\\JAVA\\AddressBookDatabase\\src\\main\\resources\\contacts.json";

	public static void writeDataInJSONFile(List<AddressBookMain> contacts) throws IOException {
		FileWriter writer = new FileWriter(jsonFilePath);
		HashMap<String,List<AddressBookMain>> jsonObject = new HashMap<>(); 
		jsonObject.put("Contacts", contacts);
		Gson gson = new Gson();
		String jsonString = gson.toJson(jsonObject);
		writer.write(jsonString);
		writer.close();
	}
	
	public static List<AddressBookMain> readDataFromJSONFile() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(jsonFilePath)); 
		Gson gson = new Gson();
		AddressBookMain[] contacts = gson.fromJson(reader, AddressBookMain[].class);
		List<AddressBookMain> addressBook = Arrays.asList(contacts);
		return addressBook;
	}
}
