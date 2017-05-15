package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    // Returns the results page when valid
    @RequestMapping(value="/results",
    				method=RequestMethod.POST)
    public String result(@RequestBody Answers answers) // Puts HTML form into Answers
    {
    	final int MAX_SCORE = 100;
    	
    	boolean[] answerList = answers.getAnswers();
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
	    		
	    		// One line has an asterisk, so that line will be rewritten
	    		if (line.contains("*") && score >= MAX_SCORE/2 && score <= MAX_SCORE)
	    		{
	    			line = "<p>It seems to be a sandwich."  +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + ".</p>";
	    		}
	    		else if (line.contains("*") && score < MAX_SCORE/2 && score >= 0)
	    		{
	    			line = "<p>It does not seem to be a sandwich." +
    						" It has a  score of " + score + 
    						" out of " + MAX_SCORE + ".</p>";
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
}
