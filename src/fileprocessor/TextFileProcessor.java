package fileprocessor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TextFileProcessor{
	public static ArrayList<ArrayList<String>> textFileReader (String tcFileName, int[][] tcSkeleton)  throws Exception {
		
		File file = new File(tcFileName);			// Create file object based on given file name string
		ArrayList<ArrayList<String>> processedInfo = new ArrayList<ArrayList<String>> ();
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		int lineNum = 0;
		int lnSkeletonPosition = 0;
		// Loop through lines of the given text file
		while ((st = br.readLine()) != null) {
			int nStartChar = 0;
			int nEndChar = 0;
			int nLengthString = st.length();
			int nCharCount = 0;
			// Loop through each line of the given skeleton to process the file and separate into the array
			int nSkeleLen = tcSkeleton[0].length;
			for (int ny = 0; ny < nSkeleLen; ny++) {
				// Determines if line is shorter than expected and processes accordingly
				if (nEndChar + tcSkeleton[lnSkeletonPosition][ny] > nLengthString) {
					nEndChar = nLengthString;
				} else {
					nEndChar = nEndChar + tcSkeleton[lnSkeletonPosition][ny];
				}
				nCharCount = nCharCount + nEndChar;
				processedInfo.add(new ArrayList<String>());
				processedInfo.get(lineNum).add(ny, st.substring(nStartChar,nEndChar));
				nStartChar = nEndChar;
			}
			lineNum = lineNum + 1;
			lnSkeletonPosition = lnSkeletonPosition + 1;
			// Go back to first Skeleton row if reached the end of the array
			if (lnSkeletonPosition >= tcSkeleton.length)
				lnSkeletonPosition = 0;
		}
		
		return processedInfo;
	}
	
	public static void textFileWriter (String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite)  throws Exception {
		FileWriter writer = new FileWriter(tcFileName);
		BufferedWriter bw = new BufferedWriter(writer);
		
		for (int writex = 0; writex < tcDataToWrite.size()/tcDataToWrite.get(0).size(); writex++) {
			for (int writey = 0; writey < tcDataToWrite.get(0).size(); writey++) {
				bw.write(tcDataToWrite.get(writex).get(writey));
			}
			bw.write("\n");
		}
		bw.close();
		writer.close();
		
	}
}