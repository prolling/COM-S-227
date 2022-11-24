package hw1;
/**
 * Models pages being printed from a simple printer
 * @author paige
 *
 */
public class Printer {
	/**
	 * number of sheets available 
	 */
	private int numSheetsAvailable; 
	/**
	 * total amount of pages that have been printed
	 */
	private int totalPages;
	/**
	 * total pages in document 
	 */
	private int pagesInDoc;
	/**
	 * maximum capacity of sheets that the tray can hold
	 */
	private int maxCap;
	/**
	 * instance variable to hold the next page to be printed
	 */
	private int nextPage;
	/**
	 * number of sheets in the tray
	 */
	private int numSheetsTray;
	/**
	 * constructs a new printer with the given maximum tray capacity of the number of paper sheets it can hold 
	 * initially the tray is empty and the printer has not printed any pages
	 * @param trayCapacity number of sheets that the tray can hold
	 */
	public Printer(int trayCapacity) {
		
		 maxCap = trayCapacity;
		 totalPages = 0;
		 numSheetsTray = 0;
		 numSheetsAvailable = 0;
		 
		 
	}
	/**
	 * starts a new print job to make copies of a document that is a specified page length
	 * updates the next page to print as page 0
	 * @param documentPages gives the amount of pages in the document 
	 */
	public void startPrintJob(int documentPages) {
		nextPage = 0;
		pagesInDoc = documentPages; 
		 
		
	}
	/**
	 * returns the number of sheets available for printing 
	 * @return integer value of the number of sheets available 
	 */
	public int getSheetsAvailable() {
		
		return numSheetsAvailable;
	}
	/**
	 * returns the next page number of the document that will be printed 
	 * @return integer value of the page number that will be printed 
	 */
	public int getNextPage() {
		
		nextPage = nextPage % pagesInDoc;
		return nextPage; 
	}
	/**
	 * returns the count of all pages printed by the printer since its construction 
	 * @return integer value of the total amount of pages that have been printed
	 */
	public int getTotalPages() {
		
		return totalPages; 
	}
	/**
	 * simulates the printer printing a page
	 * the number pages is either one or zero depending on whether there is at least one sheet of paper available to the printer
	 * increments the total page count of the printer by the number of pages printed
	 * advances to the next page to print by the number of pages printed
	 * the number of pages available to the printer and in the tray are updated accordingly 
	 */
	public void printPage() {
		
		nextPage = nextPage + (Math.min(1, numSheetsAvailable));  
		totalPages = totalPages + (Math.min(1, numSheetsAvailable)); 
		
		numSheetsAvailable --;
		numSheetsAvailable = (Math.max(0,  numSheetsAvailable)); 
		 
		
	}
	/**
	 * removes the paper tray from the printer
	 * makes the sheets available to the printer zero
	 */
	public void removeTray() {
		numSheetsTray = numSheetsAvailable; 
		numSheetsAvailable = 0; 
	
	}
	/**
	 * replaces the tray in the printer
	 * makes the sheets available to the printer the same as the number of sheets in the tray
	 */
	public void replaceTray() {
		numSheetsAvailable = numSheetsTray;
		numSheetsTray = 0; 
	}
	/**
	 * simulates removing the tray, adding the given number of sheets, and replacing the tray in the printer
	 * @param sheets number of sheets
	 */
	public void addPaper(int sheets) {
		
		numSheetsTray = Math.max(numSheetsAvailable, numSheetsTray); 
		
		numSheetsTray = numSheetsTray + sheets; 
		numSheetsTray = Math.min(numSheetsTray, maxCap);
		
		numSheetsAvailable = numSheetsTray; 
		numSheetsTray = 0; 
	}
	/**
	 * simulates removing the tray, removing the given number of sheets, and replacing the tray in the printer
	 * @param sheets number of sheets
	 */
	public void removePaper(int sheets) {
	
		numSheetsTray = Math.max(numSheetsAvailable, numSheetsTray);
		
		numSheetsTray = numSheetsTray - sheets; 
		numSheetsTray = Math.max(numSheetsTray, 0);
		
		numSheetsAvailable = numSheetsTray;
		numSheetsTray = 0; 
	}
}
