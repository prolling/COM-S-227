package hw3;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import api.Direction;
import api.Move;

import hw3.Board;


/**
 * A puzzle solver for the the Block Slider game.
 * <p>
 * THE ONLY METHOD YOU ARE CHANGING IN THIS CLASS IS solve().
 * @author paige
 */
public class Solver {
	/**
	 * Maximum number of moves allowed in the search.
	 */
	private int maxMoves;

	/**
	 * Associates a string representation of a grid with the move count required to
	 * reach that grid layout.
	 */
	private Map<String, Integer> seen = new HashMap<String, Integer>();

	/**
	 * All solutions found in this search.
	 */
	private ArrayList<ArrayList<Move>> solutions = new ArrayList<ArrayList<Move>>();

	/**
	 * Constructs a solver with the given maximum number of moves.
	 * 
	 * @param givenMaxMoves maximum number of moves
	 */
	public Solver(int givenMaxMoves) {
		maxMoves = givenMaxMoves;
		solutions = new ArrayList<ArrayList<Move>>();
	}

	/**
	 * Returns all solutions found in the search. Each solution is a list of moves.
	 * 
	 * @return list of all solutions
	 */
	public ArrayList<ArrayList<Move>> getSolutions() {
		return solutions;
	}

	/**
	 * Prints all solutions found in the search.
	 */
	public void printSolutions() {
		for (ArrayList<Move> moves : solutions) {
			System.out.println("Solution:");
			for (Move move : moves) {
				System.out.println(move);
			}
			System.out.println();
		}
	}

	/**
	 * EXTRA CREDIT 15 POINTS
	 * <p>
	 * Recursively search for solutions to the given board instance according to the
	 * algorithm described in the assignment pdf. This method does not return
	 * anything its purpose is to update the instance variable solutions with every
	 * solution found.
	 * 
	 * @param board any instance of Board
	 */
	public void solve(Board board) {
	
		//if the number of moves is over the max
			if (board.getMoveCount() > maxMoves)
				return; 
			//else if the game is over
			else if (board.isGameOver()) {
			
				//record the move history as a solution
				solutions.add((ArrayList<Move>)board.getMoveHistory().clone());
				
				return;
			}
			//else if the grid layout is one we've seen before
			 else if (seen.containsKey(board.toString())) {
				 
				 //if number of moves is greater than or equal to how we got there before
				 if (board.getMoveCount() >= seen.get(board.toString())) 
					 return;
	
				 else
					 //update the number of moves for that grid
					 maxMoves = board.getMoveCount();
			 } else 
				 //record the grid layout and number of moves in the list of those we've seen
				 seen.put(board.toString(), board.getMoveCount());
			//if we get here, we keep searching
			//get a list of all possible moves 
			ArrayList<Move> possMoves = board.getAllPossibleMoves();
			
			//for each possible move
				for (Move m : possMoves) {
					//grab the block to move
					Block b = m.getBlock();
					int row = b.getFirstRow();
					int col = b.getFirstCol();
					Direction dir = m.getDirection();
					board.grabBlockAtCell(row, col);
					//move the block in one direction
					board.moveGrabbedBlock(dir);
						solve(board);
						//undo the move
						board.undoMove();
					
				}
	}
}
