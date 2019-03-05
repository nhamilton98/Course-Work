import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner dataIn;
		boolean fromFile;
		
		if (args.length > 0)
		{
			dataIn = new Scanner(new File(args[0]));
			fromFile = true;
		}
		else
		{
			dataIn = new Scanner(System.in);
			fromFile = false;
		}
		
		startRegex(dataIn, fromFile);
		
		dataIn.close();
	}
	
	private static void startRegex(Scanner dataIn, boolean fromFile)
	{
		String menuChoice = "";
		String inputString = "";
		
		System.out.println("Regex Menu");
		System.out.println("A - Social Security Number");
		System.out.println("B - U.S. Phone Number");
		System.out.println("C - E-mail Address");
		System.out.println("D - Name (Last, First, MI; MI(s) optional");
		System.out.println("E - Date (MM-DD-YYYY)");
		System.out.println("F - Street Address");
		System.out.println("G - City, State, ZIP");
		System.out.println("H - Military Time");
		System.out.println("I - US Currency (including cents)");
		System.out.println("J - URL (not case sensitive)");
		System.out.println("K - Password (10+ characters; at least one upper and lower case letter, digit, and special character; cannot have more than two of the same consecutive characters)");
		System.out.println("L - Words ending in \"ion\" with an odd number of characters.");
		System.out.println("Q - Quit");
		
		if (!fromFile)
			System.out.println("Enter menu choice:");
		else
			System.out.println();
		
		do {
			menuChoice = dataIn.nextLine();
			menuChoice = menuChoice.toLowerCase();
			if (!menuChoice.equals("q"))
				inputString = dataIn.nextLine();
			
			switch (menuChoice)
			{
				case "a":
					regexSSN(inputString);
					break;
				case "b":
					regexPhoneNumber(inputString);
					break;
				case "c":
					regexEmail(inputString);
					break;
				case "d":
					regexRosterName(inputString);
					break;
				case "e":
					regexDate(inputString);
					break;
				case "f":
					regexStreetAddress(inputString);
					break;
				case "g":
					regexMailAddress(inputString);
					break;
				case "h":
					regexMilitaryTime(inputString);
					break;
				case "i":
					regexCurrency(inputString);
					break;
				case "j":
					regexURL(inputString);
					break;
				case "k":
					regexPassword(inputString);
					break;
				case "l":
					regexIon(inputString);
					break;
				case "q":
					System.out.println("End of regex tester");
					break;
				default:
					System.out.println("Invalid menu choice: " + menuChoice);
					break;
			}
		} while (!menuChoice.equals("q") && dataIn.hasNext());
	}
	
	/* SSN (A)
	 * Valid: 123-45-6789
	 * 		  123 45 6789
	 * 		  123456789
	 * 
	 * Invalid: 1234
	 * 			1234567890
	 * 			12-345-6789
	 * 			1234-56789
	 * 			12 345 6789
	 * 			1234 56789
	 * 			123 45-6789
	 */
	private static void regexSSN(String input)
	{
		String regex = "^\\d{3}([- ]?)\\d{2}\\1\\d{4}$";
		matchesRegex(regex, input, "SSN");
	}
	
	/* Phone Number (B)
	 * Valid: 123-456-7890
	 * 		  123-4567890
	 * 		  123 456-7890
	 * 		  123 4567890
	 * 		  (123) 456-7890
	 * 		  (123) 4567890
	 * 		  (123)-456-7890
	 * 		  (123)-4567890
	 * 
	 * Invalid: 1234
	 * 			1234567890
	 * 			123-456-78901
	 * 			12-345-67890
	 * 			123456-7890
	 * 			(123-456-7890
	 * 			123)-456-7890
	 * 			(123)4567890
	 */
	private static void regexPhoneNumber(String input)
	{
		String regex = "^((\\(\\d{3}\\)|\\d{3})[\\- ]\\d{3}-?\\d{4})$";
		matchesRegex(regex, input, "Phone Number");
	}
	
	/* E-mail (C)
	 * Valid: example@email.com
	 * 		  example123@email.com
	 * 		  ex.ample@email.com
	 * 		  ex_ample@email.com
	 * 		  ex-ample@email.com
	 * 		  example@email123.com
	 * 		  example@e_mail.com
	 * 		  example@e-mail.com
	 * 		  example@eagles.ewu.edu
	 * 		  eXaMpLe@EmAiL.cOm
	 * 
	 * Invalid: example
	 * 		    example@
	 * 			example@email.commm
	 * 			example@email.123
	 * 			example@e,mail.com
	 */
	private static void regexEmail(String input)
	{
		String regex = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z]{2,4})+$";
		matchesRegex(regex, input, "E-mail");
	}
	
	/* Roster Name (D)
	 * Valid: Doe, John, M
	 * 		  Doe, John, MI
	 * 		  Doe, Jane
	 * 
	 * Invalid: Doe, John,
	 * 			Doe, JohnJohn
	 * 			Doe
	 * 			Doe, J
	 * 			D, Jane
	 */
	private static void regexRosterName(String input)
	{
		String regex = "^(([A-Z]{1}[a-z]+, ){2}[A-Z]*|[A-Z]{1}[a-z]+, [A-Z]{1}[a-z]+)$";
		matchesRegex(regex, input, "Name");
	}
	
	/* Date (E)
	 * Valid: 10/24/2018
	 *		  10-24-2018
	 * 		  02/29/2016
	 * 		  02-29-2016
	 * 
	 * Invalid: 1
	 * 			10/24
	 * 			24/10/2018
	 * 			10/32/2018
	 * 			11/31/2018
	 * 			02/29/2018
	 * 			02/30/2016
	 * 			02/29/2000
	 * 			10-24/2018
	 * 			1/1/2018
	 */
	private static void regexDate(String input)
	{
		String regex = "^\\d{2}([-/])\\d{2}\\1\\d{4}$";
		if (validate(input, "Date"))
			matchesRegex(regex, input, "Date");
		else
			System.out.println("Invalid Date: " + input);
	}
	
	/* Street Address (F)
	 * Valid: 123 Streetname St
	 * 		  123 Streetname Street
	 * 		  123 N Streetname Boulevard
	 * 		  123 W Streetname Blvd
	 * 		  123 E 1st Avenue
	 * 
	 * Invalid: 123
	 * 			Streetname
	 * 			123 N Blvd
	 * 			Streetname Blvd
	 */
	private static void regexStreetAddress(String input)
	{
		String regex = "^[0-9]+ ([NSEW][ ])?([A-Z]{1}|[0-9]+)[A-Za-z ]+ (Ave|Avenue|Blvd|Boulevard|Rd|Road|St|Street|Ct|Court)$";
		matchesRegex(regex, input, "Street Address");
	}
	
	/* Mailing Address (G)
	 * Valid: Cheney, WA 99004
	 * 		  Cheney, WA, 99004
	 * 		  Cheney, WA 99004-1234
	 * 		  Cheney, Washington 99004
	 * 
	 * Invalid: Cheney
	 * 			Cheney, Washington
	 * 			Cheney, WA, 9900
	 * 			Cheney, WA 99004-
	 */
	private static void regexMailAddress(String input)
	{
		String regex = "^[A-Za-z' ]+, [A-Za-z' ]+,? \\d{5}([-]\\d{4})?$";
		matchesRegex(regex, input, "Street, State, ZIP");
	}
	
	/* Military Time (H)
	 * Valid: 23:59
	 * 		  00:00
	 * 		  02:02
	 * 
	 * Invalid: 1
	 * 			24:00
	 * 			00:60
	 * 			241:30
	 * 			24:1
	 * 			2:02
	 */
	private static void regexMilitaryTime(String input)
	{
		String regex = "^\\d{2}:\\d{2}$";
		if (validate(input, "Time"))
			matchesRegex(regex, input, "Time");
		else
			System.out.println("Invalid Time: " + input);
	}
	
	/* Currency (I)
	 * Valid: $1.23
	 * 		  $1,234.56
	 * 		  $12,345,678.90
	 * 
	 * Invalid: 1
	 * 			1.2
	 * 			1.23
	 * 			$1234.56
	 * 			$12345,678.90
	 */
	private static void regexCurrency(String input)
	{
		String regex = "^\\$\\d{1,3}(,\\d{3})*.\\d{2}$";
		matchesRegex(regex, input, "Dollar Amount");
	}
	
	/* URL (J)
	 * Valid: url.com
	 * 		  www.url.com
	 * 		  http://url.org
	 * 		  https://url.org
	 * 		  http://www.url.gov
	 * 		  https://www.url.gov
	 * 		  HtTpS://wWw.UrL.iNfO
	 * 
	 * Invalid: url.123
	 * 			ur l.com
	 * 			url.commm
	 */
	private static void regexURL(String input)
	{
		String regex = "^(?i)(http[s]?://)?([w]{3}.)?[a-zA-Z0-9_\\-]+(\\.[a-zA-Z]{2,4})+$";
		matchesRegex(regex, input, "URL");
	}
	
	/* Password (k)
	 * Valid: passWord1!
	 * 		  PassWord123?
	 * 		  passworD1.
	 * 
	 * Invalid: pass
	 * 			Password
	 * 			PASSWORD
	 * 			password4
	 * 			Password4
	 * 			Password!
	 * 			Passsword1!
	 */
	private static void regexPassword(String input)
	{
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\.\\?]).{10,}$";
		if (validate(input, "password"))
			matchesRegex(regex, input, "Password");
		else
			System.out.println("Invalid Password: " + input);
	}
	
	/* Odd -ion Word (L)
	 * Valid: ion
	 * 		  bastion
	 * 		  BaStIoN
	 * 
	 * Invalid: cat
	 * 			cation
	 *			cations
	 */
	private static void regexIon(String input)
	{
		String regex = "^([A-Za-z][A-Za-z])*(?i)ion$";
		matchesRegex(regex, input, "Odd -ion Word");
	}
	
	private static boolean validate(String input, String type)
	{
		try
		{
			if (type.equalsIgnoreCase("date"))
			{
				String[] date = new String[3];
				int month, day, year;
				
				if (input.charAt(2) == '-')
					date = input.split("-");
				else
					date = input.split("/");
				
				month = Integer.parseInt(date[0]);
				day = Integer.parseInt(date[1]);
				year = Integer.parseInt(date[2]);
				
				if (month < 1 || month > 12)
					return false;
				else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 | month == 10 | month == 12)
					return day <= 31;
				else if (month == 2)
					if (year % 4 == 0 && !(year % 100 == 0))
						return day <= 29;
					else
						return day <= 28;
				else
					return day <= 30;
			}
			else if (type.equalsIgnoreCase("time"))
			{
				String[] time = new String[2];
				int hour, minute;
				time = input.split(":");
				hour = Integer.parseInt(time[0]);
				minute = Integer.parseInt(time[1]);
				
				if (time[0].length() != 2 || time[1].length() != 2)
					return false;
				return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
			}
			else if (type.equalsIgnoreCase("password"))
			{
				char[] password = input.toCharArray();
				int index = 0;
				while (index < password.length - 2)
				{
					if (password[index] == password[index + 1] && password[index] == password[index + 2])
						return false;
					
					index++;
				}
				
				return true;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		
		return false;
	}
	
	private static void matchesRegex(String regex, String input, String type)
	{
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(input);
		
		if (matcher.matches())
			System.out.println("Valid " + type + ": " + input);
		else
			System.out.println("Invalid " + type + ": " + input);
	}
}
