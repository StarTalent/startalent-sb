package com.revenatium.startalent_sb.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonNodeConverterTests {

    private final JsonNodeConverter converter = new JsonNodeConverter();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("debería convertir a cadena JSON cuando el nodo JSON no es nulo")
    void shouldConvertToDatabaseColumn_ReturnsJsonString_WhenJsonNodeIsNotNull() throws Exception {
        JsonNode jsonNode = objectMapper.readTree("{\"key\":\"value\"}");
        String result = converter.convertToDatabaseColumn(jsonNode);

        assertThat(result).isEqualTo("{\"key\":\"value\"}");
    }

    @Test
    @DisplayName("debería convertir a nulo cuando el nodo JSON es nulo")
    void shouldConvertToDatabaseColumn_ReturnsNull_WhenJsonNodeIsNull() {
        String result = converter.convertToDatabaseColumn(null);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("debería convertir a nodo JSON cuando los datos de la base de datos no son nulos")
    void shouldConvertToEntityAttribute_ReturnsJsonNode_WhenDbDataIsNotNull() throws Exception {
        String dbData = "{\"key\":\"value\"}";
        JsonNode result = converter.convertToEntityAttribute(dbData);

        assertThat(result.get("key").asText()).isEqualTo("value");
    }

    @Test
    @DisplayName("debería convertir a nulo cuando los datos de la base de datos son nulos")
    void shouldConvertToEntityAttribute_ReturnsNull_WhenDbDataIsNull() {
        JsonNode result = converter.convertToEntityAttribute(null);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("debería lanzar una excepción cuando hay un error al convertir JSON a cadena")
    void shouldConvertToEntityAttribute_ThrowsException_WhenDbDataIsInvalid() {
        String invalidDbData = "{\"key\":";

        assertThatThrownBy(() -> converter.convertToEntityAttribute(invalidDbData))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Error converting string to JSON");
    }
}
