package fileprocessor;

import java.util.ArrayList;

public class FileProcessorThread extends Thread {

	private static String lcFileName;
	private static ArrayList<ArrayList<String>> lcDataToProcess;
	private static int[][] liSkeleton;
	private static boolean llIsDelimited;
	private static boolean llWriter;
	
	FileProcessorThread(String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite, boolean tlIsDelimited, boolean tlWriter) {
		lcFileName = tcFileName;
		lcDataToProcess = tcDataToWrite;
		llIsDelimited = tlIsDelimited;
		llWriter = tlWriter;
	}
	
	FileProcessorThread(String tcFileName,  boolean tlIsDelimited, boolean tlWriter) {
		lcFileName = tcFileName;
		llIsDelimited = tlIsDelimited;
		llWriter = tlWriter;
	}
	
	FileProcessorThread(String tcFileName, int[][] tcSkeleton, boolean tlIsDelimited, boolean tlWriter) {
		lcFileName = tcFileName;
		llIsDelimited = tlIsDelimited;
		llWriter = tlWriter;
		liSkeleton = tcSkeleton;
	}
	
	public void run(){
		try {
			if (llIsDelimited) {
				if (llWriter) {
					runDelimitedFileWriterThread(lcFileName, lcDataToProcess, ",", "\"");
				} else {
					runDelimitedFileReaderThread(lcFileName, ",", "\"");
				}
			}
			else {
				if (llWriter) {
					runTextFileWriterThread(lcFileName, lcDataToProcess);
				} else {
					runTextFileReaderThread(lcFileName, liSkeleton);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runDelimitedFileWriterThread(String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite, String delimChar, String charGroup) throws Exception {
		delimitedFileProcessor.delimitedFileWriter(tcFileName, tcDataToWrite, delimChar, charGroup);
	}
	
	public static void runTextFileWriterThread(String tcFileName, ArrayList<ArrayList<String>> tcDataToWrite) throws Exception {
		TextFileProcessor.textFileWriter(tcFileName, tcDataToWrite);
	}
	
	public static void runDelimitedFileReaderThread(String tcFileName, String delimChar, String charGroup) throws Exception {
		lcDataToProcess = delimitedFileProcessor.delimitedFileReader(tcFileName, delimChar, charGroup);
	}
	
	public static void runTextFileReaderThread(String tcFileName, int[][] tiSkeleton) throws Exception {
		lcDataToProcess = TextFileProcessor.textFileReader(tcFileName, tiSkeleton);
	}
	
	public ArrayList<ArrayList<String>> getDataToProcess() {
		return lcDataToProcess;
	}
}
