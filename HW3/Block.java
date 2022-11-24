package hw3;

import static api.Direction.*;

import api.Direction;
import api.Orientation;

/**
 * Represents a block in the Block Slider game.
 * @author paige
 */
public class Block {
	/**
	 * instance variable for the current row of the Block
	 */
	private int row;
	/**
	 * instance variable for the current column of the Block 
	 */
	private int col; 
	/**
	 * instance variable for the length of the block
	 */
	private int len; 
	/**
	 * instance variable for the orientation of the Block
	 */
	private Orientation or; 
	/**
	 * instance variable for the original row of the Block
	 */
	private int row1; 
	/**
	 * instance variable for the original column of the Block 
	 */
	private int col1; 
	/**
	 * Constructs a new Block with a specific location relative to the board. The
	 * upper/left most corner of the block is given as firstRow and firstCol. All
	 * blocks are only one cell wide. The length of the block is specified in cells.
	 * The block can either be horizontal or vertical on the board as specified by
	 * orientation.
	 * 
	 * @param firstRow    the first row that contains the block
	 * @param firstCol    the first column that contains the block
	 * @param length      block length in cells
	 * @param orientation either HORIZONTAL or VERTICAL
	 */
	public Block(int firstRow, int firstCol, int length, Orientation orientation) {
		
		//set the original row, length, orientation, and column to the given 
		row1 = firstRow; 
		col1 = firstCol;
	    len = length; 
	    or = orientation; 
		
		//set the current row and column to the original
		row = firstRow;
		col = firstCol; 
		  
	}

	/**
	 * Resets the position of the block to the original firstRow and firstCol values
	 * that were passed to the constructor during initialization of the the block.
	 */
	public void reset() {
		
		//set the block back to original
		row = row1;  
		col = col1;  	
	}

	/**
	 * Move the blocks position by one cell in the direction specified. The blocks
	 * first column and row should be updated. The method will only move VERTICAL
	 * blocks UP or DOWN and HORIZONTAL blocks RIGHT or LEFT. Invalid movements are
	 * ignored.
	 * 
	 * @param dir direction to move (UP, DOWN, RIGHT, or LEFT)
	 */
	public void move(Direction dir) {
		
		if (dir == Direction.DOWN && or == Orientation.VERTICAL) {
			row ++;
			
		} else if (dir == Direction.UP && or == Orientation.VERTICAL) {
			row --;
			
		} else if (dir == Direction.LEFT && or == Orientation.HORIZONTAL) {
			col --;
			
		} else if (dir == Direction.RIGHT && or == Orientation.HORIZONTAL) {
			col ++;
				
		}
		
	}

	/**
	 * Gets the first row of the block on the board.
	 * 
	 * @return first row
	 */
	public int getFirstRow() {
		
		return row;
	}

	/**
	 * Sets the first row of the block on the board.
	 * 
	 * @param firstRow first row
	 */
	public void setFirstRow(int firstRow) {
		
		row = firstRow; 
	}

	/**
	 * Gets the first column of the block on the board.
	 * 
	 * @return first column
	 */
	public int getFirstCol() {
		
		return col;
	}

	/**
	 * Sets the first column of the block on the board.
	 * 
	 * @param firstCol first column
	 */
	public void setFirstCol(int firstCol) {
		
		col = firstCol;
	}

	/**
	 * Gets the length of the block.
	 * 
	 * @return length measured in cells
	 */
	public int getLength() {
	
		return len;
	}

	/**
	 * Gets the orientation of the block.
	 * 
	 * @return either VERTICAL or HORIZONTAL
	 */
	public Orientation getOrientation() {
		
		return or;
	}

	@Override
	public String toString() {
		return "(row=" + getFirstRow() + ", col=" + getFirstCol() + ", len=" + getLength()
				+ ", ori=" + getOrientation() + ")";
	}
}
