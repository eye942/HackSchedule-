package hello;

public class SandwichCalculator {
	private final int NUM_QUESTIONS = 3;

	private int[] numberData = new int[NUM_QUESTIONS];
	
	// method to enter the score into the array
	public void enterScore(int questionNumber)
	{
		// TODO fill in the code here
	}
	
	// method that returns the score to the GreetingController
	public int getScore()
	{
		int totalScore = 0;
		
		for (int i = 0; i < numberData.length; i++)
		{
			totalScore = totalScore + numberData[i];
		}
		
		return totalScore;
	}
}
