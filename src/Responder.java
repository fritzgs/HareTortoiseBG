import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
* The Responder class represents a response
* generator object. It is used to generate an
* automatic response.
*
* @author Jack Donoghue
* @version 1.0 (12.03.2016)
*/
public class Responder
{
	Scanner input;
    private HashMap<String, String> responseMap;
    private ArrayList<String> defaultResponses;
    private Random randomGenerator;
 
    public Responder()
    {
        responseMap = new HashMap<String, String>();
        defaultResponses = new ArrayList<String>();
        fillResponseMap();
        fillDefaultResponses();
        randomGenerator = new Random();
        
        
    }
  
    public String generateResponse(HashSet<String> words)
    {
        Iterator<String> it = words.iterator();
        while(it.hasNext()) {
            String word = it.next();
            String response = responseMap.get(word);
            if(response != null) {
                return response;
            }
        }
        return pickDefaultResponse();
    }
   
   //these are the 'keys' and 'values' that are used in our response map
   // 
    
    public void fillResponseMap()
    {
        responseMap.put("win", 
                        "To win you need to reach the finish square with less than 10 carrots\n" +
                        "if you are 1st place, 20 for 2nd and 30 for 3rd and have no lettuce,etc.");
        responseMap.put( "finish",
                        "To win you need to reach the finish square with less than 10 carrots\n" +
                        "if you are 1st place, 20 for 2nd and 30 for 3rd and have no lettuce,etc");
        responseMap.put( "help",
                        "To win you need to reach the finish square with less than 10 carrots\n" +
                        "if you are 1st place, 20 for 2nd and 30 for 3rd and have no lettuce,etc");
        responseMap.put("move",
                        "If you cannot afford to move forward you must either return to a tortoise\n" +
                        "or move back to the start and recieve 65 carrots.\n"+
                        "our software?");
        responseMap.put("performance", 
                        "This games performance is optimal, however yours is questionable" );
        responseMap.put("carrot", 
                        "Carrots are used to traverse through the squares\n" );
        responseMap.put("buggy", 
                        "I have no idea what you are talking about.");
        responseMap.put("lettuce", 
                        "You must chew all of your lettuce before you pass the finish line");
        responseMap.put("expensive", 
                        "The key is to travel in short bursts and plan your moves");
        responseMap.put("fun", 
                        "error 404 fun not found");
    }
// these are the instructions used in the main menu
// I used "\n" to create a space in the lines to prevent a blob of words from appearing on screen
    
  public void instructions()
  {
	  System.out.println("Welcome to the Hair and Tortoise game!.\n"+
	  			 "\n");
           
      System.out.println("The instructions are as follows\n"+
	             "\n");
      System.out.println("1)All players must get rid of all lettuce cards before they \n"+
				 "can finish the game\n"+
	             "\n");
      System.out.println("2)You can not finish the race unless you have less than 10 cards for \n"+
				 "1st place, 20 carrots for 2nd place and 30 carrots for 3rd place, etc\n"+
	             "\n");
      System.out.println("3)You may move forward to any square except a tortoise square\n"+
	             "\n");
      System.out.println("4)You can move as far as you like if you have the carrots to get there\n"+
	             "\n");
      System.out.println("5)Later in the game, you can move back to the tortoise squares and gain 10\n"+
	             " carrots for every square you move back.\n"+
          	 "\n");
      System.out.println("6)Carrot squares allow the player who land on them to gain 10 carrots on their\n"+
	             "next turn or if you are near to the finish you can deposit 10 carrots\n"+
	             "\n");
      System.out.println("7)Lettuce squares allow the player to leave a lettuce behind, but they\n"+
	             "must miss a turn/n"+
	             "\n");
      System.out.println("8)Hare squares allow the player to draw 1 of 7 cards where they will be\n"+
	  		     "be instructed on the actions they must take. \n"+
	             "\n");
      System.out.println("9)If you do run out of carrots and cannot make a legal move you must move\n"+
 		         "back to the start and restore your carrot count to 65, you dont need to\n"+
	             "pick up any lettuces/n"+
	             "\n");
     
  }
 

  //this is where the default responses are defined
  
    private void fillDefaultResponses()
    {
        defaultResponses.add("That sounds odd. Could you describe that problem in more detail?");
        defaultResponses.add("I need a bit more information on that.");
        defaultResponses.add("Many of these solutions can be found in Instuctions, (2) in main menu");
    }
    // randomly selects an response from the above
    private String pickDefaultResponse()
    {
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }
    
    
}