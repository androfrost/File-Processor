package fileprocessor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class delimitedFileProcessor {
	public static ArrayList<ArrayList<String>> delimitedFileReader (String tcFileName, String delimChar, String charGroup)  throws Exception {
		
		ArrayList<ArrayList<String>> processedInfo = new ArrayList<ArrayList<String>> ();
		
		try {
			File file = new File(tcFileName);			// Create file object based on given file name string
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String st;
			int lineNum = 0;
			// Loop through lines of the given text file
			while ((st = br.readLine()) != null) {
	
				boolean isCharGroup = false;
				boolean isProcessString = false;
				String currentChar = "", previousChar = "";
				String processedItem = "";
				int processCount = -1;
				int charCount = 0;
				processedInfo.add(new ArrayList<String>());
				
				// Loop through all characters in the given file line
				for (int charpos = 0; charpos < st.length(); charpos++) {
					currentChar = Character.toString(st.charAt(charpos));
					charCount = charCount + 1;
					
					// Turn isCharGroup on and off each time it is hit to split what is processed and what is not
					if ((currentChar.contentEquals(charGroup)) && (Boolean.compare(isProcessString, false) == 0)) {
						if (Boolean.compare(isCharGroup, false) == 0) {
							isCharGroup = true;
							processCount = processCount + 1;
						}
						else {
							isCharGroup = false;
						}
					}
					
					if (!(currentChar.contentEquals(charGroup)) && !(currentChar.contentEquals(delimChar)) && (Boolean.compare(isCharGroup, false) == 0)) {
						if (Boolean.compare(isProcessString, false) == 0) {
							isProcessString = true;
							processCount = processCount + 1;
						}
						else {
							if (currentChar.contentEquals(delimChar))
								isProcessString = false;
						}
					} else {
						if (currentChar.contentEquals(delimChar))
							isProcessString = false;
					}
					
					// When processing add each character to the string to be added to arraylist
					if ((Boolean.compare(isCharGroup, true) == 0) && (!currentChar.contentEquals(charGroup))) {
						processedItem = processedItem + currentChar;
					}
					
					if (Boolean.compare(isProcessString, true) == 0){
						processedItem = processedItem + currentChar;
					}
					
					if (previousChar.contentEquals(",") && currentChar.contentEquals(",")) {
						processCount = processCount + 1;
						//processedItem = processedItem + " ";
					}
					
					// Add processed string info to arraylist
					if (((Boolean.compare(isCharGroup, false) == 0) && (!currentChar.contentEquals(charGroup)) && (currentChar.contentEquals(delimChar))) || (charCount == st.length())) {
						processedInfo.get(lineNum).add(processCount, processedItem);
						processedItem = "";
					}
					
					//if (previousChar.contentEquals(",") && currentChar.contentEquals(",")) {
					//	processedInfo.get(lineNum).add(processCount, " ");
					//}
					
					previousChar = currentChar;
				}
				lineNum = lineNum + 1;
			}
		} catch(IOException ioe) {
			System.out.println("Delimited File Issues");
		}
		
		return processedInfo;
	}
	
	public static ArrayList<ArrayList<String>> commaDelimitedFileReader (String tcFileName) throws Exception{
		ArrayList<ArrayList<String>> processedInfo = new ArrayList<ArrayList<String>> ();
		
		processedInfo = delimitedFileReader(tcFileName, ",", "\"");
		
		return processedInfo;
	}
	
	public static void delimitedFileWriter (String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite, String delimChar, String charGroup)  throws Exception {
		FileWriter writer = new FileWriter(tcFileName);
		BufferedWriter bw = new BufferedWriter(writer);
		String currentChar = "";
		
		for (int writex = 0; writex < tcDataToWrite.size(); writex++) {
						
			for (int writey = 0; writey < tcDataToWrite.get(writex).size(); writey++) {
				if (writey > 0) {
					bw.write(delimChar);
				}
				
				for (int delimTest = 0; delimTest < tcDataToWrite.get(writex).get(writey).length(); delimTest++) {
					currentChar = Character.toString(tcDataToWrite.get(writex).get(writey).charAt(delimTest));
				}
				if (currentChar.contentEquals(charGroup)) {
					bw.write(charGroup);
				}
				bw.write(tcDataToWrite.get(writex).get(writey));
				if (currentChar.contentEquals(charGroup)) {
					bw.write(charGroup);
				}
			}
			bw.write("\n");
		}
		bw.close();
		writer.close();
		
	}
	public static void commaDelimitedFileWriter (String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite) throws Exception{
		ArrayList<ArrayList<String>> lalProcessedInfo = new ArrayList<ArrayList<String>> ();
		lalProcessedInfo = tcDataToWrite;
		
		delimitedFileWriter(tcFileName, lalProcessedInfo, ",", "\"");
	}
}