import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SerialSearchPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if( args.length < 2) {
			System.out.println("Usage: Java SerialSearchPattern FileName Pattern");
			System.exit(0);
		}
		SerialSearchFile sf = new SerialSearchFile();
		String fileName = args[0];    // fileName = files/wikipedia2text-extracted.txt
		String pattern = args[1];     // pattern = "(John) (.+?) ";
		//String pattern = "(There is) \\d";
		
		int total = 0;
		long start = System.currentTimeMillis();
		try {
			total = sf.searchPattern(fileName, pattern);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Total occurence of pattern \'" + pattern + "\' is " + total );
		System.out.println("Total time cost for serial solution is " + (end - start) + "ms.");
	}

}

class SerialSearchFile {
	// Search a text file for a pattern
	public int searchPattern(String fileName, String pattern) throws IOException {
		FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        int currentRow = 0;
        int totalFound = 0;
        // read in the rest of rows
        while ((line = bufferedReader.readLine()) != null) {
        	totalFound += searchLine(line, pattern);
            currentRow ++;
        }
        System.out.println("Total number of lines searched is " + currentRow);
        bufferedReader.close();
        fileReader.close();
        return totalFound;
	}//end of searchPattern
	
	public static int searchLine(String text, String patt){
		Pattern pattern = Pattern.compile(patt);
		Matcher matcher = pattern.matcher(text);

		int count = 0;
		while(matcher.find()) {
		    count++;
		    //System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
		}
		return count;
	}
	
}

//http://tutorials.jenkov.com/java-regex/matcher.html#java-matcher-example
//http://www.evanjones.ca/software/wikipedia2text.html
//http://tutorials.jenkov.com/java-regex/syntax.html
