package com.revenatium.startalent_sb.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * La función de esta clase es cargar las variables de entorno definidas en el archivo .env,
 * en el entorno de Spring Boot, de manera que puedan ser accedidas a través de la clase Environment.
 */
public class EnvPropertySourceLoader implements EnvironmentPostProcessor {

    private final Dotenv dotenv;

    public EnvPropertySourceLoader() {
        this(Dotenv.load());
    }

    public EnvPropertySourceLoader(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Properties props = new Properties();
        dotenv.entries().forEach(e -> props.setProperty(e.getKey(), e.getValue()));
        environment.getPropertySources().addFirst(new PropertiesPropertySource("dotenvProperties", props));
    }
}
