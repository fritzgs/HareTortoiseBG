/**
* The Player Class creates an object of type
* Player to store players statistics
* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
* @version 1.0 (12.03.2016)
*/
public class Player {
	private String playerName;
    private int carrotsAvailable;
    private int boardPosition;
    private int lettuce;
    
    public Player(String playerName){    	
    	this.playerName=playerName;
    	this.carrotsAvailable=65;
    	this.lettuce=3;
    	this.boardPosition=0;
    }
    
    /**
	* Accessors for the player object.
	* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
	* 
	*/   
    public String getPlayerName(){
    	return playerName;
    }
    public int getCarrotsAvailable(){
    	return carrotsAvailable;
    }
    public int getBoardPosition(){
    	return boardPosition;
    }
    public int getLettuce(){
    	return lettuce;
    }
    
    /**
	* Mutators for the player object.
	* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
	* 
	*/ 
    public void setPlayerName(String playerName){
    	this.playerName = playerName;
    }
    public void setCarrotsAvailable(int carrotsAvailable){
    	if((carrotsAvailable>=0)&&(carrotsAvailable<=820)){
    		this.carrotsAvailable=carrotsAvailable;
    	}
    	else{
    		this.carrotsAvailable=65;
    	}
    	
    }
    public void setLettuce(int lettuce){
    	if((lettuce>=0)&&(lettuce<=3)){
    		this.lettuce=lettuce;
    	}
    	else{
    		this.lettuce=3;
    	}
    }
    public void setBoardPosition(int boardPosition){
    	this.boardPosition = boardPosition;
    }
    
    /**
	* toString method provides a more suitable string 
	* representation of a particular type(player)
	* accessed from the predefined object class
	* @author Jack Donoghue, Richard Newton, Fritz Gerald Santos, Samantha Sheehan
	* 
	*/   
    public String toString(){
    	return "Player Name: " + playerName
                + "\nNumber of carrots: " + carrotsAvailable
                + "\nPosition on the board: " + boardPosition
                + "\nNumber of lettuce: " + lettuce;
    }
}

