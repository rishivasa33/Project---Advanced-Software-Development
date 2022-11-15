package dal.csci5308.project.group15.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuraApplication.class, args);
	}

}
