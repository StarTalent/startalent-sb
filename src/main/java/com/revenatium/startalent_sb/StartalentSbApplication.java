package com.revenatium.startalent_sb;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StartalentSbApplication {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StartalentSbApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StartalentSbApplication.class, args);
        Environment env = context.getEnvironment();
        // Se imprimen las variables de entorno definidas en el archivo .env
        log.debug("DB_URL: {}", env.getProperty("DB_URL"));
        log.debug("DB_USERNAME: {}", env.getProperty("DB_USERNAME"));
        log.debug("DB_PASSWORD: {}", env.getProperty("DB_PASSWORD"));
	}

}
