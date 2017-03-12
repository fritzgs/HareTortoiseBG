import java.util.ArrayList;
public class Board {
	/**
	* The Board class creates an object of type board
	* and contains most the functional methods  
	* which run the game
	* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
	* @version 1.0 (12.03.2016)
	*/
	private ArrayList<Player> players;
	
	public Board (){		
		players = new ArrayList<Player>();
	}
	
	public  void add(Player player){
		players.add (player);
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}	
	
	/**
	* Takes in the number of places the player 
	* wants to move and maps that number to the
	* correct carrot value and returns the
	* carrots the user must spend
	* @param numberOfPlaces
	* @return carrotsSpent
	* @author Samantha Sheehan
	* 
	*/
	public int mapCarrotValues(int numberOfPlaces){
		int carrotValue[]= new int[40];
		int carrotValueOne=1;
		int increment=2;
		for (int i=0; i<carrotValue.length; i++){
			carrotValue[i]=carrotValueOne;
			carrotValueOne+=increment;
			increment++;
		}
		int carrotsSpent=carrotValue[numberOfPlaces];
		return carrotsSpent;
	}
	

	/**
	* Takes in the index value which indicates the 
	* current player. Uses a primitive array to cycle
	* over the other players and returns the number
	* of players which are ahead of the current player
	* @param index
	* @return relativePosition
	* @author Samantha Sheehan
	* 
	*/
	public int playerPosition(int index)
	{	
		int counter=0;
		if (players.size()!=0){			
			for (int i = 0; i < players.size(); i++){
				if (players.get(i).getBoardPosition() > players.get(index).getBoardPosition() )  
					counter++;
				}		
		}
		return counter;
	}
	
	/**
	* takes in the value of the players choice
	* and either adds or subtract 10 carrots 
	* from the player
	* @param  index, choices
	* @author Samantha Sheehan
	* 
	*/
	public void carrotChoices(int index,int choices){
		if (choices==1){		
			int carrotsAvailable=getPlayers().get(index).getCarrotsAvailable()+10;
			getPlayers().get(index).setCarrotsAvailable(carrotsAvailable);
			//skipTurn(index);
		}
		if (choices==2){
			int carrotsAvailable=getPlayers().get(index).getCarrotsAvailable()-10;
			getPlayers().get(index).setCarrotsAvailable(carrotsAvailable);
			//skipTurn(index);
		}
	}
	
	/**
	* add 10 carrots for every move back from
	* a tortoise square
	* @param  index, numberOfPlaces
	* @author Samantha Sheehan
	* 
	*/
	public void tortoise(int index, int numberOfPlaces){
		int carrotsAvailable=getPlayers().get(index).getCarrotsAvailable();
		int addCarrots= -numberOfPlaces*10;
		getPlayers().get(index).setCarrotsAvailable(carrotsAvailable+addCarrots);	
	}
	
	//AUTHOR: Fritz G. Santos
		//HARE CARDS INSTRUCTIONS METHODS.
		
		public void hareCard1(int index)
		{
			System.out.print("Current amount of carrots: " + getPlayers().get(index).getCarrotsAvailable());
		}
		 
		 
		public void hareCard2(int index)
		{
			int timesLettuce = getPlayers().get(index).getLettuce();
			int a = getPlayers().get(index).getCarrotsAvailable();
			getPlayers().get(index).setCarrotsAvailable(a + (timesLettuce * 10));
			
			//If lettuce == 0 -> 10 * 0 = 0 therefore no additional carrots will be added (skip turn).
		}
		 	
		public void hareCard3(int index)
		{
			getPlayers().get(index).setCarrotsAvailable(65);
		}
		 	
		public void hareCard4(int index)
		{	
			//if current player carrots/2 = even number then half the current amount of carrots.
		 	if(getPlayers().get(index).getCarrotsAvailable() % 2 ==0);
		 	{
		 		int half = getPlayers().get(index).getCarrotsAvailable() / 2;
		 		getPlayers().get(index).setCarrotsAvailable(half);
		 	}
		 		
		 }
		 
		public void hareCard5(int index)
		{
			int numPlayers = getPlayers().size();
			
			for (int i=0; i<numPlayers; i ++)
			{
				//minus 1 carrot from every player
				int carrotsAvailable = getPlayers().get(i).getCarrotsAvailable();
				getPlayers().get(i).setCarrotsAvailable(carrotsAvailable - 1);
			}
			
			//Adds (1 * number of players) carrots to current player.
			int addPlusOne = getPlayers().get(index).getCarrotsAvailable() + (getPlayers().size() - 1);
			getPlayers().get(index).setCarrotsAvailable(addPlusOne);
			 
		}
	
}
