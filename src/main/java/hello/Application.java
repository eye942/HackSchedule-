package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

@SpringBootApplication @Controller
public class Application extends SpringBootServletInitializer
{	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	// Simply runs the applications
    public static void main(String[] args) {
    	//QuestionsParser.parseQuestionsFromFile();
    	QuestionsParser.setScores();
        SpringApplication.run(Application.class, args);
    }
}
