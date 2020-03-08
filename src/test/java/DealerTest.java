import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DealerTest {
    
	//create static field for the the BaccaratDealer
	static BaccaratDealer deal;
	
	//Initialize the deal 
	@BeforeAll
	static void init()
	{
		deal = new BaccaratDealer();
	}
	
	//Test: check the class and look is it valid or no?
	@Test
	void checkCon()
	{
		assertEquals("class BaccaratDealer", deal.getClass().toString(), "Wrong constructor");
	}
	
	//Test: test the method dealHand() and check it it return 2 
	@Test
	void TestDealHand()
	{
		deal.generateDeck();
		ArrayList<Card> q;
		q = deal.dealHand();
		assertEquals(2, q.size(), "Wrong cards");
	}
	
	//Test: test the method dealHand() 4 times and check it it return 2 
	@Test
	void  TestDealHand2()
	{
			deal.generateDeck();
			ArrayList<Card> q;
			q = deal.dealHand();
			q = deal.dealHand();
			q = deal.dealHand();
			q = deal.dealHand();
			assertEquals(2, q.size(), "Wrong cards");
	}
	
	//Test: test the method to check it does not give same card again (it removes the card after it is drawn)
	@Test
	void testDes()
	{
		        deal.generateDeck();
				Card b = deal.drawOne();
				Card q = deal.drawOne();
				assertNotEquals(q ,b, "Wrong cards");
	}
	
	//Test: this checks if card is valid that is drawn from the deck 
	@Test
	void testDes2()
	{
		        deal.generateDeck();
		        Card p = deal.deck.get(0);
				Card b = deal.drawOne();
				assertEquals(p ,b, "Wrong cards");
	}
	
	//Test:check the card shuffle in right way so first card shouldn't stay at the same place 
	@Test
	void testShuffle()
	{
			        deal.generateDeck();
			        Card p = deal.deck.get(0);
					deal.shuffleDeck();
					assertNotEquals(p ,deal.deck.get(0), "Wrong cards");
	}
	

	//Test:check the card shuffle in right way so middle card shouldn't stay at the same place 
	@Test
	void testShuffle2()
	{
			 deal.generateDeck();
			 Card p = deal.deck.get(26);
		     deal.shuffleDeck();
		     assertNotEquals(p ,deal.deck.get(26), "Wrong cards");
	}
	
	//Test:check the size after one hand deal
	@Test
	void testSize1()
	{
		ArrayList<Card> q;
		q = deal.dealHand();
		assertEquals(48, deal.deckSize(), "Wrong cards");
	}
	//Test:check the size of one and two cards deck when card is taken out two decks
	@Test
	void testSize2()
	{
		deal.generateDeck();
		ArrayList<Card> q;
		q = deal.dealHand();
	
		assertEquals(98, deal.deckSize(), "Wrong cards");
	}
	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/

}
