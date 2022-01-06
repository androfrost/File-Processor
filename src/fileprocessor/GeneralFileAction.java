package fileprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GeneralFileAction {
	public static void createDirectory(String tcDirectory) {
		try {

		    Path path = Paths.get(tcDirectory);

		    if (Files.notExists(path)) {
		    	Files.createDirectories(path);
		    	System.out.println("Directory has been created!");
		    }

		  } catch (IOException e) {

		    System.err.println("Failed to create directory!" + e.getMessage());

		  }
	}
	
	public static void createFile(String tcFile, String tcFirstLine) throws IOException {
		try {

		    File file = new File(tcFile);

		    if (file.exists()) {
		    	System.out.println("File exists!");
		    } else {
		    	FileWriter writer = new FileWriter(file);
				
				if (!tcFirstLine.contentEquals("")) {
					writer.append(tcFirstLine);
				}
				
				writer.close();
				
				System.out.println("File has been created!");
		    }

		  } catch (IOException e) {

			  System.err.println("Failed to create file!" + e.getMessage());

		  }
	}
}
