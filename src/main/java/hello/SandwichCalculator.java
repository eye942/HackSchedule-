package hello;

public class SandwichCalculator {
	private boolean[] numberData;
	
	public SandwichCalculator(boolean[] answersParameter)
	{
		numberData = answersParameter;
	}
	
	// method that returns the total score
	// EXPECTS ANSWERS IN true, false FORMAT
	private int getTotalScore()
	{
		int totalScore = 0;
		
		for (boolean bool : numberData)
		{
			if (bool)
			{
				totalScore++;
			}
		}
		
		return totalScore;
	}
	
	// Returns the result out of the given max result
	public int calculateResult(int maxResult)
	{
		return (int)(((double)(getTotalScore())/numberData.length)*maxResult);
	}
}
