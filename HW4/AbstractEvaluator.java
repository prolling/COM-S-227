package hw4;
import java.util.ArrayList;
import java.util.Collections;

import api.Card;
import api.Hand;
import api.IEvaluator;
import util.SubsetFinder;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy
 * is organized.
 * IEvaluator is the interface that holds all of the methods being used by the evaluators
 * Abstract Evaluator gives the instance variables, constructor, canSubsetSatisfy(), 
 * createHand(), and getBestHand() methods
 * the canSatisfy() method is done by each evaluator and is specific to each evaluators' needs
 * 
 * @author paige
 */
public abstract class AbstractEvaluator implements IEvaluator
{
	/**
	 * instance variable for the name of the evaluator 
	 */
	private String name; 
	/**
	 * instance variable for the rank of the evaluator 
	 */
	private int rank;
	/**
	 * instance variable for the hand of the evaluator 
	 */
	private int hand; 
	/**
	 * instance variable for the required cards of the evaluator 
	 */
	private int requiredCards;
	
	/**
	 * ArrayList to hold all of the subsets that will satisfy a given evaluator 
	 * is used in the best hand method
	 */
	private ArrayList<int[]> subsetsThatSatisfy = new ArrayList<>();
	
	/**
	 * constructor for the abstract evaluator that gives the name, rank, hand, 
	 * and requiredCards of the evaluator 
	 * @param name of the evaluator 
	 * @param rank of the evaluator 
	 * @param hand of the evaluator 
	 * @param requiredCards of the evaluator 
	 */
	protected AbstractEvaluator(String name, int rank, int hand, int requiredCards) {
		this.name = name;
		this.rank = rank;
		this.hand = hand; 
		this.requiredCards = requiredCards; 
	}
	/**
	   * Returns a name for this evaluator.
	   * @return
	   *   name of this evaluator
	   */
	  public String getName() {
		  return name; 
	  }
	  
	  /**
	   * Returns the ranking for this evaluator, where a lower number
	   * is considered "better".
	   * @return
	   *   ranking for this evaluator
	   */
	  public int getRanking() {
		  return rank; 
	  }
	  
	  /**
	   * Returns the minimum number of cards needed for a hand
	   * produced by this evaluator (main cards only).
	   * @return
	   */
	  public int cardsRequired() {
		  return requiredCards; 
	  }
	  
	  /**
	   * Returns the number of cards in a hand.  This value is generally
	   * defined by a game, and is not necessarily the same as
	   * the value returned by <code>cardsRequired</code>
	   * (main cards plus side cards).
	   * @return
	   *   number of cards in a hand
	   */
	  public int handSize() {
		  return hand; 
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
	  public abstract boolean canSatisfy(Card[] mainCards);
	  
	  /**
	   * Determines whether there exists a subset of the given cards
	   * that satisfies the criteria for this evaluator.  The length of
	   * the given array must be greater than or equal to the value
	   * returned by <code>cardsRequired()</code>. The
	   * given array must be sorted with highest-ranked card first
	   * according to <code>Card.compareTo()</code>.  The array
	   * is not modified by this operation.
	   * @param allCards
	   *   list of cards to evaluate
	   * @return
	   *   true if some subset of the given cards satisfy this evaluator
	   */
	  public boolean canSubsetSatisfy(Card[] allCards) {
		  boolean isTrue = false;
		  //if the length of all cards is greater than the required cards
		  if (allCards.length >= cardsRequired()) {
			  
			  
			  //use SubsetFinder to find all of the possible subsets
			  ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, cardsRequired());
			  //iterate through the subsets that are found and see if they satisfy
			  for (int i = 0; i < subsets.size(); ++i) {
				  int[] subset = subsets.get(i);
				  Card[] temp = new Card[cardsRequired()];
				  int resultIndex = 0; 
				  for (int j = 0; j < subset.length; ++j) {
					  int index = subset[j];
					  Card c = allCards[index];
					  temp[resultIndex] = c;
					  resultIndex += 1;
				  }
				  //if the current subset satisfies the given evaluator 
				  if (canSatisfy(temp) != false){
					  //add the subset to the list of subsets that satisfy
					  subsetsThatSatisfy.add(subset); 
					  //set boolean to true
					  isTrue = true;
				  }
				  
			  }
			 
		  }
		  return isTrue; 
	  }
	  
	  /**
	   * Returns a hand whose main cards consist of the indicated subset
	   * of the given cards.  If the indicated subset does
	   * not satisfy the criteria for this evaluator, this
	   * method returns null. The subset is described as
	   * an ordered array of indices to be selected from the given
	   * Card array.  The number of main cards in the hand
	   * will be the value of <code>getRequiredCards()</code>
	   * and the total number of cards in the hand will
	   * be the value of <code>handSize()</code>.  If the length
	   * of the given array of cards is less than <code>handSize()</code>,
	   * this method returns null.   The
	   * given array must be sorted with highest-ranked card first
	   * according to <code>Card.compareTo()</code>.  The array
	   * is not modified by this operation.
	   * 
	   * @param allCards
	   *   list of cards from which to select the main cards for the hand
	   * @param subset
	   *   list of indices of cards to be selected, in ascending order
	   * @return
	   *   hand whose main cards consist of the indicated subset, or null
	   *   if the indicated subset does not satisfy this evaluator
	   */
	  
	  public Hand createHand(Card[] allCards, int[] subset) {
		  //if the greatest card in the subset is less than or equal to the length of allCards
		  if (subset[1] <= allCards.length) {
			   
			  int subsetLength = subset.length; 
			  //card array of the main cards
			  Card[] mainCards = new Card[subsetLength]; 
			  //card array of the side cards
			  Card[] sideCards = new Card[handSize() - subsetLength];
			   		
			  int indexMain = 0;
			  int indexSide = 0;
			  //put all of the cards from the subset into the main cards
			  for (int sub : subset) {
				  mainCards[indexMain] = allCards[sub]; 
				  indexMain ++; 
			  }
			  
			  //put the cards that aren't in the subset into the side cards
			  boolean notIn = false;
			  //go through all of the cards
				for (int i = 0; i < allCards.length; ++i) {
					//go through the indexes in the subset 
					for (int sub : subset) {
				
					//if the main card is not in the subset
					if (i != sub) {
						notIn = true;
					} else {
						notIn = false;
						break;
					}
					}
					//if the main card is not in the subset
					if (notIn) {
						//while the index of the side cards is less than the length of the side cards
						if (indexSide < sideCards.length) {
							//add the card to the side cards
							sideCards[indexSide] = allCards[i]; 
							indexSide ++; 
						}
					
					}
				 
			  }
			  
					 
		  if (this.canSatisfy(mainCards))
			  //return a new Hand object
			  return new Hand(mainCards, sideCards, this);
		  else
			  return null; 
			//else return null
		  } else
			  return null;
	  }
	  /**
	   * Returns the best possible hand satisfying this evaluator's 
	   * criteria that can be formed from the given list of cards.
	   * "Best" is defined to be first according to the compareTo() method of 
	   * Hand.  Returns null if there is no such hand.  The number of main cards 
	   * in the hand will be the value of <code>getRequiredCards()</code>
	   * and the total number of cards in the hand will
	   * be the value of <code>handSize()</code>.  If the length
	   * of the given array of cards is less than <code>totalCards()</code>,
	   * this method returns null.  The
	   * given array must be sorted with highest-ranked card first
	   * according to <code>Card.compareTo()</code>.  The array
	   * is not modified by this operation.
	   *  
	   * @param allCards
	   *   list of cards from which to create the hand
	   * @return
	   *   best possible hand satisfying this evaluator that can be formed
	   *   from the given cards
	   */
	  public Hand getBestHand(Card[] allCards) {
		  //if there is a subset that satisfies the evaluator 
		  if (canSubsetSatisfy(allCards)) {
			  //new array list of all possible hands 
			  ArrayList<Hand> hands = new ArrayList<Hand>(); 
			  //iterate through the subsets that satisfy
			  for (int i = 0; i < subsetsThatSatisfy.size(); ++i) {
				  Hand hand = createHand(allCards, subsetsThatSatisfy.get(i));
				  if (hand != null)
					  hands.add(hand); 
			  }
			  //sort the hand
			  Collections.sort(hands); 
			  //return the best hand
			  return hands.get(0); 
		  } else 
			  return null;
	  }
}
