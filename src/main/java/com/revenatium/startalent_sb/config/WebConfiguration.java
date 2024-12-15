package com.revenatium.startalent_sb.config;

import com.revenatium.startalent_sb.config.tenant.TenantInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final Optional<TenantInterceptor> tenantInterceptor;

    public WebConfiguration(Optional<TenantInterceptor> tenantInterceptor) {
        this.tenantInterceptor = tenantInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        tenantInterceptor.ifPresent(registry::addWebRequestInterceptor);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedHeaders("*")
                    .exposedHeaders("Authorization")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE","OPTIONS");
            }
        };
    }
}
