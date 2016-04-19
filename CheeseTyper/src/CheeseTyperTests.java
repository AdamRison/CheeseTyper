import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

public class CheeseTyperTests {
	
	/**
	*	Tests that CalculateBaseWordScore returns the word length as the score for words that are
	*	under 6 characters in length.
	*/
	@Test
	public void CalculateBaseWordScoreWithWordUnderFiveLettersTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore("fox"), 3);
	}
	
	/**
	*	Tests that CalculateBaseWordScore returns the length of the word plus one when the size of the
	*	word is 6 or greater, but less than or equal to 10.  For this case, Burgos is 6 letters in length, so the method
	*	should return 7.
	*/
	@Test public void CalculateBaseWordScoreWithWordMoreThanFiveLettersTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore("Burgos"), 7);
	}
	
	/**
	 * Tests that CalculateBaseWordScore returns an integer equal to the length of the word, plus 3 if
	 * its length is between 11 and 15 characters.
	 */
	@Test
	public void CalculateBaseWordScoreWithWordOverTenLettersTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore("superfluous"), ("superfluous".length()+3));
	}
	
	/**
	 * Tests that CalculateBaseWordScore returns an integer equal to the length of the word, plus 5 if
	 * its length is between 16 and 20 characters.
	 */
	@Test
	public void CalculateBaseWordScoreWithWordFifteenLettersTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore("MoreThanFifteenChar"), 24);
	}
	
	/**
	 * Tests that CalculateBaseWordScore returns the int 0 when given an empty String.
	 */
	@Test
	public void CalculateBaseWordScoreEmptyStringTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore(""), 0);
	}
	
	/**
	 * Tests that CalculateBaseWordScore returns the integer 0 when given a null String.
	 * Previously did not check for this case and it caused a problem.
	 */
	@Test
	public void CalculateBaseWordScoreNullStringTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore(null), 0);
	}
	
	/**
	 * Tests that CalculateBaseWordScore will take any word greater than 20 characters in length
	 * and return its size plus seven.
	 */
	@Test
	public void CalculateBaseWordScoreVeryLongStringTest(){
		assertEquals(CheeseTyper.CalculateBaseWordScore("Pneumonoultramicroscopicsilicovolcanoconiosis"), 
				("Pneumonoultramicroscopicsilicovolcanoconiosis".length()+7));
	}
	
	/**
	 * Tests that RandomlySelectString does not return the same String each time it is called.
	 * This is a nondeterministic test, so there is a chance that it will fail.  However, as long
	 * as it passes roughly 653 out of 654 trials, this test passes.
	 */
	@Test
	public void RandomlySelectStringTest(){
		assertNotEquals(CheeseTyper.RandomlySelectString(), CheeseTyper.RandomlySelectString());
	}
	
	/**
	 * Tests that CalculateLetterBonusPoints will return an integer greater than 0 when given
	 * a String that contains multiple bonus point letters. 
	 */
	@Test
	public void CalculateLetterBonusPointsWithBonusPointLettersTest(){
		assertTrue(CheeseTyper.CalculateLetterBonusPoints("Queso Blanco con Frutas --Pina y Mango")>0);
	}
	
	/**
	 * Tests that CalculateLetterBonusPoints will return 0 if it receives an empty String.
	 */
	@Test
	public void CalculateLetterBonusPointsWithEmptyStringTest(){
		assertEquals(CheeseTyper.CalculateLetterBonusPoints(""),0);
	}
	
	/**
	 * Tests that CalculateLetterBonusPoints will return 0 if it receives a null String.
	 */
	@Test
	public void CalculateLetterBonusPointsWithNullStringTest(){
		assertTrue(CheeseTyper.CalculateLetterBonusPoints(null)==0);
	}
	
	/**
	 * Tests that CalculateLetterBonusPoints will return 0 if it receives a String that does
	 * not contain any of the bonus letters.
	 */
	@Test
	public void CalculateLetterBonusPointsWithoutBonusPointLettersTest(){
		assertTrue(CheeseTyper.CalculateLetterBonusPoints("Sottocenare al Tartufo")==0);
	}
	
	/**
	 * Tests that CheckEqualityOfWords will return true if it receives two Strings that
	 * are equal to each other.  This test inspired CheckEqualityOfWordsWithVaryingUpperAndLowerCasesTest
	 * because the first parameter was case insensitive but not the second parameter.
	 */
	@Test
	public void CheckEqualityOfWordsWhenWordsAreEqual(){
		assertTrue(CheeseTyper.CheckEqualityOfWords("Swiss", "Swiss"));
	}
	
	/**
	 * Tests that CheckEqualityOfWords will return false if the two Strings it receives are not
	 * equal to each other.
	 */
	@Test
	public void CheckEqualityOfWordsWhenWordsAreNotEqual(){
		assertFalse(CheeseTyper.CheckEqualityOfWords("Sardo", "Swiss"));
	}
	
	/**
	 * Tests that CheckEqualityOfWords works as case insensitive
	 */
	@Test
	public void CheckEqualityOfWordsWithVaryingUpperAndLowerCasesTest(){
		assertTrue(CheeseTyper.CheckEqualityOfWords("cHeDdeR", "chedder"));
	}
	
	/**
	 * Tests that CalculateTotalScore accurately calculates the score for an ArrayList that
	 * contains one six letter String that contains no bonus letters.  I discovered that I cannot
	 * use Mockito with static methods.  I also learned that to get around this problem, I would
	 * need to make wrapper objects, but I do not have the time.
	 */
	@Test
	public void CalculateTotalScoreWithOneItemTest(){
		ArrayList<String> cheeses = new ArrayList<String>();
		cheeses.add("cheddar");
		assertEquals(CheeseTyper.CalculateTotalScore(cheeses), 8);
	}
	
	/**
	 * Tests that CalculateTotalScore accurately calculates the score for an ArrayList that
	 * contains one three letter String that contains two bonus letters.  This test suffers from the same
	 * issue mentioned above for CalculateTotalScoreWithOneItemTest
	 */
	@Test
	public void CalculateTotalScoreWithOneItemWithBonusPoints(){
		ArrayList<String> cheeses = new ArrayList<String>();
		cheeses.add("Zam");
		assertEquals(CheeseTyper.CalculateTotalScore(cheeses), "Zam".length()+2);
	}
	
	/**
	 * Tests that CalculateTotalScore accurately calculates the score for an ArrayList that contains
	 * one 15 character String with multiple bonus letters.  This tests that the function can appropriately
	 * add when multiple conditions exist.  This test suffers from the same issue mentioned above 
	 * for CalculateTotalScoreWithOneItemTest.
	 */
	@Test
	public void CalculateTotalScoreMultipleConditions(){
		ArrayList<String> cheeses = new ArrayList<String>();
		cheeses.add("blue zam cheese");
		assertEquals(CheeseTyper.CalculateTotalScore(cheeses), "blue zam cheese".length()+3+3);
	}
	
	/**
	 * Tests that CalculateTotalScore accurately calculates the score for an ArrayList that contains
	 * multiple Strings of varying lengths.
	 */
	@Test
	public void CalculateTotalScoreWithMultipleArrayItems(){
		ArrayList<String> cheeses = new ArrayList<String>();
		cheeses.add("1");
		cheeses.add("two");
		cheeses.add("three");
		assertEquals(CheeseTyper.CalculateTotalScore(cheeses), 1+3+5);
	}
	
	/**
	 * Tests that CalculateTotalScore will return a score of 0 upon receiving a null
	 * ArrayList.
	 */
	@Test
	public void CalculateTotalScoreWithNullArray(){
		ArrayList<String> cheeses = null;
		assertEquals(CheeseTyper.CalculateTotalScore(cheeses), 0);
	}
	
	/**
	 * Tests the the proper String is returned when GetGameStatusMessage receives the Time's Up code (1).
	 * This test is mainly here for code coverage.
	 */
	@Test
	public void GetGameStatusMessageTimeRanOutTest(){
		assertEquals(CheeseTyper.GetGameStatusMessage(1), "Time's up!");
	}
	
	/**
	 * Tests the the proper String is returned when GetGameStatusMessage receives the Incorrect 
	 * Answer code (2). This test is mainly here for code coverage.
	 */
	@Test
	public void GetGameStatusMessageIncorrectAnswerTest(){
		assertEquals(CheeseTyper.GetGameStatusMessage(2), "That's incorrect! Game over!");
	}
	
	/**
	 * Tests that in the event this method is called for some reason without one of the proper
	 * input codes, it returns an empty String.
	 */
	@Test
	public void GetGameStatusMessageErrorCaseTest(){
		assertEquals(CheeseTyper.GetGameStatusMessage(218), "");
	}
	
	/**
	 * Tests that GetEndGameStatus will return the Time's Up code (1) if the user did not prematurely end
	 * the game by entering the incorrect String.
	 */
	@Test
	public void GetEndGameStatusTimeIsUpTest(){
		assertEquals(CheeseTyper.GetEndGameStatus(false, true), 1);
	}
	
	/**
	 * Tests that GetEndGameStatus will return the Incorrect Answer code (2) if the user prematurely ended
	 * the game by entering the incorrect String.
	 */
	@Test
	public void GetEndGameStatusIncorrectAnswerTest(){
		assertEquals(CheeseTyper.GetEndGameStatus(true, false), 2);
	}
	
	/**
	 * Tests that GetEndGameStatus will return the Time's Up code (1) if the user did not enter an
	 * incorrect input and the timer has not run out.  The Time's Up code is the default return code.  Even
	 * though it is returned, it will not be displayed to the user unless the game ends, which in this case, it
	 * has not.
	 */
	@Test
	public void GetEndGameStatusNothingHappenedYetTest(){
		assertEquals(CheeseTyper.GetEndGameStatus(false, false), 1);
	}
}
