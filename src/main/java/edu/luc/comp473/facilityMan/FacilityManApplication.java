package edu.luc.comp473.facilityMan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class FacilityManApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacilityManApplication.class, args);
	}

}
