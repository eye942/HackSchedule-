package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{

	ArrayList<Integer> arrayInstance;
	
    @RequestMapping("/")
    public String sandwich()
    {
    	String text = "";
    	Scanner scan = null;

    	try {
			scan = new Scanner(new File("index.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	if (scan != null)
    	{
	    	while (scan.hasNextLine())
	    	{
	    		text += scan.nextLine() + "\n";
	    	}
    	}

        return text;
    }
    
    @RequestMapping(value="/results",
    				method=RequestMethod.POST)
    public String result(@RequestBody String answers)
    {
    	SandwichCalculator sandCalc = new SandwichCalculator(answerArray(answers));
    	int score = sandCalc.calculateResult();
    	
    	final int MAX_SCORE = 100;
    	String text = "";
    	Scanner scan = null;

    	try {
			scan = new Scanner(new File("results.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	if (scan != null)
    	{
	    	while (scan.hasNextLine() )
	    	{
	    		String line = scan.nextLine();
	    		
	    		if (line.contains("*") && score >= 50)
	    		{
	    			line = "<p>It seems to be a sandwich."  +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + ".</p>";
	    		}
	    		else if (line.contains("*") && score < 50)
	    		{
	    			line = "<p>It does not seem to be a sandwich." +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + ".</p>";
	    		}
	    		
	    		text += line + "\n";
	    	}
    	}

        return text;
    }
    
    private static int[] answerArray(String fullParameters)
    {
    	String[] toParse = fullParameters.split("&");
    	String[] answers = new String[toParse.length];
    	int[] finalAnswer = new int[toParse.length];
    	
    	for (int i = 0; i < toParse.length; i++)
    	{
    		String questionAnswerCombo = toParse[i];
    		String[] QACombo = questionAnswerCombo.split("=");
    		answers[i] = QACombo[1];
    	}
    	
    	for (int i = 0; i < toParse.length; i++)
    	{
    		String answer = answers[i];
    		
    		if (answer.equals("1"))
    			finalAnswer[i] = 1;
    		else if (answer.equals("0"))
    			finalAnswer[i] = 0;
    		else
    			finalAnswer[i] = -1;
    	}
    	
    	return finalAnswer;
    }
}
