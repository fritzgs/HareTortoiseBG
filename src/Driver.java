import java.util.HashSet;
import java.util.Scanner;
/**
* The driver class contains the main method and most methods which speak to the console
* It pulls together the different methods in order for the game to run
* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
* @version 1.0 (12.03.2016)
*/
public class Driver {
	
	private Scanner input;
	private Board board;
	private Player player;
	private InputReader reader;
	private Responder responder;
	
	public Driver(){
		
		input = new Scanner(System.in);
		responder = new Responder();
		reader = new InputReader();
	}
	
	 public static void main(String[] args) {
			Driver d = new Driver();
			d.printWelcome();
			d.runMenu();
			d.printGoodbye();
	}
	 //Prints a welcome to the user
	 //Jack Donoghue
	 private void printWelcome()
	 {
		 System.out.println("Welcome to the Hair and Tortoise game!\n");	        
	 } 
	 
	// I chose to limit the main menu to 4 intractable options to not overload the user with pointless options
	//Troubleshoot offers the user a chance to ask questions
	//Instructions provide a clear outline of game rules and hints on how to play
	//Play Game consists of player setup and laying the game itself 
	//Troubleshoot can be exited by typing 'Bye'
	//Instructions can be exited by simply pressing any button
	//Jack Donoghue
	private int mainMenu(){ 			
        System.out.println("Hare And Tortoise");
        System.out.println("---------");     
        System.out.println("  1) Troubleshoot");    
        System.out.println("  2) Instructions");    
        System.out.println("  3) Play Game"); 
        System.out.println("-----------------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        boolean goodInput = false;
		int option = 0;
		do{
			try {
        option = input.nextInt();
        goodInput=true;
	}
	catch (Exception e){
		String throwOut = input.nextLine();
		System.out.println("Number required - you entered text");
	}
	}while(!goodInput);
        return option;
	   }
		 
	//in runMenu I defined my the menus choices and called the respective classes and methods needed.
	//Case 1 is my Troubleshoot option it prompts the user for a response and uses a hash set to generate 
	//either a defined response or the randomly generated responses if none of the users inputs match a defined key.
	//Case 2 calls the instructions method in Responder and displays them.
	//Case 3 contain both processPlayers and takeTurns, the order of these methods is essential as we must first
	//add players to the game before we can takeTurns.
	//Jack Donoghue
	private void runMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {           
            switch (option)
            {
               case 1:   System.out.println("Please type your question?");
           				HashSet<String> input = reader.getInput();
           				while(!input.contains("bye"))
           				{
           					String responses = responder.generateResponse(input);
           					System.out.println(responses);              	 
           					input = reader.getInput();
           				}
            	         break;
               case 2:   responder.instructions();
                         break;
               case 3:   processPlayers();
               			 printBoard();
               			 takeTurns();
               			 break;
               default:  System.out.println("Invalid option entered: " + option);
                         break;
             }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  
            option = mainMenu();           
        }
        System.out.println("Exiting... bye");
        System.exit(0);
    }
	
	/**
	* Reads input from the user and adds
	* players to the array list of type player
	* using the board method add 
	* @author Samantha Sheehan
	* 
	*/
	public void addPlayer(){		
		input.nextLine();
        System.out.print("Enter the Players Name:  ");
        String playerName = input.nextLine();
        board.add(player=new Player(playerName));        
	}	
	/**
	* Takes input from the user of how many players
	* to add then uses for loops to add and
	* print each player
	* @author Samantha Sheehan, Jack Donoghue(exception handling)
	* 
	*/
	private void processPlayers(){
		boolean goodInput = false;
		int numberPlayers =0;
		do{
			try {
				System.out.print("How many players would like to play? ");
					numberPlayers = input.nextInt();
					numberOfPlayers(numberPlayers);
					goodInput=true;
				}
			
			catch (Exception e){
				String throwOut = input.nextLine();
				System.out.println("Number required - you entered text");
			}
			}while(!goodInput);		
		board=new Board();		
		for(int i=0; i<numberPlayers;i++){
			addPlayer();
		}
		for(int i=0; i<numberPlayers;i++){
			System.out.println(board.getPlayers().get(i)+"\n");
		}
	}
	
	/**
	* Ensures there are only 2-6 players
	* @author Samantha Sheehan
	* @param numberOfPlayers
	*/
	private void numberOfPlayers(int numberOfPlayers){
		if((numberOfPlayers<2)||(numberOfPlayers>6)){
			System.out.println("You can only have 2-6 players.");
			System.out.print("Please try again\n");
			processPlayers();
		}
			
	}
	
	/**
	* Plays the game for each player in a loop
	* @author Samantha Sheehan
	* 
	*/
	private void takeTurns(){
		int numberOfPlayers=board.getPlayers().size();
		while(numberOfPlayers!=0){
			int numberPlayers= board.getPlayers().size();
			for(int i=0; i<numberPlayers;i++){				
				playGame(i);
			}
		}
	}
	
	/**
	* Contains the sequence of methods
	* required to play the game. Takes
	* in the index value from the method
	* in order to keep track of the takeTurns
	* current player and checks that moves are valid.
	* @param index
	* @author Samantha Sheehan, Jack Donoghue(exception handling)
	* 
	*/
	private void playGame(int index){
		System.out.print("\n---Player Number " + (index+1) +" it's your turn---\n");
		System.out.println(board.getPlayers().get(index));
		carrotSquare(index); 
		lettuceSquare(index);
		twoSquare(index);
		threeSquare(index);
		fourSquare(index);
		multiSquare(index);
		boolean goodInput = false;
		int numberOfPlaces = 0;
		do{
		try{
		System.out.print("How many places would you like to move?");
		numberOfPlaces=input.nextInt();	
		goodInput=true;
		}
		catch (Exception e){
			String throwOut = input.nextLine();
			System.out.println("Number required - you entered text");
		}
		}while(!goodInput);
		invalidMoves(index, numberOfPlaces);
		updateCarrots(index, numberOfPlaces);
		updateBoardPosition(index, numberOfPlaces);
		hareSquare(index);
		tortoiseSquare(index,numberOfPlaces);
		finishLineSquare(index);
	}
	/**
	* updates the current players carrot holdings
	* using the index value,taken from the play 
	* game method, and map carrots method in the board class.
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void updateCarrots( int index, int numberOfPlaces){
		if (numberOfPlaces!=0){
			int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
			int carrotsSpent=board.mapCarrotValues(numberOfPlaces-1);
			if(carrotsAvailable>=carrotsSpent){
				carrotsAvailable-=carrotsSpent;
				board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable);
				System.out.println("\nYou have spent " + carrotsSpent + " carrots.");
			}
			else{
				System.out.println("You do not have enough carrots");
				resetPlayer(index);
			}
		}
		else{
			System.out.println("You must move at least one place.");
			resetPlayer(index);
		}
	}
	
	

	/**
	* updates the current players board position
	* using the index value,taken from the play 
	* game method
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void updateBoardPosition(int index, int numberOfPlaces){
		int boardPosition=board.getPlayers().get(index).getBoardPosition();
		boardPosition+=numberOfPlaces;	
		board.getPlayers().get(index).setBoardPosition(boardPosition);

		System.out.println("Your current position on the board is " + boardPosition+"\n");
	}
	
	/**
	* List of methods which contain invalid moves
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void invalidMoves(int index,int numberOfPlaces){
		noMoveBack(index, numberOfPlaces);
		noLettuce(index, numberOfPlaces);
		spaceTaken(index, numberOfPlaces);
	}	

	/**
	* Gives the player a choice to reset 
	* their statistics if they have no legal moves
	* @param  index
	* @author Samantha Sheehan
	* 
	*/
	private void resetPlayer(int index){
		System.out.print("If you have no moves to make you may move back to the start.\n");
		System.out.print("Would you like to go back to the start? (y/n): \n");
		char restart=input.next().charAt(0);
		if((restart=='y') || (restart=='Y')){
			System.out.println("You have moved back to the start.\n"+
					"Your carrots have been reset to 65.\n"+
					"Your lettuce remains the same.");
			board.getPlayers().get(index).setCarrotsAvailable(65);
			board.getPlayers().get(index).setBoardPosition(0);
		}
		else if((restart=='n') || (restart=='N')){
			playGame(index);
		}
		else{
			System.out.print("I said '(y/n):'\n");
			resetPlayer(index);
		}
	}
	
	/**
	* Ensures players cannot land in the 
	* same position as another player
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void spaceTaken(int index, int numberOfPlaces){
		int numberPlayers=board.getPlayers().size();
		for (int i=0;i<numberPlayers;i++){
			if(board.getPlayers().get(index).getBoardPosition()+numberOfPlaces==board.getPlayers().get(i).getBoardPosition()){
				System.out.println("You cannot land on another player!");
				System.out.println("Please try again.");
				resetPlayer(index);
			}
		}
	}
	
	/**
	* Ensures players cannot move backwards unless 
	* landing on a tortoise square, and players cannot
	* move forward to a tortoise square
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void noMoveBack(int index, int numberOfPlaces){
		int[] tortoiseSquares={11,15,19,24,30,37,43,50,56};
		for (int i=0;i<tortoiseSquares.length;i++){
				if(((board.getPlayers().get(index).getBoardPosition()+numberOfPlaces!=tortoiseSquares[i])&&(numberOfPlaces<0))
						||((board.getPlayers().get(index).getBoardPosition()+numberOfPlaces==tortoiseSquares[i])&&(numberOfPlaces>0))){
					System.out.print("You can only move back to a tortoise square!!");
					System.out.print("Please try again");
					resetPlayer(index);
				}
		}
	}
	
	/*Lettuce squares (if you have no lettuce cards). 
	 * Lettuce method.
	 * List of the board positions the lettuce squares appear on.  
	 * If you have less than one lettuce you cannot move to a lettuce square.
	 * Prints out instructions if you move to a lettuce square if you do not have enough lettuce cards. 
	 * Richard Newton*/
	private void noLettuce(int index, int numberOfPlaces){
		int [] lettuceSquares={10,22,42,57};
		for (int i=0;i<lettuceSquares.length;i++){
			if(board.getPlayers().get(index).getBoardPosition()+numberOfPlaces==lettuceSquares[i]){
				if(board.getPlayers().get(index).getLettuce()<1){
					System.out.print("You cannot land on a lettuce square\n if you have no lettuce!!");
					System.out.print("Please try again!");
					resetPlayer(index);
				}			
			}
		}
	}
	
	/**
	* skip a turn is not working
	* index out of bounds error if the player 
	* s the final player in the index as it can't +1
	* I've tried to do this a few different ways with
	* booleans and putting it different places but 
	* none of them worked
	* @param  index
	* @author Samantha Sheehan
	* 
	*/
	/*public void skipTurn(int index){
		if (index==board.getPlayers().size()){
			//takeTurns();//playGame(0);//index=0;playGame(index);//
		}
		else{
			playGame(index+1);
		}
	}*/
	
	/*AUTHOR: Fritz G. Santos. 
	This states where the hare is positioned on the board.*/
	private void hareSquare(int index)
	{
		int[] hareSquares = {1,3,6,14,25,31,34,39,46,51,58,62};
		for (int i=0; i<hareSquares.length; i++)
		{
			if(board.getPlayers().get(index).getBoardPosition() == hareSquares[i])
			{
				System.out.println("You have landed on a Hare!");
				hareOnBoard(index);
			}
		}
	}
	
	//AUTHOR: Fritz G. Santos.
    private void hareOnBoard(int index)
	{
		//switch ((int)(n * Math.random()) 
    	//idea taken from http://math.hws.edu/javanotes/c3/s6.html
    	//Section: 3.6.4  Definite Assignment and switch Statements
    	
    	
	    switch( (int)(5 * Math.random()))
		{
			case 0:
				System.out.println("Count your carrot cards face up to the table so that everyone will know how many you have left");
					
				board.hareCard1(index);
					
			break;
						
			case 1:
				System.out.println("Draw 10 carrots for each lettuce you still hold."
								+ " If you have none left, miss a turn.");
				board.hareCard2(index);
					
			break;	
						
			case 2:
				System.out.println("Restore your carrot holding to exactly 65."
								+ " If you have more than 65, pay extras to the carrot patch,"
								+ " if fewer, draw extras from the carrot patch");
				board.hareCard3(index);
			break;
						
			case 3:
				System.out.println("Lose half of your carrots!"
								+ "If an odd number, keep the odd one");
				board.hareCard4(index);
						
			break;
					
			case 4:
				System.out.println("Shuffle the hare cards and receive from each player 1 carrot for doing so."
								+ " Do not replace this card at the bottom of the pack but include it in the shuffle");
						
				board.hareCard5(index);
						
			break;
						
			default:
					System.out.println("If there are more players behind you than in front of you, miss a turn."
							+ " If not, play again."
							+ " If equal, of course, play again.");
						
					//hareCard6();
			break;
					
		}
	}
	/**
	* Checks if the player land on a carrot square
	* and performs the actions required using methods
	* from the board class, because I couldn't figure 
	* out how to skip a turn this feature isn't working
	* @param  index
	* @author Samantha Sheehan, Jack Donoghue(exception handling)
	* 
	*/
    private void carrotSquare(int index){
    	int[] carrotSquares= {2,5,13,21,26,33,38,40,49,55,59,61,63} ;
		for (int i=0;i<carrotSquares.length;i++){
			if (board.getPlayers().get(index).getBoardPosition()==carrotSquares[i]){
				System.out.println("You have landed on a carrot square!"
						+"\n You have three choices:"
						+ "\n 1) Skip a turn and collect  10 carrots"
						+ "\n 2) Skip a turn and deposit 10 carrots"
						+ "\n 3) Take your turn as normal");
				boolean goodInput = false;
				int choices = 0;
				do{
				try{
				choices=input.nextInt();
				goodInput=true;
				}
				catch (Exception e){
					String throwOut = input.nextLine();
					System.out.println("Number required - you entered text");
				}
				}while(!goodInput);
				board.carrotChoices(index, choices);				
			}
		}	
    }
    
    /* Lettuce squares (if you do have lettuce cards). 
	 * As before, lists the board position of the lettuce squares.
	 * Prints out instructions, what happens when you land on a lettuce square when you have lettuce cards to spend.
	 * explains that you will lose a lettuce card.
	 * gives the instruction depending on your position how many carrots you get.
	 * After you get your carrots the board up dates with your total of carrots the player has
	 * plays the game (moves on to the next player */ 	
	private void lettuceSquare(int index){
		int [] lettuceSquares={10,22,42,57};
		for (int i=0;i<lettuceSquares.length;i++){
			if(board.getPlayers().get(index).getBoardPosition()==lettuceSquares[i]){
				System.out.println("You have landed on a lettuce square!!\n"+
						"You must chew one lettuce!!\n"+
						"Collect carrots 10 times the amount of your position on the board.\n"+
						"Skip one turn.");
				int lettuce=board.getPlayers().get(index).getLettuce();
				board.getPlayers().get(index).setLettuce(lettuce-1);
				int relativePosition=board.playerPosition(index)+1;
				int addCarrots=relativePosition*10;
				int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
				board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+addCarrots);
				//skipTurn(index);
			}
		}  
	}
	
	/**
	* Checks if the player has moved back
	* to a tortoise square and adds 10 carrots 
	* for every move backwards using a method 
	* from the board class
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	private void tortoiseSquare(int index, int numberOfPlaces){
		int[] tortoiseSquares={11,15,19,24,30,37,43,50,56};
		for (int i=0;i<tortoiseSquares.length;i++){
				if((board.getPlayers().get(index).getBoardPosition()+numberOfPlaces==tortoiseSquares[i])&&(numberOfPlaces<0)){
					System.out.println("You have landed on a tortoise square"+
							"Collect 10 carrots for every step you moved back!");
					board.tortoise(index, numberOfPlaces);				
				}
		}
	}	
	
	//The twoSquare method uses a primitive array to store board values.
	//It works by getting the getting the current players input(index) and checking if their board position 
	//is the same as the numbers defined in the primitive array.
	//it then checks the players position to ensure they are second.
	//if their actualPosition==2 then they receive +20 carrots.
	//Jack Donoghue
    private void twoSquare(int index){ 				
    	int[] twoSquares= {8,17,23,29,35,41,47,53};	
		for (int i=0;i<twoSquares.length;i++){
			if (board.getPlayers().get(index).getBoardPosition()==twoSquares[i]){ 
				System.out.println("You have landed on a number 2 square!");
				int actualPosition=board.playerPosition(index)+1;
				int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
				if(actualPosition==2){
					System.out.println("You are in second position collect 20 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+20);
				}
			}
		}
	}
    
    //The multiSquare is a similar concept to the twoSquare but with multiple possible outcomes
    //to create multiple outcomes I used multiple if statements to define what would happen 
    //if you landed on any of these squares depending on the position you are in in the game. 
    //Jack Donoghue
	private void multiSquare(int index){
    	int[] multiSquares= {7,16,32,34,48,60} ;		
		for (int i=0;i<multiSquares.length;i++){
			if (board.getPlayers().get(index).getBoardPosition()==multiSquares[i]){
				System.out.println("You have landed on a 1,5,6 square!");
				int actualPosition=board.playerPosition(index)+1;
				int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
				if(actualPosition==1){
					System.out.println("You are in first position collect 10 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+10);
				}
				if(actualPosition==5){
					System.out.println("You are in fifth position collect 50 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+50);
				}
				if(actualPosition==6){
					System.out.println("You are in sixth position collect 60 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+60);
				}
			}
		}
	}
		
	//the threeSquare and fourSquare act the same as the twoSquare
	//Jack Donoghue
	private void threeSquare(int index){
    	int[] threeSquares= {4,12,20,28,36,44,52} ;		
		for (int i=0;i<threeSquares.length;i++){
			if (board.getPlayers().get(index).getBoardPosition()==threeSquares[i]){
				System.out.println("You have landed on a 3 square!");
				int actualPosition=board.playerPosition(index)+1;
				int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
				if(actualPosition==3){
					System.out.println("You are in third position collect 30 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+30);
				}
			}
		}
	}
	
	private void fourSquare(int index){
    	int[] fourSquares= {9,18,27,45,54} ;		
		for (int i=0;i<fourSquares.length;i++){
			if (board.getPlayers().get(index).getBoardPosition()==fourSquares[i]){
				System.out.println("You have landed on a 4 square!");
				int actualPosition=board.playerPosition(index)+1;
				int carrotsAvailable=board.getPlayers().get(index).getCarrotsAvailable();
				if(actualPosition==4.){
					System.out.println("You are in third position collect 40 carrots");
					board.getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+40);
				}
			}
		}
	}
	
	 private void finishLineSquare(int index)
	 {	
    	int playerPosition = board.playerPosition(index) +1;    
    	if((board.getPlayers().get(index).getBoardPosition() == 64) &&
    			board.getPlayers().get(index).getLettuce() == 0)
    	{
    		if((playerPosition == 1) && (board.getPlayers().get(index).getCarrotsAvailable() <= 10))
    		{
    			System.out.println("Congratulations! You have finished the game in first place."); 
    		}
    		
    		if((playerPosition == 2) && (board.getPlayers().get(index).getCarrotsAvailable() <=20))
    		{
    			System.out.println("Congratulations! You have finished the game in second place.");
    		}
    		
    		if((playerPosition == 3) && (board.getPlayers().get(index).getCarrotsAvailable() <=30))
    			
    		{
    			System.out.println("Congratulations! You have finished the game in third place.");
    		}
    		
    		if((playerPosition == 4) && (board.getPlayers().get(index).getCarrotsAvailable() <=40))
    			
    		{
    			System.out.println("Congratulations! You have finished the game in fourth place.");
    		}
    		
    		if((playerPosition == 5) && (board.getPlayers().get(index).getCarrotsAvailable() <=50))
    			
    		{
    			System.out.println("Congratulations! You have finished the game in fifth place.");
    		}
    		
    		if((playerPosition == 6) && (board.getPlayers().get(index).getCarrotsAvailable() <=60))
    			
    		{
    			System.out.println("Congratulations! You have finished the game in sixth place.");
    		}
    		
    		
    		//end game 
    		
    		int numPlayers = board.getPlayers().size();
    		
    		for (int i=0; i<numPlayers; i ++)
    		{
    			if (board.getPlayers().get(i).getBoardPosition() == 64)
    			{
    				System.out.println("All players have completed the game!");
    				System.exit(0);
    			}
    		}
    		    			
    		
    	}         	
	}
	 
	//this provides a termination message for the user 
	//Jack Donoghue
	private void printGoodbye()
	{
		System.out.println("Game Over");
  	}
	
	private void printBoard(){
		System.out.println("Board Layout");
		System.out.println("==============");
		System.out.println("Square number 0:  Start");
		System.out.println("Square number 1:  Hare");
		System.out.println("Square number 2:  Carrot");
		System.out.println("Square number 3:  Hare");
		System.out.println("Square number 4:  Three");
		System.out.println("Square number 5:  Carrot");
		System.out.println("Square number 6:  Hare");
		System.out.println("Square number 7:  One, Five, Six");
		System.out.println("Square number 8:  Two");
		System.out.println("Square number 9:  Four");
		System.out.println("Square number 10:  Lettuce");
		System.out.println("Square number 11:  Tortoise");
		System.out.println("Square number 12:  Three");
		System.out.println("Square number 13:  Carrot");
		System.out.println("Square number 14:  Hare");
		System.out.println("Square number 15:  Tortoise");
		System.out.println("Square number 16:  One, Five, Six");
		System.out.println("Square number 17:  Two");
		System.out.println("Square number 18:  Four");
		System.out.println("Square number 19:  Tortoise");
		System.out.println("Square number 20:  Three");
		System.out.println("Square number 21:  Carrot");
		System.out.println("Square number 22:  Lettuce");
		System.out.println("Square number 23:  Two");
		System.out.println("Square number 24:  Tortoise");
		System.out.println("Square number 25:  Hare");
		System.out.println("Square number 26:  Carrot");
		System.out.println("Square number 27:  Four");
		System.out.println("Square number 28:  Three");
		System.out.println("Square number 29:  Two");
		System.out.println("Square number 30:  Tortoise");
		System.out.println("Square number 31:  Hare");
		System.out.println("Square number 32:  One, Five, Six");
		System.out.println("Square number 33:  Carrot");
		System.out.println("Square number 34:  One, Five, Six");
		System.out.println("Square number 35:  Two");
		System.out.println("Square number 36:  Three");
		System.out.println("Square number 37:  Tortoise");
		System.out.println("Square number 38:  Carrot");
		System.out.println("Square number 39:  Hare");
		System.out.println("Square number 40:  Carrot");
		System.out.println("Square number 41:  Two");
		System.out.println("Square number 42:  Lettuce");
		System.out.println("Square number 43:  Tortoise");
		System.out.println("Square number 44:  Three");
		System.out.println("Square number 45:  Four");
		System.out.println("Square number 46:  Hare");
		System.out.println("Square number 47:  Two");
		System.out.println("Square number 48:  One, Five, Six");
		System.out.println("Square number 49:  Carrot");
		System.out.println("Square number 50:  Tortoise");
		System.out.println("Square number 51:  Hare");
		System.out.println("Square number 52:  Three");
		System.out.println("Square number 53:  Two");
		System.out.println("Square number 54:  Four");
		System.out.println("Square number 55:  Carrot");
		System.out.println("Square number 56:  Tortoise");
		System.out.println("Square number 57:  Lettuce");
		System.out.println("Square number 58:  Hare");
		System.out.println("Square number 59:  Carrot");
		System.out.println("Square number 60:  One, Five, Six");
		System.out.println("Square number 61:  Carrot");
		System.out.println("Square number 62:  Hare");
		System.out.println("Square number 63:  Carrot");
		System.out.println("Square number 64:  Finish");
	}

}