package hw4;

import api.Card;


/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of cards required is equal to the hand size. 
 * 
 * The name of this evaluator is "All Primes".
 * 
 * @author Paige
 */

public class AllPrimesEvaluator extends AbstractEvaluator 
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public AllPrimesEvaluator(int ranking, int handSize)
  {
    // call the super
	  super("All Primes", ranking, handSize, handSize);
  }

  /**
   * helper method to determine if a number is Prime
   * @param n the number that is being tested for if it is prime 
   * @return true is the number is Prime and false otherwise 
   */
  private boolean isPrime(int n) {
	  for (int i = 2; i < n; ++i) 
		  if (n % i == 0)
			  	return false;
	  
	  return true; 
  }
  
 
@Override
public boolean canSatisfy(Card[] mainCards) {
	boolean isTrue = false;
	//if all of the cards in the hand are prime
	if (mainCards.length == cardsRequired()) {
		//for each card in the main cards
		for (Card c : mainCards) {
			//if the number is prime set to true
			if (isPrime(c.getRank()))
				isTrue = true;
			else
				//else return false
				return false;
		}
		
	}
	//if all of the numbers are prime return true 
	return isTrue; 
}
	
}
