/**
* Creates an object of type reader which 
* take input from the scanner
* @author Jack Donoghue
* @version 1.0 (12.03.2016)
*/
import java.util.HashSet;
import java.util.Scanner;

public class InputReader
{
    Scanner input;

    public InputReader()
    {
      input = new Scanner(System.in);
    }


    public HashSet<String> getInput() 
    {
        System.out.print("> ");               
        String inputLine = input.nextLine().trim().toLowerCase();

        String[] wordArray = inputLine.split(" ");  

        // add words from array into hashset 
        HashSet<String> words = new HashSet<String>();
        for(String word : wordArray) {
            words.add(word);
        }
        return words;
    }
}