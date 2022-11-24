package hw4;

import api.Card;
import api.Hand;

/**
 * Evaluator for a generalized full house.  The number of required
 * cards is equal to the hand size.  If the hand size is an odd number
 * n, then there must be (n / 2) + 1 cards of the matching rank and the
 * remaining (n / 2) cards must be of matching rank. In this case, when constructing
 * a hand, the larger group must be listed first even if of lower rank
 * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
 * If the hand size is even, then half the cards must be of matching 
 * rank and the remaining half of matching rank.  Any group of cards,
 * all of which are the same rank, always satisfies this
 * evaluator.
 * 
 * The name of this evaluator is "Full House".
 * 
 * @author paige
 */

public class FullHouseEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public FullHouseEvaluator(int ranking, int handSize)
  {
    //call super
	  super("Full House", ranking, handSize, handSize);
  }

  /**
   * Evaluator for a generalized full house.  The number of required
   * cards is equal to the hand size.  If the hand size is an odd number
   * n, then there must be (n / 2) + 1 cards of the matching rank and the
   * remaining (n / 2) cards must be of matching rank. In this case, when constructing
   * a hand, the larger group must be listed first even if of lower rank
   * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
   * If the hand size is even, then half the cards must be of matching 
   * rank and the remaining half of matching rank.  Any group of cards,
   * all of which are the same rank, always satisfies this
   * evaluator.
   */
 
  
@Override
public boolean canSatisfy(Card[] mainCards) {
	
	// if the length of the mainCards is the amount of cards required 
	if (mainCards.length == cardsRequired()) {
		//check to see if all of the cards are the same
		int numTheSame = 0;
		for (int i = 0; i < mainCards.length; ++i) {
			int rankOfAll = mainCards[0].getRank();
			if (mainCards[i].getRank() == rankOfAll) {
				numTheSame ++;
			}
		}
		//if they are return true
		if (numTheSame == mainCards.length)
			return true; 
		
		//check if there is a full house for if the number of cards is odd
		if (handSize() % 2 != 0) {
			
			Card[] mainOdd = new Card[(mainCards.length / 2) + 1];
			Card[] mainEven = new Card[mainCards.length / 2]; 
			
			int rank1 = mainCards[0].getRank();
			int numRank1 = 0;
			int rank2 = mainCards[mainCards.length - 1].getRank();
			int numRank2 = 0;
			
			//iterate through the cards and find which rank is the even and which is the odd
			for (Card c : mainCards) {
				if (c.getRank() == rank1) {
					numRank1 ++;
				} else if (c.getRank() == rank2)
					numRank2 ++;
			}
			//if the two ranks combined do not equal all of the cards, don't do anymore and return false
			if (numRank1 + numRank2 != mainCards.length || 
					//if not either one of the ranks is equal to the mainOdd and the other to mainEven 
					!((numRank1 == mainOdd.length && numRank2 == mainEven.length) 
							|| (numRank1 == mainEven.length && numRank2 == mainOdd.length)))
				return false;
			
			int inx1 = 0;
			int inx2 = 0;
			//if the rank is odd, the cards with that rank go into the oddCards
			if (numRank1 % 2 != 0 && numRank2 % 2 == 0) {
				for (Card c: mainCards) {
					if (c.getRank() == rank1) {
						mainOdd[inx1] = c;
						inx1 ++;
					//the other cards go into the even 
					} else if (c.getRank() == rank2) {
						mainEven[inx2] = c;
						inx2 ++; 
					}
				}
			//else if the second rank is odd, the cards with that rank go into the oddCards
			} else if (numRank2 % 2 != 0 && numRank1 % 2 == 0) {
				for (Card c : mainCards) {
					if (c.getRank() == rank2) {
						mainOdd[inx2] = c;
						inx2 ++;
					//the other cards go into the even 
					} else if (c.getRank() == rank1) {
						mainEven[inx1] = c;
						inx1 ++;
					}
				}
			}
			//reorder the main cards so that the odd is before the even 
			//put the mainOdd in first
			for (int i = 0; i < mainOdd.length; ++i) {
				mainCards[i] = mainOdd[i];
			}
			//put the mainTwo in after
			for (int j = mainOdd.length, inxP = 0; j < mainCards.length; ++j, inxP ++) {
				mainCards[j] = mainEven[inxP]; 
			}
			
			//if the numRank1 and numRank2 combined is equal to the total amount of cards
			if (numRank1 + numRank2 == mainCards.length)
				return true; 
			 
			
			//check if there is a full house for if the number of cards is even
		} else if (handSize() % 2 == 0) {
			//Card[] main1 = new Card[(mainCards.length / 2) + 1];
			//Card[]  = new Card[mainCards.length / 2]; 
			
			int rank1 = mainCards[0].getRank();
			int numRank1 = 0;
			int rank2 = mainCards[mainCards.length - 1].getRank();
			int numRank2 = 0;
			
			//iterate through the cards and find which rank is rank1 and which is rank2
			for (Card c : mainCards) {
				if (c.getRank() == rank1) {
					numRank1 ++;
				} else if (c.getRank() == rank2)
					numRank2 ++;
			}
			
			//if the number of rank1 is equal to rank2 and when they're added together they equal the
			//total amount of cards, return true
			if (numRank1 == numRank2 && numRank1 + numRank2 == mainCards.length)
				return true; 
			
	 
	}
	}
	return false;
	}
}
