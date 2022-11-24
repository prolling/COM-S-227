
package mini2;

import java.util.Arrays;

/**
 * Utilities for working with arrays.
 */
public class ArrayAdventures {
// disable instantiation
private ArrayAdventures() { }

	public static void remove(int[] arr, int pos){
		if (pos > arr.length)
			;
		else if (arr.length == 0)
			;
		else {
			for (int i = pos; i < arr.length - 1; i++) {
				arr[i] = arr[i + 1];
			}
			
			if (arr[arr.length - 1] != 0) {
				arr[arr.length - 1] = 0; 
			}
		}
	}

	public static void removeAndShiftUp(int[] arr, int pos){
		if (pos > arr.length)
			;
		else if (arr.length == 0)
			;
		else {
			int[] temp = new int[arr.length];
			temp[0] = 0; 
			
			for(int i = 1, j = 0; i < arr.length; ++i, ++j) {
				if (j < pos) {
					temp[i] = arr[j];
				} else if (j >= pos) {
					temp[i] = arr[j+1];
				}
			}
			for (int i = 0; i < arr.length; ++i) {
				arr[i] = temp[i]; 
			}
		}
		
	}

	public static void removeOddElements(int[] arr) {
		int[] temp = new int [arr.length]; 
		
		for (int i = 0, j = 0; i < arr.length; ++i) {
			if (arr[i] % 2 == 0) {
				temp[j++] = arr[i];
			}
		}
		
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = temp[i]; 
		}
	}

	public static boolean[] findRuns(int[] arr) {
		boolean[] isRun = new boolean[arr.length]; 
		int runLength = 0;
		
		for (int i = 0; i < arr.length; i = i + runLength) {
			isRun[i] = false;
			runLength = 0;
			for (int j = i; j < arr.length; ++j) {
				if(arr[i] == arr[j]) {
					runLength ++;
				} else if (arr[i] != arr[j]) {
					break; 
				}
			}
			if (runLength >= 3) {
				for(int k = i; k < i + runLength; ++k) {
					isRun[k] = true; 
				}
			}
		}
	return isRun; 
	}
	
public static void removeRuns(int[] arr) {
	int runLength = 0;
	int[] temp = new int[arr.length]; 
	int p = 0;
	//finds the length of the run
	for (int i = 0; i < arr.length; i += 0) {
		runLength = 0;
		for (int j = i; j < arr.length; ++j) {
			if(arr[i] == arr[j]) {
				runLength ++;
			} else if (arr[i] != arr[j]) {
				break; 
			}
			 
		}
		if (runLength < 3)
			temp[p] = arr[i];
		if (runLength >= 3) {
			i += runLength;  
		} else {
			i++; 
			p++;
		}
		
	}
	
	//copies the temp array to arr
	for (int k = 0; k < arr.length; ++k) {
		arr[k] = temp[k]; 
	}
  //System.out.println(Arrays.toString(arr)); 
}
	

public static String toString1D(int[] arr) {
	String result = ""; 
	for (int i = 0; i < arr.length; ++i) {
		result += String.format("%7d", arr[i]);
		result += ",";
	}
	if (result.length() > 0)
		result = result.substring(0, result.length() - 1);
	
	//result = result[result.length() - 1]; 
return "{" + result + "}";
}

public static String toString2D(int[][] arr) {
	//to get full points => fix so that there is a space before the { on the rows 
	//that don't have two {{
	String result = ""; 
	for (int i=0; i<arr.length; ++i) {
		int[] row = arr[i].clone();
		Arrays.toString(row);
		String level = ""; 
		for (int j = 0; j< row.length; ++j) {
			level += String.format("%7d", row[j]);
			level += ",";
		}
		if (level.length() > 0)
			level = level.substring(0, level.length() - 1);
		
		level = "{" + level + "}" + ",";
		
		result += level;
		result += "\n";
		
	}
	
	
	result = result.substring(0,result.length() - 2);
	return "{" + result + "}";
}

public static void swapRows(int[][] arr, int row1, int row2) {
	 
	int[] temp = arr[row1].clone(); 
	int[] temp2 = arr[row2].clone(); 
	
	arr[row2] = temp;
	arr[row1] = temp2; 

}
     

public static boolean isIncreasing(int[] values, int[] indexes) {
	if (indexes.length <= 1) {
		return true; 
	}
	
	int[] temp = new int[indexes.length]; 
	for (int i = 0; i < indexes.length; ++i) {
		temp[i] = values[indexes[i]]; 
	}
	//return false;
	boolean isIncreasing = false; 
	for (int j = 0; j < temp.length - 1; ++j) {
		if (temp[j] < temp[j + 1]) {
			isIncreasing = true; 
		} else {
			return false; 
		}
	}
	
	if (isIncreasing) {
		return true;
	}
	return false; 
}
}