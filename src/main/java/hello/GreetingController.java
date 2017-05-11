package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{

    @RequestMapping("/")
    public String sandwich()
    {
    	String text = "";
    	Scanner scan = null;

    	try {
			scan = new Scanner(new File("index.html"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
    
    @RequestMapping("/results")
    public String result(@RequestParam("1") int first, 
    					@RequestParam("1") int second,
    					@RequestParam("1") int third)
    {
    	SandwichCalculator sandCalc;
    	
    	sandCalc = new SandwichCalculator(new int[]{first,second,third});
    	
    	int score = 0;
    	final int MAX_SCORE = 100;
    	String text = "";
    	Scanner scan = null;

    	try {
			scan = new Scanner(new File("results.html"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
