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
    
    // Returns the results page when valid
    @RequestMapping(value="/results",
    				method=RequestMethod.POST)
    public String result(@RequestBody Answers answers) // Puts HTML form into Answers
    {
    	final int MAX_SCORE = maxScore;
    	
    	int score = answers.answerSum(); // Calculates score based on array values
    	
    	String text = "";
    	Scanner scan = null;
    	
    	// Gets file path for a server
    	String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
    	absolute = absolute.substring(0, absolute.length() - 1);
    	absolute = absolute.substring(0, absolute.lastIndexOf("/") + 1);
    	absolute = absolute.substring(6);// 6 because this includes "file:/" at the beginning
    	// Goes up to WEB-INF in directory
    	
    	File file = new File(absolute.substring(System.getProperty("user.dir").length()) + 
								"classes/META-INF/resources/results.html");
    	// else if in STS:
    	//File file = new File("src/main/resources/META-INF/resources/results.html");
    	// Also for STS, change results.html redirect
    	
    	// Finds file
    	try
    	{
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	// Somewhat dynamic page that changes based on the score
    	if (scan != null)
    	{
	    	while (scan.hasNextLine()) // Cycles through each line of results.html
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
    
    // Max score is set by adding the weights
    public static void setMax()
    {
    	int[] weights = {6, 9, 7, 15, 2};
		int sum = 0;
		for (int i : weights)
			sum += i;
		maxScore = sum;
    }
}
