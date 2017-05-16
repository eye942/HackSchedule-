package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{
	private static int maxScore;
	
    @RequestMapping(value="/")
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
    
    // Returns the results page when valid
    @RequestMapping(value="/results",
    				method=RequestMethod.POST)
    public String result(@RequestBody Answers answers) // Puts HTML form into Answers
    {
    	final int MAX_SCORE = maxScore;
    	
    	int[] answerList = answers.getAnswers();
    	// SandCalc obj created, which holds the array of
    	// answers made with answerArray method
    	SandwichCalculator sandCalc = new SandwichCalculator(answerList);
    	int score = sandCalc.calculateResult(MAX_SCORE); // Calculates score based on array values
    	
    	String text = "";
    	Scanner scan = null;

    	try {
			scan = new Scanner(new File("results.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	if (scan != null)
    	{
	    	while (scan.hasNextLine()) // Goes through each line of results.html
	    	{
	    		String line = scan.nextLine(); // Each line stored in this string
	    		String itIsNPercentASandwich = "<br>It is " + (int)(100*((double)score/(MAX_SCORE))) + "% a sandwich, according to Radical Sandwich Anarchism's <i>Definition of a Sandwich</i>.";
	    		
	    		// One line has an asterisk, so that line will be rewritten
	    		if (line.contains("*") && score >= 3*MAX_SCORE/4 && score <= MAX_SCORE)
	    		{
	    			line = "<p>It seems to be a sandwich."  +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + "." + itIsNPercentASandwich + "</p>";
	    		}
	    		else if (line.contains("*") && score <= MAX_SCORE/4 && score >= 0)
	    		{
	    			line = "<p>It does not seem to be a sandwich." +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + "." + itIsNPercentASandwich + "</p>";
	    		}
	    		else if (line.contains("*") && score < 3*MAX_SCORE/4 && score > MAX_SCORE/4)
	    		{
	    			line = "<p>Honestly, we can't be sure whether it is a sandwich or not." +
	    					" It has a  score of " + score + 
    						" out of " + MAX_SCORE + "." + itIsNPercentASandwich + "</p>";
	    		}
	    		else if (line.contains("*") && (score < 0 || score > MAX_SCORE))
	    		{
	    			line = "<p>Please don't mess with our code, we know what we're doing." +
    						"</p>";
	    		}
	    		
	    		
	    		text += line + "\n";
	    	}
    	}

        return text;
    }
    
    public static void changeMaxScore(int mxScore)
    {
    	maxScore = mxScore;
    }
}
