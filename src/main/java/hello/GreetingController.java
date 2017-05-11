package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{


    @RequestMapping("/greeting")
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
}
