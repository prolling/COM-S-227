package hw4;

import api.Card;
import api.Hand;
import api.Suit;

/**
 * Evaluator for a hand consisting of a "straight" in which the
 * card ranks are consecutive numbers AND the cards all
 * have the same suit.  The number of required 
 * cards is equal to the hand size.  An ace (card of rank 1) 
 * may be treated as the highest possible card or as the lowest
 * (not both) To evaluate a straight containing an ace it is
 * necessary to know what the highest card rank will be in a
 * given game; therefore, this value must be specified when the
 * evaluator is constructed.  In a hand created by this
 * evaluator the cards are listed in descending order with high 
 * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
 * one exception: In case of an ace-low straight, the ace
 * must appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight Flush".
 * 
 * @author paige
 */

public class StraightFlushEvaluator extends AbstractEvaluator
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
	 * instance variable that holds the maxRank for the straight 
	 */
	private int maxCardRank; 
	
  public StraightFlushEvaluator(int ranking, int handSize, int maxCardRank)
  {
    // call super 
	  super("Straight Flush", ranking, handSize, handSize);
	  //Initialize the maxRank
	  this.maxCardRank = maxCardRank;
  }

@Override
public boolean canSatisfy(Card[] mainCards) {
	//static instance of the straight evaluator 
	StraightEvaluator straight = new StraightEvaluator(getRanking(), handSize(), maxCardRank); 
	Suit suitForAll = mainCards[0].getSuit(); 
	//if the length of cards equals the required amount of cards
	if (mainCards.length == cardsRequired()) {
		
		
		//if they all have the same suit and satisfy the straight evaluator 
		if (straight.canSatisfy(mainCards)) {
			//make the mainCards the same as the mainCards from the straight evaluator
			Hand h = straight.getBestHand(mainCards); 
			mainCards = h.getMainCards();
			//for all of the cards in main cards
			boolean sameSuits = false;
			for (Card c : mainCards) {
				//check to see if they all have the same suit
				if (c.getSuit() == suitForAll) {
					sameSuits = true;
				} else
					return false;
			}
			if (sameSuits)
				//return true 
				
				return true; 
			}	
		}
	
	return false; 
}

}

