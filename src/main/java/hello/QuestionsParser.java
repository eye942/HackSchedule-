package hello;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionsParser {
	static Scanner questionScanner;
	static FileWriter writer;
	
	public QuestionsParser() throws IOException {
		// creating the scanner for the questions file
		try {
			questionScanner = new Scanner(new File("questions"));
		} catch (FileNotFoundException error)
			{error.printStackTrace();}
		
		writer = new FileWriter("index.html");
	}
	
	// reads the questions and returns a string formatted in HTML
	public static void parseQuestionsFromFile()
	{
		FileWriter fw;
		try
		{
			questionScanner = new Scanner(new File("questions"));
			fw = new FileWriter("index.html");
			fw = new FileWriter("index.html", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
		
		
		
		
		
			ArrayList<String> questions = new ArrayList<String>();
			
			while (questionScanner.hasNextLine())
			{
				questions.add(questionScanner.nextLine());
			}
			
			// WRITING THE HTML FILE BASED ON THE QUESTIONS IN THE QUESTION FILE
			try {
				// Empty current
				
				
				
	            FileWriter writer = new FileWriter("index.html", false);
	            writer.write("");
	            
	            pw.println("<!DOCTYPE html>\n");
	            pw.println("<html>\n");
	            pw.println("<body>\n");
	            pw.println("<h1>Are you eating a sandwich?</h1>\n");
	            pw.println("<form name=\"form\" action=\"http://localhost:8080/results\" method=\"POST\">\n");
	            
	            for (int i = 0; i < questions.size(); i++) {
	            	pw.println("<p>");
	            	pw.println((i+1) + ". ");
	            	pw.println(questions.get(i));
	            	pw.println("<p>\n<input type=\"radio\" name=\""+ (i+1) + "\" value=true checked> Yes<br>");
	            	pw.println("<input type=\"radio\" name=\""+ (i+1) + "\" value=false> No<br>");
	            	pw.println("\n");
	            }
	            
			  	pw.println("</div>\n<p></p>\n</form>\n");
			  	pw.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
			  	pw.println("<script>\nfunction forward()\n{\nvar information = $(\"form\").serializeArray();");
			  	pw.println("var toReturn = [];\nfor (i = 0; i < information.length; i++)\n{\n");
			  	pw.println("toReturn[i] = information[i].value;\n}\n$.ajax({\nurl:\"/results\",\n");
			  	pw.println("type: \"POST\",\ncontentType: \"application/json; charset=utf-8\",\n");
			  	pw.println("contentType: \"application/json; charset=utf-8\",\ndatatype: \"json\",\n");
			  	pw.println("data: JSON.stringify({answers:toReturn}),\nsuccess:function(data, textStatus, xhr)\n{\n");
			  	pw.println("document.open();\ndocument.write(xhr.responseText);\ndocument.close();\n}\n");
			  	pw.println("});\n}\n</script>\n<button onclick=\"forward()\">Sandwich?</button>\n</body>\n</html>");
	
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			questionScanner.close();
			pw.flush();
			pw.close();
		} catch(IOException e)
		{
			//TODO
		}
	}
}
