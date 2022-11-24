package hw4;

import api.Card;

/**
 * Evaluator for a hand consisting of a "straight" in which the
 * card ranks are consecutive numbers.  The number of required 
 * cards is equal to the hand size.  An ace (card of rank 1) 
 * may be treated as the highest possible card or as the lowest
 * (not both).  To evaluate a straight containing an ace it is
 * necessary to know what the highest card rank will be in a
 * given game; therefore, this value must be specified when the
 * evaluator is constructed.  In a hand created by this
 * evaluator the cards are listed in descending order with high 
 * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
 * one exception: In case of an ace-low straight, the ace
 * must appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight".
 * 
 * @author paige
 */

public class StraightEvaluator extends AbstractEvaluator
{  
  /**
   * Constructs the evaluator. Note that the maximum rank of
   * the cards to be used must be specified in order to 
   * correctly evaluate a straight with ace high.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   * @param maxCardRank
   *   largest rank of any card to be used
   */
	
	/**
	 * instance variable to hold the max card rank
	 */
	private int maxCardRank; 
	
  public StraightEvaluator(int ranking, int handSize, int maxCardRank)
  {

	  //call super class constructor
	  super("Straight", ranking, handSize, handSize);
	  this.maxCardRank = maxCardRank;
  }
  
 
  /**
   * Evaluator for a hand consisting of a "straight" in which the
   * card ranks are consecutive numbers.  The number of required 
   * cards is equal to the hand size.  An ace (card of rank 1) 
   * may be treated as the highest possible card or as the lowest
   * (not both).  To evaluate a straight containing an ace it is
   * necessary to know what the highest card rank will be in a
   * given game; therefore, this value must be specified when the
   * evaluator is constructed.  In a hand created by this
   * evaluator the cards are listed in descending order with high 
   * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
   * one exception: In case of an ace-low straight, the ace
   * must appear last, as in [5 4 3 2 A]
   * 
   */
@Override
public boolean canSatisfy(Card[] mainCards) {
	boolean isTrue = false;
	if (mainCards.length == cardsRequired()) { 
		//check for the high ace first because that gives you the best hand 
		//if mainCards[1] != king
		if (mainCards[1].getRank() != maxCardRank) {
			//ace is low
			//if the first card is an Ace and the last card is a two
			if(mainCards[0].getRank() == 1 && mainCards[mainCards.length - 1].getRank() == 2) {
				//iterate through the cards and check that they are in decreasing order
				for (int i = 2; i < mainCards.length; ++i) {
					if (mainCards[i].getRank() == mainCards[i - 1].getRank() - 1) {
						isTrue = true; 
					} 
					else {
						//isTrue = false; 
						//break;
						return false;
					}
				}
				//check that the last card is a two and that it is in decreasing order
				if (mainCards[mainCards.length - 1].getRank() == 2 && isTrue) {
					//change the position of the cards so that the Ace is last
					//temp variable to hold the ace
					Card ace = mainCards[0]; 
					for (int i = 1; i < mainCards.length; ++i) {
						mainCards[i - 1] = mainCards[i];
					}
					//set the ace at the last position
					mainCards[mainCards.length - 1] = ace; 
					return true; 
				}
				//there is no ace and test without
			} else {
				for (int i = 0; i < mainCards.length - 1; ++i) {
					if (mainCards[i].getRank() == mainCards[i + 1].getRank() + 1) {
						isTrue = true;
					} else {
						//isTrue = false;
						return false; 
					}
						//check that the last card is decreasing
					} if (mainCards[mainCards.length - 1].getRank() + 1 == mainCards[mainCards.length -2].getRank())
						isTrue = true;
					else {
						//isTrue = false;
						return false;  
					}
				}
			}
	
		 //else ace is ranked 14 
		else {
			//ace is high
			//if the first card is an Ace
			if(mainCards[0].getRank() == 1) {
				//iterate through and check that they are in decreasing order
				for (int i = 2; i < mainCards.length; ++i) {
					if (mainCards[i].getRank() == mainCards[i - 1].getRank() - 1) {
						isTrue = true; 
					} else {
						//isTrue = false;
						//break;
						return false;
					}
					
				}
				if (isTrue)
					return true; 
			}
		}
		
	}
	return isTrue;
}

}
