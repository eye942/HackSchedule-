package hello;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionsParser {
	static Scanner questionScanner;
	static FileWriter writer;
	
	// reads the questions and returns a string formatted in HTML
	public static void parseQuestionsFromFile()
	{
		try
		{
			// Reading the question file
			FileWriter fw;
			questionScanner = new Scanner(new File("questions"));
			fw = new FileWriter("index.html");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
		
			ArrayList<String> questions = new ArrayList<String>();
			ArrayList<String> weights = new ArrayList<String>();
			ArrayList<Integer> realWeights = new ArrayList<Integer>();
			
			
			while (questionScanner.hasNextLine())
			{
				String[] QuestionAnswer = questionScanner.nextLine().split(";");
				
				questions.add(QuestionAnswer[0]);
				
				weights.add(QuestionAnswer[1]);
			}
			
			realWeights = getRealWeights(weights);
			
			// Pass max score to GreetingController:
			int score = 0;
			for (Integer i : realWeights)
				score += i;
			GreetingController.changeMaxScore(score);
			
			// WRITING THE HTML FILE BASED ON THE QUESTIONS IN THE QUESTION FILE
            
            pw.println("<!DOCTYPE html>\n");
            pw.println("<html>\n");
            pw.println("<body>\n");
            pw.println("<h1>Are you eating a sandwich?</h1>\n");
            pw.println("<form name=\"form\" action=\"http://localhost:8080/results\" method=\"POST\">\n");
            
            for (int i = 0; i < questions.size(); i++) {
            	pw.println("<p>");
            	pw.println((i+1) + ". ");
            	pw.println(questions.get(i));
            	pw.println("Weight: " + realWeights.get(i));
            	pw.println("<p>\n<input type=\"radio\" name=\""+ (i+1) + "\" value=" + realWeights.get(i) +" checked> Yes<br>");
            	pw.println("<input type=\"radio\" name=\""+ (i+1) + "\" value=0> No<br>");
            	pw.println("\n");
            }
            
		  	pw.println("<p></p>\n</form>\n");
		  	pw.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		  	pw.println("<script>\nfunction forward()\n{\nvar information = $(\"form\").serializeArray();");
		  	pw.println("var toReturn = [];\nfor (i = 0; i < information.length; i++)\n{\n");
		  	pw.println("toReturn[i] = information[i].value;\n}\n$.ajax({\nurl:\"/results\",\n");
		  	pw.println("type: \"POST\",\ncontentType: \"application/json; charset=utf-8\",\n");
		  	pw.println("contentType: \"application/json; charset=utf-8\",\ndatatype: \"json\",\n");
		  	pw.println("data: JSON.stringify({answers:toReturn}),\nsuccess:function(data, textStatus, xhr)\n{\n");
		  	pw.println("document.open();\ndocument.write(xhr.responseText);\ndocument.close();\n}\n");
		  	pw.println("});\n}\n</script>\n<button onclick=\"forward()\">Sandwich?</button>\n<img src=\"http://gifrific.com/wp-content/uploads/2012/08/noms-shaggy-sandwich.gif\">\n</body>\n</html>");
            
			questionScanner.close();
			
			pw.flush();
			pw.close();
			
		} catch(IOException e)
		{
			//TODO
		}
	}
	
	private static ArrayList<Integer> getRealWeights(ArrayList<String> str)
	{
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		
		for (String s : str)
		{
			arrList.add(Integer.parseInt(s));
		}
		
		return arrList;
	}
	
	public static void setScores()
	{
		try
		{
			// Reading the question file
			questionScanner = new Scanner(new File("questions"));
		
			ArrayList<String> questions = new ArrayList<String>();
			ArrayList<String> weights = new ArrayList<String>();
			ArrayList<Integer> realWeights = new ArrayList<Integer>();
			
			
			while (questionScanner.hasNextLine())
			{
				String[] QuestionAnswer = questionScanner.nextLine().split(";");
				
				questions.add(QuestionAnswer[0]);
				
				weights.add(QuestionAnswer[1]);
			}
			
			realWeights = getRealWeights(weights);
			
			// Pass max score to GreetingController:
			int score = 0;
			for (Integer i : realWeights)
				score += i;
			GreetingController.changeMaxScore(score);
			
			questionScanner.close();
			
		} catch(IOException e)
		{
			//TODO
		}
	}
}
