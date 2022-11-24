package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of cards required is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 * 
 * @author paige
 */

public class FourOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public FourOfAKindEvaluator(int ranking, int handSize)
  {
    // call the super
	  super("Four of a kind", ranking, handSize, 4);
  }

@Override
public boolean canSatisfy(Card[] mainCards) {
	boolean isTrue = false;
	if (mainCards.length == cardsRequired()) {
		//set the rank that all of the cards should be 
		int rankForAll = mainCards[0].getRank();
		//if they are all the same rank return true
		  for (Card c : mainCards) {
			  if (c.getRank() == rankForAll) {
				  isTrue = true;
			  } else {
				  isTrue = false; 
			  }
		  }
			  
	
}
  
	return isTrue;
}
}
