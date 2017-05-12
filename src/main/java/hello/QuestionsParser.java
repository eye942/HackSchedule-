package hello;

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
	private static void parseQuestionsFromFile() {
		File questionSourceHTML = new File("index.html");
		
		int numberOfQuestions = 0;
		String formattedHTML;
		
		while (questionScanner.hasNextLine()) {
			numberOfQuestions++;
			questionScanner.nextLine();
		}
		
		// WRITING THE HTML FILE BASED ON THE QUESTIONS IN THE QUESTION FILE
		try {
            FileWriter writer = new FileWriter("index.html", true);
            
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<h1>Are you eating a sandwich?</h1>\n");
            writer.write("<form name=\"form\" action=\"http://localhost:8080/results\" method=\"POST\">\n");
            
            for (int i = 0; i < numberOfQuestions; i++) {
            	writer.write("<p>");
            	writer.write((i+1) + ". ");
            	//writer.write(*THE QUESTION FROM THE RESPECTIVE LINE*);
            	//writer.write(*the first line after the question*);
            	//writer.write(*the second line after the question*);
            	writer.write("\n");
            }
            
            writer.write("<p></p>\n");
            writer.write("<input type=\"submit\" value=\"Submit\">\n");
            writer.write("</form>\n</body>\n</html>");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
