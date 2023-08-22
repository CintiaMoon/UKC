package org.cargain.ukc.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.cargain.ukc.commons", "org.cargain.ukc.content" })
public class UkcContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UkcContentApplication.class, args);
	}

}
