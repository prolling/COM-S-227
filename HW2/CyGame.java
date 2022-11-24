package hw2;
/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author Paige Rolling
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 * No additional action is taken. After applying the above action
	 * the current player's turn has ended, update the current player
	 * accordingly
	 * EXCEPTION: ignore the previous statement when the EXTRA_TURN 
	 * action is in effect
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 * Awards the PASS_GO_PRIZE
	 * A player cannot both land on PASS_GO and pass over PASS_GO
	 * (i.e., the player only gets the PASS_GO_PRIZE once each time
	 * around the board)
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 * The current player is moved to the same square as the other 
	 * player. If the player passes go during the move no prize is awarded
	 * Do not apply the rules of the square that is moved to.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 * The current player pays the other player (PAYMENT_PER_UNIT *
	 * number_of_other_player's_units). 
	 * That is to say, the current player's money is decreased and the 
	 * other player's money is increased by that amount 
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 * The current player remains the current player for one more move
	 * (i.e. don't change the current player after this move.)
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 * Walk light is on to cross: The player moves forward an additional
	 * 4 squares. Do not apply the rules of the square that is moved
	 * to. If the player passes over the PASS_GO square add PASS_GO_PRIZE
	 * to the player's money
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 * Stuck in the campa-meal: No additional action is taken for the
	 * remainder of the players turn. The player will not be able 
	 * to leave this square until they roll an even number
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	
	/**
	 * instance variable for which player is currently playing
	 * 1 for player 1 and 2 for player 2
	 */
	private int currentPlayer;
	/**
	 * instance variable for what square player 1 is on
	 */
	private int player1Square;
	/**
	 * instance variable for how much money player 1 has
	 */
	private int player1Money;
	/**
	 * instance variable for the number of units that player 1 has
	 */
	private int player1Units;
	/**
	 * instance variable for what square player 2 is on
	 */
	private int player2Square;
	/**
	 * instance variable for how much money player 2 has
	 */
	private int player2Money;
	/**
	 * instance variable for the number of units that player 2 has
	 */
	private int player2Units;
	/**
	 * instance variable for the number of squares on the board 
	 */
	private int numSquaresBoard;
	/**
	 * instance variable of an integer for the square type
	 */
	private int squareType;
	
	/**
	 * Constructs a game that has the given number of squares and starts
	 * both player on square 0
	 * Both players start with the given amount of money and 1 unit
	 * each. It is initially Player 1's turn to move 
	 * @param numSquares -- number of squares on board
	 * @param startingMoney -- starting money for each player
	 */
	public CyGame(int numSquares, int startingMoney) {
		currentPlayer = 1;
		
		player1Square = 0;
		player2Square = 0;
		player1Money = startingMoney;
		player2Money = startingMoney;
		player1Units = 1;
		player2Units = 1;
		
		numSquaresBoard = numSquares; 
	}
	
	/**
	 * Method called to indicate the current player attempts to buy
	 * one unit
	 * The purchase is only allowed if the player is currently on a 
	 * square type of DO_NOTHING and has sufficient money to buy one 
	 * unit at UNIT_COST. If allowed, subtract the cost from the player's
	 * money and increment the player's number of units by one.
	 * 
	 * If the current player successfully buys a unit their turn ends
	 * (they are not allowed to take any further action or roll the dice)
	 * Update the turn to the other player
	 * 
	 * This method does nothing if the game has ended
	 */
	public void buyUnit() {
	
			if (currentPlayer == 1) {
				
				if(player1Money >= UNIT_COST && getSquareType(player1Square) == DO_NOTHING && isGameEnded() == false) {
					player1Money -= UNIT_COST;
					player1Units += 1;
					endTurn();
				} 
			} else if (currentPlayer == 2) {
				
				if ((player2Money >= UNIT_COST) && (getSquareType(player2Square) == DO_NOTHING) && (isGameEnded() == false)) {
					player2Money -= UNIT_COST;
					player2Units += 1;
					endTurn();
				} 
			}
		} 
	
	/**
	 * Method called to indicate the current player passes or
	 * completes their turn
	 * Change from current turn to Player 1 to Player 2 or vice versa
	 */
	public void endTurn() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		}
		else if (currentPlayer == 2){
			currentPlayer = 1; 
		}
	}
	
	/**
	 * Get the current player
	 * @return 1 if it is currently Player 1's turn, otherwise 2
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Get Player 1's money
	 * @return Player 1's money
	 */
	public int getPlayer1Money() {
		return player1Money;
	}
	
	/**
	 * Get Player 1's square
	 * @return the square number
	 */
	public int getPlayer1Square() {
		return player1Square;
	}
	
	/**
	 * Get Player 1's units
	 * @return Player 1's Units
	 */
	public int getPlayer1Units() {
		return player1Units;
	}
	
	/**
	 * get Player 2's money
	 * @return Player 2's money
	 */
	public int getPlayer2Money() {
		return player2Money;
	}
	
	/**
	 * Get Player 2's square
	 * @return the square number
	 */
	public int getPlayer2Square() {
		return player2Square;
	}
	
	/**
	 * Get Player 2's units
	 * @return Player 2's units
	 */
	public int getPlayer2Units() {
		return player2Units;
	}
	
	/**
	 * Get the type of square for the given square number
	 * Each square is assigned a single type based on the following
	 * rules. The rules are listed in order of highest to lowest
	 * precedence (i.e. when multiple rules match a square number, apply
	 * the one higher on the list
	 * Rules: 
	 * Square 0 is type PASS_GO
	 * The very last square (before wrapping back to 0) is CYCLONE
	 * Every 5th square (e.g. 5,10,15...) is PAY_PLAYER
	 * Every 7th and every 11th square is EXTRA_TURN
	 * Every 3rd square is STUCK
	 * Every 2nd square is JUMP_FORWARD
	 * All remaining squares are DO_NOTHING
	 * @param square -- the square number
	 * @return the square type
	 */
	public int getSquareType(int square) {
		 
			if (square == 0) {
				squareType = PASS_GO;
			}
			else if (square == numSquaresBoard - 1) {
				squareType = CYCLONE;
			}
			else if (square % 5 == 0) {
				squareType = PAY_PLAYER;
			}
			else if (square % 7 == 0 || square % 11 == 0) {
				squareType = EXTRA_TURN;
			}
			else if (square % 3 == 0) {
				squareType = STUCK;
			}
			else if (square % 2 == 0) {
				squareType = JUMP_FORWARD;
			}
			else {
				squareType = DO_NOTHING; 
			}
		return squareType; 
	}
	
	/**
	 * Returns true if game is over, false otherwise. The game is over
	 * when either player has at least MONEY_TO_WIN money or either
	 * player has negative amount of money
	 * @return -- true if the game is over, false otherwise 
	 */
	public boolean isGameEnded() {
		if (player1Money >= MONEY_TO_WIN || player2Money >= MONEY_TO_WIN) {
			return true;
		}
		else if (player1Money < 0 || player2Money < 0) {
			return true;
		}
		else {
			return false; 
			}
	}
	
	
	/**
	 * Method called to indicate the dice has been rolled
	 * Advance the current player forward by a number of squares 
	 * determined by the number rolled. 
	 * EXCEPTION: If the player is currently on a STUCK square they
	 * only move forward if the value rolled is even, otherwise their 
	 * turn is over. 
	 * If the player passes over the PASS_GO square add PASS_GO_PRIZE
	 * to the player's money. Then apply the action of the square the 
	 * player lands on
	 * 
	 * This method does nothing if the game has ended
	 * @param value -- the number rolled by the dice (1 to 6 inclusive) 
	 */
	//FIXME
	public void roll(int value) {
		if (currentPlayer == 1 && isGameEnded() == false) {
			
			getSquareType(player1Square);
			if ((squareType == STUCK) && (value % 2 != 0)) {
				endTurn();
			
			} else {
				player1Square += value;
				getSquareType(player1Square);
				if (player1Square >= numSquaresBoard) {
					player1Square = player1Square - value; 
				}
				
					if (player1Square - value < 0 || squareType == PASS_GO) {
						player1Money += PASS_GO_PRIZE;
					}
					else if (squareType == CYCLONE) {
						player1Square = player2Square;
					}
					else if (squareType == PAY_PLAYER) {
						player1Money -= (PAYMENT_PER_UNIT * player2Units);
						player2Money += (PAYMENT_PER_UNIT * player2Units);
					} 
					else if (squareType == EXTRA_TURN) {
						endTurn();
					} 
					else if (squareType == JUMP_FORWARD) {
						player1Square += 4;
						if (player1Square >= numSquaresBoard) {
							player1Square = player1Square - 5;
							if (player1Square - value < 0 || squareType == PASS_GO) {
								player1Money += PASS_GO_PRIZE;
							}
						}
					}
					else if (squareType == DO_NOTHING) {
				
					} 
					endTurn();
				}
		}
				
			
		
		
			else if(currentPlayer == 2 && isGameEnded() == false) {
				getSquareType(player2Square);
				if ((squareType == STUCK) && (value % 2 != 0)) {
					endTurn();
				} else {
					player2Square += value;
					getSquareType(player2Square);
					if (player2Square >= numSquaresBoard) {
						player2Square = player2Square - value;
					}
					
						if (player2Square - value < 0 || squareType == PASS_GO) {
							player2Money += PASS_GO_PRIZE;
						} else if (squareType == CYCLONE) {
							player2Square = player1Square;
					
						} else if (squareType == PAY_PLAYER) {
							player2Money -= (PAYMENT_PER_UNIT * player1Units);
							player1Money += (PAYMENT_PER_UNIT * player1Units);
					
						} else if (squareType == EXTRA_TURN) {
							endTurn();
					
						} else if (squareType == JUMP_FORWARD) {
							player2Square += 4;
							if (player2Square >= numSquaresBoard) {
								player2Square = player2Square - 4;
								if (player2Square - value < 0 || squareType == PASS_GO) {
									player2Money += PASS_GO_PRIZE;
								}
							}
					
						} else if (squareType == DO_NOTHING) {
					
						} 
						endTurn();
					}
			}
	}
		
		
			
				
		
	
		
		
		
		
			
	/**
	 * Method called to indicate the current player attempts to sell
	 * one unit
	 * The sale is only allowed if the player has at least one unit to sell.
	 * If allowed, pay the current player UNIT_COST and decrease the 
	 * player's number of units by one. 
	 * 
	 * If the current player successfully sells a unit their turn ends
	 * (they are not allowed to take any further action or roll the dice).
	 * Update the turn to the other player
	 * 
	 * This method does nothing if the game has ended.
	 */
	public void sellUnit() {
		if(isGameEnded() == false) {
			if (currentPlayer == 1) {
				if(player1Units >= 1) {
					player1Money += UNIT_COST;
					player1Units -= 1;
					endTurn();
				}
			} else if (currentPlayer == 2) {
				if (player2Units >= 1) {
					player2Money += UNIT_COST;
					player2Units -= 1;
					endTurn();
				}
			}
		}
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
			String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
			String player1Turn = "";
			String player2Turn = "";
			if (getCurrentPlayer() == 1) {
				player1Turn = "*";
			} else {
				player2Turn = "*";
			}
			return String.format(fmt,
					player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
					player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}