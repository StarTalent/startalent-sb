package com.revenatium.startalent_sb.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        try {
            return jsonNode != null ? objectMapper.writeValueAsString(jsonNode) : null;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error converting JSON to string", ex);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? objectMapper.readTree(dbData) : null;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error converting string to JSON", ex);
        }
    }
}