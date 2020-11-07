package AddressBookDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {
	
	public static boolean checkFirstName(String firstName)
	{
		String regex="^[A-Z]{1}[a-zA-Z]{2,}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(firstName);
		return m.matches();
	}
	
	public static boolean checkLastName(String lastName)
	{
		String regex="^[A-Z]{1}[a-zA-Z]{2,}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(lastName);
		return m.matches();
	}

	public static boolean checkEmail(String email) 
	{
		String regex="(?=[^@\\s]+@[^@\\s]+$)^[a-zA-Z0-9]{1,}+(([\\.+-_][a-zA-Z0-9]{1,})?)+@(?:[a-zA-Z0-9]{1,})+\\.[a-z]{2,4}+((\\.[a-z]{2})?)$";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean checkPhoneNumber(String phoneNumber) {
		String regex="^[0-9]{2,3}\\s[0-9]{10}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	public static boolean checkPassword(String password) 
	{
		String regex="^(?=.*[A-Z])(?=.*[0-9])(?=[^.,:;'!@#$%^&*_+=|]*[.,:;'!@#$%^&*_+=|][^.,:;'!@#$%^&*_+=|]*$).{8,}$";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(password);
		return m.matches();
	}

	public static boolean checkAddress(String aname)
	{
		String regex="^[a-zA-Z0-9\\s.,']{3,}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(aname);
		return m.matches();
	}

	public static boolean checkZipcode(int zip) 
	{
		String regex="^[0-9]{6}";
		Pattern p = Pattern.compile(regex);
		String zname=Integer.toString(zip);
		Matcher m = p.matcher(zname);
		return m.matches();
	}

	public static boolean checkCityName(String cname) 
	{
		String regex="^[A-Z]{1}[a-zA-Z\\s]{2,}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(cname);
		return m.matches();
	}

	public static boolean checkStateName(String sname) 
	{
		String regex="^[A-Z]{1}[a-zA-Z\\s]{2,}";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(sname);
		return m.matches();
	}
	
}