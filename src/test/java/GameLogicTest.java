import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameLogicTest {
	static BaccaratGameLogic game1;					//running test for BaccaratGameLogic
	static Card a,b,c,d;
	static ArrayList<Card> player,player2,banker;
	@BeforeAll
	static void setUp() throws Exception {
		a= new Card("Red",5);
		b= new Card("Red",2);
		c= new Card("Red",6);
		d= new Card("Red",4);//generating deck
		player=	new ArrayList<Card>();						//dealing for the player
		player2=	new ArrayList<Card>();	
		banker=	new ArrayList<Card>();						//dealing for the banker
		player.add(a);
		player.add(b);
		banker.add(c);
		banker.add(d);
		game1= new BaccaratGameLogic();					//instantiating the gamelogic
		
		player2.add(new Card("Red",8));			//adding cards to new player2
		player2.add(new Card("Red",6));
		
		
	}

	@Test
	void testConstructor() {						//checking if the constructor was called properly.
		assertEquals("class BaccaratGameLogic",game1.getClass().toString(), "handTotal is incorrect"	);
	}
	@Test
	void testhandTotal1() {							//checking if the total comes true or not
		assertEquals(7,game1.handTotal(player), "handTotal is incorrect");
	}
	@Test
	void testhandTotal2() {							//checking if the total come true or not
		assertEquals(0,game1.handTotal(banker), "handTotal is incorrect");
	}
	
	@Test
	void testhandTotal3() {							//checking if the total come true or not
		
		assertEquals(4,game1.handTotal(player2), "handTotal is incorrect");
	}
	@Test
	void testEvaluatePlayer1() {							//checking if the Evaluateplayer come true or not
		assertEquals(false,game1.evaluatePlayerDraw(player), "evaluateplayer is incorrect");
	}
	@Test
	void testEvaluatePlayer2() {							//checking if the Evaluateplayer come true or not
		assertEquals(true,game1.evaluatePlayerDraw(player2), "evaluateplayer is incorrect");
	}
	@Test
	void testEvaluateBanker1() {							//checking if the EvaluateBanker come true or not
		assertEquals(true,game1.evaluateBankerDraw(banker, a), "evaluatebanker is incorrect");
	}
	@Test
	void testEvaluateBanker2() {							//checking if the EvaluateBanker come true or not
		assertEquals(true,game1.evaluateBankerDraw(banker, c), "evaluatebanker is incorrect");
	}
	@Test
	void testwhoWon1() {							//checking if the whoWon come true or not
		assertEquals("Player Won",game1.whoWon(player, banker), "evaluatebanker is incorrect");
	}
	@Test
	void testwhoWon2() {							//checking if whoWon come true or not
		assertEquals("Player Won",game1.whoWon(player, banker), "evaluatebanker is incorrect");
	}
}
