package com.revenatium.startalent_sb.config;

import com.revenatium.startalent_sb.config.tenant.TenantIdentifierResolver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestTenantConfig {
    @Bean
    public TenantIdentifierResolver tenantIdentifierResolver() {
        return new TenantIdentifierResolver() {
            @Override
            public String resolveCurrentTenantIdentifier() {
                return "test-tenant";
            }
        };
    }
}
