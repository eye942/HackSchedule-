package hello;

public class Answers
{
	private int[] answers;
	
	public int[] getAnswers() {
		return answers;
	}

	public void setAnswers(int[] answers) {
		this.answers = answers;
	}
	
	public int answerSum() {
		int sum = 0;
		for (int i : answers)
			sum += i;
		return sum;
	}
}
