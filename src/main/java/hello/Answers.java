package hello;

public class Answers
{
	private int first = -1;
	private int second = -1;
	private int third = -1;
	
	public int getFirst()
	{
		return first;
	}
	
	public void setFirst(int first)
	{
		this.first = first;
	}
	
	public int getSecond()
	{
		return second;
	}
	
	public void setSecond(int second)
	{
		this.second = second;
	}
	
	public int getThird()
	{
		return third;
	}
	
	public void setThird(int third)
	{
		this.third = third;
	}
	
	public int[] toAnArray()
	{
		int[] toReturn = new int[3];
		toReturn[0] = first;
		toReturn[1] = second;
		toReturn[2] = third;
		return toReturn;
	}
	
}
