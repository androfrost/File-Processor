package fileprocessor;
import java.util.ArrayList;

public class Encryption {

	private static String lcTestString  = "setEncryptAction";
	
	/*
	 * Encrypt a given ArrayList<ArrayList<String>> based on the given encryption skeleton.
	 * 		tcDataToEncrypt - contains strings that are to be encrypted
	 * 		tcEncryptSkeleton - contains how many ascii numbers to encrypt a character as it scans the ArrayList<ArrayList<String>> characters
	 */
	public static ArrayList<ArrayList<String>> asciiEncryption (ArrayList<ArrayList <String>> tcDataToEncrypt, int [] tcEncryptSkeleton) throws Exception {
		
		String currentString = "";
		char charChar = ' ';
		int asciiValue = 0;
		int encrSkelx = 0;
		
		ArrayList<ArrayList<String>> lcEncryptedData  = new ArrayList<ArrayList<String>> ();
		// Loop to add the ArrayLists inside the main ArrayList
		for (int arrayDatax = 0; arrayDatax < tcDataToEncrypt.size(); arrayDatax++) {
			lcEncryptedData.add(new ArrayList<String>());
			for (int arrayDatay = 0; arrayDatay < tcDataToEncrypt.get(arrayDatax).size(); arrayDatay++) {
				currentString = "";
				for (int lencnt = 0; lencnt < tcDataToEncrypt.get(arrayDatax).get(arrayDatay).length();lencnt++) {
					charChar 		= tcDataToEncrypt.get(arrayDatax).get(arrayDatay).charAt(lencnt);
					asciiValue 		= (int) charChar;
					
					asciiValue 		= asciiValue + tcEncryptSkeleton[encrSkelx];
										
					charChar 		= (char) asciiValue;
					currentString 	= currentString + String.valueOf(charChar);
					encrSkelx++;
					if (encrSkelx == tcEncryptSkeleton.length) {
						encrSkelx = 0;
					}
				}
				lcEncryptedData.get(arrayDatax).add(arrayDatay,currentString);
			}
		}		
		
		return lcEncryptedData;
	}
	
	public static ArrayList<ArrayList<String>> asciiEncryption (ArrayList<ArrayList <String>> tcDataToEncrypt, int [][] tcEncryptSkeleton) throws Exception {
		
		String currentString = "";
		char charChar = ' ';
		int asciiValue = 0;
		int encrSkelx = 0, encrSkely = 0;
		
		ArrayList<ArrayList<String>> lcEncryptedData  = new ArrayList<ArrayList<String>> ();
		// Loop to add the ArrayLists inside the main ArrayList
		for (int arrayDatax = 0; arrayDatax < tcDataToEncrypt.size(); arrayDatax++) {
			lcEncryptedData.add(new ArrayList<String>());
			int skeletonColumnLength = tcEncryptSkeleton[0].length;
			for (int arrayDatay = 0; arrayDatay < tcDataToEncrypt.get(arrayDatax).size(); arrayDatay++) {
				for (int encrypty = 0; encrypty < skeletonColumnLength; encrypty++) {
					currentString 			= "";
					int skeletonRowLength 	= tcDataToEncrypt.get(arrayDatax).get(arrayDatay).length();
					for (int lencnt = 0; lencnt < skeletonRowLength; lencnt++) {
						if (tcEncryptSkeleton[encrSkelx][encrypty] == 0) {
							encrSkelx = 0;
						}
						charChar 		= tcDataToEncrypt.get(arrayDatax).get(arrayDatay).charAt(lencnt);
						asciiValue 		= (int) charChar;
						
						asciiValue 		= asciiValue + tcEncryptSkeleton[encrSkelx][encrypty];
											
						charChar 		= (char) asciiValue;
						currentString 	= currentString + String.valueOf(charChar);
						encrSkelx++;
						// Reset encryption Skeleton location if we reach the end of an ArrayList
						if (encrSkelx == tcEncryptSkeleton.length) {
							encrSkelx = 0;
						}

					}
					// After running each line of the Encryption array on the data ArrayList, add the encrypted entry to the encryption ArrayList
					if (encrypty == skeletonColumnLength-1)
						lcEncryptedData.get(arrayDatax).add(arrayDatay,currentString);
				}
			}
		}		
		
		return lcEncryptedData;
	}
	
	public static ArrayList<ArrayList<String>> asciiEncryptedFileTest(ArrayList<ArrayList <String>> tcDataToTest, int [] tcEncryptSkeleton) throws Exception{
		ArrayList<ArrayList<String>> lcEncryptedData	= new ArrayList<ArrayList<String>> ();
		String lcTest								= "";
		int [] lcEncryptSkeleton 					= tcEncryptSkeleton;
		
		lcTest = tcDataToTest.get(0).get(0);

		if (lcTestString.contains(lcTest)) {
			tcDataToTest.remove(0);
			lcEncryptedData = tcDataToTest;
		}
		else {
			lcEncryptedData = asciiDecryption(tcDataToTest, lcEncryptSkeleton);
		}	
			
		return lcEncryptedData;
	}
	
	public static ArrayList<ArrayList<String>> asciiEncryptedFileTest(ArrayList<ArrayList <String>> tcDataToTest, int [][] tcEncryptSkeleton) throws Exception{
		ArrayList<ArrayList<String>> lcEncryptedData	= new ArrayList<ArrayList<String>> ();
		String lcTest									= "";
		int [][] lcEncryptSkeleton 						= tcEncryptSkeleton;
		
		lcTest = tcDataToTest.get(0).get(0);

		if (lcTestString.contains(lcTest)) {
			tcDataToTest.remove(0);
			lcEncryptedData = tcDataToTest;
		}
		else {
			lcEncryptedData = asciiDecryption(tcDataToTest, lcEncryptSkeleton);
		}	
			
		return lcEncryptedData;
	}
	
	public static ArrayList<ArrayList<String>> asciiDecryption (ArrayList<ArrayList <String>> tcDataToEncrypt, int [] tcEncryptSkeleton) throws Exception {
		
		ArrayList<ArrayList<String>> lcEncryptedData  	= new ArrayList<ArrayList<String>> ();
		int [] lcEncryptSkeleton 						= tcEncryptSkeleton;
		
		for (int skeletonx = 0; skeletonx < lcEncryptSkeleton.length; skeletonx++) {
			tcEncryptSkeleton[skeletonx] = lcEncryptSkeleton[skeletonx] * -1;
		}
		lcEncryptedData = asciiEncryption(tcDataToEncrypt, lcEncryptSkeleton);
		
		return lcEncryptedData;
	}
	
	public static ArrayList<ArrayList<String>> asciiDecryption (ArrayList<ArrayList <String>> tcDataToEncrypt, int [][] tcEncryptSkeleton) throws Exception {
		
		ArrayList<ArrayList<String>> lcEncryptedData  = new ArrayList<ArrayList<String>> ();
		
		int [][] lcEncryptSkeleton = tcEncryptSkeleton;
		for (int skeletonx = 0; skeletonx < lcEncryptSkeleton.length; skeletonx++) {
			for (int skeletony = 0; skeletony < lcEncryptSkeleton[0].length; skeletony++) {
				tcEncryptSkeleton[skeletonx][skeletony] = lcEncryptSkeleton[skeletonx][skeletony] * -1;	
			}
		}
		lcEncryptedData = asciiEncryption(tcDataToEncrypt, lcEncryptSkeleton);
		
		return lcEncryptedData;
	}
	
	
}
