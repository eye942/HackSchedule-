package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{	
	// Simply runs the applications
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //QuestionsParser parser = new QuestionsParser();
    }
}
