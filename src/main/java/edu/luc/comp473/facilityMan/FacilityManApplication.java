package edu.luc.comp473.facilityMan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacilityManApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FacilityManApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
	}

}
