/*
 * Name: Raj Patel       NetId : rpate375
 * Name: Samarth Patel   NetId : spate504
 *  
 */

import java.util.ArrayList;

//BaccaratGameLogic
public class BaccaratGameLogic {

	//Function: takes two parameter of arrayList and evalute  who has won and return string
	public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2)
	{
		//get total for hand 1
		int total1 = handTotal(hand1);
		
		//get total for hand 2
		int total2 = handTotal(hand2);
		
		//if both total is equal draw else player won and banker one
		if(total1 == total2)
		{
			return "Draw";
		}else if(total1 > total2)
		{
			return "Player Won";
		}else
		{
			return "Banker Won";
		}
	}
	
	//Funtion: it takes the arrayList of cards and return the total of two cards
	public int handTotal(ArrayList<Card> hand)
	{
		
		int oneValue=0;
		
		int total=0;
		for(Card one: hand)
		{
			oneValue = (one.value >= 10) ? 0 : one.value;
			total+=oneValue;
		}
		
		if(total >= 10)
		{
		    total = total % 10; 
		}
		

		return total;
	}
	
	//Function: it takes the array List of cards and card of player 
	public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
	{
		//evalute the hand total
		int total = handTotal(hand);
		
		//evalute the value of player card
		int value = playerCard.value;
		
		//if total of banker cards is less than 2 than it will draw a card 
		if(total >= 0 && total <= 2)
		{
			return true;
	     //if total is grater than three and less than 6 also player card value is less than 5 
		// than draw a card
		}else if(total >= 3 && total <= 6 && value < 5)
		{
			return true;
	    //if it is greate than 6 than dont draw a card
		}else if(total > 6)
		{
			return false;
		}
		
		//if any condition is not met than return false
		return false;	
	}
	
	//Function: evalute the players card and return true or false for draw a third card
	public boolean evaluatePlayerDraw(ArrayList<Card> hand)
	{
	   //get total sum by hand total
	   int total = handTotal(hand);
	   
	   //if total is greater than 5 player wont draw third card else it will draw a card for 0-5
	   if(total > 5)
	   {
		   return false;
	   }else
	   {
		   return true;
	   }
	}
}
