import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Nathan Hamilton
// Forrest Wallace
// Matthew Hopkins
public class CodeDefender
{
	private static final File ERROR_LOG = new File("errorLog.txt");
	private static final File PASSWORD_FILE = new File("password.txt");
	
	private static PrintStream mErrorWriter;
	private static PrintStream mPasswordWriter;
	private static Scanner mPasswordReader;
	private static Scanner mKeyboardIn;
	
	private static String mFirstName, mLastName;
	private static int mNum1, mNum2;
	private static File mInputFile, mOutputFile;
	
	private static Scanner mInputFileReader;
	private static PrintStream mOutputFileWriter;
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException
	{
		try
		{
			if (ERROR_LOG.exists())
				ERROR_LOG.delete();
			ERROR_LOG.createNewFile();
			
			mErrorWriter = new PrintStream(ERROR_LOG);
			mErrorWriter.println("CodeDefender Errors Encountered:");
		} catch (IOException e)
		{
			System.out.println("Something went wrong while creating the error log file.");
			System.out.println("Program ending.");
			System.exit(-1);
		}
		
		mKeyboardIn = new Scanner(System.in);
		
		boolean invalid = true;
		String tempFirstName;
		System.out.println("Note: Names must be no larger than 50 characters.");
		System.out.println("      A-Z, a-z, ', -, and space are permitted.");
		System.out.println("      First letter must be uppercase.");
		System.out.println("-------------------------------------------------");
		do {
			System.out.println("FIRST NAME:");
			tempFirstName = mKeyboardIn.nextLine();
			
			if (validate(tempFirstName, "Name"))
			{
				mFirstName = tempFirstName;
				invalid = false;
			}
			else
			{
				System.out.println("Invalid First Name.\n");
				mErrorWriter.println("Invalid First Name: " + tempFirstName);
			}
		} while (invalid);
		
		System.out.println();
		invalid = true;
		String tempLastName;
		do {
			System.out.println("LAST NAME:");
			tempLastName = mKeyboardIn.nextLine();
			
			if (validate(tempLastName, "Name"))
			{
				mLastName = tempLastName;
				invalid = false;
			}
			else
			{
				System.out.println("Invalid Last Name.\n");
				mErrorWriter.println("Invalid Last Name: " + tempLastName);
			}
		} while(invalid);
		
		invalid = true;
		String tempNum = "";
		System.out.println();
		System.out.println("Note: Numbers must be integers between [-2147483648, 2147483647].");
		System.out.println("-----------------------------------------------------------------");
		do
		{
			try
			{
				System.out.println("NUMBER:");
				tempNum = mKeyboardIn.nextLine();
				
				if (validate(tempNum, "Number"))
				{
					if (Long.parseLong(tempNum) >= -2147483648 && Long.parseLong(tempNum) <= 2147483647)
					{
						mNum1 = Integer.parseInt(tempNum);
						invalid = false;
					}
					else
					{
						System.out.println("Invalid Number.\n");
						mErrorWriter.println("Invalid Number: " + tempNum);
					}
				}
				else
				{
					System.out.println("Invalid Number.\n");
					mErrorWriter.println("Invalid Number: " + tempNum);
				}
			} catch (NumberFormatException e)
			{
				System.out.println("Invalid number.\n");
				mErrorWriter.println("Invalid Number: " + tempNum);
			}
		} while (invalid);
		
		invalid = true;
		tempNum = "";
		System.out.println();
		do
		{
			System.out.println("ANOTHER NUMBER:");
			tempNum = mKeyboardIn.nextLine();
			
			if (validate(tempNum, "Number"))
			{
				if (Long.parseLong(tempNum) >= -2147483648 && Long.parseLong(tempNum) <= 2147483647)
				{
					mNum2 = Integer.parseInt(tempNum);
					invalid = false;
				}
			}
			else
			{
				System.out.println("Invalid Number.\n");
				mErrorWriter.println("Invalid Number: " + tempNum);
			}
		} while (invalid);
		
		invalid = true;
		String tempFilename = "";
		File tempFile;
		System.out.println();
		System.out.println("Note: File must reside in the current directory.");
		System.out.println("      File must be of type text. (extension: .txt)");
		System.out.println("--------------------------------------------------");
		do
		{
			System.out.println("INPUT FILE:");
			tempFilename = mKeyboardIn.nextLine();
			
			if (validate(tempFilename, "File"))
			{
				tempFile = new File(tempFilename);
				if (tempFile.exists())
				{
					invalid = false;
					mInputFile = new File(tempFilename);
				}
				else
				{
					System.out.println("Invalid Filename.\n");
					mErrorWriter.println("Invalid Input Filename: " + tempFilename);
				}
			}
			else
			{
				System.out.println("Invalid Filename.\n");
				mErrorWriter.println("Invalid Input Filename: " + tempFilename);
			}
		} while (invalid);
		mInputFileReader = new Scanner(mInputFile);
		
		invalid = true;
		System.out.println();
		do
		{
			System.out.println("OUTPUT FILE:");
			tempFilename = mKeyboardIn.nextLine();
			
			if (validate(tempFilename, "File") && !tempFilename.equals(mInputFile.getName()))
			{
				mOutputFile = new File(tempFilename);
				if (mOutputFile.exists())
					mOutputFile.delete();
				mOutputFile.createNewFile();
				
				invalid = false;
			}
			else
			{
				System.out.println("Invalid Filename.\n");
				mErrorWriter.println("Invalid Output Filename: " + tempFilename);
			}
		} while (invalid);
		mOutputFileWriter = new PrintStream(mOutputFile);
		
		mOutputFileWriter.println("NAME:");
		mOutputFileWriter.println(mFirstName + " " + mLastName);
		mOutputFileWriter.println();
		mOutputFileWriter.println("NUMBERS:");
		mOutputFileWriter.println(mNum1 + ", " + mNum2);
		mOutputFileWriter.println();
		mOutputFileWriter.println("ADDITION:");
		mOutputFileWriter.println((long)mNum1 + (long)mNum2);
		mOutputFileWriter.println();
		mOutputFileWriter.println("MULTIPLICATION:");
		mOutputFileWriter.println((long)mNum1 * (long)mNum2);
		mOutputFileWriter.println();
		mOutputFileWriter.println("INPUT FILE:");
		mOutputFileWriter.println(mInputFile.getName());
		mOutputFileWriter.println();
		mOutputFileWriter.println("CONTENTS:");
		while (mInputFileReader.hasNextLine())
			mOutputFileWriter.println(mInputFileReader.nextLine());
		
		invalid = true;
		String tempPassword = "";
		try
		{
			if (PASSWORD_FILE.exists())
				PASSWORD_FILE.delete();
			PASSWORD_FILE.createNewFile();
			
			mPasswordWriter = new PrintStream(PASSWORD_FILE);
			mPasswordReader = new Scanner(PASSWORD_FILE);
		} catch (Exception e)
		{
			System.out.println("Something went wrong while creating the password file.");
			System.out.println("Program ending.");
			System.exit(-1);
		}
		System.out.println();
		System.out.println("Note: Password must be at least 10 characters.");
		System.out.println("      Password must contain at least one upper case letter, one");
		System.out.println("      lower case letter, one number, and one punctuation character.");
		System.out.println("-------------------------------------------------------------------");
		do
		{
			System.out.println("CREATE PASSWORD:");
			tempPassword = mKeyboardIn.nextLine();
			
			if (validate(tempPassword, "Password"))
			{
				byte[] salt = createSalt();
				for (byte b: salt)
					mPasswordWriter.println(b);
				mPasswordWriter.println(hashPassword(tempPassword, salt));
				tempPassword = "";
				salt = null;
				invalid = false;
			}
			else
			{
				System.out.println("Password does not adhere to requirements.\n");
				mErrorWriter.println("Password does not adhere to requirements: " + tempPassword);
				tempPassword = "";
			}
		} while (invalid);
		
		invalid = true;
		byte[] salt = new byte[16];
		String hashedPassword = "";
		String tempHashedPassword = "";
		for (int x = 0; x < salt.length; x++)
			salt[x] = Byte.parseByte(mPasswordReader.nextLine());
		hashedPassword = mPasswordReader.nextLine();
		mPasswordReader.close();
		System.out.println();
		do
		{
			System.out.println("RE-ENTER PASSWORD:");
			tempPassword = mKeyboardIn.nextLine();
			
			tempHashedPassword = hashPassword(tempPassword, salt);
			if (tempHashedPassword.equals(hashedPassword))
			{
				System.out.println("Correct Password.");
				invalid = false;
			}
			else
				System.out.println("Incorrect Password.\n");
			try
			{
				mPasswordReader = new Scanner(PASSWORD_FILE);
			} catch (Exception e)
			{
				System.out.println("Something went wrong while creating the password file.");
				System.out.println("Program ending.");
				System.exit(-1);
			}
		} while (invalid);
		tempPassword = "";
		hashedPassword = "";
		salt = null;
		
		try
		{
			mKeyboardIn.close();
			mInputFileReader.close();
			mOutputFileWriter.close();
			mErrorWriter.close();
		} catch (Exception e)
		{
			System.out.println("Unable to close one or more open file. Memory may have been leaked.");
			mErrorWriter.println("Unable to close one or more open file. Memory may have been leaked.");
			System.exit(-1);
		}
	}
	
	private static boolean validate(String input, String type)
	{
		String regex = "";
		if (type.equalsIgnoreCase("name"))
			regex = "^[A-Za-z]{1}[A-Za-z '-]{1,49}$";
		else if (type.equalsIgnoreCase("number"))
			regex = "^-?\\d{1,10}|0$";
		else if (type.equalsIgnoreCase("file"))
			regex = "^(./)?[A-Za-z -_]+.txt$";
		else if (type.equalsIgnoreCase("password"))
			regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\.\\?]).{10,}$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		return matcher.matches();
	}
	
	private static String hashPassword(String password, byte[] salt)
	{
		String hashedPassword = null;
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(salt);
			
			byte[] bytes = md5.digest(password.getBytes());
			
			StringBuilder hex = new StringBuilder();
			for (int x = 0; x < bytes.length; x++)
				hex.append(Integer.toString((bytes[x] & 0xff) + 0x100, 16).substring(1));
			
			hashedPassword = hex.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("Password hashing algorithm does not exist.");
			mErrorWriter.println("Password hashing algorithm does not exist: MD5");
		}
		
		return hashedPassword;
	}
	
	private static byte[] createSalt() throws NoSuchAlgorithmException, NoSuchProviderException
	{
		SecureRandom generator = SecureRandom.getInstance("SHA1PRNG", "SUN");
		
		byte[] salt = new byte[16];
		generator.nextBytes(salt);
		
		return salt;
	}
}
