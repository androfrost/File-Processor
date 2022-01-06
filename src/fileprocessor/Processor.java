package fileprocessor;
import java.util.ArrayList;

public class Processor {
	
	public static void main(String[] args) throws Exception {
		//int[] testArray = Ascii.asciiStringValue("123456789");
		//for (int xa = 0; xa < testArray.length; xa++)
		//	System.out.println(testArray[xa]);
		textFileFormat1();
		delimitedFileFormat1();
		delimitedFileFormat2();
	}
	
	public static void textFileFormat1 () throws Exception {
		String fileName1 = "C:\\ImportFiles\\importfile1.txt";
		int[][] fileParsing = new int[2][4];
		fileParsing[0][0] = 3;
		fileParsing[0][1] = 10;
		fileParsing[0][2] = 10;
		fileParsing[0][3] = 10;
		fileParsing[1][0] = 30;
		fileParsing[1][1] = 1;
		fileParsing[1][2] = 1;
		fileParsing[1][3] = 1;
		ArrayList<ArrayList<String>> parsedFile = new ArrayList<ArrayList<String>> ();
		
		parsedFile = TextFileProcessor.textFileReader(fileName1, fileParsing);
		
		int[][] encryptionSkeleton = new int[5][3]; 
		encryptionSkeleton[0][0] = 12;
		encryptionSkeleton[1][0] = 15;
		encryptionSkeleton[2][0] = -11;
		encryptionSkeleton[3][0] = 6;
		encryptionSkeleton[4][0] = -2;
		encryptionSkeleton[0][1] = 5;
		encryptionSkeleton[1][1] = -8;
		encryptionSkeleton[2][1] = 7;
		encryptionSkeleton[3][1] = -7;
		encryptionSkeleton[4][1] = 0;
		encryptionSkeleton[0][2] = -6;
		encryptionSkeleton[1][2] = 5;
		encryptionSkeleton[2][2] = -3;
		encryptionSkeleton[3][2] = 0;
		encryptionSkeleton[4][2] = 0;
		parsedFile = Encryption.asciiEncryption(parsedFile, encryptionSkeleton);
		parsedFile = Encryption.asciiDecryption(parsedFile, encryptionSkeleton);
				
		TextFileProcessor.textFileWriter("C:\\ImportFiles\\exporttxt1.txt", parsedFile);
		delimitedFileProcessor.delimitedFileWriter("C:\\ImportFiles\\exportcsv1.csv", parsedFile,",","\"");
	}
	
	public static void delimitedFileFormat1 () throws Exception {
		String fileName2 = "C:\\ImportFiles\\importfile2.txt";
		ArrayList<ArrayList<String>> parsedFile = new ArrayList<ArrayList<String>> ();
		
		parsedFile = delimitedFileProcessor.delimitedFileReader(fileName2, ",", "\"");
				
		TextFileProcessor.textFileWriter("C:\\ImportFiles\\exporttxt2.txt", parsedFile);
		delimitedFileProcessor.delimitedFileWriter("C:\\ImportFiles\\exportcsv2.csv", parsedFile,",","\"");
	}
	
	public static void delimitedFileFormat2 () throws Exception {
		String fileName3 = "C:\\ImportFiles\\csvtest.csv";
		ArrayList<ArrayList<String>> parsedFile = new ArrayList<ArrayList<String>> ();
		
		parsedFile = delimitedFileProcessor.delimitedFileReader(fileName3, ",", "\"");

		TextFileProcessor.textFileWriter("C:\\ImportFiles\\exporttxt3.txt", parsedFile);
		delimitedFileProcessor.delimitedFileWriter("C:\\ImportFiles\\exportcsv3.csv", parsedFile,",","\"");
	}
}
