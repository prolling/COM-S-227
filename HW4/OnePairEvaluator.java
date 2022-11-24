package hw4;
import api.Card;


/**
 * Evaluator for a hand containing (at least) two cards of the same rank.
 * The number of cards required is two.
 * 
 * The name of this evaluator is "One Pair".
 * 
 * @author paige
 */

public class OnePairEvaluator extends AbstractEvaluator
{

  /**
   * Constructs the evaluator by calling the super
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
	
  public OnePairEvaluator(int rank, int hand) 
  { 
	  super("One Pair", rank, hand, 2);
  } 
  
  /**
   * Determines whether the given group of cards satisfies the
   * criteria this evaluator.  The length
   * of the given array must exactly match the value 
   * returned by <code>cardsRequired()</code>.  The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * @param mainCards
   *   array of cards
   * @return
   *   true if the given cards satisfy this evaluator
   */
  @Override
  public boolean canSatisfy(Card[] mainCards) {
	  //if the main cards is the same length as the amount of cards required
	  if (mainCards.length == cardsRequired())
		  //if the rank of the first main card is the same as the second return true
		  if (mainCards[0].getRank() == mainCards[1].getRank())
			  return true; 
		  
		   
	  return false; 
  }
}
