/*
 * Name: Raj Patel       NetId : rpate375
 * Name: Samarth Patel   NetId : spate504
 *  
 * Resources 
 * Text Formatter:
 * //https://stackoverflow.com/questions/40472668/numeric-textfield-for-integers-in-javafx-8-with-
 * textformatter-and-or-unaryoperat
 * 
 * Java Fx Features:
 * https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.html
 * 
 * Images for card: 
 * http://pngimg.com/imgs/objects/cards/
 * 
 */

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.css.converter.StringConverter;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class BaccaratGame extends Application {
	
	//player cards arrayList
	ArrayList<Card> playerHand;
	//banker cards arrayList
	ArrayList<Card> bankerHand;
	
	//instance for the BaccaratDealer
	BaccaratDealer theDealer;
	
	//Amount from the textField
	double  dAmount=0.0;
	
	//instance for the BaccaratGameLogic
	BaccaratGameLogic gameLogic;
	
	//Double currentBet
	double currentBet=0.0;
	
	//double TotalWinnings
	double totalWinnings=0.0;
	
	//count the total from players card
	int playerTotal=0;
	
	//count total from Banker's card
	int bankerTotal=0;
	
	//who for the bet on banker/player/tie
	String who;
	
	//string for eval the amount
	String eval;
	
	
	//method game 
	public void game(Stage primaryStage)
	{
		//Title
	    primaryStage.setTitle("Let's Play Baccarat!!!");
		
		//BackGround color : set it green;
		BackgroundFill background_fill = new BackgroundFill(Color.GREEN,  
                CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		
		//Menubar with 1 option
		MenuBar menu= new MenuBar();
		Menu mOne = new Menu("Options");
		//options with two choices
		MenuItem freshStart = new MenuItem("Fresh Start");		//option to start the game fresh
		MenuItem ExitBtn = new MenuItem("Exit");				//option ot exit the game
		
		freshStart.setOnAction(e ->{							// Starting fresh with winning is 0
			totalWinnings=0;
			game(primaryStage);
		});
		
		ExitBtn.setOnAction(e->primaryStage.close());			//closing the game
		
		mOne.getItems().addAll(freshStart,ExitBtn);					//adding the items in the options
		menu.getMenus().add(mOne);							//adding option to menu
		
		HBox mbar= new HBox(menu);							//box for saving the menu
		
		
		
		
		//player lable : with Golden color and TimeNewRoman font with 30 size
		Label playerLable = new Label("Player");
		playerLable.setTextFill(Color.web("#ffd700", 0.8));
		playerLable.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		playerLable.setTranslateX(100);
		playerLable.setTranslateY(100);
		
		//Dealer lable : with white color and TimeNewRoman font with 30 size 
		Label dealerLable = new Label("Dealer");
		dealerLable.setTextFill(Color.web("#ffffff", 0.8));
		dealerLable.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		dealerLable.setTranslateX(400);
		dealerLable.setTranslateY(0);
		
		//Dealer lable : with red color and TimeNewRoman font with 30 size
		Label bankerLable = new Label("Banker");
		bankerLable.setTextFill(Color.web("#ff0000", 0.8));
		bankerLable.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		bankerLable.setTranslateX(650);
		bankerLable.setTranslateY(100);
		
		//HBox for player Card Images and bankerImages set the coordinates 
		HBox playerImages =new HBox();
		HBox bankerImages= new HBox();
		playerImages.setTranslateY(300);
		bankerImages.setTranslateY(300);
	
		//TextField for user Amount and set TextField
		TextField betAmount = new TextField("");
		betAmount.setPromptText("Bet her Only Nums");
		UnaryOperator<Change> integerFilter = change -> {
		    String newText = change.getControlNewText();
		    if (newText.matches("-?([1-9][0-9]*)?")) { 
		        return change;
		    }
		    return null;
		};

		betAmount.setTextFormatter(
		    new TextFormatter<Integer>(integerFilter));					
		betAmount.setPrefSize(225, 50);
		
		//Button for player and set width and height
		Button playerBtn = new Button("Player");
		playerBtn.setTextFill(Color.BLACK);
		playerBtn.setPrefSize(100, 50);
		
		//Button for banker and set width and height
		Button bankerBtn = new Button("Banker");
		bankerBtn.setTextFill(Color.RED);
		bankerBtn.setPrefSize(100, 50);
		
		//Button for banker and set width and height
		Button tieBtn = new Button("Tie");
		tieBtn.setTextFill(Color.BLUE);
		tieBtn.setPrefSize(100, 50);
		
		//Button for play again and set width and height and adjusted position
		Button playAgn = new Button("Play Again");
		playAgn.setPrefSize(100, 50);
		playAgn.setTranslateX(100);
		
		//winning amount lable : with golden color and TimeNewRoman font with 30 size
		Label winningAmt = new Label("Win Amount: $" + Double.toString(totalWinnings));
		winningAmt.setTranslateX(50);
		winningAmt.setTextFill(Color.web("#ffd700", 0.8));
		winningAmt.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		
		//label for who has one
		Label won= new Label();														// label to  print who won
		won.setTextFill(Color.web("#ff0000", 0.8));
		won.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		won.setTranslateY(250);
    	won.setTranslateX(200);
		
    	//player total
		Label playertot= new Label();												
		won.setTranslateY(250);
		won.setTranslateX(200);
		
		//banker total 
        Label bankertot= new Label();
		won.setTranslateY(250);
		won.setTranslateX(200);
		
		//show total on the screen for both player and banker
		playertot.setTextFill(Color.web("#ffd700", 0.8));
		playertot.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		playertot.setTranslateX(-275);
		playertot.setTranslateY(600);
		
		bankertot.setTextFill(Color.web("#ffd700", 0.8));
		bankertot.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
		bankertot.setTranslateX(200);
		bankertot.setTranslateY(600);
		
		
		//horizontal box for viewing the  individual total of each player and banker
		HBox total= new HBox(playertot,bankertot);
		//HBox : labels for the title
        HBox title = new HBox(playerLable, dealerLable, bankerLable,won,total,mbar);
		
		//Hbox: it has player/banker/tie/play again button with winning amaount
        HBox bottom = new HBox( playerBtn,betAmount, bankerBtn,tieBtn,winningAmt, playAgn);
		
		//create instance of the borderpane
		BorderPane p = new BorderPane();
		
		//set images in middle left for player and right for banker
		p.setLeft(playerImages);
		
		p.setRight(bankerImages);
		
		//set title Hbox on the top
		p.setTop(title);
		
		//set bottom hbox in the bottom
		p.setBottom(bottom);
		
		//set background to back ground
	    p.setBackground(background);
		
		//playerBtn sectOnAction when it is clicked 
        playerBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent action) {
				//set all button to disable so when game starts
				bankerBtn.setDisable(true);
				playerBtn.setDisable(true);
				tieBtn.setDisable(true);
				
				//get the amount from text field and parse it to double
				String amount=betAmount.getText();
				if(amount.isEmpty())				//if the string is empty
					dAmount=0.0;
				else
					dAmount = Double.parseDouble(amount);
				
				//set string to "p" so game logic knows who has bet on
				who="p";
				
				//get the amount and set it to currentBet
				currentBet = dAmount;
				
				//set the amount and who(player) and player/Banker Images to game play method
				gameplay(dAmount,who,playerImages,bankerImages );
				
				//evalute totalWinning
				totalWinnings = evaluateWinnings();
				
				//show who has won
				bankertot.setText("Banker Total: "+Integer.toString(bankerTotal));
				playertot.setText("Player Total: "+Integer.toString(playerTotal));
				won.setText(eval);
				
				//set totalWinnig to the winning amount lable
				winningAmt.setText(String.format("$ %.2f", totalWinnings));
			}
		});
		
        //bankerBtn sectOnAction when it is clicked 
		bankerBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent action) {
					//set all button to disable so when game starts
					bankerBtn.setDisable(true);
					playerBtn.setDisable(true);
					tieBtn.setDisable(true);
					//get the amount from text field and parse it to double
					String amount=betAmount.getText();
					if(amount.isEmpty())				//if the string is empty
						dAmount=0.0;
					else
						dAmount = Double.parseDouble(amount);
					
					
					
					//set string to "b" so game logic knows who has bet on
					who="b";
					
					//get the amount and set it to currentBet
					currentBet=dAmount;
					
					//set the amount and who(banker) and player/Banker Images to game play method
					gameplay(dAmount,who,playerImages,bankerImages );
					
					//evalute totalWinning
					totalWinnings = evaluateWinnings();
					
					won.setText(eval);
					bankertot.setText("Banker Total: "+Integer.toString(bankerTotal));
					playertot.setText("Player Total: "+Integer.toString(playerTotal));
					//set totalWinnig to the winning amount lable
					winningAmt.setText(String.format("$ %.2f", totalWinnings));
				}
			});
		
		  //tieBtn sectOnAction when it is clicked 
		  tieBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent action) {
					//set all button to disable so when game starts
					bankerBtn.setDisable(true);
					playerBtn.setDisable(true);
					tieBtn.setDisable(true);
					
					//get the amount from text field and parse it to double
					String amount=betAmount.getText();
					if(amount.isEmpty())				//if the string is empty
						dAmount=0.0;
					else
						dAmount = Double.parseDouble(amount);
					
					
					//set string to "t" so game logic knows who has bet on
					who="t";
					
					//get the amount and set it to currentBet
					
					
					//set the amount and who(banker) and player/Banker Images to game play method
					gameplay(dAmount,who,playerImages,bankerImages );
					currentBet = dAmount;
					//Evaluate totalWinning
					totalWinnings = evaluateWinnings();
					
					
					won.setText(eval);
					bankertot.setText("Banker Total: "+Integer.toString(bankerTotal));
					playertot.setText("Player Total: "+Integer.toString(playerTotal));
					//set totalWinnig to the winning amount lable
					winningAmt.setText(String.format("$ %.2f", totalWinnings));
				}
			});
			
		//playerAgain sectOnAction when it is clicked 
		playAgn.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent action) {
					 p.getChildren().clear();
					 
					 restart(primaryStage);
					    
				}
			});
		 
		//create scene with 1050 * 800 and set border pane
		Scene scene = new Scene(p,1050,800);
		
		//set scene in primary stage 
		primaryStage.setScene(scene);
		
		//show the primary stage
		primaryStage.show();
		 
	}
	
	//game play : back ground logic
	public void gameplay(double betAmount, String who, HBox playerImages,HBox bankerImages )
	{
		//boolean for player sum and banker sum
		boolean playersum, bankersum;
		
		//create the BaccaratDealer
		this.theDealer= new BaccaratDealer();
		
		//create the BaccaratGameLogic
		this.gameLogic= new BaccaratGameLogic();
		
		//generate Deck
		theDealer.generateDeck();
		
		//shuffle deck
		theDealer.shuffleDeck();
		
		//get the card and store it in to player hand ArryList
		playerHand=theDealer.dealHand();
		
		//shuffle deck again
		theDealer.shuffleDeck();
		
		//get the card and store it in to  banker hand ArryList
		bankerHand=theDealer.dealHand();
		
		//evalute the player hand for less than 5 or greater than 5
		playersum=this.gameLogic.evaluatePlayerDraw(playerHand);
		
		//if playersum is true than show third card and add it to the player Hand
		if(playersum)
		{
			playerHand.add(theDealer.drawOne());
			bankersum=this.gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2));
			if(bankersum)
			{
				bankerHand.add(theDealer.drawOne());			//if Bankersum is true than show third card and add it to the player Hand
			}

		}
		
		
		
		
		//Images for playerHand
		for( Card c: playerHand)
		{
			//merge the player suite and number and get the image 
			Image playercard1= new Image("images/"+Integer.toString(c.value)+c.suite+".png",175,200,false, false);
			ImageView playp1= new ImageView(playercard1);
			playerImages.getChildren().add(playp1);
			
		}
		for( Card c: bankerHand)
		{
			//merge the banker suite and number and get the image 
			Image playercard1= new Image("images/"+Integer.toString(c.value)+c.suite+".png",175,200,false, false);
			ImageView playp1= new ImageView(playercard1);
			bankerImages.getChildren().add(playp1);
		}
		
		//send total to get hand total for player and banker
		playerTotal= gameLogic.handTotal(playerHand);
		bankerTotal=gameLogic.handTotal(bankerHand);
		
		//Evaluate  the who has won 
		eval = gameLogic.whoWon(playerHand, bankerHand);
		
	}
	
	public double evaluateWinnings()
	{
		//total of 5 cases
		//if bet is on player and player won double the bet amount and return
		if(who == "p" && eval == "Player Won")
		{
			totalWinnings = totalWinnings + (currentBet * 2);
	    //if bet is on banker and banker won take 5% and return the original bet + 95% of bet amount
		}else if(who == "b" && eval == "Banker Won")
		{
			totalWinnings = totalWinnings + (currentBet * 0.95) + currentBet;
	    //if bet is on tie and match goes to draw return 8 times the current bet	
		}else if(who == "t" && eval == "Draw")
		{
			totalWinnings = totalWinnings + (currentBet * 8);
			
		}else 
		{
			//else take the amount out of totalwinning
			totalWinnings = totalWinnings - currentBet;
			
		}
		//return total winning
		return totalWinnings;
	}
	
	//main 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
     //start the game here
	 game(primaryStage);
		
	}
	
   //restart the game and for play again button
   void restart (Stage stage)
   {
	 playerHand.clear();
	 bankerHand.clear();
	 game(stage);
    }
 

}
