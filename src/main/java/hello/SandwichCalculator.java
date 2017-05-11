package hello;

public class SandwichCalculator {
	private int[] numberData;
	
	public SandwichCalculator(int[] answersParameter)
	{
		numberData = answersParameter;
	}
	
	// method to enter the score into the array
	public void enterScore(int questionNumber)
	{
		// TODO fill in the code here
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
	
	public double calcualteResult()
	{
		return getTotalScore()/numberData.length;
	}
}
