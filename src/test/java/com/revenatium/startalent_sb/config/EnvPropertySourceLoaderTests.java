package com.revenatium.startalent_sb.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvPropertySourceLoaderTests {

    @Test
    @DisplayName("debería cargar las propiedades definidas en el archivo .env")
    void shouldLoadDotenvProperties_fromFileDotenv() {
        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = new MutablePropertySources();
        when(environment.getPropertySources()).thenReturn(propertySources);

        SpringApplication application = mock(SpringApplication.class);
        EnvPropertySourceLoader loader = new EnvPropertySourceLoader();

        loader.postProcessEnvironment(environment, application);

        verify(environment).getPropertySources();
        assertTrue(propertySources.contains("dotenvProperties"));
        assertTrue(Objects.requireNonNull(propertySources.get("dotenvProperties")).containsProperty("DB_URL"));
        assertTrue(Objects.requireNonNull(propertySources.get("dotenvProperties")).containsProperty("DB_USERNAME"));
        assertTrue(Objects.requireNonNull(propertySources.get("dotenvProperties")).containsProperty("DB_PASSWORD"));
    }

    @Test
    @DisplayName("debería cargar las propiedades definidas en un objeto Dotenv mockeado")
    void shouldLoadMockDotenvProperties() {
        Dotenv dotenv = mock(Dotenv.class);
        Set<DotenvEntry> entries = Set.of(new DotenvEntry("key1", "value1"), new DotenvEntry("key2", "value2"));
        when(dotenv.entries()).thenReturn(entries);

        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = new MutablePropertySources();
        when(environment.getPropertySources()).thenReturn(propertySources);

        SpringApplication application = mock(SpringApplication.class);
        EnvPropertySourceLoader loader = new EnvPropertySourceLoader(dotenv);

        loader.postProcessEnvironment(environment, application);

        verify(environment).getPropertySources();
        assertTrue(propertySources.contains("dotenvProperties"));
        assertTrue(Objects.requireNonNull(propertySources.get("dotenvProperties")).containsProperty("key1"));
        assertTrue(Objects.requireNonNull(propertySources.get("dotenvProperties")).containsProperty("key2"));
    }

    @Test
    @DisplayName("debería no cargar ninguna propiedad si el objeto Dotenv está vacío")
    void shouldNotLoadProperties_whenDotenvIsEmpty() {
        Dotenv dotenv = mock(Dotenv.class);
        when(dotenv.entries()).thenReturn(Collections.emptySet());

        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = new MutablePropertySources();
        when(environment.getPropertySources()).thenReturn(propertySources);
        SpringApplication application = mock(SpringApplication.class);

        EnvPropertySourceLoader loader = new EnvPropertySourceLoader(dotenv);
        loader.postProcessEnvironment(environment, application);

        verify(environment).getPropertySources();
        assertTrue(propertySources.contains("dotenvProperties"));
        assertTrue(((Properties) Objects.requireNonNull(propertySources.get("dotenvProperties")).getSource()).isEmpty());
    }

    @Test
    @DisplayName("debería lanzar una excepción si el objeto Dotenv es nulo")
    void shouldThrowException_whenDotenvIsNull() {
        Dotenv dotenv = mock(Dotenv.class);
        when(dotenv.entries()).thenReturn(null);

        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = new MutablePropertySources();
        when(environment.getPropertySources()).thenReturn(propertySources);
        SpringApplication application = mock(SpringApplication.class);

        EnvPropertySourceLoader loader = new EnvPropertySourceLoader(dotenv);
        Assertions.assertThrows(NullPointerException.class, () -> loader.postProcessEnvironment(environment, application));
        assertEquals(0, propertySources.stream().map(PropertySource::getName).toArray().length);
    }
}
