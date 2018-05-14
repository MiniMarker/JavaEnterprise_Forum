package no.cmarker.frontend;

import no.cmarker.Application;
import org.springframework.boot.SpringApplication;

/**
 * @author Christian Marker on 14/05/2018 at 22:04.
 */
public class LocalApplicationRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, "--spring.profiles.active=test");
	}
}
