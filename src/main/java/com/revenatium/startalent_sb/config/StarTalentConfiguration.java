package com.revenatium.startalent_sb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "startalent", ignoreUnknownFields = true)
public class StarTalentConfiguration {

    private Jwt jwt;

    @Setter
    @Getter
    public static class Jwt {
        private String secret;
        private String expirationMs;
    }
}
