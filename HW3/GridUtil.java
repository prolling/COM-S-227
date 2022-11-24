package hw3;

import static api.Orientation.*;
import static api.CellType.*;

import java.util.ArrayList;

import api.Cell;

/**
 * Utilities for parsing string descriptions of a grid.
 * @author paige
 */
public class GridUtil {
	/**
	 * Constructs a 2D grid of Cell objects given a 2D array of cell descriptions.
	 * String descriptions are a single character and have the following meaning.
	 * <ul>
	 * <li>"*" represents a wall.</li>
	 * <li>"e" represents an exit.</li>
	 * <li>"." represents a floor.</li>
	 * <li>"[", "]", "^", "v", or "#" represent a part of a block. A block is not a
	 * type of cell, it is something placed on a cell floor. For these descriptions
	 * a cell is created with CellType of FLOOR. This method does not create any
	 * blocks or set blocks on cells.</li>
	 * </ul>
	 * The method only creates cells and not blocks. See the other utility method
	 * findBlocks which is used to create the blocks.
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a 2D array of cells the represent the grid without any blocks present
	 */
	public static Cell[][] createGrid(String[][] desc) {
		//new 2D array to hold the types of all of the cells
		Cell[][] disCell = new Cell[desc.length][desc[0].length]; 
		
		//iterate through the rows of the array
		for (int i = 0; i < desc.length; ++i) {
			//iterate through the columns of the array
			for (int j = 0; j < desc[0].length; ++j){
				if (desc[i][j].equals("*"))
					disCell[i][j] = new Cell(WALL, i, j);
				else if (desc[i][j].equals("e"))
					disCell[i][j] = new Cell(EXIT, i, j);
				else if (desc[i][j].equals("."))
					disCell[i][j] = new Cell(FLOOR, i, j); 
				else if (desc[i][j].equals("^") || desc[i][j].equals("v") || desc[i][j].equals("#") || desc[i][j].equals("[") || desc[i][j].equals("]"))
					disCell[i][j] = new Cell(FLOOR, i, j);
				
				else 
					disCell[i][j] = new Cell(FLOOR, i, j);
			}
			 
		}
		return disCell;
	}

	/**
	 * Returns a list of blocks that are constructed from a given 2D array of cell
	 * descriptions. String descriptions are a single character and have the
	 * following meanings.
	 * <ul>
	 * <li>"[" the start (left most column) of a horizontal block</li>
	 * <li>"]" the end (right most column) of a horizontal block</li>
	 * <li>"^" the start (top most row) of a vertical block</li>
	 * <li>"v" the end (bottom most column) of a vertical block</li>
	 * <li>"#" inner segments of a block, these are always placed between the start
	 * and end of the block</li>
	 * <li>"*", ".", and "e" symbols that describe cell types, meaning there is not
	 * block currently over the cell</li>
	 * </ul>
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a list of blocks found in the given grid description
	 */
	public static ArrayList<Block> findBlocks(String[][] desc) {
		
		//new ArrayList of the block 
		ArrayList<Block> blocks = new ArrayList<Block>(); 
		
		//iterate through the rows of desc
		for (int i = 0; i < desc.length; ++i) {
			//iterate through the columns of desc
			for (int j = 0; j < desc[0].length; ++j) {
				if (desc[i][j].equals("[")) {
					int firstRow = i; 
					int currentCol = j; 
					int firstCol = currentCol;
					int length = 2;
					while (desc[i][++currentCol].equals("#")) {
						length ++; 
						//currentCol++;
						
					}
				
				blocks.add(new Block(firstRow, firstCol, length, HORIZONTAL));
			} if (desc[i][j].equals("^")) {
				int currentRow = i;
				int firstRow = currentRow; 
				int firstCol = j;
				int length = 2;
				while (desc[++currentRow][j].equals("#")) {
					length ++;
					//currentRow++;
					 
				}
				blocks.add(new Block(firstRow, firstCol, length, VERTICAL));
			}
		}
	}
		return blocks;
	}
}
	

