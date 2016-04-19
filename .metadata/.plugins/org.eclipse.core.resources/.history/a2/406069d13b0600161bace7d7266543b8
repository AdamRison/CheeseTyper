import java.io.*;
import java.util.*;

public class CheeseTyper {
	
	static Scanner keyboard = new Scanner (System.in);
	private static String currentCheese;
	private static ArrayList<String> correctlyTypedCheeses = new ArrayList<String>();

	public static void main (String args[]){
		System.out.println("You have 60 seconds to enter as many cheeses as you see appear on screen! Go! \n");
		int resultStatusString = 1;
		UpdateCheeseDisplayedForUser();
		int gameSessionTime = 60000;
		long startTime = System.currentTimeMillis();
		boolean outOfTime = false;
		
		while(!outOfTime){
			if((System.currentTimeMillis() - startTime) >= gameSessionTime){
				outOfTime = true;
			}
			else{
				if(keyboard.hasNext()){
					outOfTime = !CheckEqualityOfWords(keyboard.nextLine(), currentCheese);
					resultStatusString = GetEndGameStatus(outOfTime, (System.currentTimeMillis() - startTime) >= gameSessionTime);
				}
			}
		}
		System.out.println(GetGameStatusMessage(resultStatusString)+"\nYour score is "+CalculateTotalScore(correctlyTypedCheeses));
	}
	
	/**
	 * Calculates the user's total score for the game by adding all word scores together.
	 * @return - integer value representing the user's total score
	 */
	public static int CalculateTotalScore(ArrayList<String> correctlyTypedStrings){
		int totalScore = 0;
		if(correctlyTypedStrings!=null){
			for(int i=0;i<correctlyTypedStrings.size();i++){
				totalScore += CalculateLetterBonusPoints(correctlyTypedStrings.get(i));
				totalScore += CalculateBaseWordScore(correctlyTypedStrings.get(i));
			}
		}
		return totalScore;
	}
	
	
	/**
	 * Returns a String that indicates how the game ended.  The first case occurs when the game's timer runs out.
	 * The second case occurs when the user incorrectly enters a cheese String.  The third case only occurs if 
	 * this method was called incorrectly.
	 * @param statusCode - integer indicating the case in which the game ended
	 * @return - String that indicates the way the game ended to the user
	 */
	public static String GetGameStatusMessage(int statusCode){
		String gameStatusMessage;
		if(statusCode == 1){
			gameStatusMessage = "Time's up!";
		}
		else if(statusCode == 2){
			gameStatusMessage = "That's incorrect! Game over!";
		}
		else{
			gameStatusMessage = "";
		}
		return gameStatusMessage;
	}
	
	/**
	 * Adds up the number of bonus points obtained from typing Strings with the letters 'b,' 'm,' 'p,'
	 * 'q,' and 'z,' which are supposedly some of the hardest characters to type in rapid succession.
	 * @param cheeseString - String to be checked for bonus point characters
	 * @return - integer value for the number of bonus points
	 */
	
	public static int CalculateLetterBonusPoints(String cheeseString){
		int bonusPoints = 0;
		if(cheeseString!=null){
			bonusPoints += cheeseString.length() - cheeseString.toLowerCase().replace("q", "").length();
			bonusPoints += cheeseString.length() - cheeseString.toLowerCase().replace("z", "").length();
			bonusPoints += cheeseString.length() - cheeseString.toLowerCase().replace("p", "").length();
			bonusPoints += cheeseString.length() - cheeseString.toLowerCase().replace("m", "").length();
			bonusPoints += cheeseString.length() - cheeseString.toLowerCase().replace("b", "").length();
		}
		return bonusPoints;
	}
	
	
	/**
	 * Calculates base word score based on the number of letters the word contains.  The more letters
	 * a word contains, the more points it is worth.
	 * @param word - Word whose score is desired
	 * @return - int value that represents the word's score
	 */
	public static int CalculateBaseWordScore(String word){
		int wordScore = 0;
		if(word!=null){
			wordScore = word.length();
			if(wordScore>20){
				wordScore+=7;
			}
			else if(wordScore>15){
				wordScore+=5;
			}
			else if(wordScore>10){
				wordScore+=3;
			}
			else if(wordScore>5){
				wordScore++;
			}
		}
		return wordScore;
	}
	
	/**
	 * Checks that two Strings are equal.  Used to check that the cheeseString and the 
	 * String that the user entered are equal to each other.  If they are correct, the cheeseString is 
	 * added to the ArrayList of correctlyTypedStrings and a new cheese is randomly selected
	 * @param cheeseString - String presented to the user to type back
	 * @param enteredString - String that the user entered
	 * @return - Boolean: True if the Strings are equal; false if the Strings are not equal
	 */
	public static boolean CheckEqualityOfWords(String cheeseString, String enteredString){
		boolean returnValue = false;
		if(cheeseString.toLowerCase().equals(enteredString.toLowerCase())){
			returnValue = true;
			correctlyTypedCheeses.add(cheeseString);
		}
		return returnValue;
	}
	
	/**
	 * Randomly returns a Cheese String (not to be confused with String cheese).
	 * @return Randomly selected String from Cheeses String array
	 */
	public static String RandomlySelectString(){
		String resultString;
		Random rand = new Random();
		resultString = Cheeses.CheeseStrings[rand.nextInt(Cheeses.CheeseStrings.length-1)];
		return resultString.toLowerCase();
	}
	
	//TODO
	public static int GetEndGameStatus(boolean killGameStatus, boolean timeExceededLimit){
		int resultStatusCode = 1;
		if(!killGameStatus && !timeExceededLimit){
			UpdateCheeseDisplayedForUser();
		}
		else if(killGameStatus){
			resultStatusCode = 2;
		}
		return resultStatusCode;
	}
	
	/**
	 * This method updates currentCheese with a new String and displays it for the user to copy.
	 */
	private static void UpdateCheeseDisplayedForUser(){
		currentCheese = RandomlySelectString();
		System.out.println(currentCheese);
	}
	
}
