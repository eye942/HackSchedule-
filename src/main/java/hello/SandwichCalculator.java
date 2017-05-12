package hello;

public class SandwichCalculator {
	private int[] numberData;
	
	public SandwichCalculator(int[] answersParameter)
	{
		numberData = answersParameter;
	}
	
	// method that returns the total score
	// EXPECTS ANSWERS IN 0=NO, 1=YES FORMAT
	private int getTotalScore()
	{
		int totalScore = 0;
		
		for (int i = 0; i < numberData.length; i++)
		{
			totalScore += numberData[i];
		}
		
		return totalScore;
	}
	
	// Returns the result out of the given max result
	public int calculateResult(int maxResult)
	{
		return (int)(((double)(getTotalScore())/numberData.length)*maxResult);
	}
}
