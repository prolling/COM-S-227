package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 * 
 * @author paige 
 */
public class ThreeOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public ThreeOfAKindEvaluator(int ranking, int handSize)
  {
    //call the super
	  super("Three of a kind", ranking, handSize, 3);

  }

@Override
public boolean canSatisfy(Card[] mainCards) {
	boolean isTrue = false;
	if (mainCards.length == cardsRequired()) {
		//set the rank that all the cards should be
		int rankForAll = mainCards[0].getRank();
		//if all of the cards are the same rank return true 
		for (Card c : mainCards) {
			if (c.getRank() == rankForAll)
				isTrue = true;
			else
				isTrue = false; 
		}
		  
		   
	  
}
	return isTrue;
}
}
