package hello;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
		File questionSourceHTML = new File("index.html");
		ArrayList<String> questions = new ArrayList<String>();
		
		int numQuestions = 0;
		String formattedHTML;
		
		while (questionScanner.hasNextLine())
		{
			numQuestions++;
			questions.add(questionScanner.nextLine());
		}
		
		// WRITING THE HTML FILE BASED ON THE QUESTIONS IN THE QUESTION FILE
		try {
            FileWriter writer = new FileWriter("index.html", true);
            
            writer.flush();
            
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<h1>Are you eating a sandwich?</h1>\n");
            writer.write("<form name=\"form\" action=\"http://localhost:8080/results\" method=\"POST\">\n");
            
            for (int i = 0; i < numQuestions; i++) {
            	writer.write("<p>");
            	writer.write((i+1) + ". ");
            	writer.write(questions.get(i));
            	writer.write("<p>\n<input type=\"radio\" name=\""+ (i+1) + "\" value=true checked> Yes<br>");
            	writer.write("<input type=\"radio\" name=\""+ (i+1) + "\" value=false> No<br>");
            	writer.write("\n");
            }
            
		  	writer.write("</div>\n<p></p>\n</form>\n");
		  	writer.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		  	writer.write("<script>\nfunction forward()\n{\nvar information = $(\"form\").serializeArray();");
		  	writer.write("var toReturn = [];\nfor (i = 0; i < information.length; i++)\n{\n");
		  	writer.write("toReturn[i] = information[i].value;\n}\n$.ajax({\nurl:\"/results\",\n");
		  	writer.write("type: \"POST\",\ncontentType: \"application/json; charset=utf-8\",\n");
		  	writer.write("contentType: \"application/json; charset=utf-8\",\ndatatype: \"json\",\n");
		  	writer.write("data: JSON.stringify({answers:toReturn}),\nsuccess:function(data, textStatus, xhr)\n{\n");
		  	writer.write("document.open();\ndocument.write(xhr.responseText);\ndocument.close();\n}\n");
		  	writer.write("});\n}\n</script>\n<button onclick=\"forward()\">Sandwich?</button>\n</body>\n</html>");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
