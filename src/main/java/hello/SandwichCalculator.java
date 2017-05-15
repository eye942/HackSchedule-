package hello;

public class SandwichCalculator {
	private int[] numberData;
	
	public SandwichCalculator(int[] answersParameter)
	{
		numberData = answersParameter;
	}
	
	// method that returns the total score
	private int getTotalScore()
	{
		int totalScore = 0;
		
		for (int i : numberData)
		{
			totalScore += i;
		}
		
		return totalScore;
	}
	
	// Returns the result out of the given max result
	public int calculateResult(int maxResult)
	{
		return getTotalScore();
	}
}
