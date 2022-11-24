package mini1;
import java.util.Scanner;
/**
 * Utility class with a bunch of static methods for loop practice.
 * @author Paige
 */
public class LoopsInfinityAndBeyond {
	// disable instantiation
	private LoopsInfinityAndBeyond() { }
	
	public static String doubleChars(String text)
	{
		String newString = "";
		int numCharsSame = 0;
		for(int i = 0; i < text.length(); i = i + numCharsSame) {
			numCharsSame = 0;
			for(int j = i; j < text.length(); ++j) {
				if(text.charAt(j) == text.charAt(i)) {
					numCharsSame ++; 
				} else {
					break; 
				}
			}
			if(numCharsSame > 1) {
				for(int k = 0; k < numCharsSame; ++k) {
					newString += text.charAt(i);
				} 
			} else {
				newString = newString + text.charAt(i) + text.charAt(i); 
			}
		}
		return newString;
	}
			
	
	
	public static int maxYear(String data){
		
		Scanner scnr = new Scanner(data); 
		
		int maxYear = -1;
		double maxNum = 0;
		while(scnr.hasNext()) {
			String year = scnr.next(); 
			int currentYear = Integer.parseInt(year);
			String num = scnr.next(); 
			double currentNum = Double.parseDouble(num);
			if (currentNum > maxNum) {
				maxNum = currentNum; 
				maxYear = currentYear; 
			}
		}
		scnr.close();
	return maxYear;
	}
	
	public static int collatzCounter(int n){
		
		int numInts = 0;
		
		if (n < 1) {
			numInts = -1;
		} else {
			while (n> 1) {
				if (n % 2 == 0) {
					n /= 2;
				} else {
					n = (n * 3) + 1; 
				}
				numInts ++;
			}
			 
		}
		return numInts;
	}
	
	public static String doubleWords(String text){
		Scanner scnr = new Scanner(text); 
		String newString = ""; 
		
		if (text == "") {
			return newString; 
		} else {
		while(scnr.hasNext()) {
			String word = scnr.next();
			if (Character.isLetter(word.charAt(0))) {
				newString = newString + word + " " + word + " "; 
			} else {
				newString = newString + word + " "; 
			}
			
		}
		
		if (newString.charAt(newString.length() - 1) == ' ') {
			newString = newString.substring(0, newString.length() - 1); 
		}
		scnr.close();
		return newString;
		}
	}
	
	public static boolean oneVowelRemoved(String s, String t) {
		s = s.toLowerCase();
		t = t.toLowerCase();
		int numVowelsS = 0; 
		int numVowelsT = 0;
		
		//counts the number of vowels in s
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
				numVowelsS ++; 
			}
		}	
		
		//counts how many vowels are in t
		for (int j = 0; j < t.length(); ++j) {
			if (t.charAt(j) == 'a' || t.charAt(j) == 'e' || t.charAt(j) == 'i' || t.charAt(j) == 'o' || t.charAt(j) == 'u') {
				numVowelsT ++; 
			}
		}
		
		//returns true if there is one less vowel in the substring 
		if (numVowelsS == numVowelsT + 1) {
			return true; 
		} else {
		
		return false;
		}
	}
	
	public static String ufo(String space) {
		String ufoPain = "";
		String newUfo = "";
		boolean noYes = false;
		int temp1 = space.indexOf("<");
		int temp2 = space.indexOf(">");
		int index1 = 0;
		int ufoLength = 0;
		
		if (temp2>temp1) {
			for (int i = temp1; i <= temp2; i++) {
				if (space.substring(i,i+1).equals("<")) {
					ufoPain += space.substring(i, i+1);
					index1 = i;
					noYes = false;
				}
				if (space.substring(i,i+1).equals(">")) {
					ufoPain+= space.substring(i,i+1);
					noYes = false;
				}
				if (space.substring(i, i+1).equals("=")) {
					ufoPain += space.substring(i,i+1);
					noYes = false;
				}
				if (!(((space.substring(i,i+1).equals("="))||(space.substring(i,i+1).equals(">")||(space.substring(i,i+1).equals("<")))))){
					ufoPain = "";
					noYes = true;
					index1 = 0; 
				}
			}
			if (noYes == true) {
				return space;
			}
			for (int i = 0; i < ufoPain.length(); i++) {
				ufoLength += 1;
			}
			for (int i = 0; i < index1; i++) {
				newUfo += space.substring(i,i+1);
			}
			newUfo += " ";
			newUfo += ufoPain;
			for (int i= temp2+2; i <space.length(); i++) {
				newUfo += space.substring(i,i+1);
			}
		}
		return newUfo; 
	}
	
	public static void printX(int n) {
		
		String dash = "-";
		int outDash; 
		int inDash; 
		int i;
		
		for (i = 0; i < n; ++i) {
			outDash = i;
			inDash = (n - (i + 1)) * 2;
			System.out.println(dash.repeat(outDash) + "\\" + dash.repeat(inDash) + "/"); 
			
		}
		
		for (i = 0; i < n; ++i) {
			outDash = (n - (i + 1));
			inDash = i*2; 
			System.out.println(dash.repeat(outDash) + "/" + dash.repeat(inDash) + "\\");
		}
		
		
		
	}
	
}
