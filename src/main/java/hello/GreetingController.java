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
    public String result(@RequestBody Answers answers)
    {
    	SandwichCalculator sandCalc = new SandwichCalculator(answers.toAnArray());
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
    
    public @ResponseBody String byParameter(@RequestParam("foo") String foo) {
        return "Mapped by path + method + presence of query parameter! (MappingController) - foo = "
               + foo;
    }
}
