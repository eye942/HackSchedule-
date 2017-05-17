package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

@SpringBootApplication @Controller
public class Application extends SpringBootServletInitializer
{	
	@Override // Will be executed once run on server
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		GreetingController.setMax(); // Sets max score
        return application.sources(Application.class);
    }
	
	// Simply runs the applications
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
