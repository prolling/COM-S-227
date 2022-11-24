package hw3;

import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import api.Cell;
import api.CellType;
import api.Direction;
import api.Move;
import api.Orientation;

/**
 * Represents a board in the Block Slider game. A board contains a 2D grid of
 * cells and a list of blocks that slide over the cells.
 * @author paige 
 */
public class Board {
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of blocks that are positioned on the board.
	 */
	private ArrayList<Block> blocks;


	/**
	 * A list of moves that have been made in order to get to the current position
	 * of blocks on the board.
	 */
	private ArrayList<Move> moves = new ArrayList<Move>();
	
	/**
	 * the current Block that is grabbed 
	 */
	private Block currentBlock; 
	
	/**
	 * the current Cell that is grabbed 
	 */
	private Cell currentCell; 
	
	/**
	 * the number of moves that have been made
	 */
	private int moveCount;
	
	/**
	 * the game is over if a block has made it to the exit 
	 */
	private boolean isOver;
	
	/**
	 * true if a block is grabbed, false otherwise 
	 */
	private boolean isBlockGrabbed; 
	
	

	/**
	 * Constructs a new board from a given 2D array of cells and list of blocks. The
	 * cells of the grid should be updated to indicate which cells have blocks
	 * placed over them (i.e., setBlock() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param blocks list of blocks already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Block> blocks) {
		
		//Initialize the grid
		this.grid = grid;
		//Initialize the blocks
		this.blocks = blocks;
		
		reset(); 
	}

	/**
	 * Constructs a new board from a given 2D array of String descriptions.
	 * <p>
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBlocks(desc));
	}

	/**
	 * Models the user grabbing a block over the given row and column. The purpose
	 * of grabbing a block is for the user to be able to drag the block to a new
	 * position, which is performed by calling moveGrabbedBlock(). This method
	 * records two things: the block that has been grabbed and the cell at which it
	 * was grabbed.
	 * 
	 * @param row row to grab the block from
	 * @param col column to grab the block from
	 */
	public void grabBlockAtCell(int row, int col) {
		
		currentBlock = grid[row][col].getBlock();
		currentCell = grid[row][col]; 
		
		isBlockGrabbed = true; 
	}

	/**
	 * Set the currently grabbed block to null.
	 */
	public void releaseBlock() {
		
		currentBlock = null;
		isBlockGrabbed = false; 
	}

	/**
	 * Returns the currently grabbed block.
	 * 
	 * @return the current block
	 */
	public Block getGrabbedBlock() {
		
		return currentBlock;
	}

	/**
	 * Returns the currently grabbed cell.
	 * 
	 * @return the current cell
	 */
	public Cell getGrabbedCell() {
		
		return currentCell;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a block
	 * to be placed over it. Blocks can only be placed over floors and exits. A
	 * block cannot be placed over a cell that is occupied by another block.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a block, otherwise false
	 */
	public boolean canPlaceBlock(int row, int col) {
		
		boolean canPlace;
		//if the cell has a block or is a wall
		if (grid[row][col].hasBlock() || grid[row][col].isWall()) {
			//cannot place block
			canPlace = false;
			//else can place block
		} else {
			canPlace = true;
		}
		return canPlace; 
	}

	/**
	 * Returns the number of moves made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		
		return moveCount;
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCell(int row, int col) {
		
		return grid[row][col];
	}

	/**
	 * Returns a list of all blocks on the board.
	 * 
	 * @return a list of all blocks
	 */
	public ArrayList<Block> getBlocks() {
		
		return blocks;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a block
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		
		//loop through the cells
		boolean isOver = false; 
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				//if the block is on the exit
				if (grid[i][j].isExit() && grid[i][j].hasBlock()) {
					//return true
					isOver = true; 
				} 
			} 
		}
		return isOver; 
	}

	
	
	/**
	 * Moves the currently grabbed block by one cell in the given direction. A
	 * horizontal block is only allowed to move right and left and a vertical block
	 * is only allowed to move up and down. A block can only move over a cell that
	 * is a floor or exit and is not already occupied by another block. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No block is currently grabbed by the user.</li>
	 * <li>A block is currently grabbed by the user, but the block is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does the following:
	 * <ul>
	 * <li>Moves the block object by calling its move method.</li>
	 * <li>Sets the block for the grid cell that the block is being moved into.</li>
	 * <li>For the grid cell that the block is being moved out of, sets the block to
	 * null.</li>
	 * <li>Moves the currently grabbed cell by one cell in the same moved direction.
	 * The purpose of this is to make the currently grabbed cell move with the block
	 * as it is being dragged by the user.</li>
	 * <li>Adds the move to the end of the moveHistory list.</li>
	 * <li>Increment the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBlock(Direction dir) {
		
		Direction direct = dir; 
		int rowCC = currentCell.getRow();
		int colCC = currentCell.getCol(); 
		
		//if game is over, no block is grabbed, or the block is not allowed to move
		if (isOver || !isBlockGrabbed) 
			return; 
		else {
			//do for horizontal block
			if ((direct == RIGHT || direct == LEFT) && currentBlock.getOrientation() == HORIZONTAL){
				if((direct == RIGHT) && (canPlaceBlock(rowCC, colCC + currentBlock.getLength()))) { //move the block right
					currentBlock.move(direct);
					setBlockOrNull(); 
					currentCell = grid[rowCC][colCC +1]; 
				} else if(direct == LEFT && canPlaceBlock(rowCC, colCC - 1)) { //move the block left
					currentBlock.move(direct);
					setBlockOrNull();
					currentCell = grid[rowCC][colCC - 1];
				} else {
					return;
				}
			//do for vertical block
			} else if ((direct == UP || direct == DOWN) && currentBlock.getOrientation() == VERTICAL){
				if(direct == UP && canPlaceBlock(rowCC - 1, colCC)) { //move the block up
					currentBlock.move(direct);
					setBlockOrNull();
					currentCell = grid[rowCC - 1][colCC]; 
				} else if(direct == DOWN && canPlaceBlock(rowCC + currentBlock.getLength(), colCC)) { //move the block down
					currentBlock.move(direct);
					setBlockOrNull();
					currentCell = grid[rowCC + 1][colCC];
				} else {
					return; 
				}
			} else {
				return; 
			}
		}
		
		//add move to the end of moveHistory list
		moves.add(new Move(currentBlock, direct)); 
		//increment the total moves made
		moveCount ++; 
	}
			
		
	/**
	 * sets all of the cells of the grid null and then block if they have a block	
	 */
	private void setBlockOrNull() {
		//set all cells as a null block
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				grid[i][j].setBlock(null);
			}
		}
		
		//iterate through the blocks set block on the cells that have a block
		for (Block block: blocks) {
			int firstRow = block.getFirstRow();
			int firstCol = block.getFirstCol();
			int blockLen = block.getLength();
			Orientation blockOr = block.getOrientation();
			//set block at the first row and column of the block
			grid[firstRow][firstCol].setBlock(block); 
			//iterate through the rest of the length of the block
			for (int k = 0; k < blockLen; ++k) {
				//if it is a horizontal block
				if (blockOr == HORIZONTAL) {
					//keep setting the blocks across the columns
					grid[firstRow][firstCol + k].setBlock(block);
				} else {
					//else it is a vertical block
					grid[firstRow + k][firstCol].setBlock(block); 
					//keep setting the blocks across the rows 
				}
			}
		}
		
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each block object. It also updates each grid cells by calling
	 * their setBlock method to either set a block if one is located over the cell
	 * or set null if no block is located over the cell.
	 */
	public void reset() {
		
		//initialize that there is not a block grabbed
		isBlockGrabbed = false; 
		//resets the moveCount to 0
		moveCount = 0; 
		//resets the moveHistory to empty
		moves.clear();
		//resets isGameOver to false
		isOver = false; 
		//calls the reset method for each block object
		for (Block e : blocks) {
			e.reset(); 
		}
		setBlockOrNull();	
	}
		
	

	/**
	 * Returns a list of all legal moves that can be made by any block on the
	 * current board. If the game is over there are no legal moves.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		//new array list of the possible moves
		ArrayList<Move> possMoves = new ArrayList<Move>();
		//if the game is over
		if (isGameOver()) {
			//cannot make the arrayList of moves
			return possMoves;
		} else {
			//iterate through all of the cells of the grid
			for (int i = 0; i < grid.length; ++i) {
				for (int k = 0; k < grid[0].length; ++k) {
					//if the grid has a block find how the block can move
					if (grid[i][k].hasBlock()) {
						//if the block is vertical 
						if (grid[i][k].getBlock().getOrientation() == VERTICAL) {
							//if the cell above it is a floor or an exit
							if (i - 1 >= 0 && (grid[i - 1][k].isFloor() || grid[i - 1][k].isExit()) && !(grid[i - 1][k].hasBlock()|| grid[i-1][k].getBlock() == grid[i][k].getBlock())) {
									Move move = new Move(grid[i][k].getBlock(), UP);
									possMoves.add(move);
							} else if (i + 1 < grid.length && (grid[i + 1][k].isExit() || grid[i +1][k].isFloor())) {
								if (!(grid[i + 1][k].hasBlock() || grid[i - 1][k].getBlock() == grid[i][k].getBlock())){
									//add the move to ArrayList
									Move move = new Move(grid[i][k].getBlock(), DOWN);
									possMoves.add(move);
								}
							}
						//if the block is horizontal 
						} else if (grid[i][k].getBlock().getOrientation() == HORIZONTAL) { 
							//if the cell to the right is a floor or exit
							if (k + 1 < grid[i].length && (grid[i][k + 1].isFloor() || grid[i][k + 1].isExit()) && !(grid[i][k + 1].hasBlock() || grid[i - 1][k].getBlock() == grid[i][k].getBlock())) {
									Move move = new Move(grid[i][k].getBlock(), RIGHT);
									possMoves.add(move);  
							} else if (k - 1 >= 0 && (grid[i][k-1].isExit() || grid[i][k - 1].isFloor())) {
								if (!(grid[i][k - 1].hasBlock() || grid[i-1][k].getBlock()==grid[i][k].getBlock())) {
									Move move = new Move(grid[i][k].getBlock(), LEFT);
									possMoves.add(move);
								}
							}
						}
						
					} else {
						continue; 
					}
				} 
			}
		}
		return possMoves;
	}
		
	

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moves;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>grabs the moved block and calls moveGrabbedBlock passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBlock twice</li>
	 * <li>if required, sets is game over to false</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		
		if (moves.size() >= 0) {
			//set game over to false
			isOver = false;
			//index of the move that is being undone
			int indexUndo = moves.size() - 1;
			//the move that is being undone 
			Move moveToUndo = moves.get(indexUndo);
			//the block that is being moved
			Block undoBlock = moveToUndo.getBlock();
			//remove the last move from the move history
			moves.remove(indexUndo); 
			//increment the number of moves down by two
			moveCount =- 2; 
			//grab block for the move
			grabBlockAtCell(undoBlock.getFirstRow(), undoBlock.getFirstCol());
			
			//if move direction is up
			if (moveToUndo.getDirection() == UP) {
				//move down
				moveGrabbedBlock(DOWN);
				moves.remove(moves.size() - 1);
			//else if move direction is down
			}else if (moveToUndo.getDirection() == DOWN) {
				//move up
				moveGrabbedBlock(UP);
				moves.remove(moves.size() - 1);
			//else if move direction is right 
			}else if (moveToUndo.getDirection() == RIGHT) {
				//move left
				moveGrabbedBlock(LEFT);
				moves.remove(moves.size() - 1);
			//else direction must be left
			}else { 
				//move right
				moveGrabbedBlock(RIGHT); 
				moves.remove(moves.size() - 1);
			}
		}
		
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
