package com.revenatium.startalent_sb.config;

import com.revenatium.startalent_sb.config.tenant.TenantInterceptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestWebConfig {
    @Bean
    public TenantInterceptor mockTenantInterceptor() {
        return Mockito.mock(TenantInterceptor.class);
    }
}
