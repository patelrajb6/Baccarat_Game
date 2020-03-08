/*
 * Name: Raj Patel       NetId : rpate375
 * Name: Samarth Patel   NetId : spate504
 *  
 */

import java.util.ArrayList;
import java.util.Collections;

//Baccarat Dealer Class
public class BaccaratDealer {
    
	//create the deck of 52 cards 
	ArrayList<Card> deck = new ArrayList<Card>(52);
	
	//geneate the card
	public void generateDeck()
	{
		//array for suits
		String suit[] = {"hearts","clovers","diamonds","spades"};
		//array for value
		int value[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		
		//generate the card for all numbers and all suite card and push it in to deck
		for(int i = 0; i<value.length; i++)
		{
			for(int j =0; j<suit.length; j++)
			{
				deck.add(new Card(suit[j],value[i]));
			}
		}
	}
	
	//deal hand: it will return arrayList of cards 
	public ArrayList<Card> dealHand()
	{
		
		ArrayList<Card> d = new ArrayList<Card>(2);
		//get the two cards
		d.add(deck.get(0));
		d.add(deck.get(1));
		
		//remove the cards from the deck
		deck.remove(0);
		deck.remove(1);
		
		//return the array list 
		return d;
	}
	
	//it will return one card 
	public Card drawOne()
	{	
		Card b = deck.get(0);
		//remove the card from the deck
		deck.remove(0);
		return b;
	}
	
	//shuffle the deck with collection.shuffle
	public void shuffleDeck()
	{
		//shuffle the deck arrayList
		 Collections.shuffle(deck); 
	}
	
	//return deck size
	public int deckSize()
	{
		return deck.size();
	}
}
